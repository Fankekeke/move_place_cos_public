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
        fileList: [],
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
        remark: {
            show: false,
            value: ''
        },
        appointmentTime: '',
        appointmentTimeShow: false,
        calculateAmountInfo: null,
        discount: {
            show: false,
            label: '',
            value: '',
            options: []
        }
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
    calculateAmountResult() {
        let data = {
            userId: this.data.userInfo.id,
            vehicleOptions: this.data.vehicle.value,
            staffOptions: this.data.staff.value,
            startAddress: this.data.startPoint.startAddress,
            endAddress: this.data.endPoint.endAddress,
            startLongitude: this.data.startPoint.point.longitude,
            startLatitude: this.data.startPoint.point.latitude,
            endLongitude: this.data.endPoint.point.longitude,
            endLatitude: this.data.endPoint.point.latitude,
            hasElevator: this.data.elevator.value,
            discountCode: this.data.discount.value
        }
        http.post('calculateAmountResult', data).then((r) => {
            this.setData({
                calculateAmountInfo: r
            })
            this.queryUseDiscountByUserId(this.data.userInfo.id, r.amount)
        })
    },
    afterRead(event) {
        const {
            file
        } = event.detail;
        let that = this
        // 当设置 mutiple 为 true 时, file 为数组格式，否则为对象格式
        wx.uploadFile({
            url: 'http://127.0.0.1:9527/file/fileUpload', // 仅为示例，非真实的接口地址
            filePath: file.url,
            name: 'avatar',
            success(res) {
                // 上传完成需要更新 fileList
                const {
                    fileList = []
                } = that.data;
                fileList.push({
                    ...file,
                    url: res.data
                });
                that.setData({
                    fileList
                });
            },
        });
    },
    queryUseDiscountByUserId(userId, amount) {
        http.get('queryUseDiscountByUserId', {
            userId,
            amount
        }).then((r) => {
            r.data.forEach(item => {
                item.text = item.couponName
            })
            this.setData({
                'discount.options': r.data
            })
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
        } else if (e.currentTarget.dataset.type == 5) {
            this.setData({
                'discount.show': true,
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
        } else if (e.currentTarget.dataset.type == 5) {
            this.setData({
                'discount.show': false,
            })
        }
    },
    onRemarkChange(e) {
        this.setData({
            'remark.value':  e.detail,
        })
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
        } else if (e.currentTarget.dataset.type == 5) {
            this.setData({
                'discount.label': e.detail.value.couponName,
                'discount.value': e.detail.value.code,
                'discount.show': false
            })
            this.calculateAmountResult()
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
        if (this.data.fileList.length === 0) {
            wx.showToast({
                title: '请上传货物图片',
                icon: 'none',
                duration: 2000
            })
            return false
        }
        wx.showLoading({
            title: '正在模拟支付',
        })
        setTimeout(() => {
            let images = []
            this.data.fileList.forEach(item => {
              images.push(item.url)
            });
            let data = {
                userId: this.data.userInfo.id,
                vehicleOptions: this.data.vehicle.value,
                staffOptions: this.data.staff.value,
                startAddress: this.data.startPoint.startAddress,
                endAddress: this.data.endPoint.endAddress,
                startLongitude: this.data.startPoint.point.longitude,
                startLatitude: this.data.startPoint.point.latitude,
                endLongitude: this.data.endPoint.point.longitude,
                endLatitude: this.data.endPoint.point.latitude,
                hasElevator: this.data.elevator.value,
                discountCode: this.data.discount.value,
                distanceLength: this.data.calculateAmountInfo.distanceLength,
                amount: this.data.calculateAmountInfo.amount,
                remark: this.data.remark.value,
                discountAmount: this.data.calculateAmountInfo.discountAmount,
                afterAmount: this.data.calculateAmountInfo.afterAmount,
                appointmentTime: this.data.appointmentTime,
                images: images.length !== 0 ? images.join(',') : null
            }
            http.post('addOrder', data).then((r) => {
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
            wx.hideLoading()
        }, 1000)
    }
});