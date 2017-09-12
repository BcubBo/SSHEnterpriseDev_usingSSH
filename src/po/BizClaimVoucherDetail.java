package po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * BizClaimVoucherDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "BIZ_CLAIM_VOUCHER_DETAIL",schema="BCUBBO3")
public class BizClaimVoucherDetail implements java.io.Serializable {

	// Fields

	private Long id;
	private BizClaimVoucher bizClaimVoucher;
	private String item;
	private Double account;
	private String desc;

	// Constructors

	/** default constructor */
	public BizClaimVoucherDetail() {
	}

	/** full constructor */
	public BizClaimVoucherDetail(Long id, BizClaimVoucher bizClaimVoucher,
			String item, Double account, String des) {
		this.id = id;
		this.bizClaimVoucher = bizClaimVoucher;
		this.item = item;
		this.account = account;
		this.desc = des;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "claim_voucher_detail")
	@SequenceGenerator(name = "claim_voucher_detail", sequenceName = "SEQ_VOUCHER_DETAIL", allocationSize = 1)
	@Column(name = "ID", unique = true, nullable = false, precision = 10, scale = 0)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MAIN_ID")
	public BizClaimVoucher getBizClaimVoucher() {
		return this.bizClaimVoucher;
	}

	public void setBizClaimVoucher(BizClaimVoucher bizClaimVoucher) {
		this.bizClaimVoucher = bizClaimVoucher;
	}

	@Column(name = "ITEM", length = 20)
	public String getItem() {
		return this.item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	@Column(name = "ACCOUNT", precision = 20)
	public Double getAccount() {
		return this.account;
	}

	public void setAccount(Double account) {
		this.account = account;
	}

	@Column(name = "DES", length = 200)
	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String des) {
		this.desc = des;
	}

}