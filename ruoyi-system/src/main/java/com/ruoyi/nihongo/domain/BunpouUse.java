package com.ruoyi.nihongo.domain;

public class BunpouUse {
    private static final long serialVersionUID = 1L;

    private Long bunpouUseId;

    private String use;

    private String type;

    private String attention;

    private String level;

    private String explanation;

    public Long getBunpouUseId() {
        return bunpouUseId;
    }

    public void setBunpouUseId(Long bunpouUseId) {
        this.bunpouUseId = bunpouUseId;
    }

    public String getUse() {
        return use;
    }

    public void setUse(String use) {
        this.use = use;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
