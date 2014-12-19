function createPager(ajaxMethod,id,pageSize)
{
	if(pageSize==undefined){pageSize=20;}
	var html='<div class="xyt-pager" id="pager_'+ajaxMethod+'">\
    			<div class="xyt-pager-content">\
	<span id="'+ajaxMethod+'_first" class="disabled" onclick="ajaxPagerNav(\''+ajaxMethod+'\',\'F\')">首页</span>\
        			<span id="'+ajaxMethod+'_prev" class="xyt-pager-left disabled opacity" title="上一页" onclick="ajaxPagerNav(\''+ajaxMethod+'\',\'P\')"></span>\
        			<span class="default">第 <input type="text" size="5" maxLength="10" id="'+ajaxMethod+'_currentPage" value="0" onkeypress="event.returnValue=((event.keyCode >= 48) && (event.keyCode <= 57));if(event.keyCode==13){if(!isPos(this.value)){this.value=1;}else{ajaxPagerNav(\''+ajaxMethod+'\',\'C\');}}"/> / <span id="'+ajaxMethod+'_totalPage">0</span> 页</span>\
        			<span class="default">\
        				每页 <span id="'+ajaxMethod+'_pageSize">0</span> 条记录\
        				共 <span id="'+ajaxMethod+'_dataSize">0</span> 条记录\
        			</span>\
        			<span id="'+ajaxMethod+'_next" class="xyt-pager-right disabled opacity" title="下一页" onclick="ajaxPagerNav(\''+ajaxMethod+'\',\'N\')"></span>\
        			<span id="'+ajaxMethod+'_last" class="disabled" onclick="ajaxPagerNav(\''+ajaxMethod+'\',\'L\')">末页</span>\
        		</div>\
        		<input type="hidden" name="pageSize" value="'+pageSize+'"/>\
        		<input type="hidden" name="currentPage" value="1"/>\
        	 </div>';
	if(id==undefined)
	{
		document.writeln(html);
	}else{
		$("#"+id).html(html);
	}
}

function ajaxPagerNav(ajaxMethod,nav)
{
	var dataSize=parseInt($("#"+ajaxMethod+"_dataSize").html(),10);
	var pageSize=parseInt($("#pager_"+ajaxMethod+">[name='pageSize']").val(),10);
	var currentPage=parseInt($("#"+ajaxMethod+"_currentPage").val(),10);
	var totalPage=0;
	if(dataSize<pageSize)
	{
		totalPage=1;
	}else{
		totalPage=dataSize/pageSize;
		totalPage+=dataSize%pageSize>0?1:0;
	}
	totalPage=parseInt(totalPage+"",10);
	if(nav=="P")
	{
		if(currentPage<=1){return;}
		currentPage-=1;
	}else if(nav=="F"){
		if(currentPage<=1){return;}
		currentPage=1;
	}else if(nav=="N"){
	    if(currentPage>=totalPage){return;}
		currentPage+=1;
	}else if(nav=="L"){
		if(currentPage>=totalPage){return;}
		currentPage=totalPage;
	}else if(nav=="C"){
		if(currentPage>totalPage || currentPage<1){return;}
	}
	$("#pager_"+ajaxMethod+">[name='currentPage']").val(currentPage);
	eval(ajaxMethod+"()");
}

function setAjaxPagerValues(ajaxMethod,dataSize,pageSize,currentPage)
{
	var totalPage=0;
	if(dataSize<pageSize)
	{
		totalPage=1;
	}else{
		totalPage=dataSize/pageSize;
		totalPage+=dataSize%pageSize>0?1:0;
	}
	totalPage=parseInt(totalPage+"",10);
	if(dataSize==0){currentPage=1;}
	if(currentPage>totalPage){currentPage=1;}
	$("#"+ajaxMethod+"_currentPage").val(currentPage);
	$("#"+ajaxMethod+"_totalPage").html(totalPage);
	$("#"+ajaxMethod+"_dataSize").html(dataSize);
	$("#"+ajaxMethod+"_pageSize").html(pageSize);
	$("#pager_"+ajaxMethod+">[name='pageSize']").val(pageSize);
	$("#pager_"+ajaxMethod+">[name='currentPage']").val(currentPage);
	$("#"+ajaxMethod+"_first").attr("class",currentPage>1?"":"disabled");
	$("#"+ajaxMethod+"_last").attr("class",currentPage==totalPage?"disabled":"");
	$("#"+ajaxMethod+"_prev").attr("class",currentPage>1?"xyt-pager-left":"xyt-pager-left disabled opacity");
	$("#"+ajaxMethod+"_next").attr("class",currentPage==totalPage?"xyt-pager-right disabled opacity":"xyt-pager-right");
}