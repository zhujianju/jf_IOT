//设备服务层
app.service('deviceService',function ($http) {

    //读取列表数据绑定到表单中
    this.findPage=function (page,rows,searchEntity) {
        return $http.post("../device/page?page="+page+"&rows="+rows,searchEntity);
    }

    //根据id查询单个
    this.findOne=function (id) {
        return $http.get("../device/findOne/"+id);
    }
    //增加
    this.add=function(entity){
        return  $http.post('../device',entity );
    }
    //修改
    this.update=function(entity){
        return  $http.put('../device',entity );
    }
    //删除
    this.dele=function(id){
        return $http.delete('../device/'+id);
    }
    //查找当前设备所绑定的用户
    this.findBindUser=function (id) {
        return $http.get('../device/findUser/'+id);
    }



});