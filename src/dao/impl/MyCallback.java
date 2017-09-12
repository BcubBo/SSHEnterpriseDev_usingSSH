package dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import po.BizClaimVoucher;

public class MyCallback implements HibernateCallback<List<BizClaimVoucher>> {
		
	@SuppressWarnings("unchecked")
	@Override
	public List<BizClaimVoucher> doInHibernate(Session arg0)throws HibernateException,SQLException{
		//要执行的代码
		Query q = arg0.createQuery("from ...");
		
		q.setFirstResult(10);//设置从第十条开始
		q.setMaxResults(10);//设置最大的返回条数
		
		return q.list();
		
	}
	
	
	
	

}
