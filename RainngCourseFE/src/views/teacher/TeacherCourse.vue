<template>
  <div class="course-wrap">
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-fa fa-book"></i> 学生信息
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div class="container">
      <div class="table">
        <el-table :data="tableData" stripe>
          <el-table-column label="序号" prop="id" />
          <el-table-column label="学生姓名" prop="name" />
          <el-table-column label="学生编号" prop="number" />
          <el-table-column label="政治成绩" prop="political" />
          <el-table-column label="英语成绩" prop="english" />
          <el-table-column label="业务课一成绩" prop="businessOne" />
          <el-table-column label="业务课二成绩" prop="businessTwo" />
          <el-table-column label="初试总成绩" prop="firstTry" />
          <el-table-column label="毕业学校" prop="graduateSchool" />
          <el-table-column label="毕业专业" prop="graduateProject" />
          <el-table-column label="学术类型" prop="science" />
          <el-table-column align="center"  label="操作" width="200px">
            <template slot-scope="scope" >
              <el-button v-if="scope.row.status === 0" 
                @click="apple(scope.row.applyId,1)"
                size="mini"
                type="success"
                >同意
              </el-button>
              <el-button v-if="scope.row.status === 0"
                @click="apple(scope.row.applyId,2)"
                size="mini"
                type="danger"
                >拒绝
              </el-button>
              <el-button v-if="scope.row.status == 1"
                size="mini"
                type= "text"
                >同意
              </el-button>
              <el-button v-if="scope.row.status == 2"
                size="mini"
                type="text"
                >拒绝
              </el-button>
            </template>
        
          </el-table-column>
        </el-table>
      </div>
    </div>
  </div>
</template>

<script>
import * as api from "../../api/teacher/course";

export default {
  name: "TeacherCourse",
  data() {
    return {
      tableData: []
    };
  },
  methods: {
    getList() {
      api.list().then(res => {
        this.tableData = res;
      });
    },
    apple(id,status){
      api.apple(id,status)
      .then(() => {
        this.$message.success("您通过了该申请!");
        this.getList();
      })
    }
  },
  created() {
    this.getList();
  }
};
</script>

<style scoped></style>
