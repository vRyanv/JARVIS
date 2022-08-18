package controller;

import library.colorCustom.ColorCustom;
import model.course.Course;
import library.fileProcess.FileProcess;
import model.user.User;
import view.CourseManager.CourseManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminController {
    private CardLayout cardLayout;
    private String currentCard = "cardNewCourse";
    private TreeMap<String, Course> courseTreeMap;
    private String pathCourse = "src/model/course/course.dat";
    public CourseManager courseManager;

    public static void main(String[] args) {
        FileProcess.writeObject("src/model/course/course.dat", new TreeMap<String, Course>());
    }

    public AdminController(CourseManager courseManager)
    {
        this.loadData();
        this.cardLayout = (CardLayout) courseManager.cardMainPanelAdmin.getLayout();
        courseManager.lbNewCourse.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                DefaultStateController(courseManager.lbCourseManager, courseManager.lbNewCourse);
                ChangeStateActive(courseManager.lbNewCourse, "press", "cardNewCourse");
                showCard(cardLayout, courseManager.cardMainPanelAdmin, "cardNewCourse");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ChangeStateActive(courseManager.lbNewCourse, "mouseEnter", "cardNewCourse");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ChangeStateLeave(courseManager.lbNewCourse, "cardNewCourse");
            }
        });

        courseManager.lbCourseManager.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DefaultStateController(courseManager.lbCourseManager, courseManager.lbNewCourse);
                ChangeStateActive(courseManager.lbCourseManager, "press","cardCourseManager");
                showCard(cardLayout, courseManager.cardMainPanelAdmin, "cardCourseManager");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ChangeStateActive(courseManager.lbCourseManager, "mouseEnter","cardCourseManager");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ChangeStateLeave(courseManager.lbCourseManager,"cardCourseManager");
            }
        });

        courseManager.btnAddNewCourse.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                AddNewCourse(courseManager.txtCuorseId.getText(),
                courseManager.txtCourseName.getText(),
                Integer.parseInt(courseManager.spinnerLession.getValue().toString()),
                courseManager.txtareaDecription.getText(),
                courseManager.lbInvalidIdCourse, courseManager.lbInvalidCourseName, courseManager, courseTreeMap);
            }
        });

        courseManager.btnClearInforCourse.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                courseManager.txtCourseName.setText("");
                courseManager.txtCuorseId.setText("");
                courseManager.txtareaDecription.setText("");
                courseManager.spinnerLession.setValue(0);
            }
        });


        courseManager.txtCuorseId.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                courseManager.lbInvalidIdCourse.setVisible(false);
            }
        });

        courseManager.txtCourseName.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                courseManager.lbInvalidCourseName.setVisible(false);
            }
        });
    }

    private void loadData()
    {
        this.courseTreeMap = (TreeMap<String, Course>) FileProcess.readObject(this.pathCourse);
        if(this.courseTreeMap == null)
        {
            System.out.println("load data");
            FileProcess.writeObject(pathCourse, new TreeMap<String, Course>());
            this.courseTreeMap = (TreeMap<String, Course>) FileProcess.readObject(this.pathCourse);
        }
    }

    private void ChangeStateActive(JLabel controller, String type, String _currentCard)
    {

        if(type.equals("press"))
        {
            currentCard = _currentCard;
            controller.setBackground(ColorCustom.green);
        }
        else
        {
            controller.setBackground(ColorCustom.lightGreen);
        }

    }

    private void ChangeStateLeave(JLabel controller, String _currentCard)
    {
        if(!currentCard.equals(_currentCard))
        {
            controller.setBackground(ColorCustom.grayBlack);
        }
        else
        {
            controller.setBackground(ColorCustom.green);
        }
    }

    private void DefaultStateController(JLabel... controllers)
    {
        for (JLabel controller: controllers)
        {
            controller.setBackground(ColorCustom.grayBlack);
        }
    }

    private void showCard(CardLayout cardLayout, JPanel cardMainAdmin, String cardName)
    {
        cardLayout.show(cardMainAdmin, cardName);
    }

    private void AddNewCourse(String id, String name, int numOfLession, String description, JLabel lbInvalidIdCourse, JLabel lbInvalidCourseName, CourseManager courseManager, TreeMap<String, Course> courseTreeMap)
    {
        if(id.equals(""))
        {
            lbInvalidIdCourse.setText("Enter id*");
            lbInvalidIdCourse.setVisible(true);
        }
        else if(name.equals(""))
        {
            lbInvalidCourseName.setText("Enter name*");
            lbInvalidCourseName.setVisible(true);
        }
        else
        {
            if(Regex("[0-9]+",id))
            {
                if(saveCourse(id, name, numOfLession, description, courseTreeMap))
                {
                    JOptionPane.showMessageDialog(courseManager.cardMainPanelAdmin,"Add new Success", "Message", JOptionPane.PLAIN_MESSAGE);
                }
            }
            else
            {
                lbInvalidIdCourse.setText("Id must be positive number*");
                lbInvalidIdCourse.setVisible(true);
            }
        }
    }

    private boolean saveCourse(String id, String name, int numOfLession, String description, TreeMap<String, Course> courseTreeMap)
    {
        Course course = new Course(id, name, numOfLession, description);
        courseTreeMap.put(id, course);
        return FileProcess.writeObject(pathCourse, courseTreeMap);
    }

    private boolean Regex(String regex, String str)
    {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}

