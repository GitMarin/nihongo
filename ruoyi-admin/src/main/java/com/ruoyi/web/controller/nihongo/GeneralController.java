package com.ruoyi.web.controller.nihongo;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.nihongo.domain.BunpouType;
import com.ruoyi.nihongo.service.ICommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 通用管理
 *
 * @author wrg
 */
@RestController
@RequestMapping("/nihongo/general")
public class GeneralController {

    @Autowired
    private ICommonService commonService;

    @GetMapping("/types")
    public AjaxResult types()
    {
        List<BunpouType> types = commonService.selectTypeList();
        return AjaxResult.success(types);
    }
}
