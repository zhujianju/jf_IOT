app.controller('baseController',function($scope,$http,$cookieStore){
	/* 分页的前端代码 */
	$scope.paginationConf = {
			 currentPage: 1,
			 totalItems: 10,
			 itemsPerPage: 10,
			 perPageOptions: [10, 20, 30, 40, 50],
			 onChange: function(){
				 $scope.reloadList();
				 $scope.selectIds=[];
			 	}
			 };
	/*定义对象，用于接收后台传递的错误信息*/
    $scope.errorEntityt={};
	/* 封装每次请求的,方法 */
	$scope.reloadList=function(){
        $scope.getCookie();
		$scope.search($scope.paginationConf.currentPage,$scope.paginationConf.itemsPerPage);
	}
	
	/* 定义集合,用户接受用户选择删除的id */
	$scope.selectIds=[];
	/* 获得用户需要删除的所有id */	//$event获得复选框源
	$scope.updateSelection=function($event,id){
		if($event.target.checked){	//获得复选框是否被选中
			$scope.selectIds.push(id);
		}else{//则是被取消
			var index=$scope.selectIds.indexOf(id);//获得被取消的复选框下标id
			$scope.selectIds.splice(index,1);//参数1被删除的下标,参数2删除几个元素
		}
	}
	
	//字符串格式转换
	$scope.jsonToString=function(jsonString,key){
		
		var json= JSON.parse(jsonString);
		var value="";
		
		for(var i=0;i<json.length;i++){
			if(i>0){
				value+=",";
			}			
			value +=json[i][key];			
		}
				
		return value;
	}
	
	//从集合中按照key,查询对象是否存在
	$scope.searchObjectBykey=function(list,key,keyValue){
		for (var i = 0; i < list.length; i++) {
			if(list[i][key]==keyValue){
				return list[i];
			}
		}
		return null;
	}

    /**
     * 获取Cookie中的权限码
     * @returns {*}
     */
    $scope.authoCode="";//权限码，用于页面的权限验证
    $scope.getCookie=function () {
       var autho= $cookieStore.get('userAutho')
        if(autho == null || autho.length <=0){
                alert("请先登陆");
          /*  window.open("../Login/index.html");
            window.top.close();*/
            window.top.location="../Login/index.html"
        }
        $scope.authoCode=autho;
        return autho;
    }

	//----------查询数据字典的通用方法
    //初始化方法（）
    $scope.initSDDC=function(){
        findDevcieType();
    }

    /*设备类型下拉框设置*/
    $scope.deviceTypes=[];
    //查询所有设备类型
    findDevcieType=function () {
        $http.get('../deviceType/findSelect').success(
            function(response){
                $scope.deviceTypes=response;
            }
        );
    }

    /*定义设备类型的数据字典转换的方法*/
    $scope.findDeviceTypeText=function (id) {
        for (var i=0;i<$scope.deviceTypes.length;i++){
            if($scope.deviceTypes[i].id==id){
                return $scope.deviceTypes[i].text;
            }
        }
    }

    /**
     * 传入集合和id，查找对应的文本
     * @param list
     * @param id
     */
    $scope.findText=function (list,id) {
        for (var i=0;i<list.length;i++){
            if(list[i].id==id){
                return list[i].text;
            }
        }
    }
    //定义参数类型下拉框
    $scope.typeList=[{id:1,text:'动值参数'},{id:2,text:'定值参数'}];
    //参数值类型
    $scope.valueTypeList=[{id:1,text:'Int'},{id:2,text:'String'}];
    //参数状态
    $scope.statusList=[{id:0,text:'禁用'},{id:1,text:'启用'}];
    //设备状态
    $scope.lifecycleWarnList=[{id:0,text:'已入库'},{id:1,text:'已出售'},{id:2,text:'已激活'},{id:3,text:'已停用'},{id:4,text:'已失效'}];
    //设备是否开启
    $scope.isenableList=[{id:0,text:'已关闭'},{id:1,text:'已开启'}];
});