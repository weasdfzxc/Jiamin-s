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
public class DonorRequest extends WorkRequest {

    private int donation;
    private Blood blood;

    public DonorRequest(Organization organ) {
        super(organ);
    }

    public int getQuantity() {
        return donation;
    }

    public void setQuantity(int donation) {
        this.donation = donation;
    }
}
