package com.order.zalopay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.order.request.ReceiptRequest;
import com.order.response.DataZaloPayCreateResponse;
import com.order.service.ReceiptService;
import com.order.service.impl.ReceiptServiceImpl;

@SuppressWarnings("serial")
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/zalo-pay")
public class ZaloPayController {

	@Autowired
	ReceiptService receiptService;

	@SuppressWarnings("serial")
	@RequestMapping(value = "/get-code", method = RequestMethod.POST)
	public ResponseEntity<?> getCodeZaloPay(
			@RequestParam(name = "title", required = false, defaultValue = "Thanh Toan") String title,
			@RequestParam(name = "appUser", required = false, defaultValue = "Duc handsome") String appUsers,
			@RequestParam(name = "description", required = false, defaultValue = "Order") String description,
			@RequestBody ReceiptRequest wrraper) throws Exception {
		Integer vnp_Amounti = wrraper.getTotalPrice();
		String callbackUrl = "http://192.168.1.3:8082/api/zalo-pay/submit?&employeeId=" + wrraper.getEmployee()
				+ "&customerId=" + wrraper.getCustomer() + "&typePayment=" + wrraper.getTypePayment() + "&typeService="
				+ wrraper.getTypeService() + "&receiptDetail=" + wrraper.getReceiptDetail()+ "&tableId=" + wrraper.getTableId()
				+ "&customerPay=" + wrraper.getCustomerPay()+ "&voucherName=" + wrraper.getVoucherName()+ "&discountPrice=" + wrraper.getDiscountPrice();
		Map<String, Object> order = new HashMap<String, Object>() {
			{
				String codeTranId = ZaloPayConfig.getAppTransId();
				String timeNow = ZaloPayConfig.getAppTime().toString();
				put("app_id", ZaloPayConfig.appId.toString());
				put("app_user", appUsers);
				put("app_trans_id", codeTranId);
				put("app_time", timeNow);
				put("amount", vnp_Amounti.toString());
				put("order_type", "GOODS");
				put("title", title);
				put("description", description);
				put("callback_url", callbackUrl);
				put("item", ZaloPayConfig.getItem());
				put("embed_data", ZaloPayConfig.getEmbedData(callbackUrl));
				put("mac", ZaloPayConfig.getMac(timeNow, codeTranId, appUsers, vnp_Amounti.toString(), callbackUrl));
				put("currency", "VND");
				put("bank_code", "CC");
			}
		};
		String query = ZaloPayConfig.generateQueryUrl(order);
		//https://sandbox.zalopay.com.vn/v001/tpe/createorder
		//https://sb-openapi.zalopay.vn/v2/create
		URIBuilder uri = new URIBuilder("https://sb-openapi.zalopay.vn/v2/create?" + query);

		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(uri.build());

		CloseableHttpResponse res = client.execute(post);
		BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
		StringBuilder resultJsonStr = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			resultJsonStr.append(line);
		}
		JSONObject result = new JSONObject(resultJsonStr.toString());

