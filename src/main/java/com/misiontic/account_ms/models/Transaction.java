package com.misiontic.account_ms.models;

import org.springframework.data.annotation.Id;

import java.util.Date;

public class Transaction {
    @Id
    private String id;
    private String userIdOrigin;
    private String userIdDestiny;
    private Integer value;
    private Date date;

    public Transaction(String id, String userIdOrigin, String userIdDestiny, Integer value, Date date) {
        this.id = id;
        this.userIdOrigin = userIdOrigin;
        this.userIdDestiny = userIdDestiny;
        this.value = value;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserIdOrigin() {
        return userIdOrigin;
    }

    public void setUserIdOrigin(String userIdOrigin) {
        this.userIdOrigin = userIdOrigin;
    }

    public String getUserIdDestiny() {
        return userIdDestiny;
    }

    public void setUserIdDestiny(String userIdDestiny) {
        this.userIdDestiny = userIdDestiny;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
