# 微信增强模块 - iOS风格界面

一个具有流畅iOS风格界面的微信增强模块，支持功能自由开关，占用率极低。

## 功能特性

### 🎨 iOS风格界面
- 完整的iOS设计语言实现
- 流畅的过渡动画
- 现代化的卡片式布局
- 透明状态栏（iOS风格）

### ⚡ 低占用率设计
- 性能模式开关
- 动画可单独禁用
- 内存优化机制
- 资源占用监控

### 🔧 功能自由开关
- 朋友圈防撤回
- 朋友圈去广告
- 消息防撤回
- 自动原图发送

## 项目结构

```
WeChatEnhancer_iOS_UI/
├── app/
│   ├── src/main/java/com/example/wechat_enhancer/
│   │   ├── MainActivity.java          # 主界面
│   │   ├── SettingsActivity.java      # 设置界面
│   │   ├── TestActivity.java          # 测试界面
│   │   ├── ConfigManager.java         # 配置管理
│   │   ├── PerformanceOptimizer.java  # 性能优化
│   │   └── MainHook.java              # Hook框架
│   ├── src/main/res/
│   │   ├── layout/                    # 布局文件
│   │   ├── values/                    # 资源文件
│   │   ├── anim/                      # 动画文件
│   │   └── drawable/                  # 图标文件
│   └── build.gradle                   # 模块构建配置
├── .github/workflows/
│   └── android.yml                    # GitHub Actions配置
├── build.gradle                       # 项目构建配置
├── settings.gradle                    # 项目设置
└── README.md                          # 项目说明
```

## 快速开始

### 1. 本地构建

```bash
# 克隆项目
git clone https://github.com/yourusername/WeChatEnhancer_iOS_UI.git

# 进入项目目录
cd WeChatEnhancer_iOS_UI

# 构建APK
./gradlew assembleDebug

# 构建发布版APK
./gradlew assembleRelease
```

### 2. GitHub Actions自动构建

项目已配置GitHub Actions，每次推送到main/master分支或手动触发时，会自动构建APK。

构建完成后，可以在Actions页面下载：
1. `wechat-enhancer-apk` - Debug版本APK
2. `wechat-enhancer-release-apk` - Release版本APK

### 3. 安装使用

1. 下载构建好的APK文件
2. 在Android设备上安装
3. 打开应用，根据需要启用功能
4. 配合Xposed框架使用（需要额外配置）

## 配置说明

### 功能开关
- **朋友圈防撤回**: 防止朋友圈内容被撤回
- **朋友圈去广告**: 移除朋友圈中的广告内容
- **消息防撤回**: 防止聊天消息被撤回
- **自动原图**: 发送图片时自动选择原图

### 性能设置
- **性能模式**: 降低动画效果，减少资源占用
- **启用动画**: 控制iOS风格过渡动画的开关
- **自动更新**: 自动检查新版本

## 技术实现

### 界面设计
- 基于Material Design Components
- 自定义iOS风格主题
- 矢量图标减少资源占用
- 响应式布局设计

### 性能优化
- 内存使用监控
- 动画性能优化
- 配置持久化存储
- 低内存设备适配

### 配置管理
- SharedPreferences存储配置
- 单例模式配置管理器
- 实时配置更新
- 配置备份与恢复

## 开发指南

### 环境要求
- Android Studio 2022.3.1+
- JDK 17+
- Android SDK 34+
- Gradle 8.3+

### 代码规范
- 遵循Android官方开发规范
- 使用有意义的变量名和方法名
- 添加必要的注释
- 保持代码简洁清晰

### 扩展功能
如需添加新功能：
1. 在`ConfigManager`中添加配置项
2. 在`MainHook`中实现Hook逻辑
3. 在界面中添加对应的开关
4. 更新相关布局和资源

## 注意事项

### 兼容性
- 支持Android 5.0+ (API 21+)
- 需要Xposed框架支持实际功能
- 本应用仅为UI界面，实际功能需要Xposed模块

### 权限说明
- 需要网络权限（检查更新）
- 需要存储权限（配置保存）
- 无敏感权限要求

### 免责声明
本应用仅供学习和研究使用，请勿用于商业用途或侵犯他人权益。使用本应用产生的任何问题，开发者不承担责任。

## 贡献指南

欢迎提交Issue和Pull Request！

1. Fork项目
2. 创建功能分支
3. 提交更改
4. 推送到分支
5. 创建Pull Request

## 许可证

本项目采用MIT许可证。详见[LICENSE](LICENSE)文件。

## 联系方式

如有问题或建议，请通过以下方式联系：
- GitHub Issues: [项目Issues页面](https://github.com/yourusername/WeChatEnhancer_iOS_UI/issues)
- Email: your.email@example.com

---

**感谢使用微信增强模块！** 🚀