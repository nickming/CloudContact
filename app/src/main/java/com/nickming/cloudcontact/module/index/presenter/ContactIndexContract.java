package com.nickming.cloudcontact.module.index.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;

import com.nickming.cloudcontact.module.BasePresenter;
import com.nickming.cloudcontact.module.BaseView;
import com.nickming.cloudcontact.module.index.data.PersonInfo;

import java.util.List;

/**
 * Desc:
 * Author:nickming
 * Date:16/5/17
 * Time:14:24
 * E-mail:962570483@qq.com
 */
public interface ContactIndexContract {

    interface View extends BaseView<Presenter>
    {
        Snackbar showSnackBar(String msg);

        void showShortSnackBar(String msg);

        void showContactList(List<PersonInfo> personInfoList);
    }

    interface Presenter extends BasePresenter
    {
        void loadLocalContactData();

        void requestPermission(Activity activity);

        void uploadContactData(Context context);

        void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);

        void onActivityResult(int requestCode, int resultCode, Intent data);
    }
}
