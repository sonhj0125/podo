package member.domain;

public class PointDTO {
	
    private String userID;		 // 유저아이디
    private String poIncome;	 // 변동된 포인트값
    private String poDetail;	 // 포인트 변동내역
    private String poDate;	     // 포인트 적립한 날짜
    private String poStatus;	 // 포인트 상태 0 : 포인트시간 만료 || 1 : 사용가능포인트 || 2 : 사용한 포인트
    private String poExpiryDate; // 포인트 유효기간 만료일
    
    // List 로 값을 가져올 때 필요해서.. //
    private String ExpiringPoints;	// 소멸예정 포인트 (포인트 소멸까지 7일 남았을 때)
    private String daysUntilExpiry;	// 소멸예정까지 남은 일수 (포인트 소멸까지 7일 남았을 때)
    private String availablePoints;	// 사용가능 포인트(가용 적립금)
    private String TotalPoints;		// 총 포인트(누적 적립금)
    private String UsedPoints;		// 사용한 포인트(사용 적립금)
    

    // List 로 값을 가져올 때 필요해서.. //	
    
    private MemberDTO mdto;
    
    public PointDTO() {}

    public PointDTO(String userID, String poIncome, String poDetail, String poDate, String poStatus, String poExpiryDate, String daysUntilExpiry, String availablePoints, String TotalPoints, String UsedPoints) {
        this.userID = userID;
        this.poIncome = poIncome;
        this.poDetail = poDetail;
        this.poDate = poDate;
        this.poStatus = poStatus;
        this.poExpiryDate = poExpiryDate;
        this.daysUntilExpiry = daysUntilExpiry;
        this.availablePoints = availablePoints;
        this.TotalPoints = TotalPoints;
        this.UsedPoints = UsedPoints;
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

	public String getPoStatus() {
		return poStatus;
	}

	public void setPoStatus(String poStatus) {
		this.poStatus = poStatus;
	}

	public String getPoExpiryDate() {
		return poExpiryDate;
	}

	public void setPoExpiryDate(String poExpiryDate) {
		this.poExpiryDate = poExpiryDate;
	}

	public String getDaysUntilExpiry() {
		return daysUntilExpiry;
	}

	public void setDaysUntilExpiry(String daysUntilExpiry) {
		this.daysUntilExpiry = daysUntilExpiry;
	}

	public String getAvailablePoints() {
		return availablePoints;
	}

	public void setAvailablePoints(String availablePoints) {
		this.availablePoints = availablePoints;
	}

	public String getTotalPoints() {
		return TotalPoints;
	}

	public void setTotalPoints(String totalPoints) {
		TotalPoints = totalPoints;
	}

	public String getUsedPoints() {
		return UsedPoints;
	}

	public void setUsedPoints(String usedPoints) {
		UsedPoints = usedPoints;
	}

	public MemberDTO getMdto() {
		return mdto;
	}

	public void setMdto(MemberDTO mdto) {
		this.mdto = mdto;
	}

	public String getExpiringPoints() {
		return ExpiringPoints;
	}

	public void setExpiringPoints(String expiringPoints) {
		ExpiringPoints = expiringPoints;
	}


	
 
    
    
    
}
