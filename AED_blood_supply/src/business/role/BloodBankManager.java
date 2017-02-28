/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.role;

import business.EcoSystem;
import business.organization.Organization;
import business.useraccount.UserAccount;
import interfacepac.manager.BankWorkAreaJPanel;
import javax.swing.JPanel;

/**
 *
 * @author fjx19
 */
public class BloodBankManager extends Role {

    @Override
    public JPanel createWorkArea(JPanel displayPanel, UserAccount userAccount, Organization organization, EcoSystem system) {
        return new BankWorkAreaJPanel(displayPanel, userAccount, organization, system);
    }
    
}
