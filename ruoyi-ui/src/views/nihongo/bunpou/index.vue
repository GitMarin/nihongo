<template>
  <div class="app-container">
    <!-- 按钮 开始 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAddBunpou"
        >新增</el-button>
      </el-col>
      <!-- <right-toolbar :showSearch.sync="showSearch" @queryTable="getList" :columns="columns"></right-toolbar> -->
    </el-row>
    <!-- 按钮 结束 -->
    <!-- 文法表格 开始 -->
    <el-table v-loading="loading" :data="bunpouList" :row-key="bunpouRowKey" :expand-row-keys="bunpouExpendRowKeys" @row-click="clickBunpouTable" @expand-change="bunpouExpandChange" ref="bunpouTable" stripe border style="width: 100%">
      <!-- 文法用法表格 开始 -->
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-table v-loading="loading" :data="props.row.bunpouUses" @row-click="clickBunpouUseTable" :row-key="useRowKey" :expand-row-keys="useExpendRowKeys" @expand-change="useExpandChange" ref="bunpouUseTable"  stripe border style="width: 100%" >
            <!-- 文法用法例句表格 开始 -->
            <el-table-column type="expand">
              <template slot-scope="uses">
                <el-table v-loading="loading" :data="uses.row.bunpouUseExamples" stripe border style="width: 100%">
                  <el-table-column label="例句" align="center" key="example" prop="example"  />
                  <el-table-column label="翻译" align="center" key="translation" prop="translation"  />
                  <el-table-column
                    label="操作"
                    align="center"
                    width="160"
                    class-name="small-padding fixed-width"
                  >
                    <template slot-scope="scope">
                      <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdateExample(scope.row)"
                      >修改</el-button>
                    </template>
                  </el-table-column>
                </el-table>
              </template>
            </el-table-column>
            <!-- 文法用法例句表格 结束 -->
            <el-table-column label="使用方式" align="center" key="use" prop="use"  />
            <el-table-column label="解说" align="center" key="explanation" prop="explanation"  />
            <el-table-column label="类型" align="center" key="type" prop="type"  >
              <template slot-scope="scope">
                <el-tag
                  v-for="item in scope.row.typeNames"
                  :key="item"
                  :disable-transitions="true">
                  {{ item }}
                </el-tag>
              </template> 
            </el-table-column>
            <el-table-column label="等级" align="center" key="level" prop="level"  />
            <el-table-column label="注意" align="center" key="attention" prop="attention"  />
            <el-table-column
              label="操作"
              align="center"
              width="160"
              class-name="small-padding fixed-width"
            >
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="text"
                icon="el-icon-plus"
                @click="handleAddExample(scope.row)"
              >新增例句</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdateUse(scope.row)"
              >修改</el-button>
            </template>
            </el-table-column>
          </el-table>
            
        </template>
      </el-table-column>
      <!-- 文法用法表格 结束 -->
      <el-table-column label="语法内容" align="center" key="content" prop="content"  />
      <el-table-column
        label="操作"
        align="center"
        width="160"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.userId !== 1"
            size="mini"
            type="text"
            icon="el-icon-plus"
            @click="handleAddUse(scope.row)"
          >新增用法</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdateBunpou(scope.row)"
          >修改</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 文法表格 结束 -->

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
    <!-- 用例弹框 开始 -->
    <el-dialog :title="exampleTitle" :visible.sync="exampleVisible" width="600px" append-to-body>
      <el-form :model="exampleForm" label-width="80px" ref="exampleForm">
        <example-form :exampleProp="exampleForm"></example-form>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm('example')">确 定</el-button>
        <el-button @click="cancel('exampleVisible','exampleForm')">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 用例弹框 结束 -->

    <!-- 用法弹框 开始 -->
    <el-dialog :title="useTitle" :visible.sync="useVisible" width="600px" append-to-body>
      <el-form :model="useForm" label-width="80px" ref="useForm">
        <el-form-item label="使用方式">
          <el-input v-model="useForm.use"></el-input>
        </el-form-item>
        <el-form-item label="解说">
          <el-input v-model="useForm.explanation"></el-input>
        </el-form-item>
        <el-form-item label="类型">
          <el-select multiple filterable clearable v-model="useForm.types">
            <el-option v-for="item in typeOptions" :key="item.id" :label="item.label" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="等级">
          <el-select v-model="useForm.level">
            <el-option v-for="item in levelGroup" :key="item.value" :label="item.value" :value="item.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="注意">
          <el-input v-model="useForm.attention"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm('use')">确 定</el-button>
        <el-button @click="cancel('exampleVisible','exampleForm')">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 用法弹框 结束 -->

    <!-- 文法弹框 开始 -->
    <el-dialog :title="bunpouTitle" :visible.sync="bunpouVisible" width="600px" append-to-body>
      <el-form :model="bunpouForm" label-width="80px" ref="bunpouForm">
        <el-form-item label="语法内容">
          <el-input v-model="bunpouForm.content"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm('bunpou')">确 定</el-button>
        <el-button @click="cancel('exampleVisible','exampleForm')">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 文法弹框 结束 -->
  </div>
