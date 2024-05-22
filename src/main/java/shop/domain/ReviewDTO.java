package shop.domain;

public class ReviewDTO {
	private int rindex;
	private String rstar;
	private String rdetail;
	private String rdate;
	
	private OrderDTO odto;
	private ProductDTO pdto;

	
	public int getRindex() {
		return rindex;
	}

	public void setRindex(int rindex) {
		this.rindex = rindex;
	}

	public String getRstar() {
		return rstar;
	}

	public void setRstar(String rstar) {
		this.rstar = rstar;
	}

	public String getRdetail() {
		return rdetail;
	}

	public void setRdetail(String rdetail) {
		this.rdetail = rdetail;
	}

	public String getRdate() {
		return rdate;
	}

	public void setRdate(String rdate) {
		this.rdate = rdate;
	}

	public OrderDTO getOdto() {
		return odto;
	}

	public void setOdto(OrderDTO odto) {
		this.odto = odto;
	}

	public ProductDTO getPdto() {
		return pdto;
	}

	public void setPdto(ProductDTO pdto) {
		this.pdto = pdto;
	}

}
