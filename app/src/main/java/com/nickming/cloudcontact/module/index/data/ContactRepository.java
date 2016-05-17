package com.nickming.cloudcontact.module.index.data;

/**
 * Desc:
 * Author:nickming
 * Date:16/5/17
 * Time:14:52
 * E-mail:962570483@qq.com
 */
public class ContactRepository implements ContactDataSource{

    /**
     * 饿汉式单例,增强了性能
     */
    private static class InstanceHolder
    {
        private static ContactRepository repository=new ContactRepository();
    }

    public static ContactRepository getInstance()
    {
        return InstanceHolder.repository;
    }


}
