
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Simulation extends javax.swing.JFrame
{
    private final JFrame parent;
    private final int gd;
    private Vector<String> buckets;
    private final int bfr;
    private final GlassPane gp;
    private final Vector<Vector<Vector<JPanel>>> map;
    private int key;
    private String keyStr;
    private boolean flag;
    private String overflow;
    private final Vector<Integer> keys;
    private final Timer addTimer1;
    private final Timer addTimer2;
    private final Timer addTimer3;
    private final Timer addTimer4;
    private final Timer searchTimer1;
      private final Timer searchTimer2;
      private final Timer searchTimer3;
    private Vector<Vector<JPanel>> link;
    private final int DELAY = 1;
        
    public Simulation(int gd, Vector<String> buckets, int bfr, Vector<Integer> keys, JFrame parent)
    {
        this.setVisible(true);
        this.parent = parent;
        this.parent.dispose();
        this.gd = gd;
        this.buckets = buckets;
        this.bfr = bfr;
        this.map = new Vector<>();
        this.gp = new GlassPane();
        this.keys = keys;
        this.flag = false;
        this.overflow = null;
        this.addTimer4 = new Timer(DELAY, (ActionEvent e) -> {simulation4();});
        this.addTimer4.setRepeats(false);
        this.addTimer3 = new Timer(DELAY, (ActionEvent e) -> {
            if(simulation3(false))
            {
                addTimer4.start();
                if(flag)
                {
                    simulateNew();
                }
            }
        });
        this.addTimer3.setRepeats(false);
        this.addTimer2 = new Timer(DELAY, (ActionEvent e) -> {
            if(simulation2())
            {
                addTimer3.start();
            }
        });
        this.addTimer2.setRepeats(false);
        this.addTimer1 = new Timer(DELAY, (ActionEvent e) -> {
            if(simulation1(Color.BLUE))
            {
                addTimer2.start();
            }
        });
        this.addTimer1.setRepeats(false);
        this.searchTimer3 = new Timer(DELAY, (ActionEvent e) -> {
            if(searchKey())
            {
                addTimer4.start();
            }
        });
        this.searchTimer3.setRepeats(false);
        this.searchTimer2 = new Timer(DELAY, (ActionEvent e) -> {
            if(simulation2())
            {
                searchTimer3.start();
            }
        });
        this.searchTimer2.setRepeats(false);
        this.searchTimer1 = new Timer(DELAY, (ActionEvent e) -> {
            float[] clr = Color.RGBtoHSB(37, 138, 64, null);
            if(simulation1(Color.getHSBColor(clr[0], clr[1], clr[2])))
            {
                searchTimer2.start();
            }
        });
        this.searchTimer1.setRepeats(false);
        initComponents();
        this.setGlassPane(gp);
        this.gp.setVisible(true);
        this.constructDirectories();
        this.constructBlocks();
        this.createMap();
        this.addKeys();
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Extendible Hashing");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setBackground(new java.awt.Color(0, 255, 204));
        jButton1.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Enter Key value");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(0, 255, 204));
        jButton2.setFont(new java.awt.Font("Berlin Sans FB", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Add ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 255, 204));
        jButton3.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Search");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(51, 255, 204));
        jPanel3.setPreferredSize(new java.awt.Dimension(412, 427));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        jLabel3.setText("Directory and Pointer");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new java.awt.GridLayout(0, 2));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap(20, Short.MAX_VALUE))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(0, 255, 204));
        jPanel4.setForeground(new java.awt.Color(0, 255, 204));
        jPanel4.setPreferredSize(new java.awt.Dimension(412, 427));

        jLabel4.setBackground(new java.awt.Color(51, 51, 51));
        jLabel4.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        jLabel4.setText("Data File Buckets ( Bfr = 3 )");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new java.awt.GridLayout(0, bfr + 1));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(241, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap(241, Short.MAX_VALUE))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 531, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel1.setBackground(new java.awt.Color(0, 255, 204));
        jLabel1.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setText("Please select desired function :");

        jLabel2.setBackground(new java.awt.Color(0, 255, 204));
        jLabel2.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel2.setText("Currently using Mod function : K Mod 10");

        jLabel5.setBackground(new java.awt.Color(0, 255, 204));
        jLabel5.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Please enter a key value to get started.");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 757, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(90, 90, 90)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(252, 252, 252))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(478, 478, 478))))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(jLabel2)
                    .addContainerGap(952, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(649, Short.MAX_VALUE)))
        );

        jButton2.setVisible(false);
        jButton3.setVisible(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1032, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    
    private void constructDirectories()
    {
        int maxgd = 1 << gd;
        Dimension size = jPanel5.getSize();
        size = new Dimension(size.width / 2, size.height / maxgd);
        for(int i = 0; i < maxgd; i++)
        {
            JPanel panel1 = new JPanel(new GridLayout(1, 1));
            RectangleText rect = new RectangleText(Utility.toBinary(i, gd), Color.BLACK, size);
            panel1.add(rect);
            JPanel panel2 = new JPanel(new GridLayout(1, 1));
            RectangleText rect2 = new RectangleText("Pointer",Color.BLACK, size);
            panel2.add(rect2);
            jPanel5.add(panel1);
            jPanel5.add(panel2);
        }
    }
    
    private void constructBlocks()
    {
        int maxld = buckets.size();
        Dimension size = jPanel6.getSize();
        size = new Dimension(size.width / (bfr + 1), size.height / maxld);
        for(String bucketText : buckets)
        {
            JPanel panel1 = new JPanel(new GridLayout(1, 1));
            RectangleText rect = new RectangleText(bucketText, Color.BLACK, size);
            panel1.add(rect);
            jPanel6.add(panel1);
            for(int j = 0; j < bfr; j++)
            {
                JPanel panel2 = new JPanel(new GridLayout(1, 1));
                panel2.add(new RectangleText(size));
                jPanel6.add(panel2);
            }
        }
    }
    
    private void createMap()
    {
        int maxgd = 1 << gd;
        int maxld = buckets.size();
        for(int i = 0; i < maxgd; i++)
        {
            JPanel dir = (JPanel) jPanel5.getComponent(2 * i);
            RectangleText rect = (RectangleText) dir.getComponent(0);
            JPanel dir2 = (JPanel) jPanel5.getComponent(2 * i + 1);
            Vector<Vector<JPanel>> lnk = new Vector<>();
            Vector<JPanel> vd = new Vector<>();
            vd.add(dir);
            vd.add(dir2);
            lnk.add(vd);
            for(int j = 0; j < maxld; j++)
            {
                JPanel block = (JPanel) jPanel6.getComponent((bfr + 1) * j);
                RectangleText rt = (RectangleText) block.getComponent(0);
                if(rect.getString().endsWith(rt.getString()))
                {
                    gp.link(dir2, block);
                    Vector<JPanel> vb = new Vector<>();
                    vb.add(block);
                    for(int k = 1; k <= bfr; k++)
                    {
                        JPanel p = (JPanel) jPanel6.getComponent((bfr + 1) * j + k);
                        vb.add(p);
                    }
                    lnk.add(vb);
                }
            }
            map.add(lnk);
        }
    }
    
    private void addKeys()
    {
        for(int i = 0; i < keys.size() - 1; i++)
        {
            int x = keys.get(i);
            key = x;
            keyStr = Utility.toBinary(key, 4);
            simulation1(Color.BLUE);
            simulation2();
            simulation3(true);
            simulation4();
        }
        if(!keys.isEmpty())
        {
            key = keys.get(keys.size() - 1);
            keyStr = Utility.toBinary(key, 4);
            addTimer1.start();
        }
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        while(true)
        {
            try
            {
                String str = JOptionPane.showInputDialog(null, "Enter key value...", null, JOptionPane.QUESTION_MESSAGE);
                if(str == null)
                {
                    break;
                }
         
                jButton3.setVisible(true);
                key = Integer.parseInt(str);
                int out = Utility.hash(key);
                keyStr = Utility.toBinary(out, 4);
                //jLabel2.setText("Key entered: " + str + " (" + keyStr + ")");
                //jLabel2.setVisible(true);
                jButton1.setText("Change Key");
                jButton2.setVisible(true);
                
                break;
            }
            catch(NumberFormatException e)
            {
                JOptionPane.showMessageDialog(null, "Please enter valid numeric key", "Key error", JOptionPane.ERROR_MESSAGE);
            }
        }  
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        searchTimer1.start();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        keys.add(key);
        addTimer1.start();
    }//GEN-LAST:event_jButton2ActionPerformed
       
    private boolean simulation1(Color clr)
    {
        jButton1.setVisible(false);
        //jButton2.setVisible(false);
        jButton3.setVisible(false);
        for(Vector<Vector<JPanel>> lnk : map)
        {
            Vector<JPanel> dir = lnk.get(0);
            JPanel textPanel = dir.get(0);
            RectangleText rect = (RectangleText) textPanel.getComponent(0);
            if(keyStr.endsWith(rect.getString()))
            {
                link = lnk;
                break;
            }
        }
        Vector<JPanel> dir = link.get(0);
        JPanel linkPanel = dir.get(1);
        RectangleText rect = (RectangleText) linkPanel.getComponent(0);
        linkPanel.add(new RectangleText(String.valueOf(key), clr, rect.getDimension()), 0);
        linkPanel.remove(1);
        return true;
    }
    
    public boolean simulation2()
    {
        Vector<JPanel> dir = link.get(0);
        JPanel linkPanel = dir.get(1);
        RectangleText rect = (RectangleText) linkPanel.getComponent(0);
        linkPanel.add(new RectangleText(rect.getDimension()), 0);
        linkPanel.remove(1);
        return true;
    }
    
    public boolean simulation3(boolean n)
    {
        if(!n)
        {
            for(int i = 0; i < keys.size() - 1; i++)
            {
                if(keys.get(i) == key)
                {
                    JOptionPane.showMessageDialog(null, "Key is already present, Sir.", "Add key", JOptionPane.WARNING_MESSAGE);
                    keys.remove(keys.size() - 1);
                    flag = false;
                    return true;
                }
            }
        }
        Vector<JPanel> blocks = link.get(1);
        for(JPanel block : blocks)
        {
            RectangleText rt = (RectangleText) block.getComponent(0);
            String str = rt.getString();
            if(str == null)
            {
                block.add(new RectangleText(String.valueOf(key), rt.getDimension()), 0);
                block.remove(1);
                flag = false;
                return true;
            }
        }
        JPanel block = blocks.get(0);
        RectangleText rt = (RectangleText) block.getComponent(0);
        overflow = rt.getString();
        flag = true;
        return true;
    }
        
    public void simulation4()
    {
        jButton1.setText("Enter key value please.");
        jButton1.setVisible(true);
        //jLabel2.setVisible(false);
    }
    
    public void simulateNew()
    {
        int newgd = gd;
        Vector<String> copy = (Vector<String>) buckets.clone();
        JOptionPane.showMessageDialog(null, "Overflow detected.\nSplitting data buckets to accomodate : " + overflow, "Overflow", JOptionPane.WARNING_MESSAGE);
        int index = buckets.indexOf(overflow);
        buckets.remove(overflow);
        buckets.add(index, "1" + overflow);
        buckets.add(index, "0" + overflow);
        int longest = Utility.longest(buckets);
        if(longest > 4)
        {
            JOptionPane.showMessageDialog(null, "Local depth too high to display. \nKey discarded.\n(Local depth = " + longest + ")", "Overflow", JOptionPane.ERROR_MESSAGE);
            buckets = copy;
            keys.remove(keys.size() - 1);
            simulation2();
            simulation4();
            return;
        }
        else if(longest > newgd)
        {
            JOptionPane.showMessageDialog(null, "Local depth exceeded global depth.\nGlobal depth increased\n(Local depth = " + longest + ", Global depth = " + gd + ")", "Overflow", JOptionPane.WARNING_MESSAGE);
            newgd = longest;
        }
        Simulation simulation = new Simulation(newgd, buckets, bfr, keys, this);
    }
    
    public boolean searchKey()
    {
        Vector<JPanel> bktlist = link.get(1);
        JPanel index = bktlist.get(0);
        RectangleText rt = (RectangleText) index.getComponent(0);
        String bucket = rt.getString();
        if(keys.contains(key))
        {
            JOptionPane.showMessageDialog(null, "Key found! Inside :  " + bucket, "Search key", JOptionPane.INFORMATION_MESSAGE);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Key not found. ", "Search key", JOptionPane.ERROR_MESSAGE);
        }
        return true;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    // End of variables declaration//GEN-END:variables
}