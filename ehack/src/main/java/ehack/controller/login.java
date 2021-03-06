package ehack.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ehack.user.UserDto;
import ehack.user.UserEntity;
import ehack.user.UserRepository;
import ehack.util.JsonUtil;


@RestController
public class login {
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/intro.do")
	public @ResponseBody Map<String,Object> checkToken(HttpSession session
									, @RequestParam("muuid") String strUuid) {
		Map<String,Object> mapRsltData = new HashMap<String, Object>();
		
		UserEntity ud = userRepository.findByMuuid(strUuid);
		session.setAttribute("muuid", strUuid);
		if(ud==null || ud.getAccesstoken()==null) {
			mapRsltData.put("rlst","register");
			return JsonUtil.putFailJsonContainer("9999", "회원 가입이 필요합니다.");
		} else {
			//session.setAttribute("user_token", ud.getAccesstoken());
			//session.setAttribute("refresh_token", ud.getReaccesstoken());
			session.setAttribute("muuid", "test");
			session.setAttribute("user_token", "5caf168ed3d5ccab211b0ab5896862105f83d5a4b2e6c07ed8d0a465aee1cba2123a346fe9835c7a8f7ea644f364bbd5d329fce979cd50ac80960e3873c612a0");
			session.setAttribute("refresh_token", "10b5227432e9b87e10e241b078d2f0b0d4c3b58b5299ea8039e8e50b1646139cddf92ee8d8eef9a6b1614314dc6d5ddc299d769fc9ebad55982e0434d8592ec7");
			mapRsltData.put("rlst", "success");
			return JsonUtil.putSuccessJsonContainer(mapRsltData);
		}
	}
	
	@RequestMapping("/login.do")
	public @ResponseBody String showLoginRslt(HttpSession session
								, HttpServletResponse response
								, @RequestParam("code") String strCode
								, @RequestParam(value="error",required=false) String strError) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(strError!=null) {
			return "error";
		} else {
			Map<String, Object> reqData = new HashMap<String, Object>();
			HttpClient httpclient = new DefaultHttpClient();
			try {
				String strResponse = "";
				HttpPost httpget = new HttpPost("https://enertalk-auth.encoredtech.com/token");

				//HttpGet httpget = new HttpGet("https://enertalk-auth.encoredtech.com/token");
				httpget.addHeader("Content-Type", "application/json");
				reqData.put("client_id", "d293NTQ2OEBuYXZlci5jb21fZWhhY2s=");
				reqData.put("client_secret", "e91cu4xp5oz8bl9bb0ss0af0cu6s23sp97h3yh1");
				reqData.put("code", strCode);
				reqData.put("grant_type", "authorization_code");
				ObjectMapper mapperObj = new ObjectMapper();
				String jsonResp = mapperObj.writeValueAsString(reqData);
				System.out.println(jsonResp);
				StringEntity params =new StringEntity(jsonResp);
				
				httpget.setEntity(params);
				
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
				
				if(map.get("error")!=null) {
					session.setAttribute("user_token", (String)map.get("access_token"));
					session.setAttribute("refresh_token", (String)map.get("refresh_token"));

					
					//사용자 등록 시작
					UserEntity ud = new UserEntity();
					ud.setMuuid((String)session.getAttribute("muuid"));
					ud.setAccesstoken((String)map.get("access_token"));
					ud.setReaccesstoken((String)map.get("refresh_token"));
					userRepository.saveAndFlush(ud);
				}
				
				
			} catch(Exception e){
				e.printStackTrace();
			}

			return "success";
		}
	}
}
