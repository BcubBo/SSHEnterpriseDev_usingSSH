package dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface BaseDao<T> {
	
	public void save(T entity);
	public void update(T entity);
	public void saveOrUpate(T entity);
	public T merge(T entity);
	
	
	public void delete(T entity);
	public void delete(Collection<T> entities);
	public int delete(Class<T> clz,Object[] ids);
	//传递参数告知删除的数据类型为什么
	//Class<T>
	
	public T findById(Class<T> clz,Serializable id);
	//
	
	public List<T> findForPage(DetachedCriteria criteria ,int pageNo,int pageSize);
	//分页查询
	public Integer getTotalCount(DetachedCriteria criteria);
	//查询统计
	
	public List<T> findAll(Class<T> clz);
	//小数据量的时候使用的方法，无条件的查询一个类型所有的数据
	
	public List<T> findByExample(T entity);
	//按照模板查询
	
	public List<T> find(String hql,Object...params);
	//最后保底方法
	
	
	
	
}
