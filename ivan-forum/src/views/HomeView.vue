<template>
  <div class="home" style="height: 100%">
    <el-container style="height: 100%; border: 1px solid #eee">
      <el-aside
        :width="sideWidth + 'px'"
        style="background-color: skyblue; height: 100%"
        direction="vertical"
      >
        <div class="log" v-if="!isCollapse">
          <h1>IVAN管理系统</h1>
        </div>
        <el-menu
          :default-openeds="['1', '3']"
          style="overflow-x: hidden; left: 0px; width: 100%; border: none"
          background-color="skyblue"
          active-text-color="#ffd04b"
          text-color="#fff"
          :collapse="isCollapse"
        >
          <el-submenu index="1">
            <template slot="title"
              ><i class="el-icon-message"></i>
              <span slot="title">导航一</span>
            </template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="1-1">选项1</el-menu-item>
              <el-menu-item index="1-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="1-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="1-4">
              <template slot="title">选项4</template>
              <el-menu-item index="1-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span slot="title">导航二</span>
            </template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="2-1">选项1</el-menu-item>
              <el-menu-item index="2-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="2-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="2-4">
              <template slot="title">选项4</template>
              <el-menu-item index="2-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-setting"></i>
              <span slot="title">导航三</span>
            </template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="3-1">选项1</el-menu-item>
              <el-menu-item index="3-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="3-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="3-4">
              <template slot="title">选项4</template>
              <el-menu-item index="3-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-container>
        <el-header
          style="
            text-align: right;
            font-size: 12px;
            display: flex;
            padding: 0 0;
            line-height: 60px;
            height: 60px;
          "
        >
          <div style="margin-bottom: 20px; left: 0px">
            <el-button @click="flodChange()" v-if="!isCollapse"
              ><i class="el-icon-s-fold"></i
            ></el-button>
            <el-button @click="flodChange()" v-if="isCollapse"
              ><i class="el-icon-s-unfold"></i
            ></el-button>
          </div>
          <el-dropdown trigger="click" style="left: 90%">
            <div
              class="el-dropdown-link"
              style="display: flex; align-items: center"
            >
              用户名&nbsp;&nbsp;<el-avatar
                shape="square"
                size="medium"
              ></el-avatar>
            </div>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item icon="el-icon-user-solid"
                >个人中心</el-dropdown-item
              >
              <el-dropdown-item icon="el-icon-pear">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </el-header>

        <el-main>
          <el-table :data="tableData" style="width: 100%">
            <el-table-column label="#" type="index" width="50"> </el-table-column>
            <el-table-column prop="username" label="姓名" width="180">
            </el-table-column>
            <el-table-column prop="password" label="密码" width="180">
            </el-table-column>
            <el-table-column prop="createTime" label="创建时间">
            </el-table-column>
            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button
                  size="mini"
                  @click="handleEdit(scope.row)"
                  >编辑</el-button
                >
                <el-button
                  size="mini"
                  type="danger"
                  @click="handleDelete(scope.row.id)"
                  >删除</el-button
                >
              </template>
            </el-table-column>
          </el-table>
          <div class="block">
            <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="pageinfo.pageNum"
              :page-sizes="[5, 10, 15, 20]"
              :page-size="pageinfo.pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="total"
            >
            </el-pagination>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import { deleteById, getUser } from "@/request/api";
export default {
  name: "HomeView",
  data() {
    return {
      pageinfo: {
        pageNum: 1,
        pageSize: 5,
      },
      total: 0,
      msg: "我是一个信息",
      isCollapse: false,
      tableData: [],
      sideWidth: 200,
    };
  },
  methods: {
    getUserFn(){
      getUser(this.pageinfo).then((res) => {
        this.tableData = res.data.data.records;
        this.total = res.data.data.total;
      });
    },
    handleSizeChange(val) {
      this.pageinfo.pageSize = val;
      this.getUserFn()

    },
    handleCurrentChange(val) {
      this.pageinfo.pageNum = val;
      this.getUserFn()
    },
    flodChange() {
      this.isCollapse = !this.isCollapse;
      if (this.isCollapse) {
        //收缩
        this.sideWidth = 100;
      } else {
        //展示
        this.sideWidth = 200;
      }
    },
    // 编辑删除函数
    handleEdit(id) {
      console.log(row);
    },
    handleDelete(id) {
      deleteById(id).then(res=>{
            getUser(this.pageinfo).then((res) => {
          this.tableData = res.data.data.records;
          this.total = res.data.data.total;
        });
        
      })


    },
  },
  created() {
    getUser(this.pageinfo).then((res) => {
      this.tableData = res.data.data.records;
      this.total = res.data.data.total;
    });
  },
};
</script>
<style>
.el-header {
  background-color: skyblue;
  color: #fff;
  line-height: 60px;
}

.el-aside {
  color: #fff;
}
.log {
  margin: 20px 0;
}
</style>
