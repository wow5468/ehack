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
@Table(name = "data", catalog = "energy_scheduler")
public class DataEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "muuid",  unique = true, nullable = false)
	private String muuid;
	
	@Column(name = "duuid")
	private String duuid;
	
	@Column(name = "pid")
	private int pid;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "count")
	private int count;

	@Column(name = "rusage")
	private int rusage;
	
	@Column(name = "pname")
	private String pname;
	
	@Column(name = "img")
	private String img;

	


}