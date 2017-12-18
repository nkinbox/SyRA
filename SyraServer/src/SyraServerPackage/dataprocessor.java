package SyraServerPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class dataprocessor extends Thread {
Socket socket;
private DataInputStream dIn;
private DataOutputStream dOut;

@Override
public void run() {
    System.out.println("new Thread dataprocessor started.");
    try {
        int id = 0;
        socket.setSoTimeout(4000);
        dIn = new DataInputStream(socket.getInputStream());
        dOut = new DataOutputStream(socket.getOutputStream());
        boolean done = false;
        byte GoTo = dIn.readByte();
        if(GoTo != 1) {
        id = checksession(dIn.readUTF(),dIn.readUTF());
        if(id == 0) {
        dOut.writeByte(-2);
        dOut.flush();
        done=true;
        }
        }
        while(!done) {
        switch(GoTo){
            case 1:
                break;
            case 2:  //home
                home(id);
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                done = true;
        }
        if(!done)
        GoTo = dIn.readByte();
        }
        dIn.close();
        dOut.close();
    } catch(java.lang.Exception ex) {
    System.out.println("dataprocessor1 : " + ex.toString());
    } finally {
        try {
            socket.close();
        } catch (java.lang.Exception ex) {
        System.out.println("dataprocessor2 : " + ex.toString());
        }
    }
    System.out.println("Thread exited.");
}
private int checksession(String sessionid,String ipaddress) {
    int id = 0;
    Date today = new Date();
    Timestamp timestamp = new Timestamp(today.getTime());
    database db;
    db = new database();
    if(!db.error) {
        try {
            db.statement = db.connect.prepareStatement("select id from session where sessionid = ? and ipaddress = ? and expiringon > ? limit 1;");
            db.statement.setString(1, sessionid);
            db.statement.setString(2, ipaddress);
            db.statement.setTimestamp(3, timestamp);
            db.result = db.statement.executeQuery();
            if(db.result.next()) {
            id = db.result.getInt("id");
            }
        } catch (java.lang.Exception ex) {
            System.out.println("checksession : " + ex.toString());
            db.close();
            return id;
        }
    }
    db.close();
    return id;
}
private String getusername(int id) {
    String username = "Not_Available";
    database db;
    db = new database();
    if(!db.error) {
        try {
            db.statement = db.connect.prepareStatement("select username from technicians where id = ? limit 1;");
            db.statement.setInt(1, id);
            db.result = db.statement.executeQuery();
            if(db.result.next()) {
            username = db.result.getString("username");
            }
        } catch (java.lang.Exception ex) {
            System.out.println("username : " + ex.toString());
            db.close();
            return username;
        }
    }
    db.close();
    return username;
}
private void home(int id) {
    database db;
    db = new database();
    if(!db.error) {
        try {
            //Notifications
            db.statement = db.connect.prepareStatement("select remoteaccess.remoteid, remote.name, technicians.username from remoteaccess inner join remote on remote.remoteid = remoteaccess.remoteid inner join technicians on technicians.id = remote.id where remoteaccess.id = ? and remoteaccess.control = 'p' and remote.startat = remote.stopat;");
            db.statement.setInt(1, id);
            db.result = db.statement.executeQuery();
            while(db.result.next()) {
            dOut.writeByte(1);
            dOut.writeUTF(db.result.getString("remoteid"));
            dOut.writeUTF(db.result.getString("name"));
            dOut.writeUTF(db.result.getString("username"));
            dOut.flush();
            }
            dOut.writeByte(-1);
            dOut.flush();
            //Active remotes
            db.statement = db.connect.prepareStatement("select remote.remoteid, remote.name, remote.startat, remoteaccess.control from remote inner join remoteaccess on remoteaccess.remoteid = remote.remoteid where remoteaccess.id = ? and remoteaccess.control <> 'p' and remote.startat = remote.stopat order by remoteaccess.control desc;");
            db.statement.setInt(1, id);
            db.result = db.statement.executeQuery();
            while(db.result.next()) {
            dOut.writeByte(1);
            dOut.writeUTF(db.result.getString("remoteid"));
            dOut.writeUTF(db.result.getString("name"));
            dOut.writeUTF(db.result.getString("startat"));
            dOut.writeUTF(db.result.getString("control"));
            dOut.flush();
            }
            dOut.writeByte(-1);
            dOut.flush();
            //technicians
            db.statement = db.connect.prepareStatement("select technicians.id, technicians.username, technicians.groupname from technicians inner join session on technicians.id = session.id where session.sessionid <> '0' and technicians.createdby = (select createdby from technicians where id = ? limit 1);");
            db.statement.setInt(1, id);
            db.result = db.statement.executeQuery();
            while(db.result.next()) {
            dOut.writeByte(1);
            dOut.writeUTF(db.result.getString("id"));
            dOut.writeUTF(db.result.getString("username"));
            dOut.writeUTF(db.result.getString("groupname"));
            dOut.flush();
            }
            dOut.writeByte(-1);
            dOut.flush();
        } catch (SQLException | IOException ex) {
            System.out.println("home : " + ex.toString());
            db.close();
        }
    }
    db.close();
}
}
