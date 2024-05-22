package shop.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import shop.domain.ProductDTO;
import shop.domain.ReviewDTO;

public interface ProductDAO {

	// 제품 리스트 뽑아오기 DESC
	List<ProductDTO> listReadDesc() throws SQLException;

	// 제품 하나 뽑아오기
	ProductDTO getproduct(int pindex) throws SQLException;

	// Search창 와인 검색
	List<ProductDTO> searchWineName(String searchWord) throws SQLException;
	
	// Search창 와인 영문 검색
	List<ProductDTO> searchWineEngName(String searchWord) throws SQLException;
	
	// 페이징 처리를 위해 검색이 있는 또는 검색이 없는 상품에 대한 총 페이지 수 알아오기
	int getTotalPage(String[] ptype_arr, Map<String, String> paraMap) throws SQLException;

	// **** 페이징 처리를 한 검색 포함 상품 목록 보여주기 ****
	List<ProductDTO> selectProductPaging(String[] ptype_arr, Map<String, String> paraMap) throws SQLException;
	
	// 페이징 처리한 검색 포함 상품 목록 인기순 정렬
	List<ProductDTO> selectProductPagingPopular(String[] ptype_arr, Map<String, String> paraMap) throws SQLException;;

	// 좋아요
	int setLike(Map<String, String> paraMap) throws SQLException;

	// 유저의 좋아요 여부 판단
	boolean isLike(Map<String, String> paraMap) throws SQLException;

	// 좋아요 제거
	int setunlike(Map<String, String> paraMap) throws SQLException;
	
	// ** product 이미지의 상세정보 가져오기 **
	String getProductDetailImg(int pindex) throws SQLException;

	// 인기 품목 리스트 뽑아오기 DESC
	List<ProductDTO> listPopReadDesc() throws SQLException;

	// [리뷰 관리] 회원이 주문한 상품 중 배송완료인 상품 목록 띄우기
	List<ReviewDTO> selectProductReviewList(String userid) throws SQLException;
	
}
