package view.CourseBox;

import javax.swing.*;
import java.awt.*;

public class CourseBox extends JPanel
{
    public JButton btnCourseId;
    private JLabel lbCourseName;
    public CourseBox(String name, String id)
    {
        lbCourseName = new JLabel(name);
        btnCourseId = new JButton(id);

        //======== this ========
        setBackground(new Color(250, 176, 5));
        setAlignmentX(0.0F);
        setAlignmentY(0.0F);

        //---- lbCourseName ----
        lbCourseName.setText(name);
        lbCourseName.setForeground(Color.white);
        lbCourseName.setHorizontalAlignment(SwingConstants.CENTER);
        lbCourseName.setFont(new Font("JetBrains Mono", Font.BOLD, 16));

        //---- btnRegiterCourse ----
        btnCourseId.setText(id);
        btnCourseId.setFont(new Font("JetBrains Mono", Font.BOLD, 11));
        btnCourseId.setHorizontalTextPosition(SwingConstants.CENTER);
        btnCourseId.setBackground(new Color(33, 204, 121));
        btnCourseId.setForeground(Color.white);
        btnCourseId.setCursor(new Cursor(Cursor.HAND_CURSOR));

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(lbCourseName, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                        .addComponent(btnCourseId, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(lbCourseName, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                .addComponent(btnCourseId)
                                .addContainerGap())
        );

    }
}