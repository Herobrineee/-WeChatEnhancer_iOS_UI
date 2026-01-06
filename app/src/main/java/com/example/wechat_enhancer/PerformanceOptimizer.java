package com.example.wechat_enhancer;

import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class PerformanceOptimizer {
    
    private static final String TAG = "PerformanceOptimizer";
    private final Context context;
    
    public PerformanceOptimizer(Context context) {
        this.context = context;
    }
    
    /**
     * 应用性能优化设置
     * @param performanceModeEnabled 是否启用性能模式
     */
    public void applyPerformanceSettings(boolean performanceModeEnabled) {
        if (performanceModeEnabled) {
            enablePerformanceMode();
            Log.i(TAG, "性能模式已启用");
        } else {
            disablePerformanceMode();
            Log.i(TAG, "性能模式已禁用");
        }
    }
    
    /**
     * 启用性能模式
     */
    private void enablePerformanceMode() {
        try {
            // 1. 减少窗口动画（如果可能）
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                android.provider.Settings.Global.putInt(
                    context.getContentResolver(),
                    "window_animation_scale",
                    0
                );
                android.provider.Settings.Global.putInt(
                    context.getContentResolver(),
                    "transition_animation_scale",
                    0
                );
                android.provider.Settings.Global.putInt(
                    context.getContentResolver(),
                    "animator_duration_scale",
                    0
                );
            }
            
            // 2. 优化内存使用
            Runtime.getRuntime().gc();
            
            // 3. 设置低内存模式标志
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ((android.app.ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE))
                    .setProcessMemoryTrimLevel(android.app.ActivityManager.PROCESS_STATE_TOP, 
                        android.app.ActivityManager.TRIM_MEMORY_RUNNING_MODERATE);
            }
            
        } catch (Exception e) {
            Log.w(TAG, "启用性能模式时出错: " + e.getMessage());
        }
    }
    
    /**
     * 禁用性能模式
     */
    private void disablePerformanceMode() {
        try {
            // 恢复默认动画设置
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                android.provider.Settings.Global.putInt(
                    context.getContentResolver(),
                    "window_animation_scale",
                    1
                );
                android.provider.Settings.Global.putInt(
                    context.getContentResolver(),
                    "transition_animation_scale",
                    1
                );
                android.provider.Settings.Global.putInt(
                    context.getContentResolver(),
                    "animator_duration_scale",
                    1
                );
            }
        } catch (Exception e) {
            Log.w(TAG, "禁用性能模式时出错: " + e.getMessage());
        }
    }
    
    /**
     * 优化窗口性能
     * @param window 要优化的窗口
     */
    public void optimizeWindow(Window window) {
        if (window == null) return;
        
        try {
            // 禁用硬件加速（在某些设备上可能提高性能）
            window.setFlags(
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED
            );
            
            // 设置透明状态栏（iOS风格）
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(context.getColor(R.color.ios_status_bar));
            }
            
        } catch (Exception e) {
            Log.w(TAG, "优化窗口时出错: " + e.getMessage());
        }
    }
    
    /**
     * 获取内存使用信息
     * @return 内存使用信息字符串
     */
    public String getMemoryInfo() {
        try {
            Runtime runtime = Runtime.getRuntime();
            long totalMemory = runtime.totalMemory();
            long freeMemory = runtime.freeMemory();
            long usedMemory = totalMemory - freeMemory;
            long maxMemory = runtime.maxMemory();
            
            return String.format(
                "内存使用: %.2f MB / %.2f MB (最大: %.2f MB)",
                usedMemory / (1024.0 * 1024.0),
                totalMemory / (1024.0 * 1024.0),
                maxMemory / (1024.0 * 1024.0)
            );
        } catch (Exception e) {
            return "无法获取内存信息: " + e.getMessage();
        }
    }
    
    /**
     * 清理内存
     */
    public void cleanupMemory() {
        try {
            Runtime.getRuntime().gc();
            System.runFinalization();
            Log.i(TAG, "内存清理完成");
        } catch (Exception e) {
            Log.w(TAG, "清理内存时出错: " + e.getMessage());
        }
    }
    
    /**
     * 检查是否在低内存设备上运行
     * @return 是否低内存设备
     */
    public boolean isLowMemoryDevice() {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            
            // 如果总内存小于2GB，认为是低内存设备
            return memoryInfo.totalMem < 2L * 1024 * 1024 * 1024;
        } catch (Exception e) {
            return false;
        }
    }
}