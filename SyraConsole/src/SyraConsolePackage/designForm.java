package SyraConsolePackage;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

public class designForm extends MainConsole {
    syraAction syraEvent = new syraAction();
    protected void drawGUI() {
        drawTopPanel();
        drawmainPanel();
        mainframe.setIconImage(new ImageIcon("resources/logo.png").getImage());
        mainframe.setLayout(new BorderLayout());
        mainframe.add(topPanel,BorderLayout.NORTH);
        mainframe.add(mainPanel,BorderLayout.CENTER);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.pack();
        mainframe.setExtendedState(mainframe.getExtendedState()|JFrame.MAXIMIZED_BOTH);
        mainframe.setVisible(true);
    }
    
    private void drawTopPanel() {
        home = new JButton("Home", new ImageIcon("resources/home.png"));
        home.setMargin(new Insets(0, 0, 0, 5));
        home.setBackground(new Color(200,200,200));
        home.setFocusPainted(false);
        home.setBorderPainted(false);
        home.addActionListener(syraEvent);  
        
        currentsession = new JButton("Session", new ImageIcon("resources/currentsession.png"));
        currentsession.setMargin(new Insets(0, 0, 0, 5));
        currentsession.setBackground(new Color(230,230,230));
        currentsession.setFocusPainted(false);
        currentsession.setBorderPainted(false);
        currentsession.addActionListener(syraEvent);
        
        mydrive = new JButton("MyDrive", new ImageIcon("resources/mydrive.png"));
        mydrive.setMargin(new Insets(0, 0, 0, 5));
        mydrive.setBackground(new Color(230,230,230));
        mydrive.setFocusPainted(false);
        mydrive.setBorderPainted(false);
        mydrive.addActionListener(syraEvent);
        
        screenshot = new JButton("ScreenShot", new ImageIcon("resources/screenshot.png"));
        screenshot.setMargin(new Insets(0, 0, 0, 5));
        screenshot.setBackground(new Color(230,230,230));
        screenshot.setFocusPainted(false);
        screenshot.setBorderPainted(false);
        screenshot.addActionListener(syraEvent);
        
        whiteboard = new JButton("MyBoard", new ImageIcon("resources/whiteboard.png"));
        whiteboard.setMargin(new Insets(0, 0, 0, 5));
        whiteboard.setBackground(new Color(230,230,230));
        whiteboard.setFocusPainted(false);
        whiteboard.setBorderPainted(false);
        whiteboard.addActionListener(syraEvent);
        
        reboot = new JButton("Reboot", new ImageIcon("resources/reboot.png"));
        reboot.setMargin(new Insets(0, 0, 0, 5));
        reboot.setBackground(new Color(230,230,230));
        reboot.setFocusPainted(false);
        reboot.setBorderPainted(false);
        reboot.addActionListener(syraEvent);
        
        chat = new JButton("Chat", new ImageIcon("resources/chat.png"));
        chat.setMargin(new Insets(0, 0, 0, 5));
        chat.setBackground(new Color(230,230,230));
        chat.setFocusPainted(false);
        chat.setBorderPainted(false);
        chat.addActionListener(syraEvent);
        
        notes = new JButton("Notes", new ImageIcon("resources/notes.png"));
        notes.setMargin(new Insets(0, 0, 0, 5));
        notes.setBackground(new Color(230,230,230));
        notes.setFocusPainted(false);
        notes.setBorderPainted(false);
        notes.addActionListener(syraEvent);
        
        topPanel=new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
        topPanel.setBackground(new Color(250,250,250));
        topPanel.add(home);
        topPanel.add(currentsession);
        topPanel.add(mydrive);
        topPanel.add(screenshot);
        topPanel.add(whiteboard);
        topPanel.add(reboot);
        topPanel.add(chat);
        topPanel.add(notes);
    }
    private void drawmainPanel() {
        drawHomePanel();
        drawCurrentSessionPanel();
        drawMyDrivePanel();
        drawChatPanel();
        drawNotesPanel();
        drawStatus();
        mainPanel=new JPanel();
        panels = new CardLayout();
        mainPanel.setLayout(panels);
        mainPanel.add(homePanel,"home");
        mainPanel.add(currentsessionPanel,"currentsession");
        mainPanel.add(mydrivePanel,"mydrive");
        mainPanel.add(chatPanel,"chat");
        mainPanel.add(notesPanel,"notes");
        mainPanel.add(status,"status");
    }
    private void drawHomePanel() {
        homePanel = new JPanel();
        homePanel.setLayout(new BorderLayout());
        JPanel home_top,home_top1,home_top2;
        JScrollPane scroll2,scroll3;
        
        newsession = new JButton("New Session",new ImageIcon("resources/newsession.png"));
        newsession.setMargin(new Insets(0, 0, 0, 5));
        newsession.setBackground(new Color(250,250,250));
        newsession.setFocusPainted(false);
        newsession.setBorderPainted(false);
        newsession.addActionListener(syraEvent);
        
        logout = new JButton("LogOut",new ImageIcon("resources/logout.png"));
        logout.setMargin(new Insets(0, 0, 0, 5));
        logout.setBackground(new Color(250,250,250));
        logout.setFocusPainted(false);
        logout.setBorderPainted(false);
        logout.addActionListener(syraEvent);
        
        loggedinName = new JLabel("N/A");
        loggedinName.setBorder(new EmptyBorder(0, 8, 0, 5));
        home_top2 = new JPanel();
        home_top2.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        home_top2.add(loggedinName);
        home_top2.add(logout);
        home_top1 = new JPanel();
        home_top1.setLayout(new BorderLayout());
        home_top1.setBackground(new Color(210,210,210));
        home_top1.setBorder(new EmptyBorder(0,5,5,5));
        home_top1.add(newsession,BorderLayout.WEST);
        home_top1.add(home_top2,BorderLayout.EAST);
        notification = new JPanel();
        notification.setLayout(new GridLayout(0,1,5,5));
        notificationscroll = new JScrollPane(notification,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        notificationscroll.setBorder(new EmptyBorder(5, 5, 5, 5));
        notificationscroll.setPreferredSize(new Dimension(10,100));
        home_top = new JPanel();
        home_top.setLayout(new BorderLayout());
        home_top.setBorder(new EmptyBorder(5, 5, 5, 5));
        home_top.setBackground(new Color(210,210,210));
        home_top.add(home_top1,BorderLayout.NORTH);
        home_top.add(notificationscroll,BorderLayout.CENTER);
        notificationscroll.setVisible(false);
        sessionholder = new JPanel();
        technicians = new JPanel();
        
        scroll2 = new JScrollPane(sessionholder,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        
        scroll3 = new JScrollPane(technicians,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll3.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        
        homepanels = new CardLayout();
        home_bottom = new JPanel();
        home_bottom.setLayout(homepanels);
        home_bottom.setBackground(new Color(250,250,250));
        home_bottom.setBorder(new EmptyBorder(12, 30, 12, 30));
        home_bottom.add(scroll2,"sessionholder");
        home_bottom.add(scroll3,"technicians");
        
        homePanel.add(home_top,BorderLayout.NORTH);
        homePanel.add(home_bottom,BorderLayout.CENTER);
    }
    private void drawCurrentSessionPanel() {
        mouseScreen=new rScreen();
        mouseScreen.setPreferredSize(new Dimension(100,100));
        mouseScreen.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        mouseScreen.setVisible(true);
        mainScreen=new rScreen();
        mainScreen.setFocusable(true);
        mainScreen.setVisible(true);
        currentsessionPanel = new JPanel();
        currentsessionPanel.setLayout(new BorderLayout());
        currentsessionPanel.setBackground(new Color(0,0,0));
        currentsessionPanel.setBorder(new EmptyBorder(1, 0, 0, 0));
        currentsessionPanel.add(mouseScreen,BorderLayout.WEST);
        currentsessionPanel.add(mainScreen,BorderLayout.CENTER);
    }
    private void drawMyDrivePanel() {
        makenewfolder = new JButton(new ImageIcon("resources/makenewfolder.png"));
        makenewfolder.setBackground(new Color(230,230,230));
        makenewfolder.setFocusPainted(false);
        makenewfolder.setBorderPainted(false);
        makenewfolder.setToolTipText("Make New Folder");
        makenewfolder.addActionListener(syraEvent);
                
        movefile = new JButton(new ImageIcon("resources/movefile.png"));
        movefile.setBackground(new Color(230,230,230));
        movefile.setFocusPainted(false);
        movefile.setBorderPainted(false);
        movefile.setToolTipText("Move File");
        movefile.addActionListener(syraEvent);
        
        copyfile = new JButton(new ImageIcon("resources/copyfile.png"));
        copyfile.setBackground(new Color(230,230,230));
        copyfile.setFocusPainted(false);
        copyfile.setBorderPainted(false);
        copyfile.setToolTipText("Copy File");
        copyfile.addActionListener(syraEvent);
        
        sendfiletoclient = new JButton(new ImageIcon("resources/sendfiletoclient.png"));
        sendfiletoclient.setBackground(new Color(230,230,230));
        sendfiletoclient.setFocusPainted(false);
        sendfiletoclient.setBorderPainted(false);
        sendfiletoclient.setToolTipText("Send File To Client");
        sendfiletoclient.addActionListener(syraEvent);
        
        sendfiletotechnician = new JButton(new ImageIcon("resources/sendfiletotechnician.png"));
        sendfiletotechnician.setBackground(new Color(230,230,230));
        sendfiletotechnician.setFocusPainted(false);
        sendfiletotechnician.setBorderPainted(false);
        sendfiletotechnician.setToolTipText("Send File To Technician");
        sendfiletotechnician.addActionListener(syraEvent);
        
        downloadfile = new JButton(new ImageIcon("resources/downloadfile.png"));
        downloadfile.setBackground(new Color(230,230,230));
        downloadfile.setFocusPainted(false);
        downloadfile.setBorderPainted(false);
        downloadfile.setToolTipText("Download File");
        downloadfile.addActionListener(syraEvent);
        
        uploadfile = new JButton(new ImageIcon("resources/uploadfile.png"));
        uploadfile.setBackground(new Color(230,230,230));
        uploadfile.setFocusPainted(false);
        uploadfile.setBorderPainted(false);
        uploadfile.setToolTipText("Upload New File");
        uploadfile.addActionListener(syraEvent);
        
        sendfilefromlocal = new JButton(new ImageIcon("resources/sendfilefromlocal.png"));
        sendfilefromlocal.setBackground(new Color(230,230,230));
        sendfilefromlocal.setFocusPainted(false);
        sendfilefromlocal.setBorderPainted(false);
        sendfilefromlocal.setToolTipText("Send File From Local Drive");
        sendfilefromlocal.addActionListener(syraEvent);
        
        mydrive_bottom=new JPanel();
        mydrive_bottom.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
        mydrive_bottom.add(makenewfolder);
        mydrive_bottom.add(movefile);
        mydrive_bottom.add(copyfile);
        mydrive_bottom.add(sendfiletotechnician);
        mydrive_bottom.add(downloadfile);
        mydrive_bottom.add(uploadfile);
        mydrive_bottom.add(sendfilefromlocal);
        mydrive_bottom.add(sendfiletoclient);
        
        JScrollPane scroll4 = new JScrollPane(mydrive_top,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll4.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                
        mydrive_top=new JPanel();
        mydrivePanel = new JPanel();
        mydrivePanel.setLayout(new BorderLayout());
        mydrivePanel.setBackground(new Color(225,225,225));
        mydrivePanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        mydrivePanel.add(mydrive_bottom,BorderLayout.SOUTH);
        mydrivePanel.add(scroll4,BorderLayout.CENTER);
    }
    private void drawChatPanel() {
        chatPanel = new JPanel();
        chatPanel.add(new JLabel("chat"));
    
    }
    private void drawNotesPanel() {
        notesPanel = new JPanel();
        notesPanel.add(new JLabel("notes"));
    
    }
    private void drawStatus() {
        statusLabel = new JLabel();
        status = new JPanel();
        status.setLayout(new FlowLayout(FlowLayout.CENTER,50,50));
        status.add(statusLabel);
    }
}
