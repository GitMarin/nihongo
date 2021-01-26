package com.ruoyi.web.controller.nihongo;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.nihongo.domain.Bunpou;
import com.ruoyi.nihongo.domain.vo.ExampleVo;
import com.ruoyi.nihongo.domain.vo.BunpouVo;
import com.ruoyi.nihongo.domain.vo.UseVo;
import com.ruoyi.nihongo.service.IBunpouService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文法管理
 *
 * @author wrg
 */
@RestController
@RequestMapping("/nihongo/bunpou")
public class BunpouController extends BaseController {
    @Autowired
    private IBunpouService bunpouService;

    @GetMapping("/list")
    public TableDataInfo list()
    {
        startPage();
        List<BunpouVo> bunpouVos = bunpouService.selectBunpouVo();
        return getDataTable(bunpouVos);
    }

    /**
     * 修改用例
     */
    @PutMapping("/updateExample")
    public AjaxResult updateExample(@RequestBody ExampleVo example) {
        return AjaxResult.success(bunpouService.updateExample(example));
    }

    /**
     * 新增用例
     */
    @PostMapping("/addExample")
    public AjaxResult addExample(@RequestBody ExampleVo example) {
        return AjaxResult.success(bunpouService.insertExample(example));
    }

    /**
     * 修改用法
     */
    @PutMapping("/updateUse")
    public AjaxResult updateUse(@RequestBody UseVo use) {
        return AjaxResult.success(bunpouService.updateUse(use));
    }

    /**
     * 新增用法
     */
    @PostMapping("/addUse")
    public AjaxResult addUse(@RequestBody UseVo use) {
        return AjaxResult.success(bunpouService.insertUse(use));
    }

    /**
     * 修改文法
     */
    @PutMapping("/updateBunpou")
    public AjaxResult updateBunpou(@RequestBody Bunpou bunpou) {
        return AjaxResult.success(bunpouService.updateBunpou(bunpou));
    }

    /**
     * 新增文法
     */
    @PostMapping("/addBunpou")
    public AjaxResult addBunpou(@RequestBody Bunpou bunpou) {
        return AjaxResult.success(bunpouService.insertBunpou(bunpou));
    }
}
