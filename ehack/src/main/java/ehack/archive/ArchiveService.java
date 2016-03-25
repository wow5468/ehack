package ehack.archive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArchiveService {

	@Autowired
	private ArchiveRepository archiveRepository;

}
