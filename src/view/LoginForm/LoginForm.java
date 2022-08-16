/*
 * Created by JFormDesigner on Tue Aug 16 21:18:37 ICT 2022
 */

package view.LoginForm;

import java.awt.event.*;
import com.formdev.flatlaf.FlatDarculaLaf;
import controller.LoginController;

import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author khang
 */
public class LoginForm extends JFrame {
    public LoginForm() {
        initComponents();
        loginConfig();
    }

    private void loginConfig()
    {
        this.setTitle("Jarvis");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/iconTitle.jpg"));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        //event listener
        LoginController loginController = new LoginController(this, (CardLayout) this.mainPanel.getLayout());
        
        
    }

    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        }catch (Exception ex){

        }
        LoginForm loginForm = new LoginForm();
    }

    private void btnLoginMousePressed(MouseEvent e) {
        // TODO add your code here
    }
    
    
    

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - khang
        mainPanel = new JPanel();
        loginCard = new JPanel();
        label4 = new JLabel();
        label5 = new JLabel();
        txtUserName2 = new JTextField();
        txtPass2 = new JPasswordField();
        btnLogin2 = new JButton();
        label6 = new JLabel();
        panel3 = new JPanel();
        lbUsernameIncorrect2 = new JLabel();
        panel4 = new JPanel();
        lbPassIncorrect2 = new JLabel();
        label7 = new JLabel();
        btnShowRegisterCard = new JLabel();
        registerCard = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        txtUserName = new JTextField();
        txtPass = new JPasswordField();
        btnLogin = new JButton();
        label3 = new JLabel();
        panel1 = new JPanel();
        lbUsernameIncorrect = new JLabel();
        panel2 = new JPanel();
        lbPassIncorrect = new JLabel();
        btnShowLoginCard = new JLabel();

        //======== this ========
        var contentPane = getContentPane();

        //======== mainPanel ========
        {
            mainPanel.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border
            . EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDes\u0069gner \u0045valua\u0074ion", javax. swing. border. TitledBorder. CENTER, javax
            . swing. border. TitledBorder. BOTTOM, new java .awt .Font ("D\u0069alog" ,java .awt .Font .BOLD ,
            12 ), java. awt. Color. red) ,mainPanel. getBorder( )) ); mainPanel. addPropertyChangeListener (new java. beans
            . PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("\u0062order" .equals (e .
            getPropertyName () )) throw new RuntimeException( ); }} );
            mainPanel.setLayout(new CardLayout());

            //======== loginCard ========
            {

                //---- label4 ----
                label4.setText("USERNAME");
                label4.setFont(label4.getFont().deriveFont(label4.getFont().getStyle() | Font.BOLD));

                //---- label5 ----
                label5.setText("PASSWORD");
                label5.setFont(label5.getFont().deriveFont(label5.getFont().getStyle() | Font.BOLD));

                //---- txtUserName2 ----
                txtUserName2.setMaximumSize(null);
                txtUserName2.setMinimumSize(null);
                txtUserName2.setPreferredSize(new Dimension(60, 25));

                //---- txtPass2 ----
                txtPass2.setMaximumSize(null);
                txtPass2.setMinimumSize(null);
                txtPass2.setPreferredSize(new Dimension(60, 25));

                //---- btnLogin2 ----
                btnLogin2.setText("Login");
                btnLogin2.setMaximumSize(null);
                btnLogin2.setMinimumSize(null);
                btnLogin2.setPreferredSize(new Dimension(60, 35));
                btnLogin2.setBackground(new Color(88, 101, 242));
                btnLogin2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnLogin2.setForeground(Color.white);
                btnLogin2.setFont(new Font("JetBrains Mono ExtraBold", Font.BOLD, 15));

                //---- label6 ----
                label6.setText("Wellcome To JARVIS!");
                label6.setHorizontalAlignment(SwingConstants.CENTER);
                label6.setFont(new Font("JetBrains Mono ExtraBold", Font.BOLD, 18));
                label6.setForeground(Color.white);

                //======== panel3 ========
                {
                    panel3.setPreferredSize(new Dimension(248, 20));

                    //---- lbUsernameIncorrect2 ----
                    lbUsernameIncorrect2.setText("Username incorrect");
                    lbUsernameIncorrect2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    lbUsernameIncorrect2.setForeground(new Color(255, 0, 51));

                    GroupLayout panel3Layout = new GroupLayout(panel3);
                    panel3.setLayout(panel3Layout);
                    panel3Layout.setHorizontalGroup(
                        panel3Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                                .addContainerGap(133, Short.MAX_VALUE)
                                .addComponent(lbUsernameIncorrect2, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                    );
                    panel3Layout.setVerticalGroup(
                        panel3Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                                .addGap(0, 3, Short.MAX_VALUE)
                                .addComponent(lbUsernameIncorrect2))
                    );
                }

                //======== panel4 ========
                {
                    panel4.setPreferredSize(new Dimension(242, 20));

                    //---- lbPassIncorrect2 ----
                    lbPassIncorrect2.setText("Password incorrect");
                    lbPassIncorrect2.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    lbPassIncorrect2.setForeground(new Color(255, 0, 51));

                    GroupLayout panel4Layout = new GroupLayout(panel4);
                    panel4.setLayout(panel4Layout);
                    panel4Layout.setHorizontalGroup(
                        panel4Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel4Layout.createSequentialGroup()
                                .addContainerGap(158, Short.MAX_VALUE)
                                .addComponent(lbPassIncorrect2, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                    );
                    panel4Layout.setVerticalGroup(
                        panel4Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel4Layout.createSequentialGroup()
                                .addGap(0, 3, Short.MAX_VALUE)
                                .addComponent(lbPassIncorrect2))
                    );
                }

                //---- label7 ----
                label7.setText("Need an account?");
                label7.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));

                //---- btnShowRegisterCard ----
                btnShowRegisterCard.setText("Register");
                btnShowRegisterCard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnShowRegisterCard.setForeground(new Color(0, 175, 244));

                GroupLayout loginCardLayout = new GroupLayout(loginCard);
                loginCard.setLayout(loginCardLayout);
                loginCardLayout.setHorizontalGroup(
                    loginCardLayout.createParallelGroup()
                        .addGroup(loginCardLayout.createSequentialGroup()
                            .addGroup(loginCardLayout.createParallelGroup()
                                .addGroup(loginCardLayout.createSequentialGroup()
                                    .addGap(31, 31, 31)
                                    .addGroup(loginCardLayout.createParallelGroup()
                                        .addComponent(panel3, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                                        .addGroup(loginCardLayout.createSequentialGroup()
                                            .addGroup(loginCardLayout.createParallelGroup()
                                                .addGroup(loginCardLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtUserName2, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                                                    .addComponent(label4)
                                                    .addComponent(txtPass2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(label5)
                                                    .addComponent(btnLogin2, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(label6, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE))
                                                .addGroup(loginCardLayout.createSequentialGroup()
                                                    .addGap(6, 6, 6)
                                                    .addComponent(label7)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(btnShowRegisterCard)))
                                            .addGap(0, 33, Short.MAX_VALUE))))
                                .addGroup(GroupLayout.Alignment.TRAILING, loginCardLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(panel4, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)))
                            .addContainerGap())
                );
                loginCardLayout.setVerticalGroup(
                    loginCardLayout.createParallelGroup()
                        .addGroup(loginCardLayout.createSequentialGroup()
                            .addGap(53, 53, 53)
                            .addComponent(label6, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
                            .addGap(43, 43, 43)
                            .addComponent(label4)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtUserName2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(label5)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtPass2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(panel4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnLogin2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(loginCardLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(btnShowRegisterCard)
                                .addComponent(label7))
                            .addContainerGap(77, Short.MAX_VALUE))
                );
            }
            mainPanel.add(loginCard, "loginCard");

            //======== registerCard ========
            {

                //---- label1 ----
                label1.setText("USERNAME");
                label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD));

                //---- label2 ----
                label2.setText("PASSWORD");
                label2.setFont(label2.getFont().deriveFont(label2.getFont().getStyle() | Font.BOLD));

                //---- txtUserName ----
                txtUserName.setMaximumSize(null);
                txtUserName.setMinimumSize(null);
                txtUserName.setPreferredSize(new Dimension(60, 25));

                //---- txtPass ----
                txtPass.setMaximumSize(null);
                txtPass.setMinimumSize(null);
                txtPass.setPreferredSize(new Dimension(60, 25));

                //---- btnLogin ----
                btnLogin.setText("Register");
                btnLogin.setMaximumSize(null);
                btnLogin.setMinimumSize(null);
                btnLogin.setPreferredSize(new Dimension(60, 35));
                btnLogin.setBackground(new Color(88, 101, 242));
                btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnLogin.setForeground(Color.white);
                btnLogin.setFont(new Font("JetBrains Mono ExtraBold", Font.BOLD, 15));
                btnLogin.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent e) {
                        btnLoginMousePressed(e);
                    }
                });

                //---- label3 ----
                label3.setText("Wellcome To JARVIS!");
                label3.setHorizontalAlignment(SwingConstants.CENTER);
                label3.setFont(new Font("JetBrains Mono ExtraBold", Font.BOLD, 18));
                label3.setForeground(Color.white);

                //======== panel1 ========
                {
                    panel1.setPreferredSize(new Dimension(248, 20));

                    //---- lbUsernameIncorrect ----
                    lbUsernameIncorrect.setText("Username incorrect");
                    lbUsernameIncorrect.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    lbUsernameIncorrect.setForeground(new Color(255, 0, 51));

                    GroupLayout panel1Layout = new GroupLayout(panel1);
                    panel1.setLayout(panel1Layout);
                    panel1Layout.setHorizontalGroup(
                        panel1Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addContainerGap(133, Short.MAX_VALUE)
                                .addComponent(lbUsernameIncorrect, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                    );
                    panel1Layout.setVerticalGroup(
                        panel1Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addGap(0, 3, Short.MAX_VALUE)
                                .addComponent(lbUsernameIncorrect))
                    );
                }

                //======== panel2 ========
                {
                    panel2.setPreferredSize(new Dimension(242, 20));

                    //---- lbPassIncorrect ----
                    lbPassIncorrect.setText("Password incorrect");
                    lbPassIncorrect.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    lbPassIncorrect.setForeground(new Color(255, 0, 51));

                    GroupLayout panel2Layout = new GroupLayout(panel2);
                    panel2.setLayout(panel2Layout);
                    panel2Layout.setHorizontalGroup(
                        panel2Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                                .addContainerGap(158, Short.MAX_VALUE)
                                .addComponent(lbPassIncorrect, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                    );
                    panel2Layout.setVerticalGroup(
                        panel2Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                                .addGap(0, 3, Short.MAX_VALUE)
                                .addComponent(lbPassIncorrect))
                    );
                }

                //---- btnShowLoginCard ----
                btnShowLoginCard.setText("Already have an account?");
                btnShowLoginCard.setForeground(new Color(0, 175, 244));
                btnShowLoginCard.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                btnShowLoginCard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                GroupLayout registerCardLayout = new GroupLayout(registerCard);
                registerCard.setLayout(registerCardLayout);
                registerCardLayout.setHorizontalGroup(
                    registerCardLayout.createParallelGroup()
                        .addGroup(registerCardLayout.createSequentialGroup()
                            .addGroup(registerCardLayout.createParallelGroup()
                                .addGroup(registerCardLayout.createSequentialGroup()
                                    .addGap(31, 31, 31)
                                    .addGroup(registerCardLayout.createParallelGroup()
                                        .addComponent(panel1, GroupLayout.DEFAULT_SIZE, 281, Short.MAX_VALUE)
                                        .addGroup(registerCardLayout.createSequentialGroup()
                                            .addGroup(registerCardLayout.createParallelGroup()
                                                .addGroup(registerCardLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtUserName, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                                                    .addComponent(label1)
                                                    .addComponent(txtPass, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(label2)
                                                    .addComponent(btnLogin, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(label3, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE))
                                                .addGroup(registerCardLayout.createSequentialGroup()
                                                    .addGap(6, 6, 6)
                                                    .addComponent(btnShowLoginCard)))
                                            .addGap(0, 33, Short.MAX_VALUE))))
                                .addGroup(GroupLayout.Alignment.TRAILING, registerCardLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(panel2, GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)))
                            .addContainerGap())
                );
                registerCardLayout.setVerticalGroup(
                    registerCardLayout.createParallelGroup()
                        .addGroup(registerCardLayout.createSequentialGroup()
                            .addGap(53, 53, 53)
                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
                            .addGap(43, 43, 43)
                            .addComponent(label1)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtUserName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(label2)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtPass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnShowLoginCard)
                            .addContainerGap(80, Short.MAX_VALUE))
                );
            }
            mainPanel.add(registerCard, "registerCard");
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(mainPanel, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - khang
    public JPanel mainPanel;
    public JPanel loginCard;
    private JLabel label4;
    private JLabel label5;
    public JTextField txtUserName2;
    public JPasswordField txtPass2;
    private JButton btnLogin2;
    private JLabel label6;
    private JPanel panel3;
    public JLabel lbUsernameIncorrect2;
    private JPanel panel4;
    public JLabel lbPassIncorrect2;
    private JLabel label7;
    public JLabel btnShowRegisterCard;
    public JPanel registerCard;
    private JLabel label1;
    private JLabel label2;
    public JTextField txtUserName;
    public JPasswordField txtPass;
    private JButton btnLogin;
    private JLabel label3;
    private JPanel panel1;
    public JLabel lbUsernameIncorrect;
    private JPanel panel2;
    public JLabel lbPassIncorrect;
    public JLabel btnShowLoginCard;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
