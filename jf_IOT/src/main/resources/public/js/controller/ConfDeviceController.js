//控制层
app.controller('deviceController',function ($scope,$controller,deviceService) {
    $controller('baseController',{$scope:$scope});//继承通用的控制层
    $controller('errorController',{$scope:$scope});//继承通用的异常控制层
    //分页
    $scope.findPage=function(page,rows){
        deviceService.findPage(page,rows).success(
            function(response){
                $scope.list=response.rows;
                $scope.paginationConf.totalItems=response.total;//更新总记录数
            }
        );
    }
    $scope.searchEntity={};//定义搜索对象
    //搜索并分页
    $scope.search=function(page,rows){
        deviceService.findPage(page,rows,$scope.searchEntity).success(
            function(response){
                $scope.errorEntityt.code=000;//000则为正常。不提示任何错误信息
                $scope.list=response.items;
                $scope.paginationConf.totalItems=response.total;//更新总记录数
            }
        ).error(function (error) {
            $scope.getError(error);
        });
    }

    //查询实体
    $scope.findOne=function(id){
        deviceService.findOne(id).success(
            function(response){
                $scope.deivceEntity=response;
                $scope.deivceEntity.status=1;
            }
        );
    }

    //保存
    save=function(){
        var serviceObject;//服务层对象
        if($scope.deivceEntity.status == 1){//如果有状态
            serviceObject=deviceService.update( $scope.deivceEntity ); //修改
        }else{
            serviceObject=deviceService.add( $scope.deivceEntity  );//增加
        }
        serviceObject.success(
            function(response){
                    $('#editModal').modal('hide');
                    $scope.reloadList();//重新加载
            }
        ).error(
            function (error) {
                $scope.getError(error);
            }
        );
    }

    //删除
    $scope.delete=function(id){
        if (confirm("是否确认删除")){
            //获取选中的复选框
            deviceService.dele( id ).success(
                function(response){
                        $scope.reloadList();//刷新列表
                }
            ).error(function (error) {
                $scope.getError(error);
            });
        }
    }
    //查看设备所绑定的用户
    $scope.findBindUser=function(id){
        deviceService.findBindUser(id).success(function (response) {
            $scope.bindUserEntity=response;
        }).error(function (error) {//报错时
            $scope.getError(error);
        });
    }

    $scope.saleactivate=[{id:1,text:'已激活'},{id:0,text:'未激活'}];
    /*定义设备类型的数据字典转换的方法*/
    $scope.isSaleactivate=function (id) {
        for (var i=0;i<$scope.saleactivate.length;i++){
            if($scope.saleactivate[i].id==id){
                return $scope.saleactivate[i].text;
            }
        }
    }



    /**
     * 验证的方法
     */
    $scope.validStation=function () {

      /*  //验证非空字段
        if($scope.deivceEntity.stationid == null|| isNaN($scope.deivceEntity.stationid)||$scope.deivceEntity.stationid.length<=0){
            alert("请选择站点");
            return false;
        }*/
        save();
    }

});