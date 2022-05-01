package com.patni.lld.model;

import java.util.Date;
import java.util.List;

public class Order {
    int orderId;
    List<Item> orderItem;
    double orderValue;
    Buyer buyer;
    Date orderDate;

    List<OrderLog> orderLog;

    public OrderStatus placeOrder(){return null;}

    public OrderStatus trackOrder(){return null;}

    public void addOrderLogs(){}

    public PaymentStatus makePayment(PaymentType paymentType){return null;}
}

enum OrderStatus {
    PACKED, SHIPPED, ENROUTE, OUT_FOR_DELIVERY, DELIVERED, CANCELLED;
}

enum PaymentStatus {
    SUCCESS, ERROR, CANCELLED, REFUND_INITIATED, REFUNDED;
}

enum PaymentType {
    CREDIT_CARD, DEBIT_CARD, NET_BANKING, UPI;
}

class OrderLog {
    String orderDetail;
    Date createdDate;
    OrderStatus status;
}