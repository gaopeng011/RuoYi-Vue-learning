package com.ruoyi.project.shop.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.project.shop.domain.LitemallStorage;
import com.ruoyi.project.shop.service.ILitemallStorageService;
import com.ruoyi.project.shop.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

/**
 * 文件存储Controller
 *
 * @author ruoyi
 * @date 2020-03-11
 */
@RestController
@RequestMapping("/shop/storage")
public class LitemallStorageController extends BaseController
{
    @Autowired
    private ILitemallStorageService litemallStorageService;
    @Autowired
    private StorageService storageService;

    /**
     * 查询文件存储列表
     */
    @PreAuthorize("@ss.hasPermi('shop:storage:list')")
    @GetMapping("/list")
    public TableDataInfo list(LitemallStorage litemallStorage)
    {
        startPage();
        List<LitemallStorage> list = litemallStorageService.selectLitemallStorageList(litemallStorage);
        return getDataTable(list);
    }

    /**
     * 导出文件存储列表
     */
    @PreAuthorize("@ss.hasPermi('shop:storage:export')")
    @Log(title = "文件存储", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(LitemallStorage litemallStorage)
    {
        List<LitemallStorage> list = litemallStorageService.selectLitemallStorageList(litemallStorage);
        ExcelUtil<LitemallStorage> util = new ExcelUtil<LitemallStorage>(LitemallStorage.class);
        return util.exportExcel(list, "storage");
    }

    /**
     * 获取文件存储详细信息
     */
    @PreAuthorize("@ss.hasPermi('shop:storage:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(litemallStorageService.selectLitemallStorageById(id));
    }

    /**
     * 新增文件存储
     */

    @Log(title = "文件存储", businessType = BusinessType.INSERT)
    @PostMapping("/create")
//    @PreAuthorize("@ss.hasPermi('shop:storage:create')")
    public AjaxResult add(@RequestParam("file") MultipartFile file)
    {
        String originalFilename = file.getOriginalFilename();
        LitemallStorage litemallStorage = null;
        try {
            litemallStorage = storageService.store(file.getInputStream(), file.getSize(),
                    file.getContentType(), originalFilename);


            return AjaxResult.success(litemallStorage);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.error("文件上传失败!");
        }

    }

    @Log(title = "文件存储", businessType = BusinessType.INSERT)
    @PostMapping("/createwx")
//    @PreAuthorize("@ss.hasPermi('shop:storage:create')")
    public AjaxResult addwx(@RequestParam("file") MultipartFile file)
    {
        String originalFilename = file.getOriginalFilename();
        LitemallStorage litemallStorage = null;
        try {
            litemallStorage = storageService.store(file.getInputStream(), file.getSize(),
                    file.getContentType(), originalFilename);


            return AjaxResult.success(litemallStorage);
        } catch (IOException e) {
            e.printStackTrace();
            return AjaxResult.error("文件上传失败!");
        }

    }


    @PostMapping("/read")
    public AjaxResult read(@NotNull Long id) {
        LitemallStorage storageInfo = litemallStorageService.selectLitemallStorageById(id);
        if (storageInfo == null) {
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }


    /**
     * 修改文件存储
     */
    @PreAuthorize("@ss.hasPermi('shop:storage:edit')")
    @Log(title = "文件存储", businessType = BusinessType.UPDATE)
    @PutMapping("/update")
    public AjaxResult edit(@RequestBody LitemallStorage litemallStorage)
    {
        return toAjax(litemallStorageService.updateLitemallStorage(litemallStorage));
    }

    /**
     * 删除文件存储
     */
    @PreAuthorize("@ss.hasPermi('shop:storage:remove')")
    @Log(title = "文件存储", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(litemallStorageService.deleteLitemallStorageByIds(ids));
    }

    @PreAuthorize("@ss.hasPermi('shop:storage:remove')")
    @Log(title = "对象文件删除", businessType = BusinessType.DELETE)
    @PostMapping("/delete")
    public AjaxResult delete(@RequestBody LitemallStorage litemallStorage) {
        String key = litemallStorage.getKey();
        if (StringUtils.isEmpty(key)) {
            return AjaxResult.error("未找到相关对象文件");
        }
        litemallStorageService.selectLitemallStorageByKey(litemallStorage);
        storageService.delete(key);
        return AjaxResult.success();
    }
}
