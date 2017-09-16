package biz.impl;

import java.util.Date;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import biz.ClaimVoucherBiz;
import dao.ClaimVoucherDao;
import entity.ClaimVoucher;
import util.PaginationSupport;

public class ClaimVoucherBizImpl implements ClaimVoucherBiz {
	private ClaimVoucherDao claimVoucherDao;
	//dao基本需要，由spring注入
	
	

	@Override
	public PaginationSupport<ClaimVoucher> findForPage(String createSN, String nextDealSN, String status,
			Date startDate, Date endDate, int pageNo, int pageSize) {
		
		PaginationSupport<ClaimVoucher> result = new PaginationSupport<ClaimVoucher>();
		if(pageNo>0) {
			result.setCurrPageNo(pageNo);
		}
		if(pageSize>0) {
			result.setPageSize(pageSize);
		}
		//拼凑条件
		DetachedCriteria criteria = DetachedCriteria.forClass(ClaimVoucher.class);
		if(createSN != null && createSN.length()!=0) {
			criteria.add(Restrictions.eq("creator.sn", createSN));
		}
		if(nextDealSN!=null && nextDealSN.length()!=0) {
			criteria.add(Restrictions.eq("nextDeal.sn",nextDealSN));
		}
		if(status!=null && status.length()!=0) {
			criteria.add(Restrictions.eq("status",status));
		}
		if(startDate !=null) {
			criteria.add(Restrictions.gt("createTime",startDate));
			
		}
		if(endDate !=null) {
			criteria.add(Restrictions.le("createTime",endDate));
		}
		//执行
		
		
		//1，查询符合条件的记录总数
		
		
		//2，分页查询
		
		
		return null;
	}

	public ClaimVoucherDao getClaimVoucherDao() {
		return claimVoucherDao;
	}

	public void setClaimVoucherDao(ClaimVoucherDao claimVoucherDao) {
		this.claimVoucherDao = claimVoucherDao;
	}
	
}
