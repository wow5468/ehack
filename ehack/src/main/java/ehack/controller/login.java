package ehack.controller;

import javax.servlet.http.HttpSession;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class login {
	
	@RequestMapping("/login.do")
	public String showLoginRslt(HttpSession session
								, @RequestParam("code") String strCode
								, @RequestParam("error") String strError) {
		if(strError!=null) {
			return "error";
		} else {
			
			HttpClient httpclient = new DefaultHttpClient();
			
			session.setAttribute("accesstoken_ser", "fsfasfasfas");

			return "success";
		}
	}
}
