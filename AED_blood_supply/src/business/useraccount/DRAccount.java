/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.useraccount;

import business.VitalSign.VitalSignHistory;

/**
 *
 * @author Jiamin.S <shang.j@husky.neu.edu>
 */
public class DRAccount extends UserAccount{
    
    private VitalSignHistory vitalSignHistory;

    public DRAccount() {
        vitalSignHistory = new VitalSignHistory();
    }

    public VitalSignHistory getVitalSignHistory() {
        return vitalSignHistory;
    }

    public void setVitalSignHistory(VitalSignHistory vitalSignHistory) {
        this.vitalSignHistory = vitalSignHistory;
    }
    
    
}
