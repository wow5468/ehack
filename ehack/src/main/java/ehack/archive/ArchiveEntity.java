package ehack.archive;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;



@Data
@Entity
@Table(name = "archive", catalog = "nextershp")
public class ArchiveEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "no",  unique = true, nullable = false)
	private int no;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "desc")
	private String desc;
	
	@Column(name = "prizeYN")
	private String prizeYN;
}
