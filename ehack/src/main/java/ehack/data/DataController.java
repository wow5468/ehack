package ehack.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import ehack.util.RefineData;
import ehack.util.TransUtil;

@RestController
public class DataController {

	@Autowired
	private DataService dataService;
	@Autowired
	private DataRepository dataRepository;
	@Autowired
	private RefineData refineData;

	
	private String deviceListUrl = "https://api.encoredtech.com/1.2/devices/list";
	private String userToken;
	
	@RequestMapping(value ="/DeviceList " ,method = RequestMethod.GET)

	public ModelAndView getListInformation(HttpSession session) {

		userToken = (String) session.getAttribute("user_token");
		Map<String, Object> map = new HashMap<String, Object>();
		TransUtil tu = new TransUtil();

		Map<String, Object> mapRsltData = tu.getApiListData(userToken);
		
		map = (Map<String, Object>) dataRepository.findAll();

		return null;
	}
	
}
