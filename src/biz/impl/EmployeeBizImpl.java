package biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.EmployeeBiz;
import dao.EmployeeDao;
import po.SysEmployee;

@Service
@Transactional//所有的公共类都会添加事务属性为默认值
public class EmployeeBizImpl implements EmployeeBiz {
	
	private EmployeeDao employeeDao;

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)//默认不使用事务，但是如果有也不拒绝。把最顶层的配置给覆盖掉了
	public SysEmployee checkLogin(SysEmployee emp) {
		SysEmployee result = employeeDao.findById(emp.getSn());
		if(result!=null && result.getPassword().equals(emp.getPassword())) {
			return result;
		}else {
			
			
			return null;
			
		}
		
		
	}

	
	//setter 和 getter获取位置
	@Autowired
	public void setEmployeeDao(EmployeeDao employeeDao) {
		this.employeeDao = employeeDao;
	}//employeeDao获取

}
