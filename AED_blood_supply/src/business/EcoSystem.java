/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import business.organization.*;
import business.role.Role;
import business.role.SAdmin;
import business.useraccount.UserAccountDir;
import java.util.ArrayList;

/**
 *
 * @author Jiamin.S <shang.j@husky.neu.edu>
 */
public class EcoSystem extends Organization {

    private static EcoSystem business;
    private ArrayList<BloodManageCenter> bloodManageCenterList;
    private DORUserController dORUserController;
    private DistributionCenter distributionCenter;

    public DistributionCenter getDistributionCenter() {
        return distributionCenter;
    }

    public void setDistributionCenter(DistributionCenter distributionCenter) {
        this.distributionCenter = distributionCenter;
    }

    private EcoSystem() {
        super("system");
        bloodManageCenterList = new ArrayList();
        dORUserController = new DORUserController("User Area");
        distributionCenter = new DistributionCenter("Global DistributionCenter");
    }

    public static EcoSystem getInstance() {
        if (business == null) {
            business = new EcoSystem();
        }
        return business;
    }

    public ArrayList<BloodManageCenter> getBloodManageCenterList() {
        return bloodManageCenterList;
    }

    public void setBloodManageCenterList(ArrayList<BloodManageCenter> bloodManageCenterList) {
        this.bloodManageCenterList = bloodManageCenterList;
    }

    public BloodManageCenter createBloodManageCenter(String s) {
        BloodManageCenter bloodManageCenter = new BloodManageCenter(s);
        bloodManageCenterList.add(bloodManageCenter);
        return bloodManageCenter;
    }

    public DORUserController getdORUserController() {
        return dORUserController;
    }

    public void setdORUserController(DORUserController dORUserController) {
        this.dORUserController = dORUserController;
    }
    

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roleList = new ArrayList();
        roleList.add(new SAdmin());
        return roleList;
    }

}
