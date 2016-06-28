(function($) {
	    //将表单序列化成JSON对象
		$.fn.serializeJson = function() {
			var serializeObj = {};
			$(this.serializeArray()).each(function() {
				serializeObj[this.name] = this.value;
			});
			return serializeObj;
		};
		
		//获取URL参数
		 $.getUrlParam = function (name) {
             var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
             var r = window.location.search.substr(1).match(reg);
             if (r != null) return decodeURI(r[2]); return null;
         };
		
		//子类继承父类json字符串并转换为json对象
		var addParam =function(otherData){
		    var Data, tempData = JSON.parse(JSON.stringify(Base));
		    var extend = function(destination, source) {
		        for (var property in source)
		        destination[property] = source[property];
		        return destination;
		    };
		    Data = extend(tempData, otherData);
		    return JSON.stringify(Data);
		};
})(jQuery);


(function(win, undefined) {
    "use strict";

    var Base = (function(){

        var getBrowser = (function(){
            var nav = win.navigator,
                ua = nav.userAgent.toLowerCase();

            ua = /(iphone|ipad|ipod)(?:.*version)?[ \/]([\w.]+)/.exec(ua) || // Mobile IOS
                /(android)(?:.*version)?[ \/]([\w.]+)/.exec(ua) || []; // Mobile Webkit

            var browser = ua[1],
                version = ua[2];

           switch (browser) {
                case "ipod":
                case "ipad":
                case "iphone":
                    browser = "iOS";
                    break;
            }

            var YSAppFlg = false,
                YSAppOs = '';

            if (typeof(getDevice) === 'function') { //调用 iOS App 查询接口
                YSAppFlg = true;
                YSAppOs = getDevice();
            }else if(typeof(sys) !== 'undefined' && typeof(sys.getDevice) === 'function') { //调用 Android App 查询接口
                YSAppFlg = true;
                YSAppOs = sys.getDevice();
            }

            return {
                browser : browser,
                version : version,
                isWeixin : /MicroMessenger/gi.test(nav.userAgent),
                isYsApp : YSAppFlg,
                YsAppOs : YSAppOs
            }
        }());

        return {
//            appVersion:'1.0.1', //应用程序版本号
//            osVersion:getBrowser.version,
//            termTyp:getBrowser.browser, //客户端类型
//            isWeixin:getBrowser.isWeixin, //是否为微信客户端
//            isYsApp:getBrowser.isYsApp, //是否为有赏APP客户端
//            YsAppOs:getBrowser.YsAppOs, //运行有赏APP的平台
//            channelId:'WCH', //h5渠道
            requestTm:Math.round(new Date().getTime()/1000) //请求时间
        }

    }());

    win.Base = Base;

    win.APPCODEID = '';
    win.setAppCodeid = function(codeid){ // APP 传参 Codeid
        win.APPCODEID = codeid || '';
    };

}(window));


var addParam =function(otherData){
    var Data, tempData = JSON.parse(JSON.stringify(Base));
    var extend = function(destination, source) {
        for (var property in source)
        destination[property] = source[property];
        return destination;
    };
    Data = extend(tempData, otherData);
    return JSON.stringify(Data);
};