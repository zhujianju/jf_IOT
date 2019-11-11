app.controller('errorController',function ($scope,$controller) {
    /*定义通用的，异常处理方法*/
    //000则为正常。不提示任何错误信息。001，为设备页面的异常信息
    $scope.getError=function (error) {
        if(error.status==404 && error.message=='没有查询到设备'){
            $scope.errorEntityt=error;
            $scope.errorEntityt.text="当前查询条件下，没有找到任何设备."
            $scope.errorEntityt.code=001; //根据不同的code来处理不同的错误信息
        }
        if(error.status==404 && error.message=='用户未登录'){
            $scope.errorEntityt=error;
            $scope.errorEntityt.text="您尚未登陆，或登陆超时,<a href='#' onclick='window.top.location=\"../Login/index.html\"'>请登录</a>."
            $scope.errorEntityt.code=001; //根据不同的code来处理不同的错误信息

        }
        if(error.status==405 && error.message=='需要管理员才能进行此操作'){
            alert(error.message);
        }
        if(error.status==500){
           alert("系统内部错误");
        }
    }


});