//设备分类服务层
app.service('deviceTypeService',function ($http) {

    //读取列表数据绑定到表单中
    this.findPage=function (page,rows,searchEntity) {
        return $http.post("../deviceType/page?page="+page+"&rows="+rows,searchEntity);
    }

    //根据id查询单个
    this.findOne=function (id) {
        return $http.get("../deviceType/findOne/"+id);
    }
    //增加
    this.add=function(entity){
        return  $http.post('../deviceType',entity );
    }
    //修改
    this.update=function(entity){
        return  $http.put('../deviceType',entity );
    }
    //删除
    this.dele=function(id){
        return $http.delete('../deviceType/'+id);
    }
    //查找当前设备所绑定的用户
    this.findBindUser=function (id) {
        return $http.get('../deviceType/findUser/'+id);
    }



});