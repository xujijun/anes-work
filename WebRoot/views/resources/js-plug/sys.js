function getContextPath()
{
	return "/anes-work";
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