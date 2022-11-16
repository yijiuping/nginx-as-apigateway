package com.nginx.controller;

import com.nginx.model.Inventory;
import com.nginx.model.Item;
import com.nginx.model.Price;
import com.nginx.model.Sku;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <p>SampleData</p>
 *
 * @author Jiuping Yi
 */
public class SampleData {

    private static final Item macbook = Item.builder().id("1").name("MacbookPro").build();

    private static final Sku macbookGray = Sku.builder().id("1").item(macbook).color("Gray").build();
    private static final Sku macbookWhite = Sku.builder().id("2").item(macbook).color("White").build();

    private static final Price macbookGrayPrice = Price.builder().id("1").sku(macbookGray).price(18000D).build();
    private static final Price macbookWhitePrice = Price.builder().id("2").sku(macbookWhite).price(18800D).build();

    private static final Inventory macbookGrayInv = Inventory.builder().id("1").sku(macbookGray).stock(1000).build();
    private static final Inventory macbookWhiteInv = Inventory.builder().id("1").sku(macbookWhite).stock(2000).build();

    public static List<Item> getItems() {
        return Collections.singletonList(macbook);
    }

    public static List<Sku> getSkus() {
        return Arrays.asList(macbookGray, macbookWhite);
    }

    public static List<Price> getPrices() {
        return Arrays.asList(macbookGrayPrice, macbookWhitePrice);
    }

    public static List<Inventory> getInventories() {
        return Arrays.asList(macbookGrayInv, macbookWhiteInv);
    }

}
