package SyraClientPackage;

import java.awt.AWTException;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Robot;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class CommandReceiver extends Thread{
    private Socket socket;
    private DataInputStream dIn;
    private DataOutputStream dOut;
    private Robot robot;
    private int cType;
    private String cmd;
    @Override
    public void run() {
    System.out.println("CommandReceiver started..");
    try {
        GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gDev = gEnv.getDefaultScreenDevice();
        robot = new Robot(gDev);
        socket = new Socket(MainClient.SYRA_IP,MainClient.SYRA_PORT);
        socket.setSoTimeout(4000);
        dIn = new DataInputStream(socket.getInputStream());
        dOut = new DataOutputStream(socket.getOutputStream());
        dOut.writeByte(6);
        dOut.writeInt(MainClient.SYRA_REMOTE);
        dOut.flush();
        while(true) {
        if(dIn.readBoolean()) {
        cmd = dIn.readUTF();
        cType = Integer.parseInt(cmd.split("|", 2)[0]);
        cmd = cmd.split("|", 2)[1];
        switch(cType) {
            case 1:
                robot.mousePress(Integer.parseInt(cmd));
                break;
            case 2:
                robot.mouseRelease(Integer.parseInt(cmd));
                break;
            case 3:
                robot.keyPress(Integer.parseInt(cmd));
                break;
            case 4:
                robot.keyRelease(Integer.parseInt(cmd));
                break;
            case 5:
                robot.mouseMove(Integer.parseInt(cmd.split(",", 2)[0]),Integer.parseInt(cmd.split(",", 2)[1]));
                break;
        }
        }
        }    
    } catch(HeadlessException | AWTException | IOException | NumberFormatException ex) {
    System.out.println("Command Receiver : " + ex.toString());
    } finally {
    try {
    dIn.close();
    dOut.close();
    socket.close();
    } catch(Exception ex1) {}
    }
    System.out.println("CommandReceiver Exited.");
    }
}
