const app = getApp();
let http = require('../../../utils/request')
Page({
	data: {
		StatusBar: app.globalData.StatusBar,
		CustomBar: app.globalData.CustomBar,
		hidden: true,
		current: 0,
		lines: 0,
		swiperlist: [{
			id: 0,
			url: 'https://www.huolala.cn/rs/img/housemove/tab_1_bg.png',
			type: 1
		}, {
			id: 1,
			url: 'https://www.huolala.cn/rs/img/housemove/tab_3_bg.png',
			type: 2
		}, {
			id: 2,
			url: 'https://www.huolala.cn/rs/img/housemove/tab_4_bg.png',
			type: 3
		}],
		iconList: [{
			id: 1,
			icon: 'questionfill',
			color: 'red',
			name: '公告',
			type: 1
		}, {
			id: 3,
			icon: 'shopfill',
			color: 'yellow',
			name: '论坛',
			type: 3
		}],
		Headlines: [{
			id: 1,
			title: "搬家公司入驻",
			type: 1
		}, {
			id: 2,
			title: "搬家大作战",
			type: 2
		}],
		shopInfo: [],
		postInfo: [],
		commodityHot: [],
		keys: '',
		videosrc: "http://wxsnsdy.tc.qq.com/105/20210/snsdyvideodownload?filekey=30280201010421301f0201690402534804102ca905ce620b1241b726bc41dcff44e00204012882540400&bizid=1023&hy=SH&fileparam=302c020101042530230204136ffd93020457e3c4ff02024ef202031e8d7f02030f42400204045a320a0201000400",
		startPoint: {
			startAddress: '',
			point: null
		},
		endPoint: {
			endAddress: '',
			point: null
		},
	},
	onLoad: function () {
		/*console.log(app.globalData.StatusBar);
		console.log(app.globalData.CustomBar);*/
		// wx.getSetting({
		//     success: res => {
		//         if (!res.authSetting['scope.userInfo']) {
		//             wx.redirectTo({
		//               	url: '/pages/auth/auth'
		//             })
		//         }
		//     }
		// });
		this.home()
	},
	/**
   * 选择位置
   */
  startChooseLocation() {
	    const _this = this;
	    wx.chooseLocation({
	      success(res) {
					console.log(res)
	        _this.setData({
	          ['startPoint.startAddress']: res.address,
	          ['startPoint.point']: {latitude: res.latitude, longitude: res.longitude},
	        })
	      },
	      fail(e) {
	        console.log(e);
	      }
	    })
	  },
	/**
   * 选择位置
   */
  endChooseLocation() {
	    const _this = this;
	    wx.chooseLocation({
	      success(res) {
					console.log(res)
	        _this.setData({
	          ['endPoint.endAddress']: res.address,
	          ['endPoint.point']: {latitude: res.latitude, longitude: res.longitude},
	        })
	      },
	      fail(e) {
	        console.log(e);
	      }
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
	shopDeatil(e) {
		wx.navigateTo({
			url: '/pages/shop/index/index?shopId=' + e.currentTarget.dataset.shopid + ''
		});
	},
	home() {
		http.get('home').then((r) => {
			console.log(r)

			r.shopInfo.forEach(item => {
				if (item.images) {
					item.image = item.images.split(',')[0]
				}
			});
			r.postInfo.forEach(item => {
				if (item.images) {
					item.image = item.images.split(',')[0]
				}
				item.days = this.timeFormat(item.createDate)
			});
			this.setData({
				shopInfo: r.shopInfo,
				postInfo: r.postInfo,
				commodityHot: r.commodityHot
			})
		})
	},
	postDetail(event) {
		wx.navigateTo({
			url: '/pages/coupon/detail/index?postId=' + event.currentTarget.dataset.postid + ''
		});
	},
	swiperchange: function (e) {
		this.setData({
			current: e.detail.current
		});
	},
	swipclick: function (e) {
		let that = this;
		var swip = that.data.swiperlist[that.data.current];
		console.log(swip);
		if (swip.type === 1) {
			wx.navigateTo({
				url: '/pages/home/doc/index?id=' + swip.id
			});
		}
	},
	lineschange: function (e) {
		this.setData({
			lines: e.detail.current
		});
	},
	linesclick: function (e) {
		let that = this;
		var swip = that.data.Headlines[that.data.current];
		console.log(swip);
		if (swip.type === 1) {
			wx.navigateTo({
				url: '/pages/home/doc/index?id=' + swip.id
			});
		}
	},
	itemckcred: function (e) {
		let that = this;
		var item = e.currentTarget.dataset;
		console.log(item.index, item.itemtype)
		if (item.itemtype === 1) {
			wx.navigateTo({
				url: '/pages/home/bulletin/index'
			});
		}
		if (item.itemtype === 2) {
			wx.navigateTo({
				url: '/pages/home/groom/index'
			});
		}
		if (item.itemtype === 3) {
			wx.navigateTo({
				url: '/pages/coupon/index/index'
			});
		}
		if (item.itemtype === 4) {
			wx.navigateTo({
				url: '/pages/home/search/index?key=生鲜'
			});
		}
	},
	getKeysValue: function (e) {
		this.setData({
			keys: e.detail.value
		})
	},
	search: function () {
		wx.navigateTo({
			url: '/pages/home/search/index?key=' + this.data.keys + ''
		});
	}
});