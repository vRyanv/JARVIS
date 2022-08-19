package controller;

import library.fileProcess.FileProcess;
import model.user.User;
import view.CourseManager.CourseManager;
import view.LoginForm.LoginForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginController{
    public String pathUserList = System.getProperty("user.dir")+"\\src\\model\\user\\userList.dat";
    private TreeMap<String, User> userTreeMap;
    private LoginForm loginForm;
    public LoginController(LoginForm loginForm, CardLayout cardLayout)
    {
        this.loginForm = loginForm;
        this.LoadData();

        //===== login form =====
        loginForm.txtPass.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    loginProcess(loginForm.txtEmail.getText(), loginForm.txtPass.getPassword());
                }
            }
        });
        loginForm.btnLogin.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                loginProcess(loginForm.txtEmail.getText(), loginForm.txtPass.getPassword());
            }
        });

        loginForm.btnShowLoginCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cardLayout.show(loginForm.mainPanel, "loginCard");
            }
        });



        //====== register form =====
        loginForm.btnShowRegisterCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cardLayout.show(loginForm.mainPanel, "registerCard");
            }
        });
        loginForm.btnRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                loginForm.lbEmptyInforRegister.setVisible(false);
                loginForm.lbPassIncorrectRegister.setVisible(false);
                loginForm.lbEmailIncorrectRegister.setVisible(false);
                registerProcess(loginForm.txtEmailRegister.getText(), loginForm.txtPassRegister.getPassword(), loginForm.txtPassConfirm.getPassword());
            }
        });
        loginForm.txtEmailRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                loginForm.lbEmailIncorrectRegister.setVisible(false);
            }
        });
        loginForm.txtPassRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                loginForm.lbPassIncorrectRegister.setVisible(false);
            }
        });
        loginForm.txtPassConfirm.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                loginForm.lbPassIncorrectConfirm.setVisible(false);
            }
        });
    }

    private void LoadData()
    {
        if(this.userTreeMap == null)
        {
            this.userTreeMap = (TreeMap<String, User>) FileProcess.readObject(this.pathUserList);
            if(this.userTreeMap == null)
            {
                JOptionPane.showMessageDialog(this.loginForm, "Can't load user data", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void loginProcess(String email, char[] pass)
    {
        if(this.userTreeMap.containsKey(email))
        {
            if(Arrays.equals(this.userTreeMap.get(email).getPassword(), pass))
            {
                this.loginForm.dispose();
                new CourseManager(email);
            }
        }
        else
        {
            this.loginForm.lbEmailOrPassWrong.setVisible(true);
        }
    }

    private void registerProcess(String email, char[] pass,  char[] passConfirm)
    {
        if(email.isBlank() || pass.length == 0 || passConfirm.length == 0)
        {
            this.loginForm.lbEmptyInforRegister.setVisible(true);
        }
        else
        {
            if(!checkInvalidEmail(email))
            {
                this.loginForm.lbEmailIncorrectRegister.setText("Invalid email!");
                this.loginForm.lbEmailIncorrectRegister.setVisible(true);
            }
            else if(checkEmailExisted(email))
            {
                this.loginForm.lbEmailIncorrectRegister.setText("email existed!");
                this.loginForm.lbEmailIncorrectRegister.setVisible(true);
            }
            else if (!checkPasswordRegister(pass))
            {
                this.loginForm.lbPassIncorrectRegister.setText("Password must be greater than 5 character!");
                this.loginForm.lbPassIncorrectRegister.setVisible(true);
            }
            else if (!checkConfirmPass(pass, passConfirm))
            {
                this.loginForm.lbPassIncorrectConfirm.setText("Confirm password not match!");
                this.loginForm.lbPassIncorrectConfirm.setVisible(true);
            }
            else
            {
                if(saveAccount(email, pass))
                {
                    loginForm.lbEmptyInforRegister.setText("Register success <3");
                    loginForm.lbEmptyInforRegister.setForeground(new Color(35, 177, 77));
                    loginForm.lbEmptyInforRegister.setVisible(true);
                }
                else
                {
                    loginForm.lbEmptyInforRegister.setText("System error!");
                    loginForm.lbEmptyInforRegister.setVisible(true);
                }
            }
        }


    }

    private boolean checkInvalidEmail(String email)
    {
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    private boolean checkEmailExisted(String email)
    {
        return userTreeMap == null ? false : userTreeMap.containsKey(email);

    }

    private boolean checkPasswordRegister(char[] pass)
    {
      return pass.length >= 5;
    }

    private boolean checkConfirmPass(char[] pass, char[] confirmPass)
    {
        return Arrays.equals(pass, confirmPass);
    }

    private boolean saveAccount(String email, char[] pass)
    {
        this.userTreeMap.put(email, new User(email, pass));
        return FileProcess.writeObject(this.pathUserList ,this.userTreeMap);
    }


}
