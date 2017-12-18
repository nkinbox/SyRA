package SyraServerPackage;

import java.util.concurrent.ConcurrentHashMap;

public class MainServer {
    static ConcurrentHashMap rectangleContainer,notesContainer,mouseContainer,screenContainer,commandContainer;
    private dataconn con;
    private clientconn ccon;
    public static void main(String[] args) {
      new MainServer().startServer();      
    }
    private void startServer() {
    dataconn.port = 5659;//Integer.parseInt(JOptionPane.showInputDialog("Server Port"));
    clientconn.port = 5956;//Integer.parseInt(JOptionPane.showInputDialog("Client Server Port"));
    database.MysqlUser = "root";//JOptionPane.showInputDialog("MySql User");
    database.MysqlPass = "jJtEVgxt4qrV7,y";//JOptionPane.showInputDialog("MySql Pass");
    rectangleContainer = new ConcurrentHashMap();
    mouseContainer = new ConcurrentHashMap();
    screenContainer = new ConcurrentHashMap();
    commandContainer = new ConcurrentHashMap();
    notesContainer = new ConcurrentHashMap();
    con = new dataconn();
    con.start();
    ccon = new clientconn();
    ccon.start();
    }
}
