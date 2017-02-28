/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfacepac.manager;

import business.EcoSystem;
import business.blood.Blood;
import business.organization.BloodBank;
import business.organization.BloodManageCenter;
import business.organization.Clinic;
import business.organization.Organization;
import business.useraccount.UserAccount;
import business.workqueue.WorkRequest;
import interfacepac.donorreceiver.*;
import interfacepac.receptionist.*;
import interfacepac.sysadmin.*;
import java.awt.CardLayout;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import com.orsoncharts.label.StandardCategoryItemLabelGenerator;
import com.orsoncharts.label.StandardCategoryLabelGenerator;
import com.orsoncharts.util.TextAnchor;
import java.awt.BasicStroke;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RectangleInsets;

/**
 *
 * @author Jiamin.S <shang.j@husky.neu.edu>
 */
public class ManageWorkAreaJPanel extends javax.swing.JPanel {

    /**
     * Creates new form BloodManageCenterJPanel
     */
    private JPanel displayPanel;
    private UserAccount userAccount;
    private Organization organization;
    private EcoSystem system;
    private Organization organ;
    int tya;
    int tyb;
    int tyab;
    int tyo;
    int tyrha;
    int tyrhb;
    int tyrhab;
    int tyrho;

    public ManageWorkAreaJPanel(JPanel displayPanel, UserAccount userAccount, Organization organization, EcoSystem system) {
        this.displayPanel = displayPanel;
        this.userAccount = userAccount;
        this.organization = organization;
        this.system = system;
        organ = organization;
        initComponents();
        populatePanel();
    }

    public void populatePanel() {
        if (organization.getUpOrgan() == null) {
            populateSystemTree();
            populateOngoingTbl();
            populateProcessTbl();
            requreBtn.setEnabled(false);
        } else if (organization.getUpOrgan().getType().equals(system.getType())) {
            populateLvFTree();
            populateOngoingTbl();
            populateProcessTbl();
        } else {
            populateLvSTree();
            populateOngoingTbl();
            populateProcessTbl();
        }

        if (organ.getType().equals(Organization.OrganizationType.BloodBank)) {
            transferBtn.setEnabled(true);
        } else {
            transferBtn.setEnabled(false);
        }
    }

    public void populateSystemTree() {
        DefaultTreeModel model = (DefaultTreeModel) viewJTree.getModel();
        ArrayList<BloodManageCenter> firstBMCList = system.getBloodManageCenterList();

        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        root.removeAllChildren();

        int i = 0;
        for (BloodManageCenter lv1BMC : firstBMCList) {
            DefaultMutableTreeNode firstBMCNode = new DefaultMutableTreeNode(lv1BMC);
            root.insert(firstBMCNode, i++);
            int j = 0;
            for (BloodManageCenter lv2BMC : lv1BMC.getNextLvBloodManageCenterList()) {
                DefaultMutableTreeNode secondBMCNode = new DefaultMutableTreeNode(lv2BMC);
                firstBMCNode.insert(secondBMCNode, j++);
                int k = 0;
                for (BloodBank bloodBank : lv2BMC.getBloodBankList()) {
                    DefaultMutableTreeNode bloodBankNode = new DefaultMutableTreeNode(bloodBank);
                    secondBMCNode.insert(bloodBankNode, k++);
                }
                //DefaultMutableTreeNode thirdDistributionNode = new DefaultMutableTreeNode(lv2BMC.getDistributionCenter());
                //secondBMCNode.insert(thirdDistributionNode, k++);
            }
            //DefaultMutableTreeNode secondDistributionNode = new DefaultMutableTreeNode(lv1BMC.getDistributionCenter());
            //firstBMCNode.insert(secondDistributionNode, j++);
        }
        DefaultMutableTreeNode firstDistributionNode = new DefaultMutableTreeNode(system.getDistributionCenter());
        root.insert(firstDistributionNode, i++);
        model.reload();
    }

