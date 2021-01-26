<template>
  <div>
    <el-form-item label="例句">
        <el-input v-model="exampleProp.example"></el-input>
    </el-form-item>
    <el-form-item label="翻译">
        <el-input v-model="exampleProp.translation"></el-input>
    </el-form-item>
  </div>
</template>
<script>
export default {
    name: 'exampleForm',
    props:{
        exampleProp: Object,
        
    },
    data () {
        return {
            that:this
        };
    },
    methods:{
        // getThat(){
        //     this.$emit('that',this)
        // }
    }

}
</script>
<style>
</style>