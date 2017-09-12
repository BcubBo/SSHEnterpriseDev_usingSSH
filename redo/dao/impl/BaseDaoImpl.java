package dao.impl;

import java.sql.SQLException;
import java.util.Collection;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import dao.BaseDao;
//仍然是类型模糊的实现类
//
public class BaseDaoImpl<T> implements BaseDao<T> {
	
	private HibernateTemplate hibernateTemplate;

	@Override
	public void save(T entity) {
		
		this.getHibernateTemplate().save(entity);
	}

	@Override
	public void update(T entity) {
		
		this.getHibernateTemplate().update(entity);
	}

	@Override
	public void saveOrUpate(T entity) {
		
		this.getHibernateTemplate().saveOrUpdate(entity);
	}

	@Override
	public T merge(T entity) {
		
		return this.getHibernateTemplate().merge(entity);
	}

	@Override
	public void delete(T entity) {
		
		this.getHibernateTemplate().delete(entity);
	}

	@Override
	public void delete(Collection<T> entities) {
		
		this.getHibernateTemplate().deleteAll(entities);
	}

	@Override
	public int delete(Class<T> clz, Object[] ids) {
		
		return this.getHibernateTemplate().execute(
				new HibernateCallback<Integer>() {
					public Integer doInHibernate(Session arg0)throws HibernateException,SQLException {
						return null;
						
					}
				}
			);
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	
	

}
