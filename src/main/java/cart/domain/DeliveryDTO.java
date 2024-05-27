package cart.domain;

public class DeliveryDTO {

	private int dindex,oindex;
	private String dnumber,dregisterday,dname,demail,dphone
				   ,dmsg,daddress,daddressdetail;
	
	public int getDindex() {
		return dindex;
	}
	public void setDindex(int dindex) {
		this.dindex = dindex;
	}
	public int getOindex() {
		return oindex;
	}
	public void setOindex(int oindex) {
		this.oindex = oindex;
	}
	public String getDnumber() {
		return dnumber;
	}
	public void setDnumber(String dnumber) {
		this.dnumber = dnumber;
	}
	public String getDregisterday() {
		return dregisterday;
	}
	public void setDregisterday(String dregisterday) {
		this.dregisterday = dregisterday;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getDemail() {
		return demail;
	}
	public void setDemail(String demail) {
		this.demail = demail;
	}
	public String getDphone() {
		return dphone;
	}
	public void setDphone(String dphone) {
		this.dphone = dphone;
	}
	public String getDmsg() {
		return dmsg;
	}
	public void setDmsg(String dmsg) {
		this.dmsg = dmsg;
	}
	public String getDaddress() {
		return daddress;
	}
	public void setDaddress(String daddress) {
		this.daddress = daddress;
	}
	public String getDaddressdetail() {
		return daddressdetail;
	}
	public void setDaddressdetail(String daddressdetail) {
		this.daddressdetail = daddressdetail;
	}
	
}
