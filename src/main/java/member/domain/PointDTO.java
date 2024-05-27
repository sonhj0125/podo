package member.domain;

public class PointDTO {
	
    private String userID;		 // 유저아이디
    private String poIncome;	 // 변동된 포인트값
    private String poDetail;	 // 포인트 변동내역
    private String poDate;	     // 포인트 적립한 날짜

    

    // List 로 값을 가져올 때 필요해서.. //	
    
    private MemberDTO mdto;
    
    public PointDTO() {}

    public PointDTO(String userID, String poIncome, String poDetail, String poDate) {
        this.userID = userID;
        this.poIncome = poIncome;
        this.poDetail = poDetail;
        this.poDate = poDate;
    }

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getPoIncome() {
		return poIncome;
	}

	public void setPoIncome(String poIncome) {
		this.poIncome = poIncome;
	}

	public String getPoDetail() {
		return poDetail;
	}

	public void setPoDetail(String poDetail) {
		this.poDetail = poDetail;
	}

	public String getPoDate() {
		return poDate;
	}

	public void setPoDate(String poDate) {
		this.poDate = poDate;
	}

	public MemberDTO getMdto() {
		return mdto;
	}

	public void setMdto(MemberDTO mdto) {
		this.mdto = mdto;
	}

	
 
    
    
    
}
