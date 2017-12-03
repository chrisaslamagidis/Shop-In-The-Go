package com.sirialkillers.shoponthego;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sirialkillers.shoponthego.Models.DiscountModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 16-Nov-17.
 */

public class DiscountListViewAdapter extends BaseAdapter{
    private Context c;
    private List<DiscountModel> discounts;


    public DiscountListViewAdapter(Context ctx, List<DiscountModel> discounts) {
        this.c = ctx;
        this.discounts = discounts;
    }

    @Override
    public int getCount() {
        return discounts.size();
    }

    @Override
    public Object getItem(int pos) {
        return discounts.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return discounts.indexOf(getItem(pos));
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView==null){
            convertView=inflater.inflate(R.layout.adapter_layout, parent, false);
        }
        TextView nameTxt= (TextView) convertView.findViewById(R.id.nameTextView);
        //Needs implementation of DiscountModel
        nameTxt.setText(discounts.get(pos).getTitle());
        return convertView;
    }
}
