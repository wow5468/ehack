package ehack.archive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

	@Autowired
	private DataService dataService;
	
	
}
