/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacepac;

import business.EcoSystem;
import business.db4outil.DB4OUtil;
import business.organization.BloodBank;
import business.organization.BloodManageCenter;
import business.organization.Clinic;
import business.organization.DistributionCenter;
import business.organization.Organization;
import business.useraccount.UserAccount;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Jiamin.S <shang.j@husky.neu.edu>
 */
public class CertificationJPanel extends javax.swing.JPanel {

    /**
     * Creates new form CertificationJPanel
     */
    private EcoSystem system;
    private JPanel displayJPanel;
    private boolean isDOR;

    public CertificationJPanel(JPanel displayJPanel, boolean isDOR, EcoSystem system) {
        initComponents();
        this.system = system;
        this.displayJPanel = displayJPanel;
        this.isDOR = isDOR;
        populateDisplayPanel();
    }

    private void populateDisplayPanel() {

        LoadSPanel RegJPanel = new LoadSPanel(displayJPanel, system);
        RegJPanel.welcomeIfLabel.setVisible(isDOR);
        RegJPanel.registerBtn.setVisible(isDOR);
        logoutBtn.setText("Back");
        displayPanel.add("welcome", RegJPanel);
        CardLayout layout = (CardLayout) displayPanel.getLayout();
        layout.next(displayPanel);
    }

    private void loginCheck() {
        String userName = usernameTField.getText();
        char[] passwordCharArray = passwordTField.getPassword();
        String password = String.valueOf(passwordCharArray);
        UserAccount userAccount = system.getUserAccountList().authenticateUser(userName, password);

        Organization inOrganization = null;

        if (userAccount == null) {
            userAccount = system.getdORUserController().getUserAccountList().authenticateUser(userName, password);
            if (userAccount == null) {
                userAccount = system.getDistributionCenter().getUserAccountList().authenticateUser(userName, password);
                if (userAccount == null) {
                    here:
                    for (BloodManageCenter bloodMC : system.getBloodManageCenterList()) {
                        userAccount = bloodMC.getUserAccountList().authenticateUser(userName, password);
                        if (userAccount == null) {
                            userAccount = bloodMC.getDistributionCenter().getUserAccountList().authenticateUser(userName, password);
                            if (userAccount != null) {
                                inOrganization = bloodMC.getDistributionCenter();
                                break;
                            }
                            for (BloodManageCenter bloodMC2 : bloodMC.getNextLvBloodManageCenterList()) {
                                userAccount = bloodMC2.getUserAccountList().authenticateUser(userName, password);
                                if (userAccount == null) {
                                    userAccount = bloodMC2.getDistributionCenter().getUserAccountList().authenticateUser(userName, password);
                                    if (userAccount != null) {
                                        inOrganization = bloodMC2.getDistributionCenter();
                                        break here;
                                    }
                                    for (BloodBank bloodBank : bloodMC2.getBloodBankList()) {
                                        userAccount = bloodBank.getUserAccountList().authenticateUser(userName, password);
                                        if (userAccount == null) {
                                            userAccount = bloodBank.getDistributionCenter().getUserAccountList().authenticateUser(userName, password);
                                            if (userAccount != null) {
                                                inOrganization = bloodBank.getDistributionCenter();
                                                break here;
                                            }
                                            for (Clinic clinic : bloodBank.getClinicList()) {
                                                    for (Organization organization : clinic.getOrganizationList()) {
                                                        userAccount = organization.getUserAccountList().authenticateUser(userName, password);
                                                        if (userAccount != null) {
                                                            inOrganization = organization;
                                                            break here;
                                                        }
                                                    }
                                            }
                                        } else {
                                            inOrganization = bloodBank;
                                            break here;
                                        }
                                    }
                                } else {
                                    inOrganization = bloodMC2;
                                    break here;
                                }
                            }
                        } else {
                            inOrganization = bloodMC;
                            break here;
                        }
                    }
                } else {
                    inOrganization = system.getDistributionCenter();
                }
            } else {
                inOrganization = system.getdORUserController();
            }
        } else {
            inOrganization = system;
        }

        if (userAccount == null) {
            JOptionPane.showMessageDialog(null, "Invalid credentials");
            return;
        } else {
            CardLayout layout = (CardLayout) displayPanel.getLayout();
            displayPanel.add("workArea", userAccount.getRole().createWorkArea(displayPanel, userAccount, inOrganization, system));
            layout.next(displayPanel);
            loginBtn.setVisible(false);
            logoutBtn.setVisible(true);
            logoutBtn.setText("Logout");
            usernameTField.setVisible(false);
            passwordTField.setVisible(false);
            passwordLabel.setText(userAccount.toString());
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

        splitPanel = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        welcomeLabel1 = new javax.swing.JLabel();
        usernameTField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        passwordTField = new javax.swing.JPasswordField();
        loginBtn = new javax.swing.JButton();
        logoutBtn = new javax.swing.JButton();
        displayPanel = new javax.swing.JPanel();

        splitPanel.setDividerLocation(50);
        splitPanel.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jPanel1.setBackground(new java.awt.Color(250, 250, 250));

        welcomeLabel1.setBackground(new java.awt.Color(250, 250, 250));
        welcomeLabel1.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        welcomeLabel1.setText("Username:");

        usernameTField.setFont(new java.awt.Font("Verdana", 0, 22)); // NOI18N

        passwordLabel.setBackground(new java.awt.Color(250, 250, 250));
        passwordLabel.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        passwordLabel.setText("Password:");

        passwordTField.setFont(new java.awt.Font("Verdana", 0, 22)); // NOI18N

        loginBtn.setBackground(new java.awt.Color(250, 250, 250));
        loginBtn.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        loginBtn.setText("Login");
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });

        logoutBtn.setBackground(new java.awt.Color(250, 250, 250));
        logoutBtn.setFont(new java.awt.Font("Verdana", 0, 20)); // NOI18N
        logoutBtn.setText("Logout");
        logoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(237, Short.MAX_VALUE)
                .addComponent(welcomeLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameTField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(passwordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordTField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(loginBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(logoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(welcomeLabel1)
                    .addComponent(usernameTField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordTField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loginBtn)
                    .addComponent(logoutBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        splitPanel.setTopComponent(jPanel1);

        displayPanel.setBackground(new java.awt.Color(250, 250, 250));
        displayPanel.setLayout(new java.awt.CardLayout());
        splitPanel.setRightComponent(displayPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(splitPanel)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(splitPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 897, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        // TODO add your handling code here:
        loginCheck();

    }//GEN-LAST:event_loginBtnActionPerformed

    private void logoutBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutBtnActionPerformed
        // TODO add your handling code here:
        displayJPanel.remove(this);
        CardLayout layout = (CardLayout) displayJPanel.getLayout();
        layout.previous(displayJPanel);
        DB4OUtil dB4OUtil = DB4OUtil.getInstance();
        dB4OUtil.storeSystem(system);
    }//GEN-LAST:event_logoutBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel displayPanel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton loginBtn;
    private javax.swing.JButton logoutBtn;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField passwordTField;
    private javax.swing.JSplitPane splitPanel;
    private javax.swing.JTextField usernameTField;
    private javax.swing.JLabel welcomeLabel1;
    // End of variables declaration//GEN-END:variables
}
