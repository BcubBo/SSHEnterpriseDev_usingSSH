package service;

import entity.Employee;
import exception.JboaException;

public interface EmployeeService {
	
	public Employee getEmployeeBySN(String sn);
	
	public boolean saveEmployee(Employee employee);
	
	public Employee login(Employee emp)throws JboaException;
	
	public Employee getManager(Employee employee);
	
	public Employee getGeneralManager();
	
	public Employee getCashier();
	
	
}
