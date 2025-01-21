const app = getApp();
let http = require('../../../utils/request')
Page({
    data: {
        StatusBar: app.globalData.StatusBar,
        CustomBar: app.globalData.CustomBar,
        TabbarBot: app.globalData.tabbar_bottom,
        TabCur: 1,
        scrollLeft: 0,
        SortMenu: [{
            id: 1,
            name: "使用中"
        }, {
            id: 2,
            name: "已失效"
        }],
        userInfo: null,
        useList: [],
        usedList: [],
    },
    onLoad: function (options) {
        let that = this;
    },
    onShow() {
        wx.getStorage({
          key: 'userInfo',
          success: (res) => {
            this.setData({ userInfo: res.data })
            this.getOrderListByUserId(res.data.id)
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
    tabSelect(e) {
        console.log(e.currentTarget.dataset.id);
        this.setData({
            TabCur: e.currentTarget.dataset.id,
            scrollLeft: (e.currentTarget.dataset.id - 1) * 60
        })
    },
    timeFormat(time) {
        var nowTime = new Date();
        var day = nowTime.getDate();
        var hours = parseInt(nowTime.getHours());
        var minutes = nowTime.getMinutes();
        // 开始分解付入的时间
        var timeday = time.substring(8, 10);
        var timehours = parseInt(time.substring(11, 13));
        var timeminutes = time.substring(14, 16);
        var d_day = Math.abs(day - timeday);
        var d_hours = hours - timehours;
        var d_minutes = Math.abs(minutes - timeminutes);
        if (d_day <= 1) {
            switch (d_day) {
                case 0:
                    if (d_hours == 0 && d_minutes > 0) {
                        return d_minutes + '分钟前';
                    } else if (d_hours == 0 && d_minutes == 0) {
                        return '1分钟前';
                    } else {
                        return d_hours + '小时前';
                    }
                    break;
                case 1:
                    if (d_hours < 0) {
                        return (24 + d_hours) + '小时前';
                    } else {
                        return d_day + '天前';
                    }
                    break;
            }
        } else if (d_day > 1 && d_day < 10) {
            return d_day + '天前';
        } else {
            return time;
        }
    },
    getOrderListByUserId(userId) {
        http.get('queryDiscountSortByUserId', {
            userId
        }).then((r) => {
            r.notUse.forEach(item => {
                item.days = this.timeFormat(item.createDate)
            });
            r.used.forEach(item => {
                item.days = this.timeFormat(item.createDate)
            });
            this.setData({
                useList: r.notUse,
                usedList: r.used
            })
        })
    },
});