const app = getApp();
let http = require('../../../utils/request')
Page({
    data: {
        StatusBar: app.globalData.StatusBar,
        CustomBar: app.globalData.CustomBar,
        TabbarBot: app.globalData.tabbar_bottom,
        orderInfo: null,
        merchant: null,
        user: null,
        staff: [],
        vehicle: null
    },
    onLoad: function (options) {
        this.getOrderDetail(options.orderId)
    },
    message(event) {
        wx.getStorage({
            key: 'userInfo',
            success: (res) => {
                wx.navigateTo({
                    url: '/pages/user/detail/index?takeUser=' + event.currentTarget.dataset.shopid + '&sendUser=' + res.data.id + '&otherName=' + event.currentTarget.dataset.shopname + ''
                });
            },
            fail: res => {
                wx.showToast({
                    title: '请先进行登录',
                    icon: 'none',
                    duration: 2000
                })
            }
        })
    },
    takePhone() {
        wx.makePhoneCall({
            phoneNumber: '1340000'
          })
    },
    getOrderDetail(orderId) {
        http.get('queryOrderDetail', {orderId: orderId}).then((r) => {
            console.log(r)
            // r.data.forEach(item => {
            //     item.image = item.images.split(',')[0]
            // });
            let merchant = r.merchant;
            if (merchant.images) {
                merchant.image = merchant.images.split(',')[0]
            }
            let staff = r.staff;
            let user = r.user;
            staff.forEach(item => {
                item.image = item.images.split(',')[0]
            });
            let order = r.order;
            if (order.images) {
                order.imageList = order.images.split(',')
            }
            let vehicle = r.vehicle;
			this.setData({
                orderInfo: order,
                merchant,
                staff,
                vehicle,
                user
            })
            console.log(this.data.orderInfo)
		})
    }
});
