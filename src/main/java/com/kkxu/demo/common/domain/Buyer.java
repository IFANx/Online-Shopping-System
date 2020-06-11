package com.kkxu.demo.common.domain;

import com.kkxu.demo.common.domain.Extend.BuyerExtend;

public class Buyer  extends BuyerExtend {
    private Integer id;

    private String accountId;

    private String name;

    private Boolean sex;

    private String email;

    private String personalsign;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPersonalsign() {
        return personalsign;
    }

    public void setPersonalsign(String personalsign) {
        this.personalsign = personalsign == null ? null : personalsign.trim();
    }
}