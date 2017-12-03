package com.sirialkillers.shoponthego.Controllers;

import com.sirialkillers.shoponthego.Models.DiscountModel;
import com.sirialkillers.shoponthego.Models.OfferModel;
import com.sirialkillers.shoponthego.Models.ProductModel;
import com.sirialkillers.shoponthego.Models.ShopModel;
import com.sirialkillers.shoponthego.Position;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Ioakeim James Theologou
 * @version 19/10/2017
 *
 */
public class ShopControllerTest {
    private Position position;
    private MockMvc mockMvc;
    private List<ShopModel> shops;
    private List<DiscountModel> discounts;
    private List<OfferModel> offers;
    private List<ProductModel> products;

    @InjectMocks
    private ShopController shopController;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Before
    public void setUp() throws Exception {
        position = new Position(40.6657785,22.9468865);
        shopController = new ShopController();

        MockitoAnnotations.initMocks(this);

        shops = Arrays.asList(
                new ShopModel("404","Irish square", position),
                new ShopModel("333", "Coffee branch", position));

        discounts = Arrays.asList(
                new DiscountModel("202","404", 50.00,
                        "All shoes are on 50% discount",
                        "Only for a limited time"),
                new DiscountModel("304","202", 20.00,
                        "All sunglasses are on 20% discount",
                        "The discount will end tomorrow"));

        offers = Arrays.asList(
                new OfferModel("201", "33", "2+1 Burgers in KFC",
                        "Only for a limited time"),
                new OfferModel("401", "30", "2+1 Club Pita Gyros",
                        "Only for a limited time"));

        products = Arrays.asList(
                new ProductModel("34", "Chicken nuggets"),
                new ProductModel("32", "Coca Cola"));

    }

    @Test
    public void readOneShopAndCompareValues() throws Exception{
        mockMvc.perform(get("/shops"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(this.shops.get(0).getId())))
                .andExpect(jsonPath("$.name", is(this.shops.get(0).getName())))
                .andExpect(jsonPath("$.position", is(this.shops.get(0).getPosition())));
    }

