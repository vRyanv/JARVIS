/*
 * Created by JFormDesigner on Wed Aug 17 10:08:18 ICT 2022
 */

package view.CourseManager;

import javax.swing.border.*;
import com.formdev.flatlaf.FlatDarculaLaf;
import controller.AdminController;
import controller.ClassRoomController;
import controller.CourseController;

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
    public String currentCard = "cardCourse";;
    public CourseManager() {
        initComponents();
        CourseConfig();
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
        Color orange = new Color(253, 138, 114);
        Color pink = new Color(249, 118, 176);
        Color yellow = new Color(250, 176, 5);
        this.courseController.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(!currentCard.equals("cardCourse") )
                {
                    activeController(courseController, "cardCourse","COURSE", orange);
                    new CourseController(_courseManager);
                }
            }
        });
        this.classRoomController.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(!currentCard.equals("cardClassRoom"))
                {
                    activeController(classRoomController, "cardClassRoom","CLASS ROOM", yellow);
                    new ClassRoomController(_courseManager);
                }
            }
        });
        this.adminController.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(!currentCard.equals("cardAdmin"))
                {
                    activeController(adminController, "cardAdmin","ADMIN", pink);
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

        //======== this ========
        var contentPane = getContentPane();

        //======== mainPanel ========
        {
            mainPanel.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.
            EmptyBorder(0,0,0,0), "JFor\u006dDesi\u0067ner \u0045valu\u0061tion",javax.swing.border.TitledBorder.CENTER,javax.swing
            .border.TitledBorder.BOTTOM,new java.awt.Font("Dia\u006cog",java.awt.Font.BOLD,12),
            java.awt.Color.red),mainPanel. getBorder()));mainPanel. addPropertyChangeListener(new java.beans.PropertyChangeListener()
            {@Override public void propertyChange(java.beans.PropertyChangeEvent e){if("bord\u0065r".equals(e.getPropertyName()))
            throw new RuntimeException();}});

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
                                .addComponent(label1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(controllerPanelLayout.createSequentialGroup()
                                    .addComponent(adminController, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(GroupLayout.Alignment.TRAILING, controllerPanelLayout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(classRoomController, GroupLayout.PREFERRED_SIZE, 207, GroupLayout.PREFERRED_SIZE))
                                .addComponent(courseController, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                lbTitleHeader.setFont(new Font("JetBrains Mono", Font.BOLD, 16));

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
                            .addGap(0, 559, Short.MAX_VALUE)
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
                            .addGap(0, 559, Short.MAX_VALUE)
                    );
                }
                cardPanel.add(cardClassRoom, "cardClassRoom");

                //======== cardAdmin ========
                {
                    cardAdmin.setBackground(new Color(31, 32, 70));

                    GroupLayout cardAdminLayout = new GroupLayout(cardAdmin);
                    cardAdmin.setLayout(cardAdminLayout);
                    cardAdminLayout.setHorizontalGroup(
                        cardAdminLayout.createParallelGroup()
                            .addGap(0, 800, Short.MAX_VALUE)
                    );
                    cardAdminLayout.setVerticalGroup(
                        cardAdminLayout.createParallelGroup()
                            .addGap(0, 559, Short.MAX_VALUE)
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
                        .addComponent(cardPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