		return ResponseEntity.ok(new Gson().fromJson(result.toString(), DataZaloPayCreateResponse.class));
	}

	@RequestMapping(value = "/submit", method = RequestMethod.GET)
	public String post(@RequestParam(name = "employeeId", required = false, defaultValue = "null") Integer employeeId,
			@RequestParam(name = "customerId", required = false, defaultValue = "null") Integer customerId,
			@RequestParam(name = "typePayment", required = false, defaultValue = "null") Integer typePayment,
			@RequestParam(name = "typeService", required = false, defaultValue = "null") Integer typeService,
			@RequestParam(name = "receiptDetail", required = false, defaultValue = "null") String receiptDetail,
			@RequestParam(name = "tableId", required = false, defaultValue = "null") Integer tableId,
			@RequestParam(name = "customerPay", required = false, defaultValue = "null") Integer customerPay,
			@RequestParam(name = "voucherName", required = false, defaultValue = "null") String voucherName,
			@RequestParam(name = "discountPrice", required = false, defaultValue = "null") Integer discountPrice,
			@RequestParam(name = "amount", required = false, defaultValue = "null") String amount,
			@RequestParam(name = "appid", required = false, defaultValue = "null") String appid,
			@RequestParam(name = "checksum", required = false, defaultValue = "null") String checksum,
			@RequestParam(name = "apptransid", required = false, defaultValue = "null") String apptransid,
			@RequestParam(name = "pmcid", required = false, defaultValue = "null") String pmcid,
			@RequestParam(name = "bankcode", required = false, defaultValue = "null") String bankcode,
			@RequestParam(name = "status", required = false, defaultValue = "01") Integer statusCode)
			throws UnsupportedEncodingException {
		ReceiptRequest reciept = new ReceiptRequest();
		reciept.setCustomer(customerId);
		reciept.setEmployee(employeeId);
		reciept.setReceiptDetail(receiptDetail);
		reciept.setTotalPrice(Integer.parseInt(amount));
		reciept.setTable(tableId);
		reciept.setTypePayment(typePayment);
		reciept.setTypeService(typeService);
		reciept.setCustomerPay(customerPay);
		reciept.setVoucherName(voucherName);
		reciept.setDiscountPrice(discountPrice);
		
		boolean addReceiptCheck = receiptService.addReceipt(reciept);
		
		String button = "<a href=\r\n" + "????y l?? link front end khi b???m v??o button n??y" + "success/" + "hihi" + ">\r\n"
				+ "<button style=\"vertical-align:middle;position: relative;display: inline-block;\r\n"
				+ "  border-radius: 4px;\r\n" + "  background-color: #f4511e;\r\n" + "  border: none;\r\n"
				+ "  color: #FFFFFF;\r\n" + "  text-align: center;\r\n" + "  font-size: 22px;\r\n"
				+ "  padding: 20px;\r\n" + "  width: 35%;\r\n" + "  transition: all 0.5s;\r\n"
				+ "  cursor: pointer;\r\n" + "  margin-left: 30%;\">" + "OK" + "</button>" + "</a>";
		String responses = "<div><h1 style=\" text-align: center	\">THANH TO??N TH??NH C??NG</h1></div>" + button;
		if (!addReceiptCheck) {
			responses = "<div><h1 style=\" text-align: center;	\">THANH TO??N TH???T B???I</h1></div>" + button;
		} else {
			responses = "<div><h1 style=\" text-align: center;	\">THANH TO??N TH??NH C??NG</h1></div>" + button;
		}
		return responses;
	}

	
//	@RequestMapping(value = "/refund", method = RequestMethod.GET)
//	public ResponseEntity<BaseResponse> refund(
//			@RequestParam(name = "app_trans_id", required = false, defaultValue = "Dinh kute") String appTransId)
//			throws Exception {
//		BaseResponse response = new BaseResponse();
//
//		Map<String, Object> order = new HashMap<String, Object>() {
//			{
//				put("app_id", ZaloPayConfig.appId);
//				put("app_trans_id", appTransId);
//				put("mac", ZaloPayConfig.getMacForQuery(appTransId));
//
//			}
//		};
//
//		List<NameValuePair> params = new ArrayList<>();
//		params.add(new BasicNameValuePair("app_id", ZaloPayConfig.appId.toString()));
//		params.add(new BasicNameValuePair("app_trans_id", appTransId));
//		params.add(new BasicNameValuePair("mac", ZaloPayConfig.getMacForQuery(appTransId)));
//
//		URIBuilder uri = new URIBuilder("https://sb-openapi.zalopay.vn/v2/query");
//		uri.addParameters(params);
//
//		CloseableHttpClient client = HttpClients.createDefault();
//		HttpPost post = new HttpPost(uri.build());
//
//		// Content-Type: application/x-www-form-urlencoded
//		post.setEntity(new UrlEncodedFormEntity(params));
//
//		CloseableHttpResponse res = client.execute(post);
//		BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
//		StringBuilder resultJsonStr = new StringBuilder();
//		String line;
//
//		while ((line = rd.readLine()) != null) {
//			resultJsonStr.append(line);
//		}
//		JSONObject result = new JSONObject(resultJsonStr.toString());
//		DataRefundResponse data = new Gson().fromJson(result.toString(), DataRefundResponse.class);
//		response.setData(data);
//		return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
//	}

}
