package SyraServerPackage;

import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

public class dataconn extends Thread {
    static int port;
    ServerSocket sc;
    static boolean running;
    @Override
    public void run() {
    try {
        running = true;
        sc = new ServerSocket(port);
        System.out.println("listner started");
        while(running) {
            Socket conn = sc.accept();
            System.out.println("new connection");
            dataprocessor dataprocessor = new dataprocessor();
            dataprocessor.socket = conn;
            dataprocessor.start();
        }
        System.out.println("listner stopped");
    } catch(java.lang.Exception ex) {
    System.out.println(ex.toString());
    JOptionPane.showMessageDialog(null, "Server Not Started!", "Syra Server", JOptionPane.ERROR_MESSAGE);
    }
    }
}
