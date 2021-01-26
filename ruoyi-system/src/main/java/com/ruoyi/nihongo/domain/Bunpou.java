package com.ruoyi.nihongo.domain;

public class Bunpou {
    private static final long serialVersionUID = 1L;
    private Long bunpouId;

    private String content;

    public Long getBunpouId() {
        return bunpouId;
    }

    public void setBunpouId(Long bunpouId) {
        this.bunpouId = bunpouId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
