package ehack.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "product", catalog = "energy_scheduler")
public class ProductEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "pid",  unique = true, nullable = false)
	private int pid;
	
	@Column(name = "category")
	private String category;
	
	@Column(name = "company")
	private String company;
}