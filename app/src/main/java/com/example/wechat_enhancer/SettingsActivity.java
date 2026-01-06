package com.example.wechat_enhancer;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class SettingsActivity extends AppCompatActivity {
    
    private ConfigManager configManager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        
        configManager = ConfigManager.getInstance(this);
        setupUI();
    }
    
    private void setupUI() {
        // 设置标题
        TextView title = findViewById(R.id.settings_title);
        title.setText("设置");
        
        // 性能模式开关
        CardView performanceCard = findViewById(R.id.performance_card);
        TextView performanceTitle = performanceCard.findViewById(R.id.setting_title);
        TextView performanceDesc = performanceCard.findViewById(R.id.setting_description);
        Switch performanceSwitch = performanceCard.findViewById(R.id.setting_switch);
        
        performanceTitle.setText("性能模式");
        performanceDesc.setText("降低动画效果，减少资源占用");
        performanceSwitch.setChecked(configManager.isPerformanceModeEnabled());
        performanceSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            configManager.setPerformanceModeEnabled(isChecked);
        });
        
        // 动画开关
        CardView animationCard = findViewById(R.id.animation_card);
        TextView animationTitle = animationCard.findViewById(R.id.setting_title);
        TextView animationDesc = animationCard.findViewById(R.id.setting_description);
        Switch animationSwitch = animationCard.findViewById(R.id.setting_switch);
        
        animationTitle.setText("启用动画");
        animationDesc.setText("启用iOS风格的过渡动画");
        animationSwitch.setChecked(configManager.isAnimationEnabled());
        animationSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            configManager.setAnimationEnabled(isChecked);
        });
        
        // 自动更新开关
        CardView updateCard = findViewById(R.id.update_card);
        TextView updateTitle = updateCard.findViewById(R.id.setting_title);
        TextView updateDesc = updateCard.findViewById(R.id.setting_description);
        Switch updateSwitch = updateCard.findViewById(R.id.setting_switch);
        
        updateTitle.setText("自动检查更新");
        updateDesc.setText("自动检查新版本");
        updateSwitch.setChecked(configManager.isAutoUpdateEnabled());
        updateSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            configManager.setAutoUpdateEnabled(isChecked);
        });
        
        // 关于卡片
        CardView aboutCard = findViewById(R.id.about_card);
        TextView aboutTitle = aboutCard.findViewById(R.id.about_title);
        TextView aboutVersion = aboutCard.findViewById(R.id.about_version);
        TextView aboutDescription = aboutCard.findViewById(R.id.about_description);
        
        aboutTitle.setText("关于");
        aboutVersion.setText("版本 1.0.0");
        aboutDescription.setText("微信增强模块 - iOS风格界面\n低占用率设计\n支持功能自由开关");
        
        // 返回按钮
        CardView backCard = findViewById(R.id.back_card);
        TextView backText = backCard.findViewById(R.id.back_text);
        backText.setText("返回主界面");
        backCard.setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });
    }
    
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}