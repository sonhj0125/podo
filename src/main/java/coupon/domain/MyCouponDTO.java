package coupon.domain;

import member.domain.MemberDTO;

public class MyCouponDTO {
	
	private int coindex, costatus;
	private MemberDTO mdto;
	private CouponDTO codto;
	
	public int getCoindex() {
		return coindex;
	}
	public void setCoindex(int coindex) {
		this.coindex = coindex;
	}
	public MemberDTO getMdto() {
		return mdto;
	}
	public void setMdto(MemberDTO mdto) {
		this.mdto = mdto;
	}
	public CouponDTO getCodto() {
		return codto;
	}
	public void setCodto(CouponDTO codto) {
		this.codto = codto;
	}
	public int getCostatus() {
		return costatus;
	}
	public void setCostatus(int costatus) {
		this.costatus = costatus;
	}

}
