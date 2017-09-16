package service;

import java.util.Date;
import java.util.List;

import entity.ClaimVoucher;

public interface ClaimVoucherBiz {
	
	public List<ClaimVoucher> findForPage(String createSN,String nextDealSN,String status,Date startDate, Date endDate,int pageNo,int pageSize);
	//根据三个条件查询
	
	

}
