package com.kangkang.vote.modle;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class Vote {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private Integer spiderMan1;

    private Integer spiderMan2;

    private Integer spiderMan3;

    private Integer userId;

    public Integer getId() {
        return id;
    }

    public Integer getSpiderMan1() {
        return spiderMan1;
    }

    public Integer getSpiderMan2() {
        return spiderMan2;
    }

    public Integer getSpiderMan3() {
        return spiderMan3;
    }
    public Integer getUserId() {
        return userId;
    }

    public void setSpiderMan1(Integer spiderMan1) {
        this.spiderMan1 = spiderMan1;
    }

    public void setSpiderMan2(Integer spiderMan2) {
        this.spiderMan2 = spiderMan2;
    }

    public void setSpiderMan3(Integer spiderMan3) {
        this.spiderMan3 = spiderMan3;
    }


    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    
}
