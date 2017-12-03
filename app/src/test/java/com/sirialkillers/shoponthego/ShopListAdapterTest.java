package com.sirialkillers.shoponthego;

import android.content.Context;

import com.sirialkillers.shoponthego.Models.ShopModel;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by User on 04-Nov-17.
 */

public class ShopListAdapterTest {

    private ShopListAdapter shopListAdapter;
    private ArrayList<ShopModel> shops;

    @Before
    public void setUp() throws Exception{
        Context context = Mockito.mock(Context.class);
        populateListOfShops();
        shopListAdapter =new ShopListAdapter(context, shops);
    }

    private void populateListOfShops()
    {
        shops= new ArrayList<>();
        shops.add(new ShopModel("1","rafaele", new Position(40.6657785,22.9468865)));
        shops.add(new ShopModel("2","mavidis", new Position(40.6666259,22.9455427)));
        shops.add(new ShopModel("3","porkys", new Position(40.663449,22.9475822)));
        shops.add(new ShopModel("3","seven", new Position(40.6595399,22.9445063)));
        shops.add(new ShopModel("4", "mad gym",new Position(40.6566813,22.9328894)));
    }
    @Test
    public void shouldNotBeNull() throws Exception
    {
        assertNotNull(shopListAdapter);
    }

    @Test
    public void getCount_ReturnCorrectCount() throws Exception
    {
        assertEquals( shopListAdapter.getCount(), shops.size() );
    }


    @Test
    public void getShop_ReturnCorrectShop() throws Exception
    {
        for ( int index = 0; index < shops.size(); index++ )
        {
            assertEquals( shopListAdapter.getItem( index ),
                    shops.get( index ) );
        }
    }

    @Test
    public void getItemId_shouldReturnProperItemId() throws Exception
    {
        for ( int index = 0; index < shops.size(); index++ )
        {
            assertEquals( shopListAdapter.getItemId( index ), index );
        }
    }



}
