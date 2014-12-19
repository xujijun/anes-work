$(function() {
	$("body").append("<div class='divCover'></div>");
	initSelect();  
	bindSelectEvent();
});

var selectedNode = new Object();

function bindSelectEvent() {
	//var select_callback = eval(_callback) ||function(){}; // 处理 Callback
	var $pid = "";
	/* 下拉区域和渠道 */
	$('.xyt-select').delegate('.txt,.btn', 'click', function() {
		$pid = '#' + $(this).parent().parent().attr('id');// 获得当前按钮的父元素(DIV)的id
		$($pid + ' .btn').next('.hiddenDomian').first().attr('flag', 'choose');
		if ($($pid + ' .selectDiv').attr('isDown') == 'false') {
			$(".divCover").show();
			$($pid + ' .selectDiv').show();// 向下滑动
			//$(this).css('background','url('+getRootContextPath()+'/front/resources/xyt-select/up.gif) no-repeat');//改变按钮图标
			$($pid + ' .selectDiv').attr('isDown', 'true');// isDown属性是判断div是否下拉了...这里是设置为true,就是表示已经下滑了
			// 判断这个div是否是第一次点击,是第一次点击就加载树状
			if ($($pid + ' .selectDiv').attr('isinit') == "0") {
				// 因为这个Div初始化的时候会重新生成..所以要在生成的时候加一个input隐藏域
				$($pid + ' .selectDiv').jstree().delegate('a', 'click', function(event, data) {

					 		
							$($pid + ' .txt').val($(this).text());// 设置选择地区的名字到文本框
							$($pid + ' .selectDiv').hide();// 向上收缩
							//$($pid + ' .btn').css('background','url('+getRootContextPath()+'/front/resources/xyt-select/down.gif) no-repeat');//改变按钮图标
							$($pid + ' .selectDiv').attr('isDown', 'false');// isDown属性是判断div是否下拉了...这里是设置为false,就是表示已经收缩了
							$(".divCover").hide();
							$($pid + ' .hiddenDomian[flag="choose"]').val($(this).attr('lang'));
							$($pid + ' .hiddenDomian[flag="choose"]').removeAttr("flag");
							selectedNode.key = $(this).attr('lang');
							selectedNode.value = $(this).text();
							selectedNode.isLeaf = $(this).attr('isLeaf');
							
							// 处理 Callback
							var _parent =$(this).attr("_parent");
							var _callback =$("#"+_parent).attr("callback");
							var select_callback = eval(_callback) ||function(){}; 
							//var args ={"id": $(this).attr("lang"), "name": $(this).text(), "isLeaf": $(this).attr('isLeaf')};
					 		!!_callback && select_callback(selectedNode);// 扩展jsTree, select tree node Callback;;
					 		//END 处理 Callback
						});
				$($pid + ' .selectDiv').attr('isinit', '1');
			}
		} else {
			$(".divCover").show();
			$($pid + ' .selectDiv').hide();
			//$(this).css('background', 'url('+getRootContextPath()+'/front/resources/xyt-select/down.gif) no-repeat');
			$($pid + ' .selectDiv').attr('isDown', 'false');
			}
	});

	// 删除,清空记录
	$('.xyt-select').delegate('.clear-text', 'click', function() {
		var parentId = $(this).parent().parent().attr('id');
		var clear_text = $(this).attr('class');
		$("#" + parentId + " ." + clear_text).prev('.txt').val('');
		$("#" + parentId + " ." + clear_text).next().next('.hiddenDomian').val('');
	});

	// 遮盖层,用在选择地区的时候,当点击选择地区框以外的对象,就模拟关闭按钮,关闭选择地区框
	$('.divCover').click(function() {
		$($pid + ' .selectDiv').hide();
		//$($pid + ' .btn').css('background', 'url('+getRootContextPath()+'/front/resources/xyt-select/down.gif) no-repeat');//改变按钮图标
		$($pid + ' .selectDiv').attr('isDown', 'false');
		$($pid + ' .hiddenDomian[flag="choose"]').removeAttr("flag");// 删除标记
		$(this).hide();
	});
}
function getRootContextPath() {
	var localObj = window.location;
	return localObj.protocol + "//" + localObj.host + "/" + localObj.pathname.split("/")[1];
}
function initSelect() {
	// 初始化默认值
	$('.xyt-select').each(function() {
		var _title = $(this).attr('_Title') || "";
		var _id = $(this).attr('_id') || "";
		var callback =$(this).attr('callback') || "";
		var html = "";
		html += "<span>" + $(this).attr('dialogTitle')
				+ "</span>";
		html += "<div class='xyt-select-child'>";
		html += "<input type='text' class='txt' readonly='readonly' placeholder='"
				+ _title
				+ "' id='"
				+ _id
				+ "' name='"
				+ _id
				+ "' /><span class='clear-text'></span><span class='btn'></span>";
		html += "<div class='selectDiv' isinit='0' isdown='false'>";
		html += "</div></div>";
		$(this).html(html);
	
		// 第一次加载数据
		if ($(this).attr('action').length >= 0) {
			eval($(this).attr('action'));
		}
		var hiddenValue = $(this).attr('hiddenIdValue');// 获取默认值
		var name = $(this).attr('hiddenFieldName');
		$(this).find('.btn').after("<input type='hidden' class='hiddenDomian' id='"
						+ name + "' name='" + name
						+ "' value='" + hiddenValue + "'/>");
		//var displayValue = $(this).attr('displayValue');//获取显示文本框的值
		//$(this).find('.txt').val(displayValue);//设置默认值
		 
	});
}

