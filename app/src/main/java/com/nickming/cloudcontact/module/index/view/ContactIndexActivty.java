package com.nickming.cloudcontact.module.index.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.nickming.cloudcontact.R;
import com.nickming.cloudcontact.module.BaseActivity;
import com.nickming.cloudcontact.module.index.data.PersonInfo;
import com.nickming.cloudcontact.module.index.presenter.ContactIndexContract;
import com.nickming.cloudcontact.module.index.presenter.ContactIndexPresenter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ContactIndexActivty extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, ContactIndexContract.View {

    private ContactIndexContract.Presenter mPresenter;
    private FloatingActionButton fab;
    @Bind(R.id.recycleview_contact)
    RecyclerView mRecycleView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_index_activty);
        ButterKnife.bind(this);
        initViews();

    }

    private void initViews()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPresenter = new ContactIndexPresenter(this);

        mPresenter.requestPermission(this);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> {});


        initDrawerAndNavigationbar(toolbar);

        initRecycleview();

    }

    private void initRecycleview() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecycleView.setLayoutManager(linearLayoutManager);
        mPresenter.loadLocalContactData();
    }

    private void initDrawerAndNavigationbar(Toolbar toolbar) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.contact_index_activty, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void setPresenter(ContactIndexContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mPresenter.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mPresenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public Snackbar showSnackBar(String msg) {
        return Snackbar.make(fab, msg, Snackbar.LENGTH_INDEFINITE);
    }

    @Override
    public void showShortSnackBar(String msg) {
        Snackbar.make(fab, msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showContactList(List<PersonInfo> personInfoList) {
        mRecycleView.setAdapter(new ContactListAdapter(personInfoList,this));
    }

}
