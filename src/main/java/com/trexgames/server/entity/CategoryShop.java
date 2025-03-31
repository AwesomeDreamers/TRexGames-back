package com.trexgames.server.entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class CategoryShop {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="shop_id")
    private Shop shop;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();
}
