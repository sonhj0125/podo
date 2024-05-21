package coupon.domain;

public class CouponDTO {
	
	private String coname,codetail,codate,coregisterday;
	private int cotype,codiscount,comin;
	
	public String getConame() {
		return coname;
	}
	public void setConame(String coname) {
		this.coname = coname;
	}
	public String getCodetail() {
		return codetail;
	}
	public void setCodetail(String codetail) {
		this.codetail = codetail;
	}
	public String getCodate() {
		return codate;
	}
	public void setCodate(String codate) {
		this.codate = codate;
	}
	public int getCotype() {
		return cotype;
	}
	public void setCotype(int cotype) {
		this.cotype = cotype;
	}
	public int getCodiscount() {
		return codiscount;
	}
	public void setCodiscount(int codiscount) {
		this.codiscount = codiscount;
	}
	public int getComin() {
		return comin;
	}
	public void setComin(int comin) {
		this.comin = comin;
	}
	public String getCoregisterday() {
		return coregisterday;
	}
	public void setCoregisterday(String coregisterday) {
		this.coregisterday = coregisterday;
	}
	
}
