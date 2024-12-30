package com.itchat.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itchat.mapper.SensitiveWordsDao;
import com.itchat.entity.SensitiveWords;
import com.itchat.service.SensitiveWordsService;
import org.springframework.stereotype.Service;

/**
 * 敏感词表(SensitiveWords)表服务实现类
 *
 * @author makejava
 * @since 2024-12-30 18:20:53
 */
@Service("sensitiveWordsService")
public class SensitiveWordsServiceImpl extends ServiceImpl<SensitiveWordsDao, SensitiveWords> implements SensitiveWordsService {

}

