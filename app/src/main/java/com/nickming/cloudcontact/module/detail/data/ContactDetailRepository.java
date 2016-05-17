package com.nickming.cloudcontact.module.detail.data;

/**
 * Desc:
 * Author:nickming
 * Date:16/5/17
 * Time:21:27
 * E-mail:962570483@qq.com
 */
public class ContactDetailRepository implements ContactDetailDataSource{

    private static class InstanceHolder{
        static ContactDetailRepository mInstance=new ContactDetailRepository();
    }

    public static ContactDetailRepository getInstance()
    {
        return InstanceHolder.mInstance;
    }
}
