/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.blood;

import business.VitalSign.VitalSign;
import business.organization.Clinic;
import business.useraccount.UserAccount;

/**
 *
 * @author Jiamin.S <shang.j@husky.neu.edu>
 */
public class Blood {

    private UserAccount donor;
    private UserAccount usePerson;
    private String bloodType;
    private String date;
    private Clinic clinic;
    private int volum;
//    private VitalSign vitalSign;

//    public Blood(DRAccount donor, String bloodType, Clinic clinic) {
//        this.donor = donor;
//        this.bloodType = bloodType;
////        this.date = date;
//        this.clinic = clinic;

    public Blood() {
        usePerson = null;
    }

//    }
    public UserAccount getDonor() {
        return donor;
    }

    public void setDonor(UserAccount donor) {
        this.donor = donor;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getDate() {
        return date;
    }

    public UserAccount getUsePerson() {
        return usePerson;
    }

    public void setUsePerson(UserAccount userPerson) {
        this.usePerson = userPerson;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public int getVolum() {
        return volum;
    }

    public void setVolum(int volum) {
        this.volum = volum;
    }

    
    
    

}
