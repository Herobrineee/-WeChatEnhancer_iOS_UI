package com.example.wechat_enhancer;

import android.content.Context;
import android.content.SharedPreferences;

public class ConfigManager {
    
    // 配置键名
    public static final String KEY_MODULE_ACTIVE = "module_active";
    public static final String KEY_MOMENTS_ANTI_RECALL = "moments_anti_recall";
    public static final String KEY_MOMENTS_AD_REMOVAL = "moments_ad_removal";
    public static final String KEY_MESSAGE_ANTI_RECALL = "message_anti_recall";
    public static final String KEY_AUTO_ORIGINAL_IMAGE = "auto_original_image";
    public static final String KEY_PERFORMANCE_MODE = "performance_mode";
    public static final String KEY_ANIMATION_ENABLED = "animation_enabled";
    public static final String KEY_AUTO_UPDATE = "auto_update";
    
    private static final String PREF_NAME = "wechat_enhancer_config";
    
    private static ConfigManager instance;
    private final SharedPreferences preferences;
    
    private ConfigManager(Context context) {
        preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        initializeDefaults();
    }
    
    public static synchronized ConfigManager getInstance(Context context) {
        if (instance == null) {
            instance = new ConfigManager(context.getApplicationContext());
        }
        return instance;
    }
    
    private void initializeDefaults() {
        // 设置默认值（如果不存在）
        if (!preferences.contains(KEY_MODULE_ACTIVE)) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(KEY_MODULE_ACTIVE, true);
            editor.putBoolean(KEY_MOMENTS_ANTI_RECALL, true);
            editor.putBoolean(KEY_MOMENTS_AD_REMOVAL, true);
            editor.putBoolean(KEY_MESSAGE_ANTI_RECALL, true);
            editor.putBoolean(KEY_AUTO_ORIGINAL_IMAGE, false);
            editor.putBoolean(KEY_PERFORMANCE_MODE, false);
            editor.putBoolean(KEY_ANIMATION_ENABLED, true);
            editor.putBoolean(KEY_AUTO_UPDATE, true);
            editor.apply();
        }
    }
    
    // 模块状态
    public boolean isModuleActive() {
        return preferences.getBoolean(KEY_MODULE_ACTIVE, true);
    }
    
    public void setModuleActive(boolean active) {
        preferences.edit().putBoolean(KEY_MODULE_ACTIVE, active).apply();
    }
    
    // 朋友圈防撤回
    public boolean isMomentsAntiRecallEnabled() {
        return preferences.getBoolean(KEY_MOMENTS_ANTI_RECALL, true);
    }
    
    public void setMomentsAntiRecallEnabled(boolean enabled) {
        preferences.edit().putBoolean(KEY_MOMENTS_ANTI_RECALL, enabled).apply();
    }
    
    // 朋友圈去广告
    public boolean isMomentsAdRemovalEnabled() {
        return preferences.getBoolean(KEY_MOMENTS_AD_REMOVAL, true);
    }
    
    public void setMomentsAdRemovalEnabled(boolean enabled) {
        preferences.edit().putBoolean(KEY_MOMENTS_AD_REMOVAL, enabled).apply();
    }
    
    // 消息防撤回
    public boolean isMessageAntiRecallEnabled() {
        return preferences.getBoolean(KEY_MESSAGE_ANTI_RECALL, true);
    }
    
    public void setMessageAntiRecallEnabled(boolean enabled) {
        preferences.edit().putBoolean(KEY_MESSAGE_ANTI_RECALL, enabled).apply();
    }
    
    // 自动原图
    public boolean isAutoOriginalImageEnabled() {
        return preferences.getBoolean(KEY_AUTO_ORIGINAL_IMAGE, false);
    }
    
    public void setAutoOriginalImageEnabled(boolean enabled) {
        preferences.edit().putBoolean(KEY_AUTO_ORIGINAL_IMAGE, enabled).apply();
    }
    
    // 性能模式
    public boolean isPerformanceModeEnabled() {
        return preferences.getBoolean(KEY_PERFORMANCE_MODE, false);
    }
    
    public void setPerformanceModeEnabled(boolean enabled) {
        preferences.edit().putBoolean(KEY_PERFORMANCE_MODE, enabled).apply();
    }
    
    // 动画开关
    public boolean isAnimationEnabled() {
        return preferences.getBoolean(KEY_ANIMATION_ENABLED, true);
    }
    
    public void setAnimationEnabled(boolean enabled) {
        preferences.edit().putBoolean(KEY_ANIMATION_ENABLED, enabled).apply();
    }
    
    // 自动更新
    public boolean isAutoUpdateEnabled() {
        return preferences.getBoolean(KEY_AUTO_UPDATE, true);
    }
    
    public void setAutoUpdateEnabled(boolean enabled) {
        preferences.edit().putBoolean(KEY_AUTO_UPDATE, enabled).apply();
    }
    
    // 获取所有配置
    public String getAllConfig() {
        return String.format(
            "模块状态: %s\n朋友圈防撤回: %s\n朋友圈去广告: %s\n消息防撤回: %s\n自动原图: %s\n性能模式: %s\n动画: %s\n自动更新: %s",
            isModuleActive() ? "已激活" : "未激活",
            isMomentsAntiRecallEnabled() ? "已启用" : "已禁用",
            isMomentsAdRemovalEnabled() ? "已启用" : "已禁用",
            isMessageAntiRecallEnabled() ? "已启用" : "已禁用",
            isAutoOriginalImageEnabled() ? "已启用" : "已禁用",
            isPerformanceModeEnabled() ? "已启用" : "已禁用",
            isAnimationEnabled() ? "已启用" : "已禁用",
            isAutoUpdateEnabled() ? "已启用" : "已禁用"
        );
    }
    
    // 重置所有配置
    public void resetAllConfig() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        initializeDefaults();
    }
}