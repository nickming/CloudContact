package com.nickming.cloudcontact.module.index.data;

import cn.bmob.v3.BmobObject;

/**
 * Desc:
 * Author:nickming
 * Date:16/5/17
 * Time:23:48
 * E-mail:962570483@qq.com
 */
public class PersonInfo extends BmobObject {

    private Integer contactId;
    private String displayName;
    private String phoneNum;
    private String sortKey;
    private Long photoId;
    private String lookUpKey;
    private Integer selected = 0;
    private String formattedNumber;
    private String pinyin;

    public PersonInfo() {
        this.setTableName("T_contact_list");
    }


    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getSortKey() {
        return sortKey;
    }

    public void setSortKey(String sortKey) {
        this.sortKey = sortKey;
    }

    public long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(long photoId) {
        this.photoId = photoId;
    }

    public String getLookUpKey() {
        return lookUpKey;
    }

    public void setLookUpKey(String lookUpKey) {
        this.lookUpKey = lookUpKey;
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
    }

    public String getFormattedNumber() {
        return formattedNumber;
    }

    public void setFormattedNumber(String formattedNumber) {
        this.formattedNumber = formattedNumber;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

}

