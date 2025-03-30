package com.trexgames.server.vo;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Shop {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @OneToMany(mappedBy = "shop", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoryShop> categoryShops = new ArrayList<>();

    private String productName;
    private int productPrice;
    private int productQnt;

    @Lob
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date editAt;

    private int sale;
}
