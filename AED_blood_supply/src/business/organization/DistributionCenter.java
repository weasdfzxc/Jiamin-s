/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.organization;

import business.role.DistributionManager;
import business.role.Role;
import java.util.ArrayList;

/**
 *
 * @author Jiamin.S <shang.j@husky.neu.edu>
 */
public class DistributionCenter extends Organization{
    //private Organization upOrgan = null;
    
    public DistributionCenter(String name){
        super(OrganizationType.Distribution.getValue());
        this.setName(name);
    }

//    public Organization getUpOrgan() {
//        return upOrgan;
//    }
//
//    public void setUpOrgan(Organization upOrgan) {
//        this.upOrgan = upOrgan;
//    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new DistributionManager());
        return roles;
    }
    
}
