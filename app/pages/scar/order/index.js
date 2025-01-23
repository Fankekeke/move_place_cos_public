const app = getApp();
let http = require('../../../utils/request')
Page({
    data: {
        StatusBar: app.globalData.StatusBar,
        CustomBar: app.globalData.CustomBar,
        TabbarBot: app.globalData.tabbar_bottom,
        orderType: 0,
        orderIds: [],
        addressInfo: null,
        commodityId: null,
        orderList: [],
        allPrice: 0,
        userInfo: null,
        startPoint: {
            startAddress: '',
            point: null
        },
        endPoint: {
            endAddress: '',
            point: null
        },
        vehicle: {
            show: false,
            label: '小型车',
            value: '2',
            options: ['大型车', '中型车', '小型车']
        },
        staff: {
            show: false,
            label: '一个',
            value: '1',
            options: ['不需要', '一个', '两个', '三个']
        },
        elevator: {
            show: false,
            label: '有',
            value: '1',
            options: ['无', '有']
        },
        appointmentTime: '',
        appointmentTimeShow: false

    },
    onLoad: function (options) {
        wx.getStorage({
            key: 'userInfo',
            success: (res) => {
                console.log(res.data)
                this.setData({
                    userInfo: res.data
                })
                let address = JSON.parse(options.address)
                this.setData({
                    startPoint: address.startPoint,
                    endPoint: address.endPoint,
                    appointmentTime: this.formatDate(new Date())
                })
                //this.getUserAddress(res.data.id)
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

    openPopup(e) {
        console.log(e.currentTarget.dataset.type)
        if (e.currentTarget.dataset.type == 1) {
            this.setData({
                'vehicle.show': true,
            })
        } else if (e.currentTarget.dataset.type == 2) {
            this.setData({
                'staff.show': true,
            })
        } else if (e.currentTarget.dataset.type == 3) {
            this.setData({
                'elevator.show': true,
            })
        } else if (e.currentTarget.dataset.type == 4) {
            this.setData({
                appointmentTimeShow: true,
            })
        }
    },
    onClose(e) {
        if (e.currentTarget.dataset.type == 1) {
            this.setData({
                'vehicle.show': false,
            })
        } else if (e.currentTarget.dataset.type == 2) {
            this.setData({
                'staff.show': false,
            })
        } else if (e.currentTarget.dataset.type == 3) {
            this.setData({
                'elevator.show': false,
            })
        } else if (e.currentTarget.dataset.type == 4) {
            this.setData({
                appointmentTimeShow: false,
            })
        }
    },
    onChange(e) {
        if (e.currentTarget.dataset.type == 1) {
            this.setData({
                'vehicle.label': e.detail.value,
                'vehicle.value': e.detail.index + 1,
                'vehicle.show': false
            })
        } else if (e.currentTarget.dataset.type == 2) {
            this.setData({
                'staff.label': e.detail.value,
                'staff.value': e.detail.index,
                'staff.show': false
            })
        } else if (e.currentTarget.dataset.type == 3) {
            this.setData({
                'elevator.label': e.detail.value,
                'elevator.value': e.detail.index,
                'elevator.show': false
            })
        }
    },
    onConfirm(event) {
        this.setData({
            appointmentTimeShow: false,
            appointmentTime: this.formatDate(event.detail),
        });
    },
    formatDate(date) {
        date = new Date(date);
        return `${date.getFullYear()}-${date.getMonth() + 1}-${date.getDate()}`;
    },
    calculateAmountResult(data) {
        http.post('calculateAmountResult', data).then((r) => {
            //this.setData({ addressInfo: r.data })
        })
    },
    getUserAddress(userId) {
        http.get('selDefaultAddress', {
            userId
        }).then((r) => {
            this.setData({
                addressInfo: r.data
            })
        })
    },
    getGoodsDetail(commodityId) {
        http.get('goodsDetail', {
            commodityId
        }).then((r) => {
            let order = r
            let allPrice = order.price
            order.image = order.images.split(',')[0]
            this.setData({
                orderList: [order],
                allPrice: allPrice.toFixed(2)
            })
        })
    },
    getOrderList(orderIds) {
        http.get('selOrderListByOrderIds', {
            ids: orderIds.join(',')
        }).then((r) => {
            let allPrice = 0
            r.data.forEach(item => {
                item.image = item.images.split(',')[0]
                allPrice += item.price
            });
            this.setData({
                orderList: r.data,
                allPrice: allPrice.toFixed(2)
            })
        })
    },
    submit() {
        wx.showLoading({
            title: '正在模拟支付',
        })
        setTimeout(() => {
            if (this.data.orderType == 1) {
                let data = {
                    ids: this.data.orderIds.join(',')
                }
                http.get('goodsCartComplete', data).then((r) => {
                    wx.showToast({
                        title: '支付成功',
                        icon: 'success',
                        duration: 1000
                    })
                    setTimeout(() => {
                        wx.navigateBack({
                            changed: true
                        });
                    }, 1000)
                })
            } else {
                let data = {
                    commodityId: this.data.commodityId,
                    price: this.data.allPrice,
                    addressId: this.data.addressInfo.id,
                    userId: this.data.userInfo.id
                }
                http.post('buyGoods', data).then((r) => {
                    wx.showToast({
                        title: '支付成功',
                        icon: 'success',
                        duration: 1000
                    })
                    setTimeout(() => {
                        wx.navigateBack({
                            changed: true
                        });
                    }, 1000)
                })
            }
            wx.hideLoading()
        }, 1000)
    }
});