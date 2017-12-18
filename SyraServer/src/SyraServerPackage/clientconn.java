package SyraServerPackage;

import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

public class clientconn extends Thread {
    static int port;
    ServerSocket sc;
    static boolean running;
    @Override
    public void run() {
    try {
        running = true;
        sc = new ServerSocket(port);
        System.out.println("client listner started");
        while(running) {
            Socket conn = sc.accept();
            System.out.println("new connection");
            clientprocessor clientprocessor = new clientprocessor();
            clientprocessor.socket = conn;
            clientprocessor.start();
        }
        System.out.println("client listner stopped");
    } catch(java.lang.Exception ex) {
    System.out.println(ex.toString());
    JOptionPane.showMessageDialog(null, "Client Server Not Started!", "Syra Server", JOptionPane.ERROR_MESSAGE);
    }
    }
}
