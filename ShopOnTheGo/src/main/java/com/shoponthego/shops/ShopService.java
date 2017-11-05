package com.shoponthego.shops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ShopService {

	private List<Shop> shops = new ArrayList<>(Arrays.asList(
			new Shop("1213","Adidas Store"),
			new Shop("2321","Nike Store"),
			new Shop("3421","Bershka"),
			new Shop("4555","H&M")
			));
	
	public List<Shop> getAllShops() {
		return shops;
	}
	
	public Shop getShopById(String id) {
		for (Shop shop : shops) {
			if(shop.getId().equalsIgnoreCase(id)) {
				return shop;
			}
		}
		return null;
	}
	
	public void addShop(Shop shop) {
		shops.add(shop);
	}

	public void updateShop(String id, Shop updatedShop) {
		for (int i=0; i<shops.size(); i++) {
			Shop shop = shops.get(i);
			if(shop.getId().equalsIgnoreCase(id)) {
				shops.set(i, updatedShop);
				return;
			}
		}		
	}

	public void deleteShop(String id) {
		for (int i=0; i<shops.size(); i++) {
			Shop shop = shops.get(i);
			if(shop.getId().equalsIgnoreCase(id)) {
				shops.remove(shop);
				return;
			}
		}
	}
}