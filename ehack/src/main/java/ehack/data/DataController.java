package ehack.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
		Map<String, Object> rankData = new HashMap<String, Object>();
		List<DataEntity> dataAll = dataRepository.findOrderByRusageDesc();
		
		rankData.put("rankdata", dataAll);
		return JsonUtil.putSuccessJsonContainer(rankData);
	}
	
	//연결된 플러그 정보(시리얼, 모델 넘버)를 받고 나머지 기록하고 DB에 저장
	@RequestMapping(value="/insert", method = RequestMethod.POST)
	public void insert(@RequestBody DataEntity dd)
	{
		dd.setCount(0);
		dd.setUsage(0);
		dd.setRusage(0);
		dd.setStatus("OFF");
		dd.setImg(null);
		dataRepository.save(dd);
	}
	
	
	@RequestMapping(value ="/DeviceList" ,method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getListInformation(HttpSession session) {
		String strMuuid = (String)session.getAttribute("muuid");
		userToken = (String) session.getAttribute("user_token");
		
		Map<String, Object> map = new HashMap<String, Object>();
		TransUtil tu = new TransUtil();

		//from api
		List<Map<String, Object>> mapRsltData = tu.getApiListData(userToken);
		
		//from db
		List<DataEntity> a = dataRepository.findByMuuid(strMuuid);
		System.out.println("mapRsltData::"+mapRsltData);
		

		for(int i=0; i<mapRsltData.size(); i++) {
			boolean isConnected = false;
		  for(int j=0; j<a.size(); j++)
		  {
			//연결 되어 있는 경우
			if(a.get(j).getDuuid().equals(mapRsltData.get(i).get("uuid")))
			{
<<<<<<< HEAD
				rd.setDeviceUUID(a.get(j).getDuuid());
				rd.setModelNum((String)mapRsltData.get(j).get("model"));
				rd.setIsConnect("YES");
				rd.setSerialNum((String)mapRsltData.get(j).get("serialNumber"));
				break;
			}//연결 안된 경우
			else
			{
				rd.setDeviceUUID(a.get(i).getDuuid());
				rd.setIsConnect("NO");
				rd.setModelNum("NULL");
				rd.setSerialNum("NULL");
=======
				isConnected = true;
>>>>>>> branch 'master' of https://github.com/wow5468/ehack
			}
<<<<<<< HEAD
			rdata.add(rd);	
=======
>>>>>>> branch 'master' of https://github.com/wow5468/ehack
		  }
			mapRsltData.get(i).put("isConnected", isConnected);
		}
		  map.put("list", mapRsltData);

		  
		return JsonUtil.putSuccessJsonContainer(map);
	}
		

}