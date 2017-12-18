package SyraServerPackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

public class clientprocessor extends Thread {
Socket socket;
private DataInputStream dIn;
private DataOutputStream dOut;
private ObjectOutputStream oos;
private ObjectInputStream ois;
@Override
public void run() {
    System.out.println("new Thread client processor started.");
    try {
        socket.setSoTimeout(20000);
        dIn = new DataInputStream(socket.getInputStream());
        byte GoTo = dIn.readByte();
        System.out.println("GoTo : " + GoTo);
        switch(GoTo){
            case 1:
                dOut = new DataOutputStream(socket.getOutputStream());
                newClient(dIn.readInt(),dIn.readUTF());
                dIn.close();
                dOut.close();
                break;
            case 2:
                newScreen(dIn.readInt());
                dIn.close();
                ois.close();
                break;
            case 3:
                newMouse(dIn.readInt());
                dIn.close();
                ois.close();
                break;
            case 4:
                viewScreen(dIn.readInt());
                dIn.close();
                break;
            case 5:
                viewMouse(dIn.readInt());
                dIn.close();
                break;
            case 6:
                dOut = new DataOutputStream(socket.getOutputStream());
                getcommands(dIn.readInt());
                dIn.close();
                dOut.close();
                break;
            case 7:
                dOut = new DataOutputStream(socket.getOutputStream());
                putcommands(dIn.readInt());
                dIn.close();
                dOut.close();
                break;
        }
    } catch(java.lang.Exception ex) {
    System.out.println("Client processor1 : " + ex.toString());
    } finally {
        try {
            socket.close();
        } catch (java.lang.Exception ex) {
        System.out.println("client processor2 : " + ex.toString());
        }
    }
    System.out.println("Thread exited.");
}
private void newClient(int remoteId, String Passcode) {
    database db;
    db = new database();
    if(!db.error) {
        try {
            db.statement = db.connect.prepareStatement("select passcode from remote where remoteid = ? limit 1;");
            db.statement.setInt(1, remoteId);
            db.result = db.statement.executeQuery();
            if(db.result.next()) {
                String pc = db.result.getString("passcode");
            if(pc.equals(Passcode)) {
                dOut.writeUTF("allow");
            } else if(pc.equals("0")) {
            db.statement = db.connect.prepareStatement("UPDATE `syra`.`remote` SET `passcode` = ? WHERE `remoteid`= ? limit 1;");
            db.statement.setString(1, Passcode);
            db.statement.setInt(2, remoteId);
            db.statement.executeUpdate();
            dOut.writeUTF("allow");
            } else {
                dOut.writeUTF("no");
            }
            } else {
                dOut.writeUTF("no");
            }
            dOut.flush();
        } catch (SQLException | IOException ex) {
            System.out.println("checksession : " + ex.toString());
            db.close();
            try {
                dOut.writeUTF("no");
                dOut.flush();
            } catch (IOException ex1) {
                System.out.println("checksession : " + ex1.toString());
            }
        }
    }
    db.close();
}
private void newScreen(int key) {
    try {
        ois = new ObjectInputStream(socket.getInputStream());
        MainServer.rectangleContainer.put(key, ois.readObject());
        while(true) {
            MainServer.screenContainer.put(key, ois.readObject());
        }
    } catch (IOException | ClassNotFoundException ex) {
        System.out.println("newScreen:" + ex.toString());
    }
}
private void newMouse(int key) {
    try {
        ois = new ObjectInputStream(socket.getInputStream());
        while(true) {
            MainServer.mouseContainer.put(key, ois.readObject());
        }
    } catch (IOException | ClassNotFoundException ex) {
        System.out.println("newScreen:" + ex.toString());
    }
}
private void viewScreen(int rid) {
try {
    oos = new ObjectOutputStream(socket.getOutputStream());
    while(true) {
    if(MainServer.screenContainer.containsKey(rid)) {
    oos.writeBoolean(true);
    oos.writeObject(MainServer.rectangleContainer.get(rid));
    oos.writeObject(MainServer.screenContainer.get(rid));
    oos.flush();
    oos.reset();
    } else {
    oos.writeBoolean(false);
    oos.flush();
    oos.reset();
    }
    }
} catch(IOException ex) {
System.out.println("viewScreen : " + ex.toString());
}
}
private void viewMouse(int rid) {
try {
    oos = new ObjectOutputStream(socket.getOutputStream());
    while(true) {
    if(MainServer.mouseContainer.containsKey(rid)) {
        oos.writeBoolean(true);
        oos.writeObject(MainServer.mouseContainer.get(rid));
        oos.flush();
        oos.reset();
    } else {
    oos.writeBoolean(false);
    oos.flush();
    oos.reset();
    }
    }   
} catch(IOException ex) {
System.out.println("viewMouse : " + ex.toString());
}
}
private void getcommands(int rid){
    try {
    while(true) {
    if(MainServer.commandContainer.containsKey(rid)) {
    dOut.writeBoolean(true);
    dOut.writeUTF((String)MainServer.commandContainer.get(rid));
    dOut.flush();
    MainServer.commandContainer.put(rid,"0,0");
    } else {
    dOut.writeBoolean(false);
    dOut.flush();
    }
    }
    } catch(Exception ex) {
    System.out.println("GetCommands : " + ex.toString());
    }
}
private void putcommands(int rid) {
try {
    String cmd="";
    while(true) {
    if(MainServer.commandContainer.containsKey(rid)) {
    cmd = (String)MainServer.commandContainer.get(rid);
    MainServer.commandContainer.put(rid,"0,0");
    }
    else
    MainServer.commandContainer.put(rid,dIn.readUTF());
    }
    } catch(Exception ex) {
    System.out.println("GetCommands : " + ex.toString());
    }
}
}
