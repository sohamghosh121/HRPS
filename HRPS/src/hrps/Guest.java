/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hrps;

import java.io.Serializable;
import java.util.Scanner;

/**
 *
 * @author Soham G
 */
public class Guest implements Serializable{
    public static final int MALE = 1;
    public static final int FEMALE = 2;

    private String name;
    private String address;
    private int gender;
    private String passportNumber;
    private String nationality;
    private String contact;//email ID

    public Guest()
    {

    }
    public Guest (String n, String a, int g, String pp, String nat, String c)
    {
        name = n;
        address = a;
        gender = g;
        passportNumber = pp;
        nationality = nat;
        contact = c;
    }


    public void showGuest()
    {
        System.out.println("Name: "+name+"\tPassport: "+passportNumber+"\t\tContact: "+contact);
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }



}
