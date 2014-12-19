function getContextPath() {
	return "/anes-work";
}

Array.prototype.contains = function(value){return this.indexOf(value)>=0;};

//未登录。直接给页面加个白色蒙板
checkLoginError_addbackdrop=function(){
	var Login_Backdrop = '<div class="login-backdrop"></div>';
	$(Login_Backdrop).appendTo('body');
};

function handleAjaxRequest(resultBean, status, XMLHttpRequest)
{
	if(!resultBean.success)
	{
		if(resultBean.messageCode=="01")
		{
			checkLoginError_addbackdrop();
			alert(resultBean.message);
			window.top.location.href=getContextPath()+"/views/";
			return false;
		}else if(resultBean.messageCode=="02")
		{
			alert(resultBean.message);
			return false;
		}else if(resultBean.messageCode=="03")
		{
			alert(resultBean.message);
			return false;
		}
		alert(resultBean.message);
	}
	var ajaxRequestChecking = XMLHttpRequest.getResponseHeader("AJAX_REQUEST_CHECKING");
	if(ajaxRequestChecking!=null)
	{
		if(ajaxRequestChecking=="01")
		{
			checkLoginError_addbackdrop();
			alert('您未登录或会话已过期');
			window.top.location.href=getContentPath()+"/views/";
			return false;
		}else if(ajaxRequestChecking=="02"){
			alert('您没有此模块的访问权限');
			return false;
		}else if(ajaxRequestChecking=="03"){
			alert('系统出错');
			return false;
		}
	}
	var ajaxRequestException = XMLHttpRequest.getResponseHeader("AJAX_REQUEST_EXCEPTION");
	if(ajaxRequestException!=null)
	{
		alert(ajaxRequestException);
		return false;
	}
	if (status == "error") 
	{
		alert('系统出错');
		return false;
	}
	return resultBean.success;
}

function topLoading()
{
	if($("#topLoadingDiv").html()==undefined)
	{
		$("body").append("<div id='topLoadingDiv' class='top_loading'><img border='0' src='"+getContextPath()+"/views/resources/images/loading.gif'/></div>");
	}
	$("#topLoadingDiv").attr("class","top_loading");
	$("#topLoadingDiv").css("left",(document.documentElement.clientWidth-32)/2+"px");
	$("#topLoadingDiv").css("top",(document.documentElement.clientHeight-32)/2+"px");
}

function topLoaded()
{
	if($("#topLoadingDiv").html()!=undefined)
	{
		$("#topLoadingDiv").attr("class","top_loaded");
	}
}

function initSelectOptions()
{
	$("select").each(function()
	{
		var isWithBlank=false;
		var reqUrl=null,select=null,opt=null;
		if($(this).attr("isWithBlank"))
		{
			isWithBlank=$(this).attr("isWithBlank").toLowerCase()=="true"?true:false;
		}

		if($(this).attr("data") && this.options.length==0)
		{
			select=this;

			blankLabel=$(this).attr("blankLabel");
			if(blankLabel==undefined)
			{
				blankLabel="";
			}
			
			reqUrl=$(this).attr("data");
			
			if(reqUrl.indexOf("getLocalDict")!=-1){//如果是调用"getLocalDict"，则从前端缓存中获取数据
				//var clsCode = reqUrl.substr(reqUrl.lastIndexOf("clsCode=")+8).trim();
				var dictMap = eval(reqUrl);
				if(dictMap != null){
					for(var i=select.options.length;i>=0;i--) select.options.remove(i);	
					
					if(isWithBlank)
					{
						opt=document.createElement('option');
						select.options.add(opt);
						opt.value="";
						opt.innerHTML=blankLabel; 
					}
					for(var key in dictMap)
					{
						opt=document.createElement('option');
						select.options.add(opt);
						opt.value=key;
						opt.innerHTML=dictMap[key]; 	
					}
					return;
				}else{
					alert("未能获取数据字典！请按F5刷新页面或重新登录。");
					return;
				}
			}
			
			if(reqUrl.indexOf(getContextPath()+"/")==-1)
			{
				reqUrl=getContextPath()+reqUrl;
			}
			var s= reqUrl.indexOf("?")==-1 ? "?_=" : "&_=";
			
			reqUrl=reqUrl+s+new Date().getTime();
			

			topLoading();
			$.ajax({type:"GET",dataType:"json",url:reqUrl,async:false,success:function(resultBean, status, xhRequest)
			{
				topLoaded();
				if(!handleAjaxRequest(resultBean, status,xhRequest)){return;}
				
				for(var i=select.options.length;i>=0;i--) select.options.remove(i);	
				
				if(isWithBlank)
				{
					opt=document.createElement('option');
					select.options.add(opt);
					opt.value="";
					opt.innerHTML=blankLabel; 
				}
				var dataMap=resultBean.data;
				for(var key in dataMap)
				{
					opt=document.createElement('option');
					select.options.add(opt);
					opt.value=key;
					opt.innerHTML=dataMap[key]; 	
				}
				
			}});	
		}
	});
}

//根据用户权限控制页面显示内容
function checkPermission()
{
	var loginUser=window.top.getLoginUser();
	if(loginUser!=undefined)
	{
		  var permissionIdSet=loginUser.user.permissionIdSet;
		  var permissionId;
		  $("a,input[type='button'],button,div").each(function()
		  {
			  permissionId=$(this).attr("permissionId");
			  if(permissionId!=undefined && !permissionIdSet.contains(permissionId))
			  {
				  //$(this).css("display","none");
				  $(this).remove();
			  }
		  });	
	 }
}

function initXytItem()
{
	initSelectOptions();
	checkPermission();
}

//弹出框移动算法
dialoguiDraggable= function (_dlg) {
    var elem = _dlg.find(".modal-content");
    var startX, startY, x, y;
    x = 0;
    y = 0;
    startX = 0;
    startY = 0;

    var mousemove=null, mouseup=null;
    elem.css({
        position: "relative"
    });
    elem.find(".modal-header").css({
        cursor: "move"
    });
    elem.find(".modal-header").bind("mousedown", function (event) {
        startX = event.screenX - x;
        startY = event.screenY - y;
        $(document).bind("mousemove", mousemove);
        $(document).bind("mouseup", mouseup);
    });
    mousemove = function (event) {
        y = event.screenY - startY;
        x = event.screenX - startX;
        elem.css({
            top: y + "px",
            left: x + "px"
        });
    };
    mouseup = function () {
        $(document).unbind("mousemove", mousemove);
        $(document).unbind("mouseup", mouseup);
    };
};

function _Dialog(title, url, callback, width, callbackpram, styleClassName) {
    var _width = width || 530;
    var _styleClassName = styleClassName || "m-common-bg";
	 options = {
			 _type:'Dialog',
             _dialogW: _width,
             _url:url,
             _title:title,
             _callbackfn: callback,
             _jquery: $, // 测试Jq 的实例传出
             _iscallbackpram: callbackpram,
             _classTileBgStyle: _styleClassName
         };
	 if(width)
		 options._dialogW = width;
    $(window.parent.showPrompt(options));
}

/**
 * 错误提示框，在弹出框中使用
 * @param msg
 * @param icontype: "success"/"error"
 * @parama times显示时间，默认3000毫秒
 */
function _showMessage(msg, icontype,times){
	var _icontype=icontype|| "success";
	var _times=times||3000;
	options = {
            _type: 'MsgDialog',
            _times:_times,
            _countdown:true,
            _msgicon: _icontype,
            _msg:msg,
            _closeDialog:true,
            _callbackfn: function () {
            	$("#iframeid").contents().find("#but-search").click();
            }
        };
        showPrompt(options);
}