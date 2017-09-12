package dao;

import java.util.Collection;

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
	

}
