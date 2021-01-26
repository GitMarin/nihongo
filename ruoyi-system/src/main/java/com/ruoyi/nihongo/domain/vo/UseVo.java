package com.ruoyi.nihongo.domain.vo;

import com.ruoyi.nihongo.domain.BunpouUse;

import java.util.List;

public class UseVo extends BunpouUse {

    private Long bunpouId;

    private List<String> types;

    public Long getBunpouId() {
        return bunpouId;
    }

    public void setBunpouId(Long bunpouId) {
        this.bunpouId = bunpouId;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }
}
