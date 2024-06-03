package shop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import cart.domain.DeliveryDTO;
import shop.domain.OrderDTO;
import shop.domain.ProductDTO;
import shop.domain.ReviewDTO;

public class ProductDAO_imple implements ProductDAO {

	private DataSource ds;
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private DecimalFormat df = new DecimalFormat("#,###");
	
	public ProductDAO_imple() {
		
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/semioracle");
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

	
	
	// 제품 리스트 뽑아오기 DESC
	@Override
	public List<ProductDTO> listReadDesc() throws SQLException {
		
		List<ProductDTO> resultList = null;
		boolean isFSearch = true;
		
		try {
			conn = ds.getConnection();
			
			String sql = " SELECT * "
					   + " FROM PRODUCT "
					   + " ORDER BY PINDEX DESC ";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				if(isFSearch) {
					resultList = new ArrayList<>();
					isFSearch = false;
				}
				
				ProductDTO pdto = new ProductDTO();
				
				pdto.setPname(rs.getString("pname"));
				pdto.setPengname(rs.getString("pengname"));
				pdto.setPtype(rs.getString("ptype"));
				pdto.setPhometown(rs.getString("phometown"));
				
				String price = df.format(Integer.parseInt(rs.getString("pprice")));
				
				pdto.setPprice(price);
				pdto.setPpoint(rs.getString("ppoint"));
				pdto.setPbody(rs.getString("pbody"));
				pdto.setPacid(rs.getString("pacid"));
				pdto.setPtannin(rs.getString("ptannin"));
				pdto.setPacl(rs.getString("ptannin"));
				pdto.setPdetail(rs.getString("pdetail"));
				pdto.setPstock(rs.getString("pstock"));
				
				int pindex = rs.getInt("pindex");
				
				pdto.setPindex(pindex);
				pdto.setPimg(rs.getString("pimg"));
				
				ProductDAO_add pdaoadd = new ProductDAO_add();
				
				int like = pdaoadd.getlikecntAdd(pindex);
				
				pdto.setLike(like);
				
				resultList.add(pdto);
				
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return resultList;
		
	}// end of public Map<Integer, ProductDTO> listReadDesc()

	
	
	// 제품 하나 뽑아오기
	@Override
	public ProductDTO getproduct(int pindex) throws SQLException {
		
		ProductDTO pdto = null;
		
		try {
		
			conn = ds.getConnection();
			
			String sql = "select * "
					+ " from PRODUCT "
					+ " where PINDEX=? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pindex);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				pdto = new ProductDTO();
				
				pdto.setPname(rs.getString("pname"));
				pdto.setPengname(rs.getString("pengname"));
				pdto.setPtype(rs.getString("ptype"));
				pdto.setPhometown(rs.getString("phometown"));
				
				String price = df.format(Integer.parseInt(rs.getString("pprice")));
				
				pdto.setPprice(price);
				pdto.setPpoint(rs.getString("ppoint"));
				pdto.setPbody(rs.getString("pbody"));
				pdto.setPacid(rs.getString("pacid"));
				pdto.setPtannin(rs.getString("ptannin"));
				pdto.setPacl(rs.getString("pacl"));
				pdto.setPdetail(rs.getString("pdetail"));
				pdto.setPstock(rs.getString("pstock"));
				pdto.setPindex(rs.getInt("pindex"));
				pdto.setPimg(rs.getString("pimg"));
				
			}
			
		}finally {
			close();
		}
		
		return pdto;
	}// end of public ProductDTO getproduct(int pindex)
	
	
	
	// Search창 와인 검색
	@Override
	public List<ProductDTO> searchWineName(String searchWord) throws SQLException {

		List<ProductDTO> wineList = new ArrayList<>();

		try {

			conn = ds.getConnection();

			String sql = " select pname, pengname, ptype, phometown, pprice, pdetail, pimg, pindex "
					   + " from product "
					   + " where pname like '%'|| ? || '%' ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchWord);

			rs = pstmt.executeQuery();

			while(rs.next()) {

				ProductDTO pdto = new ProductDTO();

				pdto.setPname(rs.getString("pname"));
				pdto.setPengname(rs.getString("pengname"));
				pdto.setPtype(rs.getString("ptype"));
				pdto.setPhometown(rs.getString("phometown"));
				
				String price = df.format(Integer.parseInt(rs.getString("pprice")));
				
				pdto.setPprice(price);
				pdto.setPdetail(rs.getString("pdetail"));
				pdto.setPimg(rs.getString("pimg"));
				pdto.setPindex(rs.getInt("pindex"));

				wineList.add(pdto);

			}

		} finally {
			close();
		}

		return wineList;

	} // end of public List<ProductDTO> searchWineName(String searchWord)

	
	
	// Search창 와인 영문 검색
	@Override
	public List<ProductDTO> searchWineEngName(String searchWord) throws SQLException {

		List<ProductDTO> wineList = new ArrayList<>();

		try {

			conn = ds.getConnection();

			String sql = " select pname, pengname, ptype, phometown, pprice, pdetail, pimg, pindex "
					   + " from product "
					   + " where pengname like '%'|| UPPER(?) || '%' ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchWord);

			rs = pstmt.executeQuery();

			while(rs.next()) {

				ProductDTO pdto = new ProductDTO();

				pdto.setPname(rs.getString("pname"));
				pdto.setPengname(rs.getString("pengname"));
				pdto.setPtype(rs.getString("ptype"));
				pdto.setPhometown(rs.getString("phometown"));
				
				String price = df.format(Integer.parseInt(rs.getString("pprice")));
				
				pdto.setPprice(price);
				pdto.setPdetail(rs.getString("pdetail"));
				pdto.setPimg(rs.getString("pimg"));
				pdto.setPindex(rs.getInt("pindex"));

				wineList.add(pdto);

			}

		} finally {
			close();
		}

		return wineList;

	} // end of public List<ProductDTO> searchWineEngName(String searchWord) throws SQLException ----------------
	
	
	// 페이징 처리를 위해 검색이 있는 또는 검색이 없는 상품에 대한 총 페이지 수 알아오기
	@Override
	public int getTotalPage(Map<String, Object> paraMap) throws SQLException {

	    int totalPage = 0;

	    try {

	        conn = ds.getConnection();

	        String sql = " select ceil(count(*)/?) "
	                   + " from product "
	                   + " where ";

	        int paramsIndex = 2; // SQL 파라미터 인덱스 초기값 설정

	        String[] ptype_arr = (String[])paraMap.get("ptype_arr");
	        String pprice = (String)paraMap.get("pprice");
	        String[] phometown_arr = (String[])paraMap.get("phometown_arr");
	        String pbody = (String)paraMap.get("pbody");
	        String pacid = (String)paraMap.get("pacid");
	        String ptannin = (String)paraMap.get("ptannin");
	        
	        // 와인 종류
			if(ptype_arr != null) {
	            
	            for(int i=0; i<ptype_arr.length; i++) {
	            	
	            	if(i == 0) {
	            		sql += "ptype in(";
	            		
	            	}
	            	
	            	if(i <= ptype_arr.length - 2) {
	            		sql += "?,";
	            		
	                }
	            	
	            	if(i == ptype_arr.length - 1) {
	                	sql += "?) ";
	                }
	            }
	            
	            sql += " and ";
	        }
	        
			// 와인 가격대
	        if(pprice != null) {
	            
	            switch (pprice) {
		            case "1":
		                sql += " (to_number(pprice) < 10000) ";
		                break;
		                
		            case "2":
		                sql += " (10000 <= to_number(pprice) and to_number(pprice) < 50000) ";
		                break;
		                
		            case "3":
		                sql += " (50000 <= to_number(pprice) and to_number(pprice) < 150000) ";
		                break;
		                
		            case "4":
		                sql += " (150000 <= to_number(pprice) and to_number(pprice) < 300000) ";
		                break;
		                
		            case "5":
		                sql += " (to_number(pprice) >= 300000) ";
		                break;
		                
		            default:
		                break;
	            }
	            
	            sql += " and ";
	        }

	        // 와인 원산지
			if(phometown_arr != null) {
	            
	            for(int i=0; i<phometown_arr.length; i++) {
	            	
	            	if(i == 0) {
	            		sql += "phometown in(";
	            		
	            	}
	            	
	            	if(i <= phometown_arr.length - 2) {
	            		sql += "?,";
	            		
	                }
	            	
	            	if(i == phometown_arr.length - 1) {
	                	sql += "?) ";
	                }
	            }
	            
	            sql += " and ";
	        }
			
	        sql += " pbody like '%' ||  ? || '%' and "
	             + " pacid like '%' ||  ? || '%' and "
	             + " ptannin like '%' ||  ? || '%' ";
	        
	        pstmt = conn.prepareStatement(sql);

	        pstmt.setInt(1, Integer.parseInt((String)paraMap.get("sizePerPage")));

	        if(ptype_arr != null) {
	            for(int i=0; i<ptype_arr.length; i++) {
	                pstmt.setString(paramsIndex++, ptype_arr[i]);
	            }
	        }
	        
	        if(phometown_arr != null) {
	            for(int i=0; i<phometown_arr.length; i++) {
	                pstmt.setString(paramsIndex++, phometown_arr[i]);
	            }
	        }
	        
	        pstmt.setString(paramsIndex++, pbody);
	        pstmt.setString(paramsIndex++, pacid);
	        pstmt.setString(paramsIndex, ptannin);
	        
	        rs = pstmt.executeQuery();
	        rs.next();

	        totalPage = rs.getInt(1);

	    } finally {
	        close();
	    }

	    return totalPage;

	} // end of public int getTotalPage(Map<String, String> paraMap) throws SQLException -------

	
	
	// **** 페이징 처리를 한 검색 포함 상품 목록 보여주기 ****
	@Override
	public List<ProductDTO> selectProductPaging(Map<String, Object> paraMap) throws SQLException {

		List<ProductDTO> pdtList = new ArrayList<>();

		try {

			conn = ds.getConnection();

			String sql = " SELECT RNO, PNAME, PENGNAME, PTYPE, PHOMETOWN, PPRICE, "
						+ "            PPOINT, PBODY, PACID, PTANNIN, PACL, PDETAIL, PIMG, PSTOCK, PINDEX "
						+ "FROM "
						+ "(  "
						+ "    select rownum AS RNO, pname, pengname, ptype, phometown, pprice, "
						+ "           ppoint, pbody, pacid, ptannin, pacl, pdetail, pimg, pstock, pindex "
						+ "    from "
						+ "    ( "
						+ "        select pname, pengname, ptype, phometown, to_number(pprice) as pprice, "
						+ "			      ppoint, pbody, pacid, ptannin, pacl, pdetail, pimg, pstock, pindex "
						+ "        from product"
						+ "		   where ";
			
			int paramsIndex = 1; // SQL 파라미터 인덱스 초기값 설정
			
			String[] ptype_arr = (String[])paraMap.get("ptype_arr");
			String pprice = (String)paraMap.get("pprice");
			String[] phometown_arr = (String[])paraMap.get("phometown_arr");
			String pbody = (String)paraMap.get("pbody");
			String pacid = (String)paraMap.get("pacid");
			String ptannin = (String)paraMap.get("ptannin");
			
			
			if(ptype_arr != null) {
            
	            for(int i=0; i<ptype_arr.length; i++) {
	            	
	            	if(i == 0) {
	            		sql += "ptype in(";
	            		
	            	}
	            	
	            	if(i <= ptype_arr.length - 2) {
	            		sql += "?,";
	            		
	                }
	            	
	            	if(i == ptype_arr.length - 1) {
	                	sql += "?) ";
	                }
	            }
	            
	            sql += " and ";
	        }
			
			if(pprice != null) {
				
				switch (pprice) {
				case "1":
					sql += " (to_number(pprice) < 10000) ";
					break;
					
				case "2":
					sql += " (10000 <= to_number(pprice) and to_number(pprice) < 50000) ";
					break;
					
				case "3":
					sql += " (50000 <= to_number(pprice) and to_number(pprice) < 150000) ";
					break;
					
				case "4":
					sql += " (150000 <= to_number(pprice) and to_number(pprice) < 300000) ";
					break;
					
				case "5":
					sql += " (to_number(pprice) >= 300000) ";
					break;
					
				default:
					break;
				}
				
				sql += " and ";
			}
			
			if(phometown_arr != null) {
	            
	            for(int i=0; i<phometown_arr.length; i++) {
	            	
	            	if(i == 0) {
	            		sql += "phometown in(";
	            		
	            	}
	            	
	            	if(i <= phometown_arr.length - 2) {
	            		sql += "?,";
	            		
	                }
	            	
	            	if(i == phometown_arr.length - 1) {
	                	sql += "?) ";
	                }
	            }
	            
	            sql += " and ";
	        }

			sql += " pbody like '%' ||  ? || '%' and "
				 + " pacid like '%' ||  ? || '%' and "
				 + " ptannin like '%' ||  ? || '%' ";
			
			String sortType = (String)paraMap.get("sortType");
			
			switch (sortType) {
			case "latest":
				sql += " order by pindex desc ";
				break;
				
			case "popular":
				
				break;
				
			case "highPrice":
				sql += " order by pprice desc ";
				break;
				
			case "lowPrice":				
				sql += " order by pprice asc ";
				break;
				
			default:
				sql += " order by pindex desc ";
				break;
				
			} // end of switch(sortType) ---------------
			
						
			sql += "    ) V "
			+ " ) T "
			+ " WHERE T.rno BETWEEN ? AND ? ";
			
			pstmt = conn.prepareStatement(sql);

			if(ptype_arr != null) {
	            for(int i=0; i<ptype_arr.length; i++) {
	                pstmt.setString(paramsIndex++, ptype_arr[i]);
	            }
	        }

			if(phometown_arr != null) {
	            for(int i=0; i<phometown_arr.length; i++) {
	                pstmt.setString(paramsIndex++, phometown_arr[i]);
	            }
	        }
			
			pstmt.setString(paramsIndex++, pbody);
			pstmt.setString(paramsIndex++, pacid);
			pstmt.setString(paramsIndex++, ptannin);
			
			/*
			=== 페이징처리의 공식 ===
			where RNO between (조회하고자하는페이지번호 * 한페이지당보여줄행의개수) - (한페이지당보여줄행의개수 - 1) and (조회하고자하는페이지번호 * 한페이지당보여줄행의개수);
			*/

			int currentShowPageNo = Integer.parseInt((String)paraMap.get("currentShowPageNo"));
			int sizePerPage =Integer.parseInt((String)paraMap.get("sizePerPage"));

			pstmt.setInt(paramsIndex++, (currentShowPageNo * sizePerPage - (sizePerPage - 1)));
			pstmt.setInt(paramsIndex, (currentShowPageNo * sizePerPage));

			rs = pstmt.executeQuery();

			while(rs.next()) {

				ProductDTO pdto = new ProductDTO();

				pdto.setPname(rs.getString("pname"));
				pdto.setPengname(rs.getString("pengname"));
				pdto.setPtype(rs.getString("ptype"));
				pdto.setPhometown(rs.getString("phometown"));

				String price = df.format(rs.getInt("pprice"));
				pdto.setPprice(price);

				pdto.setPpoint(rs.getString("ppoint"));
				pdto.setPbody(rs.getString("pbody"));
				pdto.setPacid(rs.getString("pacid"));
				pdto.setPtannin(rs.getString("ptannin"));
				pdto.setPacl(rs.getString("pacl"));
				pdto.setPdetail(rs.getString("pdetail"));
				pdto.setPimg(rs.getString("pimg"));
				pdto.setPstock(rs.getString("pstock"));
				pdto.setPindex(rs.getInt("pindex"));

				pdtList.add(pdto);

			} // end of while(rs.next()) ------------------------------------

		} finally {
			close();
		}

		return pdtList;
		
	} // end of public List<ProductDTO> selectProductPaging(Map<String, String> paraMap) throws SQLException -----------

	
	
	// 페이징 처리한 검색 포함 상품 목록 인기순 정렬
	@Override
	public List<ProductDTO> selectProductPagingPopular(Map<String, Object> paraMap) throws SQLException {

		List<ProductDTO> pdtList = new ArrayList<>();
		
		try {

			conn = ds.getConnection();

			String sql = " SELECT rno, pindex, popular, pname, pengname, ptype, phometown, "
					   + "       pprice, ppoint, pbody, pacid, ptannin, pacl, pdetail, pimg, pstock "
					   + " FROM "
					   + " ( "
					   + "     SELECT rownum as RNO, V1.* "
					   + "     FROM "
					   + "     ( "
					   + "         WITH "
					   + "         P AS ( "
					   + "             select * "
					   + "             from product"
					   + "			   where ";
			
			int paramsIndex = 1; // SQL 파라미터 인덱스 초기값 설정
			
			String[] ptype_arr = (String[])paraMap.get("ptype_arr");
			String pprice = (String)paraMap.get("pprice");
			String[] phometown_arr = (String[])paraMap.get("phometown_arr");
			String pbody = (String)paraMap.get("pbody");
			String pacid = (String)paraMap.get("pacid");
			String ptannin = (String)paraMap.get("ptannin");
			
			
			if(ptype_arr != null) {
            
	            for(int i=0; i<ptype_arr.length; i++) {
	            	
	            	if(i == 0) {
	            		sql += "ptype in(";
	            		
	            	}
	            	
	            	if(i <= ptype_arr.length - 2) {
	            		sql += "?,";
	            		
	                }
	            	
	            	if(i == ptype_arr.length - 1) {
	                	sql += "?) ";
	                }
	            }
	            
	            sql += " and ";
	        }
			
			if(pprice != null) {
				
				switch (pprice) {
				case "1":
					sql += " (to_number(pprice) < 10000) ";
					break;
					
				case "2":
					sql += " (10000 <= to_number(pprice) and to_number(pprice) < 50000) ";
					break;
					
				case "3":
					sql += " (50000 <= to_number(pprice) and to_number(pprice) < 150000) ";
					break;
					
				case "4":
					sql += " (150000 <= to_number(pprice) and to_number(pprice) < 300000) ";
					break;
					
				case "5":
					sql += " (to_number(pprice) >= 300000) ";
					break;
					
				default:
					break;
				}
				
				sql += " and ";
			}
			
			if(phometown_arr != null) {
	            
	            for(int i=0; i<phometown_arr.length; i++) {
	            	
	            	if(i == 0) {
	            		sql += "phometown in(";
	            		
	            	}
	            	
	            	if(i <= phometown_arr.length - 2) {
	            		sql += "?,";
	            		
	                }
	            	
	            	if(i == phometown_arr.length - 1) {
	                	sql += "?) ";
	                }
	            }
	            
	            sql += " and ";
	        }
			
			sql += " 				pbody like '%' ||  ? || '%' and "
				 + " 				pacid like '%' ||  ? || '%' and "
				 + " 				ptannin like '%' ||  ? || '%' "
			     + "        	), "
				 + "            L AS ( "
				 + "                select pindex, count(pindex) as popular "
				 + "                from likeit "
				 + "                group by pindex "
				 + "            ) "
				 + "            SELECT P.*, popular "
				 + "            FROM P LEFT JOIN L "
				 + "            ON P.pindex = L.pindex "
				 + "            order by CASE "
				 + "                     WHEN popular IS NULL THEN 1 "
				 + "                     ELSE 0 "
				 + "                     END, "
				 + "                     popular desc "
				 + "        ) V1 "
				 + " ) V2 "
				 + " WHERE V2.rno between ? and ? ";
				
			pstmt = conn.prepareStatement(sql);

			if(ptype_arr != null) {
	            for(int i=0; i<ptype_arr.length; i++) {
	                pstmt.setString(paramsIndex++, ptype_arr[i]);
	            }
	        }

			if(phometown_arr != null) {
	            for(int i=0; i<phometown_arr.length; i++) {
	                pstmt.setString(paramsIndex++, phometown_arr[i]);
	            }
	        }
			
			pstmt.setString(paramsIndex++, pbody);
			pstmt.setString(paramsIndex++, pacid);
			pstmt.setString(paramsIndex++, ptannin);
			
			/*
			=== 페이징처리의 공식 ===
			where RNO between (조회하고자하는페이지번호 * 한페이지당보여줄행의개수) - (한페이지당보여줄행의개수 - 1) and (조회하고자하는페이지번호 * 한페이지당보여줄행의개수);
			*/

			int currentShowPageNo = Integer.parseInt((String)paraMap.get("currentShowPageNo"));
			int sizePerPage =Integer.parseInt((String)paraMap.get("sizePerPage"));

			pstmt.setInt(paramsIndex++, (currentShowPageNo * sizePerPage - (sizePerPage - 1)));
			pstmt.setInt(paramsIndex, (currentShowPageNo * sizePerPage));

			rs = pstmt.executeQuery();

			while(rs.next()) {

				ProductDTO pdto = new ProductDTO();

				pdto.setPname(rs.getString("pname"));
				pdto.setPengname(rs.getString("pengname"));
				pdto.setPtype(rs.getString("ptype"));
				pdto.setPhometown(rs.getString("phometown"));

				String price = df.format(rs.getInt("pprice"));
				pdto.setPprice(price);

				pdto.setPpoint(rs.getString("ppoint"));
				pdto.setPbody(rs.getString("pbody"));
				pdto.setPacid(rs.getString("pacid"));
				pdto.setPtannin(rs.getString("ptannin"));
				pdto.setPacl(rs.getString("pacl"));
				pdto.setPdetail(rs.getString("pdetail"));
				pdto.setPimg(rs.getString("pimg"));
				pdto.setPstock(rs.getString("pstock"));
				pdto.setPindex(rs.getInt("pindex"));

				pdtList.add(pdto);

			} // end of while(rs.next()) ------------------------------------

		} finally {
			close();
		}

		return pdtList;
		
	} // end of public List<ProductDTO> selectProductPagingPopular(String[] ptype_arr, Map<String, String> paraMap) throws SQLException ------------


	
	// 좋아요
	@Override
	public int setLike(Map<String, String> paraMap) throws SQLException {
		
		int result = 0;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = "INSERT INTO Likeit (USERID, PINDEX) "
					+ "VALUES (?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, paraMap.get("userid"));
			pstmt.setString(2, paraMap.get("pindex"));
			
			result = pstmt.executeUpdate();
			
			
		}finally {
			close();
		}
		
		return result;
	}

	
	
