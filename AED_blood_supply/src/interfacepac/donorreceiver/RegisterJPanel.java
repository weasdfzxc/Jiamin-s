/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacepac.donorreceiver;

import business.EcoSystem;
import business.organization.BloodBank;
import business.organization.BloodManageCenter;
import business.organization.Clinic;
import business.organization.Organization;
import business.useraccount.UserAccount;
import interfacepac.*;
import business.role.*;
import com.db4o.defragment.FirstPassCommand;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Jiamin.S <shang.j@husky.neu.edu>
 */
public class RegisterJPanel extends javax.swing.JPanel {

    /**
     * Creates new form RegisterJPanel
     */
    private JPanel displayPanel;
    private UserAccount account;
    private Organization organization;
    private EcoSystem system;
    private Boolean validation = true;
    private String errorInfo;

    public RegisterJPanel(JPanel displayPanel, UserAccount userAccount, Organization organization, EcoSystem system) {
        initComponents();
        this.displayPanel = displayPanel;
        this.account = userAccount;
        this.organization = organization;
        this.system = system;

    }

    public void initialData() {
        String userName = userNameTField.getText();
        char[] passwordCharArray = passwordTField.getPassword();
        String password = String.valueOf(passwordCharArray);
        char[] repasswordCharArray = rePasswordTField.getPassword();
        String repassword = String.valueOf(repasswordCharArray);
        String firstName = firstNameTField.getText();
        String lastName = lastNameTField.getText();
        String gender = (String) genderCBox.getSelectedItem();
        String dob = dobTField.getText();
        String homePhone = homePhoneTField.getText();
        String workPhone = workPhoneTField.getText();
        String email = emailTField.getText();

        UserAccount userAccount = system.getUserAccountList().usernameCheck(userName);
        if (userAccount == null) {
            userAccount = system.getdORUserController().getUserAccountList().usernameCheck(userName);
            if (userAccount == null) {
                userAccount = system.getDistributionCenter().getUserAccountList().usernameCheck(userName);
                if (userAccount == null) {
                    here:
                    for (BloodManageCenter bloodMC : system.getBloodManageCenterList()) {
                        userAccount = bloodMC.getUserAccountList().usernameCheck(userName);
                        if (userAccount == null) {
                            userAccount = bloodMC.getDistributionCenter().getUserAccountList().usernameCheck(userName);
                            if (userAccount != null) {
                                break;
                            }
                            for (BloodManageCenter bloodMC2 : bloodMC.getNextLvBloodManageCenterList()) {
                                userAccount = bloodMC2.getUserAccountList().usernameCheck(userName);
                                if (userAccount == null) {
                                    userAccount = bloodMC2.getDistributionCenter().getUserAccountList().usernameCheck(userName);
                                    if (userAccount != null) {
                                        break here;
                                    }
                                    for (BloodBank bloodBank : bloodMC2.getBloodBankList()) {
                                        userAccount = bloodBank.getUserAccountList().usernameCheck(userName);
                                        if (userAccount == null) {
                                            userAccount = bloodBank.getDistributionCenter().getUserAccountList().usernameCheck(userName);
                                            if (userAccount != null) {
                                                break here;
                                            }
                                            for (Clinic clinic : bloodBank.getClinicList()) {
                                                for (Organization organization : clinic.getOrganizationList()) {
                                                    userAccount = organization.getUserAccountList().authenticateUser(userName, password);
                                                    if (userAccount != null) {
                                                        break here;
                                                    }
                                                }
                                            }
                                        } else {
                                            break here;
                                        }
                                    }
                                } else {
                                    break here;
                                }
                            }
                        } else {
                            break here;
                        }
                    }
                } else {
                }
            } else {
            }
        } else {
        }

        if (userAccount == null) {
            userAccount = new UserAccount();
            if (password.equals(repassword)) {
                userAccount.setPassword(password);
                userAccount.setUsername(userName);
                userAccount.setFirstName(firstName);
                userAccount.setLastName(lastName);
                userAccount.setGender(gender);
                userAccount.setDateOfBirth(dob);
                userAccount.setHomePhone(homePhone);
                userAccount.setWorkPhone(workPhone);
                userAccount.setEmail(email);
                if (organization.getType().equals(Organization.OrganizationType.BloodMngCenter.getValue())) {
                    userAccount.setRole(new FisrtManager());
                } else if (organization.getType().equals(Organization.OrganizationType.BloodBank.getValue())) {
                    userAccount.setRole(new BloodBankManager());
                } else if (organization.getType().equals(Organization.OrganizationType.Clinic.getValue())) {

                } else if (organization.getType().equals(Organization.OrganizationType.Distribution.getValue())) {
                    userAccount.setRole(new DistributionManager());
                } else if (organization.getType().equals(Organization.OrganizationType.Lab.getValue())) {
                    userAccount.setRole(new LabAssistant());
                } else if (organization.getType().equals(Organization.OrganizationType.NurseCenter.getValue())) {
                    userAccount.setRole(new Medicalstaff());
                } else if (organization.getType().equals(Organization.OrganizationType.ReceptionistService.getValue())) {
                    userAccount.setRole(new Receptionist());
                } else if (organization.getType().equals(Organization.OrganizationType.DOR.getValue())) {
                    userAccount.setRole(new DonorReceiver());
                } else {
                    JOptionPane.showMessageDialog(null, "no match role");
                }
                organization.getUserAccountList().addUserAccount(userAccount);
                JOptionPane.showMessageDialog(null, "Welcome!");
            } else {
                JOptionPane.showMessageDialog(null, "Two Passwords are different!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "username already exist");
        }

    }

    public boolean validateDate(String str) {
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("^(([0]\\d{1})|([1][012]{1})){1}\\/(([0][1-9]{1})|([12]{1}\\d{1})|([3]{1}[01]{1})){1}\\/(\\d{4}){1}$");
//        月/日/年
        java.util.regex.Matcher match = pattern.matcher(str);
        return match.matches() == false;
    }

    public boolean validatePhoneNumber(String str) {
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("\\d{3}-?\\d{7}$");
        java.util.regex.Matcher match = pattern.matcher(str);
        return match.matches() == false;
    }

    public boolean validateEmail(String str) {
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("^([.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((.[a-zA-Z0-9_-]{2,6}){1,2})$");
        java.util.regex.Matcher match = pattern.matcher(str);
        return match.matches() == false;
    }

    public void validateInput() {
        validation = true;
        errorInfo = "";
        char[] passwordCharArray = passwordTField.getPassword();
        String password = String.valueOf(passwordCharArray);
        char[] repasswordCharArray = rePasswordTField.getPassword();
        String repassword = String.valueOf(repasswordCharArray);

        if (userNameTField.getText().length() < 1 || password.length() < 1) {
            errorInfo += "Please input user name and password.\n";
            validation = false;
        }
        if (!password.equals(repassword)) {
            errorInfo += "Please input the same password.\n";
            validation = false;
        }
        if (firstNameTField.getText().length() < 1 || firstNameTField.getText().length() < 1) {
            //JOptionPane.showMessageDialog(null,"Input full name please");
            errorInfo += "Please input full name. \n";
            validation = false;
        }
        if (lastNameTField.getText().length() < 1 || lastNameTField.getText().length() < 1) {
            //JOptionPane.showMessageDialog(null,"Input full name please");
            errorInfo += "Please input full name. \n";
            validation = false;
        }
        if (validateDate(dobTField.getText()) || dobTField.getText().length() < 1) {
            errorInfo += "Please innput Correct date of birth (mm/dd/yyyy). \n";
            validation = false;
        }
        if (validatePhoneNumber(homePhoneTField.getText()) || validatePhoneNumber(workPhoneTField.getText())) {
            errorInfo += "Please innput Correct homephone/workphone. \n";
            validation = false;
        }
        if (validateEmail(emailTField.getText())) {
            errorInfo += "Please innput Correct e-mail. \n";
            validation = false;
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

        firstNameLabel1 = new javax.swing.JLabel();
        firstNameLabel2 = new javax.swing.JLabel();
        firstNameLabel3 = new javax.swing.JLabel();
        passwordTField = new javax.swing.JPasswordField();
        rePasswordTField = new javax.swing.JPasswordField();
        userNameTField = new javax.swing.JTextField();
        firstNameLabel = new javax.swing.JLabel();
        firstNameTField = new javax.swing.JTextField();
        lastNameLabel = new javax.swing.JLabel();
        lastNameTField = new javax.swing.JTextField();
        genderCBox = new javax.swing.JComboBox<>();
        genderLaber = new javax.swing.JLabel();
        dobLabel = new javax.swing.JLabel();
        dobTField = new javax.swing.JTextField();
        homePhoneTField = new javax.swing.JTextField();
        homePhoneLabel = new javax.swing.JLabel();
        workPhoneLabel = new javax.swing.JLabel();
        workPhoneTField = new javax.swing.JTextField();
        emailTField = new javax.swing.JTextField();
        EmailLabel = new javax.swing.JLabel();
        confirmBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        welcomeLabel = new javax.swing.JLabel();
        backBtn = new javax.swing.JButton();

        setBackground(new java.awt.Color(250, 250, 250));

        firstNameLabel1.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        firstNameLabel1.setText("Re-Password");
        firstNameLabel1.setPreferredSize(new java.awt.Dimension(150, 24));

        firstNameLabel2.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        firstNameLabel2.setText("Password");
        firstNameLabel2.setPreferredSize(new java.awt.Dimension(150, 24));

        firstNameLabel3.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        firstNameLabel3.setText("Username");
        firstNameLabel3.setPreferredSize(new java.awt.Dimension(150, 24));

        passwordTField.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 14)); // NOI18N

        rePasswordTField.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 14)); // NOI18N

