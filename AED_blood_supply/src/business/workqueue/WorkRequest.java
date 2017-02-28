/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.workqueue;

import business.blood.Blood;
import business.organization.Organization;
import business.useraccount.UserAccount;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Jiamin.S <shang.j@husky.neu.edu>
 */
public class WorkRequest {

    private String message;
    private UserAccount sender;
    private UserAccount receiver;
    private String status;
    private Date requestDate;
    private Date resolveDate;
    private int quantity;
    private Blood blood;
    private Organization destination;
    private ArrayList<Blood> useBloodList;
    private static int requestcount = 0;
    private  int requestId = 1;


    public WorkRequest(Organization organ) {
        destination = organ;
        useBloodList = new ArrayList<>();
        requestcount++;
        requestId +=requestcount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public UserAccount getSender() {
        return sender;
    }

    public void setSender(UserAccount sender) {
        this.sender = sender;
    }

    public int getQuantity() {
        return quantity;
    }

    public Organization getDestination() {
        return destination;
    }

    public void setDestination(Organization destination) {
        this.destination = destination;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ArrayList<Blood> getUseBloodList() {
        return useBloodList;
    }

    public void setUseBloodList(ArrayList<Blood> useBloodList) {
        this.useBloodList = useBloodList;
    }

    public Blood getBlood() {
        return blood;
    }

    public void setBlood(Blood blood) {
        this.blood = blood;
    }

    public UserAccount getReceiver() {
        return receiver;
    }

    public void setReceiver(UserAccount receiver) {
        this.receiver = receiver;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }
    
    public int quanOfList(){
        int amount = 0;
        for(Blood blood : useBloodList){
                amount += blood.getVolum();
        }
        return amount;
    }

    public Date getResolveDate() {
        return resolveDate;
    }

    public void setResolveDate(Date resolveDate) {
        this.resolveDate = resolveDate;
    }

    @Override
    public String toString() {
        return status;
    }


}
