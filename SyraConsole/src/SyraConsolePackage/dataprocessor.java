package SyraConsolePackage;

import java.io.IOException;
import javax.swing.JLabel;


public class dataprocessor extends Thread {
int source;
SyraSocket c;

@Override
public void run() {
    c = new SyraSocket();
    if(!c.error) {
    try {
    switch(source) {
        case 1:
            break;
        case 2:
            c.dOut.writeByte(source);
            c.dOut.writeUTF(MainConsole.SESSION_ID);
            c.dOut.writeUTF(MainConsole.IP_ADDRESS);
            c.dOut.flush();
            home();
            c.dOut.writeByte(-1);
            c.dOut.flush();
            break;
    }
    c.closethis();
    } catch (IOException ex) {
    System.out.println(ex.toString());
    }
    }
}
void home() {
    try {
        int rbyte = c.dIn.readByte();
        if(rbyte == -2) {
            MainConsole.statusLabel.setText("[Error: User not Logged In]");
        } else {
            //notifications
            if (rbyte == -1) {
            MainConsole.notificationscroll.setVisible(false);
            } else if (rbyte == 1) {
            MainConsole.notification.removeAll();
            while(rbyte == 1) {
                //remoteid, name, username
                MainConsole.notification.add(new h_notification(c.dIn.readUTF(),c.dIn.readUTF(),c.dIn.readUTF()));
                rbyte = c.dIn.readByte();
            }
            MainConsole.notification.revalidate();
            MainConsole.notification.repaint();
            MainConsole.notificationscroll.setVisible(true);
            }
            
            //Sessions
            rbyte = c.dIn.readByte();
            if(rbyte == -1) {
                MainConsole.sessionholder.removeAll();
                MainConsole.sessionholder.add(new JLabel("No Active Remotes"));
                MainConsole.sessionholder.revalidate();
                MainConsole.sessionholder.repaint();
            } else if (rbyte == 1) {
                MainConsole.sessionholder.removeAll();
                while(rbyte == 1) {
                //remoteid, name, startat, control
                MainConsole.sessionholder.add(new h_remote(c.dIn.readUTF(),c.dIn.readUTF(),c.dIn.readUTF(),c.dIn.readUTF()));
                rbyte = c.dIn.readByte();
                }
                MainConsole.sessionholder.revalidate();
                MainConsole.sessionholder.repaint();
            }
            
            //technicians
            rbyte = c.dIn.readByte();
            if(rbyte == -1) {
                MainConsole.technicians.removeAll();
                MainConsole.technicians.add(new JLabel("No Technician Available"));
                MainConsole.technicians.revalidate();
                MainConsole.technicians.repaint();
            } else if (rbyte == 1) {
                MainConsole.technicians.removeAll();
                h_technicians ht = new h_technicians();
                while(rbyte == 1) {
                    //id, username, groupname
                    ht.newdata(c.dIn.readUTF(),c.dIn.readUTF(),c.dIn.readUTF());
                    rbyte = c.dIn.readByte();
                }
                ht.view();
                MainConsole.technicians.add(ht.tree);
                MainConsole.technicians.revalidate();
                MainConsole.technicians.repaint();
            }
            MainConsole.panels.show(MainConsole.mainPanel,"home");
            MainConsole.homepanels.show(MainConsole.home_bottom,"session");
        }
    } catch (IOException ex) {
        System.out.println(ex.toString());
        MainConsole.statusLabel.setText("[Detected] : " + ex.toString());
    }
}
}
