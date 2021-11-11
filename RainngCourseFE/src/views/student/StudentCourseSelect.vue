<template>
  <div class="course-select-wrap">
    <div class="crumbs">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item>
          <i class="el-icon-fa fa-book"></i> 导师申请
        </el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div class="container">
      <div class="query-form">
        <el-row :gutter="20">
          <el-col :span="3">
            <el-input
              @keyup.enter.native="query"
              placeholder="教师名"
              v-model="queryForm.teacherName"
            />
          </el-col>
          <el-col :span="3">
            <el-button @click="query" icon="el-icon-search" type="primary"
              >搜索
            </el-button>
          </el-col>
        </el-row>
      </div>

      <el-row justify="center" type="flex">
        <el-pagination
          :current-page.sync="pageIndex"
          :page-size="pageSize"
          :total="pageSize * pageCount"
          @current-change="getPage"
          background
          layout="prev, pager, next"
        >
        </el-pagination>
      </el-row>

      <div class="table">
        <el-table :data="tableData" stripe >
          <el-table-column label="序号" prop="id" />
          <el-table-column label="导师姓名" prop="name" width="180px" />
          <el-table-column label="性别" prop="sex" />
          <el-table-column label="工号" prop="number" />
          <el-table-column
            align="center"
            label="职称"
            prop="title"
            width="130px"
          />
          <el-table-column label="导师类型" prop="teacherType" />
          <el-table-column label="校内学术保研数" prop="scienceInTrue" />
           <el-table-column label="校外学术保研数" prop="scienceOutTrue" />
          <el-table-column label="可招收学术研究生总数" prop="scienceSum" />
           <el-table-column label="剩余学术研究生名额数" prop="scienceSurplus" />
          <el-table-column label="校内专硕保研数" prop="noScienceInTrue" />
           <el-table-column label="校外专硕保研数" prop="noScienceOutTrue" />
          <el-table-column label="可招收专硕研究生总数" prop="noScienceSum" />
           <el-table-column label="剩余专硕研究生名额数" prop="noScienceSurplus" />
    

          <el-table-column align="center" label="操作" width="200px">
            <template slot-scope="scope">
              <el-button
                @click="choose(scope.row.teacherUuid)"
                size="mini"
                type="success"
                >申请
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>

       <el-dialog :visible.sync="editing" title="申请类型" width="30%">
        
    <el-radio-group v-model="entityForm.type">
      <el-radio label="1">学术硕士</el-radio>
      <el-radio label="0">专硕</el-radio>
    </el-radio-group>

        <span class="dialog-footer" slot="footer">
          <el-button @click="save" type="primary">确 定</el-button>
          <el-button @click="editing = false">取 消</el-button>
        </span>
      </el-dialog>
      
    </div>
  </div>
</template>

<script>
import * as api from "../../api/student/courseSelect";

export default {
  name: "StudentCourseSelect",
  data() {
    return {
      queryForm: {
        courseName: "",
        teacherName: ""
      },
      entityForm: {},
      tableData: [],
      pageSize: api.pageSize,
      pageCount: 1,
      pageIndex: 1,
      editing: false
    };
  },
  methods: {
    query() {
      api
        .getPageCount(this.queryForm.courseName, this.queryForm.teacherName)
        .then(res => {
          this.pageCount = res;
          this.pageIndex = 1;
          this.getPage(1);
        });
    },
    getPage(pageIndex) {
      api
        .getPage(
          pageIndex,
          this.queryForm.courseName,
          this.queryForm.teacherName
        )
        .then(res => {
          this.tableData = res;
        });
    },
    choose(teacherUuid) {
      this.entityForm.teacherUuid =  teacherUuid;
      this.editing = true;
    },
    save(){
      api.choose(
        this.entityForm.teacherUuid,
        this.entityForm.type
      )
      .then(() => {
        this.$message.success("恭喜您，选择成功!");
        this.editing = false;
        this.getPage(this.pageIndex);
      },() => {
        this.editing = false;
      })
    },
    select(id) {
      api.select(id).then(() => {
        this.$message.success("选修成功!");
        this.getPage(this.pageIndex);
      });
    }
  },
  created() {
    this.query();
  }
};
</script>

<style scoped></style>
