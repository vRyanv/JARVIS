/*
 * Created by JFormDesigner on Sun Aug 21 13:58:35 ICT 2022
 */

package view.EditCourseDialog;

import library.fileProcess.FileProcess;
import model.course.Course;

import java.awt.*;
import java.util.TreeMap;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.border.*;

/**
 * @author khang
 */
public class EditCourseDialog extends JDialog {

    public EditCourseDialog(Window owner) {
        super(owner);
        initComponents();
        this.pack();
        this.setTitle("Edit Course");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setVisible(true);
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - khang
        dialogPane = new JPanel();
        contentPanel = new JPanel();
        label2 = new JLabel();
        txtCourseId = new JTextField();
        label3 = new JLabel();
        txtCourseName = new JTextField();
        label4 = new JLabel();
        spinnerLession = new JSpinner();
        label5 = new JLabel();
        txtareaDecription = new JTextArea();
        lbInvalidCourseName = new JLabel();
        lbInvalidIdCourse = new JLabel();
        buttonBar = new JPanel();
        btnSaveEdit = new JButton();
        btnDeleteCourse = new JButton();
        btnCancelEdit = new JButton();

        //======== this ========
        setMinimumSize(new Dimension(900, 580));
        var contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        //======== dialogPane ========
        {
            dialogPane.setBorder(new EmptyBorder(12, 12, 12, 12));
            dialogPane.setPreferredSize(new Dimension(100, 50));
            dialogPane.setMaximumSize(new Dimension(100, 50));
            dialogPane.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border.EmptyBorder(
            0,0,0,0), "JF\u006frmDes\u0069gner \u0045valua\u0074ion",javax.swing.border.TitledBorder.CENTER,javax.swing.border.TitledBorder
            .BOTTOM,new java.awt.Font("D\u0069alog",java.awt.Font.BOLD,12),java.awt.Color.
            red),dialogPane. getBorder()));dialogPane. addPropertyChangeListener(new java.beans.PropertyChangeListener(){@Override public void propertyChange(java.
            beans.PropertyChangeEvent e){if("\u0062order".equals(e.getPropertyName()))throw new RuntimeException();}});
            dialogPane.setLayout(new BorderLayout());

            //======== contentPanel ========
            {
                contentPanel.setMaximumSize(null);
                contentPanel.setPreferredSize(new Dimension(798, 435));
                contentPanel.setBackground(new Color(31, 32, 70));

                //---- label2 ----
                label2.setText("Course id");
                label2.setForeground(Color.white);

                //---- txtCourseId ----
                txtCourseId.setForeground(Color.white);
                txtCourseId.setSelectedTextColor(Color.black);
                txtCourseId.setSelectionColor(Color.white);
                txtCourseId.setEditable(false);

                //---- label3 ----
                label3.setText("Course name");
                label3.setForeground(Color.white);

                //---- txtCourseName ----
                txtCourseName.setForeground(Color.white);
                txtCourseName.setSelectionColor(Color.white);
                txtCourseName.setSelectedTextColor(Color.black);

                //---- label4 ----
                label4.setText("number Of Lessons");
                label4.setForeground(Color.white);

                //---- spinnerLession ----
                spinnerLession.setModel(new SpinnerNumberModel(0, 0, null, 1));

                //---- label5 ----
                label5.setText("Description");
                label5.setForeground(Color.white);

                //---- txtareaDecription ----
                txtareaDecription.setForeground(Color.white);
                txtareaDecription.setSelectionColor(Color.white);
                txtareaDecription.setSelectedTextColor(Color.black);

                //---- lbInvalidCourseName ----
                lbInvalidCourseName.setText("text");
                lbInvalidCourseName.setForeground(new Color(229, 128, 131));
                lbInvalidCourseName.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
                lbInvalidCourseName.setVisible(false);

                //---- lbInvalidIdCourse ----
                lbInvalidIdCourse.setText("text");
                lbInvalidIdCourse.setForeground(new Color(229, 128, 131));
                lbInvalidIdCourse.setFont(new Font("JetBrains Mono", Font.PLAIN, 15));
                lbInvalidIdCourse.setVisible(false);

                GroupLayout contentPanelLayout = new GroupLayout(contentPanel);
                contentPanel.setLayout(contentPanelLayout);
                contentPanelLayout.setHorizontalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addGroup(contentPanelLayout.createParallelGroup()
                                .addComponent(label2, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label4, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
                                .addComponent(spinnerLession, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
                                .addComponent(label5, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addComponent(txtareaDecription, GroupLayout.PREFERRED_SIZE, 766, GroupLayout.PREFERRED_SIZE))
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addGroup(contentPanelLayout.createParallelGroup()
                                        .addComponent(txtCourseId, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label3, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtCourseName, GroupLayout.PREFERRED_SIZE, 232, GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(contentPanelLayout.createParallelGroup()
                                        .addComponent(lbInvalidIdCourse, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lbInvalidCourseName, GroupLayout.PREFERRED_SIZE, 265, GroupLayout.PREFERRED_SIZE))))
                            .addContainerGap(22, Short.MAX_VALUE))
                );
                contentPanelLayout.setVerticalGroup(
                    contentPanelLayout.createParallelGroup()
                        .addGroup(contentPanelLayout.createSequentialGroup()
                            .addComponent(label2, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                            .addGap(6, 6, 6)
                            .addGroup(contentPanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addComponent(txtCourseId, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
                                    .addGap(12, 12, 12)
                                    .addComponent(label3, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                    .addGap(6, 6, 6)
                                    .addComponent(txtCourseName, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
                                .addGroup(contentPanelLayout.createSequentialGroup()
                                    .addComponent(lbInvalidIdCourse)
                                    .addGap(66, 66, 66)
                                    .addComponent(lbInvalidCourseName)))
                            .addGap(6, 6, 6)
                            .addComponent(label4, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                            .addGap(6, 6, 6)
                            .addComponent(spinnerLession, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                            .addGap(12, 12, 12)
                            .addComponent(label5, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                            .addGap(7, 7, 7)
                            .addComponent(txtareaDecription, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
                            .addGap(0, 11, Short.MAX_VALUE))
                );
            }
            dialogPane.add(contentPanel, BorderLayout.CENTER);

            //======== buttonBar ========
            {
                buttonBar.setBorder(new EmptyBorder(12, 0, 0, 0));
                buttonBar.setLayout(new GridBagLayout());
                ((GridBagLayout)buttonBar.getLayout()).columnWidths = new int[] {0, 0, 85, 80};
                ((GridBagLayout)buttonBar.getLayout()).columnWeights = new double[] {1.0, 0.0, 0.0, 0.0};

                //---- btnSaveEdit ----
                btnSaveEdit.setText("Save");
                btnSaveEdit.setForeground(Color.white);
                btnSaveEdit.setBackground(new Color(33, 204, 121));
                buttonBar.add(btnSaveEdit, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- btnDeleteCourse ----
                btnDeleteCourse.setText("Delete");
                btnDeleteCourse.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnDeleteCourse.setBackground(new Color(229, 53, 37));
                btnDeleteCourse.setForeground(Color.white);
                buttonBar.add(btnDeleteCourse, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 5), 0, 0));

                //---- btnCancelEdit ----
                btnCancelEdit.setText("Cancel");
                btnCancelEdit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                btnCancelEdit.setBackground(new Color(250, 176, 5));
                btnCancelEdit.setForeground(Color.white);
                buttonBar.add(btnCancelEdit, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0,
                    GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                    new Insets(0, 0, 0, 0), 0, 0));
            }
            dialogPane.add(buttonBar, BorderLayout.SOUTH);
        }
        contentPane.add(dialogPane, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - khang
    private JPanel dialogPane;
    private JPanel contentPanel;
    private JLabel label2;
    public JTextField txtCourseId;
    private JLabel label3;
    public JTextField txtCourseName;
    private JLabel label4;
    public JSpinner spinnerLession;
    private JLabel label5;
    public JTextArea txtareaDecription;
    public JLabel lbInvalidCourseName;
    public JLabel lbInvalidIdCourse;
    private JPanel buttonBar;
    public JButton btnSaveEdit;
    public JButton btnDeleteCourse;
    public JButton btnCancelEdit;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
