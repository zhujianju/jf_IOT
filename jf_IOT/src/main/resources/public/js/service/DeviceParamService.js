//设备分类服务层
app.service('deviceParamService',function ($http) {

    //读取列表数据绑定到表单中
    this.findPage=function (page,rows,searchEntity) {
        return $http.post("../deviceParam/page?page="+page+"&rows="+rows,searchEntity);
    }

    //根据id查询单个
    this.findOne=function (id) {
        return $http.get("../deviceParam/"+id);
    }
    //增加
    this.add=function(entity){
        return  $http.post('../deviceParam',entity );
    }
    //修改
    this.update=function(entity){
        return  $http.put('../deviceParam',entity );
    }
    //删除
    this.dele=function(id){
        return $http.delete('../deviceParam/'+id);
    }
    //获取下拉框

});