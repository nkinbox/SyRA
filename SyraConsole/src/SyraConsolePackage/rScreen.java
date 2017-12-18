package SyraConsolePackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

class rScreen extends JPanel{
    Image temp;
    Dimension dim;
    boolean zoom,f=false;
    public rScreen() {
    setBackground(Color.WHITE);
    CommandProcessor i = new CommandProcessor();
    addKeyListener(i);
    addMouseListener(i);
    addMouseMotionListener(i);
    }
    void startrScreen()  
     { 
        dim = getSize(); 
     }
    void setScreenImage(Image i,boolean j,boolean k) {
    temp = i;
    zoom = j;
    f = k;
    }
    @Override
    public void paintComponent(Graphics g) {
    if(f) {
    super.paintComponent(g);
    if(zoom) {
    temp = temp.getScaledInstance(dim.width, -1, Image.SCALE_SMOOTH);
    } else {
    temp = temp.getScaledInstance(dim.width, dim.height, Image.SCALE_SMOOTH);
    }
    //g.clearRect(0,0,dim.width,dim.width);
    g.drawImage(temp, 0, 0, this);//drawImage(temp,0,0,dim.width,dim.height, this);
    } else {
    if (ui != null) {
            Graphics scratchGraphics = (g == null) ? null : g.create();
            try {
                ui.update(scratchGraphics, this);
            }
            finally {
                scratchGraphics.dispose();
            }
        }
    }}
    @Override
    public void update(Graphics g) {
    paintComponent(g);
    }
}
