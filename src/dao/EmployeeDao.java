package dao;

import po.SysEmployee;

public interface EmployeeDao {
	
	public SysEmployee findById(java.io.Serializable sn);

}
