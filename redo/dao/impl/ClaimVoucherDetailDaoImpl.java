package dao.impl;

import dao.ClaimVoucherDetailDao;
import entity.ClaimVoucher;
import entity.ClaimVoucherDetail;

public class ClaimVoucherDetailDaoImpl extends BaseDaoImpl<ClaimVoucherDetail> implements ClaimVoucherDetailDao {

	@Override
	public int deleteByClaimVoucher(ClaimVoucher claimVoucher) {
		
		String hql = "delete from ClaimVoucherDetail where bizClaimVoucher.id = " + claimVoucher.getId();
		//hql删除操作
		this.getHibernateTemplate().bulkUpdate(hql);
		//调用此方法进行更新操作
		
		return 0;
	}

}
