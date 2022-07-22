/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Shift;

/**
 *
 * @author User
 */
public class ShiftBean implements java.io.Serializable{
    private String title;
    private String fromtime;
    private String totime;
    private String desc;
    
    public ShiftBean(){
        
    }

    public ShiftBean(String title, String fromtime, String totime, String desc) {
        this.title = title;
        this.fromtime = fromtime;
        this.totime = totime;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFromtime() {
        return fromtime;
    }

    public void setFromtime(String fromtime) {
        this.fromtime = fromtime;
    }

    public String getTotime() {
        return totime;
    }

    public void setTotime(String totime) {
        this.totime = totime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
}