</template>

<script>
import { listBunpou ,updateExample ,addExample ,updateUse ,addUse ,updateBunpou ,addBunpou ,listType} from "@/api/nihongo/bunpou";
import { getToken } from "@/utils/auth";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";
import exampleForm from '../template/exampleForm.vue';

export default {
  components: { exampleForm },
  name: "Bunpou",
  data() {
    return {
      // 用例弹框展示控制
      exampleVisible: false,
      // 用例表单对象
      exampleForm: {},
      // 用法表单对象
      useForm :{
        types:[]
      },
      // 用法弹框展示控制
      useVisible :false,
      // 文法弹框展示控制
      bunpouVisible: false,
      // 文法表单对象
      bunpouForm: {},
      //用例弹框标题
      exampleTitle:'',
      //文法弹框标题
      bunpouTitle:'',
      //用法弹框标题
      useTitle:'',
      //文法表格展开行数组
      bunpouExpendRowKeys: [],
      //用法表格展开行数组
      useExpendRowKeys: [],
      // 文法表格数据
      bunpouList: [],
      // 遮罩层
      loading: true,

      levelGroup:[
        {
          value:'N1'
        },{
          value:'N2'
        },{
          value:'N3'
        },{
          value:'N4'
        },{
          value:'N5'
        }
        
      ],
      // 文法类型字典
      typeOptions: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userName: undefined,
        phonenumber: undefined,
        status: undefined,
        deptId: undefined
      },
      
      // 总条数
      total: 0,
      
      // 是否显示弹出层
      open: false,
     
      
      
    
      // 表单校验
      rules: {
        userName: [
          { required: true, message: "用户名称不能为空", trigger: "blur" }
        ],
        nickName: [
          { required: true, message: "用户昵称不能为空", trigger: "blur" }
        ],
        password: [
          { required: true, message: "用户密码不能为空", trigger: "blur" }
        ],
        email: [
          {
            type: "email",
            message: "'请输入正确的邮箱地址",
            trigger: ["blur", "change"]
          }
        ],
        phonenumber: [
          {
            pattern: /^1[3|4|5|6|7|8|9][0-9]\d{8}$/,
            message: "请输入正确的手机号码",
            trigger: "blur"
          }
        ]
      }
    };
  },
  // watch: {
  //   // 根据名称筛选部门树
  //   deptName(val) {
  //     this.$refs.tree.filter(val);
  //   }
  // },
  created() {
    this.getList();
    listType().then(res => {
      this.typeOptions = res.data;
    });
    
  },
  methods: {
    /** 控制点击文法行 */
    clickBunpouTable(row, column, event){
      this.$refs.bunpouTable.toggleRowExpansion(row)
    },
    /** 控制点击文法用法行 */
    clickBunpouUseTable(row, column, event){
      this.$refs.bunpouUseTable.toggleRowExpansion(row)
    },
    //提供展开行标记id
    bunpouRowKey(row) {
      return row.bunpouId;
    },
    useRowKey(row){
      return row.bunpouUseId;
    },
    bunpouExpandChange(row, expandedRows ){
      this.bunpouExpendRowKeys = [];
      for( let item of expandedRows){
        this.bunpouExpendRowKeys.push(item.bunpouId);
      }
    },
    //设置保存用法展开
    useExpandChange(row, expandedRows ){
      let index = this.useExpendRowKeys.indexOf(row.bunpouUseId);
      if (index !== -1){
        this.useExpendRowKeys.splice(index,1);
      } else {
        this.useExpendRowKeys.push(row.bunpouUseId)
      }
    },
    
    //处理用例修改
    handleUpdateExample(row) {
      // this.resetForm("exampleForm");
      this.exampleForm = {};
      this.exampleVisible = true;
      this.exampleTitle = "修改用例";
      this.exampleForm = JSON.parse(JSON.stringify(row));
    },
    //处理用例新增
    handleAddExample(row) {
      this.exampleForm = {};
      // this.resetForm("exampleForm");
      this.exampleVisible = true;
      this.exampleTitle = "新增用例";
      this.exampleForm.bunpouUseId = row.bunpouUseId;
    },
    //处理用法修改
    handleUpdateUse(row) {
      // this.resetForm("exampleForm");
      this.useForm = {
        types:[]
      };
      this.useVisible = true;
      this.useTitle = "修改用法";
      this.useForm = JSON.parse(JSON.stringify(row));
    },
    //处理用法新增
    handleAddUse(row) {
      this.useForm = {
        types:[]
      };
      // this.resetForm("exampleForm");
      this.useVisible = true;
      this.useTitle = "新增用法";
      this.useForm.bunpouId = row.bunpouId;
    },
    //处理用法修改
    handleUpdateBunpou(row) {
      // this.resetForm("exampleForm");
      this.bunpouForm = {};
      this.bunpouVisible = true;
      this.bunpouTitle = "修改文法";
      this.bunpouForm = JSON.parse(JSON.stringify(row));
    },
    //处理用法新增
    handleAddBunpou() {
      this.bunpouForm = {};
      // this.resetForm("exampleForm");
      this.bunpouVisible = true;
      this.bunpouTitle = "新增文法";
    },
    exampleUpdate(form) {
      return updateExample(form);
    },
    exampleAdd(form) {
      return addExample(form);
    },
    useUpdate(form) {
      return updateUse(form);
    },
    useAdd(form) {
      return addUse(form);
    },
    bunpouUpdate(form) {
      return updateBunpou(form);
    },
    bunpouAdd(form) {
      return addBunpou(form);
    },

    submitForm(name) {
      let id;
      if (name === 'example') {
        id = this[name+"Form"].bunpouUseExampleId;
      } else if (name === 'use'){
        id = this[name+"Form"].bunpouUseId;
      } else if (name === 'bunpou') {
        id = this[name+"Form"].bunpouId;
      }
      if (id != undefined) {
        this.$options.methods[name+"Update"](this[name+"Form"]).then(response => {
          this.msgSuccess("修改成功");
          this[name+"Visible"] = false;
          this.getList();
        });
      } else {
        this.$options.methods[name+"Add"](this[name+"Form"]).then(response => {
          this.msgSuccess("新增成功");
          this[name+"Visible"] = false;
          this.getList();
        });
      }
      
    },
    cancel(visible,formName) {
      this[visible] = false,
      this[formName] = {}
    },
    /** 查询文法列表 */
    getList() {
      this.loading = true;
      listBunpou(this.queryParams).then(response => {
          this.bunpouList = response.rows;
          this.total = response.total;
          this.loading = false;
        }
      );
    },
    
    
    // 表单重置
    reset() {
      // this.form = {
      
      // };
      this.resetForm("form");
    },
    
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
  }
};
</script>