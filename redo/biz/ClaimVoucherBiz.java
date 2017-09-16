package biz;

import java.util.Date;

import entity.ClaimVoucher;
import util.PaginationSupport;

public interface ClaimVoucherBiz {
	
	public PaginationSupport<ClaimVoucher> findForPage(String createSN,String nextDealSN,String status,Date startDate, Date endDate,int pageNo,int pageSize);
	//根据三个条件查询
	//返回PaginationSupport对象包含些应有的数据对象
	
	

}
