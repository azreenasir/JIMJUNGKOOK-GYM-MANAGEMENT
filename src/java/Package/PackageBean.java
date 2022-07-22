/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Package;

/**
 *
 * @author azree
 */
public class PackageBean implements java.io.Serializable {
    private String title;
    private double fee;
    private String desc;

    public PackageBean() {
    }

    public PackageBean(String title, double fee, String desc) {
        this.title = title;
        this.fee = fee;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    
    
}
