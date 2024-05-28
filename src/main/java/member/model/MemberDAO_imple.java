package member.model;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import member.domain.MemberDTO;
import member.domain.PointDTO;
import shop.domain.OrderDTO;
import shop.domain.ProductDTO;
import shop.domain.ReviewDTO;
import util.security.AES256;
import util.security.SecretMykey;
import util.security.Sha256;

public class MemberDAO_imple implements MemberDAO {

	private DataSource ds;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private AES256 aes;
	
	private DecimalFormat df = new DecimalFormat("#,###");
	
	public MemberDAO_imple() {
		
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/semioracle");
			
			aes = new AES256(SecretMykey.KEY);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}// end of public MemberDAO_imple()
	
	
	
	// 자원 반납
	private void close() {
		
		try {
			if(rs != null)    {rs.close();    rs=null;}
			if(pstmt != null) {pstmt.close(); pstmt=null;}
			if(conn != null)  {conn.close();  conn=null;}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}// end of private void close()

	
	
	// 회원가입
	@Override
	public int doRegister(MemberDTO mdto) throws SQLException {
		
		int result = 0;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " INSERT INTO MEMBER "
					   + " (USERID, PWD, NAME, EMAIL, PHONE, ADDRESS, ADDRESSDETAIL, GENDER, BIRTHDAY) "
					   + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, mdto.getUserid());
			pstmt.setString(2, Sha256.encrypt(mdto.getPwd()));
			pstmt.setString(3, mdto.getName());
			pstmt.setString(4, aes.encrypt(mdto.getEmail()) );
			pstmt.setString(5, aes.encrypt(mdto.getPhone()));
			pstmt.setString(6, mdto.getAddress());
			pstmt.setString(7, mdto.getAddressDetail());
			pstmt.setString(8, mdto.getGender());
			pstmt.setString(9, mdto.getBirthday());

			result = pstmt.executeUpdate();
			
		} catch (UnsupportedEncodingException | GeneralSecurityException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return result;
		
	}// end of public int doRegister(MemberDTO mdto)

	
	@Override
	public MemberDTO signin(Map<String, String> paraMap) throws SQLException {
		
		MemberDTO mdto = null;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " select userid, name, email, phone, address, addressdetail, gender, birthday, point, registerday, pwdupdateday, memberidx "
					+ " from MEMBER where userid = ? and pwd = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("userid"));
			pstmt.setString(2, Sha256.encrypt(paraMap.get("pwd")));
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				mdto = new MemberDTO();
				
				mdto.setUserid(rs.getString(1));
				mdto.setName(rs.getString(2));
				mdto.setEmail(aes.decrypt(rs.getString(3)));
				mdto.setPhone(aes.decrypt(rs.getString(4)));
				mdto.setAddress(rs.getString(5));
				mdto.setAddressDetail(rs.getString(6));
				mdto.setGender(rs.getString(7));
				mdto.setBirthday(rs.getString(8));
				
				String point = df.format(Integer.parseInt(rs.getString(9)));
				mdto.setPoint(point);
				
				mdto.setRegisterDay(rs.getString(10));
				mdto.setPwdUpdateDay(rs.getString(11));
				mdto.setMemberIdx(rs.getString(12));
				
			}
			
		} catch (UnsupportedEncodingException | GeneralSecurityException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return mdto;
		
	} // end of public MemberDTO signin(Map<String, String> paraMap)
	
	
	// 아이디 중복 확인
	@Override
	public boolean idDuplicateCheck(String userid) throws SQLException {
		
		boolean isExist = false;
		
		try {
			conn = ds.getConnection();
			
			String sql = " select userid "
					   + " from member "
					   + " where userid = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			
			isExist = rs.next();
			
		} finally {
			close();
		}
		
		return isExist;
		
	} // end of public boolean idDuplicateCheck(String userid)



	// 이메일 중복 확인
	@Override
	public boolean emailDuplicateCheck(String email) throws SQLException {

		boolean isExist = false;
		
		try {
			conn = ds.getConnection();
			
			String sql = " select email "
					   + " from member "
					   + " where email = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, aes.encrypt(email));
			rs = pstmt.executeQuery();
			
			isExist = rs.next(); 
			
		} catch (UnsupportedEncodingException | GeneralSecurityException e) {
			e.printStackTrace();
			
		} finally {
			close();
		}
		
		return isExist;
		
	} // end of public boolean emailDuplicateCheck(String email)



	// 아이디 찾기
	@Override
	public String finduserid(Map<String, String> paraMap) throws SQLException {
		
		String userid = null;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " select userid from member where name = ? and email = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("name"));
			pstmt.setString(2, aes.encrypt(paraMap.get("email")));
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				userid = rs.getString("userid");
			}
			
		} catch (UnsupportedEncodingException | GeneralSecurityException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return userid;
	} // end of public String finduserid(Map<String, String> paraMap) throws SQLException



	// 비밀번호 찾기(성명, 아이디, (휴대폰 or 이메일)을 입력받아 해당 사용자가 존재하는지 여부 알아오기)
	@Override
	public boolean isUserExist(Map<String, String> paraMap) throws SQLException {

		boolean isUserExist = false;
		
		try {
			conn = ds.getConnection();

			String sql = " select userid "
					   + " from member "
					   + " where memberidx = 1 and name = ? and userid = ? ";

			if(paraMap.containsKey("email")) { // '이메일 인증'일 때
				sql += " and email = ? ";
				
			} else { // '휴대폰 인증'일 때
				sql += " and phone = ? ";
			}
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("name"));
			pstmt.setString(2, paraMap.get("userid"));
			
			if(paraMap.containsKey("email")) { // '이메일 인증'일 때
				pstmt.setString(3, aes.encrypt(paraMap.get("email")));
				
			} else { // '휴대폰 인증'일 때
				pstmt.setString(3, aes.encrypt(paraMap.get("phone")));
			}
			
			rs = pstmt.executeQuery();

			isUserExist = rs.next(); // 행이 있으면(사용자가 존재하면) true,
			 						 // 행이 없으면(사용자가 존재하지 않으면) false

		} catch (GeneralSecurityException | UnsupportedEncodingException e) {
			e.printStackTrace();
			
		} finally {
			close();
		}

		return isUserExist;
		
	} // end of public boolean isUserExist(Map<String, String> paraMap) throws SQLException { -----------



	// 비밀번호 변경
	@Override
	public int pwdUpdate(Map<String, String> paraMap) throws SQLException {

		int result = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = " update member set pwd = ?, pwdupdateday = to_char(sysdate, 'yyyy-mm-dd') "
					   + " where userid = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, Sha256.encrypt(paraMap.get("new_pwd"))); // 암호를 SHA256 알고리즘으로 단방향 암호화 시킨다.
			pstmt.setString(2, paraMap.get("userid"));
	        
	        result = pstmt.executeUpdate();
			
		} finally {
			close();
		}
		
		return result;
		
	} // end of public int pwdUpdate(Map<String, String> paraMap) throws SQLException ------------



	// 로그 기록
	@Override
	public int logwrite(Map<String, String> paraMap) throws SQLException {
		
		int result = 0;
		
		try {
		
			conn = ds.getConnection();
			
			String sql = " insert into LOG(LOGINDEX, userid, ipaddress) VALUES (SEQ_LOGIDX.nextval, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("userid"));
			pstmt.setString(2, paraMap.get("clientip"));
			
			result = pstmt.executeUpdate();
			
		}finally {
			
			close();
		
		}
		
		return result;
	}

	
	
	// 관리자 회원관리 - 페이징 처리를 한 모든 회원 또는 검색한 회원 목록 보여주기
	@Override
	public List<MemberDTO> select_Member_paging(Map<String, String> paraMap) throws SQLException {
		
		List<MemberDTO> memberList = new ArrayList<>();
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " SELECT rno, userid, name, email, phone, gender, point, status "
					   + " FROM "
					   + " ( "
					   + "    select rownum AS rno, userid, name, email, phone, gender, point, status "
					   + "    from "
					   + "    ( "
					   + "        select userid, name, email, phone, gender, point, status "
					   + "        from member join memberidx on member.memberidx = memberidx.memberidx "
					   + "        where memberidx.memberidx != 9";
			
			
			String colname = paraMap.get("searchType");
			String searchWord = paraMap.get("searchWord");
			
			
			if((colname != null && !colname.trim().isEmpty()) && 
			   (searchWord != null && !searchWord.trim().isEmpty())) {
				
				sql += " and " + colname + " like '%'|| ? ||'%' ";
			}
			
			
			sql += " order by registerday desc "
				 + "    ) "
				 + " ) T "
				 + " WHERE T.rno BETWEEN ? AND ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			
			int currentShowPageNo = Integer.parseInt(paraMap.get("currentShowPageNo"));
			int sizePerPage =Integer.parseInt(paraMap.get("sizePerPage"));
			
			
			if((colname != null && !colname.trim().isEmpty()) && 
			   (searchWord != null && !searchWord.trim().isEmpty())) {
				// 검색이 있는 경우
				
				pstmt.setString(1, searchWord);
				pstmt.setInt(2, (currentShowPageNo * sizePerPage - (sizePerPage - 1)));
				pstmt.setInt(3, (currentShowPageNo * sizePerPage));
				
			} else {
				// 검색이 없는 경우
				
				pstmt.setInt(1, (currentShowPageNo * sizePerPage - (sizePerPage - 1)));
				pstmt.setInt(2, (currentShowPageNo * sizePerPage));
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberDTO mdto = new MemberDTO();
				
				mdto.setUserid(rs.getString("userid"));
				mdto.setName(rs.getString("name"));
				mdto.setEmail(aes.decrypt(rs.getString("email")));
				mdto.setPhone(aes.decrypt(rs.getString("phone")));
				mdto.setGender(rs.getString("gender"));
				mdto.setPoint(rs.getString("point"));
				mdto.setStatus(rs.getString("status"));
				
				memberList.add(mdto);
				
			} // end of while(rs.next())
			
		} catch (GeneralSecurityException | UnsupportedEncodingException e) {
			e.printStackTrace();
			
		} finally {
			close();
		}
		
		return memberList;
		
	} // end of public List<MemberDTO> select_Member_paging(Map<String, String> paraMap)


	/* >>> 뷰단(memberList.jsp)에서 "페이징 처리 시 보여주는 순번 공식" 에서 사용하기 위해 
	   검색이 있는 또는 검색이 없는 회원의 총 개수 알아오기 <<< */
	@Override
	public int getTotalMemberCount(Map<String, String> paraMap) throws SQLException {
		int totalMemberCount = 0;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " select count(*) "
					   + " from member join memberidx on member.memberidx = memberidx.memberidx "
					   + " where memberidx.memberidx != 9 ";

			String colname = paraMap.get("searchType");
			String searchWord = paraMap.get("searchWord");
			
			
			if((colname != null && !colname.trim().isEmpty()) && 
			   (searchWord != null && !searchWord.trim().isEmpty())) {
				
				sql += " and " + colname + " like '%'|| ? ||'%' ";
			}
			
			pstmt = conn.prepareStatement(sql);
			
			if((colname != null && !colname.trim().isEmpty()) && 
			   (searchWord != null && !searchWord.trim().isEmpty())) {
				// 검색이 있는 경우
				
				pstmt.setString(1, searchWord);
			}
			
			rs = pstmt.executeQuery();
			rs.next();
			
			totalMemberCount = rs.getInt(1);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			close();
		}
		
		return totalMemberCount;
		
	} // end of public int getTotalMemberCount(Map<String, String> paraMap)

	
	// 관리자 회원관리 - 페이징 처리를 위한 검색이 있는 또는 검색이 없는 회원에 대한 총 페이지 수 알아오기
	@Override
	public int getTotalPage(Map<String, String> paraMap) throws SQLException {
		
		int totalPage = 0;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " select ceil(count(*)/?) "
					   + " from member "
					   + " join memberidx "
					   + " on member.memberidx = memberidx.memberidx "
					   + " where memberidx.memberidx != 9 ";

			String colname = paraMap.get("searchType");
			String searchWord = paraMap.get("searchWord");
			
			
			if((colname != null && !colname.trim().isEmpty()) && 
			   (searchWord != null && !searchWord.trim().isEmpty())) {
				
				sql += " and " + colname + " like '%'|| ? ||'%' ";
			}
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, Integer.parseInt(paraMap.get("sizePerPage")) );
			
			if((colname != null && !colname.trim().isEmpty()) && 
			   (searchWord != null && !searchWord.trim().isEmpty())) {
				// 검색이 있는 경우
				
				pstmt.setString(2, searchWord);
			}
			
			rs = pstmt.executeQuery();
			rs.next();
			
			totalPage = rs.getInt(1);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			close();
		}
		
		return totalPage;
		
	} // end of public int getTotalPage(Map<String, String> paraMap) throws SQLException
			
	// 관리자 회원관리 - 한명 조회
	@Override
	public MemberDTO selectOneMember(String userid) throws SQLException {
		
		MemberDTO mdto = null;
	      
	      try {
	         conn = ds.getConnection();
	         
	         String sql =  " select userid, name, email, phone, address, addressdetail, gender, member.memberidx "
	         			 + " , birthday, point, registerday, memberidx.status "
	         			 + " from member join memberidx on member.memberidx = memberidx.memberidx "
	         			 + " where memberidx.memberidx = 1 and userid = ? ";
	                     
	         pstmt = conn.prepareStatement(sql);
	         
	         pstmt.setString(1, userid);
	         
	         rs = pstmt.executeQuery();
	         
	         if(rs.next()) {
	        	 
	        	 mdto = new MemberDTO();
	            
	        	 mdto.setUserid(rs.getString("userid"));
	        	 mdto.setName(rs.getString("name"));
	        	 mdto.setEmail(aes.decrypt(rs.getString("email")));
	        	 mdto.setPhone(aes.decrypt(rs.getString("phone")));
	        	 mdto.setAddress(rs.getString("address"));
	        	 mdto.setAddressDetail(rs.getString("addressdetail"));
	        	 mdto.setGender(rs.getString("gender"));
	        	 mdto.setMemberIdx(rs.getString("memberidx"));
	        	 mdto.setBirthday(rs.getString("birthday"));
	        	 mdto.setPoint(rs.getString("point"));
	        	 mdto.setRegisterDay(rs.getString("registerday"));
	        	 mdto.setStatus(rs.getString("status"));
	        	 
	         } // end of if(rs.next())
	         
	      } catch(Exception e) {
	         e.printStackTrace();
	      } finally {
	         close();
	      }
	      
	      return mdto;
	      
	} // end of public MemberDTO selectOneMember(String userid) throws SQLException


	
	
	
	
	// 관리자 회원관리 - 포인트 내역 조회
	@Override
	public List<PointDTO> getMyPoint(String userid) throws SQLException {
		
		List<PointDTO> podtoList = new ArrayList<>();
	      
	    try {
	    	conn = ds.getConnection();
 
	    	String sql =  " select member.userid AS userid, poincome, podetail, podate "
	    				+ " from point "
	    				+ " join member on point.userid = member.userid "
	    				+ " where member.userid = ? "
	    				+ " order by podate desc ";
             
	    	pstmt = conn.prepareStatement(sql);
 
	    	pstmt.setString(1, userid);
 
	    	rs = pstmt.executeQuery();
 
	    	while(rs.next()) {
				
	    		PointDTO podto = new PointDTO();

	    		podto.setUserID(rs.getString("userid"));
	    		podto.setPoIncome(rs.getString("poincome"));
	    		podto.setPoDetail(rs.getString("podetail"));
	    		podto.setPoDate(rs.getString("podate"));
	    		
	    		podtoList.add(podto);
				
			}
				
	         
	    } catch(Exception e) {
	         e.printStackTrace();
	    } finally {
	         close();
	    }
	      
	      return podtoList;
	} // end of public List<PointDTO> getMyPoint(String userid) throws SQLException


	
	// 회원의 개인 정보 변경하기 
	@Override
	public int updateMember(MemberDTO member) throws SQLException {
		
		int result = 0;
		
		try {
	          conn = ds.getConnection();
		
        String sql = " update member set name = ? "
                   + "                   , email = ? "
                   + "                   , phone = ? "
                   + "                   , address = ? "
                   + "                   , addressdetail = ? "
                   + " where userid = ? ";
                
       pstmt = conn.prepareStatement(sql);
       
       pstmt.setString(1, member.getName());
       pstmt.setString(2, aes.encrypt(member.getEmail()) );  // 이메일을 AES256 알고리즘으로 양방향 암호화 시킨다. 
       pstmt.setString(3, aes.encrypt(member.getPhone()) ); // 휴대폰번호를 AES256 알고리즘으로 양방향 암호화 시킨다. 
       pstmt.setString(4, member.getAddress());
       pstmt.setString(5, member.getAddressDetail());
       pstmt.setString(6, member.getUserid());
                
       result = pstmt.executeUpdate();
       
    } catch(GeneralSecurityException | UnsupportedEncodingException e) {
       e.printStackTrace();
    }
     finally {
       close();
    }
    
		return result;    
		
	}// end of public int updateMember(MemberDTO member) throws SQLException-----


	// 회원정보 수정시 email 중복검사
	@Override
    public boolean emailDuplicateCheck2(Map<String, String> paraMap) throws SQLException {

      boolean isExists = false;
      
      try {
         conn = ds.getConnection();
         
         String sql = " select email "
                    + " from member "
                    + " where userid != ? and email = ? ";
         
         pstmt = conn.prepareStatement(sql); 
         pstmt.setString(1, paraMap.get("userid"));
         pstmt.setString(2, aes.encrypt(paraMap.get("email")));
         
         rs = pstmt.executeQuery();
         
         isExists = rs.next(); // 행이 있으면(중복된 email) true,
                               // 행이 없으면(사용가능한 email) false
         
      } catch(GeneralSecurityException | UnsupportedEncodingException e) {
         e.printStackTrace();
      } finally {
         close();
      }
      		
      return isExists;      
	      
	}// end of public boolean emailDuplicateCheck2(Map<String, String> paraMap) throws SQLException-----


	// 마이페이지 비밀번호 변경 비밀번호 변경
	@Override
	public int pwdUpdate2(Map<String, String> paraMap) throws SQLException {
		
		int result = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = " update member set pwd = ?, pwdupdateday = to_char(sysdate, 'yyyy-mm-dd') "
					   + " where userid = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, Sha256.encrypt(paraMap.get("new_pwd"))); // 암호를 SHA256 알고리즘으로 단방향 암호화 시킨다.
			pstmt.setString(2, paraMap.get("userid"));
	        
	        result = pstmt.executeUpdate();
		}finally {
		close();
		}
	
		return result;
	
	}// end of public int pwdUpdate2(Map<String, String> paraMap) throws SQLException-----------------------

	
	// [마이페이지] 작성할 리뷰 개수 알아오기
	@Override
	public int getReviewCnt(String userid) throws SQLException {
		
		int cnt = 0;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " SELECT count(*) as CNT "
					   + " FROM "
					   + " ( "
					   + "     select P.pindex, pname, pengname, to_number(pprice) as pprice, pimg, "
					   + "            to_number(ototalprice) as ototalprice, ostatus, odate, ovolume, oindex "
					   + "     from product P JOIN orders O "
					   + "     ON P.pindex = O.pindex "
					   + "     where O.userid = ? and O.ostatus = 4 "
					   + " ) V "
					   + " LEFT JOIN REVIEW R "
					   + " ON V.oindex = R.oindex "
					   + " where rindex is null ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();
			rs.next();
			
			cnt = rs.getInt(1);
			
		} finally {
			close();
		}
		
		return cnt;
		
	}// end of public int pwdUpdate2(Map<String, String> paraMap) throws SQLException-----------------------


	// 비밀번호 변경시 현재 사용중인 비밀번호인지 아닌지 알아오기(현재 사용중인 비밀번호 이라면 true, 새로운 비밀번호이라면 false)
	@Override
	public boolean duplicatePwdCheck(Map<String, String> paraMap) throws SQLException {
		
		boolean isExists = false;
		
		try {
	          conn = ds.getConnection();
	          
	          String sql = " select pwd "
	          		     + " from member "
	          		     + " where userid = ? and pwd = ? ";
	          
	          pstmt = conn.prepareStatement(sql); 
	          pstmt.setString(1, paraMap.get("userid"));
	          pstmt.setString(2, Sha256.encrypt(paraMap.get("new_pwd")));
	          
	          rs = pstmt.executeQuery();
	          
	          isExists = rs.next(); // 행이 있으면(현재 사용중인 비밀번호) true,
	                                // 행이 없으면(새로운 비밀번호) false
	          
	       } finally {
	          close();
	       }

		return isExists;
		
	}// end of public boolean duplicatePwdCheck(Map<String, String> paraMap) throws SQLException-----------

	@Override
	public int pointread(String userid) throws SQLException {
		
		int mypoint = 0;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " select point from MEMBER where userid = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String pointStr = rs.getString("point");
				
				try {
					mypoint = Integer.parseInt(pointStr);
				}catch (Exception e) {
					mypoint = -1;
				}
				
			}
			
		}finally {
			close();
		}
		
		return mypoint;
	}



	@Override
	public boolean delPoint(Map<String, String> paraMap) throws SQLException {
		
		boolean result = false;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = "UPDATE MEMBER SET POINT = POINT - ? WHERE USERID = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, paraMap.get("point"));
			pstmt.setString(2, paraMap.get("userid"));
			
			if(1==pstmt.executeUpdate()) {
				result = true;
			}
			
		}finally {
			close();
		}
		
		return result;
	}



	@Override
	public boolean writePointDown(Map<String, String> paraMap) throws SQLException {
		
		boolean result = false;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = "INSERT INTO POINT (USERID, POINCOME, PODETAIL) "
					+ "VALUES (?, ?, ?)" ;
			
			pstmt = conn.prepareStatement(sql);
			
			String msg = "상품 구입 차감";
			String point = "-"+paraMap.get("point");
			
			pstmt.setString(1, paraMap.get("userid"));
			pstmt.setString(2, point);
			pstmt.setString(3, msg);
			
			if(1==pstmt.executeUpdate()) {
				result = true;
			}
			
		}finally {
			close();
		}
		
		return result;
	}



	@Override
	public boolean addPoint(Map<String, String> paraMap) throws SQLException {
		
		boolean result = false;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = "UPDATE MEMBER SET POINT = POINT + ? WHERE USERID = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, paraMap.get("point"));
			pstmt.setString(2, paraMap.get("userid"));
			
			if(1==pstmt.executeUpdate()) {
				result = true;
			}
			
		}finally {
			close();
		}
		
		return result;
		
	}


	@Override
	public boolean writePointUp(Map<String, String> paraMap) throws SQLException {
		
		boolean result = false;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = "INSERT INTO POINT (USERID, POINCOME, PODETAIL) "
					+ "VALUES (?, ?, ?)" ;
			
			pstmt = conn.prepareStatement(sql);
			
			String msg = "상품 구매 적립";
			
			pstmt.setString(1, paraMap.get("userid"));
			pstmt.setString(2, paraMap.get("point"));
			pstmt.setString(3, msg);
			
			if(1==pstmt.executeUpdate()) {
				result = true;
			}
			
		}finally {
			close();
		}
		return result;
		
	}


	// 관리자 회원관리 - 리뷰 내역 조회
	@Override
	public List<ReviewDTO> getMyReview(String userid) throws SQLException {
		
		List<ReviewDTO> adminReviewList = new ArrayList<>();

		try {

			conn = ds.getConnection();

			String sql = " select review.rindex AS rindex, review.rstar AS rstar, review.rdetail AS rdetail, review.rdate AS rdate "
					   + " from review join orders on review.oindex = orders.oindex "
					   + " join member on orders.userid = member.userid "
					   + " where member.userid = ? "
					   + " order by odate desc ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);

			rs = pstmt.executeQuery();

			while(rs.next()) {

				ReviewDTO redto = new ReviewDTO();
				redto.setRindex(rs.getInt("rindex"));
				redto.setRstar(rs.getString("rstar"));
				redto.setRdetail(rs.getString("rdetail"));
				redto.setRdate(rs.getString("rdate"));
				
				
				adminReviewList.add(redto);
				

			}

		} finally {
			close();
		}

		return adminReviewList;
		
	} // end of public List<ReviewDTO> getMyReview(String userid) throws SQLException


	

	// 관리자 회원관리 - 해당 유저 정지
	@Override
	public List<MemberDTO> disableMember(String userid) throws SQLException {
		
		List<MemberDTO> adstatusOff = new ArrayList<>();
	     
		try {
			conn = ds.getConnection();
     
			String sql =  " update member "
						+ " set memberidx = 3 "
						+ " where userid = ? and memberidx = ? ";
             
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.setString(2, rs.getString("memberidx"));

			rs = pstmt.executeQuery();

			while(rs.next()) {
				
				MemberDTO mdto = new MemberDTO();
				mdto.setUserid(rs.getString("userid"));
				mdto.setMemberIdx(rs.getString("memberidx"));
				
				adstatusOff.add(mdto);
				
			}
		} finally {
			close();
		}
	      
	    return adstatusOff;
	      
	} // end of public List<MemberDTO> disableMember(String userid) throws SQLException


	

}
