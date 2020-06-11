package com.kkxu.demo.common.domain;

public class Comment extends CommentKey {
    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }
}