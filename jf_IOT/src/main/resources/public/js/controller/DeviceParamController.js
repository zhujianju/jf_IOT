//控制层
app.controller('deviceParamController',function ($scope,$controller,deviceParamService) {
    $controller('baseController',{$scope:$scope});//继承通用的控制层
    $controller('errorController',{$scope:$scope});//继承通用的异常控制层
    //分页
    /*  $scope.findPage=function(page,rows){
          deviceParamService.findPage(page,rows).success(
              function(response){
                  $scope.list=response.rows;
                  $scope.paginationConf.totalItems=response.total;//更新总记录数
              }
          );
      }*/

    $scope.searchEntity={};//定义搜索对象
    //搜索并分页
    $scope.search=function(page,rows){
        deviceParamService.findPage(page,rows,$scope.searchEntity).success(
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
        deviceParamService.findOne(id).success(
            function(response){
                $scope.entity=response;
                $scope.entity.status=1;
            }
        );
    }



    //保存
    save=function(){
        var serviceObject;//服务层对象
        if($scope.entity.status == 1){//如果有状态
            serviceObject=deviceParamService.update( $scope.entity ); //修改
        }else{
            serviceObject=deviceParamService.add( $scope.entity  );//增加
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
            deviceParamService.dele( id ).success(
                function(response){
                    $scope.reloadList();//刷新列表
                }
            ).error(function (error) {
                $scope.getError(error);
            });
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