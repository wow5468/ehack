package ehack.data;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "data")
public class DataEntity {

	@Id
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
	
	@Column(name = "usage")
	private int usage;
	
	@Column(name = "pname")
	private String pname;
	
	@Column(name = "img")
	private String img;
	
	@Column(name = "start")
	private String start;
	
	@Column(name = "end")
	private String end;
	



}
