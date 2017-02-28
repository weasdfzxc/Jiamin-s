/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacepac.sysadmin;

import business.EcoSystem;
import business.clinic.Laboratory;
import business.clinic.NurseCenter;
import business.clinic.ReceptionistService;
import business.organization.BloodBank;
import business.organization.BloodManageCenter;
import business.organization.Clinic;
import business.organization.DistributionCenter;
import business.organization.Organization;
import business.useraccount.UserAccount;
import interfacepac.donorreceiver.RegisterJPanel;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jiamin.S <shang.j@husky.neu.edu>
 */
public class ConfigureJPanel extends javax.swing.JPanel {

    /**
     * Creates new form BloodManageCenterJPanel
     */
    private JPanel displayPanel;
    private UserAccount userAccount;
    private Organization organization;
    private EcoSystem system;

    public ConfigureJPanel(JPanel displayPanel, UserAccount userAccount, Organization organization, EcoSystem system) {
        this.displayPanel = displayPanel;
        this.userAccount = userAccount;
        this.organization = organization;
        System.out.println(organization);
        this.system = system;
        initComponents();
        initAdjust();
        organTf.setText(organization.getType());
    }

    private void initAdjust() {
        typeCBox.setVisible(false);
        if (organization.getType().equals(Organization.OrganizationType.Distribution.getValue()) || organization.getType().equals(Organization.OrganizationType.Lab.getValue()) || organization.getType().equals(Organization.OrganizationType.NurseCenter.getValue()) || organization.getType().equals(Organization.OrganizationType.ReceptionistService.getValue())) {
            int selectedrow = resumeTbl.getSelectedRow();
            if (selectedrow >= 0) {
                 UserAccount ua = (UserAccount)resumeTbl.getValueAt(selectedrow, 0);
                UserNameLabel.setText(ua.getUsername());
            }
            else{
                UserNameLabel.setText("Choose or create");
            }
        } else {
            if (organization.getUserAccountList().getUserAccountList().size() == 0) {
                createManagerBtn.setVisible(true);
                deleteManagerBtn.setVisible(false);
            } else {
                createManagerBtn.setVisible(false);
                deleteManagerBtn.setVisible(true);
                UserNameLabel.setText(organization.getUserAccountList().getUserAccountList().get(0).getUsername());
            }
        }
//        if (organization.getType() == Organization.OrganizationType.Clinic.getValue()) {
//            firstNameLabel.setVisible(false);
//            createManagerBtn.setVisible(false);
//            deleteManagerBtn.setVisible(false);
//            typeCBox.setVisible(true);
//            populateComboBox();} else
        if (organization.getType().equals(Organization.OrganizationType.Distribution.getValue()) || organization.getType().equals(Organization.OrganizationType.Lab.getValue()) || organization.getType().equals(Organization.OrganizationType.NurseCenter.getValue()) || organization.getType().equals(Organization.OrganizationType.ReceptionistService.getValue())) {
            //System.out.println("lala");
            System.out.println(Organization.OrganizationType.Distribution.getValue());
            nameTField.setVisible(false);
            addBtn.setVisible(false);
            deleteBtn.setVisible(false);
        }
        if ("system".equals(organization.getType())) {
            UserNameLabel.setVisible(false);
            createManagerBtn.setVisible(false);
            deleteManagerBtn.setVisible(false);
        }

    }

