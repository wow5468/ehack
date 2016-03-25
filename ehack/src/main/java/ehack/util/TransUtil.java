package ehack.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TransUtil {
	
	public Map<String, Object> getApiListData(String usertoken) {
		
		String deviceListUrl = "https://api.encoredtech.com/1.2/devices/list";
		String strResponse = "";
		Map<String,Object> map = new HashMap<String,Object>();
		
		try{
			HttpClient client = new DefaultHttpClient();
			HttpGet request = new HttpGet(deviceListUrl);
			
			request.addHeader("Authorization",usertoken);
			
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
			
		}
		return map;
		
				
				
	}
}
