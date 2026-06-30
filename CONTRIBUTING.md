# 贡献指南

## 简体中文 | [English](./CONTRIBUTING-EN.md)

欢迎贡献代码！我们非常感谢社区成员的每一份贡献，无论是修复 Bug、添加新功能、完善文档还是提出建议。

## 📋 目录

- [行为准则](#行为准则)
- [如何报告 Bug](#如何报告-bug)
- [如何提出功能建议](#如何提出功能建议)
- [开发环境搭建](#开发环境搭建)
- [项目结构](#项目结构)
- [代码风格规范](#代码风格规范)
- [分支命名规范](#分支命名规范)
- [提交信息格式](#提交信息格式)
- [PR 提交流程](#pr-提交流程)
- [测试要求](#测试要求)
- [文档更新](#文档更新)
- [许可证说明](#许可证说明)

## 📜 行为准则

我们期望所有贡献者遵守以下行为准则：

- **尊重他人**：对待社区成员保持尊重和友善，避免使用攻击性语言
- **保持专业**：讨论问题时聚焦技术本身，避免人身攻击或无意义争论
- **乐于帮助**：积极帮助其他开发者解决问题，分享经验
- **遵守许可证**：所有贡献代码必须符合 Apache License 2.0 的要求

## 🐛 如何报告 Bug

如果您发现了 Bug，请按照以下步骤提交 Issue：

1. 在提交之前，请先搜索现有的 Issue，确认该 Bug 尚未被报告
2. 如果是新 Bug，请创建一个新 Issue，并包含以下信息：
   - **Bug 描述**：清晰描述问题现象
   - **复现步骤**：详细说明如何复现该问题
   - **预期行为**：预期的正确行为是什么
   - **实际行为**：当前的错误行为是什么
   - **环境信息**：JDK 版本、操作系统、Maven 版本等
   - **日志/堆栈信息**：如果有相关日志或异常堆栈，请一并提供

## 💡 如何提出功能建议

我们欢迎任何合理的功能建议：

1. 在提交之前，先搜索现有的 Issue，确认该建议尚未被提出
2. 创建一个新 Issue，并包含以下信息：
   - **功能描述**：清晰描述您希望添加的功能
   - **使用场景**：该功能的应用场景是什么
   - **实现思路**（可选）：如果您有实现思路，可以分享出来
   - **优先级评估**：该功能的重要程度和紧急程度

## ⚙️ 开发环境搭建

### 环境要求

- **JDK**：8 或更高版本
- **Maven**：3.6 或更高版本
- **IDE**：推荐使用 IntelliJ IDEA 或 Eclipse

### 步骤

1. **Fork 项目**：点击 GitHub 仓库右上角的 Fork 按钮，将项目 fork 到您的个人仓库
2. **克隆项目**：将您 fork 的仓库克隆到本地
   ```bash
   git clone https://github.com/your-username/jquick-curl.git
   cd jquick-curl
   ```
3. **配置远程仓库**：添加上游仓库，以便同步最新代码
   ```bash
   git remote add upstream https://github.com/paohaijiao/jquick-curl.git
   ```
4. **构建项目**：使用 Maven 构建项目
   ```bash
   mvn clean compile
   ```
5. **运行测试**：确保所有测试通过
   ```bash
   mvn test
   ```

## 📂 项目结构

```
jquick-curl/
├── src/
│   ├── main/
│   │   ├── java/com/github/paohaijiao/
│   │   │   ├── anno/           # 注解定义
│   │   │   ├── config/         # 配置类
│   │   │   ├── convert/        # 响应转换器
│   │   │   ├── domain/         # 领域模型
│   │   │   ├── enums/          # 枚举类
│   │   │   ├── executor/       # 执行器
│   │   │   ├── factory/        # 工厂类
│   │   │   ├── handler/        # 处理器
│   │   │   ├── interceptor/    # 拦截器
│   │   │   ├── model/          # 数据模型
│   │   │   ├── parser/         # 解析器（ANTLR）
│   │   │   ├── result/         # 结果处理
│   │   │   ├── support/        # 支持类
│   │   │   ├── util/           # 工具类
│   │   │   ├── visitor/        # 访问者
│   │   │   └── xml/            # XML 相关
│   │   └── resources/          # 资源文件
│   └── test/                   # 测试代码
├── pom.xml                     # Maven 配置
├── README.md                   # 项目说明（中文）
└── README-EN.md                # 项目说明（英文）
```

## 🎨 代码风格规范

请遵循以下代码风格规范：

- **命名规范**：
  - 类名：使用 PascalCase（大驼峰）
  - 方法名和变量名：使用 camelCase（小驼峰）
  - 常量名：使用 UPPER_SNAKE_CASE（全大写加下划线）
- **缩进**：使用 4 个空格
- **注释**：
  - 类和公共方法必须有 Javadoc 注释
  - 复杂逻辑需要有适当的注释说明
- **异常处理**：避免空的 catch 块，合理处理异常
- **代码简洁**：避免冗余代码，使用适当的设计模式

## 🌿 分支命名规范

请按照以下规范命名分支：

- **功能分支**：`feature/feature-name`
  - 示例：`feature/support-http2`
- **修复分支**：`fix/bug-description`
  - 示例：`fix/timeout-handling`
- **文档分支**：`docs/documentation-name`
  - 示例：`docs/update-readme`
- **重构分支**：`refactor/refactor-description`
  - 示例：`refactor/optimize-parser`

## 📝 提交信息格式

请遵循统一的提交信息格式，便于追踪和管理：

```
type(scope): description

optional body

optional footer
```

### 类型说明

- **feat**：新功能
- **fix**：修复 Bug
- **docs**：文档更新
- **style**：代码风格调整（不影响功能）
- **refactor**：代码重构
- **test**：测试用例添加或修改
- **chore**：构建过程或辅助工具的变动

### 示例

```
feat(api): 添加 HTTP2 协议支持

- 新增 Http2Option 枚举值
- 更新解析器以支持 --http2 参数
- 添加相关测试用例

Closes #123
```

## 🔀 PR 提交流程

1. **创建分支**：从 `main` 分支创建新分支
   ```bash
   git checkout -b feature/your-feature
   ```
2. **提交代码**：完成代码修改后，提交到您的分支
   ```bash
   git add .
   git commit -m "feat: 添加新功能"
   ```
3. **同步上游**：在提交 PR 之前，同步上游仓库的最新代码
   ```bash
   git fetch upstream
   git rebase upstream/main
   ```
4. **推送分支**：将分支推送到您的 GitHub 仓库
   ```bash
   git push origin feature/your-feature
   ```
5. **创建 PR**：在 GitHub 上创建 Pull Request，包含以下信息：
   - **标题**：清晰描述本次变更的内容
   - **描述**：详细说明变更的内容、解决的问题、测试情况等
   - **关联 Issue**：如果 PR 解决了某个 Issue，请在描述中引用
6. **等待审核**：项目维护者会审核您的 PR，请根据反馈进行修改

## 🧪 测试要求

所有代码变更都必须满足以下测试要求：

- **通过现有测试**：确保 `mvn test` 能够通过所有现有测试用例
- **新增测试用例**：对于新功能或修复，必须添加相应的测试用例
- **测试覆盖率**：尽量保持高测试覆盖率，新增代码要有对应的测试覆盖

## 📖 文档更新

如果您的变更影响了项目的使用方式，请同步更新相关文档：

- **README.md / README-EN.md**：更新使用说明、示例代码等
- **API 文档**：更新 Javadoc 注释
- **其他文档**：如果有其他相关文档，也需要同步更新

## 📄 许可证说明

本项目采用 **Apache License 2.0** 开源许可证。所有贡献代码将自动遵循该许可证。

在提交代码之前，请确保：

- 您拥有提交代码的全部版权
- 代码不包含任何第三方库的未授权代码
- 代码符合 Apache License 2.0 的要求

---

感谢您的贡献！🎉