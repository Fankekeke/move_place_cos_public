![](https://img.shields.io/badge/License-MIT-blue.svg)

![](https://img.shields.io/badge/Version-1.0.0-brightgreen.svg)

![](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)

# 搬家服务平台 (Moving Service Platform)

一个功能完善的同城搬家服务预约系统，采用前后端分离架构。平台连接用户、商家与管理员，提供从在线预约、智能调度、订单管理到售后评价的全流程服务解决方案。

本项目旨在解决同城搬家市场中信息不透明、价格不标准、服务难监管的痛点。
用户端：提供便捷的微信小程序体验，支持快速下单、费用预估及进度追踪。
商家端：为搬家公司提供高效的订单处理、车辆调度及财务管理系统。
管理端：为平台运营方提供全方位的审核、监管及数据分析能力。

#### 安装环境
️**注意**：请确保您的开发环境已安装以下依赖。

JAVA 环境 

Node.js环境 [https://nodejs.org/en/] 选择14.17

Yarn 打开cmd， 输入npm install -g yarn !!!必须安装完毕nodejs

Mysql 数据库 一定要把账户和密码记住

redis

Idea 编译器

WebStorm OR VScode 编译器

#### 采用技术及功能

后端：SpringBoot、MybatisPlus、MySQL、Redis、
前端：Vue、Apex、Antd、Axios

平台前端：vue(框架) + vuex(全局缓存) + rue-router(路由) + axios(请求插件) + apex(图表)  + antd-ui(ui组件)

平台后台：springboot(框架) + redis(缓存中间件) + shiro(权限中间件) + mybatisplus(orm) + restful风格接口 + mysql(数据库)

开发环境：windows10 or windows7 ， vscode or webstorm ， idea + lambok

---

## 核心特性

- **三端协同架构**：打通用户、商家与管理员三端数据，实现从下单、接单到监管的全链路闭环。
- **智能调度匹配**：基于地理位置与服务需求，自动匹配最优搬家公司与车辆资源。
- **透明计价体系**：支持基础里程费、楼层费、打包费等多维度计价规则，费用明细清晰可见。
- **全流程订单追踪**：实时同步订单状态变更，用户可随时查看车辆位置、服务人员及费用详情。
- **多维数据洞察**：提供订单量、营收趋势、客单价、热门区域等核心运营指标可视化分析。
- **灵活权限管控**：支持管理员、商家、员工多级角色权限配置，保障平台数据安全与业务隔离。
- **社区互动生态**：内置搬家经验分享与求助社区，增强用户粘性与平台活跃度。
- **合规审核机制**：严格审核商家资质与车辆信息，配合年审流程，保障服务质量与用户权益。


---

## ️ 功能模块概览

### 管理员端 (Admin Dashboard)

管理员端提供全局视角的平台运营监控与资源配置能力：

**平台监管与审核**

- **公司资质审核**：严格审核入驻搬家公司的资质、车辆及服务范围准入。
- **投诉与评价监管**：查看并处理用户投诉，监管商家服务评分与文字反馈。
- **车辆与人员管理**：登记监管全平台搬家车辆状态，管理运营人员账号与权限。
- **公告与消息管理**：发布平台通知、政策调整公告，统一处理站内消息通信。

**订单与财务中心**

- **全流程订单监控**：查看全平台搬家订单的实时状态、费用明细及流转数据。
- **支付与提现审核**：查询用户支付及商家收款明细，审核商家资金提现流水。
- **价格规则制定**：制定基础计价、里程费、楼层费等平台统一收费标准。
- **优惠券管理**：创建、发放平台优惠券，并监控核销与使用情况。

**数据统计与分析**

- **核心运营指标**：实时分析订单量、总收入、投诉率等关键运营数据。
- **年度/月度统计**：生成订单总量、营收趋势、客单价及热门区域分布报表。

### 商家端 (Merchant Portal)

商家端赋能搬家公司高效处理业务，优化内部资源调度：

**订单与业务处理**

- **接单中心**：实时接收新订单请求，快速确认接单或进行资源调配。
- **订单全周期管理**：管理已接订单的状态、时间、地址及费用，查看用户缴费情况。
- **客户消息互动**：及时接收并回复用户关于订单的咨询、需求或沟通。

**资源与内部管理**

- **公司与车辆维护**：编辑公司简介、服务范围，维护所属车辆信息与调度状态。
- **员工排班管理**：管理司机、搬运工等内部人员账号，安排工作排班。
- **资质年审配合**：提交或更新公司资质材料，配合平台进行的年度审核流程。

**运营与财务**

- **个性化报价设置**：在平台框架内，设置本公司的个性化报价策略与规则。
- **社区帖子管理**：在社区发布宣传、服务类帖子，提升品牌曝光度。
- **提现记录查询**：提交收益提现申请，查看历史提现明细与到账状态。

### 用户端 (Mini Program)

用户端提供便捷的微信小程序体验，实现搬家服务的“指尖预约”：

**便捷下单与追踪**

- **快速注册登录**：支持手机号一键注册与安全登录。
- **智能下单配置**：填写起止地址与时间，灵活选择车型、搬运人数及打包服务。
- **订单进度追踪**：实时查看订单状态、费用明细、车辆信息及服务人员详情。

**互动与售后**

- **在线沟通**：直接与接单的搬家公司或师傅进行在线沟通。
- **评价与投诉**：对已完成服务进行打分评价，或对不满意服务发起正式投诉并跟踪处理。
- **社区交流**：浏览或发布搬家经验分享、求助帖，参与社区互动。

**个人中心**

- **信息与地址管理**：随时更新姓名、联系方式及常用搬家地址。
- **优惠券管理**：领取、查看和使用平台发放的搬家优惠券，享受实惠。
- **消息通知**：接收订单状态变更、商家回复及系统重要提醒。
```

---

## 项目结构

```text
pharmacy-dispensing-system/
├── backend/            # 后端 API 服务
│   ├── src/
│   │   ├── controllers/  # 控制器
│   │   ├── models/       # 数据模型
│   │   ├── routes/       # 路由
│   │   └── services/     # 业务逻辑
│   └── package.json
├── frontend/           # 前端 Web 应用
│   ├── src/
│   │   ├── views/      # 页面视图
│   │   ├── components/ # 公共组件
│   │   └── store/      # 状态管理
│   └── package.json
├── docs/               # 项目文档与 API 接口说明
├── LICENSE
└── README.md
```
---


#### 前台启动方式
安装所需文件 yarn install 
运行 yarn run dev

#### 默认后台账户密码
[管理员]
admin
123456

[员工]
fank
123456

#### 项目截图
|  |  |
|---------------------|---------------------|
| ![](./assets/image%20(1).png) | ![](./assets/image%20(2).png) |
| ![](./assets/image%20(3).png) | ![](./assets/image%20(4).png) |
| ![](./assets/image%20(5).png) | ![](./assets/image%20(6).png) |
| ![](./assets/image%20(7).png) | ![](./assets/image%20(8).png) |
| ![](./assets/image%20(9).png) | ![](./assets/image%20(10).png) |
| ![](./assets/image%20(11).png) | ![](./assets/image%20(12).png) |
| ![](./assets/image%20(13).png) | ![](./assets/image%20(14).png) |
| ![](./assets/image%20(15).png) | ![](./assets/image%20(16).png) |
| ![](./assets/image%20(17).png) | ![](./assets/image%20(18).png) |
| ![](./assets/image%20(19).png) | ![](./assets/image%20(20).png) |
| ![](./assets/image%20(21).png) | ![](./assets/image%20(22).png) |
| ![](./assets/image%20(23).png) | ![](./assets/image%20(24).png) |
| ![](./assets/image%20(25).png) |  |

| ![](./assets/app_image%20(1).png) | ![](./assets/app_image%20(2).png) |
| ![](./assets/app_image%20(3).png) | ![](./assets/app_image%20(4).png) |
| ![](./assets/app_image%20(5).png) | ![](./assets/app_image%20(6).png) |
| ![](./assets/app_image%20(7).png) | ![](./assets/app_image%20(8).png) |
| ![](./assets/app_image%20(9).png) | ![](./assets/app_image%20(10).png) |
| ![](./assets/app_image%20(11).png) | ![](./assets/app_image%20(12).png) |
| ![](./assets/app_image%20(13).png) | ![](./assets/app_image%20(14).png) |

#### 演示视频

暂无

#### 获取方式

Email: fan1ke2ke@gmail.com

WeChat: `Storm_Berserker`

`附带部署与讲解服务，因为要恰饭资源非免费，伸手党勿扰，谢谢理解😭`

> 1.项目纯原创，不做二手贩子 2.一次购买终身有效 3.项目讲解持续到答辩结束 4.非常负责的答辩指导 5.**黑奴价格**

> 项目部署调试不好包退！功能逻辑没讲明白包退！

<div align=center><img align="center" src="./assets/product_word.png" alt="fankekeke" /></div>

#### 其它资源
[2026年-答辩顺利通过-客户评价🀄](https://berserker287.github.io/2026/06/29/2026%E5%B9%B4%E7%AD%94%E8%BE%A9%E9%A1%BA%E5%88%A9%E9%80%9A%E8%BF%87/)

[2025年-答辩顺利通过-客户评价🍜](https://berserker287.github.io/2025/06/18/2025%E5%B9%B4%E7%AD%94%E8%BE%A9%E9%A1%BA%E5%88%A9%E9%80%9A%E8%BF%87/)

[2024年-答辩顺利通过-客户评价👻](https://berserker287.github.io/2024/06/06/2024%E5%B9%B4%E7%AD%94%E8%BE%A9%E9%A1%BA%E5%88%A9%E9%80%9A%E8%BF%87/)

[2023年-答辩顺利通过-客户评价🐢](https://berserker287.github.io/2023/06/14/2023%E5%B9%B4%E7%AD%94%E8%BE%A9%E9%A1%BA%E5%88%A9%E9%80%9A%E8%BF%87/)

[2022年-答辩通过率100%-客户评价🐣](https://berserker287.github.io/2022/05/25/%E9%A1%B9%E7%9B%AE%E4%BA%A4%E6%98%93%E8%AE%B0%E5%BD%95/)

[毕业答辩导师提问的高频问题](https://berserker287.github.io/2023/06/13/%E6%AF%95%E4%B8%9A%E7%AD%94%E8%BE%A9%E5%AF%BC%E5%B8%88%E6%8F%90%E9%97%AE%E7%9A%84%E9%AB%98%E9%A2%91%E9%97%AE%E9%A2%98/)

[50个高频答辩问题-技术篇](https://berserker287.github.io/2023/06/13/50%E4%B8%AA%E9%AB%98%E9%A2%91%E7%AD%94%E8%BE%A9%E9%97%AE%E9%A2%98-%E6%8A%80%E6%9C%AF%E7%AF%87/)

[计算机毕设答辩时都会问到哪些问题？](https://www.zhihu.com/question/31020988)

[计算机专业毕业答辩小tips](https://zhuanlan.zhihu.com/p/145911029)

#### 接JAVAWEB毕设，纯原创，价格公道，诚信第一

`网站建设、小程序、H5、APP、各种系统 选题+开题报告+任务书+程序定制+安装调试+项目讲解+论文+答辩PPT`

More info: [悲伤的橘子树](https://berserker287.github.io/)

<div align="center"><img align="center" src="./assets/chat_qr_code.png" alt="fankekeke" width=250 /></div>

<p><img align="center" src="./assets/wechat_group.png" alt="fankekeke" /></p>

**感谢查看**
