/*
 * Created by JFormDesigner on Wed Aug 17 10:08:18 ICT 2022
 */

package view.Jarvis;

import controller.AdminController;
import controller.ClassRoomController;
import controller.CourseController;
import library.colorCustom.ColorCustom;
import library.fileProcess.FileProcess;
import model.course.Course;
import model.data.Data;
import server.Server;
import view.CourseBox.CourseBox;
import view.EditCourseDialog.EditCourseDialog;

import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.File;
import java.net.Socket;
import java.util.List;
import java.util.TreeMap;

/**
 * @author khang
 */
public class Jarvis extends JFrame {
    private String email;
    private CardLayout cardLayoutOfMain;
    private CardLayout cardLayoutOfAdmin;
    private CardLayout cardLayoutOfClassRoom;
    public String currentCard = "cardCourse";
    private AdminController adminController;
    private CourseController courseController;
    private ClassRoomController classRoomController;
    private EditCourseDialog editCourseDialog;
    private DefaultListModel messListModel;
    private DefaultListModel userListModel;
    private Thread receiver;

    private String username;

    public Jarvis(String email) {
        initComponents();
        Config(email);
        this.setVisible(true);
    }

    private void Config(String email)
    {
        cardLayoutOfMain = (CardLayout) cardPanel.getLayout();
        setTitle("Jarvis");
        setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/iconTitle.jpg"));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        lbEmail.setText(email);
        ChangeViews();

        this.email = email;
        this.username = email.substring(0, email.indexOf("@"));
        if(Data.IsAdminRole(email))
        {
            adminController = new AdminController();
            cardLayoutOfAdmin = (CardLayout) cardMainPanelAdmin.getLayout();
            RenderCourseForAdmin();
            EventListenerAdmin();

            editCourseDialog = new EditCourseDialog(Jarvis.this.getOwner());
            editCourseDialog.setVisible(false);
            EventListenerEditDialog();
        }
        else
        {
            this.btnShowCardAdmin.setVisible(false);
        }
        courseController = new CourseController();
        RenderCourseForStudent();
        EventListenerCourse();

        cardLayoutOfClassRoom = (CardLayout) cardClassRoom.getLayout();
        classRoomController = new ClassRoomController();
        EventListenerClassRoom();
        RenderRoom();
    }

