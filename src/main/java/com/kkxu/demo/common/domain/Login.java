package com.kkxu.demo.common.domain;

public class Login {
    private String accountId;

    private Integer id;

    private Integer sellerId;

    private String password;

    private Integer isseller;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getIsseller() {
        return isseller;
    }

    public void setIsseller(Integer isseller) {
        this.isseller = isseller;
    }
}