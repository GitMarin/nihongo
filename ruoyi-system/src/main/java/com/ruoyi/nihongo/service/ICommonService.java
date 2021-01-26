package com.ruoyi.nihongo.service;

import com.ruoyi.nihongo.domain.BunpouType;

import java.util.List;

public interface ICommonService {

    /**
     * 查询文法类型
     *
     * @param
     * @return 文法类型信息集合
     */
    public List<BunpouType> selectTypeList();
}
