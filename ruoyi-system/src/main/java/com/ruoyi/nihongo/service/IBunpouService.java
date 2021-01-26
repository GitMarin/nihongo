package com.ruoyi.nihongo.service;

import com.ruoyi.nihongo.domain.Bunpou;
import com.ruoyi.nihongo.domain.BunpouUseExample;
import com.ruoyi.nihongo.domain.vo.ExampleVo;
import com.ruoyi.nihongo.domain.vo.BunpouVo;
import com.ruoyi.nihongo.domain.vo.UseVo;

import java.util.List;

public interface IBunpouService {

    public List<BunpouVo> selectBunpouVo();

    public int updateExample(ExampleVo example);

    public int insertExample(ExampleVo example);

    public int updateUse(UseVo use);

    public int insertUse(UseVo use);

    public int updateBunpou(Bunpou bunpou);

    public int insertBunpou(Bunpou bunpou);
}
