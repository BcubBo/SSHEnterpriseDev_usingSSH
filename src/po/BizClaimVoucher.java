package po;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * BizClaimVoucher entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BIZ_CLAIM_VOUCHER",schema="BCUBBO3")
public class BizClaimVoucher implements java.io.Serializable {

	// Fields

	private Long id;
	private SysEmployee sysEmployeeByNextDealSn;
	private SysEmployee sysEmployeeByCreateSn;
	private Date createTime;
	private String event;
	private Double totalAccount;
	private String status;
	private Date modifyTime;
	private Set<BizCheckResult> bizCheckResults = new HashSet<BizCheckResult>(0);
	private Set<BizClaimVoucherDetail> bizClaimVoucherDetails = new HashSet<BizClaimVoucherDetail>(
			0);

	// Constructors

	/** default constructor */
	public BizClaimVoucher() {
	}

	/** minimal constructor */
	public BizClaimVoucher(Long id, SysEmployee sysEmployeeByCreateSn,
			Date createTime, String event, Double totalAccount,
			String status) {
		this.id = id;
		this.sysEmployeeByCreateSn = sysEmployeeByCreateSn;
		this.createTime = createTime;
		this.event = event;
		this.totalAccount = totalAccount;
		this.status = status;
	}

	/** full constructor */
	public BizClaimVoucher(Long id, SysEmployee sysEmployeeByNextDealSn,
			SysEmployee sysEmployeeByCreateSn, Date createTime,
			String event, Double totalAccount, String status,
			Date modifyTime, Set<BizCheckResult> bizCheckResults,
			Set<BizClaimVoucherDetail> bizClaimVoucherDetails) {
		this.id = id;
		this.sysEmployeeByNextDealSn = sysEmployeeByNextDealSn;
		this.sysEmployeeByCreateSn = sysEmployeeByCreateSn;
		this.createTime = createTime;
		this.event = event;
		this.totalAccount = totalAccount;
		this.status = status;
		this.modifyTime = modifyTime;
		this.bizCheckResults = bizCheckResults;
		this.bizClaimVoucherDetails = bizClaimVoucherDetails;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "claim_voucher")
	@SequenceGenerator(name = "claim_voucher", sequenceName = "SEQ_VOUCHER", allocationSize = 1)
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "NEXT_DEAL_SN")
	public SysEmployee getSysEmployeeByNextDealSn() {
		return this.sysEmployeeByNextDealSn;
	}

	public void setSysEmployeeByNextDealSn(SysEmployee sysEmployeeByNextDealSn) {
		this.sysEmployeeByNextDealSn = sysEmployeeByNextDealSn;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CREATE_SN")
	public SysEmployee getSysEmployeeByCreateSn() {
		return this.sysEmployeeByCreateSn;
	}

	public void setSysEmployeeByCreateSn(SysEmployee sysEmployeeByCreateSn) {
		this.sysEmployeeByCreateSn = sysEmployeeByCreateSn;
	}

	@Column(name = "CREATE_TIME", length = 7)
	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "EVENT")
	public String getEvent() {
		return this.event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	@Column(name = "TOTAL_ACCOUNT", precision = 20)
	public Double getTotalAccount() {
		return this.totalAccount;
	}

	public void setTotalAccount(Double totalAccount) {
		this.totalAccount = totalAccount;
	}

	@Column(name = "STATUS", length = 20)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "MODIFY_TIME", length = 7)
	public Date getModifyTime() {
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bizClaimVoucher")
	public Set<BizCheckResult> getBizCheckResults() {
		return this.bizCheckResults;
	}

	public void setBizCheckResults(Set<BizCheckResult> bizCheckResults) {
		this.bizCheckResults = bizCheckResults;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "bizClaimVoucher")
	public Set<BizClaimVoucherDetail> getBizClaimVoucherDetails() {
		return this.bizClaimVoucherDetails;
	}

	public void setBizClaimVoucherDetails(
			Set<BizClaimVoucherDetail> bizClaimVoucherDetails) {
		this.bizClaimVoucherDetails = bizClaimVoucherDetails;
	}

}