	// 유저의 좋아요 여부 판단
	@Override
	public boolean isLike(Map<String, String> paraMap) throws SQLException {
		
		boolean isLike = false;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = "select * from likeit where userid = ? and pindex = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, paraMap.get("userid"));
			pstmt.setString(2, paraMap.get("pindex"));
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				isLike = true;
			}
						
		}finally {
			close();
		}
		
		return isLike;
	}

	
	
	// 좋아요 제거
	@Override
	public int setunlike(Map<String, String> paraMap) throws SQLException {
		
		int result = 0;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " DELETE "
					+ " FROM LIKEIT "
					+ " WHERE USERID = ? AND PINDEX = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, paraMap.get("userid"));
			pstmt.setString(2, paraMap.get("pindex"));
			
			result = pstmt.executeUpdate();
			
			
		}finally {
			close();
		}
		
		return result;
	}
	
	
	
	// ** product 이미지의 상세정보 가져오기 **
	@Override
	public String getProductDetailImg(int pindex) throws SQLException {
		
		String pdImgName = "";
		
		try {

			conn = ds.getConnection();

			String sql = " select pdImg "
					   + " from ProductDetailImg "
					   + " where pIndex = ? ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, pindex);

			rs = pstmt.executeQuery();
			
			rs.next();
			
			pdImgName = rs.getString("pdImg");

		} finally {
			close();
		}	
		
		return pdImgName;
	}

	
	
	// 인기 품목 리스트 뽑아오기 DESC
	@Override
	public List<ProductDTO> listPopReadDesc() throws SQLException {
		
		List<ProductDTO> pdto_list = new ArrayList<>();
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " select * "
					+ " from "
					+ " (select pindex,count(pindex)as coun from LIKEIT group by pindex order by coun desc) l join PRODUCT on l.PINDEX=PRODUCT.PINDEX "
					+ " order by coun desc ";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				ProductDTO pdto = new ProductDTO();
				
				pdto.setPname(rs.getString("pname"));
				pdto.setPengname(rs.getString("pengname"));
				pdto.setPtype(rs.getString("ptype"));
				pdto.setPhometown(rs.getString("phometown"));
				
				String price = df.format(Integer.parseInt(rs.getString("pprice")));
				
				pdto.setPprice(price);
				pdto.setPpoint(rs.getString("ppoint"));
				pdto.setPbody(rs.getString("pbody"));
				pdto.setPacid(rs.getString("pacid"));
				pdto.setPtannin(rs.getString("ptannin"));
				pdto.setPacl(rs.getString("ptannin"));
				pdto.setPdetail(rs.getString("pdetail"));
				pdto.setPstock(rs.getString("pstock"));
				
				int pindex = rs.getInt("pindex");
				
				pdto.setPindex(pindex);
				pdto.setPimg(rs.getString("pimg"));
				
				ProductDAO_add pdaoadd = new ProductDAO_add();
				
				int like = pdaoadd.getlikecntAdd(pindex);
				
				pdto.setLike(like);
				
				pdto_list.add(pdto);
				
			}
			
			
		}finally {
			close();
		}
		
		return pdto_list;
	}

	// 제품번호 채번 해오기
	@Override
	public int getPnumOfProduct() throws SQLException {
		
	      int pindex = 0;
	         
	       try {
	            conn = ds.getConnection();
	            
	            String sql = " select SEQ_PINDEX.nextval AS pindex "
	            		   + " from dual ";
	            
	            pstmt = conn.prepareStatement(sql);
	            rs = pstmt.executeQuery();
	            
	               rs.next();
	               pindex = rs.getInt(1);
	            
	       } finally {
	            close();
	       }
	         
	       return pindex;
	       
	}// end of public int getPnumOfProduct() throws SQLException-----------------

	
	// 제품설명이미지 채번 해오기
	@Override
	public int getpdindexOfProduct() throws SQLException {
		
		int pdindex = 0;
        
	       try {
	            conn = ds.getConnection();
	            
	            String sql = " select SEQ_PDINDEX.nextval AS pdindex "
	            		   + " from dual ";
	            
	            pstmt = conn.prepareStatement(sql);
	            rs = pstmt.executeQuery();
	            
	               rs.next();
	               pdindex = rs.getInt(1);
	            
	       } finally {
	            close();
	       }
	         
	       return pdindex;
	}// end of public int getpdindexOfProduct() throws SQLException---------

	
	// [리뷰 관리] 회원이 주문한 상품 중 배송완료인 상품 목록 띄우기
	@Override
	public List<ReviewDTO> selectProductReviewList(String userid) throws SQLException {

		List<ReviewDTO> resultList = new ArrayList<>();
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " SELECT pindex, pname, pengname, pprice, pimg, V.oindex, ototalprice,"
					   + " ostatus, odate, ovolume, NVL(rindex, 0) AS rindex, rdate "
					   + " FROM "
					   + " ( "
					   + "     select P.pindex, pname, pengname, to_number(pprice) as pprice, pimg, "
					   + "            to_number(ototalprice) as ototalprice, ostatus, odate, ovolume, oindex "
					   + "     from product P JOIN orders O "
					   + "     ON P.pindex = O.pindex "
					   + "     where O.userid = ? and O.ostatus = 4 "
					   + " ) V "
					   + " LEFT JOIN REVIEW R "
					   + " ON V.oindex = R.oindex"
					   + " ORDER BY oindex desc ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				ReviewDTO rdto = new ReviewDTO();
				ProductDTO pdto = new ProductDTO();
				OrderDTO odto = new OrderDTO();
				
				pdto.setPindex(rs.getInt("pindex"));
				pdto.setPname(rs.getString("pname"));
				pdto.setPengname(rs.getString("pengname"));
				
				String price = df.format(rs.getInt("pprice"));
				pdto.setPprice(price);
				
				pdto.setPimg(rs.getString("pimg"));
				odto.setPdto(pdto);
				
				odto.setOindex(rs.getInt("oindex"));
				
				String ototalprice = df.format(rs.getInt("ototalprice"));
				odto.setOtotalprice(ototalprice);
				
				odto.setOstatus(rs.getInt("ostatus"));
				odto.setOdate(rs.getString("odate"));
				odto.setOvolume(rs.getString("ovolume"));
				rdto.setOdto(odto);
				
				rdto.setRindex(rs.getInt("rindex"));
				rdto.setRdate(rs.getString("rdate"));
				
				resultList.add(rdto);
			}
			
		} finally {
			close();
		}
		
		return resultList;
		
	} // end of public List<ReviewDTO> selectProductReviewList(String userid) throws SQLException -------------

	
	
	// [리뷰 작성] 주문 인덱스에 대한 상품 정보 받아오기
	@Override
	public ProductDTO getProductByOindex(Map<String, String> paraMap) throws SQLException {

		ProductDTO pdto = null;
		
		try {
		
			conn = ds.getConnection();
			
			String sql = " select P.* "
					   + " from product P JOIN orders O "
					   + " ON P.pindex = O.pindex "
					   + " where oindex = ? and userid = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("oindex"));
			pstmt.setString(2, paraMap.get("userid"));
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				pdto = new ProductDTO();
				
				pdto.setPname(rs.getString("pname"));
				pdto.setPengname(rs.getString("pengname"));
				pdto.setPtype(rs.getString("ptype"));
				pdto.setPhometown(rs.getString("phometown"));
				
				String price = df.format(Integer.parseInt(rs.getString("pprice")));
				pdto.setPprice(price);
				
				pdto.setPpoint(rs.getString("ppoint"));
				pdto.setPindex(rs.getInt("pindex"));
				pdto.setPimg(rs.getString("pimg"));
				
			}
			
		}finally {
			close();
		}
		
		return pdto;
		
	} // end of public ProductDTO getProductByOindex(String oindex) throws SQLException --------------------

	
	
	// [리뷰 작성] oindex에 대한 리뷰가 존재하는지 확인
	@Override
	public boolean isExistReviewByOindex(String oindex) throws SQLException {
		
		boolean isExistReview = false;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " select * "
					   + " from review "
					   + " where oindex = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, oindex);
			
			rs = pstmt.executeQuery();
			
			isExistReview = rs.next();
			
		} finally {
			close();
		}
		
		return isExistReview;
		
	} // end of public boolean isUploadedReview(Map<String, String> paraMap) throws SQLException -----------

	
	
	// [리뷰 작성] 리뷰 작성하기
	@Override
	public int addReview(Map<String, String> paraMap) throws SQLException {
		
		int result = 0;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " insert into review(rindex, rstar, rdetail, rdate, oindex) "
					   + " values(seq_rIdx.nextval, ?, ?, to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss'), ?) ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("rstar"));
			pstmt.setString(2, paraMap.get("rdetail"));
			pstmt.setString(3, paraMap.get("oindex"));
			
			result = pstmt.executeUpdate();
			
		} finally {
			close();
		}
		
		return result;
		
	} // end of public int addReview(Map<String, String> paraMap) throws SQLException -----------------

	
	
	// [리뷰 수정] rindex에 대한 리뷰가 존재하는지 확인하기
	@Override
	public ReviewDTO getReviewByRindex(Map<String, String> paraMap) throws SQLException {
		
		ReviewDTO rdto = null;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " select * "
					   + " from review R JOIN orders O "
					   + " on R.oindex = O.oindex "
					   + " JOIN product P "
					   + " ON O.pindex = P.pindex "
					   + " where R.rindex = ? and O.userid = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("rindex"));
			pstmt.setString(2, paraMap.get("userid"));
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				rdto = new ReviewDTO();
				
				rdto.setRindex(rs.getInt("rindex"));
				rdto.setRstar(rs.getString("rstar"));
				rdto.setRdetail(rs.getString("rdetail"));
				rdto.setRdate(rs.getString("rdate"));
				
				OrderDTO odto = new OrderDTO();
				ProductDTO pdto = new ProductDTO();
				
				pdto.setPname(rs.getString("pname"));
				pdto.setPengname(rs.getString("pengname"));
				
				String price = df.format(Integer.parseInt(rs.getString("pprice")));
				pdto.setPprice(price);
				
				pdto.setPimg(rs.getString("pimg"));
				
				odto.setPdto(pdto);
				rdto.setOdto(odto);
				
			}
			
		} finally {
			close();
		}
		
		return rdto;
		
	} // end of public boolean isExistReviewByRindex(Map<String, String> paraMap) throws SQLException -----------

	
	
	// [리뷰 수정] 리뷰 수정하기
	@Override
	public int updateReview(ReviewDTO rdto) throws SQLException {
		
		int result = 0;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " update review "
					   + " set rstar = ?, rdetail = ?, rdate = to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss') "
					   + " where rindex = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rdto.getRstar());
			pstmt.setString(2, rdto.getRdetail());
			pstmt.setInt(3, rdto.getRindex());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close();
		}
		
		return result;
		
	} // end of public int updateReview(ReviewDTO rdto) throws SQLException ----------------------

	
	
	// [shop] pindex에 대한 리뷰 목록 불러오기
	@Override
	public List<ReviewDTO> getReviewListByPindex(int pindex) throws SQLException {

		List<ReviewDTO> reviewList = new ArrayList<>();
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " select R.*, O.userid, P.pindex "
					   + " from product P JOIN orders O "
					   + " ON P.pindex = O.pindex "
					   + " JOIN review R "
					   + " ON O.oindex = R.oindex "
					   + " where P.pindex = ? "
					   + " order by rindex desc ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pindex);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				ReviewDTO rdto = new ReviewDTO();
				OrderDTO odto = new OrderDTO();
				ProductDTO pdto = new ProductDTO();
				
				rdto.setRindex(rs.getInt("rindex"));
				rdto.setRstar(rs.getString("rstar"));
				rdto.setRdetail(rs.getString("rdetail"));
				rdto.setRdate(rs.getString("rdate"));
				rdto.setOindex(rs.getInt("oindex"));
				
				odto.setUserid(rs.getString("userid"));
				
				pdto.setPindex(rs.getInt("pindex"));
				odto.setPdto(pdto);
				
				rdto.setOdto(odto);
				
				reviewList.add(rdto);
			}
			
		} finally {
			close();
		}
		
		return reviewList;
		
	} // end of public List<ReviewDTO> getReviewListByPindex(int pindex) throws SQLException --------

	
	
	// [주문내역조회] 회원이 주문한 상품 목록 받아오기
	@Override
	public List<OrderDTO> selectOrderList(String userid) throws SQLException {
		
		List<OrderDTO> orderList = new ArrayList<>();
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " select P.pname, pimg, O.ototalprice, ovolume, odate, P.pindex, oindex "
					   + " from product P JOIN orders O "
					   + " ON P.pindex = O.pindex "
					   + " where O.userid = ? "
					   + " order by oindex desc ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				OrderDTO odto = new OrderDTO();
				ProductDTO pdto = new ProductDTO();
				
				pdto.setPname(rs.getString("pname"));
				pdto.setPimg(rs.getString("pimg"));
				
				odto.setPdto(pdto);
				
				odto.setOtotalprice(rs.getString("ototalprice"));
				odto.setOvolume(rs.getString("ovolume"));
				odto.setOdate(rs.getString("odate"));
				odto.setPindex(rs.getInt("pindex"));
				odto.setOindex(rs.getInt("oindex"));
				
				orderList.add(odto);
			}
			
		} finally {
			close();
		}
		
		return orderList;
		
	} // end of public List<OrderDTO> selectOrderList(String userid) throws SQLException -----------------

	
	
	// [주문내역조회] 주문 인덱스에 대한 상품, 주문, 배송 정보 받아오기
	@Override
	public DeliveryDTO getOrderDetail(Map<String, String> paraMap) throws SQLException {

		DeliveryDTO ddto = null;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " select P.pindex, pname, pengname, pimg, ptype, phometown, pprice, "
					   + "        O.oindex, ototalprice, odate, ostatus, oardate, ovolume, "
					   + "        dnumber, dname, demail, dphone, dmsg, daddress, daddressdetail "
					   + " from product P JOIN orders O "
					   + " ON P.pindex = O.pindex "
					   + " JOIN delivery D "
					   + " ON O.oindex = D.oindex "
					   + " where O.oindex = ? and userid = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("oindex"));
			pstmt.setString(2, paraMap.get("userid"));
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				ddto = new DeliveryDTO();
				
				OrderDTO odto = new OrderDTO();
				ProductDTO pdto = new ProductDTO();
				
				pdto.setPindex(rs.getInt("pindex"));
				pdto.setPname(rs.getString("pname"));
				pdto.setPengname(rs.getString("pengname"));
				pdto.setPimg(rs.getString("pimg"));
				pdto.setPtype(rs.getString("ptype"));
				pdto.setPhometown(rs.getString("phometown"));
				pdto.setPprice(rs.getString("pprice"));
				
				odto.setPdto(pdto);
				
				odto.setOindex(rs.getInt("oindex"));
				odto.setOtotalprice(rs.getString("ototalprice"));
				odto.setOdate(rs.getString("odate"));
				odto.setOstatus(rs.getInt("ostatus"));
				odto.setOardate(rs.getString("oardate"));
				odto.setOvolume(rs.getString("ovolume"));
				
				ddto.setOdto(odto);
				
				ddto.setDnumber(rs.getString("dnumber"));
				ddto.setDname(rs.getString("dname"));
				ddto.setDemail(rs.getString("demail"));
				ddto.setDphone(rs.getString("dphone"));
				ddto.setDmsg(rs.getString("dmsg"));
				ddto.setDaddress(rs.getString("daddress"));
				ddto.setDaddressdetail(rs.getString("daddressdetail"));
				
			}
			
		} finally {
			close();
		}
		
		return ddto;
		
	} // end of public DeliveryDTO getOrderDetail(Map<String, String> paraMap) throws SQLException -----------
	
	// 좋아요 수 확인
	@Override
	public int getLikeCnt(int pindex) throws SQLException {

		int likeItCnt = 0;

		try{

			conn = ds.getConnection();

			String sql = " select NVL(count(*), 0) as count"
					   + " from LIKEIT"
					   + " where pindex = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, pindex);
			
			rs = pstmt.executeQuery();
			rs.next();
			
			likeItCnt = rs.getInt(1);
			
		}finally {
			close();
		}
		
		return likeItCnt;
	} // end of public int getLikeCnt(int pindex) throws SQLException
	// product 테이블에 제품정보 insert 하기
	@Override
	public int productInsert(ProductDTO pdto) throws SQLException {
		
	      int result = 0;
	         
	       try {
	            conn = ds.getConnection();
	            
	            String sql = " insert into product(pname, pengname, ptype, phometown, pprice, ppoint, pbody, pacid, ptannin, pacl, pdetail, pimg, pstock, pindex) "
	                       + " values(?,?,?,?,?,?,?,?,?,?,?,?,?,SEQ_PINDEX.nextval) ";
	            
	            pstmt = conn.prepareStatement(sql);
	            
	            pstmt.setString(1, pdto.getPname());
	            pstmt.setString(2, pdto.getPengname());
	            pstmt.setString(3, pdto.getPtype());    
	            pstmt.setString(4, pdto.getPhometown()); 
	            pstmt.setString(5, pdto.getPprice());    
	            pstmt.setString(6, pdto.getPpoint()); 
	            pstmt.setString(7, pdto.getPbody()); 
	            pstmt.setString(8, pdto.getPacid());
	            pstmt.setString(9, pdto.getPtannin());
	            pstmt.setString(10, pdto.getPacl());
	            pstmt.setString(11, pdto.getPdetail());
	            pstmt.setString(12, pdto.getPimg());
	            pstmt.setString(13, pdto.getPstock());
	            
	            result = pstmt.executeUpdate();
	            
	            
	       } finally {
	            close();
	       }
	         
	       return result;
	       
	}// end of public int productInsert(ProductDTO pdto) throws SQLException--------------

	
	// productDetailImg 테이블에 정보 insert 하기
	@Override
	public int productDetailInsert(Map<String, String> paraMap) throws SQLException {

		int result = 0;
        
	       try {
	            conn = ds.getConnection();
	            						   
	            String sql = " insert into productdetailimg(pdindex, pindex, pdimg) "
	                       + " values(SEQ_PDINDEX.nextval,?,?) ";
	            
	            pstmt = conn.prepareStatement(sql);
	            
	            pstmt.setString(1, paraMap.get("pindex"));
	            pstmt.setString(2, paraMap.get("pdimg"));
	            
	            result = pstmt.executeUpdate();
	            
	            
	       } finally {
	            close();
	       }
	         
	       return result;
	       
	}// end of public int productDetailInsert(Map<String, String> paraMap) throws SQLException------------

	
	// pindex 채번해오기
	@Override
	public int selectPindex(String pimg) throws SQLException {
		
		int pIndex = 0;
        
	       try {
	            conn = ds.getConnection();
	            
	            String sql = " select pindex "
	            		   + " from product "
	            		   + " where pimg = ? ";
	            
	            pstmt = conn.prepareStatement(sql);
	            
	            pstmt.setString(1, pimg);
	            
	            rs = pstmt.executeQuery();
	            
	               rs.next();
	               pIndex = rs.getInt(1);
	            
	       } finally {
	            close();
	       }
	         
	       return pIndex;
	       
	}// end of public int selectPindex(String pimg) throws SQLException ---------------

	// 물건 주문 후 재고량 수 변경
	@Override
	public boolean updatePstock(int pindex) throws SQLException {
		
		boolean result = false;
		
		try {
			
            conn = ds.getConnection();
            
            String sql = " update product set pstock = pstock - 1 where PINDEX = ? ";
            
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, String.valueOf(pindex));
            
            if(1==pstmt.executeUpdate()) {
            	result = true;
            }
            
       } finally {
            close();
       }
		
		return result;
		
	}// end of public int updatePstock(int pindex) throws SQLException
	
	
	
	
	// 관리자 회원관리 - 리뷰내역 삭제하기
	@Override
	public int delReviewAd(Map<String, String> paraMap) throws SQLException {
		
		int result1 = 0;
		int result2 = 0;
		int result3 = 0;
		int result4 = 0;
		
	       try {
	            conn = ds.getConnection();
	            
	            conn.setAutoCommit(false);
	            
	            String sql = " delete from review "
	            		   + " where to_char(rindex) = ? ";
	            
	            pstmt = conn.prepareStatement(sql);
	            
	            pstmt.setString(1, paraMap.get("rindex"));
	            
	            result1 = pstmt.executeUpdate();
	            
	            if(result1 == 1) {
	            	
	            	sql = " update member set point = point - 1000 "
	            		+ " where userid = ? ";
		            
		            pstmt = conn.prepareStatement(sql);
		            
					pstmt.setString(1, paraMap.get("userid"));
		            
		            result2 = pstmt.executeUpdate();
	            	
		            
		            if(result2 == 1) {
		            	
		            	sql = " insert into point(userid, poincome, podetail, podate) VALUES (?, -1000, '관리자 리뷰 삭제 차감', to_char(sysdate, 'yyyy-mm-dd hh24:mi:ss')) "
;
			            pstmt = conn.prepareStatement(sql);
			            
						pstmt.setString(1, paraMap.get("userid"));
			            
			            result3 = pstmt.executeUpdate();
				            
			            
		            	if(result3 == 1) {
		            		
		            		conn.commit();
			            	conn.setAutoCommit(true);
			            	result4 = 1;
		            	}
		            }
		            
	            } else {
	            	
	            	conn.rollback();
	            }
	            
	       } catch(Exception e) {
	    	   
	    	   conn.rollback();
	    	   e.printStackTrace();
	    	   
	       } finally {
	            close();
	       }
	         
	       return result4;
	       
	} // end of public int delReviewAd(String rindex) throws SQLException

	// 제품 수정하기
	@Override
	public int updateProduct(ProductDTO pdto) throws SQLException {
		
		int result = 0;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " update product "
					   + " set pname = ?, pengname = ?, ptype = ?, "
					   + "	   phometown = ?, pprice = ?, ppoint = ?, "
					   + "	   pbody = ?, pacid = ?, ptannin = ?, "
					   + "	   pacl = ?, pdetail = ?, pstock = ? "
					   + " where pindex = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, pdto.getPname());
			pstmt.setString(2, pdto.getPengname());
			pstmt.setString(3, pdto.getPtype());
			pstmt.setString(4, pdto.getPhometown());
			pstmt.setString(5, pdto.getPprice());
			pstmt.setString(6, pdto.getPpoint());
			pstmt.setString(7, pdto.getPbody());
			pstmt.setString(8, pdto.getPacid());
			pstmt.setString(9, pdto.getPtannin());
			pstmt.setString(10, pdto.getPacl());
			pstmt.setString(11, pdto.getPdetail());
			pstmt.setString(12, pdto.getPstock());
			
			pstmt.setInt(13, pdto.getPindex());
			
			result = pstmt.executeUpdate();
			
		} finally {
			close();
		}
		
		return result;
		
	} // end of public int updateProduct(ProductDTO pdto) throws SQLException -----------------

	
	// 제품타입별 판매량 수 알아오기
	@Override
	public List<Map<String, String>> chart_map_List() throws SQLException {
		
		List<Map<String, String>> chart_map_List = new ArrayList<>();
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " SELECT P.pType, nvl(sum(O.OVOLUME),0) AS Ordersum "
					   + " FROM Product P "
					   + " LEFT JOIN Orders O ON P.pindex = O.pindex "
					   + " GROUP BY P.pType ";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				String ptype =  rs.getString("pType");
				String ordersum =  rs.getString("Ordersum");
				
				Map<String, String> map = new HashMap<>();
				map.put("ptype", ptype);
				map.put("ordersum", ordersum);
				
				chart_map_List.add(map);
				
			}// end of while----------------------------
			
		} finally {
			close();
		}

		return chart_map_List;
		
	}// end of public List<Map<String, String>> chart_map_List() throws SQLException ------------

	
	
	// [제품 삭제] 제품번호에 대한 주문번호 여러 개 받아오기
	@Override
	public List<String> getOindexListByPindex(String pindex) throws SQLException {
		
		List<String> list = new ArrayList<>();
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " select oindex "
					   + " from orders "
					   + " where pindex = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pindex);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getString("oindex"));
			}
			
			
		} finally {
			close();
		}
		
		return list;
		
	} // end of public String[] getOindexByPindex(String pindex) throws SQLException -------------

	
	
	// [제품 삭제] 리뷰 삭제하기
	@Override
	public int deleteReview(String oindex) throws SQLException {
		
		int result = 0;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " delete from review "
					   + " where oindex = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, oindex);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close();
		}
		
		return result;
		
	} // end of public int deleteReview(String oindex) throws SQLException ------------

	
	
	// [제품 삭제] 배송정보 삭제하기
	@Override
	public int deleteDelivery(String oindex) throws SQLException {

		int result = 0;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " delete from delivery "
					   + " where oindex = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, oindex);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close();
		}
		
		return result;
		
	} // public int deleteDelivery(String oindex) throws SQLException ---------------

	
	
	// [제품 삭제] 주문내역 삭제하기
	@Override
	public int deleteOrders(String oindex) throws SQLException {

		int result = 0;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " delete from orders "
					   + " where oindex = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, oindex);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close();
		}
		
		return result;
		
	} // end of public int deleteOrders(String oindex) throws SQLException ------------

	
	
	// [제품 삭제] 좋아요 삭제하기
	@Override
	public int deleteLikeit(String pindex) throws SQLException {

		int result = 0;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " delete from likeit "
					   + " where pindex = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pindex);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close();
		}
		
		return result;
		
	} // end of public int deleteLikeit(String pindex) throws SQLException --------------

	
	
	// [제품 삭제] 제품상세이미지 삭제하기
	@Override
	public int deletePdimg(String pindex) throws SQLException {

		int result = 0;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " delete from productdetailimg "
					   + " where pindex = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pindex);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close();
		}
		
		return result;
		
	} // public int deletePdimg(String pindex) throws SQLException ----------------

	
	
	// 제품 삭제하기
	@Override
	public int deleteProduct(String pindex) throws SQLException {

		int result = 0;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " delete from product "
					   + " where pindex = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pindex);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close();
		}
		
		return result;
		
	} // end of public int deleteProduct(String pindex) throws SQLException ----------------

	
	
	// [제품 삭제] 주문번호에 대한 배송정보가 존재하는지 확인
	@Override
	public boolean isExistDeliveryByOindex(String oindex) throws SQLException {

		boolean isExistDelivery = false;
		
		try {
			
			conn = ds.getConnection();
			
			String sql = " select * "
					   + " from delivery "
					   + " where oindex = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, oindex);
			
			rs = pstmt.executeQuery();
			
			isExistDelivery = rs.next();
			
		} finally {
			close();
		}
		
		return isExistDelivery;
		
	} // end of public boolean isExistDeliveryByOindex(String oindex) throws SQLException --------------



}
