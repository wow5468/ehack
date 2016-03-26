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
	
	@Id
	@Column(name = "duuid")
	private String duuid;
	
	@Id
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
	
	public class grandPK implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String muuid;
		private String duuid;
		private int pid;
		
		public grandPK() {
			
		}
		

		public grandPK(String muuid, String duuid, int pid) {
			this.muuid = muuid;
			this.duuid = duuid;
			this.pid = pid;
		}
		public String getMuuid() {
			return muuid;
		}


		public void setMuuid(String muuid) {
			this.muuid = muuid;
		}


		public String getDuuid() {
			return duuid;
		}


		public void setDuuid(String duuid) {
			this.duuid = duuid;
		}


		public int getPid() {
			return pid;
		}


		public void setPid(int pid) {
			this.pid = pid;
		}


		
		
		public boolean equals(Object obj) {
	        if (obj == this) return true;
	        if (!(obj instanceof grandPK)) return false;
	        if (obj == null) return false;
	        grandPK pk = (grandPK) obj;
	        return pk.muuid == muuid && pk.duuid.equals(duuid) && pk.pid == pid;
	    }
		
	}


}
