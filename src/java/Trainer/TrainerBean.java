package Trainer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class TrainerBean implements java.io.Serializable{
    private String firstname;
    private String lastname;
    private String dob;
    private int phonenum;
    private String gender;
    private String email;
    private int shiftId;

    public TrainerBean() {
    }

    public TrainerBean(String firstname, String lastname, String dob, int phonenum, String gender, String email,int shiftId) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.dob = dob;
        this.phonenum = phonenum;
        this.gender = gender;
        this.email = email;
        this.shiftId = shiftId;
    }

    public int getShiftId() {
        return shiftId;
    }

    public void setShiftId(int shiftId) {
        this.shiftId = shiftId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
