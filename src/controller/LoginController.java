package controller;

import view.LoginForm.LoginForm;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

public class LoginController{
    public LoginController(LoginForm loginForm, CardLayout cardLayout)
    {

        loginForm.txtPass.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    loginProcess(loginForm.txtUserName.getText(), Arrays.toString(loginForm.txtPass.getPassword()));
                }
            }
        });
        loginForm.btnShowRegisterCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cardLayout.show(loginForm.mainPanel, "registerCard");
            }
        });
        loginForm.btnShowLoginCard.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cardLayout.show(loginForm.mainPanel, "loginCard");
            }
        });
    }



    private void loginProcess(String username, String pass)
    {

    }
}
