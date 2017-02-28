/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacepac.donorreceiver;

import business.EcoSystem;
import business.VitalSign.VitalSign;
import business.organization.Organization;
import business.useraccount.UserAccount;
import business.workqueue.DonorRequest;
import business.workqueue.WorkRequest;
import java.awt.CardLayout;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Jiamin.S <shang.j@husky.neu.edu>
 */
public class PersonInfoJPanel extends javax.swing.JPanel {

    /**
     * Creates new form PersonInfoJPanel
     */
    private JPanel displayPanel;
    private UserAccount userAccount;
    private Organization organization;
    private EcoSystem system;
    private Boolean validation = true;
    private String errorInfo;

    public PersonInfoJPanel(JPanel displayPanel, UserAccount userAccount, Organization organization, EcoSystem system) {
        this.displayPanel = displayPanel;
        this.userAccount = userAccount;
        this.organization = organization;
        this.system = system;
        initComponents();
        populateVSTable();
        populateTableV();
        populatePersonInfo();
    }

    public void populateVSTable() {
        DefaultTableModel model = (DefaultTableModel) vitalSignTbl.getModel();
        model.setRowCount(0);
        TableRowSorter sorter = new TableRowSorter(model);
        vitalSignTbl.setRowSorter(sorter);
        for (VitalSign vitalSign : userAccount.getVitalSignHistory().getVitalSignHistory()) {
            vitalSign.isHealthy();
            Object row[] = new Object[2];
            row[0] = vitalSign.getVitalSignID();
            row[1] = vitalSign;
            model.addRow(row);
        }
    }

    private void populateTableV() {
        DefaultTableModel dtm = (DefaultTableModel) viewBHTable.getModel();
        dtm.setRowCount(0);
        TableRowSorter sorter = new TableRowSorter(dtm);
        viewBHTable.setRowSorter(sorter);
        for (WorkRequest request : userAccount.getWorkQueue().getWorkReqestList()) {
            Object row[] = new Object[4];
            row[0] = request;
            row[1] = request.getRequestId();
            row[2] = request.getMessage();
            int donation = request.getQuantity();
            row[3] = donation;
            dtm.addRow(row);
        }
    }

    public void populatePersonInfo() {
        firstNameTField.setText(userAccount.getFirstName());
        lastNameTField.setText(userAccount.getLastName());
        genderTField.setText(userAccount.getGender());
        dobTField.setText(userAccount.getDateOfBirth());
        workPhoneTField.setText(userAccount.getWorkPhone());
        homePhoneTField.setText(userAccount.getHomePhone());
        emailTField.setText(userAccount.getEmail());
    }

    public void populateBlood() {
        int selectedrow = viewBHTable.getSelectedRow();
        if (selectedrow >= 0) {
            WorkRequest request = (WorkRequest) viewBHTable.getValueAt(selectedrow, 0);
            try {
                bBloodTypeTField.setText(request.getBlood().getBloodType());
                sourceTField.setText(request.getBlood().getClinic().getName());
            } catch (Exception e) {
                bBloodTypeTField.setText("not be tested");
                sourceTField.setText("not donate now");
            }
            quantityTField.setText(String.valueOf(request.getQuantity()));
            
        }
    }

    public void populateVitalSign() {
        int selectedrow = vitalSignTbl.getSelectedRow();
        if (selectedrow >= 0) {
            VitalSign vs = (VitalSign) vitalSignTbl.getValueAt(selectedrow, 1);
            vSBloodTypeTField.setText(vs.getBloodtype());
            hemoblobinTField.setText(vs.getHemoglobin());
            infectionTField.setText(vs.getInfection());
            diabetesTField.setText(vs.getDiabetes());
            tempConditonTField.setText(vs.getTempCondition());
            permConditionTField.setText(vs.getPermCondition());
        }
    }