    //============================================= Jarvis ===========================================
    private void ChangeViews()
    {
        activeController(btnShowCardCourse, "cardCourse","COURSE", ColorCustom.orange);
        btnShowCardCourse.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(!currentCard.equals("cardCourse") )
                {
                    defaultController();
                    activeController(btnShowCardCourse, "cardCourse","COURSE", ColorCustom.orange);
                }
            }
        });
        btnShowCardClassRoom.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(!currentCard.equals("cardClassRoom"))
                {
                    defaultController();
                    activeController(btnShowCardClassRoom, "cardClassRoom","CLASS ROOM", ColorCustom.yellow);

                }
            }
        });
        btnShowCardAdmin.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(!currentCard.equals("cardAdmin"))
                {
                    defaultController();
                    activeController(btnShowCardAdmin, "cardAdmin","ADMIN", ColorCustom.red);
                }
            }
        });

    }

    private void activeController(JLabel controller, String _currentCard, String title, Color color)
    {
        lbTitleHeader.setText(title);
        lbTitleHeader.setForeground(Color.white);
        titlePanel.setBackground(color);
        controller.setHorizontalAlignment(SwingConstants.CENTER);
        controller.setBackground(color);
        controller.setOpaque(true);
        currentCard = _currentCard;
        cardLayoutOfMain.show(cardPanel, _currentCard);
    }

    private void defaultController()
    {
        btnShowCardCourse.setHorizontalAlignment(SwingConstants.LEFT);
        btnShowCardCourse.setOpaque(false);

        btnShowCardClassRoom.setHorizontalAlignment(SwingConstants.LEFT);
        btnShowCardClassRoom.setOpaque(false);

        btnShowCardAdmin.setHorizontalAlignment(SwingConstants.LEFT);
        btnShowCardAdmin.setOpaque(false);
    }

    //=================================================================================================

    //============================================= CourseController ==================================
    private void RenderCourseForStudent()
    {
        if(courseController.LoadData())
        {
            containerCourseList.removeAll();
            for (Course course: Data.courseList.values())
            {
                CourseBox courseBox = new CourseBox(course.getName(), course.getId());
                courseBox.btnCourseId.setVisible(false);

                if(course.getStudentList().contains(this.email))
                {
                    courseBox.btnRegisterCourse.setEnabled(false);
                    courseBox.btnRegisterCourse.setText("Registered");
                }
                courseBox.btnRegisterCourse.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if(courseController.RegisterCourse(course.getId(), email))
                        {
                            courseBox.btnRegisterCourse.setEnabled(false);
                            courseBox.btnRegisterCourse.setText("Registered");
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(Jarvis.this, "Can't register this course", "Course: error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });
                containerCourseList.add(courseBox, FlowLayout.LEFT);
            }
            containerCourseList.revalidate();
            containerCourseList.repaint();
        }
    }
    private void EventListenerCourse()
    {
        btnRefreshCourseList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                RenderCourseForStudent();
            }
        });
    }

    //============================================= ClassRoomController ==================================
    private void EventListenerClassRoom()
    {
        btnLeaveRoom.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                LeaveRoom();
            }
        });
        btnRefreshRoom.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                RenderRoom();
            }
        });
        btnSendMess.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                SendMess();
            }
        });

        txtMess.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER)
                {
                    SendMess();
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(Jarvis.this, "Logout ?", "Confirm",JOptionPane.OK_CANCEL_OPTION);
                if(choice == JOptionPane.OK_OPTION)
                {
                    ExitProgram();
                }
            }
        });
    }

    private void ExitProgram()
    {
        if(!classRoomController.ExitRoom(email))
        {
            JOptionPane.showMessageDialog(Jarvis.this, "Something wrong! can't exit T_T", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void RenderRoom()
    {
        List<Course> roomList = classRoomController.GetRoom(email);

        if(!roomList.isEmpty())
        {
            containerRoomPanel.removeAll();
            for (Course course:roomList)
            {
                CourseBox courseBox = new CourseBox(course.getName(), course.getId());
                courseBox.btnRegisterCourse.setVisible(false);
                courseBox.btnCourseId.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JoinRoom(e.getActionCommand());
                    }
                });
                containerRoomPanel.add(courseBox, FlowLayout.LEFT);

            }
            containerRoomPanel.revalidate();
            containerRoomPanel.repaint();
        }
    }
    private void JoinRoom(String roomId)
    {
        this.messListModel =  new DefaultListModel();
        listMess.setModel(this.messListModel);
        this.userListModel = new DefaultListModel<>();
        listUserInRoom.setModel(this.userListModel);

        Socket socket = classRoomController.enrollRoom(roomId, email);
        if(socket != null)
        {
            receiver = new Thread(new Receiver(socket, Jarvis.this, messListModel, userListModel, cardLayoutOfClassRoom, email));
            receiver.start();
            lbRoomId.setText("Room ID: "+roomId);
            cardLayoutOfClassRoom.show(cardClassRoom, "cardRoom");
        }
        else
        {
            JOptionPane.showMessageDialog(cardClassRoom, "Can't into  room", "Error", JOptionPane.ERROR_MESSAGE);
        }


    }

    private void SendMess()
    {
        if(classRoomController.SendMess(txtMess.getText()))
        {
            messListModel.addElement( username+": "+txtMess.getText());
            txtMess.setText("");
            AutoScroll();
        }
        else
        {
            JOptionPane.showMessageDialog(Jarvis.this, "Can't send message", "Room: error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void AutoScroll()
    {
        scrollPane5.getVerticalScrollBar().setValue(scrollPane5.getVerticalScrollBar().getMaximum());
    }

    private void LeaveRoom()
    {
        String roomId = lbRoomId.getText().substring(lbRoomId.getText().lastIndexOf(" ")+1);
        if(classRoomController.LeaveRoom(roomId))
        {
            cardLayoutOfClassRoom.show(cardClassRoom, "cardChooseRoom");
        }
        else
        {
            JOptionPane.showMessageDialog(Jarvis.this, "Can't leave this room, something wrong!",
                    "Class room: error", JOptionPane.ERROR_MESSAGE);
        }

    }
    //====================================================================================================

    //============================================= AdminController ======================================
    private void EventListenerAdmin()
    {
        lbNewCourse.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                DefaultStateController(lbCourseManager, lbNewCourse);
                ChangeStateActive(lbNewCourse, "press", "cardNewCourse");
                showCard(cardLayoutOfAdmin,cardMainPanelAdmin, "cardNewCourse");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ChangeStateActive(lbNewCourse, "mouseEnter", "cardNewCourse");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ChangeStateLeave(lbNewCourse, "cardNewCourse");
            }
        });

        lbCourseManager.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DefaultStateController(lbCourseManager, lbNewCourse);
                ChangeStateActive(lbCourseManager, "press","cardCourseManager");
                showCard(cardLayoutOfAdmin, cardMainPanelAdmin, "cardCourseManager");
                RenderCourseForAdmin();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                ChangeStateActive(lbCourseManager, "mouseEnter","cardCourseManager");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                ChangeStateLeave(lbCourseManager,"cardCourseManager");
            }
        });

        btnAddNewCourse.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                NewCourse();
            }
        });

        btnClearInforCourse.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                txtCourseName.setText("");
                txtCourseId.setText("");
                txtareaDecription.setText("");
                spinnerLession.setValue(0);
            }
        });


        txtCourseId.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lbInvalidIdCourse.setVisible(false);
            }
        });

        txtCourseName.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                lbInvalidCourseName.setVisible(false);
            }
        });

        rbServerOn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                    CreateServer();
            }
        });

        rbServerOff.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                StopServer();
            }
        });


        btnLoadDataFromDisk.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String[] choices = {"Ok","Save as","Cancel"};
                int choice = JOptionPane.showOptionDialog(null,
                        "This action will overwrite the data on the current file, you should \"save as\" your current file before loading another file from disk",
                        "Confirm", 0, JOptionPane.QUESTION_MESSAGE, null, choices,"Save as");
               if(choice != -1)
               {
                   if(choices[choice].equals("Ok"))
                   {
                       LoadDataFromDisk();
                   }
                   if(choices[choice].equals("Save as"))
                   {
                       SaveAsCourse();
                   }
               }
            }
        });

        btnSaveAs.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                SaveAsCourse();
            }
        });
    }

    private void SaveAsCourse()
    {
        JFileChooser FileSave = new JFileChooser();
        FileSave.setSelectedFile(new File("courseList.dat"));
        FileSave.setFileFilter(new FileNameExtensionFilter("DAT file (*.dat)","dat"));
        int choice = FileSave.showSaveDialog(cardMainPanelAdmin);

        if(choice == JFileChooser.APPROVE_OPTION)
        {
            if(!adminController.SaveAs(FileSave.getSelectedFile().getPath()))
            {
                JOptionPane.showMessageDialog(cardMainPanelAdmin,
                        "Save as fail", "Admin: error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void LoadDataFromDisk()
    {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("DAT file (*.dat)","dat"));
        int choice = fileChooser.showOpenDialog(cardMainPanelAdmin);

        if(choice == JFileChooser.APPROVE_OPTION)
        {
            File file = fileChooser.getSelectedFile();
            String result = adminController.LoadDataFromDisk(file);
            if(result.equals("success"))
            {
                RenderCourseForAdmin();
            }
            else if (result.equals("null"))
            {
                JOptionPane.showMessageDialog(cardMainPanelAdmin,
                        "Only accept file .dat, the file you just selected is "+result,
                        "Admin: Error", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(cardMainPanelAdmin,
                        "Only accept file .dat, the file you just selected is "+result,
                        "Admin: error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void CreateServer()
    {
        if(adminController.NewServer(Jarvis.this))
        {
            lbServerTitle.setForeground(ColorCustom.green);
        }
        else
        {
            JOptionPane.showMessageDialog(cardMainPanelAdmin, "Server Error!", "Admin: error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void StopServer()
    {
        if(adminController.ConnectKillServer())
        {
            if(adminController.KillServer())
            {
                lbServerTitle.setForeground(Color.white);
                JOptionPane.showMessageDialog(cardMainPanelAdmin, "Server has stopped", "Admin: warning", JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                JOptionPane.showMessageDialog(cardMainPanelAdmin, "Can't close Server!", "Admin: error", JOptionPane.ERROR_MESSAGE);
            }

        }
        else
        {
            JOptionPane.showMessageDialog(cardMainPanelAdmin, "Can't connect to Server to stop!!", "Admin: error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void RenderCourseForAdmin()
    {
        if (Data.courseList == null) {
            JOptionPane.showMessageDialog(Jarvis.this, "Can't load course data", "Admin: Error", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            contanerCourseBoxPanel.removeAll();
            for (Course course: Data.courseList.values())
            {
                CourseBox courseBox = new CourseBox(course.getName(), course.getId());
                courseBox.btnRegisterCourse.setVisible(false);
                courseBox.btnCourseId.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ShowDialogCourseDetail(e.getActionCommand());
                    }
                });
                contanerCourseBoxPanel.add(courseBox, FlowLayout.LEFT);
            }
            contanerCourseBoxPanel.revalidate();
            contanerCourseBoxPanel.repaint();
        }
    }

    private void NewCourse()
    {
        String result = adminController.AddNewCourse(txtCourseId.getText(),
                txtCourseName.getText(),
                Integer.parseInt(spinnerLession.getValue().toString()),
                txtareaDecription.getText());
        switch (result)
        {
            case "idBlank" -> {
                lbInvalidIdCourse.setText("Enter id*");
                lbInvalidIdCourse.setVisible(true);
            }
            case "idExisted" -> {
                lbInvalidIdCourse.setText("ID existed*");
                lbInvalidIdCourse.setVisible(true);
            }
            case "nameBlank" -> {
                lbInvalidCourseName.setText("Enter name*");
                lbInvalidCourseName.setVisible(true);
            }
            case "idMustPositive" -> {
                lbInvalidIdCourse.setText("Id must be positive number*");
                lbInvalidIdCourse.setVisible(true);
            }
            case "success" ->
                JOptionPane.showMessageDialog(cardMainPanelAdmin, "Add new Success", "Admin:Message", JOptionPane.PLAIN_MESSAGE);
        }

    }

    private void ShowDialogCourseDetail(String courseId)
    {
        Course course = adminController.getCourseDetail(courseId);
        if(course != null)
        {
            editCourseDialog.txtCourseId.setText(course.getId());
            editCourseDialog.txtCourseName.setText(course.getName());
            editCourseDialog.spinnerLession.setValue(course.getNumberOfLessons());
            editCourseDialog.txtareaDecription.setText(course.getDescription());
            editCourseDialog.setVisible(true);
        }
        else
        {
            JOptionPane.showMessageDialog(cardMainPanelAdmin, "Can't found course ID: "+courseId);
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
    //===========================================================================================================

    //=========================================== EditCourseDialog ==============================================
    private void EventListenerEditDialog()
    {
        editCourseDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                SaveEditCourse();
            }
        });

        editCourseDialog.btnSaveEdit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                SaveEditCourse();
            }
        });

        editCourseDialog.btnDeleteCourse.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                DeleteCourse(editCourseDialog.txtCourseId.getText());
            }
        });

        editCourseDialog.btnCancelEdit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                editCourseDialog.dispose();
            }
        });


    }

    private void SaveEditCourse()
    {
        int choice = JOptionPane.showConfirmDialog(editCourseDialog,
                "Want to save changes ?", "Confirm",
                JOptionPane.OK_CANCEL_OPTION);

        if(choice == JOptionPane.OK_OPTION)
        {
            boolean result = adminController.saveCourse(editCourseDialog.txtCourseId.getText(),
                    editCourseDialog.txtCourseName.getText(),
                    Integer.parseInt(editCourseDialog.spinnerLession.getValue().toString()),
                    editCourseDialog.txtareaDecription.getText(),
                    "editCourse");
            if(result)
            {
                contanerCourseBoxPanel.removeAll();
                RenderCourseForAdmin();
                editCourseDialog.dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(editCourseDialog, "Can't save this course!", "Dialog: error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            editCourseDialog.dispose();
        }
    }

    private void DeleteCourse(String courseId)
    {
            int choice = JOptionPane.showConfirmDialog(editCourseDialog,
                    "Delete course with ID: "+ courseId,
                    "Confirm", JOptionPane.OK_CANCEL_OPTION);
            if(choice == JOptionPane.OK_OPTION)
            {
                if(adminController.DeleteCourse(courseId))
                {
                    contanerCourseBoxPanel.removeAll();
                    RenderCourseForAdmin();
                    editCourseDialog.dispose();
                }
                else
                {
                    JOptionPane.showMessageDialog(editCourseDialog,
                            "Not found course ID: "+courseId,"Admin: Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
    }
    //===============================================================================




    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - khang
        mainPanel = new JPanel();
        controllerPanel = new JPanel();
        label1 = new JLabel();
        btnShowCardClassRoom = new JLabel();
        btnShowCardAdmin = new JLabel();
        btnShowCardCourse = new JLabel();
        lbEmail = new JLabel();
        label7 = new JLabel();
        titlePanel = new JPanel();
        lbTitleHeader = new JLabel();
        cardPanel = new JPanel();
        cardCourse = new JPanel();
        scrollPane2 = new JScrollPane();
        containerCourseList = new JPanel();
        btnRefreshCourseList = new JButton();
        cardClassRoom = new JPanel();
        cardChooseRoom = new JPanel();
        scrollPane4 = new JScrollPane();
        containerRoomPanel = new JPanel();
        btnRefreshRoom = new JButton();
        cardRoom = new JPanel();
        panel2 = new JPanel();
        txtMess = new JTextField();
        btnSendMess = new JButton();
        lbRoomId = new JLabel();
        scrollPane5 = new JScrollPane();
        listMess = new JList();
        scrollPane6 = new JScrollPane();
        listUserInRoom = new JList();
        label6 = new JLabel();
        btnLeaveRoom = new JButton();
        cardAdmin = new JPanel();
        toolbarAdmin = new JPanel();
        lbCourseManager = new JLabel();
        lbNewCourse = new JLabel();
        lbServerTitle = new JLabel();
        rbServerOn = new JRadioButton();
        rbServerOff = new JRadioButton();
        cardMainPanelAdmin = new JPanel();
        cardNewCourse = new JPanel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        txtCourseId = new JTextField();
        txtCourseName = new JTextField();
        scrollPane1 = new JScrollPane();
        txtareaDecription = new JTextArea();
        btnAddNewCourse = new JButton();
        btnClearInforCourse = new JButton();
        spinnerLession = new JSpinner();
        lbInvalidIdCourse = new JLabel();
        lbInvalidCourseName = new JLabel();
        cardCourseManager = new JPanel();
        scrollPane3 = new JScrollPane();
        contanerCourseBoxPanel = new JPanel();
        btnLoadDataFromDisk = new JLabel();
        btnSaveAs = new JButton();

        //======== this ========
        var contentPane = getContentPane();

        //======== mainPanel ========
        {
            mainPanel.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border. EmptyBorder
            ( 0, 0, 0, 0) , "JF\u006frmDesi\u0067ner Ev\u0061luatio\u006e", javax. swing. border. TitledBorder. CENTER, javax. swing. border
            . TitledBorder. BOTTOM, new java .awt .Font ("Dialo\u0067" ,java .awt .Font .BOLD ,12 ), java. awt
            . Color. red) ,mainPanel. getBorder( )) ); mainPanel. addPropertyChangeListener (new java. beans. PropertyChangeListener( ){ @Override public void
            propertyChange (java .beans .PropertyChangeEvent e) {if ("borde\u0072" .equals (e .getPropertyName () )) throw new RuntimeException( )
            ; }} );

            //======== controllerPanel ========
            {
                controllerPanel.setBackground(new Color(26, 27, 63));

                //---- label1 ----
                label1.setText("JARVIS 1.0");
                label1.setHorizontalAlignment(SwingConstants.CENTER);
                label1.setFont(new Font("JetBrains Mono", Font.BOLD | Font.ITALIC, 18));
                label1.setForeground(Color.white);

                //---- btnShowCardClassRoom ----
                btnShowCardClassRoom.setText("Class room");
                btnShowCardClassRoom.setHorizontalAlignment(SwingConstants.LEFT);
                btnShowCardClassRoom.setForeground(Color.white);
                btnShowCardClassRoom.setFont(new Font("JetBrains Mono", Font.BOLD, 16));
                btnShowCardClassRoom.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnShowCardClassRoom.setHorizontalTextPosition(SwingConstants.LEFT);

                //---- btnShowCardAdmin ----
                btnShowCardAdmin.setText("Admin");
                btnShowCardAdmin.setHorizontalAlignment(SwingConstants.LEFT);
                btnShowCardAdmin.setForeground(Color.white);
                btnShowCardAdmin.setFont(new Font("JetBrains Mono", Font.BOLD, 16));
                btnShowCardAdmin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnShowCardAdmin.setHorizontalTextPosition(SwingConstants.LEFT);

                //---- btnShowCardCourse ----
                btnShowCardCourse.setText("Course");
                btnShowCardCourse.setForeground(Color.white);
                btnShowCardCourse.setFont(new Font("JetBrains Mono", Font.BOLD, 16));
                btnShowCardCourse.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnShowCardCourse.setHorizontalTextPosition(SwingConstants.LEFT);
                btnShowCardCourse.setHorizontalAlignment(SwingConstants.LEFT);

                //---- lbEmail ----
                lbEmail.setText("khang@123");
                lbEmail.setForeground(new Color(5, 230, 148));

                //---- label7 ----
                label7.setText("email");
                label7.setForeground(Color.white);

                GroupLayout controllerPanelLayout = new GroupLayout(controllerPanel);
                controllerPanel.setLayout(controllerPanelLayout);
                controllerPanelLayout.setHorizontalGroup(
                    controllerPanelLayout.createParallelGroup()
                        .addGroup(controllerPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(controllerPanelLayout.createParallelGroup()
                                .addComponent(label1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(GroupLayout.Alignment.TRAILING, controllerPanelLayout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(btnShowCardCourse, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
                                .addComponent(btnShowCardClassRoom, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnShowCardAdmin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbEmail, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(controllerPanelLayout.createSequentialGroup()
                                    .addComponent(label7, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE)))
                            .addContainerGap())
                );
                controllerPanelLayout.setVerticalGroup(
                    controllerPanelLayout.createParallelGroup()
                        .addGroup(controllerPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                            .addGap(3, 3, 3)
                            .addComponent(btnShowCardCourse, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnShowCardClassRoom, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnShowCardAdmin, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 371, Short.MAX_VALUE)
                            .addComponent(label7)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lbEmail)
                            .addGap(24, 24, 24))
                );
            }

            //======== titlePanel ========
            {
                titlePanel.setBackground(new Color(26, 27, 80));

                //---- lbTitleHeader ----
                lbTitleHeader.setText("Course");
                lbTitleHeader.setHorizontalAlignment(SwingConstants.CENTER);
                lbTitleHeader.setForeground(Color.white);
                lbTitleHeader.setFont(new Font("JetBrains Mono", Font.BOLD, 18));

                GroupLayout titlePanelLayout = new GroupLayout(titlePanel);
                titlePanel.setLayout(titlePanelLayout);
                titlePanelLayout.setHorizontalGroup(
                    titlePanelLayout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, titlePanelLayout.createSequentialGroup()
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTitleHeader, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
                            .addGap(292, 292, 292))
                );
                titlePanelLayout.setVerticalGroup(
                    titlePanelLayout.createParallelGroup()
                        .addComponent(lbTitleHeader, GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                );
            }

            //======== cardPanel ========
            {
                cardPanel.setBackground(new Color(31, 32, 70));
                cardPanel.setLayout(new CardLayout());

                //======== cardCourse ========
                {
                    cardCourse.setBackground(new Color(31, 32, 70));

                    //======== scrollPane2 ========
                    {
                        scrollPane2.setMaximumSize(new Dimension(100, 50));
                        scrollPane2.setPreferredSize(new Dimension(100, 50));

                        //======== containerCourseList ========
                        {
                            containerCourseList.setBackground(new Color(31, 32, 70));
                            containerCourseList.setPreferredSize(new Dimension(100, 50));
                            containerCourseList.setMaximumSize(new Dimension(100, 50));
                            containerCourseList.setMinimumSize(new Dimension(100, 50));
                            containerCourseList.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
                            ((FlowLayout)containerCourseList.getLayout()).setAlignOnBaseline(true);
                        }
                        scrollPane2.setViewportView(containerCourseList);
                    }

                    //---- btnRefreshCourseList ----
                    btnRefreshCourseList.setText("Refresh");
                    btnRefreshCourseList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                    GroupLayout cardCourseLayout = new GroupLayout(cardCourse);
                    cardCourse.setLayout(cardCourseLayout);
                    cardCourseLayout.setHorizontalGroup(
                        cardCourseLayout.createParallelGroup()
                            .addGroup(cardCourseLayout.createSequentialGroup()
                                .addGroup(cardCourseLayout.createParallelGroup()
                                    .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 917, Short.MAX_VALUE)
                                    .addGroup(GroupLayout.Alignment.TRAILING, cardCourseLayout.createSequentialGroup()
                                        .addContainerGap(788, Short.MAX_VALUE)
                                        .addComponent(btnRefreshCourseList)
                                        .addGap(8, 8, 8)))
                                .addContainerGap())
                    );
                    cardCourseLayout.setVerticalGroup(
                        cardCourseLayout.createParallelGroup()
                            .addGroup(GroupLayout.Alignment.TRAILING, cardCourseLayout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRefreshCourseList, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane2, GroupLayout.PREFERRED_SIZE, 522, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                    );
                }
                cardPanel.add(cardCourse, "cardCourse");

                //======== cardClassRoom ========
                {
                    cardClassRoom.setBackground(new Color(31, 32, 70));
                    cardClassRoom.setMaximumSize(null);
                    cardClassRoom.setPreferredSize(new Dimension(800, 564));
                    cardClassRoom.setLayout(new CardLayout());

                    //======== cardChooseRoom ========
                    {
                        cardChooseRoom.setBackground(new Color(26, 27, 63));

                        //======== scrollPane4 ========
                        {

                            //======== containerRoomPanel ========
                            {
                                containerRoomPanel.setBackground(new Color(26, 27, 63));
                                containerRoomPanel.setPreferredSize(new Dimension(100, 50));
                                containerRoomPanel.setMaximumSize(new Dimension(100, 32767));
                                containerRoomPanel.setMinimumSize(new Dimension(100, 50));
                                containerRoomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
                                ((FlowLayout)containerRoomPanel.getLayout()).setAlignOnBaseline(true);
                            }
                            scrollPane4.setViewportView(containerRoomPanel);
                        }

                        //---- btnRefreshRoom ----
                        btnRefreshRoom.setText("Refresh");
                        btnRefreshRoom.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                        GroupLayout cardChooseRoomLayout = new GroupLayout(cardChooseRoom);
                        cardChooseRoom.setLayout(cardChooseRoomLayout);
                        cardChooseRoomLayout.setHorizontalGroup(
                            cardChooseRoomLayout.createParallelGroup()
                                .addGroup(cardChooseRoomLayout.createSequentialGroup()
                                    .addComponent(scrollPane4, GroupLayout.DEFAULT_SIZE, 917, Short.MAX_VALUE)
                                    .addContainerGap())
                                .addGroup(GroupLayout.Alignment.TRAILING, cardChooseRoomLayout.createSequentialGroup()
                                    .addGap(0, 796, Short.MAX_VALUE)
                                    .addComponent(btnRefreshRoom, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
                                    .addGap(25, 25, 25))
                        );
                        cardChooseRoomLayout.setVerticalGroup(
                            cardChooseRoomLayout.createParallelGroup()
                                .addGroup(cardChooseRoomLayout.createSequentialGroup()
                                    .addGap(12, 12, 12)
                                    .addComponent(btnRefreshRoom, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(scrollPane4, GroupLayout.PREFERRED_SIZE, 514, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap())
                        );
                    }
                    cardClassRoom.add(cardChooseRoom, "cardChooseRoom");

                    //======== cardRoom ========
                    {

                        //======== panel2 ========
                        {

                            //---- btnSendMess ----
                            btnSendMess.setText("Send");
                            btnSendMess.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                            btnSendMess.setForeground(Color.white);
                            btnSendMess.setBackground(new Color(33, 204, 121));

                            GroupLayout panel2Layout = new GroupLayout(panel2);
                            panel2.setLayout(panel2Layout);
                            panel2Layout.setHorizontalGroup(
                                panel2Layout.createParallelGroup()
                                    .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                                        .addComponent(txtMess, GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                                        .addGap(12, 12, 12)
                                        .addComponent(btnSendMess, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())
                            );
                            panel2Layout.setVerticalGroup(
                                panel2Layout.createParallelGroup()
                                    .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(txtMess, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                                        .addContainerGap())
                                    .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(btnSendMess, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE))
                            );
                        }

                        //---- lbRoomId ----
                        lbRoomId.setText("Class name");
                        lbRoomId.setHorizontalAlignment(SwingConstants.RIGHT);
                        lbRoomId.setForeground(Color.white);

                        //======== scrollPane5 ========
                        {
                            scrollPane5.setViewportView(listMess);
                        }

                        //======== scrollPane6 ========
                        {
                            scrollPane6.setViewportView(listUserInRoom);
                        }

                        //---- label6 ----
                        label6.setText("Member");

                        //---- btnLeaveRoom ----
                        btnLeaveRoom.setText("Leave");
                        btnLeaveRoom.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
                        btnLeaveRoom.setBackground(new Color(250, 176, 5));
                        btnLeaveRoom.setForeground(Color.white);
                        btnLeaveRoom.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                        GroupLayout cardRoomLayout = new GroupLayout(cardRoom);
                        cardRoom.setLayout(cardRoomLayout);
                        cardRoomLayout.setHorizontalGroup(
                            cardRoomLayout.createParallelGroup()
                                .addGroup(cardRoomLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(btnLeaveRoom)
                                    .addGap(170, 170, 170)
                                    .addComponent(lbRoomId, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 327, Short.MAX_VALUE)
                                    .addComponent(label6, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                    .addGap(109, 109, 109))
                                .addGroup(cardRoomLayout.createSequentialGroup()
                                    .addGroup(cardRoomLayout.createParallelGroup()
                                        .addGroup(cardRoomLayout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(scrollPane5, GroupLayout.PREFERRED_SIZE, 646, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(scrollPane6, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                                    .addContainerGap())
                        );
                        cardRoomLayout.setVerticalGroup(
                            cardRoomLayout.createParallelGroup()
                                .addGroup(cardRoomLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(cardRoomLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbRoomId)
                                        .addComponent(btnLeaveRoom, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label6))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(cardRoomLayout.createParallelGroup()
                                        .addGroup(cardRoomLayout.createSequentialGroup()
                                            .addComponent(scrollPane5, GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(scrollPane6, GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)))
                        );
                    }
                    cardClassRoom.add(cardRoom, "cardRoom");
                }
                cardPanel.add(cardClassRoom, "cardClassRoom");

                //======== cardAdmin ========
                {
                    cardAdmin.setBackground(new Color(31, 32, 70));

                    //======== toolbarAdmin ========
                    {
                        toolbarAdmin.setBackground(new Color(31, 32, 70));

                        //---- lbCourseManager ----
                        lbCourseManager.setText("Course Manager");
                        lbCourseManager.setHorizontalAlignment(SwingConstants.CENTER);
                        lbCourseManager.setBackground(new Color(44, 52, 72));
                        lbCourseManager.setForeground(Color.white);
                        lbCourseManager.setOpaque(true);
                        lbCourseManager.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        lbCourseManager.setFont(new Font("JetBrains Mono", Font.BOLD, 15));

                        //---- lbNewCourse ----
                        lbNewCourse.setText("New Course");
                        lbNewCourse.setHorizontalAlignment(SwingConstants.CENTER);
                        lbNewCourse.setBackground(new Color(33, 204, 121));
                        lbNewCourse.setForeground(Color.white);
                        lbNewCourse.setOpaque(true);
                        lbNewCourse.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        lbNewCourse.setFont(new Font("JetBrains Mono", Font.BOLD, 15));

                        //---- lbServerTitle ----
                        lbServerTitle.setText("Chat Server: ");
                        lbServerTitle.setForeground(Color.white);

                        //---- rbServerOn ----
                        rbServerOn.setText("On");
                        rbServerOn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        rbServerOn.setForeground(Color.white);

                        //---- rbServerOff ----
                        rbServerOff.setText("Off");
                        rbServerOff.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        rbServerOff.setForeground(Color.white);
                        rbServerOff.setSelected(true);

                        GroupLayout toolbarAdminLayout = new GroupLayout(toolbarAdmin);
                        toolbarAdmin.setLayout(toolbarAdminLayout);
                        toolbarAdminLayout.setHorizontalGroup(
                            toolbarAdminLayout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, toolbarAdminLayout.createSequentialGroup()
                                    .addGap(14, 14, 14)
                                    .addComponent(lbServerTitle)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(rbServerOn)
                                    .addGap(28, 28, 28)
                                    .addComponent(rbServerOff)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbNewCourse, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(lbCourseManager, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap())
                        );
                        toolbarAdminLayout.setVerticalGroup(
                            toolbarAdminLayout.createParallelGroup()
                                .addGroup(toolbarAdminLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(toolbarAdminLayout.createParallelGroup()
                                        .addGroup(toolbarAdminLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(lbCourseManager, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbNewCourse, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(GroupLayout.Alignment.TRAILING, toolbarAdminLayout.createSequentialGroup()
                                            .addGroup(toolbarAdminLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(lbServerTitle)
                                                .addComponent(rbServerOn)
                                                .addComponent(rbServerOff))
                                            .addContainerGap())))
                        );
                    }

                    //======== cardMainPanelAdmin ========
                    {
                        cardMainPanelAdmin.setBackground(new Color(31, 32, 70));
                        cardMainPanelAdmin.setLayout(new CardLayout());

                        //======== cardNewCourse ========
                        {
                            cardNewCourse.setBackground(new Color(31, 32, 70));
                            cardNewCourse.setPreferredSize(new Dimension(800, 5));

                            //---- label2 ----
                            label2.setText("Course id");
                            label2.setForeground(Color.white);

                            //---- label3 ----
                            label3.setText("Course name");
                            label3.setForeground(Color.white);

                            //---- label4 ----
                            label4.setText("number Of Lessons");
                            label4.setForeground(Color.white);

                            //---- label5 ----
                            label5.setText("Description");
                            label5.setForeground(Color.white);

                            //---- txtCourseId ----
                            txtCourseId.setForeground(Color.white);
                            txtCourseId.setSelectedTextColor(Color.black);
                            txtCourseId.setSelectionColor(Color.white);

                            //---- txtCourseName ----
                            txtCourseName.setForeground(Color.white);
                            txtCourseName.setSelectionColor(Color.white);
                            txtCourseName.setSelectedTextColor(Color.black);

                            //======== scrollPane1 ========
                            {

                                //---- txtareaDecription ----
                                txtareaDecription.setForeground(Color.white);
                                txtareaDecription.setSelectionColor(Color.white);
                                txtareaDecription.setSelectedTextColor(Color.black);
                                scrollPane1.setViewportView(txtareaDecription);
                            }

                            //---- btnAddNewCourse ----
                            btnAddNewCourse.setText("Add");
                            btnAddNewCourse.setForeground(Color.white);
                            btnAddNewCourse.setBackground(new Color(33, 204, 121));
                            btnAddNewCourse.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                            btnAddNewCourse.setFont(new Font("JetBrains Mono", Font.BOLD, 15));

                            //---- btnClearInforCourse ----
                            btnClearInforCourse.setText("Clear");
                            btnClearInforCourse.setBackground(new Color(250, 176, 5));
                            btnClearInforCourse.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                            btnClearInforCourse.setForeground(Color.white);
                            btnClearInforCourse.setFont(new Font("JetBrains Mono", Font.BOLD, 15));

                            //---- spinnerLession ----
                            spinnerLession.setModel(new SpinnerNumberModel(0, 0, null, 1));

                            //---- lbInvalidIdCourse ----
                            lbInvalidIdCourse.setText("text");
                            lbInvalidIdCourse.setForeground(new Color(229, 128, 131));
                            lbInvalidIdCourse.setVisible(false);

                            //---- lbInvalidCourseName ----
                            lbInvalidCourseName.setText("text");
                            lbInvalidCourseName.setForeground(new Color(229, 128, 131));
                            lbInvalidCourseName.setVisible(false);

                            GroupLayout cardNewCourseLayout = new GroupLayout(cardNewCourse);
                            cardNewCourse.setLayout(cardNewCourseLayout);
                            cardNewCourseLayout.setHorizontalGroup(
                                cardNewCourseLayout.createParallelGroup()
                                    .addGroup(cardNewCourseLayout.createSequentialGroup()
                                        .addGroup(cardNewCourseLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                            .addGroup(cardNewCourseLayout.createSequentialGroup()
                                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnClearInforCourse, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
                                                .addGap(29, 29, 29)
                                                .addComponent(btnAddNewCourse, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
                                            .addGroup(GroupLayout.Alignment.LEADING, cardNewCourseLayout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(cardNewCourseLayout.createParallelGroup()
                                                    .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 768, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(label2, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(label4, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(label5, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(cardNewCourseLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                        .addGroup(GroupLayout.Alignment.LEADING, cardNewCourseLayout.createSequentialGroup()
                                                            .addGroup(cardNewCourseLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(txtCourseName, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(spinnerLession, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE))
                                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(lbInvalidCourseName, GroupLayout.DEFAULT_SIZE, 1, Short.MAX_VALUE))
                                                        .addGroup(GroupLayout.Alignment.LEADING, cardNewCourseLayout.createSequentialGroup()
                                                            .addComponent(txtCourseId, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
                                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(lbInvalidIdCourse, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))))))
                                        .addContainerGap(137, Short.MAX_VALUE))
                            );
                            cardNewCourseLayout.setVerticalGroup(
                                cardNewCourseLayout.createParallelGroup()
                                    .addGroup(cardNewCourseLayout.createSequentialGroup()
                                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(cardNewCourseLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtCourseId, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbInvalidIdCourse))
                                        .addGap(6, 6, 6)
                                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(cardNewCourseLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtCourseName, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbInvalidCourseName))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(label4, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(spinnerLession, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(label5, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(scrollPane1, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(cardNewCourseLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(btnClearInforCourse, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(btnAddNewCourse, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(18, 18, 18))
                            );
                        }
                        cardMainPanelAdmin.add(cardNewCourse, "cardNewCourse");

                        //======== cardCourseManager ========
                        {
                            cardCourseManager.setBackground(new Color(31, 32, 70));

                            //======== scrollPane3 ========
                            {

                                //======== contanerCourseBoxPanel ========
                                {
                                    contanerCourseBoxPanel.setBackground(new Color(31, 32, 70));
                                    contanerCourseBoxPanel.setInheritsPopupMenu(true);
                                    contanerCourseBoxPanel.setPreferredSize(new Dimension(100, 50));
                                    contanerCourseBoxPanel.setMinimumSize(new Dimension(100, 50));
                                    contanerCourseBoxPanel.setMaximumSize(new Dimension(100, 3276));
                                    contanerCourseBoxPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
                                    ((FlowLayout)contanerCourseBoxPanel.getLayout()).setAlignOnBaseline(true);
                                }
                                scrollPane3.setViewportView(contanerCourseBoxPanel);
                            }

                            //---- btnLoadDataFromDisk ----
                            btnLoadDataFromDisk.setText("Load data from disk?");
                            btnLoadDataFromDisk.setForeground(new Color(0, 175, 244));
                            btnLoadDataFromDisk.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                            //---- btnSaveAs ----
                            btnSaveAs.setText("Save as");
                            btnSaveAs.setFont(new Font("JetBrains Mono", Font.BOLD, 12));
                            btnSaveAs.setBackground(new Color(253, 138, 114));
                            btnSaveAs.setForeground(Color.white);

                            GroupLayout cardCourseManagerLayout = new GroupLayout(cardCourseManager);
                            cardCourseManager.setLayout(cardCourseManagerLayout);
                            cardCourseManagerLayout.setHorizontalGroup(
                                cardCourseManagerLayout.createParallelGroup()
                                    .addGroup(cardCourseManagerLayout.createSequentialGroup()
                                        .addGap(0, 602, Short.MAX_VALUE)
                                        .addComponent(btnLoadDataFromDisk)
                                        .addGap(34, 34, 34)
                                        .addComponent(btnSaveAs)
                                        .addContainerGap())
                                    .addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 923, Short.MAX_VALUE)
                            );
                            cardCourseManagerLayout.setVerticalGroup(
                                cardCourseManagerLayout.createParallelGroup()
                                    .addGroup(GroupLayout.Alignment.TRAILING, cardCourseManagerLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(cardCourseManagerLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(btnLoadDataFromDisk)
                                            .addComponent(btnSaveAs, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 478, Short.MAX_VALUE))
                            );
                        }
                        cardMainPanelAdmin.add(cardCourseManager, "cardCourseManager");
                    }

                    GroupLayout cardAdminLayout = new GroupLayout(cardAdmin);
                    cardAdmin.setLayout(cardAdminLayout);
                    cardAdminLayout.setHorizontalGroup(
                        cardAdminLayout.createParallelGroup()
                            .addComponent(toolbarAdmin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cardMainPanelAdmin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    );
                    cardAdminLayout.setVerticalGroup(
                        cardAdminLayout.createParallelGroup()
                            .addGroup(cardAdminLayout.createSequentialGroup()
                                .addComponent(toolbarAdmin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cardMainPanelAdmin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                }
                cardPanel.add(cardAdmin, "cardAdmin");
            }

            GroupLayout mainPanelLayout = new GroupLayout(mainPanel);
            mainPanel.setLayout(mainPanelLayout);
            mainPanelLayout.setHorizontalGroup(
                mainPanelLayout.createParallelGroup()
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(controllerPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(mainPanelLayout.createParallelGroup()
                            .addComponent(titlePanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cardPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            mainPanelLayout.setVerticalGroup(
                mainPanelLayout.createParallelGroup()
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(titlePanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cardPanel, GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(controllerPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addComponent(mainPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(mainPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());

        //---- serverState ----
        var serverState = new ButtonGroup();
        serverState.add(rbServerOn);
        serverState.add(rbServerOff);
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - khang
    private JPanel mainPanel;
    private JPanel controllerPanel;
    private JLabel label1;
    public JLabel btnShowCardClassRoom;
    public JLabel btnShowCardAdmin;
    public JLabel btnShowCardCourse;
    public JLabel lbEmail;
    private JLabel label7;
    public JPanel titlePanel;
    public JLabel lbTitleHeader;
    public JPanel cardPanel;
    private JPanel cardCourse;
    private JScrollPane scrollPane2;
    public JPanel containerCourseList;
    public JButton btnRefreshCourseList;
    public JPanel cardClassRoom;
    public JPanel cardChooseRoom;
    private JScrollPane scrollPane4;
    public JPanel containerRoomPanel;
    public JButton btnRefreshRoom;
    public JPanel cardRoom;
    public JPanel panel2;
    public JTextField txtMess;
    public JButton btnSendMess;
    public JLabel lbRoomId;
    public JScrollPane scrollPane5;
    public JList listMess;
    private JScrollPane scrollPane6;
    public JList listUserInRoom;
    private JLabel label6;
    public JButton btnLeaveRoom;
    private JPanel cardAdmin;
    private JPanel toolbarAdmin;
    public JLabel lbCourseManager;
    public JLabel lbNewCourse;
    public JLabel lbServerTitle;
    public JRadioButton rbServerOn;
    public JRadioButton rbServerOff;
    public JPanel cardMainPanelAdmin;
    private JPanel cardNewCourse;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    public JTextField txtCourseId;
    public JTextField txtCourseName;
    private JScrollPane scrollPane1;
    public JTextArea txtareaDecription;
    public JButton btnAddNewCourse;
    public JButton btnClearInforCourse;
    public JSpinner spinnerLession;
    public JLabel lbInvalidIdCourse;
    public JLabel lbInvalidCourseName;
    private JPanel cardCourseManager;
    private JScrollPane scrollPane3;
    public JPanel contanerCourseBoxPanel;
    public JLabel btnLoadDataFromDisk;
    public JButton btnSaveAs;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

class Receiver implements Runnable {
    private Socket socket;
    private DataInputStream dis;
    private Jarvis jarvis;
    private DefaultListModel messListModel;
    private DefaultListModel userListModel;

    private CardLayout cardLayout;
    private String email;

    public Receiver(Socket socket, Jarvis jarvis,
                    DefaultListModel messListModel, DefaultListModel userListModel,
                    CardLayout cardLayout, String email) {
        try{
            this.socket = socket;
            this.dis = new DataInputStream(socket.getInputStream());
            this.jarvis = jarvis;
            this.messListModel = messListModel;
            this.userListModel = userListModel;
            this.cardLayout = cardLayout;
            this.email = email;
        }catch (Exception e){
            JOptionPane.showMessageDialog(jarvis, "Can't create Receiver handler");
        }

    }

    @Override
    public void run() {
        try {
            while (true) {
                String getResponseFromServer = dis.readUTF();
                String[] ResponseElement = getResponseFromServer.split(",");
                if (ResponseElement[0].equals("message"))
                {
                    String mess = ResponseElement[1];
                    NewMess(mess);
                }
                else if (ResponseElement[0].equals("intoRoom"))
                {
                    this.userListModel.addElement(ResponseElement[1]);
                }
                else if (ResponseElement[0].equals("leaveRoom"))
                {
                    LeaveRoom(this.email);
                    cardLayout.show(jarvis.cardClassRoom, "cardChooseRoom");
                    break;
                }
                else if (ResponseElement[0].equals("userLeaveRoom"))
                {
                    String email = ResponseElement[1];
                    LeaveRoom(email);
                }
                else if (ResponseElement[0].equals("serverDead"))
                {
                    JOptionPane.showMessageDialog(jarvis, "server down", "Warning", JOptionPane.WARNING_MESSAGE);
                    cardLayout.show(jarvis.cardClassRoom, "cardChooseRoom");
                    socket.close();
                    break;
                }
            }
        } catch (Exception ex) {
            this.socket = null;
            JOptionPane.showMessageDialog(jarvis.cardClassRoom, "Something wrong", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void LeaveRoom(String email) {
        for (int i = 0; i < userListModel.size(); i++) {
            if (userListModel.getElementAt(i).equals(email)) {
                userListModel.removeElementAt(i);
            }
        }
    }

    private void NewMess(String mess) {
        this.messListModel.addElement(mess);
        AutoScroll();
    }

    private void AutoScroll() {
        jarvis.scrollPane5.getVerticalScrollBar().setValue(jarvis.scrollPane5.getVerticalScrollBar().getMaximum());
    }
}