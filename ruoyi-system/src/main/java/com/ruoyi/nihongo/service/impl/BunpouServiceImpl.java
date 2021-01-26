package com.ruoyi.nihongo.service.impl;

import com.ruoyi.nihongo.domain.Bunpou;
import com.ruoyi.nihongo.domain.vo.ExampleVo;
import com.ruoyi.nihongo.domain.vo.BunpouVo;
import com.ruoyi.nihongo.domain.vo.UseVo;
import com.ruoyi.nihongo.mapper.BunpouMapper;
import com.ruoyi.nihongo.service.IBunpouService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BunpouServiceImpl implements IBunpouService {

    @Autowired
    private BunpouMapper  bunpouMapper;

    @Override
    public List<BunpouVo> selectBunpouVo() {
        return bunpouMapper.selectBunpouVo();
    }

    @Override
    public int updateExample(ExampleVo example) {
        return bunpouMapper.updateExample(example);
    }

    @Override
    public int insertExample(ExampleVo example) {
        return bunpouMapper.insertExample(example);
    }

    @Override
    public int updateUse(UseVo use) {
        types2Type(use);
        return bunpouMapper.updateUse(use);
    }


    @Override
    public int insertUse(UseVo use) {
        types2Type(use);
        return bunpouMapper.insertUse(use);
    }

    @Override
    public int updateBunpou(Bunpou bunpou) {
        return bunpouMapper.updateBunpou(bunpou);
    }

    @Override
    public int insertBunpou(Bunpou bunpou) {
        return bunpouMapper.insertBunpou(bunpou);
    }

    private void types2Type(UseVo use) {
        List<String> types = use.getTypes();
        String type = types.stream().collect(Collectors.joining(",",",",","));
        use.setType(type);
    }
}


