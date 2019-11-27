//控制层
app.controller('userController',function ($scope,$controller) {
    $controller('baseController',{$scope:$scope});//继承通用的控制层
    $controller('errorController',{$scope:$scope});//继承通用的异常控制层

});