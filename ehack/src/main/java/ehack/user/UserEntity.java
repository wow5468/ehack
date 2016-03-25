package ehack.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "userp")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "muuid",  unique = true, nullable = false)
	private String muuid;

	@Column(name = "accesstoken")
	private String accesstoken;
	
	@Column(name = "reaccesstoken")
	private String reaccesstoken;


}