package com.nickming.cloudcontact.module.index.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import com.nickming.cloudcontact.common.util.show.L;
import com.nickming.cloudcontact.constant.PermissionConstant;
import com.nickming.cloudcontact.module.index.data.ContactRepository;
import com.nickming.cloudcontact.module.index.data.PersonInfo;

import java.util.List;

import cn.bmob.v3.helper.PermissionListener;
import cn.bmob.v3.helper.PermissionManager;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Desc:
 * Author:nickming
 * Date:16/5/17
 * Time:14:26
 * E-mail:962570483@qq.com
 */
public class ContactIndexPresenter implements ContactIndexContract.Presenter {

    private static final String TAG = ContactIndexPresenter.class.getSimpleName();

    protected ContactRepository mRepository;

    protected PermissionManager mPermissionHelper;

    protected ContactIndexContract.View mView;

    public ContactIndexPresenter(ContactIndexContract.View view) {
        this.mView = view;
        mRepository = ContactRepository.getInstance();
    }

    @Override
    public void start() {

    }

    @Override
    public void loadLocalContactData() {
        mRepository.getLocalPersonList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<PersonInfo>>() {
                    @Override
                    public void call(List<PersonInfo> personInfos) {
                        mView.showShortSnackBar("" + personInfos.size());
                    }
                });
    }

    @Override
    public void requestPermission(Activity activity) {
        mPermissionHelper = PermissionManager.with(activity)
                .addRequestCode(PermissionConstant.RQEUST_CONTACT_PERMISSION)
                .permissions("android.permission.READ_CONTACTS")
                .setPermissionsListener(new PermissionListener() {
                    @Override
                    public void onGranted() {
                        L.i(TAG, "onGranted: read contact yes!");
                    }

                    @Override
                    public void onDenied() {
                        L.i(TAG, "onDenied: read contact no!");
                    }

                    @Override
                    public void onShowRationale(String[] strings) {
                        //当用户拒绝某权限时并点击`不再提醒`的按钮时，
                        // 下次应用再请求该权限时，需要给出合适的响应
                        // （比如,给个展示对话框来解释应用为什么需要该权限）
                        mView.showSnackBar("需要通讯录权限").setAction("ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //必须调用该`setIsPositive(true)`方法
                                mPermissionHelper.setIsPositive(true);
                                mPermissionHelper.request();
                            }
                        }).show();

                    }
                }).request();
    }

    @Override
    public void uploadContactData(final Context context) {
        mRepository.getLocalPersonList()
                .subscribe(new Action1<List<PersonInfo>>() {
                    @Override
                    public void call(List<PersonInfo> personInfoList) {
                        mRepository.uploadContactListToBmob(context, personInfoList)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new Action1<Boolean>() {
                                    @Override
                                    public void call(Boolean aBoolean) {
                                        if (aBoolean)
                                            mView.showShortSnackBar("上传成功!");
                                        else
                                            mView.showShortSnackBar("上传失败!");
                                    }
                                });
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PermissionConstant.RQEUST_CONTACT_PERMISSION:
                mPermissionHelper.onPermissionResult(permissions, grantResults);
                break;
            default:
                break;
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
