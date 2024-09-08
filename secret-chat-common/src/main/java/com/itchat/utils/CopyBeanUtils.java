package com.itchat.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 王哲
 * @Contact 1121586359@qq.com
 * @ClassName CopyBeanUtils.java
 * @create 2023年06月28日 下午4:01
 * @Description bean转换 封装BeanUtils
 * @Version V1.0
 */
public class CopyBeanUtils<T> {

    /**
     * 单体复制
     */
    public static <T> T copy(Object source, Class<T> clazz) {
        if (source == null) {
            return null;
        }
        T obj = null;
        try {
            obj = clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        BeanUtils.copyProperties(source, obj);
        return obj;
    }

    /**
     * 列表复制
     */
    public static <T> List<T> copyList(List source, Class<T> clazz) {
        List<T> target = new ArrayList<>();
        if (!CollectionUtils.isEmpty(source)){
            for (Object c: source) {
                T obj = copy(c, clazz);
                target.add(obj);
            }
        }
        return target;
    }

//    /**
//     * POJO -> BO
//     */
//    public static <T,R> Page<R> tranferBo(IPage<T> t, Class<R> r){
//        List<R> rsbo = t.getRecords().stream().map(e -> {
//            return CopyBeanUtils.copy(e, r);
//        }).collect(Collectors.toList());
//        Page<R> bo = new Page<>();
//        bo.setRecords(rsbo);
//        bo.setCurrent(t.getCurrent());
//        bo.setPages(t.getPages());
//        bo.setSize(t.getSize());
//        bo.setTotal(t.getTotal());
//
//        return bo;
//    }

}
