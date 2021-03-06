package ehack.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TransUtil {
	
	
	public Map<String, Object> getData(HttpSession session, String strUrl) {
		Map<String,Object> map = new HashMap<String,Object>();
		SessionUtil su = new SessionUtil(session);
		
		String deviceListUrl = "https://api.encoredtech.com/1.2/"+strUrl;
		String strResponse = "";
		
		try{
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(deviceListUrl);
			
			request.addHeader("Authorization", "Bearer "+su.getAccessToekn());
			
			HttpResponse response = client.execute(request);
			org.apache.http.HttpEntity entity = response.getEntity();
			
			if(entity != null)
			{
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						response.getEntity().getContent()));
				
				String line = "";
				while((line = rd.readLine()) !=null)
				{
					System.out.println(line);
					strResponse+=line;
					
				}
			}
			request.abort();
			client.getConnectionManager().shutdown();
			ObjectMapper mapper = new ObjectMapper();
			map = mapper.readValue(strResponse, new TypeReference<Map<String, Object>>(){});
			
			System.out.println(map);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return map;
	}
	
	public List<Map<String, Object>> getApiListData(String usertoken) {
		
		String deviceListUrl = "https://api.encoredtech.com/1.2/devices/list";
		String strResponse = "";
		Map<String,Object> map = new HashMap<String,Object>();
		List<Map<String, Object>> listData = null;
		
		try{
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(deviceListUrl);
			
			request.addHeader("Authorization","Bearer "+usertoken);
			
			HttpResponse response = client.execute(request);
			org.apache.http.HttpEntity entity = response.getEntity();
			
			if(entity != null)
			{
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						response.getEntity().getContent()));
				
				String line = "";
				while((line = rd.readLine()) !=null)
				{
					System.out.println(line);
					strResponse+=line;
					
				}
			}
			System.out.println("strResponse::"+strResponse);
			request.abort();
			client.getConnectionManager().shutdown();
			ObjectMapper mapper = new ObjectMapper();
			listData = mapper.readValue(strResponse, new TypeReference<List<Object>>(){});
			
			System.out.println("listData::"+listData);
		}
		catch(Exception e)
		{
			
		}
		return listData;
				
	}
}
