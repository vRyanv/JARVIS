package view.CourseBox;

import library.fileProcess.FileProcess;

import javax.swing.*;
import java.awt.*;

public class CourseBox extends JPanel
{
    public JButton btnRegisterCourse;
   public JButton btnCourseId;
    public JLabel lbCourseName;
    public CourseBox(String name, String id)
    {
        btnCourseId = new JButton();
        btnRegisterCourse = new JButton();
        lbCourseName = new JLabel();

        setBackground(new Color(250, 176, 5));
        setAlignmentX(0.0F);
        setAlignmentY(0.0F);

        btnRegisterCourse.setText("register: "+id);
        btnRegisterCourse.setCursor((Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)));
        btnRegisterCourse.setForeground(Color.white);
        btnRegisterCourse.setBackground(new Color(33, 204, 121));

        btnCourseId.setText(id);
        btnCourseId.setForeground(Color.white);
        btnCourseId.setBackground(new Color(33, 204, 121));

        lbCourseName.setText(name);
        lbCourseName.setHorizontalAlignment(SwingConstants.CENTER);
        lbCourseName.setFont(new Font("HYWenHei-85W", Font.PLAIN, 16));
        lbCourseName.setForeground(Color.white);

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(btnCourseId, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                        .addComponent(lbCourseName, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                        .addComponent(btnRegisterCourse, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(lbCourseName, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                .addComponent(btnRegisterCourse)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCourseId)
                                .addGap(12, 12, 12))
        );
    }
}

//    public JButton btnCourseId;
//    private JLabel lbCourseName;
//    public CourseBox(String name, String id)
//    {
//        lbCourseName = new JLabel(name);
//        btnCourseId = new JButton(id);
//
//        //======== this ========
//        setBackground(new Color(250, 176, 5));
//        setAlignmentX(0.0F);
//        setAlignmentY(0.0F);
//
//        //---- lbCourseName ----
//        lbCourseName.setText(name);
//        lbCourseName.setForeground(Color.white);
//        lbCourseName.setHorizontalAlignment(SwingConstants.CENTER);
//        lbCourseName.setFont(new Font("JetBrains Mono", Font.BOLD, 16));
//
//        //---- btnRegiterCourse ----
//        btnCourseId.setText(id);
//        btnCourseId.setFont(new Font("JetBrains Mono", Font.BOLD, 11));
//        btnCourseId.setHorizontalTextPosition(SwingConstants.CENTER);
//        btnCourseId.setBackground(new Color(33, 204, 121));
//        btnCourseId.setForeground(Color.white);
//        btnCourseId.setCursor(new Cursor(Cursor.HAND_CURSOR));
//
//        GroupLayout layout = new GroupLayout(this);
//        setLayout(layout);
//        layout.setHorizontalGroup(
//                layout.createParallelGroup()
//                        .addGroup(layout.createSequentialGroup()
//                                .addContainerGap()
//                                .addGroup(layout.createParallelGroup()
//                                        .addComponent(lbCourseName, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
//                                        .addComponent(btnCourseId, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
//                                .addContainerGap())
//        );
//        layout.setVerticalGroup(
//                layout.createParallelGroup()
//                        .addGroup(layout.createSequentialGroup()
//                                .addGap(25, 25, 25)
//                                .addComponent(lbCourseName, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
//                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
//                                .addComponent(btnCourseId)
//                                .addContainerGap())
//        );
//
//    }

