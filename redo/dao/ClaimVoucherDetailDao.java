package dao;

import entity.ClaimVoucher;
import entity.ClaimVoucherDetail;

public interface ClaimVoucherDetailDao extends BaseDao<ClaimVoucherDetail> {
	
	public int deleteByClaimVoucher(ClaimVoucher claimVoucher);
	//通过ClaimVoucher对象来进行删除操作
	
	

}
