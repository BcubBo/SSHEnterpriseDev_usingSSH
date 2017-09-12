package dao.impl;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import dao.EmployeeDao;
import po.SysEmployee;
@Repository//作为bean载入
public class EmployeeDaoImpl extends HibernateDaoSupport implements EmployeeDao {
	@Autowired//自编写set方法进行sessionFactory的注入
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}	
	@Override
	public SysEmployee findById(Serializable sn) {
		
		return this.getHibernateTemplate().get(SysEmployee.class, sn);
		//模板类hibernate的内嵌的模板类
		
	}

}
