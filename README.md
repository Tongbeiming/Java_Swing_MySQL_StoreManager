# Java-Swing-mysql-仓库存储管理系统

好的，以下是一个示例 ReadMe 文件，你可以根据你们的课程设计具体情况进行修改：

# 课程设计 - Java+Swing+MySQL

本项目是一款使用 Java+Swing+MySQL 技术栈开发的课程设计作品，它包含了一个简单的图书管理系统。

## 功能特性

- 用户登录/注册
- 仓库、员工、供应商信息的查看改正
- 刷新功能
- MySQL的CRUD 

## 开发环境

- Java 11
- MySQL 8.0
- Swing GUI Framework

## 项目结构

```
project/
├── DBconnection/
├── GUI/
│   ├── Login/
│   ├── Store/
│   ├── Person/
│   └── Supplier/
├── lib/
├── manager/
├── pom.xml
└── README.md
```
 - DBconnection：包含数据库连接相关的Java类文件。
 - GUI：包含项目的主要功能页面。其中，Login为登录页面，Store为库存管理页面，Person为员工管理页面，Supplier为供应商管理页面。
 - lib：包含项目所需的外部依赖项。
 - manager：包含管理相关的Java类文件。
 - README.md：项目说明文档。
## 使用说明

1. 在 MySQL 数据库中新建一个名为 `detest1` 的数据库，如下图所示的表结构和数据。
2. 用 IntelliJ IDEA 打开项目，等待依赖库加载完成。
3. 在 `src/com/example/util/DBUtil.java` 中修改数据库连接信息，包括数据库 URL、用户名和密码。
4. 运行 `src/com/example/view/LoginFrame.java`，用管理员账号密码 `admin/admin` 进行登录。

## 数据库结构
![image](https://github.com/Tongbeiming/Java_Swing_MySQL_StoreManager/assets/106958373/0dcb5e38-2b27-4bff-8f99-b844bf124617)

![image](https://github.com/Tongbeiming/Java_Swing_MySQL_StoreManager/assets/106958373/760223ff-91be-48e0-b257-cba4c5538e91)

![image](https://github.com/Tongbeiming/Java_Swing_MySQL_StoreManager/assets/106958373/1f56cea5-1487-488c-9638-91e2d917e86f)

![image](https://github.com/Tongbeiming/Java_Swing_MySQL_StoreManager/assets/106958373/cdd20485-c08b-4ccb-87a2-f30dc4554a70)

![image](https://github.com/Tongbeiming/Java_Swing_MySQL_StoreManager/assets/106958373/6e906a5f-848e-4aa8-ac1d-4f5127fe667d)

![image](https://github.com/Tongbeiming/Java_Swing_MySQL_StoreManager/assets/106958373/d8149899-0d4e-494d-bc34-8c42d90550ee)

![image](https://github.com/Tongbeiming/Java_Swing_MySQL_StoreManager/assets/106958373/c88d10a1-cb67-47d4-8f3f-c5498a08cdfc)

![image](https://github.com/Tongbeiming/Java_Swing_MySQL_StoreManager/assets/106958373/8b9e7673-755a-4c28-b839-dfd09bd40db6)

![image](https://github.com/Tongbeiming/Java_Swing_MySQL_StoreManager/assets/106958373/94472050-306b-4ef0-98d6-0ec2730b119d)


## 开发者

- 马豪彤（项目负责和代码设计）
- 徐珺（Swing编写）
- 王俊伟、孟旭祥、刘顺清（流程和DOC编写）
- 梁元之、时鸣泽、刘研（总结梳理）

## 版权信息

本项目基于 MIT 协议发布，具体内容请查看 `LICENSE` 文件。
