<template>
  <a-card :bordered="false" class="card-area">
    <a-row :gutter="25">
      <a-col :span="5">
        <a-card hoverable style="width: 100%">
          <img
            slot="cover"
            alt="example"
            src="https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png"
          />
          <template slot="actions" class="ant-card-actions">
            <a-icon key="edit" type="edit" @click="auditBalance"/>
          </template>
          <a-card-meta :title="merchantInfo.name" :description="'余额 ' + merchantInfo.balance + '元'">
            <a-avatar
              slot="avatar"
              :src="'http://127.0.0.1:9527/imagesWeb/' + merchantInfo.images.split(',')[0]"
            />
          </a-card-meta>
        </a-card>
      </a-col>
      <a-col :span="19">
        <div :class="advanced ? 'search' : null">
          <!-- 搜索区域 -->
          <a-form layout="horizontal">
            <a-row :gutter="15">
              <div :class="advanced ? null: 'fold'">
                <a-col :md="6" :sm="24">
                  <a-form-item
                    label="公司编号"
                    :labelCol="{span: 8}"
                    :wrapperCol="{span: 15, offset: 1}">
                    <a-input v-model="queryParams.merchantCode"/>
                  </a-form-item>
                </a-col>
                <a-col :md="6" :sm="24">
                  <a-form-item
                    label="公司名称"
                    :labelCol="{span: 8}"
                    :wrapperCol="{span: 15, offset: 1}">
                    <a-input v-model="queryParams.merchantName"/>
                  </a-form-item>
                </a-col>
                <a-col :md="6" :sm="24">
                  <a-form-item
                    label="提现状态"
                    :labelCol="{span: 8}"
                    :wrapperCol="{span: 15, offset: 1}">
                    <a-select v-model="queryParams.status" allowClear>
                      <a-select-option value="0">待审核</a-select-option>
                      <a-select-option value="1">通过</a-select-option>
                      <a-select-option value="2">驳回</a-select-option>
                    </a-select>
                  </a-form-item>
                </a-col>
              </div>
              <span style="float: right; margin-top: 3px;">
            <a-button type="primary" @click="search">查询</a-button>
            <a-button style="margin-left: 8px" @click="reset">重置</a-button>
          </span>
            </a-row>
          </a-form>
        </div>
        <div>
          <div class="operator">
            <!--        <a-button type="primary" ghost @click="add">新增</a-button>-->
            <a-button @click="batchDelete">删除</a-button>
          </div>
          <!-- 表格区域 -->
          <a-table ref="TableInfo"
                   :columns="columns"
                   :rowKey="record => record.id"
                   :dataSource="dataSource"
                   :pagination="pagination"
                   :loading="loading"
                   :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
                   :scroll="{ x: 900 }"
                   @change="handleTableChange">
            <template slot="operation" slot-scope="text, record">
              <a-icon v-if="record.status == 0" type="setting" theme="twoTone" twoToneColor="#4a9ff5" @click="withdrawAuditOpen(record)" title="审 核"></a-icon>
              <a-icon type="file-search" @click="withdrawViewOpen(record)" title="详 情" style="margin-left: 15px"></a-icon>
            </template>
          </a-table>
        </div>
      </a-col>
    </a-row>
    <withdraw-add
      v-if="withdrawAdd.visiable"
      @close="handlewithdrawAddClose"
      @success="handlewithdrawAddSuccess"
      :withdrawAddVisiable="withdrawAdd.visiable">
    </withdraw-add>
    <withdraw-edit
      ref="withdrawEdit"
      @close="handlewithdrawEditClose"
      @success="handlewithdrawEditSuccess"
      :withdrawEditVisiable="withdrawEdit.visiable">
    </withdraw-edit>
    <withdraw-view
      @close="handlewithdrawViewClose"
      :withdrawShow="withdrawView.visiable"
      :withdrawData="withdrawView.data">
    </withdraw-view>
    <withdraw-audit
      @close="handlewithdrawAuditClose"
      @auditSuccess="handlewithdrawViewSuccess"
      :withdrawShow="withdrawAudit.visiable"
      :withdrawData="withdrawAudit.data">
    </withdraw-audit>
  </a-card>
</template>

