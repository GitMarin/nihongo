package com.ruoyi.nihongo.service.impl;

import com.ruoyi.nihongo.domain.BunpouType;
import com.ruoyi.nihongo.mapper.CommonMapper;
import com.ruoyi.nihongo.service.ICommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommonServiceImpl implements ICommonService {

    @Autowired
    private CommonMapper commonMapper;

    @Override
    public List<BunpouType> selectTypeList() {
        return commonMapper.selectTypeList();
    }
}
