<template>
  <div class="div-root" >
    <el-row>
      <el-col :span="8" :offset="8">
        <el-card class="el-leave">
          <el-input
            placeholder="请输入请假人员名称"
            v-model="name">
            <template slot="prepend">姓名</template>
          </el-input>
          <el-input type="number"
                    placeholder="请输入请假天数"
                    v-model="days">
            <template slot="prepend">天数</template>
          </el-input>
          <el-input type="textarea" :autosize="{minRows: 2, maxRows: 5}" aria-placeholder="请输入请假原因" v-model="reason">
            <template slot="prepend">原因</template>
          </el-input>
          <el-button type="warning" @click="postLeave" plain>提交</el-button>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
/* eslint-disable */
import axios from 'axios'

export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      name: '',
      days: '',
      reason: ''
    }
  },
  components: {

  },
  methods:{
    postLeave: function() {
      axios.post('http://localhost:8091/bpm/post/leave',{
        name: this.name,
        days: this.days,
        reason: this.reason
      }).then(response => {
        console.log(response)
      }).catch(err => {
        console.error(err)
      })
    }
  },
  created () {
    axios.get('http://localhost:8091/bpm/user')
      .then(response => {
        this.user = response.data.data
        console.log(this.user)
      })
      .catch(error => {
        console.error(error)
      })
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
  .el-card__body > div{
    margin: 10px 2px;
  }
</style>
