package com.cgfarm.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.sql.Timestamp;

@Entity
@Data
public class ErrorReport {

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    @NotNull
    private String uuid;

    @Column
    @NotNull
    private String service;

    @Column
    @NotNull
    private String errorDetails;

    @Column
    @NotNull
    private String errorStacktrace;

    @Column
    @NotNull
    private String errorClass;

    @Column
    @NotNull
    private long createTimeStamp = new Timestamp(System.currentTimeMillis()).getTime();

    @Column
    @NotNull()
    private boolean active = true;
}

