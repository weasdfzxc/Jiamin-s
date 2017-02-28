/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.organization;

import business.role.Role;
import java.util.ArrayList;

/**
 *
 * @author xdwea
 */
public class DORUserController extends Organization{
    private ArrayList<Organization> organizationList;
    
        public DORUserController(String name){
        super(OrganizationType.DOR.getValue());
        organizationList = new ArrayList();
        this.setName(name);
    }

    public ArrayList<Organization> getOrganizationList() {
        return organizationList;
    }

    public void setOrganizationList(ArrayList<Organization> organizationList) {
        this.organizationList = organizationList;
    }
        
    @Override
    public ArrayList<Role> getSupportedRole() {
        return null;
    }
}
