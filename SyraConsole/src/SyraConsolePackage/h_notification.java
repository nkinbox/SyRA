package SyraConsolePackage;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class h_notification extends JPanel{
    private final int remoteid;
    JButton accept;
    public h_notification(String rid,String name,String username) {
        remoteid = Integer.parseInt(rid);
        accept = new JButton("Accept");
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        this.setBorder(new EmptyBorder(3, 12, 3, 5));
        this.setBackground(new Color(255,255,255));
        this.add(new JLabel(rid));
        this.add(new JLabel(name));
        this.add(new JLabel(username));
        this.add(accept);
        this.setVisible(true);
    }
}
