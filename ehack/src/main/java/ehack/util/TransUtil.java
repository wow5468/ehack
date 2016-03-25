package ehack.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class TransUtil {
	
	public Map<String, Object> getApiListData(HttpSession session, String strApiUrl, Map<String, Object> mapReqData) {
		
		
		
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(deviceListUrl);
		
		request.addHeader("Authorization",auth);
		
		HttpResponse response = client.execute(request);
		
		BufferedReader rd = new BufferReader(
				new InputStreamReader(response.getEntity().getContent());
				
				
	}
}
