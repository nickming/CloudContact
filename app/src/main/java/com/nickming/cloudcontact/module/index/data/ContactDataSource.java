package com.nickming.cloudcontact.module.index.data;

import android.content.Context;

import java.util.List;

import rx.Observable;

/**
 * Desc:
 * Author:nickming
 * Date:16/5/17
 * Time:14:50
 * E-mail:962570483@qq.com
 */
public interface ContactDataSource {

    Observable<List<PersonInfo>> getLocalPersonList();

    Observable<Boolean> uploadContactListToBmob(Context context,List<PersonInfo> personInfoList);


}
