<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/pingpp.js"></script>

<script type="text/javascript">

var jsonObj = ${charge}

//获取服务器的json串,向ping++服务器发送请求进行支付,ping++将返回支付界面,点击支付,成功后,将回调success.jsp页面
pingpp.createPayment(jsonObj, function(result, err){
	// object 需是 Charge/Order/Recharge 的 JSON 字符串
	// 可按需使用 alert 方法弹出 log
	//console.log(JSON.stringify(object));
	    alert("result:" + result);
	    alert("err.msg:" + err.msg);
	    alert("err.extra:" + err.extra);
	    if (result == "success") {
	    	
	    	// 只有微信JSAPI (wx_pub)、微信小程序（wx_lite）、QQ 公众号 (qpay_pub)、支付宝小程序（alipay_lite）支付成功的结果会在这里返回，其他的支付结果都会跳转到 extra 中对应的 URL
	    } else if (result == "fail") {
	    
	        // Ping++ 对象 object不正确或者微信JSAPI/微信小程序/QQ公众号支付失败时会在此处返回
	    } else if (result == "cancel") {
	    	
	        // 微信JSAPI、微信小程序、QQ 公众号、支付宝小程序支付取消支付
	    }else{
	    	
	    }
	});


</script>
</head>
<body>
支付界面


</body>
</html>

