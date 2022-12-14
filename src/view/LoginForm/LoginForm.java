/*
 * Created by JFormDesigner on Tue Aug 16 21:18:37 ICT 2022
 */

package view.LoginForm;

import controller.LoginController;
import view.Jarvis.Jarvis;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author khang
 */
public class LoginForm extends JFrame {
    private LoginController loginController;
    private CardLayout cardLayout;
    public LoginForm() {
        initComponents();
        loginConfig();
    }

    private void loginConfig()
    {
        this.setTitle("Jarvis");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir")+"\\src\\images\\iconTitle.jpg"));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        cardLayout = (CardLayout) mainPanel.getLayout();

        loginController = new LoginController();
        LoadUserData();
        EventListenerLogin();
        EventListenerRegister();
    }

    private void LoadUserData()
    {
        if(loginController.LoadData())
        {
            JOptionPane.showMessageDialog(LoginForm.this, "Can't load user data", "login: error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void EventListenerLogin()
    {
        //===== login form =====
        txtPass.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    LoginProcess();
                }
            }
        });
        btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                LoginProcess();
            }
        });

        btnShowLoginCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cardLayout.show(mainPanel, "loginCard");
            }
        });

        cbShowPass.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(txtPass.getEchoChar() == (char)0)
                {
                    txtPass.setEchoChar('\u2022');
                }
                else
                {
                    txtPass.setEchoChar((char)0);
                }
            }
        });
    }

    private void LoginProcess()
    {
        String email = txtEmail.getText();
        char[] pass = txtPass.getPassword();
        if(loginController.loginProcess(email, pass))
        {
            LoginForm.this.dispose();
            new Jarvis(email);
        }
        else
        {
            lbEmailOrPassWrong.setVisible(true);
        }
    }

    private void EventListenerRegister()
    {
//====== register form =====
        btnShowRegisterCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cardLayout.show(mainPanel, "registerCard");
            }
        });
        btnRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lbEmptyInforRegister.setVisible(false);
                lbPassIncorrectRegister.setVisible(false);
                lbEmailIncorrectRegister.setVisible(false);
                ShowStateRegister();

            }
        });
        txtEmailRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lbEmailIncorrectRegister.setVisible(false);
            }
        });
        txtPassRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lbPassIncorrectRegister.setVisible(false);
            }
        });
        txtPassConfirm.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lbPassIncorrectConfirm.setVisible(false);
            }
        });
    }

    private void ShowStateRegister()
    {
        String result = loginController.registerProcess(txtEmailRegister.getText(),
                txtPassRegister.getPassword(),
                txtPassConfirm.getPassword(), cbIsRoleAdmin.isSelected() ? "admin":"user");

        switch (result) {
            case "blank" -> lbEmptyInforRegister.setVisible(true);
            case "emailExisted" -> {
                lbEmailIncorrectRegister.setText("Email existed!");
                lbEmailIncorrectRegister.setVisible(true);
            }
            case "emailInvalid" -> {
                lbEmailIncorrectRegister.setText("Invalid email!");
                lbEmailIncorrectRegister.setVisible(true);
            }
            case "passInvalid" -> {
                lbPassIncorrectRegister.setText("Password must be greater than 5 character!");
                lbPassIncorrectRegister.setVisible(true);
            }
            case "confirmPassInvalid" -> {
                lbPassIncorrectConfirm.setText("Confirm password not match!");
                lbPassIncorrectConfirm.setVisible(true);
            }
            case "success" -> {
                lbEmptyInforRegister.setText("Register success <3");
                lbEmptyInforRegister.setForeground(new Color(35, 177, 77));
                lbEmptyInforRegister.setVisible(true);
            }
            case "fail" -> {
                lbEmptyInforRegister.setText("System error!");
                lbEmptyInforRegister.setVisible(true);
            }
        }

    }

    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel("com.formdev.flatlaf.FlatDarculaLaf");
        }catch (Exception ex){
        }
        new LoginForm();
    }


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - khang
        mainPanel = new JPanel();
        loginCard = new JPanel();
        label4 = new JLabel();
        label5 = new JLabel();
        txtEmail = new JTextField();
        txtPass = new JPasswordField();
        btnLogin = new JButton();
        label6 = new JLabel();
        panel3 = new JPanel();
        lbEmailOrPassWrong = new JLabel();
        label7 = new JLabel();
        btnShowRegisterCard = new JLabel();
        cbShowPass = new JCheckBox();
        registerCard = new JPanel();
        label1 = new JLabel();
        label2 = new JLabel();
        txtEmailRegister = new JTextField();
        txtPassRegister = new JPasswordField();
        btnRegister = new JButton();
        label3 = new JLabel();
        panel1 = new JPanel();
        lbEmailIncorrectRegister = new JLabel();
        panel2 = new JPanel();
        lbPassIncorrectRegister = new JLabel();
        btnShowLoginCard = new JLabel();
        txtPassConfirm = new JPasswordField();
        label8 = new JLabel();
        panel5 = new JPanel();
        lbPassIncorrectConfirm = new JLabel();
        panel6 = new JPanel();
        lbEmptyInforRegister = new JLabel();
        cbIsRoleAdmin = new JCheckBox();

        //======== this ========
        var contentPane = getContentPane();

        //======== mainPanel ========
        {
            mainPanel.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new
            javax. swing. border. EmptyBorder( 0, 0, 0, 0) , "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e", javax
            . swing. border. TitledBorder. CENTER, javax. swing. border. TitledBorder. BOTTOM, new java
            .awt .Font ("Dialo\u0067" ,java .awt .Font .BOLD ,12 ), java. awt
            . Color. red) ,mainPanel. getBorder( )) ); mainPanel. addPropertyChangeListener (new java. beans.
            PropertyChangeListener( ){ @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("borde\u0072" .
            equals (e .getPropertyName () )) throw new RuntimeException( ); }} );
            mainPanel.setLayout(new CardLayout());

            //======== loginCard ========
            {

                //---- label4 ----
                label4.setText("EMAIL");
                label4.setFont(label4.getFont().deriveFont(label4.getFont().getStyle() | Font.BOLD));

                //---- label5 ----
                label5.setText("PASSWORD");
                label5.setFont(label5.getFont().deriveFont(label5.getFont().getStyle() | Font.BOLD));

                //---- txtEmail ----
                txtEmail.setMaximumSize(null);
                txtEmail.setMinimumSize(null);
                txtEmail.setPreferredSize(new Dimension(60, 30));

                //---- txtPass ----
                txtPass.setMaximumSize(null);
                txtPass.setMinimumSize(null);
                txtPass.setPreferredSize(new Dimension(60, 30));

                //---- btnLogin ----
                btnLogin.setText("Login");
                btnLogin.setMaximumSize(null);
                btnLogin.setMinimumSize(null);
                btnLogin.setPreferredSize(new Dimension(60, 35));
                btnLogin.setBackground(new Color(88, 101, 242));
                btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnLogin.setForeground(Color.white);
                btnLogin.setFont(new Font("JetBrains Mono ExtraBold", Font.BOLD, 15));

                //---- label6 ----
                label6.setText("Wellcome To JARVIS!");
                label6.setHorizontalAlignment(SwingConstants.CENTER);
                label6.setFont(new Font("JetBrains Mono ExtraBold", Font.BOLD, 18));
                label6.setForeground(Color.white);

                //======== panel3 ========
                {
                    panel3.setPreferredSize(new Dimension(248, 20));

                    //---- lbEmailOrPassWrong ----
                    lbEmailOrPassWrong.setText("Email or passwrod incorrect !");
                    lbEmailOrPassWrong.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    lbEmailOrPassWrong.setForeground(new Color(229, 128, 131));
                    lbEmailOrPassWrong.setVisible(false);

                    GroupLayout panel3Layout = new GroupLayout(panel3);
                    panel3.setLayout(panel3Layout);
                    panel3Layout.setHorizontalGroup(
                        panel3Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel3Layout.createSequentialGroup()
                                .addContainerGap(277, Short.MAX_VALUE)
                                .addComponent(lbEmailOrPassWrong, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34))
                    );
                    panel3Layout.setVerticalGroup(
                        panel3Layout.createParallelGroup()
                            .addComponent(lbEmailOrPassWrong, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                    );
                }

                //---- label7 ----
                label7.setText("Need an account?");
                label7.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));

                //---- btnShowRegisterCard ----
                btnShowRegisterCard.setText("Register");
                btnShowRegisterCard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnShowRegisterCard.setForeground(new Color(0, 175, 244));

                //---- cbShowPass ----
                cbShowPass.setText("Show password");
                cbShowPass.setFont(new Font("JetBrains Mono", Font.PLAIN, 11));

                GroupLayout loginCardLayout = new GroupLayout(loginCard);
                loginCard.setLayout(loginCardLayout);
                loginCardLayout.setHorizontalGroup(
                    loginCardLayout.createParallelGroup()
                        .addGroup(loginCardLayout.createSequentialGroup()
                            .addGroup(loginCardLayout.createParallelGroup()
                                .addGroup(loginCardLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(panel3, GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE))
                                .addGroup(loginCardLayout.createSequentialGroup()
                                    .addGroup(loginCardLayout.createParallelGroup()
                                        .addGroup(loginCardLayout.createSequentialGroup()
                                            .addGap(31, 31, 31)
                                            .addGroup(loginCardLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(label4)
                                                .addComponent(label6, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                                                .addComponent(txtPass, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(label5)
                                                .addComponent(txtEmail, GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)))
                                        .addGroup(loginCardLayout.createSequentialGroup()
                                            .addGap(32, 32, 32)
                                            .addGroup(loginCardLayout.createParallelGroup()
                                                .addGroup(loginCardLayout.createSequentialGroup()
                                                    .addGap(8, 8, 8)
                                                    .addComponent(label7)
                                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(btnShowRegisterCard))
                                                .addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(GroupLayout.Alignment.TRAILING, loginCardLayout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(cbShowPass, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)))
                                    .addGap(0, 37, Short.MAX_VALUE)))
                            .addContainerGap())
                );
                loginCardLayout.setVerticalGroup(
                    loginCardLayout.createParallelGroup()
                        .addGroup(loginCardLayout.createSequentialGroup()
                            .addGap(15, 15, 15)
                            .addComponent(label6, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
                            .addGap(81, 81, 81)
                            .addComponent(label4)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(label5)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtPass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(panel3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbShowPass)
                            .addGap(31, 31, 31)
                            .addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(8, 8, 8)
                            .addGroup(loginCardLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(btnShowRegisterCard)
                                .addComponent(label7))
                            .addContainerGap(62, Short.MAX_VALUE))
                );
            }
            mainPanel.add(loginCard, "loginCard");

            //======== registerCard ========
            {

                //---- label1 ----
                label1.setText("EMAIL");
                label1.setFont(label1.getFont().deriveFont(label1.getFont().getStyle() | Font.BOLD));

                //---- label2 ----
                label2.setText("PASSWORD");
                label2.setFont(label2.getFont().deriveFont(label2.getFont().getStyle() | Font.BOLD));

                //---- txtEmailRegister ----
                txtEmailRegister.setMaximumSize(null);
                txtEmailRegister.setMinimumSize(null);
                txtEmailRegister.setPreferredSize(new Dimension(60, 25));

                //---- txtPassRegister ----
                txtPassRegister.setMaximumSize(null);
                txtPassRegister.setMinimumSize(null);
                txtPassRegister.setPreferredSize(new Dimension(60, 25));

                //---- btnRegister ----
                btnRegister.setText("Register");
                btnRegister.setMaximumSize(null);
                btnRegister.setMinimumSize(null);
                btnRegister.setPreferredSize(new Dimension(60, 35));
                btnRegister.setBackground(new Color(88, 101, 242));
                btnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnRegister.setForeground(Color.white);
                btnRegister.setFont(new Font("JetBrains Mono ExtraBold", Font.BOLD, 15));

                //---- label3 ----
                label3.setText("Create an account");
                label3.setHorizontalAlignment(SwingConstants.CENTER);
                label3.setFont(new Font("JetBrains Mono ExtraBold", Font.BOLD, 18));
                label3.setForeground(Color.white);

                //======== panel1 ========
                {
                    panel1.setPreferredSize(new Dimension(248, 20));

                    //---- lbEmailIncorrectRegister ----
                    lbEmailIncorrectRegister.setText("Username incorrect");
                    lbEmailIncorrectRegister.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    lbEmailIncorrectRegister.setForeground(new Color(229, 128, 131));
                    lbEmailIncorrectRegister.setVisible(false);

                    GroupLayout panel1Layout = new GroupLayout(panel1);
                    panel1.setLayout(panel1Layout);
                    panel1Layout.setHorizontalGroup(
                        panel1Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbEmailIncorrectRegister)
                                .addGap(39, 39, 39))
                    );
                    panel1Layout.setVerticalGroup(
                        panel1Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                .addGap(0, 20, Short.MAX_VALUE)
                                .addComponent(lbEmailIncorrectRegister))
                    );
                }

                //======== panel2 ========
                {
                    panel2.setPreferredSize(new Dimension(242, 20));

                    //---- lbPassIncorrectRegister ----
                    lbPassIncorrectRegister.setText("Password incorrect");
                    lbPassIncorrectRegister.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    lbPassIncorrectRegister.setForeground(new Color(229, 128, 131));
                    lbPassIncorrectRegister.setVisible(false);

                    GroupLayout panel2Layout = new GroupLayout(panel2);
                    panel2.setLayout(panel2Layout);
                    panel2Layout.setHorizontalGroup(
                        panel2Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbPassIncorrectRegister)
                                .addGap(39, 39, 39))
                    );
                    panel2Layout.setVerticalGroup(
                        panel2Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                                .addGap(0, 20, Short.MAX_VALUE)
                                .addComponent(lbPassIncorrectRegister))
                    );
                }

                //---- btnShowLoginCard ----
                btnShowLoginCard.setText("Already have an account?");
                btnShowLoginCard.setForeground(new Color(0, 175, 244));
                btnShowLoginCard.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                btnShowLoginCard.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                //---- txtPassConfirm ----
                txtPassConfirm.setMaximumSize(null);
                txtPassConfirm.setMinimumSize(null);
                txtPassConfirm.setPreferredSize(new Dimension(60, 25));

                //---- label8 ----
                label8.setText("CONFIRM PASSWORD");
                label8.setFont(label8.getFont().deriveFont(label8.getFont().getStyle() | Font.BOLD));

                //======== panel5 ========
                {
                    panel5.setPreferredSize(new Dimension(242, 20));

                    //---- lbPassIncorrectConfirm ----
                    lbPassIncorrectConfirm.setText("Confirm not match");
                    lbPassIncorrectConfirm.setFont(new Font("JetBrains Mono", Font.PLAIN, 12));
                    lbPassIncorrectConfirm.setForeground(new Color(229, 128, 131));
                    lbPassIncorrectConfirm.setVisible(false);

                    GroupLayout panel5Layout = new GroupLayout(panel5);
                    panel5.setLayout(panel5Layout);
                    panel5Layout.setHorizontalGroup(
                        panel5Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel5Layout.createSequentialGroup()
                                .addContainerGap(235, Short.MAX_VALUE)
                                .addComponent(lbPassIncorrectConfirm)
                                .addGap(40, 40, 40))
                    );
                    panel5Layout.setVerticalGroup(
                        panel5Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel5Layout.createSequentialGroup()
                                .addGap(0, 20, Short.MAX_VALUE)
                                .addComponent(lbPassIncorrectConfirm))
                    );
                }

                //======== panel6 ========
                {
                    panel6.setPreferredSize(new Dimension(242, 20));

                    //---- lbEmptyInforRegister ----
                    lbEmptyInforRegister.setText("Please enter all information!");
                    lbEmptyInforRegister.setForeground(new Color(229, 128, 131));
                    lbEmptyInforRegister.setVisible(false);

                    GroupLayout panel6Layout = new GroupLayout(panel6);
                    panel6.setLayout(panel6Layout);
                    panel6Layout.setHorizontalGroup(
                        panel6Layout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, panel6Layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbEmptyInforRegister, GroupLayout.PREFERRED_SIZE, 235, GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34))
                    );
                    panel6Layout.setVerticalGroup(
                        panel6Layout.createParallelGroup()
                            .addGroup(panel6Layout.createSequentialGroup()
                                .addComponent(lbEmptyInforRegister)
                                .addGap(0, 25, Short.MAX_VALUE))
                    );
                }

                //---- cbIsRoleAdmin ----
                cbIsRoleAdmin.setText("Role admin");

                GroupLayout registerCardLayout = new GroupLayout(registerCard);
                registerCard.setLayout(registerCardLayout);
                registerCardLayout.setHorizontalGroup(
                    registerCardLayout.createParallelGroup()
                        .addGroup(registerCardLayout.createSequentialGroup()
                            .addGap(43, 43, 43)
                            .addGroup(registerCardLayout.createParallelGroup()
                                .addGroup(registerCardLayout.createSequentialGroup()
                                    .addGroup(registerCardLayout.createParallelGroup()
                                        .addComponent(txtPassRegister, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label2)
                                        .addComponent(label1)
                                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtEmailRegister, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 26, Short.MAX_VALUE))
                                .addComponent(panel2, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                                .addComponent(panel1, GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE))
                            .addContainerGap())
                        .addGroup(registerCardLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(registerCardLayout.createParallelGroup()
                                .addComponent(panel6, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 317, Short.MAX_VALUE)
                                .addGroup(registerCardLayout.createSequentialGroup()
                                    .addGap(37, 37, 37)
                                    .addGroup(registerCardLayout.createParallelGroup()
                                        .addComponent(panel5, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtPassConfirm, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(registerCardLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addGroup(registerCardLayout.createParallelGroup()
                                                .addGroup(registerCardLayout.createSequentialGroup()
                                                    .addGap(6, 6, 6)
                                                    .addComponent(btnShowLoginCard))
                                                .addComponent(btnRegister, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(registerCardLayout.createSequentialGroup()
                                                .addGap(125, 125, 125)
                                                .addComponent(cbIsRoleAdmin, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(label8, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)))))
                );
                registerCardLayout.setVerticalGroup(
                    registerCardLayout.createParallelGroup()
                        .addGroup(registerCardLayout.createSequentialGroup()
                            .addGap(13, 13, 13)
                            .addComponent(label3, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(label1)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtEmailRegister, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(label2)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtPassRegister, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(2, 2, 2)
                            .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(label8)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtPassConfirm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panel5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12)
                            .addComponent(panel6, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cbIsRoleAdmin, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnRegister, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnShowLoginCard)
                            .addContainerGap(35, Short.MAX_VALUE))
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
    public JTextField txtEmail;
    public JPasswordField txtPass;
    public JButton btnLogin;
    private JLabel label6;
    private JPanel panel3;
    public JLabel lbEmailOrPassWrong;
    private JLabel label7;
    public JLabel btnShowRegisterCard;
    public JCheckBox cbShowPass;
    public JPanel registerCard;
    private JLabel label1;
    private JLabel label2;
    public JTextField txtEmailRegister;
    public JPasswordField txtPassRegister;
    public JButton btnRegister;
    private JLabel label3;
    private JPanel panel1;
    public JLabel lbEmailIncorrectRegister;
    private JPanel panel2;
    public JLabel lbPassIncorrectRegister;
    public JLabel btnShowLoginCard;
    public JPasswordField txtPassConfirm;
    private JLabel label8;
    private JPanel panel5;
    public JLabel lbPassIncorrectConfirm;
    private JPanel panel6;
    public JLabel lbEmptyInforRegister;
    public JCheckBox cbIsRoleAdmin;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
