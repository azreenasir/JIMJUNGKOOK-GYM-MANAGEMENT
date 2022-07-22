/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Payment;

/**
 *
 * @author azree
 */
public class PaymentBean implements java.io.Serializable {
    private double amount;
    private String desc;
    private String date;
    private int pckg;
    private int client;

    public PaymentBean() {
    }

    public PaymentBean(double amount, String desc, String date, int pckg, int client) {
        this.amount = amount;
        this.desc = desc;
        this.date = date;
        this.pckg = pckg;
        this.client = client;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPckg() {
        return pckg;
    }

    public void setPckg(int pckg) {
        this.pckg = pckg;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }
    
    
    
}