    public void populateLvFTree() {
        DefaultTreeModel model = (DefaultTreeModel) viewJTree.getModel();
        BloodManageCenter bMC = (BloodManageCenter) organization;
        ArrayList<BloodManageCenter> secondBMCList = bMC.getNextLvBloodManageCenterList();

        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        root.removeAllChildren();

        int j = 0;
        for (BloodManageCenter lv2BMC : secondBMCList) {
            DefaultMutableTreeNode secondBMCNode = new DefaultMutableTreeNode(lv2BMC);
            root.insert(secondBMCNode, j++);
            int k = 0;
            for (BloodBank bloodBank : lv2BMC.getBloodBankList()) {
                DefaultMutableTreeNode bloodBankNode = new DefaultMutableTreeNode(bloodBank);
                secondBMCNode.insert(bloodBankNode, k++);
                //DefaultMutableTreeNode fourthDistributionNode = new DefaultMutableTreeNode(bloodBank.getDistributionCenter());
                //bloodBankNode.insert(fourthDistributionNode, l++);
            }
            //DefaultMutableTreeNode thirdDistributionNode = new DefaultMutableTreeNode(lv2BMC.getDistributionCenter());
            //secondBMCNode.insert(thirdDistributionNode, k++);
        }

        model.reload();
    }

    public void populateLvSTree() {
        DefaultTreeModel model = (DefaultTreeModel) viewJTree.getModel();
        BloodManageCenter bMC = (BloodManageCenter) organization;

        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        root.removeAllChildren();

        int k = 0;
        for (BloodBank bloodBank : bMC.getBloodBankList()) {
            DefaultMutableTreeNode bloodBankNode = new DefaultMutableTreeNode(bloodBank);
            root.insert(bloodBankNode, k++);
        }

        model.reload();
    }

    public void populateBankTree() {
        DefaultTreeModel model = (DefaultTreeModel) viewJTree.getModel();
        BloodBank bb = (BloodBank) organization;

        DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();
        root.removeAllChildren();
        int l = 0;
        for (Clinic clinic : bb.getClinicList()) {
            DefaultMutableTreeNode clinicNode = new DefaultMutableTreeNode(clinic);
            root.insert(clinicNode, l++);
            int m = 0;
            for (Organization organ : clinic.getOrganizationList()) {
                DefaultMutableTreeNode organNode = new DefaultMutableTreeNode(organ);
                clinicNode.insert(organNode, m++);
            }
        }

        model.reload();
    }

    public void populateOngoingTbl() {
        DefaultTableModel model = (DefaultTableModel) ongoingTbl.getModel();
        model.setRowCount(0);

        for (WorkRequest request : organization.getWorkQueue().getWorkReqestList()) {
            if (request.getStatus().equals("Holding")) {
                request.setReceiver(userAccount);
                Object[] row = new Object[5];
                row[0] = request;
                row[1] = request.getSender();
                row[2] = request.getReceiver() == null ? null : request.getReceiver();
                row[3] = request.getBlood().getBloodType();
                int donation = request.getQuantity();
                row[4] = donation;

                model.addRow(row);
            }
        }
    }

    public void populateProcessTbl() {
        DefaultTableModel model = (DefaultTableModel) finishedTbl.getModel();
        model.setRowCount(0);

        for (WorkRequest request : userAccount.getWorkQueue().getWorkReqestList()) {
            if (request.getStatus().equals("Holding")) {
            } else {
                Object[] row = new Object[5];
                row[0] = request;
                row[1] = request.getSender();
                row[2] = request.getReceiver() == null ? null : request.getReceiver();
                row[3] = request.getBlood().getBloodType();
                int donation = request.getQuantity();
                row[4] = donation;

                model.addRow(row);
            }
        }
        populateReport();
    }