    private void populateTable() {
        DefaultTableModel model = (DefaultTableModel) resumeTbl.getModel();
        model.setRowCount(0);
        if (organization.getType().equals(Organization.OrganizationType.BloodMngCenter.getValue())) {
            BloodManageCenter bMC = (BloodManageCenter) organization;
            for (Organization organ : bMC.getNextLvBloodManageCenterList()) {
                Object[] row = new Object[2];
                row[0] = organ;
                row[1] = organ.getType();
                model.addRow(row);
            }
            for (Organization organ : bMC.getBloodBankList()) {
                Object[] row = new Object[2];
                row[0] = organ;
                row[1] = organ.getType();
                model.addRow(row);
            }
        } else if (organization.getType().equals(Organization.OrganizationType.BloodBank.getValue())) {
            BloodBank bloodBank = (BloodBank) organization;
            for (Organization organ : bloodBank.getClinicList()) {
                Object[] row = new Object[2];
                row[0] = organ;
                row[1] = organ.getType();
                model.addRow(row);
            }
        } else if (organization.getType().equals(Organization.OrganizationType.Clinic.getValue())) {
            Clinic clinic = (Clinic) organization;
            for (Organization organ : clinic.getOrganizationList()) {
                Object[] row = new Object[2];
                row[0] = organ;
                row[1] = organ.getType();
                model.addRow(row);
            }
        } else if (organization.getType().equals(Organization.OrganizationType.Distribution.getValue())) {
            DistributionCenter distributionC = (DistributionCenter) organization;
            for (UserAccount user : distributionC.getUserAccountList().getUserAccountList()) {
                Object[] row = new Object[2];
                row[0] = user;
                row[1] = user.getRole();
                model.addRow(row);
            }
        } else if (organization.getType().equals(Organization.OrganizationType.Lab.getValue())) {
            Laboratory lab = (Laboratory) organization;
            for (UserAccount user : lab.getUserAccountList().getUserAccountList()) {
                Object[] row = new Object[2];
                row[0] = user;
                row[1] = user.getRole();
                model.addRow(row);
            }
        } else if (organization.getType().equals(Organization.OrganizationType.NurseCenter.getValue())) {
            NurseCenter nurseC = (NurseCenter) organization;
            for (UserAccount user : nurseC.getUserAccountList().getUserAccountList()) {
                Object[] row = new Object[2];
                row[0] = user;
                row[1] = user.getRole();
                model.addRow(row);
            }
        } else if (organization.getType().equals(Organization.OrganizationType.ReceptionistService.getValue())) {
            ReceptionistService rs = (ReceptionistService) organization;
            for (UserAccount user : rs.getUserAccountList().getUserAccountList()) {
                Object[] row = new Object[2];
                row[0] = user;
                row[1] = user.getRole();
                model.addRow(row);
            }
        } else {
            //System.out.println(organization.getType());
            for (Organization organ : system.getBloodManageCenterList()) {
                Object[] row = new Object[2];
                row[0] = organ;
                row[1] = organ.getType();
                model.addRow(row);
            }
            if (system.getDistributionCenter() != null) {
                Object[] row = new Object[2];
                row[0] = system.getDistributionCenter();
                row[1] = system.getDistributionCenter().getType();
                model.addRow(row);
            }
        }
    }

    private void populateComboBox() {
        typeCBox.removeAllItems();
        typeCBox.addItem(Organization.OrganizationType.Lab.getValue());
        typeCBox.addItem(Organization.OrganizationType.NurseCenter.getValue());
        typeCBox.addItem(Organization.OrganizationType.ReceptionistService.getValue());
    }

