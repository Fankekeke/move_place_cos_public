### 基于SpringBoot + Vue的搬家小程序平台.

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
