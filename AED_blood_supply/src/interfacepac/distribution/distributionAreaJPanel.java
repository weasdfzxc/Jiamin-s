/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacepac.distribution;

import business.EcoSystem;
import business.blood.Blood;
import business.organization.BloodBank;
import business.organization.Organization;
import business.useraccount.UserAccount;
import business.workqueue.WorkRequest;
import interfacepac.receptionist.*;
import interfacepac.sysadmin.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jiamin.S <shang.j@husky.neu.edu>
 */
public class distributionAreaJPanel extends javax.swing.JPanel {

    /**
     * Creates new form BloodManageCenterJPanel
     */
    private JPanel displayPanel;
    private UserAccount userAccount;
    private Organization organization;
    private EcoSystem system;
    private WorkRequest workRequest;

    public distributionAreaJPanel(JPanel displayPanel, UserAccount userAccount, Organization organization, EcoSystem system) {
        this.displayPanel = displayPanel;
        this.userAccount = userAccount;
        this.organization = organization;
        this.system = system;
        initComponents();
        populateProcessTbl();
        populateOngoingTbl();
    }

    public void populateOngoingTbl() {
        DefaultTableModel model = (DefaultTableModel) ongoingTbl.getModel();
        model.setRowCount(0);

        for (WorkRequest request : organization.getWorkQueue().getWorkReqestList()) {
            if (request.getStatus().equals("Transfer") || request.getStatus().equals("For transit") || request.getStatus().equals("Waiting") || (request.getStatus().equals("Sending") && request.getReceiver().getUsername().equals(userAccount.getUsername())) ||(request.getStatus().equals("Distribute Pending") && request.getReceiver().getUsername().equals(userAccount.getUsername())) || (request.getStatus().equals("Distribute Pending") && request.getReceiver().getUsername().equals(userAccount.getUsername())) || (request.getStatus().equals("Package") && request.getReceiver().getUsername().equals(userAccount.getUsername()))) {
                Object[] row = new Object[6];
                row[0] = request;
                row[1] = request.getSender();
                row[2] = request.getReceiver() == null ? null : request.getReceiver();
                row[3] = request.getBlood().getBloodType();
                row[4] = request.getDestination();
                int donation = request.getQuantity();
                row[5] = donation;

                model.addRow(row);
            }
        }
    }

    public void populateProcessTbl() {
        DefaultTableModel model = (DefaultTableModel) finishedTbl.getModel();
        model.setRowCount(0);

        for (WorkRequest request : userAccount.getWorkQueue().getWorkReqestList()) {
            if (request.getStatus().equals("Distribute Pending") || request.getStatus().equals("For transit") || request.getStatus().equals("Waiting")||request.getStatus().equals("Sending")) {
            } else {
                Object[] row = new Object[6];
                row[0] = request;
                row[1] = request.getSender();
                row[2] = request.getReceiver() == null ? null : request.getReceiver();
                row[3] = request.getBlood().getBloodType();
                row[4] = request.getDestination();
                int donation = request.getQuantity();
                row[5] = donation;

                model.addRow(row);
            }
        }
    }

