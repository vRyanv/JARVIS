/*
 * Created by JFormDesigner on Wed Aug 17 10:08:18 ICT 2022
 */

package view.CourseManager;

import javax.swing.border.*;
import controller.AdminController;
import controller.ClassRoomController;
import controller.CourseManagerController;
import library.colorCustom.ColorCustom;
import library.fileProcess.FileProcess;
import model.user.User;
import view.CourseBox.CourseBox;
import view.EditCourseDialog.EditCourseDialog;

import javax.swing.*;
import javax.swing.GroupLayout;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.TreeMap;

/**
 * @author khang
 */
public class CourseManager extends JFrame {
    private String email;

    public CourseManager(String email, String role) {
        initComponents();
        new CourseManagerController(this, email, role);
        this.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - khang
        mainPanel = new JPanel();
        controllerPanel = new JPanel();
        label1 = new JLabel();
        classRoomController = new JLabel();
        adminController = new JLabel();
        courseController = new JLabel();
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
            mainPanel.setBorder ( new javax . swing. border .CompoundBorder ( new javax . swing. border .TitledBorder ( new javax . swing. border .EmptyBorder
            ( 0, 0 ,0 , 0) ,  "JF\u006frmDes\u0069gner \u0045valua\u0074ion" , javax. swing .border . TitledBorder. CENTER ,javax . swing. border
            .TitledBorder . BOTTOM, new java. awt .Font ( "D\u0069alog", java .awt . Font. BOLD ,12 ) ,java . awt
            . Color .red ) ,mainPanel. getBorder () ) ); mainPanel. addPropertyChangeListener( new java. beans .PropertyChangeListener ( ){ @Override public void
            propertyChange (java . beans. PropertyChangeEvent e) { if( "\u0062order" .equals ( e. getPropertyName () ) )throw new RuntimeException( )
            ;} } );

            //======== controllerPanel ========
            {
                controllerPanel.setBackground(new Color(26, 27, 63));

                //---- label1 ----
                label1.setText("JARVIS 1.0");
                label1.setHorizontalAlignment(SwingConstants.CENTER);
                label1.setFont(new Font("JetBrains Mono", Font.BOLD | Font.ITALIC, 18));
                label1.setForeground(Color.white);

                //---- classRoomController ----
                classRoomController.setText("Class room");
                classRoomController.setHorizontalAlignment(SwingConstants.LEFT);
                classRoomController.setForeground(Color.white);
                classRoomController.setFont(new Font("JetBrains Mono", Font.BOLD, 16));
                classRoomController.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                classRoomController.setHorizontalTextPosition(SwingConstants.LEFT);

                //---- adminController ----
                adminController.setText("Admin");
                adminController.setHorizontalAlignment(SwingConstants.LEFT);
                adminController.setForeground(Color.white);
                adminController.setFont(new Font("JetBrains Mono", Font.BOLD, 16));
                adminController.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                adminController.setHorizontalTextPosition(SwingConstants.LEFT);

                //---- courseController ----
                courseController.setText("Course");
                courseController.setForeground(Color.white);
                courseController.setFont(new Font("JetBrains Mono", Font.BOLD, 16));
                courseController.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                courseController.setHorizontalTextPosition(SwingConstants.LEFT);
                courseController.setHorizontalAlignment(SwingConstants.LEFT);

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
                                    .addComponent(courseController, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
                                .addComponent(classRoomController, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(adminController, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addContainerGap())
                );
                controllerPanelLayout.setVerticalGroup(
                    controllerPanelLayout.createParallelGroup()
                        .addGroup(controllerPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                            .addGap(3, 3, 3)
                            .addComponent(courseController, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(classRoomController, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(adminController, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

                    GroupLayout cardCourseLayout = new GroupLayout(cardCourse);
                    cardCourse.setLayout(cardCourseLayout);
                    cardCourseLayout.setHorizontalGroup(
                        cardCourseLayout.createParallelGroup()
                            .addGroup(cardCourseLayout.createSequentialGroup()
                                .addGroup(cardCourseLayout.createParallelGroup()
                                    .addComponent(scrollPane2, GroupLayout.DEFAULT_SIZE, 896, Short.MAX_VALUE)
                                    .addGroup(GroupLayout.Alignment.TRAILING, cardCourseLayout.createSequentialGroup()
                                        .addContainerGap(767, Short.MAX_VALUE)
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
                        btnRefreshRoom.setHorizontalAlignment(SwingConstants.RIGHT);

                        GroupLayout cardChooseRoomLayout = new GroupLayout(cardChooseRoom);
                        cardChooseRoom.setLayout(cardChooseRoomLayout);
                        cardChooseRoomLayout.setHorizontalGroup(
                            cardChooseRoomLayout.createParallelGroup()
                                .addGroup(cardChooseRoomLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(cardChooseRoomLayout.createParallelGroup()
                                        .addComponent(scrollPane4)
                                        .addGroup(GroupLayout.Alignment.TRAILING, cardChooseRoomLayout.createSequentialGroup()
                                            .addGap(0, 0, Short.MAX_VALUE)
                                            .addComponent(btnRefreshRoom)))
                                    .addContainerGap())
                        );
                        cardChooseRoomLayout.setVerticalGroup(
                            cardChooseRoomLayout.createParallelGroup()
                                .addGroup(cardChooseRoomLayout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addComponent(btnRefreshRoom)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(scrollPane4, GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
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

                            GroupLayout panel2Layout = new GroupLayout(panel2);
                            panel2.setLayout(panel2Layout);
                            panel2Layout.setHorizontalGroup(
                                panel2Layout.createParallelGroup()
                                    .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                                        .addComponent(txtMess, GroupLayout.PREFERRED_SIZE, 510, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                        .addComponent(btnSendMess)
                                        .addGap(20, 20, 20))
                            );
                            panel2Layout.setVerticalGroup(
                                panel2Layout.createParallelGroup()
                                    .addGroup(GroupLayout.Alignment.TRAILING, panel2Layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addGroup(panel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(btnSendMess)
                                            .addComponent(txtMess, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                                        .addContainerGap())
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

                        GroupLayout cardRoomLayout = new GroupLayout(cardRoom);
                        cardRoom.setLayout(cardRoomLayout);
                        cardRoomLayout.setHorizontalGroup(
                            cardRoomLayout.createParallelGroup()
                                .addGroup(cardRoomLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(btnLeaveRoom)
                                    .addGap(170, 170, 170)
                                    .addComponent(lbRoomId, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(label6, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                                    .addGap(86, 86, 86))
                                .addGroup(cardRoomLayout.createSequentialGroup()
                                    .addGroup(cardRoomLayout.createParallelGroup()
                                        .addGroup(cardRoomLayout.createSequentialGroup()
                                            .addContainerGap()
                                            .addComponent(scrollPane5, GroupLayout.PREFERRED_SIZE, 646, GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(scrollPane6, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE))
                                        .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 0, Short.MAX_VALUE))
                        );
                        cardRoomLayout.setVerticalGroup(
                            cardRoomLayout.createParallelGroup()
                                .addGroup(cardRoomLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(cardRoomLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(label6)
                                        .addComponent(lbRoomId)
                                        .addComponent(btnLeaveRoom, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(cardRoomLayout.createParallelGroup()
                                        .addGroup(cardRoomLayout.createSequentialGroup()
                                            .addComponent(scrollPane5, GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(cardRoomLayout.createSequentialGroup()
                                            .addComponent(scrollPane6, GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                                            .addContainerGap())))
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
                                        .addContainerGap(116, Short.MAX_VALUE))
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
                                        .addGap(0, 595, Short.MAX_VALUE)
                                        .addComponent(btnLoadDataFromDisk)
                                        .addGap(34, 34, 34)
                                        .addComponent(btnSaveAs)
                                        .addContainerGap())
                                    .addComponent(scrollPane3, GroupLayout.DEFAULT_SIZE, 902, Short.MAX_VALUE)
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
    public JLabel classRoomController;
    public JLabel adminController;
    public JLabel courseController;
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
    private JList listUserInRoom;
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
