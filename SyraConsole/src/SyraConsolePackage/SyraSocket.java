package SyraConsolePackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SyraSocket {
    static String SYRA_IP;
    static int SYRA_PORT;
    static int SYRA_RPORT;
    Socket socket;
    DataInputStream dIn;
    DataOutputStream dOut;
    boolean error;

    public SyraSocket() {
        System.out.println("connecting...");
        try {
            socket = new Socket(SYRA_IP,SYRA_PORT);
            socket.setSoTimeout(4000);
            dIn = new DataInputStream(socket.getInputStream());
            dOut = new DataOutputStream(socket.getOutputStream());
            error = false;
            System.out.println("connected.");
        } catch (IOException ex) {
            error = true;
            System.out.println(ex.toString());
        }
    }
    void closethis() {
        try {
            dIn.close();
            dOut.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        System.out.println("Closed.");
    }
}
