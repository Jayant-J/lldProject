package com.patni.lld.model;


import java.util.List;

public class Product {
    int productId;
    String productDescription;
    String name;
    ProductCategory productCategory;
    Seller seller;
    double cost;
    List<ProductReview> productReviews;
}

class Item {
    Product product;
    int qty;
}

enum ProductCategory {
    ELECTRONICS, FURNITURE, GROCERY, MOBILE;
}

class ProductReview {
    String details;
    Buyer reviewer;
    int rating;
}