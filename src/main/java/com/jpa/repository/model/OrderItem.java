package com.jpa.repository.model;

import javax.persistence.*;

/**
 * Created by java on 2017.09.05..
 */
@Entity
@Table(name="ORDER_ITEM")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ITEM_ID")
    private long itemId;

    @Column(name="PRODUCT")
    private String product;

    @Column(name="AMOUNT")
    private int amount;

    @ManyToOne
    @JoinColumn(name="ORDER_ID")
    private Order order;


    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
