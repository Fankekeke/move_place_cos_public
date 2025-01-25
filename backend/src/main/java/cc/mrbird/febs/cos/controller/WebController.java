package cc.mrbird.febs.cos.controller;

import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.dao.NotifyInfoMapper;
import cc.mrbird.febs.cos.entity.*;
import cc.mrbird.febs.cos.service.*;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WebController {

    private final IUserInfoService userInfoService;

    private final IComplaintInfoService complaintInfoService;

    private final IBulletinInfoService bulletinInfoService;

    private final IDiscountInfoService discountInfoService;

    private final IPostInfoService postInfoService;

    private final IReplyInfoService replyInfoService;

    private final IMessageInfoService messageInfoService;

    private final IOrderInfoService orderInfoService;

    private final IEvaluateInfoService evaluationService;

    private final NotifyInfoMapper mobileInfoMapper;

    private final INotifyInfoService notifyInfoService;

    private final IMerchantInfoService merchantInfoService;

    private final IPaymentRecordService paymentRecordService;


    /**
     * File 转MultipartFile
     *
     * @param file
     * @return
     */
    public MultipartFile getMultipartFile(File file) {
        FileInputStream fileInputStream = null;
        MultipartFile multipartFile = null;
        try {
            fileInputStream = new FileInputStream(file);
            multipartFile = new MockMultipartFile(file.getName(), file.getName(),
                    ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return multipartFile;
    }

    @PostMapping("/userAdd")
    public R userAdd(@RequestBody UserInfo user) throws Exception {
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        url += "?appid=wx76a6577665633a86";//自己的appid
        url += "&secret=78070ccedf3f17b272b84bdeeca28a2e";//自己的appSecret
        url += "&js_code=" + user.getOpenId();
        url += "&grant_type=authorization_code";
        url += "&connect_redirect=1";
        String res = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse response = null;
        // 配置信息
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .setSocketTimeout(5000)
                .setRedirectsEnabled(false).build();
        httpget.setConfig(requestConfig);
        response = httpClient.execute(httpget);
        HttpEntity responseEntity = response.getEntity();
        System.out.println("响应状态为:" + response.getStatusLine());
        if (responseEntity != null) {
            res = EntityUtils.toString(responseEntity);
            System.out.println("响应内容长度为:" + responseEntity.getContentLength());
            System.out.println("响应内容为:" + res);
        }
        // 释放资源
        httpClient.close();
        response.close();
        String openid = JSON.parseObject(res).get("openid").toString();
        System.out.println("openid" + openid);
        int count = userInfoService.count(Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getOpenId, openid));
        if (count > 0) {
            return R.ok(userInfoService.getOne(Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getOpenId, openid)));
        } else {
            user.setOpenId(openid);
            user.setCreateDate(DateUtil.formatDateTime(new Date()));
            user.setName(user.getUserName());
            user.setCode("UR-" + System.currentTimeMillis());
            // 图片上传
            byte[] bytes = HttpUtil.downloadBytes(user.getAvatar());
            MultipartFile multipartFile = new MockMultipartFile("xxx.jpg", "xxx.jpg", ".jpg", bytes);
//            MultipartFile cMultiFile = new MockMultipartFile("file", file.getName(), null, new FileInputStream(file));
//
            // 1定义要上传文件 的存放路径
            String localPath = "G:/Project/20241226搬家小程序/db";
            // 2获得文件名字
            String fileName = multipartFile.getName();
            // 2上传失败提示
            String warning = "";
            String newFileName = cc.mrbird.febs.common.utils.FileUtil.upload(multipartFile, localPath, fileName);
            if (newFileName != null) {
                //上传成功
                warning = newFileName;
            } else {
                warning = "上传失败";
            }
            System.out.println(warning);
            user.setImages(warning);
            userInfoService.save(user);
            return R.ok(user);
        }
    }

    @RequestMapping("/openid")
    public R getUserInfo(@RequestParam(name = "code") String code) throws Exception {
        System.out.println("code" + code);
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        url += "?appid=wx9fffb151ded22005";//自己的appid
        url += "&secret=9666e94c91361e7de4d3a6d09a23402f";//自己的appSecret
        url += "&js_code=" + code;
        url += "&grant_type=authorization_code";
        url += "&connect_redirect=1";
        String res = null;
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // DefaultHttpClient();
        HttpGet httpget = new HttpGet(url);    //GET方式
        CloseableHttpResponse response = null;
        // 配置信息
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .setSocketTimeout(5000)
                .setRedirectsEnabled(false).build();
        httpget.setConfig(requestConfig);
        response = httpClient.execute(httpget);
        HttpEntity responseEntity = response.getEntity();
        System.out.println("响应状态为:" + response.getStatusLine());
        if (responseEntity != null) {
            res = EntityUtils.toString(responseEntity);
            System.out.println("响应内容长度为:" + responseEntity.getContentLength());
            System.out.println("响应内容为:" + res);
        }
        // 释放资源
        httpClient.close();
        response.close();
        String openid = JSON.parseObject(res).get("openid").toString();
        System.out.println("openid" + openid);
        return R.ok(openid);
    }

    @GetMapping("/subscription")
    public R subscription(@RequestParam("taskCode") String taskCode) throws Exception {
        LinkedHashMap<String, Object> tokenParam = new LinkedHashMap<String, Object>() {
            {
                put("grant_type", "client_credential");
                put("appid", "wx76a6577665633a86");
                put("secret", "78070ccedf3f17b272b84bdeeca28a2e");
            }
        };
        String tokenResult = HttpUtil.get("https://api.weixin.qq.com/cgi-bin/token", tokenParam);
        String token = JSON.parseObject(tokenResult).get("access_token").toString();
        LinkedHashMap<String, Object> data = new LinkedHashMap<String, Object>() {
            {
                put("thing1", new HashMap<String, Object>() {
                    {
                        put("value", "张三");
                    }
                });
                put("character_string3", new HashMap<String, Object>() {
                    {
                        put("value", taskCode);
                    }
                });
                put("time4", new HashMap<String, Object>() {
                    {
                        put("value", DateUtil.formatDateTime(new Date()));
                    }
                });
                put("thing5", new HashMap<String, Object>() {
                    {
                        put("value", "若查看详细内容，请登录小程序");
                    }
                });
            }
        };
        String url = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=" + token;
        LinkedHashMap<String, Object> subscription = new LinkedHashMap<String, Object>() {
            {
                put("touser", "oeDfR5zqxQD3EEA3uPT836qnauSc");
                put("template_id", "Z27pBK1n9WnKNtQ_fo7TC-nUJUlOQ9KVJk6LIgp0nH8");
                put("data", data);
            }
        };
        String result = HttpUtil.post(url, JSONUtil.toJsonStr(subscription));
        return R.ok(result);
    }

    /**
     * 获取用户信息
     *
     * @param userId 用户id
     * @return 结果
     */
    @GetMapping("/selectUserInfo")
    public R selectUserInfo(@RequestParam("userId") Integer userId) {
        return R.ok(userInfoService.getById(userId));
    }

    /**
     * 用户信息更新
     *
     * @param userInfo 用户信息
     * @return 结果
     */
    @PostMapping("/editUserInfo")
    public R editUserInfo(@RequestBody UserInfo userInfo) {
        return R.ok(userInfoService.updateById(userInfo));
    }

//
//    /**
//     * 进入小程序主页信息
//     *
//     * @return 结果
//     */
//    @GetMapping("/home")
//    public R home() {
//        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
//        result.put("commodityHot", commodityInfoService.getCommodityHot());
//        result.put("shopInfo", shopInfoService.shopInfoHot());
//        result.put("postInfo", postInfoService.getPostListHot());
//        return R.ok(result);
//    }

    /**
     * 根据用户获取消息信息
     *
     * @param userId 用户ID1
     * @return 结果
     */
    @GetMapping("/queryMessageByUser")
    public R queryMessageByUser(@RequestParam("userId") Integer userId) {
        return R.ok(mobileInfoMapper.selectList(Wrappers.<NotifyInfo>lambdaQuery().eq(NotifyInfo::getUserId, userId)));
    }

    /**
     * 删除消息信息
     *
     * @param messageId 消息ID
     * @return 结果
     */
    @GetMapping("/deleteMessage")
    public R deleteMessage(@RequestParam("messageId") Integer messageId) {
        return R.ok(notifyInfoService.update(Wrappers.<NotifyInfo>lambdaUpdate().set(NotifyInfo::getDelFlag, 1).eq(NotifyInfo::getId, messageId)));
    }

    /**
     * 查询用户投诉信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    @GetMapping("/queryComplaintListById")
    public R queryComplaintListById(@RequestParam Integer userId) {
        return R.ok(complaintInfoService.queryComplaintList(userId));
    }

    /**
     * 获取贴子信息
     *
     * @return 结果
     */
    @GetMapping("/getPostList")
    public R getPostList() {
        return R.ok(postInfoService.getPostList());
    }

    /**
     * 根据贴子编号获取详细信息
     *
     * @param postId
     * @return 结果
     */
    @GetMapping("/getPostInfoById")
    public R getPostInfoById(@RequestParam Integer postId) {
        return R.ok(postInfoService.getPostInfoById(postId));
    }

    /**
     * 贴子回复
     *
     * @return 结果
     */
    @PostMapping("/replyPost")
    public R replyPost(@RequestBody ReplyInfo replyInfo) {
        replyInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(replyInfoService.save(replyInfo));
    }

    /**
     * 添加贴子
     *
     * @param postInfo
     * @return 结果
     */
    @PostMapping("/postAdd")
    public R postAdd(@RequestBody PostInfo postInfo) {
        postInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        return R.ok(postInfoService.save(postInfo));
    }

    /**
     * 获取公告信息
     *
     * @return 结果
     */
    @GetMapping("/getBulletinList")
    public R getBulletinList() {
        return R.ok(bulletinInfoService.list());
    }

    /**
     * 查询消息信息
     *
     * @param userId
     * @return 结果
     */
    @GetMapping("/messageListById")
    public R messageListById(@RequestParam Integer userId) {
        return R.ok(messageInfoService.messageListById(userId));
    }

    /**
     * 查找聊天记录
     *
     * @param takeUser
     * @param sendUser
     * @return 结果
     */
    @GetMapping("/getMessageDetail")
    public R getMessageDetail(@RequestParam(value = "takeUser") Integer takeUser, @RequestParam(value = "sendUser") Integer sendUser, @RequestParam(value = "userId") Integer userId) {
        if (takeUser.equals(userId)) {
            messageInfoService.update(Wrappers.<MessageInfo>lambdaUpdate().set(MessageInfo::getTaskStatus, 1)
                    .eq(MessageInfo::getTakeUser, takeUser).eq(MessageInfo::getSendUser, sendUser));
        } else {
            messageInfoService.update(Wrappers.<MessageInfo>lambdaUpdate().set(MessageInfo::getTaskStatus, 1)
                    .eq(MessageInfo::getTakeUser, sendUser).eq(MessageInfo::getSendUser, takeUser));
        }
        return R.ok(messageInfoService.getMessageDetail(takeUser, sendUser));
    }

    /**
     * 消息回复
     *
     * @param messageInfo
     * @return 结果
     */
    @PostMapping("/messageReply")
    public R messageReply(@RequestBody MessageInfo messageInfo) {
        messageInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        messageInfo.setTaskStatus(0);
        return R.ok(messageInfoService.save(messageInfo));
    }
//
//    /**
//     * 商品详情
//     *
//     * @param commodityId
//     * @return 结果
//     */
//    @GetMapping("/goodsDetail")
//    public R goodsDetail(@RequestParam Integer commodityId) {
//        return R.ok(commodityInfoService.goodsDetail(commodityId));
//    }
//
//    /**
//     * 查询商品是否在购物车中
//     *
//     * @param userId
//     * @param commodityId
//     * @return 结果
//     */
//    @GetMapping("/selUserCartByGoodsId")
//    public R selUserCartByGoodsId(@RequestParam(value = "userId") Integer userId, @RequestParam(value = "commodityId") Integer commodityId) {
//        return R.ok(orderInfoService.count(Wrappers.<OrderInfo>lambdaQuery().eq(OrderInfo::getUserId, userId).eq(OrderInfo::getCommodityId, commodityId).eq(OrderInfo::getOrderStatus, 1)));
//    }
//
//    /**
//     * 添加到购物车
//     *
//     * @param orderInfo
//     * @return 结果
//     */
//    @PostMapping("/addGoodsCart")
//    public R addGoodsCart(@RequestBody OrderInfo orderInfo) {
//        orderInfo.setCode("ORD-" + System.currentTimeMillis());
//        orderInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
//        orderInfo.setOrderStatus(0);
//        orderInfo.setOrderPrice(orderInfo.getPrice());
//        // 查询用户默认地址
//        AddressInfo addressInfo = addressInfoService.getOne(Wrappers.<AddressInfo>lambdaQuery().eq(AddressInfo::getUserId, orderInfo.getUserId())
//                .eq(AddressInfo::getDefaultAddress, 1));
//        if (addressInfo != null) {
//            orderInfo.setAddressId(addressInfo.getId());
//        }
//
//        // 添加订单详情
//        OrderDetail orderDetail = new OrderDetail();
//        orderDetail.setCode(orderInfo.getCode());
//        orderDetail.setUserId(orderInfo.getUserId());
//        orderDetail.setOrderStatus("0");
//        orderDetail.setCreateDate(DateUtil.formatDateTime(new Date()));
//        orderDetail.setAddressId(orderInfo.getAddressId());
//        orderDetailService.save(orderDetail);
//
//        return R.ok(orderInfoService.save(orderInfo));
//    }
//
//    /**
//     * 查询用户购物车
//     *
//     * @param userId
//     * @return 结果
//     */
//    @GetMapping("/selGoodsCart")
//    public R selGoodsCart(@RequestParam Integer userId) {
//        return R.ok(orderInfoService.selGoodsCart(userId));
//    }
//
//    /**
//     * 根据订单ID获取订单信息
//     *
//     * @return 结果
//     */
//    @GetMapping("/selOrderListByOrderIds")
//    public R selOrderListByOrderIds(@RequestParam List<String> ids) {
//        return R.ok(orderInfoService.selOrderListByOrderIds(ids));
//    }
//
//    /**
//     * 购物车完成付款
//     *
//     * @param ids
//     * @return 结果
//     */
//    @GetMapping("/goodsCartComplete")
//    public R goodsCartComplete(@RequestParam List<String> ids) {
//        return R.ok(orderInfoService.update(Wrappers.<OrderInfo>lambdaUpdate().set(OrderInfo::getPayDate, DateUtil.formatDateTime(new Date()))
//                .set(OrderInfo::getUserNum, 1)
//                .set(OrderInfo::getOrderStatus, 1).in(OrderInfo::getId, ids)));
//    }
//
//    /**
//     * 购买商品
//     *
//     * @param orderInfo
//     * @return 结果
//     */
//    @PostMapping("/buyGoods")
//    public R buyGoods(@RequestBody OrderInfo orderInfo) {
//        orderInfo.setCode("ORD-" + System.currentTimeMillis());
//        orderInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
//        orderInfo.setOrderStatus(2);
//        return R.ok(orderInfoService.save(orderInfo));
//    }
//
//    /**
//     * 查询店铺及商品信息
//     *
//     * @return 结果
//     */
//    @GetMapping("/selShopDetailList")
//    public R selShopDetailList() {
//        return R.ok(commodityInfoService.selShopDetailList());
//    }
//
//    /**
//     * 获取商铺及商品详细信息
//     *
//     * @param shopId
//     * @return 结果
//     */
//    @GetMapping("/getShopDetail")
//    public R getShopDetail(@RequestParam Integer shopId) {
//        return R.ok(commodityInfoService.getShopDetail(shopId));
//    }
//
//    /**
//     * 店铺商品排序方式
//     *
//     * @param shopId
//     * @return 结果
//     */
//    @GetMapping("/shopCommoditSort")
//    public R shopCommoditySort(@RequestParam(value = "shopId") Integer shopId, @RequestParam(value = "type") Integer type) {
//        return R.ok(commodityInfoService.shopCommoditySort(shopId, type));
//    }
//
//    /**
//     * 模糊查询店内商品
//     *
//     * @param shopId
//     * @param key
//     * @return 结果
//     */
//    @GetMapping("/commodityLikeByShop")
//    public R commodityLikeByShop(@RequestParam(value = "shopId") Integer shopId, @RequestParam(value = "key") String key) {
//        return R.ok(commodityInfoService.commodityLikeByShop(shopId, key));
//    }
//
//    /**
//     * 查找商品或店铺
//     *
//     * @param key
//     * @return 结果
//     */
//    @GetMapping("/getGoodsFuzzy")
//    public R getGoodsFuzzy(String key) {
//        return R.ok(commodityInfoService.getGoodsFuzzy(key));
//    }
//

    /**
     * 进入小程序主页信息
     *
     * @return 结果
     */
    @GetMapping("/home")
    public R home() {
        LinkedHashMap<String, Object> result = new LinkedHashMap<>();
        result.put("shopInfo", merchantInfoService.list(Wrappers.<MerchantInfo>lambdaQuery().eq(MerchantInfo::getStatus, 1).last("limit 3")));
        result.put("postInfo", postInfoService.getPostListHot());
        return R.ok(result);
    }

    /**
     * 计算订单价格
     *
     * @param orderInfo 订单信息
     * @return 结果
     */
    @PostMapping("/calculateAmountResult")
    public R calculateAmountResult(@RequestBody OrderInfo orderInfo) {
        return R.ok(orderInfoService.calculateAmountResult(orderInfo));
    }

    /**
     * 添加订单信息
     *
     * @param orderInfo 订单信息
     * @return 结果
     */
    @PostMapping("/addOrder")
    @Transactional(rollbackFor = Exception.class)
    public R addOrder(@RequestBody OrderInfo orderInfo) {
        orderInfo.setCode("ORD-" + System.currentTimeMillis());
        orderInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        orderInfo.setStatus("0");
        // 获取用户信息
        UserInfo userInfo = userInfoService.getById(orderInfo.getUserId());
        // 添加通知
        NotifyInfo notifyInfo = new NotifyInfo(userInfo.getCode(), 0, DateUtil.formatDateTime(new Date()), userInfo.getName());
        notifyInfo.setContent("你好【" + orderInfo.getCode() + "】，此订单已付款，正在等待分配人员");
        notifyInfo.setUserId(userInfo.getId());
        notifyInfoService.save(notifyInfo);

        // 添加付款记录
        PaymentRecord paymentInfo = new PaymentRecord();
        paymentInfo.setAmount(orderInfo.getAfterAmount());
        paymentInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        paymentInfo.setUserCode(userInfo.getCode());
        paymentInfo.setOrderCode(orderInfo.getCode());
        paymentRecordService.save(paymentInfo);

        // 判断是否使用优惠券
        if (StrUtil.isNotEmpty(orderInfo.getDiscountCode())) {
            // 更新优惠券状态
            DiscountInfo discountInfo = discountInfoService.getOne(Wrappers.<DiscountInfo>lambdaQuery().eq(DiscountInfo::getCode, orderInfo.getDiscountCode()));
            discountInfo.setStatus("1");
            discountInfoService.updateById(discountInfo);
        }

        return R.ok(orderInfoService.save(orderInfo));
    }

    /**
     * 获取订单详情
     *
     * @param orderId 订单ID
     * @return 结果
     */
    @GetMapping("/queryOrderDetail")
    public R queryOrderDetail(Integer orderId) {
        OrderInfo orderInfo = orderInfoService.getById(orderId);
        return R.ok(orderInfoService.selectDetailByCode(orderInfo.getCode()));
    }

    /**
     * 添加评价信息
     *
     * @param evaluation
     * @return 结果
     */
    @PostMapping("/evaluationAdd")
    public R evaluationAdd(@RequestBody EvaluateInfo evaluation) {
        OrderInfo orderInfo = orderInfoService.getById(evaluation.getOrderId());
        // 获取用户编号
        UserInfo userInfo = userInfoService.getById(orderInfo.getUserId());
        evaluation.setOrderId(orderInfo.getId());
        evaluation.setOrderCode(orderInfo.getCode());
        evaluation.setUserCode(userInfo.getCode());
        evaluation.setCreateDate(DateUtil.formatDateTime(new Date()));
        evaluation.setMerchantId(orderInfo.getMerchantId());
        return R.ok(evaluationService.save(evaluation));
    }

    /**
     * 添加投诉信息
     *
     * @return 结果
     */
    @GetMapping("/complaintAdd")
    public R complaintAdd(Integer userId, Integer orderId, String content) {
        OrderInfo orderInfo = orderInfoService.getById(orderId);
        // 获取用户编号
        UserInfo userInfo = userInfoService.getById(orderInfo.getUserId());
        ComplaintInfo complaintInfo = new ComplaintInfo();
        complaintInfo.setContent(content);
        complaintInfo.setUserId(userId);
        complaintInfo.setOrderCode(orderInfo.getCode());
        complaintInfo.setStatus("0");
        complaintInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        complaintInfo.setMerchantId(orderInfo.getMerchantId());
        return R.ok(complaintInfoService.save(complaintInfo));
    }

    /**
     * 运输结束回调
     *
     * @param orderId 订单ID
     * @return 结果
     */
    @GetMapping("/receipt")
    public R receipt(Integer orderId) {
        return R.ok(orderInfoService.receipt(orderId));
    }

    /**
     * 获取搬家公司详情
     *
     * @param shopId 公司ID
     * @return 结果
     */
    @GetMapping("/getShopDetail")
    public R getShopDetail(Integer shopId) {
        // 返回数据
        LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>() {
            {
                put("shop", merchantInfoService.getById(shopId));
                put("evaluate", Collections.emptyList());
            }
        };
        // 评价信息
        List<LinkedHashMap<String, Object>> evaluateList = evaluationService.queryEvaluateByShopId(shopId);
        result.put("evaluate", evaluateList);
        return R.ok(result);
    }

    /**
     * 获取可用优惠券
     *
     * @param userId 用户ID
     * @return 结果
     */
    @GetMapping("/queryUseDiscountByUserId")
    public R queryUseDiscountByUserId(Integer userId, BigDecimal amount) {
        return R.ok(discountInfoService.queryUseDiscountByUserId(userId, amount));
    }

    /**
     * 获取用户所有订单
     *
     * @param userId
     * @return 结果
     */
    @GetMapping("/getOrderListByUserId")
    public R getOrderListByUserId(Integer userId) {
        return R.ok(orderInfoService.queryOrderByUserId(userId));
    }

    /**
     * 根据状态用户ID获取优惠券信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    @GetMapping("/queryDiscountSortByUserId")
    public R queryDiscountSortByUserId(Integer userId) {
        return R.ok(discountInfoService.queryDiscountSortByUserId(userId));
    }

    /**
     * 根据用户获取贴子信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    @GetMapping("/getPostByUser")
    public R getPostByUser(@RequestParam("userId") Integer userId) {
        return R.ok(postInfoService.getPostByUser(userId));
    }

    /**
     * 删除贴子信息
     *
     * @param postId 贴子ID
     * @return 结果
     */
    @GetMapping("/deletePost")
    public R deletePost(@RequestParam("postId") Integer postId) {
        return R.ok(postInfoService.removeById(postId));
    }
//
//    /**
//     * 订单收货
//     *
//     * @param orderId
//     * @return 结果
//     */
//    @GetMapping("/receipt")
//    public R receipt(Integer orderId) {
//        return R.ok(orderInfoService.update(Wrappers.<OrderInfo>lambdaUpdate().set(OrderInfo::getOrderStatus, 3).eq(OrderInfo::getId, orderId)));
//    }
//
//    /**
//     * 添加评价信息
//     *
//     * @param evaluation
//     * @return 结果
//     */
//    @PostMapping("/evaluationAdd")
//    public R evaluationAdd(@RequestBody Evaluation evaluation) {
//        OrderInfo orderInfo = orderInfoService.getById(evaluation.getOrderId());
//        List<OrderDetail> orderDetailList = orderDetailService.list(Wrappers.<OrderDetail>lambdaQuery().eq(OrderDetail::getCode, orderInfo.getCode()));
//        evaluation.setOrderId(orderDetailList.get(0).getId());
//        evaluation.setCreateDate(DateUtil.formatDateTime(new Date()));
//        return R.ok(evaluationService.save(evaluation));
//    }
//
//    /**
//     * 获取商品评价信息
//     *
//     * @param commodityId
//     * @return 结果
//     */
//    @GetMapping("/getEvaluationByGoods")
//    public R getEvaluationByGoods(Integer commodityId) {
//        return R.ok(evaluationService.getEvaluationByCommodityId(commodityId));
//    }
//
//    /**
//     * 添加商品信息
//     *
//     * @param commodityInfo
//     * @return 结果
//     */
//    @PostMapping("/addCommodity")
//    public R addCommodity(@RequestBody CommodityInfo commodityInfo) {
//        commodityInfo.setCode("C-" + System.currentTimeMillis());
//        commodityInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
//        ShopInfo shopInfo = shopInfoService.getOne(Wrappers.<ShopInfo>lambdaQuery().eq(ShopInfo::getUserId, commodityInfo.getShopId()));
//        commodityInfo.setShopId(shopInfo.getId());
//        return R.ok(commodityInfoService.save(commodityInfo));
//    }
//
//    /**
//     * 修改商品信息
//     *
//     * @param commodityInfo
//     * @return 结果
//     */
//    @PostMapping("/editCommodity")
//    public R editCommodity(@RequestBody CommodityInfo commodityInfo) {
//        return R.ok(commodityInfoService.updateById(commodityInfo));
//    }
//
//    /**
//     * 根据用户获取商品信息
//     *
//     * @param userId
//     * @return 结果
//     */
//    @GetMapping("/getGoodsByUserId")
//    public R getGoodsByUserId(Integer userId) {
//        return R.ok(commodityInfoService.getGoodsByUserId(userId));
//    }
//
//    /**
//     * 根据ID获取商品信息
//     *
//     * @param commodityId
//     * @return 结果
//     */
//    @GetMapping("/getGoodsInfoById")
//    public R getGoodsInfoById(@RequestParam Integer commodityId) {
//        return R.ok(commodityInfoService.getById(commodityId));
//    }
//
//    /**
//     * 根据用户获取卖出的订单
//     *
//     * @param userId
//     * @return 结果
//     */
//    @GetMapping("/getOrderByUserId")
//    public R getOrderByUserId(@RequestParam Integer userId) {
//        return R.ok(orderInfoService.getOrderByUserId(userId));
//    }
}
