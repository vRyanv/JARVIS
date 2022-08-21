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
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminController {
    private CardLayout cardLayout;
    private String currentCard = "cardNewCourse";
    private TreeMap<String, Course> courseTreeMap;
    private List<CourseBox> courseBoxList;
    private String pathCourse = System.getProperty("user.dir")+"\\src\\model\\course\\courseList.dat";
    public CourseManager courseManager;
    private EditCourseDialog editCourseDialog;
    private Thread threadServer;


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
                AddNewCourse(courseManager.txtCourseId.getText(),
                courseManager.txtCourseName.getText(),
                Integer.parseInt(courseManager.spinnerLession.getValue().toString()),
                courseManager.txtareaDecription.getText());
            }
        });

        courseManager.btnClearInforCourse.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                courseManager.txtCourseName.setText("");
                courseManager.txtCourseId.setText("");
                courseManager.txtareaDecription.setText("");
                courseManager.spinnerLession.setValue(0);
            }
        });


        courseManager.txtCourseId.addMouseListener(new MouseAdapter() {
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
                if(Server.serverIsOn == false)
                {
                    System.out.println("start server");
                    Server.serverIsOn = true;
                    NewServer();
                    courseManager.lbServerTitle.setForeground(ColorCustom.green);
                }
            }
        });

        courseManager.rbServerOff.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(Server.serverIsOn)
                {
                    Server.serverIsOn = false;
                    courseManager.lbServerTitle.setForeground(Color.white);
                }
            }
        });


        courseManager.btnLoadDataFromDisk.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                String[] choices = {"Ok","Save as","Cancel"};
                int choice = JOptionPane.showOptionDialog(courseManager.cardMainPanelAdmin,
                        "This action will overwrite the data on the current file, you should \"save as\" your current file before loading another file from disk",
                        "Confirm", 0, JOptionPane.QUESTION_MESSAGE, null, choices,"Save as");
                if(choices[choice].equals("Ok"))
                {
                    LoadDataFromDisk();
                }

                if(choices[choice].equals("Save as"))
                {
                    SaveAs();
                }
            }
        });

        courseManager.btnSaveAs.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                SaveAs();
            }
        });
    }

    private void NewServer()
    {
        this.threadServer = new Thread(){
            @Override
            public void run() {
                try{
                    new Server(courseManager);
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(courseManager.cardMainPanelAdmin, "Server Error!", "Admin: Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        this.threadServer.start();
    }

    private void SaveAs()
    {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setSelectedFile(new File("courseList.dat"));
        jFileChooser.setFileFilter(new FileNameExtensionFilter("DAT file (*.dat)","dat"));
        int choice = jFileChooser.showSaveDialog(courseManager.cardMainPanelAdmin);

        if(choice == JFileChooser.APPROVE_OPTION)
        {
            String path = jFileChooser.getSelectedFile().getPath();

            if(path.lastIndexOf(".") == -1 || !path.substring(path.lastIndexOf(".")).equals(".dat"))
            {
                path =  path + ".dat";
            }
            if(!FileProcess.writeObject(path, courseTreeMap))
            {
                JOptionPane.showMessageDialog(courseManager.cardMainPanelAdmin,
                        "Admin: Save as fail", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }

    }

    private void LoadDataFromDisk()
    {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("DAT file (*.dat)","dat"));
        fileChooser.showOpenDialog(courseManager.cardMainPanelAdmin);

        File file = fileChooser.getSelectedFile();

        if(file != null)
        {
            String extension = file.getName().substring(file.getName().lastIndexOf("."));

            if(extension.equals(".dat"))
            {
                courseTreeMap = (TreeMap<String, Course>) FileProcess.readObject(file);
                FileProcess.writeObject(pathCourse, courseTreeMap);
                LoadData();
            }
            else
            {
                JOptionPane.showMessageDialog(courseManager.cardMainPanelAdmin,
                        "Only accept file .dat, the file you just selected is "+extension,
                        "Admin: Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private void LoadData()
    {
        this.courseTreeMap = (TreeMap<String, Course>) Course.readCourseList(this.pathCourse);

        if (this.courseTreeMap == null) {
            JOptionPane.showMessageDialog(this.courseManager, "Can't load course data", "Admin: Error", JOptionPane.ERROR_MESSAGE);
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
                    saveCourse(editCourseDialog.txtCourseId.getText(),
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
                saveCourse(editCourseDialog.txtCourseId.getText(),
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
        String courseId = editCourseDialog.txtCourseId.getText();
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
                            "Delete fail", "Admin: Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(editCourseDialog,
                    "Not found course ID: "+courseId,"Admin: Error",
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
                    editCourseDialog.txtCourseId.setText(courseId);
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
        if(courseTreeMap == null)
        {
            int choice = JOptionPane.showConfirmDialog(courseManager.cardMainPanelAdmin,
                    "Current file not available! create new file?",
                    "Confirm", JOptionPane.OK_CANCEL_OPTION);
            if(choice == JOptionPane.OK_OPTION)
            {
                if(FileProcess.writeObject(pathCourse, new TreeMap<String, Course>()))
                {
                    JOptionPane.showMessageDialog(courseManager.cardMainPanelAdmin,
                            "Admin: system error! please contact developer");
                }
                else
                {
                    courseTreeMap = (TreeMap<String, Course>) FileProcess.readObject(pathCourse);
                }

            }
        }
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

