//控制层
app.controller('deviceController',function ($scope,$controller,deviceService) {
    $controller('baseController',{$scope:$scope});//继承通用的控制层

    //分页
    $scope.findPage=function(page,rows){
        deviceService.findPage(page,rows).success(
            function(response){

                $scope.list=response.rows;
                $scope.paginationConf.totalItems=response.total;//更新总记录数
                console.log( $scope.list);
            }
        );
    }
    $scope.searchEntity={};//定义搜索对象
    //搜索并分页
    $scope.search=function(page,rows){
        deviceService.findPage(page,rows,$scope.searchEntity).success(
            function(response){
                $scope.list=response.items;
                $scope.paginationConf.totalItems=response.total;//更新总记录数
                console.log( "$scope.list");
                console.log( $scope.list.devicename);
            }
        );
    }

    //查询实体
    $scope.findOne=function(id){
        deviceService.findOne(id).success(
            function(response){
                $scope.deivceEntity= response;

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
                if(response.success){
                    $('#editModal').modal('hide');
                    //重新查询
                    $scope.reloadList();//重新加载
                }else{
                    alert(response.message);
                }
            }
        );
    }

    //删除
    $scope.delete=function(id){
        if (confirm("是否确认删除")){
            //获取选中的复选框
            deviceService.dele( id ).success(
                function(response){
                    if(response.success){
                        $scope.reloadList();//刷新列表
                    }else{
                        alert(response.message);
                    }
                }
            );
        }
    }


    //初始化方法
    $scope.findSelect=function(){
        findSataion();
        findCommunicate();
        findDevcieType();
    }


    /*站点下拉框设置*/
    $scope.stationList=[];
    //查询所有站点
    findSataion=function () {
        deviceService.findSataion().success(
            function(response){
                $scope.stationList= response;
            }
        );
    }



    /*类型下拉框设置*/
    $scope.deviceTypes=[]
    //查询所有设备类型
    findDevcieType=function () {
        deviceService.findDevcieType().success(
            function(response){
                $scope.deviceTypes=response;
            }
        );
    }

    /*通讯下拉框设置*/
    $scope.communicates=[];
    //查询所有通道类型
    findCommunicate=function () {
        deviceService.findCommunicate().success(
            function(response){
                $scope.communicates= response;
            }
        );
    }

    $scope.enableList=[{id:1,text:'启用'},{id:0,text:'禁用'}];

    $scope.checksList=[{id:0,text:'不需要对设备进行校验'},{id:1,text:'需要对设备进行校验'}];



    /**
     * 验证的方法
     */
    $scope.validStation=function () {
        //验证非空字段
        if($scope.deivceEntity.stationid == null|| isNaN($scope.deivceEntity.stationid)||$scope.deivceEntity.stationid.length<=0){
            alert("请选择站点");
            return false;
        }
        if($scope.deivceEntity.typeid == null|| isNaN($scope.deivceEntity.typeid)||$scope.deivceEntity.typeid.length<=0){
            alert("请选择类型");
            return false;
        }
        if($scope.deivceEntity.deviceid == null|| isNaN($scope.deivceEntity.deviceid)||$scope.deivceEntity.deviceid.length<=0){
            alert("设备id不能为空,且必须为数字");
            return false;
        }
        if($scope.deivceEntity.communicateid == null||$scope.deivceEntity.communicateid.length<=0){
            alert("请选择通讯");
            return false;
        }
        if($scope.deivceEntity.subaddr == null||$scope.deivceEntity.subaddr.length<=0){
            alert("设备地址不能为空");
            return false;
        }
        if($scope.deivceEntity.checks == null||$scope.deivceEntity.checks.length<=0 ){
            alert("请选择设备数据校验标识");
            return false;
        }
        if($scope.deivceEntity.enable == null ||$scope.deivceEntity.enable.length<=0){
            alert("是否启用？");
            return false;
        }
        save();
    }

});