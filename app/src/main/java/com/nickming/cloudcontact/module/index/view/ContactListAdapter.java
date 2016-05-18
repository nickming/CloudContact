package com.nickming.cloudcontact.module.index.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nickming.cloudcontact.R;
import com.nickming.cloudcontact.module.index.data.PersonInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Desc:
 * Author:nickming
 * Date:16/5/19
 * Time:01:38
 * E-mail:962570483@qq.com
 */
public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ContactViewHolder>{

    private List<PersonInfo> mDatas=new ArrayList<>();
    private Context context;

    public ContactListAdapter(List<PersonInfo> mDatas, Context context) {
        this.mDatas = mDatas;
        this.context = context;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ContactViewHolder(LayoutInflater.from(context).inflate(R.layout.view_contact_index_item,parent,false));
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        holder.name.setText(mDatas.get(position).getDisplayName());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;

        public ContactViewHolder(View itemView) {
            super(itemView);
            name= (TextView) itemView.findViewById(R.id.tv_contact_index_name);
        }
    }
}