    public void validateInput() {
        validation = true;
        errorInfo = "";

        if (validatePhoneNumber(homePhoneTField.getText()) || validatePhoneNumber(workPhoneTField.getText())) {
            errorInfo += "Please innput Correct homephone/workphone. \n";
            validation = false;
        }
        if (validateEmail(emailTField.getText())) {
            errorInfo += "Please innput Correct e-mail. \n";
            validation = false;
        }
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        firstNameLabel = new javax.swing.JLabel();
        firstNameTField = new javax.swing.JTextField();
        lastNameTField = new javax.swing.JTextField();
        lastNameLabel = new javax.swing.JLabel();
        genderLaber = new javax.swing.JLabel();
        dobLabel = new javax.swing.JLabel();
        dobTField = new javax.swing.JTextField();
        homePhoneTField = new javax.swing.JTextField();
        homePhoneLabel = new javax.swing.JLabel();
        workPhoneTField = new javax.swing.JTextField();
        workPhoneLabel = new javax.swing.JLabel();
        EmailLabel = new javax.swing.JLabel();
        emailTField = new javax.swing.JTextField();
        genderTField = new javax.swing.JTextField();
        updateBtn = new javax.swing.JButton();
        cancelBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        firstNameLabel1 = new javax.swing.JLabel();
        bBloodTypeTField = new javax.swing.JTextField();
        lastNameLabel1 = new javax.swing.JLabel();
        quantityTField = new javax.swing.JTextField();
        dobLabel1 = new javax.swing.JLabel();
        sourceTField = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        firstNameLabel2 = new javax.swing.JLabel();
        vSBloodTypeTField = new javax.swing.JTextField();
        lastNameLabel2 = new javax.swing.JLabel();
        hemoblobinTField = new javax.swing.JTextField();
        firstNameLabel3 = new javax.swing.JLabel();
        infectionTField = new javax.swing.JTextField();
        lastNameLabel3 = new javax.swing.JLabel();
        diabetesTField = new javax.swing.JTextField();
        firstNameLabel4 = new javax.swing.JLabel();
        tempConditonTField = new javax.swing.JTextField();
        firstNameLabel5 = new javax.swing.JLabel();
        permConditionTField = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        vitalSignTbl = new javax.swing.JTable();
        cancelBtn1 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        viewBHTable = new javax.swing.JTable();

        setBackground(new java.awt.Color(250, 250, 250));

        jPanel1.setBackground(new java.awt.Color(250, 250, 250));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Personal info", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Microsoft YaHei UI", 0, 24))); // NOI18N

        firstNameLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        firstNameLabel.setText("Firstname");
        firstNameLabel.setPreferredSize(new java.awt.Dimension(150, 24));

        firstNameTField.setFont(new java.awt.Font("Microsoft YaHei Light", 0, 14)); // NOI18N
        firstNameTField.setEnabled(false);

        lastNameTField.setFont(new java.awt.Font("Microsoft YaHei Light", 0, 14)); // NOI18N
        lastNameTField.setEnabled(false);

        lastNameLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        lastNameLabel.setText("Lastname");
        lastNameLabel.setPreferredSize(new java.awt.Dimension(150, 24));

        genderLaber.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        genderLaber.setText("Gender");
        genderLaber.setPreferredSize(new java.awt.Dimension(150, 24));

        dobLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        dobLabel.setText("Date of birth");
        dobLabel.setMaximumSize(new java.awt.Dimension(150, 24));
        dobLabel.setMinimumSize(new java.awt.Dimension(150, 24));
        dobLabel.setPreferredSize(new java.awt.Dimension(150, 24));

        dobTField.setFont(new java.awt.Font("Microsoft YaHei Light", 0, 14)); // NOI18N
        dobTField.setEnabled(false);

        homePhoneTField.setFont(new java.awt.Font("Microsoft YaHei Light", 0, 14)); // NOI18N

        homePhoneLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        homePhoneLabel.setText("Home Phone");
        homePhoneLabel.setMaximumSize(new java.awt.Dimension(150, 24));
        homePhoneLabel.setMinimumSize(new java.awt.Dimension(150, 24));
        homePhoneLabel.setPreferredSize(new java.awt.Dimension(150, 24));

        workPhoneTField.setFont(new java.awt.Font("Microsoft YaHei Light", 0, 14)); // NOI18N

        workPhoneLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        workPhoneLabel.setText("Work Phone");
        workPhoneLabel.setMaximumSize(new java.awt.Dimension(150, 24));
        workPhoneLabel.setMinimumSize(new java.awt.Dimension(150, 24));
        workPhoneLabel.setPreferredSize(new java.awt.Dimension(150, 24));

        EmailLabel.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        EmailLabel.setText("Email");
        EmailLabel.setMaximumSize(new java.awt.Dimension(150, 24));
        EmailLabel.setMinimumSize(new java.awt.Dimension(150, 24));
        EmailLabel.setPreferredSize(new java.awt.Dimension(150, 24));

        emailTField.setFont(new java.awt.Font("Microsoft YaHei Light", 0, 14)); // NOI18N

        genderTField.setFont(new java.awt.Font("Microsoft YaHei Light", 0, 14)); // NOI18N
        genderTField.setEnabled(false);

        updateBtn.setBackground(new java.awt.Color(250, 250, 250));
        updateBtn.setFont(new java.awt.Font("Microsoft YaHei UI Light", 0, 14)); // NOI18N
        updateBtn.setText("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(EmailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(emailTField))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(workPhoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(workPhoneTField))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(homePhoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(homePhoneTField))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(dobLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dobTField))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(firstNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(firstNameTField, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lastNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lastNameTField, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(genderLaber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(genderTField)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(updateBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(cancelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(firstNameTField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastNameTField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(genderLaber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(genderTField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dobLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dobTField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(homePhoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(homePhoneTField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(workPhoneLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(workPhoneTField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EmailLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailTField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateBtn)
                    .addComponent(cancelBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(250, 250, 250));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Blood ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Microsoft YaHei UI", 0, 24))); // NOI18N

        firstNameLabel1.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        firstNameLabel1.setText("Blood type");
        firstNameLabel1.setPreferredSize(new java.awt.Dimension(150, 24));

        bBloodTypeTField.setBackground(new java.awt.Color(250, 250, 250));
        bBloodTypeTField.setFont(new java.awt.Font("Microsoft YaHei Light", 0, 14)); // NOI18N
        bBloodTypeTField.setEnabled(false);

        lastNameLabel1.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        lastNameLabel1.setText("quantity");
        lastNameLabel1.setPreferredSize(new java.awt.Dimension(150, 24));

        quantityTField.setBackground(new java.awt.Color(250, 250, 250));
        quantityTField.setFont(new java.awt.Font("Microsoft YaHei Light", 0, 14)); // NOI18N
        quantityTField.setEnabled(false);

        dobLabel1.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        dobLabel1.setText("Source");
        dobLabel1.setMaximumSize(new java.awt.Dimension(150, 24));
        dobLabel1.setMinimumSize(new java.awt.Dimension(150, 24));
        dobLabel1.setPreferredSize(new java.awt.Dimension(150, 24));

        sourceTField.setBackground(new java.awt.Color(250, 250, 250));
        sourceTField.setFont(new java.awt.Font("Microsoft YaHei Light", 0, 14)); // NOI18N
        sourceTField.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(dobLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sourceTField))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(firstNameLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bBloodTypeTField, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lastNameLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quantityTField, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastNameLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bBloodTypeTField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantityTField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dobLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sourceTField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(250, 250, 250));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vital sign", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Microsoft YaHei UI", 0, 24))); // NOI18N

        firstNameLabel2.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        firstNameLabel2.setText("Blood type");
        firstNameLabel2.setPreferredSize(new java.awt.Dimension(150, 24));

        vSBloodTypeTField.setBackground(new java.awt.Color(250, 250, 250));
        vSBloodTypeTField.setFont(new java.awt.Font("Microsoft YaHei Light", 0, 14)); // NOI18N
        vSBloodTypeTField.setEnabled(false);

        lastNameLabel2.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        lastNameLabel2.setText("hemoglobin");
        lastNameLabel2.setPreferredSize(new java.awt.Dimension(150, 24));

        hemoblobinTField.setBackground(new java.awt.Color(250, 250, 250));
        hemoblobinTField.setFont(new java.awt.Font("Microsoft YaHei Light", 0, 14)); // NOI18N
        hemoblobinTField.setEnabled(false);

        firstNameLabel3.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        firstNameLabel3.setText("Infection");
        firstNameLabel3.setPreferredSize(new java.awt.Dimension(150, 24));

        infectionTField.setBackground(new java.awt.Color(250, 250, 250));
        infectionTField.setFont(new java.awt.Font("Microsoft YaHei Light", 0, 14)); // NOI18N
        infectionTField.setEnabled(false);

        lastNameLabel3.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        lastNameLabel3.setText("diabetes");
        lastNameLabel3.setPreferredSize(new java.awt.Dimension(150, 24));

        diabetesTField.setBackground(new java.awt.Color(250, 250, 250));
        diabetesTField.setFont(new java.awt.Font("Microsoft YaHei Light", 0, 14)); // NOI18N
        diabetesTField.setEnabled(false);

        firstNameLabel4.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        firstNameLabel4.setText("Other temporary condition");
        firstNameLabel4.setPreferredSize(new java.awt.Dimension(150, 24));

        tempConditonTField.setBackground(new java.awt.Color(250, 250, 250));
        tempConditonTField.setFont(new java.awt.Font("Microsoft YaHei Light", 0, 14)); // NOI18N
        tempConditonTField.setEnabled(false);

        firstNameLabel5.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        firstNameLabel5.setText("Other permanent condition");
        firstNameLabel5.setPreferredSize(new java.awt.Dimension(150, 24));

        permConditionTField.setBackground(new java.awt.Color(250, 250, 250));
        permConditionTField.setFont(new java.awt.Font("Microsoft YaHei Light", 0, 14)); // NOI18N
        permConditionTField.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(firstNameLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vSBloodTypeTField, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lastNameLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(hemoblobinTField))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(firstNameLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(infectionTField, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lastNameLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(diabetesTField))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(firstNameLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                            .addComponent(firstNameLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(permConditionTField)
                            .addComponent(tempConditonTField))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastNameLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vSBloodTypeTField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hemoblobinTField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lastNameLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(infectionTField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(diabetesTField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tempConditonTField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(permConditionTField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(250, 250, 250));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vital Signs", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("微软雅黑 Light", 1, 24))); // NOI18N

        vitalSignTbl.setBackground(new java.awt.Color(250, 250, 250));
        vitalSignTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Healthy?"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        vitalSignTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vitalSignTblMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(vitalSignTbl);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 561, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cancelBtn1.setBackground(new java.awt.Color(250, 250, 250));
        cancelBtn1.setFont(new java.awt.Font("Microsoft YaHei UI Light", 0, 14)); // NOI18N
        cancelBtn1.setText("<< Back");
        cancelBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtn1ActionPerformed(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(250, 250, 250));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Blood History", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Microsoft YaHei", 0, 24))); // NOI18N

        viewBHTable.setBackground(new java.awt.Color(250, 250, 250));
        viewBHTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Status", "ID", "Aim", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        viewBHTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewBHTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(viewBHTable);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cancelBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cancelBtn1))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        // TODO add your handling code here:

        validateInput();
        if (validation) {
            userAccount.setWorkPhone(workPhoneTField.getText());
            userAccount.setHomePhone(homePhoneTField.getText());
            userAccount.setEmail(emailTField.getText());
            populatePersonInfo();
            JOptionPane.showMessageDialog(null, "Update succeed!");

        } else {
            JOptionPane.showMessageDialog(null, errorInfo);
        }


    }//GEN-LAST:event_updateBtnActionPerformed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        // TODO add your handling code here:
        populatePersonInfo();
    }//GEN-LAST:event_cancelBtnActionPerformed

    private void cancelBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtn1ActionPerformed
        // TODO add your handling code here:
        displayPanel.remove(this);
        CardLayout layout = (CardLayout) displayPanel.getLayout();
        layout.previous(displayPanel);
    }//GEN-LAST:event_cancelBtn1ActionPerformed

    private void vitalSignTblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vitalSignTblMouseClicked
        // TODO add your handling code here:
        populateBlood();
        populateVitalSign();
        populatePersonInfo();
    }//GEN-LAST:event_vitalSignTblMouseClicked

    private void viewBHTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewBHTableMouseClicked
        // TODO add your handling code here:
        populateBlood();
        populateVitalSign();
        populatePersonInfo();
    }//GEN-LAST:event_viewBHTableMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel EmailLabel;
    private javax.swing.JTextField bBloodTypeTField;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton cancelBtn1;
    private javax.swing.JTextField diabetesTField;
    private javax.swing.JLabel dobLabel;
    private javax.swing.JLabel dobLabel1;
    private javax.swing.JTextField dobTField;
    private javax.swing.JTextField emailTField;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JLabel firstNameLabel1;
    private javax.swing.JLabel firstNameLabel2;
    private javax.swing.JLabel firstNameLabel3;
    private javax.swing.JLabel firstNameLabel4;
    private javax.swing.JLabel firstNameLabel5;
    private javax.swing.JTextField firstNameTField;
    private javax.swing.JLabel genderLaber;
    private javax.swing.JTextField genderTField;
    private javax.swing.JTextField hemoblobinTField;
    private javax.swing.JLabel homePhoneLabel;
    private javax.swing.JTextField homePhoneTField;
    private javax.swing.JTextField infectionTField;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JLabel lastNameLabel1;
    private javax.swing.JLabel lastNameLabel2;
    private javax.swing.JLabel lastNameLabel3;
    private javax.swing.JTextField lastNameTField;
    private javax.swing.JTextField permConditionTField;
    private javax.swing.JTextField quantityTField;
    private javax.swing.JTextField sourceTField;
    private javax.swing.JTextField tempConditonTField;
    private javax.swing.JButton updateBtn;
    private javax.swing.JTextField vSBloodTypeTField;
    public javax.swing.JTable viewBHTable;
    private javax.swing.JTable vitalSignTbl;
    private javax.swing.JLabel workPhoneLabel;
    private javax.swing.JTextField workPhoneTField;
    // End of variables declaration//GEN-END:variables
}
