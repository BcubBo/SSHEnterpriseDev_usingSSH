package dao;

import entity.CheckResult;
import entity.ClaimVoucher;

public interface CheckResultDao extends BaseDao<CheckResult> {
	
	public int deleteByClaimVoucher(ClaimVoucher claimVoucher);

}
