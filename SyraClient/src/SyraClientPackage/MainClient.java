package SyraClientPackage;

import java.awt.HeadlessException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;

public class MainClient {
    static int SYRA_REMOTE;
    static String SYRA_PASS;
    static String SYRA_IP;
    static int SYRA_PORT;

    public static void main(String[] args) {
        new MainClient().MainClientBegin();
    }
    
    private void MainClientBegin() {
        MainClient.SYRA_IP = "192.168.1.11";
        MainClient.SYRA_PORT = 5956;
        InetAddress ipAddr;
        MessageDigest cript;
        Socket c;
        DataInputStream dIn;
        DataOutputStream dOut;
        try {
            ipAddr = InetAddress.getLocalHost();
            cript = MessageDigest.getInstance("SHA-1");
            cript.reset();
            cript.update(ipAddr.getHostAddress().getBytes("utf8"));    
            SYRA_PASS = HexString(cript.digest());
            SYRA_REMOTE = Integer.parseInt(JOptionPane.showInputDialog("What is your RemoteID?"));
            c = new Socket(MainClient.SYRA_IP,MainClient.SYRA_PORT);
            c.setSoTimeout(4000);
            dIn = new DataInputStream(c.getInputStream());
            dOut = new DataOutputStream(c.getOutputStream());
            dOut.writeByte(1);
            dOut.writeInt(MainClient.SYRA_REMOTE);
            dOut.writeUTF(MainClient.SYRA_PASS);
            dOut.flush();
            String temp = dIn.readUTF();
            dOut.close();
            dIn.close();
            c.close();
            if("allow".equals(temp)) {
                drawGUI();
                ScreenSpyer screen = new ScreenSpyer(2);
                screen.start();
                ScreenSpyer mouse = new ScreenSpyer(3);
                mouse.start();
                CommandReceiver r = new CommandReceiver();
                r.start();
            } else {
            JOptionPane.showMessageDialog(null, "RemoteId is Invalid or Expired.", "Syra Client", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NoSuchAlgorithmException | HeadlessException | NumberFormatException | IOException ex) {
            System.out.println(ex.toString());
            JOptionPane.showMessageDialog(null, "An Error Occured! Try Again.", "Syra Client", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private String HexString(byte[] b) {
        String result = "";
        for (int i=0; i < b.length; i++) {
            result += Integer.toString( ( b[i] & 0xff ) + 0x100, 16).substring( 1 );
        }
        return result;
    }
    private void drawGUI() {
    
    }
}
