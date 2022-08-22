package controller;

import library.colorCustom.ColorCustom;
import view.CourseManager.CourseManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CourseManagerController {
    private String email;
    private CardLayout cardLayout;
    public String currentCard = "cardCourse";
    private CourseManager courseManager;

    public CourseManagerController(CourseManager courseManager, String email, String role)
    {
        this.courseManager = courseManager;
        CourseConfig(email, role);
    }

    private void CourseConfig(String email, String role)
    {
        this.email = email;
        this.courseManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.courseManager.setTitle("Jarvis");
        this.courseManager.setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/iconTitle.jpg"));
        this.courseManager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.courseManager.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        AddController(role);
        ChangeView();
    }

    private void AddController(String role)
    {
        new ClassRoomController(this.courseManager, email);

        if(role.equals("user"))
        {
            this.courseManager.adminController.setVisible(false);
        }
        else
        {
            new AdminController(this.courseManager);
        }

    }

    private void ChangeView()
    {
        cardLayout = (CardLayout) this.courseManager.cardPanel.getLayout();

        activeController(this.courseManager.courseController, "cardCourse","COURSE", ColorCustom.orange);
        this.courseManager.courseController.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(!currentCard.equals("cardCourse") )
                {
                    defaultController();
                    activeController(courseManager.courseController, "cardCourse","COURSE", ColorCustom.orange);
                }
            }
        });
        this.courseManager.classRoomController.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(!currentCard.equals("cardClassRoom"))
                {
                    defaultController();
                    activeController(courseManager.classRoomController, "cardClassRoom","CLASS ROOM", ColorCustom.yellow);

                }
            }
        });
        this.courseManager.adminController.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(!currentCard.equals("cardAdmin"))
                {
                    defaultController();
                    activeController(courseManager.adminController, "cardAdmin","ADMIN", ColorCustom.red);
                }
            }
        });

    }

    private void activeController(JLabel controller, String _currentCard, String title, Color color)
    {
        this.courseManager.lbTitleHeader.setText(title);
        this.courseManager.lbTitleHeader.setForeground(Color.white);
        this.courseManager.titlePanel.setBackground(color);
        controller.setHorizontalAlignment(SwingConstants.CENTER);
        controller.setBackground(color);
        controller.setOpaque(true);
        currentCard = _currentCard;
        cardLayout.show(this.courseManager.cardPanel, _currentCard);
    }

    private void defaultController()
    {
        this.courseManager.courseController.setHorizontalAlignment(SwingConstants.LEFT);
        this.courseManager.courseController.setOpaque(false);

        this.courseManager.classRoomController.setHorizontalAlignment(SwingConstants.LEFT);
        this.courseManager.classRoomController.setOpaque(false);

        this.courseManager.adminController.setHorizontalAlignment(SwingConstants.LEFT);
        this.courseManager.adminController.setOpaque(false);
    }
}
