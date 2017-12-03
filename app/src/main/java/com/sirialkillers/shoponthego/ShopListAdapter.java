package com.sirialkillers.shoponthego;

/**
 * Created by User on 01-Nov-17.
 */

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.view.View;

import com.sirialkillers.shoponthego.Models.ShopModel;

public class ShopListAdapter extends BaseAdapter implements Filterable{
    private Context c;
    private List<ShopModel> shops;
    private CustomFilter filter;
    private List<ShopModel> filterList;

    ShopListAdapter(Context ctx, List<ShopModel> shops) {
        // TODO Auto-generated constructor stub
        this.c=ctx;
        this.shops=shops;
        this.filterList=shops;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return shops.size();
    }
    @Override
    public Object getItem(int pos) {
        // TODO Auto-generated method stub
        return shops.get(pos);
    }
    @Override
    public long getItemId(int pos) {
        // TODO Auto-generated method stub
        return shops.indexOf(getItem(pos));
    }
    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater=(LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.adapter_layout, parent, false);
        }
        TextView nameTxt= (TextView) convertView.findViewById(R.id.nameTextView);
        //SET DATA TO THEM
        nameTxt.setText(shops.get(pos).getName());
        return convertView;
    }
    @Override
    public Filter getFilter() {
        // TODO Auto-generated method stub
        if(filter == null)
        {
            filter=new CustomFilter();
        }
        return filter;
    }
    //INNER CLASS
    class CustomFilter extends Filter
    {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            // TODO Auto-generated method stub
            FilterResults results=new FilterResults();
            if(constraint != null && constraint.length()>0)
            {
                //CONSTRAINT TO UPPER
                constraint=constraint.toString().toUpperCase();
                ArrayList<ShopModel> filters= new ArrayList<>();
                //get specific items
                for(int i=0;i<filterList.size();i++)
                {
                    if(filterList.get(i).getName().toUpperCase().contains(constraint))
                    {
                        ShopModel s=new ShopModel(filterList.get(i).getId(),filterList.get(i).getName(), filterList.get(i).getPosition());
                        filters.add(s);
                    }
                }
                results.count=filters.size();
                results.values=filters;
            }else
            {
                results.count=filterList.size();
                results.values=filterList;
            }
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            // TODO Auto-generated method stub
            shops=(ArrayList<ShopModel>) results.values;
            notifyDataSetChanged();
        }
    }
}