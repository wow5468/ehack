package ehack.data;

import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import ehack.util.TransUtil;
import lombok.extern.java.Log;

@RestController
public class DataController {

	@Autowired
	private DataService dataService;
	private String deviceListUrl = "https://api.encoredtech.com/1.2/devices/list";
	
	@RequestMapping(value ="/DeviceList " ,method = RequestMethod.GET)
	public ModelAndView getListInformation(HttpSession session, String strApiUrl, java.util.Map<String, Object> mapReqData ) {
/*
		TransUtil tu = new TransUtil();
		java.util.Map<String, Object> mapRsltData = tu.getApiListData(session, "/devices/list",mapReqData.put("deviceId", "fasdfsd"));
		
		
		*/
		return null;
	}
	
}
