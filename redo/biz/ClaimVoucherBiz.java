package biz;

import java.io.Serializable;
import java.util.Date;

import entity.ClaimVoucher;
import util.PaginationSupport;

public interface ClaimVoucherBiz {
	
	public PaginationSupport<ClaimVoucher> findForPage(String createSN,String nextDealSN,String status,Date startDate, Date endDate,int pageNo,int pageSize);
	//根据三个条件查询
	//返回PaginationSupport对象包含些应有的数据对象
	public void addNewClaimVoucher(ClaimVoucher claimVoucher);
	//添加新的报销单
	//通过id查找报销单
	public ClaimVoucher findById(Serializable id);
	

}
