package ehack.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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
	
	private RefineData refineData;

	
	private String deviceListUrl = "https://api.encoredtech.com/1.2/devices/list";
	private String userToken;
	
	@RequestMapping(value ="/DeviceList " ,method = RequestMethod.GET)

	public Model getListInformation(HttpSession session,Model model) {
		String strMuuid = (String)session.getAttribute("muuid");
		userToken = (String) session.getAttribute("user_token");
		
		Map<String, Object> map = new HashMap<String, Object>();
		TransUtil tu = new TransUtil();
		List<Object> mapRslData = null;

		//from api
		List<Map<String, Object>> mapRsltData = tu.getApiListData(userToken);
		
		
		//from db
		List<DataEntity> a = dataRepository.findByMuuid(strMuuid);
		List<RefineData> rdata=null;
		for(int i=0; i<a.size(); i++) {
			
		  for(int j=0; j<a.size(); j++)
		  {
			//연결 되어 있는 경우
			if(a.get(i).getDuuid().equals(mapRsltData.get(j).get("uuid")))
			{
				rdata.get(i).setDeviceUUID(a.get(j).getDuuid());
				rdata.get(i).setModelNum((String)mapRsltData.get(j).get("model"));
				rdata.get(i).setIsConnect("YES");
				rdata.get(i).setSerialNum((String)mapRsltData.get(j).get("serialNumber"));
				
			}//연결 안된 경우
			else
			{
				rdata.get(i).setDeviceUUID(a.get(i).getDuuid());
				rdata.get(i).setIsConnect("NO");
				rdata.get(i).setModelNum("NULL");
				rdata.get(i).setSerialNum("NULL");
			}
				
		  }
		}
		
		model.addAttribute("refineData",rdata);

		return model;
	}
	
}
