/*
 * Created by JFormDesigner on Mon Aug 15 19:42:23 ICT 2022
 */

package courseManager;

import java.awt.event.*;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;
import java.awt.*;
import javax.swing.GroupLayout;

/**
 * @author khang
 */
public class CuorseManager extends JFrame {
    public CuorseManager() {
        initComponents();
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - khang
        MainPanel = new JPanel();
        btnLogin = new JButton();
        textField1 = new JTextField();
        label1 = new JLabel();
        label2 = new JLabel();
        passwordField1 = new JPasswordField();

        //======== this ========
        var contentPane = getContentPane();

        //======== MainPanel ========
        {
            MainPanel.setFocusCycleRoot(true);
            MainPanel.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder(
            0, 0, 0, 0) , "JF\u006frmD\u0065sig\u006eer \u0045val\u0075ati\u006fn", javax. swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder
            . BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ), java. awt. Color.
            red) ,MainPanel. getBorder( )) ); MainPanel. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void propertyChange (java .
            beans .PropertyChangeEvent e) {if ("\u0062ord\u0065r" .equals (e .getPropertyName () )) throw new RuntimeException( ); }} );

            //---- btnLogin ----
            btnLogin.setText("Login");
            btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            btnLogin.setMaximumSize(null);
            btnLogin.setMinimumSize(null);
            btnLogin.setPreferredSize(new Dimension(94, 35));
            btnLogin.setBackground(new Color(88, 101, 242));
            btnLogin.setForeground(Color.white);
            btnLogin.setFont(new Font("Segoe UI Historic", Font.BOLD, 15));

            //---- textField1 ----
            textField1.setMaximumSize(null);
            textField1.setMinimumSize(null);
            textField1.setPreferredSize(new Dimension(60, 25));

            //---- label1 ----
            label1.setText("USERNAME");

            //---- label2 ----
            label2.setText("PASSWORD");

            //---- passwordField1 ----
            passwordField1.setPreferredSize(new Dimension(60, 25));
            passwordField1.setMargin(new Insets(5, 15, 5, 15));
            passwordField1.setMinimumSize(null);
            passwordField1.setMaximumSize(null);

            GroupLayout MainPanelLayout = new GroupLayout(MainPanel);
            MainPanel.setLayout(MainPanelLayout);
            MainPanelLayout.setHorizontalGroup(
                MainPanelLayout.createParallelGroup()
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(label2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(MainPanelLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(MainPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnLogin, GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
                            .addComponent(textField1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(MainPanelLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(label1))
                            .addComponent(passwordField1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(35, Short.MAX_VALUE))
            );
            MainPanelLayout.setVerticalGroup(
                MainPanelLayout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                        .addContainerGap(181, Short.MAX_VALUE)
                        .addComponent(label1)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(label2)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addGap(121, 121, 121))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(MainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap(9, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(MainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap())
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        }catch (Exception exception){
        }
        CuorseManager cuorseManager = new CuorseManager();
        cuorseManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cuorseManager.setTitle("Jarvis");
        cuorseManager.setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/iconTitle.jpg"));
        cuorseManager.setVisible(true);
    }


    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - khang
    private JPanel MainPanel;
    private JButton btnLogin;
    private JTextField textField1;
    private JLabel label1;
    private JLabel label2;
    private JPasswordField passwordField1;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
