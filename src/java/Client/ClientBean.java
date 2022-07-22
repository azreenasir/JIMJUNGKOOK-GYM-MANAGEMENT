/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

/**
 *
 * @author azree
 */
public class ClientBean implements java.io.Serializable {
    private String firstname;
    private String lastname;
    private String dob;
    private int phonenum;
    private String gender;
    private String address;
    public ClientBean() {
    }

    public ClientBean(String firstname, String lastname, String dob, int phonenum, String gender, String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.phonenum = phonenum;
        this.gender = gender;
        this.address = address;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(int phonenum) {
        this.phonenum = phonenum;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
}
