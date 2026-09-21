# 更新日志

本文件记录 MyBatis Mapper Navigator 的重要变更。

## 0.1.0 - 2026-09-20

### 新增

- 支持 Java Mapper 接口类名跳转到 Mapper XML。
- 支持 Java Mapper 方法与 XML 语句双向跳转。
- 支持 XML `namespace` 与 Java Mapper 接口之间的跳转。
- 增加专属的 Java/XML 双向桥接箭头图标。
- 支持 IntelliJ IDEA 2023.3 及更高版本。

### 修复

- 修复 IDEA 新版本测试 API 变化导致的测试编译问题。
- 禁用不适用于本项目的 Configuration Cache 序列化路径。

### 说明

- SQL 日志捕获和参数还原功能尚未加入。
- 首个公开版本，发布前已完成插件构建验证。
