/*
 * Created by JFormDesigner on Wed Aug 17 10:08:18 ICT 2022
 */

package view.CourseManager;

import javax.swing.border.*;
import com.formdev.flatlaf.FlatDarculaLaf;
import controller.AdminController;
import controller.ClassRoomController;
import controller.CourseController;
import library.colorCustom.ColorCustom;
import library.fileProcess.FileProcess;

import javax.swing.*;
import javax.swing.GroupLayout;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * @author khang
 */
public class CourseManager extends JFrame {
    private CardLayout cardLayout;
    public String currentCard = "cardCourse";
    public CourseManager() {
        initComponents();
        CourseConfig();
        this.button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("click");
                box _box = new box();
                contanerCourseBoxPanel.add(_box, FlowLayout.LEFT);
                contanerCourseBoxPanel.revalidate();
                contanerCourseBoxPanel.repaint();
            }
        });
    }


    private void CourseConfig()
    {
        addController();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Jarvis");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("src/images/iconTitle.jpg"));
        this.setVisible(true);
    }

    private void addController()
    {
        CourseManager _courseManager = this;
        cardLayout = (CardLayout) this.cardPanel.getLayout();

        activeController(courseController, "cardCourse","COURSE", ColorCustom.orange);
        this.courseController.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(!currentCard.equals("cardCourse") )
                {
                    defaultController();
                    activeController(courseController, "cardCourse","COURSE", ColorCustom.orange);
                    new CourseController(_courseManager);
                }
            }
        });
        this.classRoomController.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(!currentCard.equals("cardClassRoom"))
                {
                    defaultController();
                    activeController(classRoomController, "cardClassRoom","CLASS ROOM", ColorCustom.yellow);
                    new ClassRoomController(_courseManager);
                }
            }
        });
        this.adminController.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(!currentCard.equals("cardAdmin"))
                {
                    defaultController();
                    activeController(adminController, "cardAdmin","ADMIN", ColorCustom.red);
                    new AdminController(_courseManager);
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
        cardLayout.show(cardPanel, _currentCard);
    }

    private void defaultController()
    {
        this.courseController.setHorizontalAlignment(SwingConstants.LEFT);
        this.courseController.setOpaque(false);

        this.classRoomController.setHorizontalAlignment(SwingConstants.LEFT);
        this.classRoomController.setOpaque(false);

        this.adminController.setHorizontalAlignment(SwingConstants.LEFT);
        this.adminController.setOpaque(false);
    }

    public static void main(String[] args) {
        try{
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        }catch (Exception ex){

        }
        new CourseManager();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - khang
        mainPanel = new JPanel();
        controllerPanel = new JPanel();
        label1 = new JLabel();
        courseController = new JLabel();
        classRoomController = new JLabel();
        adminController = new JLabel();
        titlePanel = new JPanel();
        lbTitleHeader = new JLabel();
        cardPanel = new JPanel();
        cardCourse = new JPanel();
        cardClassRoom = new JPanel();
        cardAdmin = new JPanel();
        toolbarAdmin = new JPanel();
        lbCourseManager = new JLabel();
        lbNewCourse = new JLabel();
        button1 = new JButton();
        cardMainPanelAdmin = new JPanel();
        cardNewCourse = new JPanel();
        label2 = new JLabel();
        label3 = new JLabel();
        label4 = new JLabel();
        label5 = new JLabel();
        txtCuorseId = new JTextField();
        txtCourseName = new JTextField();
        scrollPane1 = new JScrollPane();
        txtareaDecription = new JTextArea();
        lbInvalidIdCourse = new JLabel();
        lbInvalidCourseName = new JLabel();
        btnAddNewCourse = new JButton();
        btnClearInforCourse = new JButton();
        spinnerLession = new JSpinner();
        cardCourseManager = new JPanel();
        contanerCourseBoxPanel = new JPanel();

        //======== this ========
        var contentPane = getContentPane();

        //======== mainPanel ========
        {
            mainPanel.setBorder (new javax. swing. border. CompoundBorder( new javax .swing .border .TitledBorder (new javax. swing. border.
            EmptyBorder( 0, 0, 0, 0) , "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax. swing. border. TitledBorder. CENTER, javax. swing
            . border. TitledBorder. BOTTOM, new java .awt .Font ("Dia\u006cog" ,java .awt .Font .BOLD ,12 ),
            java. awt. Color. red) ,mainPanel. getBorder( )) ); mainPanel. addPropertyChangeListener (new java. beans. PropertyChangeListener( )
            { @Override public void propertyChange (java .beans .PropertyChangeEvent e) {if ("bord\u0065r" .equals (e .getPropertyName () ))
            throw new RuntimeException( ); }} );

            //======== controllerPanel ========
            {
                controllerPanel.setBackground(new Color(26, 27, 63));

                //---- label1 ----
                label1.setText("JARVIS 1.0");
                label1.setHorizontalAlignment(SwingConstants.CENTER);
                label1.setFont(new Font("JetBrains Mono", Font.BOLD | Font.ITALIC, 18));
                label1.setForeground(Color.white);

                //---- courseController ----
                courseController.setText("Course");
                courseController.setForeground(Color.white);
                courseController.setFont(new Font("JetBrains Mono", Font.BOLD, 16));
                courseController.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                courseController.setHorizontalTextPosition(SwingConstants.LEFT);
                courseController.setHorizontalAlignment(SwingConstants.LEFT);

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

                GroupLayout controllerPanelLayout = new GroupLayout(controllerPanel);
                controllerPanel.setLayout(controllerPanelLayout);
                controllerPanelLayout.setHorizontalGroup(
                    controllerPanelLayout.createParallelGroup()
                        .addGroup(controllerPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(controllerPanelLayout.createParallelGroup()
                                .addComponent(label1, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                                .addComponent(adminController, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                                .addComponent(classRoomController, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
                                .addComponent(courseController, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE))
                            .addContainerGap())
                );
                controllerPanelLayout.setVerticalGroup(
                    controllerPanelLayout.createParallelGroup()
                        .addGroup(controllerPanelLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(label1, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
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

                    GroupLayout cardCourseLayout = new GroupLayout(cardCourse);
                    cardCourse.setLayout(cardCourseLayout);
                    cardCourseLayout.setHorizontalGroup(
                        cardCourseLayout.createParallelGroup()
                            .addGap(0, 800, Short.MAX_VALUE)
                    );
                    cardCourseLayout.setVerticalGroup(
                        cardCourseLayout.createParallelGroup()
                            .addGap(0, 572, Short.MAX_VALUE)
                    );
                }
                cardPanel.add(cardCourse, "cardCourse");

                //======== cardClassRoom ========
                {
                    cardClassRoom.setBackground(new Color(31, 32, 70));

                    GroupLayout cardClassRoomLayout = new GroupLayout(cardClassRoom);
                    cardClassRoom.setLayout(cardClassRoomLayout);
                    cardClassRoomLayout.setHorizontalGroup(
                        cardClassRoomLayout.createParallelGroup()
                            .addGap(0, 800, Short.MAX_VALUE)
                    );
                    cardClassRoomLayout.setVerticalGroup(
                        cardClassRoomLayout.createParallelGroup()
                            .addGap(0, 572, Short.MAX_VALUE)
                    );
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

                        //---- button1 ----
                        button1.setText("text");

                        GroupLayout toolbarAdminLayout = new GroupLayout(toolbarAdmin);
                        toolbarAdmin.setLayout(toolbarAdminLayout);
                        toolbarAdminLayout.setHorizontalGroup(
                            toolbarAdminLayout.createParallelGroup()
                                .addGroup(GroupLayout.Alignment.TRAILING, toolbarAdminLayout.createSequentialGroup()
                                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(button1)
                                    .addGap(131, 131, 131)
                                    .addComponent(lbNewCourse, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(lbCourseManager, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
                                    .addContainerGap())
                        );
                        toolbarAdminLayout.setVerticalGroup(
                            toolbarAdminLayout.createParallelGroup()
                                .addGroup(toolbarAdminLayout.createSequentialGroup()
                                    .addContainerGap()
                                    .addGroup(toolbarAdminLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbCourseManager, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbNewCourse, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button1)))
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

                            //---- txtCuorseId ----
                            txtCuorseId.setForeground(Color.white);
                            txtCuorseId.setSelectedTextColor(Color.black);
                            txtCuorseId.setSelectionColor(Color.white);

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

                            //---- lbInvalidIdCourse ----
                            lbInvalidIdCourse.setText("text");
                            lbInvalidIdCourse.setForeground(new Color(229, 128, 131));
                            lbInvalidIdCourse.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
                            lbInvalidIdCourse.setVisible(false);

                            //---- lbInvalidCourseName ----
                            lbInvalidCourseName.setText("text");
                            lbInvalidCourseName.setForeground(new Color(229, 128, 131));
                            lbInvalidCourseName.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
                            lbInvalidCourseName.setVisible(false);

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
                                                    .addGroup(cardNewCourseLayout.createSequentialGroup()
                                                        .addComponent(txtCuorseId, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(lbInvalidIdCourse, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(label4, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(label5, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(cardNewCourseLayout.createSequentialGroup()
                                                        .addGroup(cardNewCourseLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                            .addComponent(txtCourseName, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                                                            .addComponent(spinnerLession, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE))
                                                        .addGap(18, 18, 18)
                                                        .addComponent(lbInvalidCourseName, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)))))
                                        .addContainerGap(14, Short.MAX_VALUE))
                            );
                            cardNewCourseLayout.setVerticalGroup(
                                cardNewCourseLayout.createParallelGroup()
                                    .addGroup(cardNewCourseLayout.createSequentialGroup()
                                        .addComponent(label2, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(cardNewCourseLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtCuorseId, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                            .addComponent(lbInvalidIdCourse))
                                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
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

                            //======== contanerCourseBoxPanel ========
                            {
                                contanerCourseBoxPanel.setBackground(new Color(31, 32, 70));
                                contanerCourseBoxPanel.setMaximumSize(new Dimension(32779, 32779));
                                contanerCourseBoxPanel.setInheritsPopupMenu(true);
                                contanerCourseBoxPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 1));
                            }

                            GroupLayout cardCourseManagerLayout = new GroupLayout(cardCourseManager);
                            cardCourseManager.setLayout(cardCourseManagerLayout);
                            cardCourseManagerLayout.setHorizontalGroup(
                                cardCourseManagerLayout.createParallelGroup()
                                    .addGroup(cardCourseManagerLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(contanerCourseBoxPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap())
                            );
                            cardCourseManagerLayout.setVerticalGroup(
                                cardCourseManagerLayout.createParallelGroup()
                                    .addGroup(cardCourseManagerLayout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(contanerCourseBoxPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addContainerGap())
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
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - khang
    private JPanel mainPanel;
    private JPanel controllerPanel;
    private JLabel label1;
    private JLabel courseController;
    private JLabel classRoomController;
    private JLabel adminController;
    private JPanel titlePanel;
    private JLabel lbTitleHeader;
    private JPanel cardPanel;
    private JPanel cardCourse;
    private JPanel cardClassRoom;
    private JPanel cardAdmin;
    private JPanel toolbarAdmin;
    public JLabel lbCourseManager;
    public JLabel lbNewCourse;
    private JButton button1;
    public JPanel cardMainPanelAdmin;
    private JPanel cardNewCourse;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    public JTextField txtCuorseId;
    public JTextField txtCourseName;
    private JScrollPane scrollPane1;
    public JTextArea txtareaDecription;
    public JLabel lbInvalidIdCourse;
    public JLabel lbInvalidCourseName;
    public JButton btnAddNewCourse;
    public JButton btnClearInforCourse;
    public JSpinner spinnerLession;
    private JPanel cardCourseManager;
    private JPanel contanerCourseBoxPanel;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}

class box extends JPanel
{
    private JLabel label1;
    public box()
    {
        label1 = new JLabel();
        setBackground(new Color(51, 255, 0));
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGap(0, 160, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGap(0, 180, Short.MAX_VALUE)
        );

        label1.setText("Course");
        label1.setForeground(Color.red);

        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(59, Short.MAX_VALUE)
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
                                .addGap(49, 49, 49))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(label1, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(64, Short.MAX_VALUE))
        );
    }
}