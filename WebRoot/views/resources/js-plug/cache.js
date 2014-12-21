/**
 * 定义需要在用户登录的时候获取到本地的数据字典类别
 */
var clsCodes = {"clsCodes" :
		["BOOL",
		 "STATUS",
		 "USER_TYPE",
		 "USER_STATUS",
		 "SEX",
		 "DEPARTMENT",
		 "ANES_METHOD"
     ]
};

/**
 * 获取数据字典到本地
 */
var dicts;
function getDicts() {
	$.post(getContextPath() + "/api/authx/getDictList",
			clsCodes,
			function(resultBean, status, xhRequest) {
				if (handleAjaxRequest(resultBean, status, xhRequest)){
					if (resultBean.data != undefined) {
						dicts = resultBean.data;
					}
				}
		 	}, 
			'json');
}