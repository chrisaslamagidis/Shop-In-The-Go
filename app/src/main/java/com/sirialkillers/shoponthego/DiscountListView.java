package com.sirialkillers.shoponthego;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.sirialkillers.shoponthego.Models.DiscountModel;
import java.util.List;

/**
 * Created by User on 16-Nov-17.
 */

public class DiscountListView extends AppCompatActivity{
    ListView lv;
    TextView tv;
    String message;
    String shopName;
    ListOfDiscounts LoD = new ListOfDiscounts();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.discounts_list_view);
        Intent i = getIntent();
        shopName=i.getStringExtra("name");
        message = i.getStringExtra("message");
        final List<DiscountModel> discountList;
        //generate discount arraylist for specific shop(recognised by it's id)
        LoD.addDiscounts(message);
        discountList=LoD.getDiscountlist();
        //get name of shop for the textview
        tv= (TextView) findViewById(R.id.shopNameTextView);
        tv.setText(shopName);
        final DiscountListViewAdapter adapter= new DiscountListViewAdapter(this, discountList);
        lv= (ListView) findViewById(R.id.discountListView);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                DiscountModel selectedDiscount= discountList.get(pos);
                Intent discountdetailsIntent=new Intent(getApplicationContext(), DiscountDetailsActivity.class);
                //Complete extras when DiscountModel is implemented
                discountdetailsIntent.putExtra("discount", (Parcelable) selectedDiscount);

                startActivity(discountdetailsIntent);
            }
        });


    }

}
