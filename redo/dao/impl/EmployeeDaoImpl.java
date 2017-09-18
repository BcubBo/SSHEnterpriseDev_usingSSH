package dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import common.Constants;
import dao.EmployeeDao;
import entity.Employee;

public class EmployeeDaoImpl extends BaseHibernateDaoSupport<Employee> implements EmployeeDao{
	
	private static Employee empGM = null;
	private static Employee empCashier = null;

	@Override
	public List<Employee> findEmployee(Employee emp) {
		// TODO Auto-generated method stub
		@SuppressWarnings("unchecked")
		List<Employee> list = getHibernateTemplate().findByExample(emp);
        return list;
	}
	
	@Override
	public Employee findEmployeeBySn(String sn) {
		// TODO Auto-generated method stub
		return this.get(sn);
	}
	@Override
	public Employee getManager(Employee employee) {
		// TODO Auto-generated method stub
		String hql = "from Employee e where e.sysDepartment.id=? and e.sysPosition.nameCn=?";
		Employee empManager = (Employee)findFirst(hql, employee.getSysDepartment().getId(),Constants.POSITION_FM);
		return empManager;
	}

	@Override
	public Employee getGeneralManager() {
		// TODO Auto-generated method stub
		if (empGM == null){
			String hql = "from Employee e where e.sysPosition.nameCn=?";
			empGM = (Employee)findFirst(hql,Constants.POSITION_GM);
		}
		return empGM;
	}

	@Override
	public Employee getCashier() {
		// TODO Auto-generated method stub
		if (empCashier == null) {
			String hql = "from Employee e where e.sysPosition.nameCn=? and e.status='"
					+ Constants.EMPLOYEE_STAY + "'";
			empCashier = (Employee) findFirst(hql, Constants.POSITION_CASHIER);
		}
		return empCashier;
	}

	@Override
	public void saveOrUpate(Employee entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Employee merge(Employee entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Collection<Employee> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int delete(Object[] ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Employee findById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findForPage(DetachedCriteria criteria, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getTotalCount(DetachedCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> findByExample(Employee entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> findByPosition(String position) {
		//通过职位查找管理
		String hql = "from Employee where status ='在职' and sysPosition.nameCn = ?";
		
		return this.getHibernateTemplate().find(hql,position);
		//查询职位信息
	}

	

}
