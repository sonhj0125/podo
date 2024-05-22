package shop.domain;

import member.domain.MemberDTO;

public class OrderDTO {

	private int oindex;
	private String ovolume;
	private String ototalprice;
	private String opoint;
	private int ostatus;
	private String odate;
	private String oardate;
	
	private ProductDTO pdto;
	private MemberDTO mdto;

	public int getOindex() {
		return oindex;
	}
	
	public void setOindex(int oindex) {
		this.oindex = oindex;
	}
	
	public String getOvolume() {
		return ovolume;
	}

	public void setOvolume(String ovolume) {
		this.ovolume = ovolume;
	}
	
	public String getOtotalprice() {
		return ototalprice;
	}
	
	public void setOtotalprice(String ototalprice) {
		this.ototalprice = ototalprice;
	}
	
	public String getOpoint() {
		return opoint;
	}

	public void setOpoint(String opoint) {
		this.opoint = opoint;
	}

	public int getOstatus() {
		return ostatus;
	}

	public void setOstatus(int ostatus) {
		this.ostatus = ostatus;
	}
	
	public String getOdate() {
		return odate;
	}
	
	public void setOdate(String odate) {
		this.odate = odate;
	}
	
	public String getOardate() {
		return oardate;
	}
	
	public void setOardate(String oardate) {
		this.oardate = oardate;
	}
	
	public ProductDTO getPdto() {
		return pdto;
	}
	
	public void setPdto(ProductDTO pdto) {
		this.pdto = pdto;
	}
	
	public MemberDTO getMdto() {
		return mdto;
	}

	public void setMdto(MemberDTO mdto) {
		this.mdto = mdto;
	}
	
}
