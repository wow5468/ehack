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
		
		//plugin api로 등록된 기기들 가져온다.
		TransUtil tu = new TransUtil();
		Map<String, Object> mapRsltData = tu.getApiListData(userToken);
		
		//DB에 저장되어 있는 기기들 가져온다.
		map= (Map<String, Object>) dataRepository.findAll();
		
		//등록된 기기와 DB에 저장된 기기를 비교 
	    
	 
		
		//비교값을 리턴
		
		
		return 
	}
	
}
