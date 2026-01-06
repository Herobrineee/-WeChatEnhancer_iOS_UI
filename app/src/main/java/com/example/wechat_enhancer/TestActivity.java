package com.example.wechat_enhancer;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class TestActivity extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        
        setupUI();
    }
    
    private void setupUI() {
        // 设置标题
        TextView title = findViewById(R.id.test_title);
        title.setText("功能测试");
        
        // 测试卡片1
        CardView testCard1 = findViewById(R.id.test_card_1);
        TextView testTitle1 = testCard1.findViewById(R.id.test_card_title);
        TextView testDesc1 = testCard1.findViewById(R.id.test_card_description);
        
        testTitle1.setText("朋友圈防撤回测试");
        testDesc1.setText("测试朋友圈防撤回功能是否正常工作");
        
        // 测试卡片2
        CardView testCard2 = findViewById(R.id.test_card_2);
        TextView testTitle2 = testCard2.findViewById(R.id.test_card_title);
        TextView testDesc2 = testCard2.findViewById(R.id.test_card_description);
        
        testTitle2.setText("消息防撤回测试");
        testDesc2.setText("测试消息防撤回功能是否正常工作");
        
        // 测试卡片3
        CardView testCard3 = findViewById(R.id.test_card_3);
        TextView testTitle3 = testCard3.findViewById(R.id.test_card_title);
        TextView testDesc3 = testCard3.findViewById(R.id.test_card_description);
        
        testTitle3.setText("去广告测试");
        testDesc3.setText("测试朋友圈去广告功能是否正常工作");
        
        // 返回按钮
        CardView backCard = findViewById(R.id.test_back_card);
        TextView backText = backCard.findViewById(R.id.test_back_text);
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