    private void reqBlood(WorkRequest req) {
        BloodBank bb = (BloodBank) organ;
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

    public void populateReport() {
        tya = 0;
        tyb = 0;
        tyab = 0;
        tyo = 0;
        tyrha = 0;
        tyrhb = 0;
        tyrhab = 0;
        tyrho = 0;
        if (organ.getUpOrgan() == null) {
            for (BloodManageCenter bmc : system.getBloodManageCenterList()) {
                for (BloodManageCenter bmcs : bmc.getNextLvBloodManageCenterList()) {
                    for (BloodBank bb : bmcs.getBloodBankList()) {
                        tya += bb.calculateARepertory();
                        tyb += bb.calculateBRepertory();
                        tyab += bb.calculateABRepertory();
                        tyo += bb.calculateORepertory();
                        tyrha += bb.calculateRHARepertory();
                        tyrhb += bb.calculateRHBRepertory();
                        tyrhab += bb.calculateRHABRepertory();
                        tyrho += bb.calculateRHORepertory();
                    }
                }
            }

        } else {
            try {
                BloodManageCenter bmc = (BloodManageCenter) organ;
                if (bmc.getUpOrgan().getType().equals(system.getType())) {
                    for (BloodManageCenter bmcs : bmc.getNextLvBloodManageCenterList()) {
                        for (BloodBank bb : bmcs.getBloodBankList()) {
                            tya += bb.calculateARepertory();
                            tyb += bb.calculateBRepertory();
                            tyab += bb.calculateABRepertory();
                            tyo += bb.calculateORepertory();
                            tyrha += bb.calculateRHARepertory();
                            tyrhb += bb.calculateRHBRepertory();
                            tyrhab += bb.calculateRHABRepertory();
                            tyrho += bb.calculateRHORepertory();
                        }
                    }
                } else {
                    for (BloodBank bb : bmc.getBloodBankList()) {
                        tya += bb.calculateARepertory();
                        tyb += bb.calculateBRepertory();
                        tyab += bb.calculateABRepertory();
                        tyo += bb.calculateORepertory();
                        tyrha += bb.calculateRHARepertory();
                        tyrhb += bb.calculateRHBRepertory();
                        tyrhab += bb.calculateRHABRepertory();
                        tyrho += bb.calculateRHORepertory();
                    }
                }
            } catch (Exception e) {
                BloodBank bb = (BloodBank) organ;
                tya = bb.calculateARepertory();
                tyb = bb.calculateBRepertory();
                tyab = bb.calculateABRepertory();
                tyo = bb.calculateORepertory();
                tyrha = bb.calculateRHARepertory();
                tyrhb = bb.calculateRHBRepertory();
                tyrhab = bb.calculateRHABRepertory();
                tyrho = bb.calculateRHORepertory();
            }

        }

        tpa.setText(String.valueOf(tya + " ml"));
        tpb.setText(String.valueOf(tyb + " ml"));
        tpab.setText(String.valueOf(tyab + " ml"));
        tpo.setText(String.valueOf(tyo + " ml"));
        tprha.setText(String.valueOf(tyrha + " ml"));
        tprhb.setText(String.valueOf(tyrhb + " ml"));
        tprhab.setText(String.valueOf(tyrhab + " ml"));
        tprho.setText(String.valueOf(tyrho + " ml"));
//        int allamount = tya + tyb + tyo + tyab + tyrha + tyrhb + tyrhab + tyrho;
//        if (allamount <= 0) {
//            tpa1.setText(0 + "%");
//            tpb1.setText(0 + "%");
//            tpab1.setText(0 + "%");
//            tpo1.setText(0 + "%");
//            tprha1.setText(0 + "%");
//            tprhb1.setText(0 + "%");
//            tprhab1.setText(0 + "%");
//            tprho1.setText(0 + "%");
//        } else {
//            tpa1.setText(tya / allamount + "%");
//            tpb1.setText(tyb / allamount + "%");
//            tpab1.setText(tyab / allamount + "%");
//            tpo1.setText(tyo / allamount + "%");
//            tprha1.setText(tyrha / allamount + "%");
//            tprhb1.setText(tyrhb / allamount + "%");
//            tprhab1.setText(tyrhab / allamount + "%");
//            tprho1.setText(tyrho / allamount + "%");
//        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        viewDetailsBtn = new javax.swing.JButton();
        requreBtn = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        finishedTbl = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        viewJTree = new javax.swing.JTree();
        jScrollPane7 = new javax.swing.JScrollPane();
        ongoingTbl = new javax.swing.JTable();
        rejectBtn = new javax.swing.JButton();
        transferBtn = new javax.swing.JButton();
        cPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tpa = new javax.swing.JLabel();
        tpb = new javax.swing.JLabel();
        tpo = new javax.swing.JLabel();
        tpab = new javax.swing.JLabel();
        tprha = new javax.swing.JLabel();
        tprhb = new javax.swing.JLabel();
        tprho = new javax.swing.JLabel();
        tprhab = new javax.swing.JLabel();
        dataBtn = new javax.swing.JButton();
        dataBtn1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(250, 250, 250));

        viewDetailsBtn.setBackground(new java.awt.Color(250, 250, 250));
        viewDetailsBtn.setFont(new java.awt.Font("Microsoft YaHei UI Light", 0, 14)); // NOI18N
        viewDetailsBtn.setText("view details");
        viewDetailsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewDetailsBtnActionPerformed(evt);
            }
        });

        requreBtn.setBackground(new java.awt.Color(250, 250, 250));
        requreBtn.setFont(new java.awt.Font("Microsoft YaHei UI Light", 0, 14)); // NOI18N
        requreBtn.setText("require");
        requreBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requreBtnActionPerformed(evt);
            }
        });

        jScrollPane6.setBackground(new java.awt.Color(250, 250, 250));

        finishedTbl.setBackground(new java.awt.Color(250, 250, 250));
        finishedTbl.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        finishedTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Status", "Sender", "Operator", "Blood type", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        finishedTbl.setGridColor(new java.awt.Color(250, 250, 250));
        jScrollPane6.setViewportView(finishedTbl);

        viewJTree.setBackground(new java.awt.Color(250, 250, 250));
        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("US");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("colors");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("blue");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("violet");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("red");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("yellow");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("sports");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("basketball");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("soccer");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("football");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("hockey");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("food");
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("hot dogs");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("pizza");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("ravioli");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("bananas");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        viewJTree.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        viewJTree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                viewJTreeMouseClicked(evt);
            }
        });
        viewJTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                viewJTreeValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(viewJTree);

        jScrollPane7.setBackground(new java.awt.Color(250, 250, 250));

        ongoingTbl.setBackground(new java.awt.Color(250, 250, 250));
        ongoingTbl.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 12)); // NOI18N
        ongoingTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Status", "Sender", "Operator", "Blood type", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ongoingTbl.setGridColor(new java.awt.Color(250, 250, 250));
        jScrollPane7.setViewportView(ongoingTbl);

        rejectBtn.setBackground(new java.awt.Color(250, 250, 250));
        rejectBtn.setFont(new java.awt.Font("Microsoft YaHei UI Light", 0, 14)); // NOI18N
        rejectBtn.setText("reject");
        rejectBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rejectBtnActionPerformed(evt);
            }
        });

        transferBtn.setBackground(new java.awt.Color(250, 250, 250));
        transferBtn.setFont(new java.awt.Font("Microsoft YaHei UI Light", 0, 14)); // NOI18N
        transferBtn.setText("transfer");
        transferBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                transferBtnActionPerformed(evt);
            }
        });

        cPanel.setBackground(new java.awt.Color(250, 250, 250));
        cPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data view", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Verdana", 0, 24))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel1.setText("Type A");

        jLabel2.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel2.setText("Type B");

        jLabel3.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel3.setText("Type O");

        jLabel4.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel4.setText("Type AB");

        jLabel5.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel5.setText("Type RHA");

        jLabel6.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel6.setText("Type RHB");

        jLabel7.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel7.setText("Type RHO");

        jLabel8.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel8.setText("Type RHAB");

        tpa.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        tpa.setText("Type A");

        tpb.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        tpb.setText("Type A");

        tpo.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        tpo.setText("Type A");

        tpab.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        tpab.setText("Type A");

        tprha.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        tprha.setText("Type A");

        tprhb.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        tprhb.setText("Type A");

        tprho.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        tprho.setText("Type A");

        tprhab.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        tprhab.setText("Type A");

        dataBtn.setBackground(new java.awt.Color(250, 250, 250));
        dataBtn.setFont(new java.awt.Font("Microsoft YaHei UI Light", 0, 14)); // NOI18N
        dataBtn.setText("view quantity of each type");
        dataBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataBtnActionPerformed(evt);
            }
        });

        dataBtn1.setBackground(new java.awt.Color(250, 250, 250));
        dataBtn1.setFont(new java.awt.Font("Microsoft YaHei UI Light", 0, 14)); // NOI18N
        dataBtn1.setText("view ratio of each type");
        dataBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dataBtn1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cPanelLayout = new javax.swing.GroupLayout(cPanel);
        cPanel.setLayout(cPanelLayout);
        cPanelLayout.setHorizontalGroup(
            cPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cPanelLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(cPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(cPanelLayout.createSequentialGroup()
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(tprhab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(cPanelLayout.createSequentialGroup()
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(tprho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(cPanelLayout.createSequentialGroup()
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(tprhb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(cPanelLayout.createSequentialGroup()
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(tprha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(cPanelLayout.createSequentialGroup()
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(tpab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(cPanelLayout.createSequentialGroup()
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(tpo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(cPanelLayout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(tpb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(cPanelLayout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(tpa, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(cPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(dataBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                        .addComponent(dataBtn1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(193, Short.MAX_VALUE))
        );
        cPanelLayout.setVerticalGroup(
            cPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(cPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tpa, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tpb, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tpo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tpab, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tprha, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tprhb, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tprho, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(cPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tprhab, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(dataBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dataBtn1)
                .addContainerGap(40, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(requreBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(transferBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rejectBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(viewDetailsBtn)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                .addComponent(cPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(requreBtn)
                                    .addComponent(rejectBtn)
                                    .addComponent(transferBtn)
                                    .addComponent(viewDetailsBtn)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(cPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(86, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void viewDetailsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewDetailsBtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = ongoingTbl.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a request.");
            return;
        }
        WorkRequest request = (WorkRequest) ongoingTbl.getValueAt(selectedRow, 0);
        PersonInfoJPanel panel = new PersonInfoJPanel(displayPanel, request.getSender(), organization, system);
        displayPanel.add("PerosonInfoJPanel", panel);
        CardLayout layout = (CardLayout) displayPanel.getLayout();
        layout.next(displayPanel);
        populatePanel();
    }//GEN-LAST:event_viewDetailsBtnActionPerformed

    private void requreBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requreBtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = ongoingTbl.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a request.");
            return;
        }
        WorkRequest request = (WorkRequest) ongoingTbl.getValueAt(selectedRow, 0);
        organization.getUpOrgan().getWorkQueue().getWorkReqestList().add(request);
        request.setStatus("Holding");
        populatePanel();
    }//GEN-LAST:event_requreBtnActionPerformed

    private void rejectBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rejectBtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = ongoingTbl.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a request.");
            return;
        }
        WorkRequest request = (WorkRequest) ongoingTbl.getValueAt(selectedRow, 0);
        request.setStatus("Reject");
        populatePanel();
    }//GEN-LAST:event_rejectBtnActionPerformed

    private void transferBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_transferBtnActionPerformed
        // TODO add your handling code here:
        int selectedRow = ongoingTbl.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(null, "Please select a request.");
            return;
        }
        WorkRequest request = (WorkRequest) ongoingTbl.getValueAt(selectedRow, 0);
        this.reqBlood(request);

        system.getDistributionCenter().getWorkQueue().getWorkReqestList().add(request);
        request.setStatus("Transfer");
        populatePanel();
    }//GEN-LAST:event_transferBtnActionPerformed

    private void viewJTreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewJTreeMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_viewJTreeMouseClicked

    private void viewJTreeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_viewJTreeValueChanged
        // TODO add your handling code here:
        try {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) viewJTree.getLastSelectedPathComponent();
            organ = (Organization) selectedNode.getUserObject();
        } catch (Exception e) {
            organ = organization;
        }

        if (organ.getType().equals(Organization.OrganizationType.BloodBank.getValue())) {
            transferBtn.setEnabled(true);
        } else {
            transferBtn.setEnabled(false);
        }
        populateReport();

    }//GEN-LAST:event_viewJTreeValueChanged

    private void dataBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataBtnActionPerformed
        // TODO add your handling code here:
        DefaultCategoryDataset lineDataset = new DefaultCategoryDataset();

        lineDataset.addValue(tya, "RH+", "Type A");
        lineDataset.addValue(tyrha, "RH-", "Type A");
        lineDataset.addValue(tyb, "RH+", "Type B");
        lineDataset.addValue(tyrhb, "RH-", "Type B");
        lineDataset.addValue(tyo, "RH+", "Type O");
        lineDataset.addValue(tyrho, "RH-", "Type O");
        lineDataset.addValue(tyab, "RH+", "Type AB");
        lineDataset.addValue(tyrhab, "RH-", "Type AB");

        //JFreeChart chart = ChartFactory.createLineChart3D
        JFreeChart chart = ChartFactory.createBarChart//non-3D
                ("Vital Sign", "Blood type", "Quantity", lineDataset);

        // set bgcolor of chart
        chart.setBackgroundPaint(new Color(255, 255, 255));//white

        // set type of title
        TextTitle title = chart.getTitle();
        title.setFont(new Font("Black", Font.PLAIN, 25));

        CategoryPlot plot = chart.getCategoryPlot();

        //set Y title direction
        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(true);
        rangeAxis.setUpperMargin(0.20);
        rangeAxis.setLabelAngle(Math.PI / 2.0);

        //LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        /*
        BasicStroke bs ;//set line weight
        for (int i = 0; i < vitalSignTable.getRowCount(); i++)
        {
            float dashes[] = {10.0f};
            bs = new BasicStroke(2.0f, BasicStroke.CAP_ROUND,
                //bs = new BasicStroke(2.0f, BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_ROUND, 10.f, dashes, 0.0f);
                if (i % 2 != 0)
                renderer.setSeriesStroke(i, bs);
                else
                renderer.setSeriesStroke(i, new BasicStroke(2.0f));
            }
         */
        //how to show Item label
        renderer.setBaseItemLabelsVisible(true);
        //renderer.setShapesVisible(true);
        //renderer.setDrawOutlines(true);
        //renderer.setUseFillPaint(true);
        renderer.setFillPaint(java.awt.Color.white);

        //renderer.setBaseItemLabelGenerator (new StandardCategoryItemLabelGenerator ());
        renderer.setItemLabelFont(new Font("Black", Font.PLAIN, 12));
        renderer.setItemLabelsVisible(true);

        //renderer.setDrawShapes(true);
        //renderer.setShapesFilled(true);
        //show data place
        //ItemLabelPosition p = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER_LEFT,TextAnchor.CENTER_LEFT, -Math.PI / 2.0 );
        //renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
        //show data
        //renderer.setBaseLabelGenerator(new StandardCategoryLabelGenerator());
        ChartFrame chartFrame = new ChartFrame("VitalSign", chart);
        chartFrame.pack(); //show suitable pic
        chartFrame.setVisible(true);//pic visible?
    }//GEN-LAST:event_dataBtnActionPerformed

    private void dataBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dataBtn1ActionPerformed
        // TODO add your handling code here:
        DefaultCategoryDataset lineDataset = new DefaultCategoryDataset();
        DefaultPieDataset pieDataset = new DefaultPieDataset();

        pieDataset.insertValue(0, "A", tya);
        pieDataset.insertValue(1, "B", tyb);
        pieDataset.insertValue(2, "O", tyo);
        pieDataset.insertValue(3, "AB", tyab);
        pieDataset.insertValue(4, "Rare type", tyrha + tyrhb + tyrho + tyrhab);

        //JFreeChart chart = ChartFactory.createLineChart3D
        JFreeChart chart = ChartFactory.createPieChart//non-3D
                ("Blood ratio", pieDataset);

        // set bgcolor of chart
        chart.setBackgroundPaint(new Color(255, 255, 255));//white

        // set type of title
        TextTitle title = chart.getTitle();
        title.setFont(new Font("Black", Font.PLAIN, 25));

        PiePlot plot = (PiePlot) chart.getPlot();

        plot.setInsets(new RectangleInsets(10, 10, 5, 10));
        plot.setInsets(new RectangleInsets(0, 0, 0, 0));
        plot.setCircular(true);
        plot.setLabelGap(0.01);
        plot.setInteriorGap(0.05D);
        plot.setLegendItemShape(new Rectangle(10, 10));
        plot.setIgnoreNullValues(true);
        plot.setLabelBackgroundPaint(null);
        plot.setLabelShadowPaint(null);
        plot.setLabelOutlinePaint(null);
        plot.setShadowPaint(null);

        ChartFrame chartFrame = new ChartFrame("VitalSign", chart);
        chartFrame.pack(); //show suitable pic
        chartFrame.setVisible(true);//pic visible?
    }//GEN-LAST:event_dataBtn1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cPanel;
    private javax.swing.JButton dataBtn;
    private javax.swing.JButton dataBtn1;
    private javax.swing.JTable finishedTbl;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable ongoingTbl;
    private javax.swing.JButton rejectBtn;
    private javax.swing.JButton requreBtn;
    private javax.swing.JLabel tpa;
    private javax.swing.JLabel tpab;
    private javax.swing.JLabel tpb;
    private javax.swing.JLabel tpo;
    private javax.swing.JLabel tprha;
    private javax.swing.JLabel tprhab;
    private javax.swing.JLabel tprhb;
    private javax.swing.JLabel tprho;
    private javax.swing.JButton transferBtn;
    private javax.swing.JButton viewDetailsBtn;
    private javax.swing.JTree viewJTree;
    // End of variables declaration//GEN-END:variables
}
