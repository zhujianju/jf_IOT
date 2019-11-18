//控制层
app.controller('deviceTypeController',function ($scope,$controller,deviceTypeService) {
    $controller('baseController', {$scope: $scope});//继承通用的控制层
    $controller('errorController', {$scope: $scope});//继承通用的异常控制层
    //分页
    $scope.findPage=function(page,rows){
        deviceTypeService.findPage(page,rows).success(
            function(response){
                $scope.list=response.rows;
                $scope.paginationConf.totalItems=response.total;//更新总记录数
            }
        );
    }
})