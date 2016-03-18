/**
 * Created by GiGoldTool.prototype on 2016/03/08.
 * description 独孤九剑全局通用方法库
 * keyword 方法库
 * mobile 18073099897
 * QQ 1181939891
 * naming rule
 *  {
 *  	module : CMD规范
 *      css : {class:全单词小写加中划线，id:全单词首字母大写},
 *      js : {驼峰命名: 首字母小写其后单词首字母大写},单元js请使用单体模式开发<一个命名空间包含自己的所有代码>
 *      html : '请使用语义化html标签'
 *  }
 */
define(function(require, exports, module){

	function GiGoldTool(){};

	/*格式化日期时间*/
	GiGoldTool.prototype.date = function(format, date, datePipe, timePipe){
		var type = typeof date,
			datePipe = typeof datePipe != 'undefined' ? datePipe : '-';
		timePipe = typeof timePipe != 'undefined' ? timePipe : ':';
		ret = null;

		if(type == 'string'){
			var oDate = new Date(date);
		}else if(type == 'object'){
			var oDate = date;
		}else{
			var oDate = new Date();
		}

		var Y = oDate.getFullYear(), M = oDate.getMonth() + 1, D = oDate.getDate();
		var H = oDate.getHours(), I = oDate.getMinutes(), S = oDate.getSeconds();

		var c = function(a, pipe){
			var ret = [];
			for(var i = 0, len = a.length; i < len; i++){
				var x = a[i];
				x = x < 10 ? '0' + x : x
				ret.push(x);
			}
			return ret.join(pipe);
		}

		switch(format){
			case 'YM':
				ret = c([Y, M], datePipe);
				break;
			case 'MD':
				ret = c([M, D], datePipe);
				break;
			case 'HI':
				ret = c([H, I], timePipe);
				break;
			case 'HIS':
				ret = c([H, I, S], timePipe);
				break;
			case 'IS':
				ret = c([I, S], timePipe);
				break;
			case 'YMDHI':
				ret = GiGoldTool.prototype.date('YMD', oDate, datePipe) + ' ' + GiGoldTool.prototype.date('HI', oDate, '', timePipe);
				break;
			case 'YMD':
			default:
				ret = c([Y, M, D], datePipe);
		}
		return ret;
	};

	/*IE浏览器识别*/
	GiGoldTool.prototype.isIE = function(x){
		var ua = navigator.userAgent.toLowerCase();
		return /msie/.test(ua) && !/opera/.test(ua) && (ua.match(/.+(?:rv|it|ra|ie)[\/: ]([\d.]+)/) || [])[1] == '' + x + ".0";
	};

	/*IE78浏览器识别*/
	GiGoldTool.prototype.isIE78 = function(){
		return GiGoldTool.prototype.isIE(7) || GiGoldTool.prototype.isIE(8);
	};

	/*IE789浏览器识别*/
	GiGoldTool.prototype.isIE789 = function(){
		return GiGoldTool.prototype.isIE(7) || GiGoldTool.prototype.isIE(8) || GiGoldTool.prototype.isIE(9);
	};

	/*微信浏览器识别*/
	GiGoldTool.prototype.isWeChat = function(){
		var ua = navigator.userAgent.toLowerCase();
		return ua.match(/MicroMessenger/i);
	};

	/*浏览器版本获取*/
	GiGoldTool.prototype.getBrower = function(){
		var brower = {};
		var ua = navigator.userAgent.toLowerCase();
		var s;
		(s = ua.match(/msie ([\d.]+)/)) ? brower.ie = s[1] :
			(s = ua.match(/firefox\/([\d.]+)/)) ? brower.firefox = s[1] :
				(s = ua.match(/chrome\/([\d.]+)/)) ? brower.chrome = s[1] :
					(s = ua.match(/opera.([\d.]+)/)) ? brower.opera = s[1] :
						(s = ua.match(/version\/([\d.]+).*safari/)) ? brower.safari = s[1] : 0;
		return brower;
	};

	/*获取浏览器滚动条的宽*/
	GiGoldTool.prototype.scrollBarWidth = function(){
		var oP = document.createElement('p'),
			styles = {
				width: '100px',
				height: '100px',
				overflowY: 'scroll'
			}, i, scrollbarWidth;
		for (i in styles) oP.style[i] = styles[i];
		document.body.appendChild(oP);
		scrollbarWidth = oP.offsetWidth - oP.clientWidth;
		oP.remove();
		return scrollbarWidth;
	};

	/*window.screen*/
	GiGoldTool.prototype.windowElement = function(){
		var tempWin = window.screen;
		return {
			'resolutionTop': tempWin.screenTop || tempWin.availTop,/*窗口距离屏幕顶部*/
			'resolutionLeft': tempWin.screenLeft || tempWin.availLeft,/*窗口距离屏幕左边界*/
			'resolutionWidth': tempWin.width,/*窗口可用工作区宽度*/
			'resolutionHeight': tempWin.height,/*窗口可用工作区高度*/
			'resolutionEnableWidth': tempWin.availWidth,/*窗口可用工作区宽度*/
			'resolutionEnableHeight': tempWin.availHeight,/*窗口可用工作区高度*/
			'resolutionColorDepth': tempWin.colorDepth,/*屏幕?位彩色*/
			'resolutionSize': tempWin.deviceXDPI || tempWin.pixelDepth/*屏幕?像素/英寸*/
		};
	};

	/*htmlCode格式化*/
	GiGoldTool.prototype.htmlEncode = function(text) {
		return (typeof text == 'string') ? text.replace(/&/g, '&amp;').replace(/</g, '&lt;').replace(/>/g, '&gt;').replace(/"/g, '&quot;') : text;
	};

	/*格式化日期时间*/
	GiGoldTool.prototype.date = function(format, date, datePipe, timePipe){
		var type = typeof date,
			datePipe = typeof datePipe != 'undefined' ? datePipe : '-';
		timePipe = typeof timePipe != 'undefined' ? timePipe : ':';
		var ret = null;

		if(type == 'string'){
			var oDate = new Date(date);
		}else if(type == 'object'){
			var oDate = date;
		}else{
			var oDate = new Date();
		}

		var Y = oDate.getFullYear(), M = oDate.getMonth() + 1, D = oDate.getDate();
		var H = oDate.getHours(), I = oDate.getMinutes(), S = oDate.getSeconds();

		var c = function(a, pipe){
			var ret = [];
			for(var i = 0, len = a.length; i < len; i++){
				var x = a[i];
				x = x < 10 ? '0' + x : x
				ret.push(x);
			}
			return ret.join(pipe);
		}

		switch(format){
			case 'YM':
				ret = c([Y, M], datePipe);
				break;
			case 'MD':
				ret = c([M, D], datePipe);
				break;
			case 'HI':
				ret = c([H, I], timePipe);
				break;
			case 'HIS':
				ret = c([H, I, S], timePipe);
				break;
			case 'IS':
				ret = c([I, S], timePipe);
				break;
			case 'YMDHI':
				ret = GiGoldTool.prototype.date('YMD', oDate, datePipe) + ' ' + XST.date('HI', oDate, '', timePipe);
				break;
			case 'YMD':
			default:
				ret = c([Y, M, D], datePipe);
		}
		return ret;
	};

	/*cookie 如果给定值,则设定cookie的值*/
	GiGoldTool.prototype.cookie = function (name, value, options) {
		if (typeof value != 'undefined') {
			options = options || {};
			if (value === null) {
				value = '';
				options = $.extend({}, options);
				options.expires = -1;
			}
			var expires = '';
			if (options.expires && (typeof options.expires == 'number'|| options.expires.toUTCString)) {
				var date;
				if (typeof options.expires == 'number') {
					date = new Date();
					date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
				}
				else {
					date = options.expires;
				}
				expires = '; expires=' + date.toUTCString();
			}
			var path = options.path ? '; path=' + (options.path) : '';
			var domain = options.domain ? '; domain=' + (options.domain) : '';
			var secure = options.secure ? '; secure' : '';
			document.cookie = [
				name,
				'=',
				encodeURIComponent(value),
				expires,
				path,
				domain,
				secure
			].join('');
		}
		else {
			var cookieValue = null;
			if (document.cookie && document.cookie != '') {
				var cookies = document.cookie.split(';');
				for (var i = 0, len = cookies.length; i < len; i++) {
					var cookie = jQuery.trim(cookies[i]);
					if (cookie.substring(0, name.length + 1) == (name + '=')) {
						cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
						break;
					}
				}
			}
			return cookieValue;
		}
	};

	var keyStr = "ABCDEFGHIJKLMNOP" + "QRSTUVWXYZabcdef" +  "ghijklmnopqrstuv" + "wxyz0123456789+/" + "=";

	/*base64加密*/
	GiGoldTool.prototype.encode64 = function (input) {
		input = escape(input);
		var output = "";
		var chr1, chr2, chr3 = "";
		var enc1, enc2, enc3, enc4 = "";
		var i = 0;
		do {
			chr1 = input.charCodeAt(i++);
			chr2 = input.charCodeAt(i++);
			chr3 = input.charCodeAt(i++);
			enc1 = chr1 >> 2;
			enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
			enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
			enc4 = chr3 & 63;
			if (isNaN(chr2)) {
				enc3 = enc4 = 64;
			}
			else if (isNaN(chr3)) {
				enc4 = 64;
			}
			output = output +
				keyStr.charAt(enc1) +
				keyStr.charAt(enc2) +
				keyStr.charAt(enc3) +
				keyStr.charAt(enc4);
			chr1 = chr2 = chr3 = "";
			enc1 = enc2 = enc3 = enc4 = "";
		} while (i < input.length);
		return output;
	};

	/*base64解密*/
	GiGoldTool.prototype.decode64 = function (input) {
		var output = "";
		var chr1, chr2, chr3 = "";
		var enc1, enc2, enc3, enc4 = "";
		var i = 0;
		/* remove all characters that are not A-Z, a-z, 0-9, +, /, or =*/
		var base64test = /[^A-Za-z0-9\+\/\=]/g;
		if (base64test.exec(input)) {
			console && console.log("There were invalid base64 characters in the input text.\n" +
				"Valid base64 characters are A-Z, a-z, 0-9, '+', '/',and '='\n" +
				"Expect errors in decoding.");
		}
		input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
		do {
			enc1 = keyStr.indexOf(input.charAt(i++));
			enc2 = keyStr.indexOf(input.charAt(i++));
			enc3 = keyStr.indexOf(input.charAt(i++));
			enc4 = keyStr.indexOf(input.charAt(i++));
			chr1 = (enc1 << 2) | (enc2 >> 4);
			chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
			chr3 = ((enc3 & 3) << 6) | enc4;
			output = output + String.fromCharCode(chr1);
			if (enc3 != 64) {
				output = output + String.fromCharCode(chr2);
			}
			if (enc4 != 64) {
				output = output + String.fromCharCode(chr3);
			}
			chr1 = chr2 = chr3 = "";
			enc1 = enc2 = enc3 = enc4 = "";
		} while (i < input.length);
		return unescape(output);
	};

	//添加到收藏夹
	GiGoldTool.prototype.addFavorite = function(url, title){
		try{
			window.external.addFavorite(url, title);
		}catch (e){
			try{
				window.sidebar.addPanel(url, title, "");
			}catch (e){
				alert("抱歉，您所使用的浏览器无法完成此操作。\n\n加入收藏失败，请使用Ctrl+D进行添加");
			}
		}
	};

	//设为网页
	GiGoldTool.prototype.setHome = function(obj, url){
		try{
			obj.style.behavior='url(#default#homepage)';
			obj.setHome(url);
		}catch(e){
			if(window.netscape) {
				try {
					netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
				}catch (e) {
					alert("此操作被浏览器拒绝！\n请在浏览器地址栏输入“about:config”并回车\n然后将 [signed.applets.codebase_principal_support]的值设置为'true',双击即可。");
				}
				var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
				prefs.setCharPref('browser.startup.homepage',url);
			}else{
				alert('您的浏览器不支持此操作\n请使用浏览器的“选项”或“设置”等功能设置首页');
				return false;
			}
		}
	};

	//深度拷贝对象
	GiGoldTool.prototype.deepCopyObj = function(source) {
		var result = {};
		for (var key in source) {
			result[key] = typeof source[key]==='object'? GiGoldTool.prototype.deepCopyObj(source[key]): source[key];
		}
		return result;
	};

	//get sessionStorage
	GiGoldTool.prototype.getSessionStorage = function(key){
		var r;
		if(window.sessionStorage){
			r=JSON.parse(window.sessionStorage.getItem(key));
		}
		return r;
	};

	//set sessionStorage
	GiGoldTool.prototype.setSessionStorage = function(key,value){
		value =JSON.stringify(value);
		if(window.sessionStorage){
			window.sessionStorage.setItem(key,value);
		}
	};

	//get localStorage
	GiGoldTool.prototype.getLocalStorage = function(key){
		var r;
		if(window.localStorage){
			r = JSON.parse(window.localStorage.getItem(key));
		}
		return r;
	};

	//set localStorage
	GiGoldTool.prototype.setLocalStorage = function(key,value){
		value = JSON.stringify(value);
		if(window.localStorage){
			window.localStorage.setItem(key,value);
		}
	}
	//提供整个全局公共方法
	module.exports = {
		GiGoldTool: GiGoldTool
	};
});







