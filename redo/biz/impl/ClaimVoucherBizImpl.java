package biz.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
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
		criteria.setProjection(Property.forName("id").count());
		//投影查询，使用一个单独数据防止全遍历，并按照给定的条件查询
		int totalCount = claimVoucherDao.getTotalCount(criteria);
		result.setTotalCount(totalCount);
		if(totalCount != 0 ) {
			
			int totalPage = (totalCount+result.getPageSize()-1)/result.getPageSize();
			result.setTotalPageCount(totalPage);
			if(result.getCurrPageNo()>totalPage) {
				result.setCurrPageNo(totalPage);
				//有默认值，将默认值覆盖的方法，默认值为1所以当页面总数为0时设置为0
			}
			//2，分页查询
			criteria.setProjection(null);
			//将投影查询的条件进行清空处理
			List<ClaimVoucher> items = claimVoucherDao.findForPage(criteria, result.getCurrPageNo(), result.getPageSize());
			//传参查询获得列表文件
			//整体都是以公共的PaginationSupport对象进行数据的封装和传递
			result.setItems(items);
			//将最后的数据进行封装，还要进行非空的判断
		}
		
		
		
		
		
		return result;
	}

	public ClaimVoucherDao getClaimVoucherDao() {
		return claimVoucherDao;
	}

	public void setClaimVoucherDao(ClaimVoucherDao claimVoucherDao) {
		this.claimVoucherDao = claimVoucherDao;
	}
	
}
