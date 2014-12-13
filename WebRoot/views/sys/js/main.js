$.ajaxSetup ({cache: false });

function getLoginUser()
{
	return loginUser;
}
var loginUser;
//初始化加载
$(function(){

	topLoading();
	$.post(getContextPath()+"/api/sys/user/getUser",{},function(resultBean, status, xhRequest)
	{	  
		topLoaded();    
		if(!handleAjaxRequest(resultBean, status,xhRequest))return;
		if(resultBean.data.user!=undefined)
		{
			loginUser=resultBean.data;
			var menuList = resultBean.data.user.menuList;
			$("#userNameLabel").html(resultBean.data.name);
			$("#user_type").html("【"+resultBean.data.user.typeName+"】");
			filter(menuList);
			buildTreeMenu(menuList);
		}
	},'json');
	
	/**
	 * 如果系统菜单数等于2,就把系统管理改成用户管理
	 */
	function filter(menuList){
		if(menuList){
			var count = 0;
			var index = -1;
			for (var i = 0; i < menuList.length; i++) {
				if (menuList[i].parentId === 'sys') {
					count++;
					continue;
				}
				if (menuList[i].id === 'sys') {
					index = i;
				}
			}
			if(count <= 2 && index >= 0){
				menuList[index].name="用户管理";
				loginUser.userAccount = true;
			}
		}
	
	}
	
	 /*$.ajax({
         async: false,
         url: getContextPath()+"/api/sys/user/getUser",  // 跨域URL
         type: 'POST',
         dataType: 'jsonp',
         jsonp: 'callback',
         data: {},
         timeout: 5000,
         beforeSend: function () {
             //jsonp 方式此方法不被触发。原因可能是dataType如果指定为jsonp的话，就已经不是ajax事件了
         },
         success: function (resultBean, status, xhRequest) {
        	 topLoaded();    
     		if(!handleAjaxRequest(resultBean, status,xhRequest))return;
     		$("#userNameLabel").html(resultBean.data.name);
     		buildTreeMenu(resultBean.data.user.menuList);
         },
         complete: function (XMLHttpRequest, textStatus) {
         },
         error: function (xhr) {
             alert("请求出错(请检查相关度网络状况.)" + xhr);
         }
     }); */
});

function goLogout() {
    topLoading();
    $.getJSON(getContextPath() + "/api/authx/logout", {}, function (resultBean, status, xhRequest) {
        topLoaded();
        if (!handleAjaxRequest(resultBean, status, xhRequest)) return;
        //top.document.location.href = getContextPath() + "/front/sys/index.html";
        top.document.location.href = getContextPath() + "/views/index.html";
    });
}

//构建菜单树
function buildTreeMenu(menuList) {
    var t = $("#nav-tree");
    t = $.fn.zTree.init(t, setting, menuList);
    demoIframe = $("#iframeid");
}

var zTree;
var demoIframe;
var searchParam;

var setting = {
    view: {
        showLine: false,
        showIcon: false,
        dblClickExpand: false,
        showLine: false,
        selectedMulti: false
    },
    data: {
        simpleData: {
            enable: true,
            idKey: "id",
            pIdKey: "parentId",
            rootPId: ""
        }
    },
    callback: {
        beforeClick: function (treeId, treeNode) {
        	searchParam = "";
            zTree = $.fn.zTree.getZTreeObj(treeId);
            if (treeNode.isParent) {
                zTree.expandNode(treeNode);
                return false;
            } else {
            	var randomNum = Math.floor(Math.random() * (182014 + 1));
            	if(typeof(treeNode.uri) == 'undefined' || !treeNode.uri){
            		demoIframe.attr("src", getContextPath() + "/views/sys/home.html?vNum=" + randomNum);
            		return;
            	}
            	if(treeNode.uri.lastIndexOf(".html")>0)
            	{
            		demoIframe.attr("src", getContextPath() + treeNode.uri+"?vNum=" + randomNum);
            	}else
        		{
            		demoIframe.attr("src", getContextPath() + treeNode.uri + ".html?vNum=" + randomNum);
        		}
                return true;
            }
        }
    }
};
// 修改密码
function modPwd(){
	var temp_url = getContextPath() + "/views/sys/modifypwd.html";
    _Dialog('新增用户',temp_url);
    
}