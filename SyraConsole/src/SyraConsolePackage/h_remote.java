package SyraConsolePackage;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class h_remote extends JPanel{
    private final int remoteid;
    JButton transfer,view,close;
    public h_remote(String rid,String name,String startat,String control) {
        remoteid = Integer.parseInt(rid);
        transfer = new JButton("Transfer");
        view = new JButton("View");
        close = new JButton("Close");
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        this.setBorder(new EmptyBorder(3, 12, 3, 5));
        this.setBackground(new Color(255,255,255));
        this.add(new JLabel(rid));
        this.add(new JLabel(name));
        this.add(new JLabel(startat));
        this.add(new JLabel(control));
        this.add(transfer);
        this.add(view);
        this.add(close);
        this.setVisible(true);
    }
}
