package action;

import java.util.Date;

import com.opensymphony.xwork2.ActionContext;

import biz.ClaimVoucherBiz;
import common.Constants;
import entity.ClaimVoucher;
import entity.Employee;
import util.PaginationSupport;

public class ClaimVoucherAction {
	private ClaimVoucherBiz claimVoucherBiz;
	private ClaimVoucher claimVoucher;
	private Date startDate;
	private Date endDate;
	private int pageNo;
	private int pageSize;
	private PaginationSupport<ClaimVoucher> pageSupport;
	public String searchClaimVoucher() {
		Employee emp = (Employee) ActionContext.getContext().getSession().get(Constants.AUTH_EMPLOYEE);
		String posi = (String) ActionContext.getContext().getSession().get(Constants.EMPLOYEE_POSITION);
		if(Constants.POSITION_STAFF.equals(posi)) {
			claimVoucherBiz.findForPage(emp.getSn(), null , claimVoucher == null ? null:claimVoucher.getStatus(), startDate, endDate, pageNo, pageSize);
		}else {
			claimVoucherBiz.findForPage(null, emp.getSn() , claimVoucher == null ? null:claimVoucher.getStatus(), startDate, endDate, pageNo, pageSize);
		}
		//进行各种验证
		
		

		//进行非空验证
		return "list";
		//回到当前页面
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public ClaimVoucherBiz getClaimVoucherBiz() {
		return claimVoucherBiz;
	}
	public void setClaimVoucherBiz(ClaimVoucherBiz claimVoucherBiz) {
		this.claimVoucherBiz = claimVoucherBiz;
	}
	public ClaimVoucher getClaimVoucher() {
		return claimVoucher;
	}
	public void setClaimVoucher(ClaimVoucher claimVoucher) {
		this.claimVoucher = claimVoucher;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public PaginationSupport<ClaimVoucher> getPageSupport() {
		return pageSupport;
	}
	public void setPageSupport(PaginationSupport<ClaimVoucher> pageSupport) {
		this.pageSupport = pageSupport;
	}

	
	
	
	
	
}
