package action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import biz.ClaimVoucherBiz;
import common.Constants;
import entity.ClaimVoucher;
import entity.ClaimVoucherDetail;
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
	private static Map<String,String> statusMap;
	private List<ClaimVoucherDetail> detailList;
	
	
	
	
	static {
		//静态导入状态映射数据内容
		System.out.println("静态代码块启动运行");
		statusMap = new HashMap<String,String>();
			statusMap.put(Constants.CLAIMVOUCHER_CREATED, Constants.CLAIMVOUCHER_CREATED);
			statusMap.put(Constants.CLAIMVOUCHER_SUBMITTED, Constants.CLAIMVOUCHER_SUBMITTED);
			statusMap.put(Constants.CLAIMVOUCHER_BACK, Constants.CLAIMVOUCHER_BACK);
			statusMap.put(Constants.CLAIMVOUCHER_APPROVING, Constants.CLAIMVOUCHER_APPROVING);
			statusMap.put(Constants.CLAIMVOUCHER_APPROVED, Constants.CLAIMVOUCHER_APPROVED);
			statusMap.put(Constants.CLAIMVOUCHER_PAID, Constants.CLAIMVOUCHER_PAID);
			statusMap.put(Constants.CLAIMVOUCHER_TERMINATED, Constants.CLAIMVOUCHER_TERMINATED);

		
		
	}
	
	
	//查询方法
	public String searchClaimVoucher() {
		Employee emp = (Employee) ActionContext.getContext().getSession().get(Constants.AUTH_EMPLOYEE);
		String posi = (String) ActionContext.getContext().getSession().get(Constants.EMPLOYEE_POSITION);
		if(Constants.POSITION_STAFF.equals(posi)) {
			System.out.println("为员工");
			pageSupport = claimVoucherBiz.findForPage(emp.getSn(), null , claimVoucher == null ? null:claimVoucher.getStatus(), startDate, endDate, pageNo, pageSize);
		}else {
			System.out.println("非员工");
			pageSupport = claimVoucherBiz.findForPage(null, emp.getSn() , claimVoucher == null ? null:claimVoucher.getStatus(), startDate, endDate, pageNo, pageSize);
		}
		//进行各种验证
		
		

		//进行非空验证
		return "list";
		//回到当前页面
		
	}
	//保存方法
	//保存报销单
	public String saveClaimVoucher() {
		Employee emp = (Employee)ActionContext.getContext().getSession().get(Constants.AUTH_EMPLOYEE);
		claimVoucher.setCreator(emp);//保存创建人
		if(Constants.CLAIMVOUCHER_SUBMITTED.equals(claimVoucher.getStatus())){
			Employee nextDeal = (Employee)ActionContext.getContext().getSession().get(Constants.AUTH_EMPLOYEE_MANAGER);
			claimVoucher.setNextDeal(nextDeal);
			//下一个处理人
			
		}
		//设置明细集合cascade级联操作
		//进行遍历
		for(ClaimVoucherDetail d:detailList) {
			
			d.setBizClaimVoucher(claimVoucher);
			//在报销单明细中设置报销单对象多对一操作
			
		}
		claimVoucher.setDetailList(detailList);
		//级联操作，设置报销单中的明细列表的赋值
		claimVoucherBiz.addNewClaimVoucher(claimVoucher);
		//直接添加新的报销单
		return "redirectList";
		//保存后跳转
	}
	//通过id查询报销单功能
	public String getClaimVoucherById() {
		
		claimVoucher = claimVoucherBiz.findById(claimVoucher.getId());
		//通过id查找报销单
		return "view";
	}
	
	//报销单更新跳转到update页面
	public String toUpdate() {
		claimVoucher = claimVoucherBiz.findById(claimVoucher.getId());
		//将id放入httpsession中;
		//将创建时间也存在httpsession中
		return "update";
	}
	public String toCheck() {
		claimVoucher = claimVoucherBiz.findById(claimVoucher.getId());
		//将id放入httpsession中;
		//将创建时间也存在httpsession中
		return "check";
	}	
	//更新操作
	public String updateClaimVoucher() {
		//httpsession get id
		//然后从httpsession中获取创建时间
		//或者走隐藏域的方式
		//或者使用hibernate框架的方式
		Employee emp = (Employee)ActionContext.getContext().getSession().get(Constants.AUTH_EMPLOYEE);
		claimVoucher.setCreator(emp);
		if(Constants.CLAIMVOUCHER_SUBMITTED.equals(claimVoucher.getStatus())){
			Employee nextDeal = (Employee)ActionContext.getContext().getSession().get(Constants.AUTH_EMPLOYEE_MANAGER);
			claimVoucher.setNextDeal(nextDeal);
			//下一个处理人
			
		}
		//设置明细集合cascade级联操作
		//进行遍历
		for(ClaimVoucherDetail d:detailList) {
			
			d.setBizClaimVoucher(claimVoucher);
			//在报销单明细中设置报销单对象多对一操作
			
		}
		claimVoucher.setDetailList(detailList);
		//级联操作，设置报销单中的明细列表的赋值
		claimVoucherBiz.updateNewClaimVoucher(claimVoucher);
		//调取更新的操作
		return "redirectList";
	}
	//通过报销单id删除报销单，需要及联操作
	public String deleteClaimVoucherById() {
		claimVoucherBiz.deleteClaimVoucher(claimVoucher);
		return "redirectList";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//setter和getter
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
	public Map<String, String> getStatusMap() {
		return statusMap;
	}
	public void setStatusMap(Map<String, String> statusMap) {
		ClaimVoucherAction.statusMap = statusMap;
	}
	public List<ClaimVoucherDetail> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<ClaimVoucherDetail> detailList) {
		this.detailList = detailList;
	}
	

	
	
	
	
	
}
