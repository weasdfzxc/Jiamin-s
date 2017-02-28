/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.workqueue;

import business.blood.Blood;
import business.organization.Organization;

/**
 *
 * @author xdwea
 */
public class ReceiverRequest extends WorkRequest {
    private int consumption;
    private Blood blood;

    public int getConsumption() {
        return consumption;
    }

    public ReceiverRequest(Organization organ) {
        super(organ);
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }
    
}
