package com.sirialkillers.shoponthego;

import com.sirialkillers.shoponthego.Models.ShopModel;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by User on 18-Nov-17.
 */

public class ListOfShopsTest {

    private ArrayList<ShopModel> mockShops;
    ListOfShops listOfShops;

    @Before
    public void setUp() throws Exception{
        populateListOfShops();
        listOfShops = new ListOfShops();
    }

    private void populateListOfShops()
    {
        mockShops= new ArrayList<>();
        mockShops.add(new ShopModel("1","rafaele", new Position(40.6657785,22.9468865)));
        mockShops.add(new ShopModel("2","mavidis", new Position(40.6666259,22.9455427)));
        mockShops.add(new ShopModel("3","porkys", new Position(40.663449,22.9475822)));
        mockShops.add(new ShopModel("3","seven", new Position(40.6595399,22.9445063)));
        mockShops.add(new ShopModel("4", "mad gym",new Position(40.6566813,22.9328894)));
    }

    @Test
    public void testFindCorrectShopsWithValidTitle() throws Exception{
        String title="rafaele";
        String returnedId;
        returnedId=listOfShops.findCorrectShop(title, mockShops);
        assertEquals("1", returnedId);
    }

    @Test
    public void testFindCorrectShopWithInvalidTitle() throws Exception{
        String title="adsada";
        String returnedId;
        returnedId=listOfShops.findCorrectShop(title, mockShops);
        assertEquals("Not Found", returnedId);
    }

    @Test
    public void testFindCorrectShopWithNullTitle() throws Exception{
        String title="null";
        String returnedId;
        returnedId=listOfShops.findCorrectShop(title,mockShops);
        assertEquals("Not Found", returnedId);
    }

}
