package com.ruoyi.nihongo.domain.vo;

import com.ruoyi.nihongo.domain.BunpouUse;
import com.ruoyi.nihongo.domain.BunpouUseExample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BunpouUseVo extends BunpouUse {

    private List<BunpouUseExample> bunpouUseExamples;

    private String typeLabels;

    public List<BunpouUseExample> getBunpouUseExamples() {
        return bunpouUseExamples;
    }

    public void setBunpouUseExamples(List<BunpouUseExample> bunpouUseExamples) {
        this.bunpouUseExamples = bunpouUseExamples;
    }

    public String getTypeLabels() {
        return typeLabels;
    }

    public void setTypeLabels(String typeLabels) {
        this.typeLabels = typeLabels;
    }

    public List<String> getTypes() {
        String type = this.getType();
        if (type == null) {
            return new ArrayList<>();
        } else {
            return Arrays.asList(type.substring(1).split(","));
        }
    }

    public List<String> getTypeNames() {
        String labels = this.typeLabels;
        if (labels == null) {
            return new ArrayList<>();
        } else {
            return Arrays.asList(labels.split(","));
        }
    }
}
