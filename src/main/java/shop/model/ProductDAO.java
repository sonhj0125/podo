package shop.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import cart.domain.DeliveryDTO;
import shop.domain.OrderDTO;
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
	int getTotalPage(Map<String, Object> paraMap) throws SQLException;

	// **** 페이징 처리를 한 검색 포함 상품 목록 보여주기 ****
	List<ProductDTO> selectProductPaging(Map<String, Object> paraMap) throws SQLException;
	
	// 페이징 처리한 검색 포함 상품 목록 인기순 정렬
	List<ProductDTO> selectProductPagingPopular(Map<String, Object> paraMap) throws SQLException;;

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

	// [리뷰 작성] 주문 인덱스에 대한 상품 정보 받아오기
	ProductDTO getProductByOindex(Map<String, String> paraMap) throws SQLException;

	// [리뷰 작성] 리뷰 작성하기
	int addReview(Map<String, String> paraMap) throws SQLException;

	// [리뷰 작성] oindex에 대한 리뷰가 존재하는지 확인
	boolean isExistReviewByOindex(String oindex) throws SQLException;

	// [리뷰 수정] rindex에 대한 리뷰가 존재하는지 확인하기
	ReviewDTO getReviewByRindex(Map<String, String> paraMap) throws SQLException;

	// [리뷰 수정] 리뷰 수정하기
	int updateReview(ReviewDTO rdto) throws SQLException;

	// [shop] pindex에 대한 리뷰 목록 불러오기
	List<ReviewDTO> getReviewListByPindex(int pindex) throws SQLException;

	// [주문내역조회] 회원이 주문한 상품 목록 받아오기
	List<OrderDTO> selectOrderList(String userid) throws SQLException;

	// [주문내역조회] 주문 인덱스에 대한 상품, 주문, 배송 정보 받아오기
	DeliveryDTO getOrderDetail(Map<String, String> paraMap) throws SQLException;
	
	// 좋아요 수 확인
	int getLikeCnt(int pindex) throws SQLException;
	
	// 제품번호 채번 해오기
	int getPnumOfProduct() throws SQLException;

	// 제품설명이미지 채번 해오기
	int getpdindexOfProduct() throws SQLException;

	// product 테이블에 제품정보 insert 하기
	int productInsert(ProductDTO pdto) throws SQLException;

	// productDetailImg 테이블에 정보 insert 하기
	int productDetailInsert(Map<String, String> paraMap) throws SQLException;

	// pindex 채번해오기
	int selectPindex(String pimg) throws SQLException;

	// 물건 주문 후 재고량 수 변경
	int updatePstock(int pindex) throws SQLException;

	// 제품타입별 판매량 수 알아오기
	List<Map<String, String>> chart_map_List() throws SQLException;


}
