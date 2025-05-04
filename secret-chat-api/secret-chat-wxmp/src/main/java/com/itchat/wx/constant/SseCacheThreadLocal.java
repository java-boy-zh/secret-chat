package com.itchat.wx.constant;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 王青玄
 * @Contact 1121586359@qq.com
 * @create 2025年01月07日 13:17
 * @Description SSE连接管理器
 * @Version V1.0
 */
public class SseCacheThreadLocal {

    private static final Map<String, SseEmitter> sseCache = new ConcurrentHashMap<>();

    private SseCacheThreadLocal() {
        // 私有构造函数，防止实例化
    }

    /**
     * 添加SSE连接
     * @param userId 用户ID
     * @param emitter SSE发射器
     */
    public static void add(String userId, SseEmitter emitter) {
        sseCache.put(userId, emitter);
    }

    /**
     * 获取SSE连接
     * @param userId 用户ID
     * @return SSE发射器
     */
    public static SseEmitter get(String userId) {
        return sseCache.get(userId);
    }

    /**
     * 移除SSE连接
     * @param userId 用户ID
     * @return 被移除的SSE发射器
     */
    public static SseEmitter remove(String userId) {
        return sseCache.remove(userId);
    }

    /**
     * 获取所有SSE连接
     * @return SSE连接映射
     */
    public static Map<String, SseEmitter> getAll() {
        return sseCache;
    }

    /**
     * 清除所有SSE连接
     */
    public static void clear() {
        sseCache.clear();
    }

    /**
     * 检查用户是否已连接
     * @param userId 用户ID
     * @return 是否已连接
     */
    public static boolean contains(String userId) {
        return sseCache.containsKey(userId);
    }
}
