package actions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.opensymphony.xwork2.ActionContext;

import biz.EmployeeBiz;
import common.Constants;
import entity.Employee;
import exception.JboaException;
import po.SysEmployee;
import service.EmployeeService;

public class EmployeeAction extends BaseAction {
	
	static Logger logger = (Logger)LogManager.getLogger();
	private Employee employee;         // 用户类
    private EmployeeService empService; // 用户业务类
    private String random;
    
    private Employee manager;
    

	@SuppressWarnings("unchecked")
	public String login(){
		try {
			employee = empService.login(employee);
			manager = empService.getManager(employee);
			getSession().put(Constants.AUTH_EMPLOYEE, employee);
			getSession().put(Constants.AUTH_EMPLOYEE_MANAGER, manager);
			getSession().put(Constants.EMPLOYEE_POSITION, employee.getSysPosition().getNameCn());
			logger.debug("employee:" + employee.getSn() + "/"+employee.getPassword()+"/Logined.");
		} catch (JboaException ex) {
			addActionError(getText("errors.invalid.usernameorpassword"));
		} 
		if (hasActionErrors()){
			return INPUT;
		}
		return SUCCESS;
	}
	  
    public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public EmployeeService getEmpService() {
		return empService;
	}

	public void setEmpService(EmployeeService empService) {
		this.empService = empService;
	}

	public String getRandom() {
		return random;
	}

	public void setRandom(String random) {
		this.random = random;
	}

	public Employee getManager() {
		return manager;
	}

	public void setManager(Employee manager) {
		this.manager = manager;
	}	
}
