package com.nickming.cloudcontact.module.index.data;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.nickming.cloudcontact.application.MyApplication;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.listener.SaveListener;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Desc:
 * Author:nickming
 * Date:16/5/17
 * Time:14:52
 * E-mail:962570483@qq.com
 */
public class ContactRepository implements ContactDataSource {

    private List<PersonInfo> mPersonList = new ArrayList<>();

    /**
     * 饿汉式单例,增强了性能
     */
    private static class InstanceHolder {
        private static ContactRepository repository = new ContactRepository();
    }

    public static ContactRepository getInstance() {
        return InstanceHolder.repository;
    }

    @Override
    public Observable<List<PersonInfo>> getLocalPersonList() {
        return Observable.create(new Observable.OnSubscribe<List<PersonInfo>>() {
            @Override
            public void call(final Subscriber<? super List<PersonInfo>> subscriber) {
                ContentResolver resolver = MyApplication.getmContext().getContentResolver();
                Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                String[] projection = {ContactsContract.CommonDataKinds.Phone._ID, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                        ContactsContract.CommonDataKinds.Phone.DATA, "sort_key", ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
                        ContactsContract.CommonDataKinds.Phone.PHOTO_ID, ContactsContract.CommonDataKinds.Phone.LOOKUP_KEY};

                Cursor cursor = resolver.query(uri, projection, null, null, "sort_key COLLATE LOCALIZED asc");
                if (cursor != null && cursor.getCount() > 0) {
                    mPersonList.clear();
                    cursor.moveToFirst();
                    for (int i = 0; i < cursor.getCount(); i++) {
                        cursor.moveToPosition(i);
                        String name = cursor.getString(1);
                        String number = cursor.getString(2);
                        String sortKey = cursor.getString(3);
                        int contactId = cursor.getInt(4);
                        Long photoId = cursor.getLong(5);
                        String lookUpKey = cursor.getString(6);

                        PersonInfo info = new PersonInfo();
                        info.setDisplayName(name);
                        info.setPhoneNum(number);
                        info.setSortKey(sortKey);
                        info.setContactId(contactId);
                        info.setPhotoId(photoId);
                        info.setLookUpKey(lookUpKey);
                        mPersonList.add(info);
                    }
                    cursor.close();
                }
                subscriber.onNext(mPersonList);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<Boolean> uploadContactListToBmob(final Context context, final List<PersonInfo> personInfoList) {
        return Observable.from(personInfoList)
                .flatMap(new Func1<PersonInfo, Observable<Boolean>>() {
                    @Override
                    public Observable<Boolean> call(final PersonInfo info) {
                        return Observable.create(new Observable.OnSubscribe<Boolean>() {
                            @Override
                            public void call(final Subscriber<? super Boolean> subscriber) {
                                info.save(context, new SaveListener() {
                                    @Override
                                    public void onSuccess() {
                                        subscriber.onNext(true);
                                        subscriber.onCompleted();
                                    }

                                    @Override
                                    public void onFailure(int i, String s) {
                                        subscriber.onError(new Throwable(s));
                                    }
                                });
                            }
                        }).subscribeOn(Schedulers.io());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread());
    }


}
