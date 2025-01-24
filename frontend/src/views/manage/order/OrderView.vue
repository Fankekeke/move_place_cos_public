<template>
  <a-modal v-model="show" title="订单详情" @cancel="onClose" :width="900">
    <template slot="footer">
      <a-button key="back" @click="onClose" type="danger">
        关闭
      </a-button>
    </template>
    <div style="font-size: 13px;font-family: SimHei" v-if="orderData !== null">
      <div style="padding-left: 24px;padding-right: 24px;margin-bottom: 50px;margin-top: 50px">
        <a-steps :current="current" progress-dot size="small">
          <a-step title="等待接单" />
          <a-step title="正在分配" />
          <a-step title="运输中" />
          <a-step title="运输完成" />
        </a-steps>
      </div>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">基础信息</span></a-col>
        <a-col :span="8"><b>订单编号：</b>
          {{ orderData.code }}
        </a-col>
        <a-col :span="8"><b>客户名称：</b>
          {{ orderData.userName ? orderData.userName : '- -' }}
        </a-col>
        <a-col :span="8"><b>联系方式：</b>
          {{ orderData.phone ? orderData.phone : '- -' }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>当前状态：</b>
          <span v-if="orderData.status == '0'">等待接单</span>
          <span v-if="orderData.status == '1'">正在分配</span>
          <span v-if="orderData.status == '2'">运输中</span>
          <span v-if="orderData.status == '3'">运输完成</span>
        </a-col>
        <a-col :span="8"><b>订单金额：</b>
          {{ orderData.amount }} 元
        </a-col>
        <a-col :span="8"><b>下单时间：</b>
          {{ orderData.createDate }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="8"><b>实际价格：</b>
          {{ orderData.afterAmount }} 元
        </a-col>
        <a-col :span="8"><b>预约时间：</b>
          {{ orderData.appointmentTime }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="24"><b>备注：</b>
          {{ orderData.remark }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">运输配置</span></a-col>
        <a-col :span="8"><b>是否需要车辆：</b>
          <span v-if="orderData.vehicleOptions == null">不需要车辆</span>
          <span v-if="orderData.vehicleOptions == 1">大型车</span>
          <span v-if="orderData.vehicleOptions == 2">中型车</span>
          <span v-if="orderData.vehicleOptions == 3">小型车</span>
        </a-col>
        <a-col :span="8"><b>搬运工：</b>
          {{ orderData.staffOptions }} 个
        </a-col>
        <a-col :span="8"><b>是否有电梯：</b>
          <span v-if="orderData.hasElevator == 0" style="color: red">无电梯</span>
          <span v-if="orderData.hasElevator == 1" style="color: red">有电梯</span>
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col :span="24"><b>起始地址：</b>
          {{ orderData.startAddress }}
        </a-col>
        <br/>
        <br/>
        <a-col :span="24"><b>运输地址：</b>
          {{ orderData.endAddress }}
        </a-col>
        <br/>
        <br/>
        <a-col :span="24"><b>总距离：</b>
          {{ orderData.distanceLength }} 千米
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">图册</span></a-col>
        <a-col :span="24">
          <a-upload
            name="avatar"
            action="http://127.0.0.1:9527/file/fileUpload/"
            list-type="picture-card"
            :file-list="fileList"
            @preview="handlePreview"
            @change="picHandleChange"
          >
          </a-upload>
          <a-modal :visible="previewVisible" :footer="null" @cancel="handleCancel">
            <img alt="example" style="width: 100%" :src="previewImage" />
          </a-modal>
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;" v-if="userInfo !== null">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">用户信息</span></a-col>
        <a-col :span="8"><b>客户编号：</b>
          {{ userInfo.code ? userInfo.code : '- -' }}
        </a-col>
        <a-col :span="8"><b>客户名称：</b>
          {{ userInfo.name ? userInfo.name : '- -' }}
        </a-col>
        <a-col :span="8"><b>联系方式：</b>
          {{ userInfo.phone ? userInfo.phone : '- -' }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;" v-if="merchantInfo !== null">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">搬家公司</span></a-col>
        <a-col :span="8"><b>公司编号：</b>
          {{ merchantInfo.code ? merchantInfo.code : '- -' }}
        </a-col>
        <a-col :span="8"><b>公司名称：</b>
          {{ merchantInfo.name ? merchantInfo.name : '- -' }}
        </a-col>
        <a-col :span="8"><b>负责人：</b>
          {{ merchantInfo.principal ? merchantInfo.principal : '- -' }}
        </a-col>
        <br/>
        <br/>
        <a-col :span="8"><b>联系方式：</b>
          {{ merchantInfo.phone ? merchantInfo.phone : '- -' }}
        </a-col>
        <a-col :span="16"><b>详细地址：</b>
          {{ merchantInfo.address ? merchantInfo.address : '- -' }}
        </a-col>
      </a-row>
      <br/>
      <a-row style="padding-left: 24px;padding-right: 24px;" v-if="vehicleInfo && vehicleInfo !== null">
        <a-col style="margin-bottom: 15px"><span style="font-size: 15px;font-weight: 650;color: #000c17">车辆信息</span></a-col>
        <a-col :span="8"><b>车辆编号：</b>
          {{ vehicleInfo.vehicleCode ? vehicleInfo.vehicleCode : '- -' }}
        </a-col>
        <a-col :span="8"><b>车牌号码：</b>
          {{ vehicleInfo.vehicleNo ? vehicleInfo.vehicleNo : '- -' }}
        </a-col>
        <a-col :span="8"><b>车辆品牌：</b>
          {{ vehicleInfo.vehicleBrand ? vehicleInfo.vehicleBrand : '- -' }}
        </a-col>
        <br/>
        <br/>
        <a-col :span="8"><b>联系方式：</b>
          <span v-if="vehicleInfo.vehicleType == '1'">大型车</span>
          <span v-if="vehicleInfo.vehicleType == '2'">中型车</span>
          <span v-if="vehicleInfo.vehicleType == '3'">小型车</span>
        </a-col>
        <a-col :span="16"><b>车辆颜色：</b>
          {{ vehicleInfo.vehicleColor ? vehicleInfo.vehicleColor : '- -' }}
        </a-col>
      </a-row>
    </div>
  </a-modal>
</template>

<script>
import moment from 'moment'
moment.locale('zh-cn')
function getBase64 (file) {
  return new Promise((resolve, reject) => {
    const reader = new FileReader()
    reader.readAsDataURL(file)
    reader.onload = () => resolve(reader.result)
    reader.onerror = error => reject(error)
  })
}
export default {
  name: 'orderView',
  props: {
    orderShow: {
      type: Boolean,
      default: false
    },
    orderData: {
      type: Object
    }
  },
  computed: {
    show: {
      get: function () {
        return this.orderShow
      },
      set: function () {
      }
    }
  },
  data () {
    return {
      loading: false,
      fileList: [],
      previewVisible: false,
      previewImage: '',
      repairInfo: null,
      reserveInfo: null,
      durgList: [],
      logisticsList: [],
      current: 0,
      userInfo: null,
      vehicleInfo: null,
      staffList: [],
      merchantInfo: null
    }
  },
  watch: {
    orderShow: function (value) {
      if (value) {
        this.dataInit(this.orderData.code)
        this.imagesInit(this.orderData.images)
        this.current = this.orderData.status
      }
    }
  },
  methods: {
    dataInit (orderCode) {
      this.$get(`/cos/order-info/detail/${orderCode}`).then((r) => {
        this.userInfo = r.data.user
        this.staffList = r.data.staff
        this.merchantInfo = r.data.merchant
        this.vehicleInfo = r.data.vehicle
      })
    },
    imagesInit (images) {
      if (images !== null && images !== '') {
        let imageList = []
        images.split(',').forEach((image, index) => {
          imageList.push({uid: index, name: image, status: 'done', url: 'http://127.0.0.1:9527/imagesWeb/' + image})
        })
        this.fileList = imageList
      }
    },
    handleCancel () {
      this.previewVisible = false
    },
    async handlePreview (file) {
      if (!file.url && !file.preview) {
        file.preview = await getBase64(file.originFileObj)
      }
      this.previewImage = file.url || file.preview
      this.previewVisible = true
    },
    picHandleChange ({ fileList }) {
      this.fileList = fileList
    },
    onClose () {
      this.$emit('close')
    }
  }
}
</script>

<style scoped>

</style>