<script>
import RangeDate from '@/components/datetime/RangeDate'
import withdrawAdd from './WithdrawAdd'
import withdrawEdit from './WithdrawEdit'
import withdrawView from './WithdrawView.vue'
import withdrawAudit from './WithdrawAudit.vue'
import {mapState} from 'vuex'
import moment from 'moment'
moment.locale('zh-cn')

export default {
  name: 'withdraw',
  components: {withdrawAdd, withdrawEdit, withdrawView, RangeDate, withdrawAudit},
  data () {
    return {
      advanced: false,
      withdrawAdd: {
        visiable: false
      },
      withdrawEdit: {
        visiable: false
      },
      withdrawView: {
        visiable: false,
        data: null
      },
      withdrawAudit: {
        visiable: false,
        data: null
      },
      queryParams: {},
      filteredInfo: null,
      sortedInfo: null,
      paginationInfo: null,
      merchantInfo: null,
      dataSource: [],
      selectedRowKeys: [],
      loading: false,
      pagination: {
        pageSizeOptions: ['10', '20', '30', '40', '100'],
        defaultCurrent: 1,
        defaultPageSize: 10,
        showQuickJumper: true,
        showSizeChanger: true,
        showTotal: (total, range) => `显示 ${range[0]} ~ ${range[1]} 条记录，共 ${total} 条记录`
      },
      userList: []
    }
  },
  computed: {
    ...mapState({
      currentUser: state => state.account.user
    }),
    columns () {
      return [{
        title: '所属公司',
        dataIndex: 'merchantName'
      }, {
        title: '照片',
        dataIndex: 'images',
        customRender: (text, record, index) => {
          if (!record.images) return <a-avatar shape="square" icon="user" />
          return <a-popover>
            <template slot="content">
              <a-avatar shape="square" size={132} icon="user" src={ 'http://127.0.0.1:9527/imagesWeb/' + record.images.split(',')[0] } />
            </template>
            <a-avatar shape="square" icon="user" src={ 'http://127.0.0.1:9527/imagesWeb/' + record.images.split(',')[0] } />
          </a-popover>
        }
      }, {
        title: '联系方式',
        dataIndex: 'principal',
        customRender: (text, row, index) => {
          if (text !== null) {
            return text
          } else {
            return '- -'
          }
        }
      }, {
        title: '提现金额',
        dataIndex: 'balance',
        customRender: (text, row, index) => {
          if (text !== null) {
            return text
          } else {
            return '- -'
          }
        }
      }, {
        title: '审核状态',
        dataIndex: 'auditStatus',
        customRender: (text, row, index) => {
          switch (text) {
            case '0':
              return <a-tag>待审核</a-tag>
            case '1':
              return <a-tag color="green">通过</a-tag>
            case '2':
              return <a-tag color="red">驳回</a-tag>
            default:
              return '- -'
          }
        }
      }, {
        title: '创建时间',
        dataIndex: 'createDate',
        customRender: (text, row, index) => {
          if (text !== null) {
            return text
          } else {
            return '- -'
          }
        }
      }, {
        title: '操作',
        dataIndex: 'operation',
        scopedSlots: {customRender: 'operation'}
      }]
    }
  },
  mounted () {
    this.fetch()
    this.getmerchantByUser()
  },
  methods: {
    auditBalance () {
      if (this.merchantInfo.balance === null || this.merchantInfo.balance === 0) {
        this.$message.error('账户余额不足')
        return false
      } else {
        this.$post('/cos/withdrawal-record', { merchantId: this.merchantInfo.id, balance: this.merchantInfo.balance }).then((r) => {
          this.$message.success('提交申请成功')
          this.search()
        })
      }
    },
    getmerchantByUser () {
      this.$get('/cos/merchant-info/getMerchantByUser', { userId: this.currentUser.userId }).then((r) => {
        this.merchantInfo = r.data.data
      })
    },
    withdrawAuditOpen (row) {
      this.withdrawAudit.data = row
      this.withdrawAudit.visiable = true
    },
    withdrawViewOpen (row) {
      this.withdrawView.data = row
      this.withdrawView.visiable = true
    },
    handlewithdrawViewClose () {
      this.withdrawView.visiable = false
    },
    handlewithdrawAuditClose () {
      this.withdrawAudit.visiable = false
    },
    handlewithdrawViewSuccess () {
      this.withdrawAudit.visiable = false
      this.$message.success('审核成功')
      this.search()
    },
    onSelectChange (selectedRowKeys) {
      this.selectedRowKeys = selectedRowKeys
    },
    toggleAdvanced () {
      this.advanced = !this.advanced
    },
    add () {
      this.withdrawAdd.visiable = true
    },
    handlewithdrawAddClose () {
      this.withdrawAdd.visiable = false
    },
    handlewithdrawAddSuccess () {
      this.withdrawAdd.visiable = false
      this.$message.success('新增提现记录成功')
      this.search()
    },
    edit (record) {
      this.$refs.withdrawEdit.setFormValues(record)
      this.withdrawEdit.visiable = true
    },
    handlewithdrawEditClose () {
      this.withdrawEdit.visiable = false
    },
    handlewithdrawEditSuccess () {
      this.withdrawEdit.visiable = false
      this.$message.success('修改提现记录成功')
      this.search()
    },
    handleDeptChange (value) {
      this.queryParams.deptId = value || ''
    },
    batchDelete () {
      if (!this.selectedRowKeys.length) {
        this.$message.warning('请选择需要删除的记录')
        return
      }
      let that = this
      this.$confirm({
        title: '确定删除所选中的记录?',
        content: '当您点击确定按钮后，这些记录将会被彻底删除',
        centered: true,
        onOk () {
          let ids = that.selectedRowKeys.join(',')
          that.$delete('/cos/withdrawal-record/' + ids).then(() => {
            that.$message.success('删除成功')
            that.selectedRowKeys = []
            that.search()
          })
        },
        onCancel () {
          that.selectedRowKeys = []
        }
      })
    },
    search () {
      let {sortedInfo, filteredInfo} = this
      let sortField, sortOrder
      // 获取当前列的排序和列的过滤规则
      if (sortedInfo) {
        sortField = sortedInfo.field
        sortOrder = sortedInfo.order
      }
      this.fetch({
        sortField: sortField,
        sortOrder: sortOrder,
        ...this.queryParams,
        ...filteredInfo
      })
    },
    reset () {
      // 取消选中
      this.selectedRowKeys = []
      // 重置分页
      this.$refs.TableInfo.pagination.current = this.pagination.defaultCurrent
      if (this.paginationInfo) {
        this.paginationInfo.current = this.pagination.defaultCurrent
        this.paginationInfo.pageSize = this.pagination.defaultPageSize
      }
      // 重置列过滤器规则
      this.filteredInfo = null
      // 重置列排序规则
      this.sortedInfo = null
      // 重置查询参数
      this.queryParams = {}
      this.fetch()
    },
    handleTableChange (pagination, filters, sorter) {
      // 将这三个参数赋值给Vue data，用于后续使用
      this.paginationInfo = pagination
      this.filteredInfo = filters
      this.sortedInfo = sorter

      this.fetch({
        sortField: sorter.field,
        sortOrder: sorter.order,
        ...this.queryParams,
        ...filters
      })
    },
    fetch (params = {}) {
      // 显示loading
      this.loading = true
      if (this.paginationInfo) {
        // 如果分页信息不为空，则设置表格当前第几页，每页条数，并设置查询分页参数
        this.$refs.TableInfo.pagination.current = this.paginationInfo.current
        this.$refs.TableInfo.pagination.pageSize = this.paginationInfo.pageSize
        params.size = this.paginationInfo.pageSize
        params.current = this.paginationInfo.current
      } else {
        // 如果分页信息为空，则设置为默认值
        params.size = this.pagination.defaultPageSize
        params.current = this.pagination.defaultCurrent
      }
      if (params.status === undefined) {
        delete params.status
      }
      params.merchantId = this.currentUser.userId
      this.$get('/cos/withdrawal-record/page', {
        ...params
      }).then((r) => {
        let data = r.data.data
        const pagination = {...this.pagination}
        pagination.total = data.total
        this.dataSource = data.records
        this.pagination = pagination
        // 数据加载完毕，关闭loading
        this.loading = false
      })
    }
  },
  watch: {}
}
</script>
<style lang="less" scoped>
@import "../../../../static/less/Common";
</style>