/**
 * 取得用户所在区域及下属地区
 * @param dialogId
 * @param url
 * @param pid
 */
function loadUserRegions(dialogId,url,pid){
	var isInit = false;
	if (pid == null || pid == undefined || pid == "") {
		isInit = true;
		//pid = window.top.getLoginUser().user.regionId;
		pid = "";
	}
	if (!isInit) {
		if ($("#" + dialogId + "-" + pid).attr("isInit") == "1") {
			return;
		}
		$("#" + dialogId + "-" + pid).attr("isInit", "1");
	}
//	if(isInit&&pid){
//		var data = [{"key":pid,"value":window.top.getLoginUser().user.regionName,"isLeaf":false}];
//		displayTree(dialogId,url,pid,isInit,data);
//	}else{
//		$.getJSON(getContextPath() + url,{parentId : pid},function(response, status, request) {
//			var data = response.data;
//			displayTree(dialogId,url,pid,isInit,data);
//		});
//	}
	$.getJSON(getContextPath() + url,{parentId : pid},function(response, status, request) {
		var data = response.data;
		displayTree(dialogId,url,pid,isInit,data);
	});
	
}
/**
 * 显示地区树
 * @param dialogId divId
 * @param url 请求ID
 * @param pid 父类ID
 * @param isInit 是否初始化
 * @param data
 */
function displayTree(dialogId,url,pid,isInit,data){
	var html = "<ul id='UL_" + pid + "'>";
	if(!data.length){
		for ( var key in data) {
			html += "<li class='jstree-closed'><ins class='jstree-icon'>&#160;</ins>";
			html += "<a _parent='"+dialogId+"' href='javascript:void(0)' id='"
					+ dialogId
					+ "-"
					+ key
					+ "' lang='"
					+ key
					+ "' isInit='0' action=\"loadDataToJTreeDialog('"
					+ dialogId
					+ "','"
					+ url
					+ "','"
					+ key
					+ "')\"><ins class='jstree-icon'>&#160;</ins>"
					+ data[key] + "</a>";
			html += "</li>";
		}
	} else {
		for (var i = 0; i < data.length; i++) {
			var bean = data[i];
			var key = bean.key;
			var value = bean.value;
			if(bean.isLeaf) {
				html += "<li class='jstree-leaf'>";
			} else {
				html += "<li class='jstree-closed'>";
			}
			html += "<ins class='jstree-icon'>&#160;</ins>";
			html += "<a _parent='"+dialogId+"' href='javascript:void(0)' id='"
					+ dialogId
					+ "-"
					+ key
					+ "' lang='"
					+ key
					+ "' isLeaf='"
					+ bean.isLeaf
					+ "' isInit='0'";
//					if(bean.isLeaf){
//						html += "<ins class='jstree-icon'>&#160;</ins>";
//					}else{
//						html += " action=\"loadDataToJTreeDialog('"
//							+ dialogId
//							+ "','"
//							+ url
//							+ "','"
//							+ key
//							+ "')\"><ins class='jstree-icon'>&#160;</ins>";
//					}
					if(!bean.isLeaf) {
						html += " action=\"loadDataToJTreeDialog('"
								+ dialogId
								+ "','"
								+ url
								+ "','"
								+ key
								+ "')\"><ins class='jstree-icon'>&#160;</ins>";
					} else {
						if(isInit){
							html += "<ins class='jstree-icon'>&#160;</ins>";
						}else{
							html += "><img src='" + getContextPath() + "/views/resources/xyt-select/folder.jpg'>&#160;</img>";
						}
					}
					html += value + "</a>";
			html += "</li>";
		}
	}
	html += "</ul>";
	if (isInit) {
		$("#" + dialogId + ' .selectDiv').html(html);
	} else {
		$("#" + dialogId + "-" + pid).after(html);
	}
}

function loadDataToJTreeDialog(dialogId, url, pid) {
	var isInit = false;
	if (pid == null || pid == undefined || pid == "") {
		isInit = true;
		pid = "";
	}
	if (!isInit) {
		if ($("#" + dialogId + "-" + pid).attr("isInit") == "1") {
			return;
		}
		$("#" + dialogId + "-" + pid).attr("isInit", "1");
	}
	$.getJSON(getContextPath() + url,{parentId : pid},function(response, status, request) {
		displayTree(dialogId,url,pid,isInit,response.data);
	});
}

/**
 * 获得当前选中节点object属性：key,value,isLeaf
 * @returns {}
 */
function getSelectedNode(){
	return selectedNode;
}