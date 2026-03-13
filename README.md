### 基于SpringBoot + Vue的搬家小程序平台.

搬家服务平台小程序、智能搬家预约系统、同城搬家服务小程序

#### 管理员功能模块介绍：
###### 公告信息：发布平台通知、搬家政策或服务调整公告。公司审核：审核搬家公司的资质、车辆及服务范围准入。投诉记录：查看并处理用户对商家或服务的投诉内容。订单评价：监管用户对搬家服务的评分与文字反馈。公司管理：维护所有入驻搬家公司的基本信息与状态。消息管理：统一处理平台内用户、商家间的消息通信。优惠券管理：创建、发放和监控优惠券使用情况。消息通知：向用户或商家推送系统提醒与重要信息。订单信息：查看全平台搬家订单的全流程状态数据。付款记录：查询用户支付及商家收款的交易明细。价格规则：制定基础计价、里程费、楼层费等收费标准。员工管理：管理平台运营及审核人员的账号与权限。客户管理：维护用户资料，处理异常账户或行为。车辆管理：登记并监管搬家车辆信息及可用状态。提现记录：审核并记录商家申请的资金提现流水。数据统计：分析订单量、收入、投诉率等核心运营指标。订单年统计：生成年度搬家订单总量、营收及趋势报表。订单月统计：按月汇总订单数、客单价、热门区域等数据。

#### 商家功能模块介绍：
###### 公司信息：编辑公司名称、简介、服务范围及联系方式。订单评价：查看客户对本司搬家服务的评分与评论。客户消息：接收并回复用户关于订单的咨询或需求。订单信息：管理已接订单的状态、时间、地址及费用。用户缴费：查看用户是否完成订单支付及到账情况。价格规则：在平台框架内设置本司个性化报价策略。员工管理：管理司机、搬运工等内部人员账号与排班。车辆管理：维护本公司所属搬家车辆的信息与调度。提现记录：提交收益提现申请并查看历史提现明细。贴子管理：发布或管理在社区中的宣传、服务类帖子。公司审核：提交或更新资质材料配合平台年审流程。客户消息：（重复项）用于及时响应用户沟通需求。接单中心：接收新订单请求，确认接单或调配资源。

#### 用户功能模块介绍：
###### 小程序注册登录：通过手机号快速注册并安全登录使用。个人信息修改：更新姓名、联系方式、常用地址等资料。我的订单：查看历史及当前搬家订单的进度与详情。我的消息：接收订单状态变更、商家回复等系统通知。优惠券管理：领取、查看和使用平台发放的搬家优惠券。社区交流：在论坛中浏览或参与搬家经验分享讨论。我的贴子：查看自己发布的求助、评价或分享内容。贴子发布：发布搬家需求、问题或服务推荐等帖子。投诉管理：对不满意的服务发起正式投诉并跟踪处理。订单下单：填写起止地址、时间等信息提交搬家请求。订单配置选择：选择车型、搬运人数、是否需要打包等服务项。商家联系：直接与接单搬家公司或师傅在线沟通。订单投诉：针对具体订单提交服务问题或纠纷反馈。订单评价：对已完成的搬家服务进行打分和文字评价。查看订单详情：查阅订单费用明细、车辆信息、服务人员等完整信息。


#### 安装环境

JAVA 环境 

Node.js环境 [https://nodejs.org/en/] 选择14.17

Yarn 打开cmd， 输入npm install -g yarn !!!必须安装完毕nodejs

Mysql 数据库 [https://blog.csdn.net/qq_40303031/article/details/88935262] 一定要把账户和密码记住

redis

Idea 编译器 [https://blog.csdn.net/weixin_44505194/article/details/104452880]

WebStorm OR VScode 编译器 [https://www.jianshu.com/p/d63b5bae9dff]

#### 采用技术及功能

后端：SpringBoot、MybatisPlus、MySQL、Redis、
前端：Vue、Apex、Antd、Axios
报表：Spread.js

平台前端：vue(框架) + vuex(全局缓存) + rue-router(路由) + axios(请求插件) + apex(图表)  + antd-ui(ui组件)

平台后台：springboot(框架) + redis(缓存中间件) + shiro(权限中间件) + mybatisplus(orm) + restful风格接口 + mysql(数据库)

开发环境：windows10 or windows7 ， vscode or webstorm ， idea + lambok

#### 管理员
公告信息、公司审核、投诉记录、订单评价、公司管理、消息管理、优惠券管理、消息通知、订单信息、付款记录、价格规则、员工管理、客户管理、车辆管理、提现记录、数据统计、订单年统计、订单月统计、

#### 商家
公司信息、订单评价、客户消息、订单信息、用户缴费、价格规则、员工管理、车辆管理、提现记录、贴子管理、公司审核、客户消息、接单中心、

#### 用户
小程序注册登录、个人信息修改、我的订单、我的消息、优惠券管理、社区交流、我的贴子、贴子发布、投诉管理、订单下单、订单配置选择、商家联系、订单投诉、订单评价、查看订单详情


#### 前台启动方式
安装所需文件 yarn install 
运行 yarn run dev

#### 默认后台账户密码
[管理员]
admin
1234qwer

[公司]
shangjia
1234qwer

[用户]
小程序登录

#### 项目截图

|  |  |
|---------------------|---------------------|
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737813745885.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737813533972.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737813738810.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737813952346.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737813705802.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737813943403.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737813698710.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737813936267.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737813673533.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737813926597.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737813660252.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737813911850.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737813654516.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737813901437.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737813645772.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737813830292.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737813629514.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737813817458.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737813623211.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737813766202.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737813616836.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737813758441.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737813610452.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737813752284.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737813600962.png) |  |


|  |  |
|---------------------|---------------------|
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737814147006.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737814067495.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737814140870.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737814048550.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737814125712.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737814042008.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737814116311.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737814023862.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737814107246.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737814015070.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737814098008.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737814002639.png) |
| ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737814082391.png) | ![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/1737814155847.png) |

#### 演示视频

暂无

#### 获取方式

Email: fan1ke2ke@gmail.com

WeChat: `Storm_Berserker`

`附带部署与讲解服务，因为要恰饭资源非免费，伸手党勿扰，谢谢理解😭`

> 1.项目纯原创，不做二手贩子 2.一次购买终身有效 3.项目讲解持续到答辩结束 4.非常负责的答辩指导 5.**黑奴价格**

> 项目部署调试不好包退！功能逻辑没讲明白包退！

![](https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/work/936e9baf53eb9a217af4f89c616dc19.png)

#### 其它资源

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

<p><img align="center" src="https://fank-bucket-oss.oss-cn-beijing.aliyuncs.com/img/%E5%90%88%E4%BD%9C%E7%89%A9%E6%96%99%E6%A0%B7%E5%BC%8F%20(3).png" alt="fankekeke" /></p>
