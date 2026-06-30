# Contribution Guide

## [简体中文](./CONTRIBUTING.md) | English

Welcome to contribute! We greatly appreciate every contribution from community members, whether it's fixing bugs, adding new features, improving documentation, or suggesting ideas.

## 📋 Table of Contents

- [Code of Conduct](#code-of-conduct)
- [How to Report Bugs](#how-to-report-bugs)
- [How to Suggest Features](#how-to-suggest-features)
- [Development Environment Setup](#development-environment-setup)
- [Project Structure](#project-structure)
- [Code Style Conventions](#code-style-conventions)
- [Branch Naming Conventions](#branch-naming-conventions)
- [Commit Message Format](#commit-message-format)
- [PR Submission Process](#pr-submission-process)
- [Testing Requirements](#testing-requirements)
- [Documentation Updates](#documentation-updates)
- [License Notice](#license-notice)

## 📜 Code of Conduct

We expect all contributors to follow these guidelines:

- **Respect Others**: Treat community members with respect and kindness, avoid offensive language
- **Stay Professional**: Focus on technical discussions, avoid personal attacks or meaningless arguments
- **Be Helpful**: Actively help other developers solve problems and share experiences
- **Follow License**: All contributed code must comply with Apache License 2.0 requirements

## 🐛 How to Report Bugs

If you find a bug, please follow these steps to submit an Issue:

1. Before submitting, search existing Issues to confirm the bug hasn't been reported
2. If it's a new bug, create a new Issue with the following information:
   - **Bug Description**: Clearly describe the problem
   - **Reproduction Steps**: Detailed steps to reproduce the issue
   - **Expected Behavior**: What the correct behavior should be
   - **Actual Behavior**: What the current incorrect behavior is
   - **Environment Info**: JDK version, OS, Maven version, etc.
   - **Logs/Stack Trace**: If there are related logs or exception stacks, include them

## 💡 How to Suggest Features

We welcome any reasonable feature suggestions:

1. Before submitting, search existing Issues to confirm the suggestion hasn't been made
2. Create a new Issue with the following information:
   - **Feature Description**: Clearly describe the feature you want to add
   - **Use Case**: What is the application scenario for this feature
   - **Implementation Idea** (optional): If you have implementation ideas, share them
   - **Priority Assessment**: The importance and urgency of the feature

## ⚙️ Development Environment Setup

### Environment Requirements

- **JDK**: Version 8 or higher
- **Maven**: Version 3.6 or higher
- **IDE**: IntelliJ IDEA or Eclipse is recommended

### Steps

1. **Fork the Project**: Click the Fork button in the upper right corner of the GitHub repository to fork the project to your personal repository
2. **Clone the Project**: Clone your forked repository to local
   ```bash
   git clone https://github.com/your-username/jquick-curl.git
   cd jquick-curl
   ```
3. **Configure Remote Repository**: Add upstream repository to sync the latest code
   ```bash
   git remote add upstream https://github.com/paohaijiao/jquick-curl.git
   ```
4. **Build the Project**: Build the project with Maven
   ```bash
   mvn clean compile
   ```
5. **Run Tests**: Ensure all tests pass
   ```bash
   mvn test
   ```

## 📂 Project Structure

```
jquick-curl/
├── src/
│   ├── main/
│   │   ├── java/com/github/paohaijiao/
│   │   │   ├── anno/           # Annotations
│   │   │   ├── config/         # Configuration classes
│   │   │   ├── convert/        # Response converters
│   │   │   ├── domain/         # Domain models
│   │   │   ├── enums/          # Enum classes
│   │   │   ├── executor/       # Executors
│   │   │   ├── factory/        # Factory classes
│   │   │   ├── handler/        # Handlers
│   │   │   ├── interceptor/    # Interceptors
│   │   │   ├── model/          # Data models
│   │   │   ├── parser/         # Parsers (ANTLR)
│   │   │   ├── result/         # Result processing
│   │   │   ├── support/        # Support classes
│   │   │   ├── util/           # Utility classes
│   │   │   ├── visitor/        # Visitors
│   │   │   └── xml/            # XML related
│   │   └── resources/          # Resource files
│   └── test/                   # Test code
├── pom.xml                     # Maven configuration
├── README.md                   # Project description (Chinese)
└── README-EN.md                # Project description (English)
```

## 🎨 Code Style Conventions

Please follow these code style conventions:

- **Naming Conventions**:
  - Class names: PascalCase
  - Method names and variable names: camelCase
  - Constant names: UPPER_SNAKE_CASE
- **Indentation**: Use 4 spaces
- **Comments**:
  - Classes and public methods must have Javadoc comments
  - Complex logic should have appropriate comments
- **Exception Handling**: Avoid empty catch blocks, handle exceptions properly
- **Code Simplicity**: Avoid redundant code, use appropriate design patterns

## 🌿 Branch Naming Conventions

Please name branches according to the following conventions:

- **Feature Branch**: `feature/feature-name`
  - Example: `feature/support-http2`
- **Fix Branch**: `fix/bug-description`
  - Example: `fix/timeout-handling`
- **Documentation Branch**: `docs/documentation-name`
  - Example: `docs/update-readme`
- **Refactor Branch**: `refactor/refactor-description`
  - Example: `refactor/optimize-parser`

## 📝 Commit Message Format

Please follow the unified commit message format for easy tracking and management:

```
type(scope): description

optional body

optional footer
```

### Type Description

- **feat**: New feature
- **fix**: Bug fix
- **docs**: Documentation update
- **style**: Code style adjustment (no functional changes)
- **refactor**: Code refactoring
- **test**: Add or modify test cases
- **chore**: Changes to build process or auxiliary tools

### Example

```
feat(api): Add HTTP2 protocol support

- Add Http2Option enum value
- Update parser to support --http2 parameter
- Add related test cases

Closes #123
```

## 🔀 PR Submission Process

1. **Create Branch**: Create a new branch from `main`
   ```bash
   git checkout -b feature/your-feature
   ```
2. **Commit Code**: After completing code changes, commit to your branch
   ```bash
   git add .
   git commit -m "feat: add new feature"
   ```
3. **Sync Upstream**: Before submitting PR, sync the latest code from upstream
   ```bash
   git fetch upstream
   git rebase upstream/main
   ```
4. **Push Branch**: Push the branch to your GitHub repository
   ```bash
   git push origin feature/your-feature
   ```
5. **Create PR**: Create a Pull Request on GitHub with the following information:
   - **Title**: Clearly describe the changes
   - **Description**: Detail the changes, issues resolved, test status, etc.
   - **Related Issue**: If the PR resolves an Issue, reference it in the description
6. **Wait for Review**: Project maintainers will review your PR, please make changes based on feedback

## 🧪 Testing Requirements

All code changes must meet the following testing requirements:

- **Pass Existing Tests**: Ensure `mvn test` passes all existing test cases
- **Add New Tests**: For new features or fixes, add corresponding test cases
- **Test Coverage**: Maintain high test coverage, new code should have corresponding test coverage

## 📖 Documentation Updates

If your changes affect the project's usage, please update relevant documentation:

- **README.md / README-EN.md**: Update usage instructions, example code, etc.
- **API Documentation**: Update Javadoc comments
- **Other Documents**: Update any other related documents

## 📄 License Notice

This project is licensed under the **Apache License 2.0**. All contributed code automatically follows this license.

Before submitting code, please ensure:

- You have full copyright of the submitted code
- The code does not contain any unauthorized third-party library code
- The code complies with Apache License 2.0 requirements

---

Thank you for your contribution! 🎉