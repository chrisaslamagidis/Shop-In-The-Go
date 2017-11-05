package com.shoponthego.shops;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShopController {
	
	@Autowired
	private ShopService shopService;
	
	@RequestMapping("/shops")
	public List<Shop> getAllShops() {
		return shopService.getAllShops();
	}
	
	@RequestMapping("/shops/{id}")
	public Shop getShopById(@PathVariable String id) {
		return shopService.getShopById(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/shops")
	public void addShop(@RequestBody Shop shop) {
		shopService.addShop(shop);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/shops/{id}")
	public void updateShop(@RequestBody Shop shop, @PathVariable String id) {
		shopService.updateShop(id, shop);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/shops/{id}")
	public void deleteShop(@PathVariable String id) {
		shopService.deleteShop(id);
	}
}
