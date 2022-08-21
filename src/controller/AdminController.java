package controller;

import library.colorCustom.ColorCustom;
import model.course.Course;
import library.fileProcess.FileProcess;
import model.user.User;
import server.Server;
import view.CourseBox.CourseBox;
import view.CourseManager.CourseManager;
import view.EditCourseDialog.EditCourseDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminController {
    private boolean serverIsOn = false;
    private CardLayout cardLayout;
    private String currentCard = "cardNewCourse";
    private TreeMap<String, Course> courseTreeMap;
    private List<CourseBox> courseBoxList;
    private String pathCourse = System.getProperty("user.dir")+"\\src\\model\\course\\courseList.dat";
    public CourseManager courseManager;
    private EditCourseDialog editCourseDialog;

    public AdminController(CourseManager courseManager)
    {
        this.courseManager = courseManager;
        this.LoadData();
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
                LoadData();
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
                courseManager.txtareaDecription.getText());
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

        courseManager.rbServerOn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(!serverIsOn)
                {
                    Thread threadServer = new Thread(){
                        @Override
                        public void run() {
                            try{
                                new Server();
                            }catch (Exception ex){
                                courseManager.rbServerOn.setSelected(false);
                                JOptionPane.showMessageDialog(courseManager.cardMainPanelAdmin, "Server Error!", "Admin:Error", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    };
                    courseManager.lbServerTitle.setForeground(ColorCustom.green);
                    threadServer.start();
                }
            }
        });

    }

    private void LoadData()
    {
        this.courseTreeMap = (TreeMap<String, Course>) Course.readCourseList(this.pathCourse);
        if (this.courseTreeMap == null) {
            JOptionPane.showMessageDialog(this.courseManager, "Can't load course data", "Admin:Error", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            this.courseBoxList = new ArrayList<>();
            courseManager.contanerCourseBoxPanel.removeAll();
            for (Course course: this.courseTreeMap.values())
            {
                CourseBox courseBox = new CourseBox(course.getName(), course.getId());
                courseBox.btnCourseId.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ShowDialogCourseDetail(e.getActionCommand());
                    }
                });
                this.courseBoxList.add(courseBox);
                courseManager.contanerCourseBoxPanel.add(courseBox, FlowLayout.LEFT);
            }
            courseManager.contanerCourseBoxPanel.revalidate();
            courseManager.contanerCourseBoxPanel.repaint();
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

    private void AddNewCourse(String id, String name, int numOfLession, String description)
    {
        if(id.equals(""))
        {
            courseManager.lbInvalidIdCourse.setText("Enter id*");
            courseManager.lbInvalidIdCourse.setVisible(true);
        }
        else if(courseTreeMap.containsKey(id))
        {
            courseManager.lbInvalidIdCourse.setText("ID existed*");
            courseManager.lbInvalidIdCourse.setVisible(true);
        }
        else if(name.equals(""))
        {
            courseManager.lbInvalidCourseName.setText("Enter name*");
            courseManager.lbInvalidCourseName.setVisible(true);
        }
        else
        {
            if(Regex("[0-9]+",id))
            {
                if(saveCourse(id, name, numOfLession, description, "newCourse"))
                {
                    JOptionPane.showMessageDialog(courseManager.cardMainPanelAdmin,"Add new Success", "Admin:Message", JOptionPane.PLAIN_MESSAGE);
                }
            }
            else
            {
                courseManager.lbInvalidIdCourse.setText("Id must be positive number*");
                courseManager.lbInvalidIdCourse.setVisible(true);
            }
        }
    }

    private void ShowDialogCourseDetail(String courseId)
    {
        if(editCourseDialog == null)
        {
            EditDialogConfig();
            LoadDataCourseDetail(courseId);
        }
        else
        {
            LoadDataCourseDetail(courseId);
            editCourseDialog.setVisible(true);
        }
    }

    private void EditDialogConfig()
    {
        editCourseDialog = new EditCourseDialog(courseManager.getOwner());

        editCourseDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(editCourseDialog,
                        "Want to save changes ?", "Confirm",
                        JOptionPane.OK_CANCEL_OPTION);

                if(choice == JOptionPane.OK_OPTION)
                {
                    saveCourse(editCourseDialog.txtCuorseId.getText(),
                            editCourseDialog.txtCourseName.getText(),
                            Integer.parseInt(editCourseDialog.spinnerLession.getValue().toString()),
                            editCourseDialog.txtareaDecription.getText(),
                            "editCourse");
                    courseManager.contanerCourseBoxPanel.removeAll();
                    LoadData();
                    editCourseDialog.dispose();
                }
                else
                {
                    editCourseDialog.dispose();
                }
            }
        });

        editCourseDialog.btnSaveEdit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                saveCourse(editCourseDialog.txtCuorseId.getText(),
                        editCourseDialog.txtCourseName.getText(),
                        Integer.parseInt(editCourseDialog.spinnerLession.getValue().toString()),
                        editCourseDialog.txtareaDecription.getText(),
                        "editCourse");
                courseManager.contanerCourseBoxPanel.removeAll();
                LoadData();
                editCourseDialog.dispose();
            }
        });

        editCourseDialog.btnDeleteCourse.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                DeleteCourse();
            }
        });

        editCourseDialog.btnCancelEdit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                editCourseDialog.dispose();
            }
        });


    }

    private void DeleteCourse()
    {
        String courseId = editCourseDialog.txtCuorseId.getText();
        if(courseTreeMap.containsKey(courseId))
        {
            int choice = JOptionPane.showConfirmDialog(editCourseDialog,
                    "Delete course with ID: "+ courseId,
                    "Confirm", JOptionPane.OK_CANCEL_OPTION);
            if(choice == JOptionPane.OK_OPTION)
            {
                courseTreeMap.remove(courseId);
                if(FileProcess.writeObject(pathCourse, courseTreeMap))
                {
                    courseManager.contanerCourseBoxPanel.removeAll();
                    LoadData();
                    editCourseDialog.dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(editCourseDialog,
                            "Delete fail", "Erorr",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(editCourseDialog,
                    "Not found course ID: "+courseId,"Erorr",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    private void LoadDataCourseDetail(String courseId)
    {
        if(this.courseTreeMap.containsKey(courseId))
        {
            for (Course course: courseTreeMap.values())
            {
                if(course.getId().equals(courseId))
                {
                    editCourseDialog.txtCuorseId.setText(courseId);
                    editCourseDialog.txtCourseName.setText(course.getName());
                    editCourseDialog.spinnerLession.setValue(course.getNumberOfLessons());
                    editCourseDialog.txtareaDecription.setText(course.getDescription());
                    break;
                }
            }
        }
        else
        {
            JOptionPane.showConfirmDialog(courseManager.cardMainPanelAdmin, "Admin: Not found course with ID: "+courseId, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean saveCourse(String id, String name, int numOfLession, String description, String type)
    {
        if(type.equals("newCourse"))
        {
            Course course = new Course(id, name, numOfLession, description);
            courseTreeMap.put(id, course);
        }
        else
        {
            courseTreeMap.get(id).setName(name);
            courseTreeMap.get(id).setNumberOfLessons(numOfLession);
            courseTreeMap.get(id).setDescription(description);
        }
        return FileProcess.writeObject(pathCourse, courseTreeMap);
    }

    private boolean Regex(String regex, String str)
    {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
}

