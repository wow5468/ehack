package ehack.controller;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ehack.data.DataEntity;
import ehack.data.DataRepository;
import ehack.util.SessionUtil;
import ehack.util.TransUtil;


@RestController
public class plug {
	
	@Autowired
	private DataRepository dataRepository;
	
	@RequestMapping("/devices/{id}/relay")
	public @ResponseBody Map<String,Object> setRelayStatus(HttpSession session
									, @PathVariable String strId
									, @RequestParam(name="mode") String mode) {
		Map<String, Object> rsltData = new HashMap<String, Object>();
		TransUtil tu = new TransUtil();
		SessionUtil su = new SessionUtil(session);
		
		Map<String, Object> reponseData = tu.getData(session, "/devices/"+strId+"/relay?mode="+mode);
		
		DataEntity de = dataRepository.findByMuuidAndDuuid(su.getMuuid(), strId);
		de.setStatus((String)rsltData.get("status"));

		rsltData.put("response", reponseData);
		rsltData.put("updateCount", dataRepository.save(de));
		return rsltData;
	}

}
