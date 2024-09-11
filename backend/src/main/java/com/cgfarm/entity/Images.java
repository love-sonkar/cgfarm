package com.cgfarm.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Images {

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "clob")
    private String images;

    private long createTimeStamp;

    private long modifiedTimeStamp;
}
