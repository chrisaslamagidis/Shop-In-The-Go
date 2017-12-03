package com.sirialkillers.shoponthego;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

import com.sirialkillers.shoponthego.Models.ShopModel;

import java.util.ArrayList;
import java.util.List;

public class ShopsListView extends AppCompatActivity{
    ListView lv;
    SearchView sv;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shops_list_view);
        ListOfShops LoS = new ListOfShops();
        LoS.addShop();
        lv= (ListView) findViewById(R.id.shopsListView);
        sv= (SearchView) findViewById(R.id.shopsSearchView);
        List<ShopModel> shopsList;
        shopsList=LoS.getShop();
        //ADAPTER
        final ShopListAdapter adapter=new ShopListAdapter(this, shopsList);
        lv.setAdapter(adapter);
        sv.setOnQueryTextListener(new OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                adapter.getFilter().filter(query);
                return false;
            }

        });


    }
}
