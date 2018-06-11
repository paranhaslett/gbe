package paranhaslett.toolbox.ui;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import java.awt.*;

class About extends JDialog {

    /**
     * Create the dialog.
     */
    About() {
        setBounds(100, 100, 450, 300);
        getContentPane().setLayout(new BorderLayout());
        JPanel contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        JLabel lblNewLabel = new JLabel(Messages.getString("About.0")); //$NON-NLS-1$
        lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 16));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setIcon(new ImageIcon(About.class.getResource("/icons/app.png"))); //$NON-NLS-1$
        JLabel lblNewLabel_1 = new JLabel(Messages.getString("About.2")); //$NON-NLS-1$
        lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 12)); //$NON-NLS-1$
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);

        JScrollPane scrollPane = new JScrollPane();
        GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
        gl_contentPanel.setHorizontalGroup(
                gl_contentPanel.createParallelGroup(Alignment.TRAILING)
                        .addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
                                        .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 407, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE)
                                        .addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE))
                                .addContainerGap())
        );
        gl_contentPanel.setVerticalGroup(
                gl_contentPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPanel.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblNewLabel)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(lblNewLabel_1)
                                .addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 121, GroupLayout.PREFERRED_SIZE)
                                .addGap(28))
        );
        contentPanel.setLayout(gl_contentPanel);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK"); //$NON-NLS-1$
                okButton.setActionCommand("OK"); //$NON-NLS-1$
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
        }
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            About dialog = new About();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
