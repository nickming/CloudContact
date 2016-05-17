package com.nickming.cloudcontact.module;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Desc:
 * Author:nickming
 * Date:16/5/17
 * Time:13:58
 * E-mail:962570483@qq.com
 */
public class BaseActivity extends AppCompatActivity{

    protected ProgressDialog mProgressBar;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mProgressBar=new ProgressDialog(this);

    }

    protected void showToast(String msg)
    {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    protected void showProgress()
    {
        if (mProgressBar!=null&&!mProgressBar.isShowing())
            mProgressBar.show();
    }

    protected void hideProgress()
    {
        if (mProgressBar!=null&&mProgressBar.isShowing())
        {
            mProgressBar.hide();
        }
    }

}