    private void addOrganization() {
        if (organization.getType().equals(Organization.OrganizationType.BloodMngCenter.getValue())) {
            BloodManageCenter bMC = (BloodManageCenter) organization;
            if (bMC.isFirstLevel()) {
                BloodManageCenter bb =bMC.createBloodManageCenter(nameTField.getText());
                bb.setUpOrgan(bMC);
            } else {
                BloodBank bb =bMC.createBloodBank(nameTField.getText());
                bb.setUpOrgan(bMC);
            }
        } else if (organization.getType().equals(Organization.OrganizationType.BloodBank.getValue())) {
            BloodBank bloodBank = (BloodBank) organization;
            Clinic cc = bloodBank.createClinic(nameTField.getText());
            cc.setUpOrgan(bloodBank);
        } else if (organization.getType().equals(Organization.OrganizationType.Clinic.getValue())) {

        } else if (organization.getType().equals(Organization.OrganizationType.Distribution.getValue())) {

        } else if (organization.getType().equals(Organization.OrganizationType.Lab.getValue())) {

        } else if (organization.getType().equals(Organization.OrganizationType.NurseCenter.getValue())) {

        } else if (organization.getType().equals(Organization.OrganizationType.ReceptionistService.getValue())) {

        } else {
            BloodManageCenter bmc = system.createBloodManageCenter(nameTField.getText());
            bmc.setFirstLevel();
            bmc.setUpOrgan(system);
        }
        nameTField.setText("");
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
        resumeTbl = new javax.swing.JTable();
        addBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        nameTField = new javax.swing.JTextField();
        createManagerBtn = new javax.swing.JButton();
        deleteManagerBtn = new javax.swing.JButton();
        UserNameLabel = new javax.swing.JLabel();
        typeCBox = new javax.swing.JComboBox<>();
        backBtn = new javax.swing.JButton();
        organTf = new javax.swing.JLabel();

        setBackground(new java.awt.Color(250, 250, 250));

        jScrollPane5.setBackground(new java.awt.Color(250, 250, 250));

        resumeTbl.setBackground(new java.awt.Color(250, 250, 250));
        resumeTbl.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        resumeTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        resumeTbl.setGridColor(new java.awt.Color(250, 250, 250));
        resumeTbl.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                resumeTblHierarchyChanged(evt);
            }
        });
        resumeTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resumeTblMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(resumeTbl);

        addBtn.setBackground(new java.awt.Color(250, 250, 250));
        addBtn.setFont(new java.awt.Font("Microsoft YaHei UI Light", 0, 14)); // NOI18N
        addBtn.setText("Add");
        addBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBtnActionPerformed(evt);
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

        nameTField.setFont(new java.awt.Font("Microsoft YaHei Light", 0, 14)); // NOI18N

        createManagerBtn.setBackground(new java.awt.Color(250, 250, 250));
        createManagerBtn.setFont(new java.awt.Font("Microsoft YaHei UI Light", 0, 14)); // NOI18N
        createManagerBtn.setText("Create");
        createManagerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createManagerBtnActionPerformed(evt);
            }
        });

        deleteManagerBtn.setBackground(new java.awt.Color(250, 250, 250));
        deleteManagerBtn.setFont(new java.awt.Font("Microsoft YaHei UI Light", 0, 14)); // NOI18N
        deleteManagerBtn.setText("Delete");
        deleteManagerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteManagerBtnActionPerformed(evt);
            }
        });

        UserNameLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        UserNameLabel.setText("Manager");
        UserNameLabel.setPreferredSize(new java.awt.Dimension(150, 24));

        typeCBox.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 14)); // NOI18N
        typeCBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        backBtn.setBackground(new java.awt.Color(250, 250, 250));
        backBtn.setText("<< Back");
        backBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backBtnActionPerformed(evt);
            }
        });

        organTf.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        organTf.setText("Organization Type");
        organTf.setPreferredSize(new java.awt.Dimension(150, 24));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(401, 401, 401)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(UserNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGap(18, 18, 18)
                            .addComponent(createManagerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(deleteManagerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(nameTField, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(addBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(deleteBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(typeCBox, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)))
                .addContainerGap(404, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(backBtn)
                        .addGap(93, 93, 93))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(organTf, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(562, 562, 562))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(organTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(typeCBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addBtn)
                    .addComponent(deleteBtn)
                    .addComponent(nameTField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createManagerBtn)
                    .addComponent(deleteManagerBtn)
                    .addComponent(UserNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(backBtn)
                .addContainerGap(53, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBtnActionPerformed
        // TODO add your handling code here:
        addOrganization();
        populateTable();
    }//GEN-LAST:event_addBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
        int selectedrow = resumeTbl.getSelectedRow();

        if (selectedrow >= 0) {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure to delete?", "warning", dialogButton);
            if (dialogResult == JOptionPane.YES_OPTION) {
                Organization organ = (Organization) resumeTbl.getValueAt(selectedrow, 0);
                if (organization.getType().equals(Organization.OrganizationType.BloodMngCenter.getValue())) {
                    BloodManageCenter bMC = (BloodManageCenter) organization;
                    if (bMC.isFirstLevel()) {
                        bMC.getNextLvBloodManageCenterList().remove(organ);
                    } else {
                        bMC.getBloodBankList().remove(organ);
                    }
                } else if (organization.getType().equals(Organization.OrganizationType.BloodBank.getValue())) {
                    BloodBank bloodBank = (BloodBank) organization;
                    bloodBank.getClinicList().remove(organ);
                } else if (organization.getType().equals(Organization.OrganizationType.Clinic.getValue())) {

                } else if (organization.getType().equals(Organization.OrganizationType.Distribution.getValue())) {

                } else if (organization.getType().equals(Organization.OrganizationType.Lab.getValue())) {

                } else if (organization.getType().equals(Organization.OrganizationType.NurseCenter.getValue())) {

                } else if (organization.getType().equals(Organization.OrganizationType.ReceptionistService.getValue())) {

                } else {
                    system.getBloodManageCenterList().remove(organ);
                }
                organization.getUserAccountList().getUserAccountList().remove(userAccount);
                JOptionPane.showMessageDialog(null, "Account has been deleted");
                populateTable();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please select any row");
        }
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void createManagerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createManagerBtnActionPerformed
        // TODO add your handling code here:
        RegisterJPanel panel = new RegisterJPanel(displayPanel, userAccount, organization, system);
        displayPanel.add("ConfigureAccountJPanel", panel);
        CardLayout layout = (CardLayout) displayPanel.getLayout();
        layout.next(displayPanel);
    }//GEN-LAST:event_createManagerBtnActionPerformed

    private void deleteManagerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteManagerBtnActionPerformed
        // TODO add your handling code here:
        if (organization.getType().equals(Organization.OrganizationType.Distribution.getValue()) || organization.getType().equals(Organization.OrganizationType.Lab.getValue()) || organization.getType().equals(Organization.OrganizationType.NurseCenter.getValue()) || organization.getType().equals(Organization.OrganizationType.ReceptionistService.getValue())) {
            int selectedrow = resumeTbl.getSelectedRow();

            if (selectedrow >= 0) {
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure to delete?", "warning", dialogButton);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    UserAccount userAccount = (UserAccount) resumeTbl.getValueAt(selectedrow, 0);
                    organization.getUserAccountList().getUserAccountList().remove(userAccount);
                    JOptionPane.showMessageDialog(null, "Account has been deleted");
                    populateTable();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select any row");
            }
        } else {
            organization.getUserAccountList().getUserAccountList().clear();
        }
        initAdjust();
    }//GEN-LAST:event_deleteManagerBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // TODO add your handling code here:
        displayPanel.remove(this);
        CardLayout layout = (CardLayout) displayPanel.getLayout();
        layout.previous(displayPanel);
    }//GEN-LAST:event_backBtnActionPerformed

    private void resumeTblHierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_resumeTblHierarchyChanged
        // TODO add your handling code here:
        populateTable();
        initAdjust();
    }//GEN-LAST:event_resumeTblHierarchyChanged

    private void resumeTblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resumeTblMouseClicked
        // TODO add your handling code here:
        initAdjust();
    }//GEN-LAST:event_resumeTblMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel UserNameLabel;
    private javax.swing.JButton addBtn;
    private javax.swing.JButton backBtn;
    private javax.swing.JButton createManagerBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton deleteManagerBtn;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextField nameTField;
    private javax.swing.JLabel organTf;
    private javax.swing.JTable resumeTbl;
    private javax.swing.JComboBox<String> typeCBox;
    // End of variables declaration//GEN-END:variables
}
