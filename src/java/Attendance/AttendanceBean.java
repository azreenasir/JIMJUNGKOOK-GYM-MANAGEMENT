/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Attendance;

/**
 *
 * @author azree
 */
public class AttendanceBean implements java.io.Serializable{
    private String date;
    private String desc;
    private int clientid;

    public AttendanceBean() {
    }

    public AttendanceBean(String date, String desc, int clientid) {
        this.date = date;
        this.desc = desc;
        this.clientid = clientid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getClientid() {
        return clientid;
    }

    public void setClientid(int clientid) {
        this.clientid = clientid;
    }
    
}
