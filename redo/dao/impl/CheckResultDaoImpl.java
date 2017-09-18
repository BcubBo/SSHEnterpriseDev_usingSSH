package dao.impl;

import dao.CheckResultDao;
import entity.CheckResult;
import entity.ClaimVoucher;

public class CheckResultDaoImpl extends BaseDaoImpl<CheckResult> implements CheckResultDao {

	@Override
	public int deleteByClaimVoucher(ClaimVoucher claimVoucher) {
		String hql = "delete from CheckResult where claimId = "+claimVoucher.getId();
		
		return this.getHibernateTemplate().bulkUpdate(hql);
	}
	
	
//通过id进行删除审查结果
}
