package controller;

import model.course.Course;
import view.CourseManager.CourseManager;
//import view.CourseManager.box;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.TreeMap;

public class CourseController {
    private String pathCuorseList = System.getProperty("user.dir")+"\\src\\model\\course\\courseList.dat";
    private CourseManager courseManager;
    private TreeMap<String, Course> courseList;
    private List<CourseBox> courseBoxList;
    public CourseController(CourseManager courseManager)
    {
        this.courseManager = courseManager;
        this.LoadData();
        this.courseManager.btnRefreshCourseList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("click");
                CourseBox courseBox = new CourseBox("adfas", "fasda");
                courseManager.containerCourseList.add(courseBox, FlowLayout.LEFT);
                courseManager.containerCourseList.revalidate();
                courseManager.containerCourseList.repaint();
            }
        });
    }

    private void LoadData()
    {
        this.courseList = (TreeMap<String, Course>) Course.readCourseList(pathCuorseList);

        for (Course course: this.courseList.values())
        {
            CourseBox courseBox = new CourseBox(course.getName(), course.getId());
            this.courseBoxList.add(courseBox);
            courseManager.containerCourseList.add(courseBox, FlowLayout.LEFT);

        }
//        contanerCourseBoxPanel.add(_box, FlowLayout.LEFT);
        courseManager.containerCourseList.revalidate();
        courseManager.containerCourseList.repaint();
    }
}
class CourseBox extends JPanel
{
    private JButton btnRegiterCourse;
    private JLabel lbCourseName;
    public CourseBox(String name, String id)
    {
        lbCourseName = new JLabel(name);
        btnRegiterCourse = new JButton(id);

        //======== this ========
        setBackground(new Color(250, 176, 5));
        setAlignmentX(0.0F);
        setAlignmentY(0.0F);

        //---- lbCourseName ----
        lbCourseName.setText("Course");
        lbCourseName.setForeground(Color.white);
        lbCourseName.setHorizontalAlignment(SwingConstants.CENTER);
        lbCourseName.setFont(new Font("JetBrains Mono", Font.BOLD, 16));

        //---- btnRegiterCourse ----
        btnRegiterCourse.setText("Study 1023");
        btnRegiterCourse.setFont(new Font("JetBrains Mono", Font.BOLD, 11));
        btnRegiterCourse.setHorizontalTextPosition(SwingConstants.CENTER);
        btnRegiterCourse.setBackground(new Color(33, 204, 121));
        btnRegiterCourse.setForeground(Color.white);

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(lbCourseName, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                        .addComponent(btnRegiterCourse, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup()
                        .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(lbCourseName, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                .addComponent(btnRegiterCourse)
                                .addContainerGap())
        );

    }
}