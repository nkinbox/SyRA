package SyraConsolePackage;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class syraAction implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        RemoteViewer.loop = false;
       if(e.getSource() == MainConsole.home) {
           MainConsole.panels.show(MainConsole.mainPanel,"status");
           MainConsole.statusLabel.setText("Loading ...");
           MainConsole.home.setBackground(new Color(200,200,200));
           MainConsole.mydrive.setBackground(new Color(230,230,230));
           MainConsole.currentsession.setBackground(new Color(230,230,230));
           MainConsole.chat.setBackground(new Color(230,230,230));
           MainConsole.notes.setBackground(new Color(230,230,230));
           dataprocessor d;
           d = new dataprocessor();
           d.source = 2;
           d.start();
        }
       else if(e.getSource() == MainConsole.mydrive) {
           MainConsole.panels.show(MainConsole.mainPanel,"mydrive");
           MainConsole.home.setBackground(new Color(230,230,230));
           MainConsole.mydrive.setBackground(new Color(200,200,200));
           MainConsole.currentsession.setBackground(new Color(230,230,230));
           MainConsole.chat.setBackground(new Color(230,230,230));
           MainConsole.notes.setBackground(new Color(230,230,230));
        }
       else if(e.getSource() == MainConsole.currentsession) {
           RemoteViewer.loop = true;
           MainConsole.panels.show(MainConsole.mainPanel,"currentsession");
           MainConsole.home.setBackground(new Color(230,230,230));
           MainConsole.mydrive.setBackground(new Color(230,230,230));
           MainConsole.currentsession.setBackground(new Color(200,200,200));
           MainConsole.chat.setBackground(new Color(230,230,230));
           MainConsole.notes.setBackground(new Color(230,230,230));
           RemoteViewer s = new RemoteViewer(1);
           s.start();
           RemoteViewer m = new RemoteViewer(2);
           m.start();
           Socket socket;
            try {
                socket = new Socket(SyraSocket.SYRA_IP,SyraSocket.SYRA_RPORT);
                CommandProcessor.dOut = new DataOutputStream(socket.getOutputStream());
                CommandProcessor.dOut.writeByte(7);
                CommandProcessor.dOut.writeInt(MainConsole.RemoteID);
                CommandProcessor.dOut.flush();
            } catch (IOException ex) {}
        }
       else if(e.getSource() == MainConsole.chat) {
           MainConsole.panels.show(MainConsole.mainPanel,"chat");
           MainConsole.home.setBackground(new Color(230,230,230));
           MainConsole.mydrive.setBackground(new Color(230,230,230));
           MainConsole.currentsession.setBackground(new Color(230,230,230));
           MainConsole.chat.setBackground(new Color(200,200,200));
           MainConsole.notes.setBackground(new Color(230,230,230));
        }
       else if(e.getSource() == MainConsole.notes) {
           MainConsole.panels.show(MainConsole.mainPanel,"notes");
           MainConsole.home.setBackground(new Color(230,230,230));
           MainConsole.mydrive.setBackground(new Color(230,230,230));
           MainConsole.currentsession.setBackground(new Color(230,230,230));
           MainConsole.chat.setBackground(new Color(230,230,230));
           MainConsole.notes.setBackground(new Color(200,200,200));
        }
       else if(e.getSource() == MainConsole.reboot) {
            
           MainConsole.mainPanel.revalidate();
           MainConsole.mainPanel.repaint();
        }
    }
}
