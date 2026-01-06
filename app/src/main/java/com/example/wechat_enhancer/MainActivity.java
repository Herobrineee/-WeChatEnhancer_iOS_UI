package com.example.wechat_enhancer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {
    
    private ConfigManager configManager;
    private PerformanceOptimizer performanceOptimizer;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // 初始化配置管理器
        configManager = ConfigManager.getInstance(this);
        performanceOptimizer = new PerformanceOptimizer(this);
        
        // 应用性能优化设置
        performanceOptimizer.applyPerformanceSettings(
            configManager.isPerformanceModeEnabled()
        );
        
        setupUI();
        updateStatusDisplay();
    }
    
    private void setupUI() {
        // 设置标题
        TextView title = findViewById(R.id.title_text);
        title.setText("微信增强模块");
        
        // 设置副标题
        TextView subtitle = findViewById(R.id.subtitle_text);
        subtitle.setText("iOS风格界面 | 低占用率 | 功能自由开关");
        
        // 设置状态卡片
        CardView statusCard = findViewById(R.id.status_card);
        TextView statusTitle = findViewById(R.id.status_title);
        TextView statusText = findViewById(R.id.status_text);
        
        statusTitle.setText("当前状态");
        if (configManager.isModuleActive()) {
            statusText.setText("模块已激活");
            statusText.setTextColor(getColor(R.color.ios_green));
        } else {
            statusText.setText("模块未激活");
            statusText.setTextColor(getColor(R.color.ios_red));
        }
        
        // 设置功能卡片
        setupFunctionCard(R.id.function_card_1, "朋友圈防撤回", 
            configManager.isMomentsAntiRecallEnabled(), 
            ConfigManager.KEY_MOMENTS_ANTI_RECALL);
        
        setupFunctionCard(R.id.function_card_2, "朋友圈去广告", 
            configManager.isMomentsAdRemovalEnabled(), 
            ConfigManager.KEY_MOMENTS_AD_REMOVAL);
        
        setupFunctionCard(R.id.function_card_3, "消息防撤回", 
            configManager.isMessageAntiRecallEnabled(), 
            ConfigManager.KEY_MESSAGE_ANTI_RECALL);
        
        setupFunctionCard(R.id.function_card_4, "自动原图", 
            configManager.isAutoOriginalImageEnabled(), 
            ConfigManager.KEY_AUTO_ORIGINAL_IMAGE);
        
        // 设置按钮
        Button settingsBtn = findViewById(R.id.settings_button);
        settingsBtn.setText("设置");
        settingsBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
        
        Button testBtn = findViewById(R.id.test_button);
        testBtn.setText("测试功能");
        testBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, TestActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });
        
        Button toggleModuleBtn = findViewById(R.id.toggle_module_button);
        toggleModuleBtn.setText(configManager.isModuleActive() ? "停用模块" : "启用模块");
        toggleModuleBtn.setOnClickListener(v -> {
            boolean newState = !configManager.isModuleActive();
            configManager.setModuleActive(newState);
            toggleModuleBtn.setText(newState ? "停用模块" : "启用模块");
            updateStatusDisplay();
        });
    }
    
    private void setupFunctionCard(int cardId, String title, boolean isEnabled, String configKey) {
        CardView card = findViewById(cardId);
        TextView cardTitle = card.findViewById(R.id.function_title);
        TextView cardStatus = card.findViewById(R.id.function_status);
        
        cardTitle.setText(title);
        
        if (isEnabled) {
            cardStatus.setText("已启用");
            cardStatus.setTextColor(getColor(R.color.ios_green));
        } else {
            cardStatus.setText("已禁用");
            cardStatus.setTextColor(getColor(R.color.ios_gray));
        }
        
        card.setOnClickListener(v -> {
            boolean newState = !isEnabled;
            switch (configKey) {
                case ConfigManager.KEY_MOMENTS_ANTI_RECALL:
                    configManager.setMomentsAntiRecallEnabled(newState);
                    break;
                case ConfigManager.KEY_MOMENTS_AD_REMOVAL:
                    configManager.setMomentsAdRemovalEnabled(newState);
                    break;
                case ConfigManager.KEY_MESSAGE_ANTI_RECALL:
                    configManager.setMessageAntiRecallEnabled(newState);
                    break;
                case ConfigManager.KEY_AUTO_ORIGINAL_IMAGE:
                    configManager.setAutoOriginalImageEnabled(newState);
                    break;
            }
            setupFunctionCard(cardId, title, newState, configKey);
        });
    }
    
    private void updateStatusDisplay() {
        CardView statusCard = findViewById(R.id.status_card);
        TextView statusText = findViewById(R.id.status_text);
        
        if (configManager.isModuleActive()) {
            statusText.setText("模块已激活");
            statusText.setTextColor(getColor(R.color.ios_green));
        } else {
            statusText.setText("模块未激活");
            statusText.setTextColor(getColor(R.color.ios_red));
        }
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        // 刷新UI状态
        setupUI();
    }
}