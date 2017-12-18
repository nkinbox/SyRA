package SyraConsolePackage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

public class h_technicians{
    TreeMap group;
    static TreeMap technicians;
    JTree tree;
    
    public h_technicians() {
    group = new TreeMap();
    technicians = new TreeMap();
    }
    void newdata(String id,String username,String groupname) {
        technicians.put(Integer.parseInt(id), username);
        if(group.containsKey(groupname)) {
        ArrayList<String> al = (ArrayList<String>) group.get(groupname);
        al.add(username);
        } else {
            ArrayList<String> al = new ArrayList<>();
            al.add(username);
            group.put(groupname,al);
        } 
    }
    void view() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Technicians");
        Set set = group.entrySet();
        Iterator i = set.iterator();
        while(i.hasNext()) {
            Map.Entry me = (Map.Entry)i.next();
            DefaultMutableTreeNode gName = new DefaultMutableTreeNode(me.getKey());
            for(String tech : (ArrayList<String>) me.getValue()) {
                gName.add(new DefaultMutableTreeNode(tech));
            }
            root.add(gName);
        }
        tree = new JTree(root);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    }
}
