package SyraConsolePackage;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.DataOutputStream;
import java.io.IOException;

public class CommandProcessor implements KeyListener,MouseMotionListener,MouseListener{
static DataOutputStream dOut;
private int x,y,btnP,btnR;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("kp");
    try {
        System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
        e.consume();
        dOut.writeUTF("3," + e.getKeyCode());
        dOut.flush();
    } catch (Exception ex) {}
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("kr");
    try {
        e.consume();
        dOut.writeUTF("4," + e.getKeyCode());
        dOut.flush();
    } catch (Exception ex) {}    
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    Point p = e.getPoint();
    double xScale = RemoteViewer.rectangle.getWidth()/MainConsole.mainScreen.getSize().width;
    double yScale = RemoteViewer.rectangle.getHeight()/MainConsole.mainScreen.getSize().height;
    x = (int)(p.x*xScale);
    y = (int)(p.y*yScale);
    try {
        dOut.writeUTF("5," + x + "," + y);
        dOut.flush();
        } catch (IOException ex) {}
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    double xScale = RemoteViewer.rectangle.getWidth()/MainConsole.mainScreen.getSize().width;
    double yScale = RemoteViewer.rectangle.getHeight()/MainConsole.mainScreen.getSize().height;
    x = (int)(e.getX()*xScale);
    y = (int)(e.getY()*yScale);
    try {
        dOut.writeUTF("5," + x + "," + y);
        dOut.flush();
        } catch (IOException ex) {}
    }


    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("mp");
        btnP = e.getButton();
    try {
        dOut.writeUTF("1," + x + "," + y + "," + btnP);
        dOut.flush();
        } catch (IOException ex) {}    
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("mr");
        btnR = e.getButton();
    try {
        dOut.writeUTF("2," + x + "," + y + "," + btnR);
        dOut.flush();
        } catch (IOException ex) {}
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    //System.out.println("me");
        MainConsole.mainScreen.requestFocusInWindow();
    }

    @Override
    public void mouseExited(MouseEvent e) {
    //System.out.println("mex");
    }
    
}
