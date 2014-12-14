function getContextPath()
{
	return "/anes-work";
}

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