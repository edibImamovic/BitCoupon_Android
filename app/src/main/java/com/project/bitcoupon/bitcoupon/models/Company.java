package com.project.bitcoupon.bitcoupon.models;

/**
 * Created by Sanela on 4/22/2015.
 */
public class Company {
    private int mId;
    private String mName;
    private String mEmail;
    private String mAddress;
    private String mCity;
    private String mContact;

    private String mLogo;

    /**
     * COnstructor
     * @param id
     * @param name
     * @param email
     * @param address
     * @param city
     * @param contact
     * @param logo
     */
    public Company(int id, String name, String email, String address, String city, String contact, String logo) {
        this.mId = id;
        this.mName = name;
        this.mEmail = email;
        this.mAddress = address;
        this.mCity = city;
        this.mContact = contact;
        this.mLogo = logo;
    }

    /**
     * Constructor
     * @param id
     * @param name
     * @param logo
     */
    public Company(int id, String name, String logo){
        this.mId = id;
        this.mName = name;
        this.mLogo = logo;

    }

    /**
     * Constructor
     * @param id
     * @param name
     * @param email
     * @param logo
     */
    public Company(int id, String name,String email, String logo){
        this.mId = id;
        this.mName = name;
        this.mLogo = logo;
        this.mEmail = email;
    }

    @Override
    public String toString() {
        return "Company{" +
                "mId=" + mId +
                ", mName='" + mName + '\'' +
                ", mEmail='" + mEmail + '\'' +
                ", mAddress='" + mAddress + '\'' +
                ", mCity='" + mCity + '\'' +
                ", mContact='" + mContact + '\'' +
                '}';
    }

    /**
     * Getter and seteri
     * @return
     */
    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmCity() {
        return mCity;
    }

    public void setmCity(String mCity) {
        this.mCity = mCity;
    }

    public String getmContact() {
        return mContact;
    }

    public void setmContact(String mContact) {
        this.mContact = mContact;
    }

    public String getmLogo() {
        return mLogo;
    }

    public void setmLogo(String mLogo) {
        this.mLogo = mLogo;
    }
}

