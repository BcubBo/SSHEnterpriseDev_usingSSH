package biz.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;

import biz.ClaimVoucherBiz;
import dao.CheckResultDao;
import dao.ClaimVoucherDao;
import dao.ClaimVoucherDetailDao;
import entity.ClaimVoucher;
import util.PaginationSupport;

public class ClaimVoucherBizImpl implements ClaimVoucherBiz {
	private ClaimVoucherDao claimVoucherDao;
	//dao基本需要，由spring注入
	private ClaimVoucherDetailDao claimVoucherDetailDao;
	
	private CheckResultDao checkResultDao;
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
			criteria.add(Restrictions.ge("createTime",startDate));
			
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
	@Override
	public void addNewClaimVoucher(ClaimVoucher claimVoucher) {
		
		claimVoucher.setCreateTime(new Date());
		//添加创建时间
		claimVoucher.setModifyTime(claimVoucher.getCreateTime());
		//设置修改时间
		claimVoucherDao.save(claimVoucher);
		//设置及联操作，进行相应配置文件的配置
		//保存操作
	}
	@Override
	public ClaimVoucher findById(Serializable id) {
		return claimVoucherDao.findById(id);
	}
	
	@Override
	public void updateNewClaimVoucher(ClaimVoucher claimVoucher) {
//		claimVoucher.setCreateTime(new Date());
		//添加创建时间,配置文件中设置不更新操作
		claimVoucher.setModifyTime(new Date());
		//设置修改时间
		//清除旧数据
		claimVoucherDetailDao.deleteByClaimVoucher(claimVoucher);
		//通过传递对象使用外键来进行删除操作
		//更新主报销单并级联添加新的明细数据
		claimVoucherDao.update(claimVoucher);
		//设置及联操作，进行相应配置文件的配置
		//保存操作
	}
	//删除操作
	
	@Override
	public void deleteClaimVoucher(ClaimVoucher claimVoucher) {
/*		claimVoucher = claimVoucherDao.findById(claimVoucher.getId());
		//先进行查询
		
		//此可以执行
*/		
		//使用hql语句进行更改
		
		claimVoucherDetailDao.deleteByClaimVoucher(claimVoucher);
		checkResultDao.deleteByClaimVoucher(claimVoucher);
		//删除相关审核记录
		claimVoucherDao.delete(claimVoucher);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	//setter和getter
	
	
	public CheckResultDao getCheckResultDao() {
		return checkResultDao;
	}
	public void setCheckResultDao(CheckResultDao checkResultDao) {
		this.checkResultDao = checkResultDao;
	}
	public ClaimVoucherDetailDao getClaimVoucherDetailDao() {
		return claimVoucherDetailDao;
	}
	public void setClaimVoucherDetailDao(ClaimVoucherDetailDao claimVoucherDetailDao) {
		this.claimVoucherDetailDao = claimVoucherDetailDao;
	}

	public ClaimVoucherDao getClaimVoucherDao() {
		return claimVoucherDao;
	}
	public void setClaimVoucherDao(ClaimVoucherDao claimVoucherDao) {
		this.claimVoucherDao = claimVoucherDao;
	}


	
}
