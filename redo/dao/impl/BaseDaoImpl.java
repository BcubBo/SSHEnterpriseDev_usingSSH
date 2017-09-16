package dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import dao.BaseDao;
//仍然是类型模糊的实现类
//
public class BaseDaoImpl<T> implements BaseDao<T> {
	private HibernateTemplate hibernateTemplate;
	public Logger logger = (Logger)LogManager.getLogger();
/*	static {
		System.out.println("HibernateTemplate模板是否存在:"+hibernateTemplate.getClass());
	}*/

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
	public int delete(final Class<T> clz, final Object[] ids) {
		//通过静态内部类的方式进行回调函数的书写
		return this.getHibernateTemplate().execute(
				new HibernateCallback<Integer>() {
					public Integer doInHibernate(Session arg0)throws HibernateException,SQLException {
						
						String hql = "delete from "+clz.getSimpleName()+" where id in (:ids)";
						return arg0.createQuery(hql).setParameterList("ids",ids).executeUpdate();
						//
						
						
						
						
						
					}
				}
			);
	}
	//另外一种不使用静态内部类的方式进行HibernateTemplate模板类的回调函数的书写
/*	class test implements HibernateCallback<Integer>{

		public Integer doInHibernate(Session arg0)throws HibernateException,SQLException{
			
			return null;
			
			
		}
	}*/

	@Override
	public T findById(Class<T> clz, Serializable id) {
		return this.getHibernateTemplate().get(clz, id);
		//通过id获取查询数据
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findForPage(DetachedCriteria criteria, int pageNo, int pageSize) {
		return this.getHibernateTemplate().findByCriteria(criteria,((pageNo-1)*pageSize),pageSize);
		//通过QBC进行查询
	}

	@Override
	public Integer getTotalCount(DetachedCriteria criteria) {
		logger.error("hibernateTemplate是否存在:"+this.getHibernateTemplate().getClass());
		//取其第一条即可,获取所有的统计条数
		return ((Long)this.getHibernateTemplate().findByCriteria(criteria).get(0)).intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(Class<T> clz) {
		
		return this.getHibernateTemplate().find("from "+clz.getSimpleName());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByExample(T entity) {
		
		return this.getHibernateTemplate().findByExample(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(String hql, Object... params) {
		
		return this.getHibernateTemplate().find(hql,params);
		//动态传参
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	
	

}
