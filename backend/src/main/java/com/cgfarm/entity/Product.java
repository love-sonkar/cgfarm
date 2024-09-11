package com.cgfarm.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    private String name;

    private String weight;

    @Column(columnDefinition = "clob")
    private String image;

    @Column(columnDefinition = "nvarchar")
    private String details;

    private long price;

    private long previousPrice;

    private long categoryId = 0;

    private long createTimeStamp;

    private long modifiedTimeStamp;
}
