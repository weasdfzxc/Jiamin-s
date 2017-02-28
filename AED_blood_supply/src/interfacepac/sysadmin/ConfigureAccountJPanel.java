/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacepac.sysadmin;

import business.EcoSystem;
import business.organization.Organization;
import business.useraccount.UserAccount;
import business.workqueue.WorkRequest;
import interfacepac.donorreceiver.PersonInfoJPanel;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jiamin.S <shang.j@husky.neu.edu>
 */
public class ConfigureAccountJPanel extends javax.swing.JPanel {

    /**
     * Creates new form BloodManageCenterJPanel
     */
    private JPanel displayPanel;
    private UserAccount userAccount;
    private Organization organization;
    private EcoSystem system;

    public ConfigureAccountJPanel(JPanel displayPanel, UserAccount userAccount, Organization organization, EcoSystem system) {
        
        this.displayPanel = displayPanel;
        this.userAccount = userAccount;
        this.organization = organization;
        this.system = system;
        initComponents();
    }

    public void populateTbl() {
        DefaultTableModel model = (DefaultTableModel) accountsTbl.getModel();
        model.setRowCount(0);
        for (UserAccount ua : system.getdORUserController().getUserAccountList().getUserAccountList()) {
            Object[] row = new Object[5];
            row[0] = ua;
            row[1] = ua.getFirstName();
            row[2] = ua.getLastName();
            row[3] = ua.getGender();
            row[4] = ua.getRole();
            model.addRow(row);
        }
        System.out.println(system.getdORUserController().getUserAccountList().getUserAccountList().size());

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane5 = new javax.swing.JScrollPane();
        accountsTbl = new javax.swing.JTable();
        viewDetailsBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        backBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(250, 250, 250));

        jScrollPane5.setBackground(new java.awt.Color(250, 250, 250));

        accountsTbl.setBackground(new java.awt.Color(250, 250, 250));
        accountsTbl.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        accountsTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User Name", "First Name", "Last Name", "Gender", "Role"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        accountsTbl.setGridColor(new java.awt.Color(250, 250, 250));
        accountsTbl.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                accountsTblHierarchyChanged(evt);
            }
        });
        jScrollPane5.setViewportView(accountsTbl);

        viewDetailsBtn.setBackground(new java.awt.Color(250, 250, 250));
        viewDetailsBtn.setFont(new java.awt.Font("Microsoft YaHei UI Light", 0, 14)); // NOI18N
        viewDetailsBtn.setText("View details");
        viewDetailsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewDetailsBtnActionPerformed(evt);
            }
        });

        deleteBtn.setBackground(new java.awt.Color(250, 250, 250));
        deleteBtn.setFont(new java.awt.Font("Microsoft YaHei UI Light", 0, 14)); // NOI18N
        deleteBtn.setText("Delete");
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });

        backBtn.setText("<< Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(485, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(viewDetailsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(482, 482, 482))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(backBtn)
                        .addGap(98, 98, 98))))
            .addGroup(layout.createSequentialGroup()
                .addGap(243, 243, 243)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 802, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewDetailsBtn)
                    .addComponent(deleteBtn))
                .addGap(69, 69, 69)
                .addComponent(backBtn)
                .addContainerGap(68, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void viewDetailsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewDetailsBtnActionPerformed
        // TODO add your handling code here:
        int selectedRow1 = accountsTbl.getSelectedRow();
        //int selectedRow2 = processTbl.getSelectedRow();

        UserAccount ua = null;
        if (selectedRow1 < 0) {
            JOptionPane.showMessageDialog(null, "Please select a request.");
            return;
        } else if (selectedRow1 >= 0) {
            ua = (UserAccount) accountsTbl.getValueAt(selectedRow1, 0);
        }

        if (ua != null) {

            PersonInfoJPanel panel = new PersonInfoJPanel(displayPanel, ua, organization, system);
            displayPanel.add("PerosonInfoJPanel", panel);
            CardLayout layout = (CardLayout) displayPanel.getLayout();
            layout.next(displayPanel);
        } else {
            JOptionPane.showMessageDialog(null, "The donor is not available");
        }
//        PersonInfoJPanel panel = new PersonInfoJPanel(displayPanel, userAccount, organization, system);
//        displayPanel.add("PerosonInfoJPanel", panel);
//        CardLayout layout = (CardLayout) displayPanel.getLayout();
//        layout.next(displayPanel);
    }//GEN-LAST:event_viewDetailsBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
        int selectedrow = accountsTbl.getSelectedRow();

        if (selectedrow >= 0) {
            UserAccount ua = (UserAccount) accountsTbl.getValueAt(selectedrow, 0);
            system.getdORUserController().getUserAccountList().getUserAccountList().remove(ua);
            System.out.println(system.getdORUserController().getUserAccountList().getUserAccountList().size());
            JOptionPane.showMessageDialog(null, "account has been deleted");
        } else {
            JOptionPane.showMessageDialog(null, "Please select any row");
        }
        populateTbl();
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // TODO add your handling code here:
        displayPanel.remove(this);

        CardLayout layout = (CardLayout) displayPanel.getLayout();
        layout.previous(displayPanel);
    }//GEN-LAST:event_backBtnActionPerformed

    private void accountsTblHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_accountsTblHierarchyChanged
        // TODO add your handling code here:
        populateTbl();
    }//GEN-LAST:event_accountsTblHierarchyChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable accountsTbl;
    private javax.swing.JButton backBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JButton viewDetailsBtn;
    // End of variables declaration//GEN-END:variables
}