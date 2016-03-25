package ehack.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ehack.user.UserService;

@RestController
public class login {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/intro.do")
	public @ResponseBody String checkToken(HttpSession session
									, @RequestParam("muuid") String strUuid) {
		UserDto
		
		return null;
		
	}
	@RequestMapping("/login.do")
	public @ResponseBody String showLoginRslt(HttpSession session
								, @RequestParam("code") String strCode
								, @RequestParam(value="error",required=false) String strError) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(strError!=null) {
			return "error";
		} else {
			
			HttpClient httpclient = new DefaultHttpClient();
			try {
				String strResponse = "";
				HttpPost httpget = new HttpPost("https://enertalk-auth.encoredtech.com/token");

				//HttpGet httpget = new HttpGet("https://enertalk-auth.encoredtech.com/token");
				 
				httpget.addHeader("client_id", "d293NTQ2OEBuYXZlci5jb21fZWhhY2s=");
				httpget.addHeader("client_secret", "e91cu4xp5oz8bl9bb0ss0af0cu6s23sp97h3yh1");
				httpget.addHeader("code", strCode);
				httpget.addHeader("grant_type", "authorization_code");
				
				HttpResponse responseData = httpclient.execute(httpget);
				org.apache.http.HttpEntity entity = responseData.getEntity();
	 
				// 응답 결과
				if (entity != null) {
					BufferedReader rd = new BufferedReader(new InputStreamReader(
							responseData.getEntity().getContent()));
	 
					String line = "";
					while ((line = rd.readLine()) != null) {
						System.out.println(line);
						strResponse+=line;
					}
				}
				httpget.abort();
				httpclient.getConnectionManager().shutdown();
				
				ObjectMapper mapper = new ObjectMapper();
				map = mapper.readValue(strResponse, new TypeReference<Map<String, Object>>(){});
				
				System.out.println(map);
				
				session.setAttribute("user_token", (String)map.get("access_token"));
				session.setAttribute("refresh_token", (String)map.get("access_token"));

				
			} catch(Exception e){
				e.printStackTrace();
			}

			return "success";
		}
	}
}
