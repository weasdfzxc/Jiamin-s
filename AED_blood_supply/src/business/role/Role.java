/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.role;

import business.EcoSystem;
import business.organization.Organization;
import business.useraccount.UserAccount;
import javax.swing.JPanel;

/**
 *
 * @author raunak
 */
public abstract class Role {
    
    public enum RoleType{
        SAdmin("Admin"),
        Donor("Doctor"),
        BMCManage("BloodManageCenterManager"),
        Nurse("Nurse"),
        Receiver("Receiver"),
        Receptionist("Receptionist");
        
        private String value;
        private RoleType(String value){
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
    
    public abstract JPanel createWorkArea(JPanel displayPanel,
            UserAccount userAccount,
            Organization organization, EcoSystem system);
    
    @Override
    public String toString() {
        return this.getClass().getName();
    }
    
    
}