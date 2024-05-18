package cart.domain;

import member.domain.MemberDTO;
import shop.domain.ProductDTO;

public class CartDTO {

	private MemberDTO mdto;
	private ProductDTO pdto;
	private String cvolume;
	private String sumprice;
	private int cindex;
	
	public MemberDTO getMdto() {
		return mdto;
	}
	public void setMdto(MemberDTO mdto) {
		this.mdto = mdto;
	}
	public ProductDTO getPdto() {
		return pdto;
	}
	public void setPdto(ProductDTO pdto) {
		this.pdto = pdto;
	}
	public String getCvolume() {
		return cvolume;
	}
	public void setCvolume(String cvolume) {
		this.cvolume = cvolume;
	}
	public int getCindex() {
		return cindex;
	}
	public void setCindex(int cindex) {
		this.cindex = cindex;
	}
	public String getSumprice() {
		return sumprice;
	}
	public void setSumprice(String sumprice) {
		this.sumprice = sumprice;
	}
	
}
