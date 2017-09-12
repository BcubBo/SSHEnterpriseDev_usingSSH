package po;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * SysDepartment entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_DEPARTMENT",schema="BCUBBO3")
public class SysDepartment implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private Set<SysEmployee> sysEmployees = new HashSet<SysEmployee>(0);

	// Constructors

	/** default constructor */
	public SysDepartment() {
	}

	/** minimal constructor */
	public SysDepartment(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	/** full constructor */
	public SysDepartment(Long id, String name, Set<SysEmployee> sysEmployees) {
		this.id = id;
		this.name = name;
		this.sysEmployees = sysEmployees;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dept")
	@SequenceGenerator(name = "dept", sequenceName = "HIBERNATE_SEQUENCE", allocationSize = 1)
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "NAME", length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysDepartment")
	public Set<SysEmployee> getSysEmployees() {
		return this.sysEmployees;
	}

	public void setSysEmployees(Set<SysEmployee> sysEmployees) {
		this.sysEmployees = sysEmployees;
	}

}