    private void reqBlood(WorkRequest req) {
        BloodBank bb = (BloodBank) req.getDestination();
        int n = 0;
        int amount = 0;
        ArrayList<Blood> tempList = new ArrayList<Blood>();
        for (int i = 1; i < bb.getBloodRepertory().size(); i++) {
            for (int j = 0; j < i; j++) {
                if (req.getSender().getVitalSignHistory().getVitalSignHistory().get(req.getSender().getVitalSignHistory().getVitalSignHistory().size()-1).getBloodtype().equals(bb.getBloodRepertory().get(j).getBloodType())) {
                    amount += bb.getBloodRepertory().get(j).getVolum();
                }
            }
            if (amount >= req.getQuantity()) {
                n = i;
                break;
            }
        }
        for (int i = 0; i < n; i++) {
            if (req.getSender().getVitalSignHistory().getVitalSignHistory().get(req.getSender().getVitalSignHistory().getVitalSignHistory().size()-1).getBloodtype().equals(bb.getBloodRepertory().get(i).getBloodType())) {
                tempList.add(bb.getBloodRepertory().get(i));
                
            }
        }
        for (Blood blood : tempList) {
            req.getUseBloodList().add(blood);
            bb.getBloodRepertory().remove(blood);
            //System.err.println(blood.getVolum());
        }
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
        ongoingTbl = new javax.swing.JTable();
        assgnBtn = new javax.swing.JButton();
        sendBtn = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        finishedTbl = new javax.swing.JTable();

        jScrollPane5.setBackground(new java.awt.Color(250, 250, 250));

        ongoingTbl.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        ongoingTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Status", "Sender", "Operator", "BloodType", "BloodBank", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ongoingTbl.setGridColor(new java.awt.Color(250, 250, 250));
        ongoingTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ongoingTblMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(ongoingTbl);

        assgnBtn.setBackground(new java.awt.Color(250, 250, 250));
        assgnBtn.setFont(new java.awt.Font("Microsoft YaHei UI Light", 0, 14)); // NOI18N
        assgnBtn.setText("assign to me");
        assgnBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assgnBtnActionPerformed(evt);
            }
        });

        sendBtn.setBackground(new java.awt.Color(250, 250, 250));
        sendBtn.setFont(new java.awt.Font("Microsoft YaHei UI Light", 0, 14)); // NOI18N
        sendBtn.setText("send");
        sendBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendBtnActionPerformed(evt);
            }
        });

        jScrollPane6.setBackground(new java.awt.Color(250, 250, 250));

        finishedTbl.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        finishedTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Status", "Sender", "Operator", "BloodType", "BloodBank", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        finishedTbl.setGridColor(new java.awt.Color(250, 250, 250));
        jScrollPane6.setViewportView(finishedTbl);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(401, 401, 401)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(assgnBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(sendBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(404, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(assgnBtn)
                    .addComponent(sendBtn))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(228, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void assgnBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assgnBtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = ongoingTbl.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a request.");
            return;
        }

        WorkRequest request = (WorkRequest) ongoingTbl.getValueAt(selectedRow, 0);
        request.setReceiver(userAccount);
        if (request.getStatus().equals("For transit")) {
            request.setStatus("Distribute Pending");
        } else if (request.getStatus().equals("Waiting")) {
            request.setStatus("Package");
        } else if (request.getStatus().equals("Transfer")) {
            request.setStatus("Sending");
        }
        userAccount.getWorkQueue().getWorkReqestList().add(request);
        JOptionPane.showMessageDialog(null, "Assigned succeed.");

        populateOngoingTbl();
        populateProcessTbl();
    }//GEN-LAST:event_assgnBtnActionPerformed

    private void sendBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendBtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = ongoingTbl.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a request.");
            return;
        }

        WorkRequest request = (WorkRequest) ongoingTbl.getValueAt(selectedRow, 0);
        if (request.getReceiver().getUsername().equals(userAccount.getUsername())) {
            if (request.getStatus().equals("Distribute Pending")) {
                BloodBank bb = (BloodBank) request.getDestination();
                bb.getBloodRepertory().add(request.getBlood());
                request.setStatus("Add into Bank");
                //clinic.getOrganizationList().get(1).getWorkQueue().getWorkReqestList().add(request);
            } else if (request.getStatus().equals("Package")) {
                this.reqBlood(request);
                request.setStatus("Used");
            } else if (request.getStatus().equals("Sending")) {
                this.reqBlood(request);
                request.setStatus("Used");
            }
        }
        populateOngoingTbl();
        populateProcessTbl();
    }//GEN-LAST:event_sendBtnActionPerformed

    private void ongoingTblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ongoingTblMouseClicked
        // TODO add your handling code here:
        int selectedRow = ongoingTbl.getSelectedRow();

        if (selectedRow >= 0) {
            WorkRequest request = (WorkRequest) ongoingTbl.getValueAt(selectedRow, 0);
            if (request.getReceiver().getUsername().equals(userAccount.getUsername())) {
                assgnBtn.setEnabled(false);
                sendBtn.setEnabled(true);
            } else {
                assgnBtn.setEnabled(true);
                sendBtn.setEnabled(false);
            }
        }
    }//GEN-LAST:event_ongoingTblMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton assgnBtn;
    private javax.swing.JTable finishedTbl;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable ongoingTbl;
    private javax.swing.JButton sendBtn;
    // End of variables declaration//GEN-END:variables
}
