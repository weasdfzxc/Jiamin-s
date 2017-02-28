/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.workqueue;

import java.util.ArrayList;

/**
 *
 * @author Jiamin.S <shang.j@husky.neu.edu>
 */
public class WorkQueue {

    private ArrayList<WorkRequest> workReqestList;

    public WorkQueue() {
        workReqestList = new ArrayList();
    }

    public ArrayList<WorkRequest> getWorkReqestList() {
        return workReqestList;
    }

}
