package dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import dao.ClaimVoucherDao;
import po.BizClaimVoucher;
@Repository
public class ClaimVoucherDaoImpl extends HibernateDaoSupport implements ClaimVoucherDao {
	@Autowired
	public void setSuperSessionFactory(SessionFactory sessionFactory) {
		
		super.setSessionFactory(sessionFactory);
		
	}
	
	
	
	@Override
	public void save(BizClaimVoucher claimVoucher) {
		
		this.getHibernateTemplate().save(claimVoucher);
		
	}
	//save方法
	
	
	public List<BizClaimVoucher> finByPage(final int pageNo,final int pageSize,final BizClaimVoucher args){
		//return this.getHibernateTemplate().findByCriteria(null,1,1);
		//模板和回调方法
		//return this.getHibernateTemplate().execute(new MyCallback());
		//使用回调
		//使用匿名内部类
		return this.getHibernateTemplate().execute(
				new HibernateCallback<List<BizClaimVoucher>>() {
					@SuppressWarnings("unchecked")
					public List<BizClaimVoucher> doInHibernate(Session arg0)throws HibernateException,SQLException{
						//匿名内部类
						return arg0.createQuery("from...")
						.setFirstResult((pageNo-1)*pageSize)
						.setMaxResults(pageSize)
						.list();
					}
				}
			);
		
		
	}
	

	
/*	public void show(int i ,String...values) {
		
		this.getHibernateTemplate().bulkUpdate("queryString ? ? ?",1,"a","b");
		this.getHibernateTemplate().clear();
		//清除对象，使对象处于脱机状态 
		this.getHibernateTemplate().delete(null);
		//删除对象
		this.getHibernateTemplate().deleteAll(null);
		//删除所有对象
		this.getHibernateTemplate().evict(null);
		//evacuate也是清除对象
		this.getHibernateTemplate().find("from ? ? ?",1,"bac",new Date());
		//查找方法
		this.getHibernateTemplate().findByCriteria(null,1,1);
		//通过Criteria查询
		this.getHibernateTemplate().findByExample(null);
		//通过简单对象查询，提取对象中的包含值的属性，但是不考虑主键和外键，而且条件运算符有限制。只能为==或者为like大于或者小于不适用
		this.getHibernateTemplate().findByNamedQuery(null);
		//通过命名查询
		this.getHibernateTemplate().findByNamedParam("from :name", new String[] {"1","2"}, null);
		//:name的形式的占位符，查询通过名称参数进行
		this.getHibernateTemplate().flush();
		//立即执行清理缓存的操作
	}*/

}
