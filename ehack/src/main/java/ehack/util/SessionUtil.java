package ehack.util;

import javax.servlet.http.HttpSession;

public class SessionUtil {
	HttpSession session;
	
	SessionUtil(HttpSession session) {
		this.session = session;
	}
	
	public String getAccessToekn() {
		return (String)session.getAttribute("user_token");
	}
	
	public String getReAccessToken() {
		return (String)session.getAttribute("refresh_token");
	}
	
	public String getMuuid() {
		return (String)session.getAttribute("muuid");
	}
	
	public void setAccessToekn(String token) {
		session.setAttribute("user_token", token);
	}
	
	public void setReAccessToken(String token) {
		session.setAttribute("refresh_token", token);
	}
	
	public void setMuuid(String muuid) {
		session.setAttribute("muuid", muuid);
	}
}
