package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pingplusplus.Pingpp;
import com.pingplusplus.exception.APIConnectionException;
import com.pingplusplus.exception.APIException;
import com.pingplusplus.exception.AuthenticationException;
import com.pingplusplus.exception.ChannelException;
import com.pingplusplus.exception.InvalidRequestException;
import com.pingplusplus.exception.RateLimitException;
import com.pingplusplus.model.Charge;

@WebServlet(urlPatterns = "/pay.do")
public class PayServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1.从客户端获取支付金额
		Integer orderMoney = 100;
		// 2.向Ping++服务器发送支付请求
		String appId = "app_rz5q90Kqn1eT9OGa";
		//测试私钥
		Pingpp.apiKey = "sk_test_mrfL0OK4SmrT8KmnX9TyDSG0";
		//请求签名
		Pingpp.privateKey = "-----BEGIN PRIVATE KEY-----\n" + 
        		"MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDkjPz9Nq0BRFtc\n" + 
        		"vtpidZDiGxKQfFRQW4F7ovNXHSdTiYto1TW2fF5rz+LUSqizBgYRJbq44tAjKHGG\n" + 
        		"XIG63+AlfVxyO0mrkScaKnOwinZqEqRf+QqjayP6lY4/qzMNiYuHG8L80vCDtaCe\n" + 
        		"s6YxCBnOW8Z/2yNBWiLu2hlWFuBRtKt4T+mgSlwdwzosWzwRF7/gbHahQD4VZJEV\n" + 
        		"SrnVjgqTkplgfLoUXfZJw+GK7XYC8wcKXwxCGOKV0jFF2ruc9UEjGNxuV7G04A4D\n" + 
        		"jQaRRYNb2K3QQPnt0Y0jXPhBKJF/zwke3kEBeMK1Z1Uso1UKccn/aX8oWrIXxtxE\n" + 
        		"z4JpHC/bAgMBAAECggEBAL9Edlx6q2JKEKOXXS/U78EpoumkAvOVANG1VyxqS4UP\n" + 
        		"sMm01NS5VFOpkEzVhkT8H4uuJYug1Ur3B7rIJBF+VhlJxloa39ZjDXRKXrM5zUuL\n" + 
        		"+mAPDSYh/eQx0O/bmEgV+dzCEPaAmaPAFoF6GvUhsGcQSxipDeIdSurVPyrazjPR\n" + 
        		"CGj5/u1DUq/xZE8C+BPNMWjsq9AGd2O5y6QydYZcEWeMFLK3qzlHLJr7gIOqE+FF\n" + 
        		"WpYVKkl4XqoZChT0WbaVIW8VXAb65huNRKR774Poc22pXEsBGX6i311VnUp/8IYj\n" + 
        		"HufDhLo9jca2SlkjvLLLL1B05c8V5EZdckkKWqsoCVECgYEA8zp1i+bXRTLasrYU\n" + 
        		"tl8pY9KAzTP/OXdOBiOXAN7EV+4JQfg6Ya3ttC9WpJfqj+max2mM/rmIZfz5GdZf\n" + 
        		"s/BF/6COPdsOnDMJg8oMfKvEJfJkCWdfP5U41EDWFjeaVS8g1YIIl3Rl2ztQY9Uj\n" + 
        		"cJvLeEnKK2N3PWK1N1nfnlITsnUCgYEA8I06gpVW+NfKFfZgenrWhWZJlucFew19\n" + 
        		"OVEiuD3LJVmzEZqhXm+FE4nS0Sso84t2TwPqRqvixrBH4/rOjvmaWq+gfvu7qNfP\n" + 
        		"IHnuYIE8XKoiwPlKj2oiwL6Byta4M7eQ0Zt12kEL4lAzvw46Glc4A3temtaZTMBW\n" + 
        		"fQ3URPbEbw8CgYAlzLsB4AEqjumwBl4XrZhYHBnKSj9q5eCpUZShHP20kJi9uN8R\n" + 
        		"2R7s9sJnV/irHunf/cZcu+a3uuXXnxpGc5vdS01vzYBqw//BjH/+TA9yukXTtE34\n" + 
        		"Kq+nROBAmamRfX6Dz+6X4w4FUSWALlqQoJWxfv49Wfro/lMdZAk9qJHJFQKBgHVQ\n" + 
        		"y0pIPwRZaUtQvzi8k8a3XghkXv3mVD4R3dbw/BQZLQXW3gBKbyJaQyCT5gLw7yAW\n" + 
        		"DrmstoqkCkubVAXSoX68zI9hgeIUbgWusZ0LKj8fEpf+fGh5IL3Tq0UbdvZx/gTb\n" + 
        		"xpxMwNj8uF/jNl8GTmEUV803/8e4vfV7OxzvJFntAoGBANf5idKGaVpVvXFcvdZL\n" + 
        		"en7lIUMt305Uys+x/wDWfJ1rqX8kFfJcEOwtaMlkB53aJXBz1yyy5z7B2esud9tj\n" + 
        		"paQ6nVwcn27tpDPn++2AaNhO3sFJsSXlZOZwIx0nWrET8WiQfwmuOV5ywJFFJaoF\n" + 
        		"GU4/q84XoSR2KjYOAlVrEt3A\n" + 
        		"-----END PRIVATE KEY-----";
		
		Charge charge = null;
		String channel = "alipay_pc_direct";//支付宝电脑端支付
		Map<String, Object> chargeMap = new HashMap<String, Object>();
		chargeMap.put("amount", orderMoney);// 订单总金额, 人民币单位：分（如订单总金额为 1 元，此处请填 100）
		chargeMap.put("currency", "cny");//人民币交易
		chargeMap.put("subject", "ping_demo");
		chargeMap.put("body", "口红");//商品描述信息
		String orderNo = "no10000002";
		chargeMap.put("order_no", orderNo);// 推荐使用 8-20 位，要求数字或字母，不允许其他字符
		chargeMap.put("channel", channel);// 支付使用的第三方支付渠道取值，请参考：https://www.pingxx.com/api#api-c-new
		chargeMap.put("client_ip", "127.0.0.1"); // 发起支付请求客户端的 IP 地址，格式为 IPV4，如: 127.0.0.1
		Map<String, String> app = new HashMap<String, String>();
		app.put("id", appId);
		chargeMap.put("app", app);
		Map<String,String> successUrl = new HashMap<>();
		successUrl.put("success_url", "http://127.0.0.1:8080/success.jsp");//支付成功,回调的业面
		chargeMap.put("extra", successUrl);
		

		try {
			charge = Charge.create(chargeMap);
			System.out.println(charge.toString());
			//3.返回支付charge对像到页面,使用js去访问alipay支付窗口
			req.getSession().setAttribute("charge", charge);
			resp.sendRedirect("pay.jsp");
		} catch (AuthenticationException | InvalidRequestException | APIConnectionException | APIException
				| ChannelException | RateLimitException e) {
			e.printStackTrace();
		}

	}

}
