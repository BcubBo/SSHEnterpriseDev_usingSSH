package dao;

import java.util.List;

import entity.Employee;

public interface EmployeeDao extends BaseDao<Employee>{
	
	public List<Employee> findEmployee(Employee emp);
	
	public Employee findEmployeeBySn(String sn);
	public Employee getManager(Employee employee);
	
	public Employee getGeneralManager();
	
	public Employee getCashier();
	public List<Employee> findByPosition(String position);
	//通过职位查管理者
	public void saveOrUpdate(Employee employee);
	
	

}
