package SyraConsolePackage;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import javax.swing.ImageIcon;

public class RemoteViewer extends Thread {
    static Rectangle rectangle = new Rectangle(100,100);
    static boolean loop = true;
    private Socket socket;
    private ObjectInputStream ois;
    private DataOutputStream dOut;
    private int type;
    private ImageIcon imageicon;
    private Image image;
    public RemoteViewer(int i) {
    type = i;
    }
    @Override
    public void run() {
        if(type == 1)
            ScreenView();
        else if(type == 2)
            MouseView();
    }

    private void ScreenView() {
        System.out.println("ScreenView started.");
        try {
            socket = new Socket(SyraSocket.SYRA_IP,SyraSocket.SYRA_RPORT);
            socket.setSoTimeout(4000);
            dOut = new DataOutputStream(socket.getOutputStream());
            dOut.writeByte(4);
            dOut.writeInt(MainConsole.RemoteID);
            dOut.flush();
            ois = new ObjectInputStream(socket.getInputStream());
            MainConsole.mainScreen.startrScreen();
            boolean t=true;
            while(loop) {
            if(ois.readBoolean()) {
            if(t) {
            rectangle = (Rectangle) ois.readObject();
            } else {t = false;}
            imageicon = (ImageIcon) ois.readObject();
            image = imageicon.getImage();
            MainConsole.mainScreen.setScreenImage(image,false,true);
            MainConsole.mainScreen.repaint();
            Thread.sleep(100);
            }
            }
        } catch(IOException | ClassNotFoundException | InterruptedException ex) {
            System.out.println("RemortViewer : " + ex.toString());
        } finally {
            try {
                ois.close();
                dOut.close();
                socket.close();
            } catch (IOException ex) {}
        }
        System.out.println("ScreenView ended.");
    }
    
    private void MouseView() {
        System.out.println("MouseView started.");
        try {
            socket = new Socket(SyraSocket.SYRA_IP,SyraSocket.SYRA_RPORT);
            socket.setSoTimeout(4000);
            dOut = new DataOutputStream(socket.getOutputStream());
            dOut.writeByte(5);
            dOut.writeInt(MainConsole.RemoteID);
            dOut.flush();
            ois = new ObjectInputStream(socket.getInputStream());
            MainConsole.mouseScreen.startrScreen();
            while(loop) {
            if(ois.readBoolean()) {
            imageicon = (ImageIcon) ois.readObject();
            image = imageicon.getImage();
            MainConsole.mouseScreen.setScreenImage(image,true,true);
            MainConsole.mouseScreen.repaint();
            Thread.sleep(100);
            }
            }
        } catch(IOException | ClassNotFoundException | InterruptedException ex) {
            System.out.println("RemortViewer : " + ex.toString());
        } finally {
            try {
                ois.close();
                dOut.close();
                socket.close();
            } catch (IOException ex) {}
        }
        System.out.println("MouseView ended.");
    }
}
