# MyBatis Mapper Navigator

## 中文说明

MyBatis Mapper Navigator 是一个免费的 IntelliJ IDEA 插件，用于在 MyBatis Mapper Java 文件与 Mapper XML 文件之间快速双向跳转。

### 当前功能

- Java Mapper 接口类名跳转到对应的 Mapper XML。
- Java Mapper 方法跳转到 XML 中相同 `id` 的语句。
- XML `namespace` 跳转到 Java Mapper 接口。
- XML 语句节点跳转到 Java Mapper 方法。
- 支持 `Mapper.java`、`Dao.java` 等命名方式，只要 XML 的 `namespace` 与 Java 全限定类名一致。
- 使用专属双向桥接箭头图标，区分普通的接口实现导航。

### 兼容性

- IntelliJ IDEA 2023.3（构建号 233）及更高版本。
- 插件以 Java 17 字节码构建。
- 当前开发和验证版本：IntelliJ IDEA 2025.2.3。

### 开发构建

使用 JDK 17 或更高版本打开项目，首次构建会自动下载目标 IntelliJ IDEA 平台及依赖：

```powershell
.\gradlew.bat buildPlugin
```

插件 ZIP 会生成在 `build/distributions` 目录。

### 当前暂不包含

SQL 日志捕获、参数还原和日志格式转换暂未加入。导航功能先保持轻量稳定，日志功能将在后续根据实际需求单独设计。

---

## English

MyBatis Mapper Navigator is a free IntelliJ IDEA plugin for fast two-way navigation between MyBatis mapper Java files and mapper XML files.

### Current Features

- Navigate from a Java mapper interface to its mapper XML file.
- Navigate from a Java mapper method to the XML statement with the same `id`.
- Navigate from an XML `namespace` to the Java mapper interface.
- Navigate from an XML statement to the matching Java mapper method.
- Supports names such as `Mapper.java` and `Dao.java` as long as the XML `namespace` matches the Java fully qualified class name.
- Uses a dedicated bidirectional bridge-arrow icon instead of the regular interface-implementation marker.

### Compatibility

- IntelliJ IDEA 2023.3 (build 233) and newer.
- Built with Java 17 bytecode.
- Current development and verification version: IntelliJ IDEA 2025.2.3.

### Development Build

Open the project with JDK 17 or newer. The first build downloads the target IntelliJ IDEA platform and dependencies automatically:

```powershell
.\gradlew.bat buildPlugin
```

The plugin ZIP is generated in `build/distributions`.

### Not Included Yet

SQL log capture, parameter restoration, and log format conversion are not included yet. Navigation is intentionally kept lightweight and stable; logging features may be designed separately based on actual demand.
