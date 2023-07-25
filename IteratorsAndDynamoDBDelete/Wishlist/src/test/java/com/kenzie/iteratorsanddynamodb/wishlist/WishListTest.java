package com.kenzie.iteratorsanddynamodb.wishlist;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class WishListTest {

    @Test
    public void addLast_itemAppendedToList_returnsList() {
        //GIVEN
        WishList wishListRef = new WishList();
        List<WishListItem> wishList = new ArrayList<>();
        WishListItem shirt = new WishListItem("blue tshirt", "clothing", 13.98);
        WishListItem book = new WishListItem("The Great Gadspeed", "book", 18.43);
        WishListItem charger = new WishListItem("iPhone charger", "electronics", 14.65);

        //WHEN
        wishList = wishListRef.addLast(wishList, shirt);
        wishList = wishListRef.addLast(wishList, book);
        wishList = wishListRef.addLast(wishList, charger);

        //THEN
        try {
            assertEquals("blue tshirt The Great Gadspeed iPhone charger", wishList.get(0).getTitle() + " " +
                    wishList.get(1).getTitle() + " " + wishList.get(2).getTitle(), "The order of the list should be " +
                    "blue tshirt The Great Gadspeed iPhone charger");
        } catch (IndexOutOfBoundsException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void addAtIndex_itemAddedAtIndex_returnsZeldaAtIndex2() {
        //GIVEN
        WishList wishListRef = new WishList();
        List<WishListItem> wishList = new ArrayList<>();
        WishListItem shirt = new WishListItem("blue tshirt", "clothing", 13.98);
        WishListItem book = new WishListItem("The Great Gadspeed", "book", 18.43);
        WishListItem charger = new WishListItem("iPhone charger", "electronics", 14.65);
        wishList = wishListRef.addLast(wishList, shirt);
        wishList = wishListRef.addLast(wishList, book);
        wishList = wishListRef.addLast(wishList, charger);

        //WHEN
        WishListItem game = new WishListItem("Zelda", "game", 56.78);
        wishList = wishListRef.addAtIndex(wishList, game, 2);

        //THEN
        try {
            assertEquals("Zelda", wishList.get(2).getTitle(), "The Zelda game should be added at index 2");
        } catch (IndexOutOfBoundsException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void addAtIndex_indexOutOfBounds_returnsZeldaAtIndex3() {
        //GIVEN
        WishList wishListRef = new WishList();
        List<WishListItem> wishList = new ArrayList<>();
        WishListItem shirt = new WishListItem("blue tshirt", "clothing", 13.98);
        WishListItem book = new WishListItem("The Great Gadspeed", "book", 18.43);
        WishListItem charger = new WishListItem("iPhone charger", "electronics", 14.65);
        wishList = wishListRef.addLast(wishList, shirt);
        wishList = wishListRef.addLast(wishList, book);
        wishList = wishListRef.addLast(wishList, charger);

        //WHEN
        WishListItem game = new WishListItem("Zelda", "game", 56.78);
        wishList = wishListRef.addAtIndex(wishList, game, 6);

        //THEN
        try {

            assertEquals("Zelda", wishList.get(3).getTitle(), "Since the given index was out of bounds for" +
                    "the list, the Zelda game should be added at index 3 (the end of the list)");
        } catch (IndexOutOfBoundsException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void removeItem_removeGivenItem_returnsListWithoutTheGreatGadspeed() {
        //GIVEN
        WishList wishListRef = new WishList();
        List<WishListItem> wishList = new ArrayList<>();
        WishListItem shirt = new WishListItem("blue tshirt", "clothing", 13.98);
        WishListItem book = new WishListItem("The Great Gadspeed", "book", 18.43);
        WishListItem charger = new WishListItem("iPhone charger", "electronics", 14.65);
        wishList = wishListRef.addLast(wishList, shirt);
        wishList = wishListRef.addLast(wishList, book);
        wishList = wishListRef.addLast(wishList, charger);

        //WHEN
        wishList = wishListRef.removeItem(wishList, book);

        //THEN
        try {
            assertEquals("blue tshirt iPhone charger", wishList.get(0).getTitle() + " " +
                    wishList.get(1).getTitle(), "The book item should have been removed from the list");
        } catch (IndexOutOfBoundsException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void removeAll_removeEntireList_returnsEmptyList() {
        //GIVEN
        WishList wishListRef = new WishList();
        List<WishListItem> wishList = new ArrayList<>();
        WishListItem shirt = new WishListItem("blue tshirt", "clothing", 13.98);
        WishListItem book = new WishListItem("The Great Gadspeed", "book", 18.43);
        WishListItem charger = new WishListItem("iPhone charger", "electronics", 14.65);
        wishList = wishListRef.addLast(wishList, shirt);
        wishList = wishListRef.addLast(wishList, book);
        wishList = wishListRef.addLast(wishList, charger);

        //WHEN
        wishList = wishListRef.removeAll(wishList);

        //THEN
        assertEquals(true, wishList.isEmpty(), "The wishList should be empty");
    }

}
