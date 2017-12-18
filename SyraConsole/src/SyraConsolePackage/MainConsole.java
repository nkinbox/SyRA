package SyraConsolePackage;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MainConsole {
    static String SESSION_ID = "222";
    static String IP_ADDRESS = "222";
    static JFrame mainframe = new JFrame("Syra Console");
    static JPanel mainPanel,topPanel,
            homePanel,currentsessionPanel,mydrivePanel,chatPanel,notesPanel,status,
            notification,home_bottom,sessionholder,technicians,
            mydrive_top,mydrive_bottom;
    static rScreen mouseScreen,mainScreen;
    static JButton home,currentsession,mydrive,screenshot,whiteboard,reboot,chat,notes,
            newsession,logout,
            makenewfolder,movefile,copyfile,sendfiletoclient,sendfiletotechnician,downloadfile,
            uploadfile,sendfilefromlocal;
    static JLabel loggedinName, statusLabel;
    static JScrollPane notificationscroll;
    static CardLayout panels,homepanels;
    static int RemoteID=1111;
       
    public static void main(String[] args) {
        SyraSocket.SYRA_IP = "192.168.1.2";
        SyraSocket.SYRA_PORT = 5659;
        SyraSocket.SYRA_RPORT = 5956;
      new designForm().drawGUI();
    }
}