    @Test
    public void readMultipleShopsAndCompareValues() throws Exception{
        mockMvc.perform(get("/shops"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$[0].id", is(this.shops.get(0).getId())))
                .andExpect(jsonPath("$[0].name", is(this.shops.get(0).getName())))
                .andExpect(jsonPath("$[0].position", is(this.shops.get(0).getPosition())))
                .andExpect(jsonPath("$[1].id", is(this.shops.get(1).getId())))
                .andExpect(jsonPath("$[1].name", is(this.shops.get(1).getName())))
                .andExpect(jsonPath("$[1].position", is(this.shops.get(1).getPosition())));
    }

    @Test
    public void readASingleDiscountFromAShopAndCompareValues() throws Exception{
        String shopId = "404";
        mockMvc.perform(get("shop/"+shopId+"/discounts"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.shopId",is(this.discounts.get(0).getShopId())))
                .andExpect(jsonPath("$.discountId",is(this.discounts.get(0).getDiscountId())))
                .andExpect(jsonPath("$.percentage",is(this.discounts.get(0).getPercentage())))
                .andExpect(jsonPath("$.title",is(this.discounts.get(0).getTitle())))
                .andExpect(jsonPath("$.description",is(this.discounts.get(0).getDescription())));


    }

    @Test
    public void readMultipleDiscountsFromAShopAndCompareValues() throws Exception{
        String shopId = "404";
        mockMvc.perform(get("shop/"+shopId+"/discounts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$[0].shopId",is(this.discounts.get(0).getShopId())))
                .andExpect(jsonPath("$[0].discountId",is(this.discounts.get(0).getDiscountId())))
                .andExpect(jsonPath("$[0].percentage",is(this.discounts.get(0).getPercentage())))
                .andExpect(jsonPath("$[0].title",is(this.discounts.get(0).getTitle())))
                .andExpect(jsonPath("$[0].description",is(this.discounts.get(0).getDescription())))
                .andExpect(jsonPath("$[1].shopId",is(this.discounts.get(1).getShopId())))
                .andExpect(jsonPath("$[1].discountId",is(this.discounts.get(1).getDiscountId())))
                .andExpect(jsonPath("$[1].percentage",is(this.discounts.get(1).getPercentage())))
                .andExpect(jsonPath("$[1].title",is(this.discounts.get(1).getTitle())))
                .andExpect(jsonPath("$[1].description",is(this.discounts.get(1).getDescription())));

    }

    @Test
    public void readASingleOfferFromAShopAndCompareValues() throws Exception{
        String shopId = "404";
        mockMvc.perform(get("shop/"+shopId+"/offers"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.shopId",is(this.offers.get(0).getShopId())))
                .andExpect(jsonPath("$.offerId",is(this.offers.get(0).getOfferId())))
                .andExpect(jsonPath("$.title",is(this.offers.get(0).getTitle())))
                .andExpect(jsonPath("$.description",is(this.offers.get(0).getDescription())));
    }

    @Test
    public void readMultipleOffersFromAShopAndCompareValues() throws Exception{
        String shopId = "404";
        mockMvc.perform(get("shop/"+shopId+"/offers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$[0].shopId",is(this.offers.get(0).getShopId())))
                .andExpect(jsonPath("$[0].offerId",is(this.offers.get(0).getOfferId())))
                .andExpect(jsonPath("$[0].title",is(this.offers.get(0).getTitle())))
                .andExpect(jsonPath("$[0].description",is(this.offers.get(0).getDescription())))
                .andExpect(jsonPath("$[1].shopId",is(this.offers.get(1).getShopId())))
                .andExpect(jsonPath("$[1].offerId",is(this.offers.get(1).getOfferId())))
                .andExpect(jsonPath("$[1].title",is(this.offers.get(1).getTitle())))
                .andExpect(jsonPath("$[1].description",is(this.offers.get(1).getDescription())));
    }

    @Test
    public void readASingleProductFromAShopAndCompareValues() throws Exception{
        String shopId = "404";
        mockMvc.perform(get("shop/"+shopId+"/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.productId",is(this.products.get(0).getProductId())))
                .andExpect(jsonPath("$.productName",is(this.products.get(0).getProductName())))
                .andExpect(jsonPath("$.quantityPerUnit",is(this.products.get(0).getQuantityPerUnit())))
                .andExpect(jsonPath("$.unitPrice",is(this.products.get(0).getUnitPrice())));
    }

    @Test
    public void readMultipleProductsFromAShopAndCompareValues() throws Exception{
        String shopId = "404";
        mockMvc.perform(get("shop/"+shopId+"/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$[0].productId",is(this.products.get(0).getProductId())))
                .andExpect(jsonPath("$[0].productName",is(this.products.get(0).getProductName())))
                .andExpect(jsonPath("$[0].quantityPerUnit",is(this.products.get(0).getQuantityPerUnit())))
                .andExpect(jsonPath("$[0].unitPrice",is(this.products.get(0).getUnitPrice())))
                .andExpect(jsonPath("$[1].productId",is(this.products.get(1).getProductId())))
                .andExpect(jsonPath("$[1].productName",is(this.products.get(1).getProductName())))
                .andExpect(jsonPath("$[1].quantityPerUnit",is(this.products.get(1).getQuantityPerUnit())))
                .andExpect(jsonPath("$[1].unitPrice",is(this.products.get(1).getUnitPrice())));
    }


    @Test
    public void checkThatShopIsCreated() throws Exception {
        ShopModel shop = new ShopModel("5", "Pizza Della Mamma", position);
        ShopModel shopThatIsCreated = shopController.create(shop);
        assertEquals(shopThatIsCreated.getId(), "5");
        assertEquals(shopThatIsCreated.getName(), "Pizza Della Mamma");
        assertEquals(shopThatIsCreated.getPosition(), position);

        //TODO: Instead of using toString() method, must use JSON type.
        /* this.mockMvc.perform(post("/shops")
                .contentType(contentType)
                .content((shop.toString())))
                .andExpect(status().isCreated()); */
    }


    @Test
    public void checkThatDiscountIsCreated() throws Exception{
        DiscountModel discount = new DiscountModel("504","690", 50.00,
                "All clothes are on 50% discount",
                "Only for a limited time");

        shopController.addShopDiscount("504", discount);
        DiscountModel discountThatWasCreated = shopController.getShopDiscount("504", "690");

        assertEquals(discountThatWasCreated.getShopId(), "504");
        assertEquals(discountThatWasCreated.getDiscountId(), "690");
        //TODO: fix this: assertEquals(discountThatWasCreated.getPercentage(), 50,00);
        assertEquals(discountThatWasCreated.getTitle(), "All clothes are on 50% discount");
        assertEquals(discountThatWasCreated.getDescription(), "Only for a limited time");
    }

    @Test
    public void checkThatOfferIsCreated() throws Exception{
        OfferModel offer = new OfferModel("205", "33", "2+1 iPhones X in Public",
                "Only for a limited time");

        shopController.addShopOffer("201", offer);
        OfferModel offerThatWasCreated = shopController.getShopOffer("205", "33");

        assertEquals(offerThatWasCreated.getShopId(), "201");
        assertEquals(offerThatWasCreated.getOfferId(), "33");
        assertEquals(offerThatWasCreated.getTitle(), "2+1 iPhones X in Public");
        assertEquals(offerThatWasCreated.getDescription(), "Only for a limited time");
    }

    @Test
    public void checkThatProductIsCreated() throws Exception{
        ProductModel product = new ProductModel("44", "Power Bank IO19");

        shopController.addShopProduct("22", product);
        ProductModel productThatWasCreated = shopController.getShopProduct("22", "44");

        assertEquals(productThatWasCreated.getProductId(), "44");
        assertEquals(productThatWasCreated.getProductName(), "Power Bank IO19");
    }

    @Test
    public void checkThatShopThatWasReturnedWasRight() throws Exception {
        ShopModel shop = shopController.getById("2");
        assertEquals(shop.getId(), "2");
    }

    @Test
    public void checkThatShopReturnedTheRightDiscount() throws Exception{
        DiscountModel discount = shopController.getShopDiscount("55", "34");
        assertEquals(discount.getShopId(), "55");
        assertEquals(discount.getDiscountId(), "34");
    }

    @Test
    public void checkThatShopReturnedTheRightOffer() throws Exception{
        OfferModel offer = shopController.getShopOffer("234", "3");
        assertEquals(offer.getShopId(), "234");
        assertEquals(offer.getOfferId(), "3");
    }

    @Test
    public void checkThatShopReturnedTheRightProduct() throws Exception{
        ProductModel product = shopController.getShopProduct("442", "42");
        assertEquals(product.getProductId(), "42");
    }

    @Test
    public void CheckThatShopGotUpdated() throws Exception {
        ShopModel newShop = new ShopModel("404","Irish square", position);
        shopController.create(newShop);

        ShopModel updatedShop = new ShopModel("404", "Irish house", position);
        shopController.update("404", updatedShop);

        ShopModel retrievedShop = shopController.getById("404");

        assertEquals(updatedShop.getName(), retrievedShop.getName());
        assertNotEquals(newShop.getName(), retrievedShop.getName());
    }

    @Test
    public void checkThatDiscountGotUpdated() throws Exception{
        DiscountModel newDiscount = new DiscountModel("504","690", 50.00,
                "All clothes are on 50% discount",
                "Only for a limited time");
        shopController.addShopDiscount("504", newDiscount);

        DiscountModel updatedDiscount = new DiscountModel("504","690", 60.00,
                "All face wear are on 50% discount",
                "Only for a limited time");
        shopController.updateShopDiscount("504", "690", updatedDiscount);

        DiscountModel retrievedDiscount = shopController.getShopDiscount("504", "690");

        assertEquals(updatedDiscount.getTitle(), retrievedDiscount.getTitle());
        assertNotEquals(newDiscount.getTitle(), retrievedDiscount.getTitle());
    }

    @Test
    public void checkThatOfferGotUpdated() throws Exception{
        OfferModel newOffer = new OfferModel("201", "33", "2+1 Burgers in KFC",
                "Only for a limited time");
        shopController.addShopOffer("201", newOffer);

        OfferModel updatedOffer = new OfferModel("201", "33", "2+2 Burgers in KFC",
                "Only for a limited time");

        OfferModel retrievedOffer = shopController.getShopOffer("201", "33");

        assertEquals(updatedOffer.getTitle(), retrievedOffer.getTitle());
        assertNotEquals(newOffer.getTitle(), retrievedOffer.getTitle());
    }

    @Test
    public void checkThatProductGotUpdated() throws Exception{
        ProductModel newProduct = new ProductModel("54", "iPhone X");
        shopController.addShopProduct("33", newProduct);

        ProductModel updatedProduct = new ProductModel("54", "iPhone 8");
        shopController.updateShopProduct("33", "54", updatedProduct);

        ProductModel retrievedProduct = shopController.getShopProduct("33", "54");

        assertEquals(updatedProduct.getProductName(), retrievedProduct.getProductName());
        assertNotEquals(newProduct.getProductName(), retrievedProduct.getProductName());
    }

    @Test
    public void checkThatShopGotDeleted() throws Exception {
        ShopModel shop = new ShopModel("404","Irish square", position);
        shopController.create(shop);

        shopController.delete("404");
        assertNull(shopController.getById("404"));
    }

    @Test
    public void checkThatDiscountGotDeleted(){
        DiscountModel discount = new DiscountModel("504","690", 50.00,
                "All clothes are on 50% discount",
                "Only for a limited time");
        shopController.addShopDiscount("504", discount);

        shopController.deleteShopDiscount("504", "690");
        assertNull(shopController.getShopDiscount("504", "690"));
    }

    @Test
    public void checkThatOfferGotDeleted(){
        OfferModel offer = new OfferModel("205", "33", "2+1 iPhones X in Public",
                "Only for a limited time");
        shopController.addShopOffer("205", offer);

        shopController.deleteShopOffer("205", "33");
        assertNull(shopController.getShopOffer("205", "33"));
    }

    @Test
    public void checkThatProductGotDeleted(){
        ProductModel product = new ProductModel("54", "iPhone X");
        shopController.addShopProduct("54", product);

        shopController.deleteShopProduct("54","54");
        assertNull(shopController.getShopProduct("54", "54"));
    }

    @After
    public void tearDown() throws Exception {
    //TODO create a tearDown structure.
    }
}