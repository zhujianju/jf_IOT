//控制层
app.controller('userController',function ($scope,$controller) {
    $controller('baseController',{$scope:$scope});//继承通用的控制层


});