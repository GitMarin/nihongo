package com.ruoyi.nihongo.domain.vo;

import com.ruoyi.nihongo.domain.Bunpou;

import java.util.List;

public class BunpouVo extends Bunpou {

    private List<BunpouUseVo> bunpouUses;

    public List<BunpouUseVo> getBunpouUses() {
        return bunpouUses;
    }

    public void setBunpouUses(List<BunpouUseVo> bunpouUses) {
        this.bunpouUses = bunpouUses;
    }
}
