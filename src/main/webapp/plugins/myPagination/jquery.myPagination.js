
(function ($) {
	$.fn.myPagination = function (param) {
		if (param && param instanceof Object) {
			var options;
			var currPage;
			var totalPage;
			var pageSize;
			var minPageSize;
			var fyPageInputName;
			var fyNumInputName;
			var maxPageNum;
			var minPageNum;
			var pageNum;
			var tempPage;
			var obj = $(this);
			var defaults = new Object({currPage:1, totalPage:1, fyPageInputName:'fypage', fyNumInputName:'fynum', maxPageNum:100, minPageNum:5, pageNum:10, pageSize:1, minPageSize:10, cssStyle:"badoo", ajax:{on:false, hiddenId:"totalPage", param:{on:false, page:1}}, info:{first:"\xe9\xa6\x96\xe9\xa1\xb5", last:"\xe5\xb0\xbe\xe9\xa1\xb5", next:"\xe4\xb8\x8b\xe4\xb8\x80\xe9\xa1\xb5", prev:"\xe4\xb8\x8a\xe4\xb8\x80\xe9\xa1\xb5", first_on:true, last_on:true,num_on:true, next_on:true, prev_on:true, msg_on:true, link:"#", msg:"<span>&nbsp;&nbsp;\xe8\xb7\xb3{curr}/{sum}\xe9\xa1\xb5</span>", text:{width:"28px"}}});
			function getCurrPage() {
				if (options.currPage) {
					return options.currPage;
				} else {
					return defaults.currPage;
				}
			}
			function getPageNum() {
				if (options.pageNum) {
					return options.pageNum;
				} else {
					return defaults.pageNum;
				}
			}
			function gettotalPage() {
				if (options.totalPage) {
					return options.totalPage;
				} else {
					return defaults.totalPage;
				}
			}
			function getPageSize() {
				if (options.pageSize) {
					if(gettotalPage()<options.pageSize)
					{
						return 	gettotalPage();
					}
					return options.pageSize;
				} else {
					return defaults.pageSize;
				}
			}
			function getMaxPageNum() {
				if (options.maxPageNum) {
					return options.maxPageNum;
				} else {
					return defaults.maxPageNum;
				}
			}
			function getMinPageNum() {
				if (options.minPageNum) {
					return options.minPageNum;
				} else {
					return defaults.minPageNum;
				}
			}
			function getMinPageSize() {
				if (options.minPageSize) {
					return options.minPageSize;
				} else {
					return defaults.minPageSize;
				}
			}
			
			function getCssStyle() {
				if (options.cssStyle) {
					return options.cssStyle;
				} else {
					return defaults.cssStyle;
				}
			}
			function getAjax() {
				if (options.ajax && options.ajax.on) {
					return options.ajax;
				} else {
					return defaults.ajax;
				}
			}
			function getParam() {
				if (options.ajax.param && options.ajax.param.on) {
					options.ajax.param.page = currPage;
					return options.ajax.param;
				} else {
					defaults.ajax.param.page = currPage;
					return defaults.ajax.param;
				}
			}
			function getFyPageInputName(){
				if (options.fyPageInputName ) {
					return options.fyPageInputName;
				} else {
					return defaults.fyPageInputName;
				}
			}
			function getFyNumInputName(){
				if (options.fyNumInputName ) {
					return options.fyNumInputName;
				} else {
					return defaults.fyNumInputName;
				}
			}
			function getFirst() {
				if (options.info && options.info.first_on == false) {
					return "";
				}
				if (options.info && options.info.first_on && options.info.first) {
					if(currPage==1)
					{
						return "<span class=\"disabled\" title='1'>" +  options.info.first + " </span>";
					}
					var str = "<a href='" + getLink() + "' title='1'>" + options.info.first + "</a>";
					return str;
				} else {
					var str = "<a href='" + getLink() + "' title='1'>" + defaults.info.first + "</a>";
					return str;
				}
			}
			function getLast(totalPage) {
				if (options.info && options.info.last_on == false) {
					return "";
				}
				if (options.info && options.info.last_on && options.info.last) {
					if(currPage==totalPage)
					{
						return "<span class=\"disabled\" title='"+totalPage+"'>" +  options.info.last + " </span>";
					}
					return "<a href='" + getLink() + "' title='" + totalPage + "'>" + options.info.last + "</a>";
				} else {
					return "<a href='" + getLink() + "' title='" + totalPage + "'>" + defaults.info.last + "</a>";
				}
			}
			function getPrev() {
				if (options.info && options.info.prev_on == false) {
					return "";
				}
				if (options.info && options.info.prev) {
					return options.info.prev;
				} else {
					return defaults.info.prev;
				}
			}
			function getNext() {
				if (options.info && options.info.next_on == false) {
					return "";
				}
				if (options.info && options.info.next) {
					return options.info.next;
				} else {
					return defaults.info.next;
				}
			}
			function getLink() {
				if (options.info && options.info.link) {
					return options.info.link;
				} else {
					return defaults.info.link;
				}
			}
			function getMsg() {
				var input = "<input id='"+getFyPageInputName()+"' type='text' value='" + currPage + "' >";
				var pagenuminput = "<input id='"+getFyNumInputName()+"' type='text' value='" + pageNum + "' >";

				if (options.info && options.info.msg_on == false) {
					return false;
				}
				if (options.info && options.info.msg) {
					var str = options.info.msg;
					str = str.replace("{curr}", input);
					str = str.replace("{sum}", totalPage);
					str = str.replace("{pageNum}",pagenuminput);
					return str;
				} else {
					var str = defaults.info.msg;
					str = str.replace("{curr}", input);
					str = str.replace("{sum}", totalPage);
					str = str.replace("{pageNum}",pagenuminput);
					return str;
				}
			}
			function getText() {
				var msg = getMsg();
				if (msg) {
					msg = $(msg);
				} else {
					return "";
				}
				var input = msg.children(":text");
				if (options.info && options.info.text) {
					var css = options.info.text;
					for (temp in css) {
						var val = eval("css." + temp);
						input.css(temp, val);
					}
					return msg.html();
				} else {
					var css = defaults.info.text;
					for (temp in css) {
						var val = eval("css." + temp);
						input.css(temp, val);
					}
					return msg.html();
				}
			}
			function getHiddendId() {
				if (options.ajax && options.ajax.hiddendId) {
					return options.ajax.hiddendId;
				} else {
					return defaults.ajax.hiddendId;
				}
			}
			function getInt(val) {
				return parseInt(val);
			}
			function isCode(val) {
				if (val < 1) {
					alert("????????????????????????1");
					return false;
				}
				var patrn = /^[0-9]{1,8}$/;
				if (!patrn.exec(val)) {
					alert("???????????????");
					return false;
				}
				if (val > totalPage && options.info.num_on == true) {
					alert("?????????????????????");
					return false;
				}
				return true;
			}
			function isPageNum(val) {
				var patrn = /^[0-9]{1,8}$/;
				if (!patrn.exec(val)) {
					alert("???????????????");
					return false;
				}
				if (val < getMinPageNum()) {
					alert("????????????????????????"+getMinPageNum());
					return false;
				}
				if (getMaxPageNum()!=0 && val > getMaxPageNum()) {
					alert("????????????????????????"+getMaxPageNum());
					return false;
				}
				return true;
			}
			function updateView() {
				currPage = getInt(currPage);
				totalPage = getInt(totalPage);
				var link = getLink();
				var firstPage = lastPage = 1;
				if (currPage - tempPage > 0) {
					firstPage = currPage - tempPage;
				} else {
					firstPage = 1;
				}
				if (firstPage + pageSize > totalPage) {
					lastPage = totalPage + 1;
					firstPage = lastPage - pageSize;
				} else {
					lastPage = firstPage + pageSize;
				}
				var content = "";
				content += getFirst();
				if (currPage == 1) {
					content += "<span class=\"disabled\" title=\"" + getPrev() + "\">" + getPrev() + " </span>";
				} else {
					content += "<a href='" + link + "' title='" + (currPage - 1) + "'>" + getPrev() + " </a>";
				}
				if (options.info && options.info.num_on == true) {
					for (firstPage; firstPage < lastPage; firstPage++) {
						if (firstPage == currPage) {
							content += "<span class=\"current\" title=\"" + firstPage + "\">" + firstPage + "</span>";
						} else {
							content += "<a href='" + link + "' title='" + firstPage + "'>" + firstPage + "</a>";
						}
					}
				}
				if ((currPage == totalPage && options.info.num_on == true) || checkIsNext()) {
					content += "<span class=\"disabled\" title=\"" + getNext() + "\">" + getNext() + " </span>";
				} else {
					content += "<a href='" + link + "' title='" + (currPage + 1) + "'>" + getNext() + " </a>";
				}
				content += getLast(totalPage);
				content += getText();
				obj.html(content);
				//?????????????????????????????????
				/** jQuery("#"+getFyPageInputName()).keypress(function (event) {
					var keycode = event.which;
					if (keycode == 13) {
						var page = $(this).val();
						if (isCode(page)) {
							obj.children("a").unbind("click");
							//createView(page);
							options.inputPageMethod();
						}
					}
				});
				jQuery("#"+getFyNumInputName()).keypress(function (event) {
					var keycode = event.which;
					if (keycode == 13) {
						var fyPageNum = $(this).val();
						if (isPageNum(fyPageNum)) {
							options.inputPageNumMethod();
						}
					}
				});**/
				obj.children("a").each(function (i) {
					var page = this.title;
					$(this).click(function () {
						obj.children("a").unbind("click");
						$('#fypage').val(page);
						//createView(page);
						$(this).focus();
						options.onchange(page);
						return false;
					});
				});
			}
			function createView(page) {
				return ;
				currPage = page;
				var ajax = getAjax();
				if (ajax.on) {
					var varUrl = ajax.url;
					var param = getParam();
					$.ajax({url:varUrl, type:"GET", data:param, contentType:"application/x-www-form-urlencoded;utf-8", async:true, cache:false, timeout:60000, error:function () {
						alert("444");
					}, success:function (data) {
						loadtotalPage({dataType:ajax.dataType, callback:ajax.callback, data:data});
						updateView();
					}});
				} else {
					updateView();
				}
			}
			function checkParam() {
				if (currPage < 1) {
					alert("?????????????????????1");
					return false;
				}
				if (currPage > totalPage && options.info.num_on == true) {
					alert("??????????????????????????????");
					return false;
				}
				if (pageSize < 1) {
					alert("??????????????????1");
					return false;
				}
				if (pageSize > 60) {
					alert("??????????????????60");
					return false;
				}
				return true;
			}
			function loadtotalPage(options) {
				if (options.dataType) {
					var data = options.data;
					var resulttotalPage = false;
					var isB = true;
					switch (options.dataType) {
					  case "json":
						data = eval("(" + data + ")");
						resulttotalPage = data.totalPage;
						break;
					  case "xml":
						resulttotalPage = $(data).find("totalPage").text();
						break;
					  default:
						isB = false;
						var callback = options.callback + "(data)";
						eval(callback);
						resulttotalPage = $("#totalPage").val();
						break;
					}
					if (resulttotalPage) {
						totalPage = resulttotalPage;
					}
					if (isB) {
						var callback = options.callback + "(data)";
						eval(callback);
					}
				}
			}
			// ??????????????????????????????????????????????????????????????????
			function checkIsNext() {
				//?????????undefined ??????????????????
				if (options.info.now_num == 'undefinded') {
					return false;
				} else {
					//??????????????????????????????????????????????????????????????????
					if(options.info.now_num < getPageNum()){
						return true;
					}else {
						return false;
					}
				}
			}
			options = param;
			currPage = getCurrPage();
			pageNum = getPageNum();
			totalPage = gettotalPage();
			pageSize = getPageSize();
			minPageSize = getMinPageSize();
			tempPage = getInt(pageSize / 2);
			var cssStyle = getCssStyle();
			obj.addClass(cssStyle);
			if (checkParam()) {
				updateView();
				createView(currPage);
			}
		}
		return $(this);
	};
})(jQuery);
