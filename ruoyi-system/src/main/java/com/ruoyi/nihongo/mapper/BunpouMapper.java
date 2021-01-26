package com.ruoyi.nihongo.mapper;

import com.ruoyi.nihongo.domain.Bunpou;
import com.ruoyi.nihongo.domain.vo.ExampleVo;
import com.ruoyi.nihongo.domain.vo.BunpouUseVo;
import com.ruoyi.nihongo.domain.vo.BunpouVo;
import com.ruoyi.nihongo.domain.vo.UseVo;

import java.util.List;


public interface BunpouMapper {

    List<BunpouVo> selectBunpouVo();

    List<BunpouUseVo> selectBunpouUseVo(String id);

    int updateExample(ExampleVo example);

    int insertExample(ExampleVo example);

    int updateUse(UseVo use);

    int insertUse(UseVo use);

    int updateBunpou(Bunpou bunpou);

    int insertBunpou(Bunpou bunpou);
}
