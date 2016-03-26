package ehack.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ehack.util.JsonUtil;
import ehack.util.RefineData;
import ehack.util.TransUtil;

@RestController
public class DataController {

	@Autowired
	private DataService dataService;
	@Autowired
	private DataRepository dataRepository;
	
	private RefineData refineData;

	
	private String deviceListUrl = "https://api.encoredtech.com/1.2/devices/list";
	private String userToken;
	
	
	//랭크 페이지에 프로덕트 데이타 전체 보내기
	@RequestMapping(value="/Rank", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getListInformation()
	{
		List<DataEntity> dataAll = dataRepository.findAll();
		return null;
		
	
	}
	
	@RequestMapping(value ="/DeviceList" ,method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getListInformation(HttpSession session) {
		String strMuuid = (String)session.getAttribute("muuid");
		userToken = (String) session.getAttribute("user_token");
		
		Map<String, Object> map = new HashMap<String, Object>();
		TransUtil tu = new TransUtil();
		List<Object> mapRslData = null;

		//from api
		List<Map<String, Object>> mapRsltData = tu.getApiListData(userToken);
		
		//from db
		List<DataEntity> a = dataRepository.findByMuuid(strMuuid);
		List<RefineData> rdata=new ArrayList<RefineData>();
		System.out.println("mapRsltData::"+mapRsltData);
		

		for(int i=0; i<mapRsltData.size(); i++) {
			boolean isConnected = false;
		  for(int j=0; j<a.size(); j++)
		  {
			//연결 되어 있는 경우
			if(a.get(j).getDuuid().equals(mapRsltData.get(i).get("uuid")))
			{
				isConnected = true;
			}
		  }
			mapRsltData.get(i).put("isConnected", isConnected);
		}
		  map.put("list", mapRsltData);

		  
		return JsonUtil.putSuccessJsonContainer(map);
	}
		

}