package com.patni.lld.model;

public class Customer {

    String customerId;
    ShoppingCart cart;
//    Search searchObj;

    public ShoppingCart getShoppingCart(String customerId) {
        return null;
    }

    public void addItemsToCart(Item item) {
    }

    public void UpdateItemsFromCart(Item item) {
    }

    public void removeItemsToCart(Item item) {
    }
}

class Guest extends Customer {
    public Account createNewAccount() {
        return null;
    }
}

class User extends Customer {
    Account account;
}

class Seller extends User {
    public void addProduct(Product product) {
    }
}

class Buyer extends User {
    Order orderObj;

    public void addReview(ProductReview review) {
    }

    public OrderStatus placeOrder(ShoppingCart cart) {
        return null;
    }
}