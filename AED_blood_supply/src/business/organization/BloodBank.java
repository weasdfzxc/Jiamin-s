/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.organization;

import business.blood.Blood;
import business.blood.BloodType;
import business.role.BloodBankManager;
import business.role.Role;
import java.util.ArrayList;

/**
 *
 * @author Jiamin.S <shang.j@husky.neu.edu>
 */
public class BloodBank extends Organization {

    private ArrayList<Clinic> clinicList;
    private DistributionCenter distributionCenter;
    private ArrayList<Blood> BloodRepertory;

    //private BloodManageCenter upOrgan = null;

    public BloodBank(String name) {
        super(OrganizationType.BloodBank.getValue());
        clinicList = new ArrayList<>();
        this.setName(name);
        distributionCenter = new DistributionCenter(name+" distribution");
        distributionCenter.setUpOrgan(this);
        BloodRepertory = new ArrayList<>();
    }

    public DistributionCenter getDistributionCenter() {
        return distributionCenter;
    }

    public void setDistributionCenter(DistributionCenter distributionCenter) {
        this.distributionCenter = distributionCenter;
    }


    public ArrayList<Clinic> getClinicList() {
        return clinicList;
    }

    public void setClinicList(ArrayList<Clinic> clinicList) {
        this.clinicList = clinicList;
    }
    
    public Clinic createClinic(String name) {
        Clinic clinic = new Clinic(name);
        clinicList.add(clinic);
        return clinic;
    }
    
    public int calculateRepertory(String str){
        int amount = 0;
        for(Blood blood : BloodRepertory){
            if(blood.getBloodType().equals(str)){
                amount += blood.getVolum();
            }
        }
        return amount;
    }
    
    public int calculateARepertory(){
        int amount = 0;
        for(Blood blood : BloodRepertory){
            if(blood.getBloodType().equals(BloodType.TYPEA.getValue())){
                amount += blood.getVolum();
            }
        }
        return amount;
    }
    
    public int calculateBRepertory(){
        int amount = 0;
        for(Blood blood : BloodRepertory){
            if(blood.getBloodType().equals(BloodType.TYPEB.getValue())){
                amount += blood.getVolum();
            }
        }
        return amount;
    }
    
    public int calculateORepertory(){
        int amount = 0;
        for(Blood blood : BloodRepertory){
            if(blood.getBloodType().equals(BloodType.TYPEO.getValue())){
                amount += blood.getVolum();
            }
        }
        return amount;
    }
    
    public int calculateRHARepertory(){
        int amount = 0;
        for(Blood blood : BloodRepertory){
            if(blood.getBloodType().equals(BloodType.TYPERHA.getValue())){
                amount += blood.getVolum();
            }
        }
        return amount;
    }
    
    public int calculateRHBRepertory(){
        int amount = 0;
        for(Blood blood : BloodRepertory){
            if(blood.getBloodType().equals(BloodType.TYPERHB.getValue())){
                amount += blood.getVolum();
            }
        }
        return amount;
    }
    
    public int calculateRHORepertory(){
        int amount = 0;
        for(Blood blood : BloodRepertory){
            if(blood.getBloodType().equals(BloodType.TYPERHO.getValue())){
                amount += blood.getVolum();
            }
        }
        return amount;
    }
    
    public int calculateRHABRepertory(){
        int amount = 0;
        for(Blood blood : BloodRepertory){
            if(blood.getBloodType().equals(BloodType.TYPERHAB.getValue())){
                amount += blood.getVolum();
            }
        }
        return amount;
    }
    
    public int calculateABRepertory(){
        int amount = 0;
        for(Blood blood : BloodRepertory){
            if(blood.getBloodType().equals(BloodType.TYPEAB.getValue())){
                amount += blood.getVolum();
            }
        }
        return amount;
    }

    public ArrayList<Blood> getBloodRepertory() {
        return BloodRepertory;
    }

    public void setBloodRepertory(ArrayList<Blood> BloodRepertory) {
        this.BloodRepertory = BloodRepertory;
    }

//    public BloodManageCenter getUpOrgan() {
//        return upOrgan;
//    }
//
//    public void setUpOrgan(BloodManageCenter upOrgan) {
//        this.upOrgan = upOrgan;
//    }

    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList();
        roles.add(new BloodBankManager());
        return roles;
    }
}
