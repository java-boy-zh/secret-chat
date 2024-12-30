package com.itchat.controller;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.itchat.entity.SensitiveWords;
import com.itchat.enums.IsDeletedFlagEnum;
import com.itchat.result.GraceJSONResult;
import com.itchat.service.SensitiveWordsService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 敏感词表(SensitiveWords)表控制层
 *
 * @author makejava
 * @since 2024-12-30 18:20:50
 */
@RestController
@RequestMapping("/sensitiveWords")
@Slf4j
public class SensitiveWordsController {
    /**
     * 服务对象
     */
    @Resource
    private SensitiveWordsService sensitiveWordsService;

    /**
     * 新增敏感词
     */
    @GetMapping(value = "/save")
    public GraceJSONResult save(String words, Integer type) {
        try {
            if (log.isInfoEnabled()) {
                log.info("新增敏感词入参{}", words);
            }
            SensitiveWords data = new SensitiveWords();
            data.setType(type);
            data.setIsDeleted(IsDeletedFlagEnum.UN_DELETED.getCode());
            data.setWords(words);
            return GraceJSONResult.ok(sensitiveWordsService.save(data));
        } catch (IllegalArgumentException e) {
            log.error("参数异常！错误原因{}", e.getMessage(), e);
            return GraceJSONResult.errorMsg(e.getMessage());
        } catch (Exception e) {
            log.error("新增敏感词异常！错误原因{}", e.getMessage(), e);
            return GraceJSONResult.errorMsg("新增敏感词异常！");
        }
    }

    /**
     * 删除敏感词
     */
    @GetMapping(value = "/remove")
    public GraceJSONResult remove(Long id) {
        try {
            if (log.isInfoEnabled()) {
                log.info("删除敏感词入参{}", id);
            }
            LambdaUpdateWrapper<SensitiveWords> update = Wrappers.<SensitiveWords>lambdaUpdate().set(SensitiveWords::getIsDeleted, IsDeletedFlagEnum.DELETED.getCode())
                    .eq(SensitiveWords::getId, id).eq(SensitiveWords::getIsDeleted, IsDeletedFlagEnum.UN_DELETED.getCode());
            return GraceJSONResult.ok(sensitiveWordsService.update(update));
        } catch (IllegalArgumentException e) {
            log.error("参数异常！错误原因{}", e.getMessage(), e);
            return GraceJSONResult.errorMsg(e.getMessage());
        } catch (Exception e) {
            log.error("删除敏感词异常！错误原因{}", e.getMessage(), e);
            return GraceJSONResult.errorMsg("删除敏感词异常！");
        }
    }
}

