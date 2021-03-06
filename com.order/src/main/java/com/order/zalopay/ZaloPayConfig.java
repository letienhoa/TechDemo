package com.order.zalopay;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.TimeZone;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.order.response.DataRefundResponse;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ZaloPayConfig {
	
	public static Integer appId = 553;
	public static String appUser = "Duc20cm";
	public static String orderType = "GOODS";
	public static String description = "Thanh toan order";
	public static String bank_code = "";
	public static String currency = "VND";
	public static String key1 = "9phuAOYhan4urywHTh0ndEXiV3pKHr5Q";
	public static String key2 = "Iyz2habzyr7AG8SgvoBCbKwKi3UzlLi3";
	public static final Map embed_data = new HashMap(){{}};
	public static final Map[] item = {
        new HashMap(){{}}
    };
	
	public static String getCurrentTimeString(String format) {
		Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("GMT+7"));
		SimpleDateFormat fmt = new SimpleDateFormat(format);
		fmt.setCalendar(cal);
		return fmt.format(cal.getTimeInMillis()+1000);
	}

	public static String getMRefundId() {
		Random rand = new Random();
		int random_id = rand.nextInt(1000000);
		return getCurrentTimeString("yyMMdd") +appId.toString()+ random_id;
	}
	
public static String getAppTransId() {
		Random rand = new Random();
		int random_id = rand.nextInt(1000000);
		return getCurrentTimeString("yyMMdd") + random_id;
	}

	public static Long getAppTime() {
		return System.currentTimeMillis();
	}

	public static String getItem() {
		return  "["+new JSONObject(item).toString()+"]";
	}

	public static String getEmbedData(String callBackUrl) {
		embed_data.put("redirecturl", callBackUrl);
		return new JSONObject(embed_data).toString();
	}

	public static String getMac(String timeNow,String codeTranId, String appUser, String amount,String callBackUrl) {
		String data = getData(timeNow,codeTranId,appUser,amount,callBackUrl);
		return HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256, key1, data);

	}
	
	public static String getMacForQuery(String appTransid) {
		return HMACUtil.HMacHexStringEncode(HMACUtil.HMACSHA256,key1,appId+"|"+appTransid+"|"+key1);

	}

	//Not call live
	public static String getData(String timeNow,String codeTranId,String appUser, String amount,String callBackUrl) {

		@SuppressWarnings("serial")
		Map<String, Object> order = new HashMap<String, Object>() {
			{
				put("app_id", ZaloPayConfig.appId);
				put("app_user", appUser);
				put("app_trans_id", codeTranId);
				put("app_time",timeNow);
				put("amount", amount);
				put("item", ZaloPayConfig.getItem());
				put("embed_data", ZaloPayConfig.getEmbedData(callBackUrl));

			}
		};

		return order.get("app_id") + "|" + order.get("app_trans_id") + "|" + order.get("app_user") + "|"
				+ order.get("amount") + "|" + order.get("app_time") + "|" + order.get("embed_data") + "|"
				+ order.get("item");
	}
	
	@SuppressWarnings("unchecked")
	public static String generateQueryUrl(Map<String, Object> zlp_Params)
			throws UnsupportedEncodingException {
		@SuppressWarnings("rawtypes")
		List fieldNames = new ArrayList(zlp_Params.keySet());
		Collections.sort(fieldNames);
		StringBuilder hashData = new StringBuilder();
		StringBuilder query = new StringBuilder();
		@SuppressWarnings("rawtypes")
		Iterator itr = fieldNames.iterator();
		while (itr.hasNext()) {
			String fieldName = (String) itr.next();
			String fieldValue = zlp_Params.get(fieldName).toString();
			if ((fieldValue != null) && (fieldValue.length() > 0)) {
				// Build hash data
				hashData.append(fieldName);
				hashData.append('=');
				hashData.append(fieldValue);
				// Build query
				query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
				query.append('=');
				query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
				if (itr.hasNext()) {
					query.append('&');
					hashData.append('&');
				}
			}
		}
		String queryUrl = query.toString();
		return queryUrl;
	}
	
	public static DataRefundResponse getTransId(String appTransId ) throws Exception {
		Map<String, Object> order = new HashMap<String, Object>() {
			{
				put("app_id", ZaloPayConfig.appId);
				put("app_trans_id",appTransId);
				put("mac", ZaloPayConfig.getMacForQuery(appTransId));

			}
		};
		
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("app_id", ZaloPayConfig.appId.toString()));
        params.add(new BasicNameValuePair("app_trans_id", appTransId));
        params.add(new BasicNameValuePair("mac", ZaloPayConfig.getMacForQuery(appTransId)));

        URIBuilder uri = new URIBuilder("https://sb-openapi.zalopay.vn/v2/query");
        uri.addParameters(params);

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost(uri.build());

        // Content-Type: application/x-www-form-urlencoded
        post.setEntity(new UrlEncodedFormEntity(params));

        CloseableHttpResponse res = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
        StringBuilder resultJsonStr = new StringBuilder();
        String line;

        while ((line = rd.readLine()) != null) {
            resultJsonStr.append(line);
        }

        JSONObject result = new JSONObject(resultJsonStr.toString());
        DataRefundResponse data = new Gson().fromJson(result.toString(), DataRefundResponse.class);
        return data;
	}
}
