/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.VitalSign;

/**
 *
 * @author fjx19
 */
public class VitalSign {

    private int vitalSignID;
    private String date;
    private String bloodtype;
    private String hemoglobin;
    private String infection;
    private String diabetes;
    private String tempCondition;
    private String permCondition;
    private static int count = 0;
    private String isHealthy;

    public VitalSign() {
        count++;
        vitalSignID = count;
    }

    public int getVitalSignID() {
        return vitalSignID;
    }

    public void setVitalSignID(int vitalSignID) {
        this.vitalSignID = vitalSignID;
    }
    public void isHealthy(){
        if(hemoglobin.equals("Abnormal")||infection.equals("Yes")||diabetes.equals("Yes")||tempCondition.equals("Yes")||permCondition.equals("Yes")){
            isHealthy="No";
        }else{
            isHealthy="Yes";
        }
    }

    public String getBloodtype() {
        return bloodtype;

    }

    public void setBloodtype(String bloodtype) {
        this.bloodtype = bloodtype;
    }

    public String getHemoglobin() {
        return hemoglobin;
    }

    public void setHemoglobin(String hemoglobin) {
        this.hemoglobin = hemoglobin;
    }

    public String getInfection() {
        return infection;
    }

    public void setInfection(String infection) {
        this.infection = infection;
    }

    public String getDiabetes() {
        return diabetes;
    }

    public void setDiabetes(String diabetes) {
        this.diabetes = diabetes;
    }

    public String getTempCondition() {
        return tempCondition;
    }

    public void setTempCondition(String tempCondition) {
        this.tempCondition = tempCondition;
    }

    public String getPermCondition() {
        return permCondition;
    }

    public void setPermCondition(String permCondition) {
        this.permCondition = permCondition;
    }
    
    @Override
    public String toString(){
        return this.isHealthy;
    }

}