        userNameTField.setFont(new java.awt.Font("Microsoft JhengHei UI Light", 0, 14)); // NOI18N

        firstNameLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        firstNameLabel.setText("Firstname");
        firstNameLabel.setPreferredSize(new java.awt.Dimension(150, 24));

        firstNameTField.setFont(new java.awt.Font("Microsoft YaHei Light", 0, 14)); // NOI18N

        lastNameLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        lastNameLabel.setText("Lastname");
        lastNameLabel.setPreferredSize(new java.awt.Dimension(150, 24));

        lastNameTField.setFont(new java.awt.Font("Microsoft YaHei Light", 0, 14)); // NOI18N

        genderCBox.setFont(new java.awt.Font("Microsoft YaHei UI Light", 0, 12)); // NOI18N
        genderCBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "secret", "male", "female", "other" }));

        genderLaber.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        genderLaber.setText("Gender");
        genderLaber.setPreferredSize(new java.awt.Dimension(150, 24));

        dobLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        dobLabel.setText("Date of birth");
        dobLabel.setMaximumSize(new java.awt.Dimension(150, 24));
        dobLabel.setMinimumSize(new java.awt.Dimension(150, 24));
        dobLabel.setPreferredSize(new java.awt.Dimension(150, 24));

        dobTField.setFont(new java.awt.Font("Microsoft YaHei Light", 0, 14)); // NOI18N

        homePhoneTField.setFont(new java.awt.Font("Microsoft YaHei Light", 0, 14)); // NOI18N

        homePhoneLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        homePhoneLabel.setText("Home Phone");
        homePhoneLabel.setMaximumSize(new java.awt.Dimension(150, 24));
        homePhoneLabel.setMinimumSize(new java.awt.Dimension(150, 24));
        homePhoneLabel.setPreferredSize(new java.awt.Dimension(150, 24));

        workPhoneLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        workPhoneLabel.setText("Work Phone");
        workPhoneLabel.setMaximumSize(new java.awt.Dimension(150, 24));
        workPhoneLabel.setMinimumSize(new java.awt.Dimension(150, 24));
        workPhoneLabel.setPreferredSize(new java.awt.Dimension(150, 24));

        workPhoneTField.setFont(new java.awt.Font("Microsoft YaHei Light", 0, 14)); // NOI18N

        emailTField.setFont(new java.awt.Font("Microsoft YaHei Light", 0, 14)); // NOI18N

        EmailLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        EmailLabel.setText("Email");
        EmailLabel.setMaximumSize(new java.awt.Dimension(150, 24));
        EmailLabel.setMinimumSize(new java.awt.Dimension(150, 24));
        EmailLabel.setPreferredSize(new java.awt.Dimension(150, 24));

        confirmBtn.setBackground(new java.awt.Color(250, 250, 250));
        confirmBtn.setFont(new java.awt.Font("Microsoft YaHei UI Light", 0, 14)); // NOI18N
        confirmBtn.setText("Confirm");
        confirmBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmBtnActionPerformed(evt);
            }
        });

        cancelBtn.setBackground(new java.awt.Color(250, 250, 250));
        cancelBtn.setFont(new java.awt.Font("Microsoft YaHei UI Light", 0, 14)); // NOI18N
        cancelBtn.setText("Cancel");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });

        welcomeLabel.setBackground(new java.awt.Color(250, 250, 250));
        welcomeLabel.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 48)); // NOI18N
        welcomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        welcomeLabel.setText("Register");

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
            .addGroup(layout.createSequentialGroup()
                .addGap(416, 416, 416)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(EmailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(emailTField))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(workPhoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(workPhoneTField))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(homePhoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(homePhoneTField))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(dobLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dobTField))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(firstNameLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(firstNameLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(firstNameLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordTField)
                            .addComponent(userNameTField)
                            .addComponent(rePasswordTField, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(firstNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(firstNameTField, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lastNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lastNameTField, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(genderLaber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(genderCBox, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(416, 416, 416))
            .addGroup(layout.createSequentialGroup()
                .addGap(382, 382, 382)
                .addComponent(welcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(confirmBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(510, 510, 510))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(backBtn)
                        .addGap(113, 113, 113))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(welcomeLabel)
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userNameTField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordTField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rePasswordTField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstNameTField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastNameTField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(genderLaber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(genderCBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dobLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dobTField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(homePhoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(homePhoneTField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(workPhoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(workPhoneTField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EmailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailTField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmBtn)
                    .addComponent(cancelBtn))
                .addGap(63, 63, 63)
                .addComponent(backBtn)
                .addContainerGap(83, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void confirmBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmBtnActionPerformed
        // TODO add your handling code here:
        validateInput();
        if (validation) {
            initialData();
            displayPanel.remove(this);
            CardLayout layout = (CardLayout) displayPanel.getLayout();
            layout.previous(displayPanel);
        } else {
            JOptionPane.showMessageDialog(null, errorInfo);
        }

    }//GEN-LAST:event_confirmBtnActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        // TODO add your handling code here:
        displayPanel.remove(this);
        CardLayout layout = (CardLayout) displayPanel.getLayout();
        layout.previous(displayPanel);
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void backBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backBtnActionPerformed
        // TODO add your handling code here:
        displayPanel.remove(this);
        CardLayout layout = (CardLayout) displayPanel.getLayout();
        layout.previous(displayPanel);
    }//GEN-LAST:event_backBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel EmailLabel;
    private javax.swing.JButton backBtn;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton confirmBtn;
    private javax.swing.JLabel dobLabel;
    private javax.swing.JTextField dobTField;
    private javax.swing.JTextField emailTField;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JLabel firstNameLabel1;
    private javax.swing.JLabel firstNameLabel2;
    private javax.swing.JLabel firstNameLabel3;
    private javax.swing.JTextField firstNameTField;
    private javax.swing.JComboBox<String> genderCBox;
    private javax.swing.JLabel genderLaber;
    private javax.swing.JLabel homePhoneLabel;
    private javax.swing.JTextField homePhoneTField;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JTextField lastNameTField;
    private javax.swing.JPasswordField passwordTField;
    private javax.swing.JPasswordField rePasswordTField;
    private javax.swing.JTextField userNameTField;
    private javax.swing.JLabel welcomeLabel;
    private javax.swing.JLabel workPhoneLabel;
    private javax.swing.JTextField workPhoneTField;
    // End of variables declaration//GEN-END:variables
}
