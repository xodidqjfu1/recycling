package recycling.buyer.service.face;

import java.util.List;

import recycling.dto.buyer.Buyer;
import recycling.dto.buyer.Cart;
import recycling.dto.buyer.CartOrder;
import recycling.dto.buyer.UpcyReview;
import recycling.dto.seller.Prd;
import recycling.dto.seller.Seller;

public interface UpcyclingService {

	/**
	 * 제품 DTO에서 리스트를 불러온다
	 * 
	 * @return
	 */
	public List<Prd> selectPrdList();

	/**
	 * 제품번호를 기준으로 불러오면서 제품 정보를 불러온다
	 * 
	 * @param prdno 제품번호
	 * @return 제품번호의 상세페이지
	 */
	public Prd selectPrd(String prdCode);
	
	/**
	 * 판매자 정보을 가져오는 코드
	 * 
	 * @param	getsCode 프라이머리키인 sCode
	 * 
	 */
	public Seller selectSeller(String getsCode);

<<<<<<< HEAD
	/**
	 * 판매자 정보 상세조회
	 * @param getbCode 구매자코드
	 * @return 판매자 상세 정보
	 */
	public Buyer selectBuyerByBCode(String getbCode);
	
	/**
	 * 판매자 거래 횟수 카운트 
	 * 
	 * @param	getsCode 프라이머리키인 sCode
	 */
	public int selectShipCnt(String getsCode);
	
=======

	/**
	 * 판매자 상세 프로필을 가져오는 코드
	 * 
	 * @param	getsCode 프라이머리키인 sCode
	 * @return	판매자 상세 정보
	 */
//	public SellerProf selectSellerProf(String sCode);
>>>>>>> main
	
	/**
	 * 개인 구매자 정보 조회
	 * 
	 * @param bId - 구매자 아이디
	 * @return 개인 구매자 정보
	 */
	public Buyer selectBuyerCode(int bCode);
	
	/**
	 * 구매
	 * 
	 * @param getbId
	 * @return
	 */
	public Buyer selectBuyerDetail(String getbId);

	/**
	 * 리뷰 DTO 로드
	 * @param prdCode 
	 * 
	 * @return
	 */
	public List<UpcyReview> selectRvwList(String prdCode);

	/**
	 * 리뷰 상세 조회
	 * @param rvwCode 리뷰번호
	 * @return 특정 리뷰번호 로드
	 */
	public UpcyReview selectRvw(String upcyCode);

	/**
	 * 리뷰 등록
	 * 
	 * @param rvwContent 리뷰 내용
	 * @param prdCode 
	 * @param buyer 작성자 로그인 정보
	 */
<<<<<<< HEAD
	public int	insertReview(UpcyReview review);


	

	
	
=======
//	public void insertReview(String rvwContent, String prdCode, Buyer buyer);
>>>>>>> main

	/**
	 * 리뷰 업데이트 메소드
	 * 
	 * @param rvwCode 리뷰코드
	 * @param rvwContent 리뷰내용
	 */
	public void updateReview(String upcyCode, String upcyContent);

	/**
	 * 리뷰 삭제 메소드
	 * 
	 * @param rvwCode 리뷰코드
	 */
	public void deleteReview(String upcyCode);

	/**
	 * 장바구니 상품 갯수 조회
	 * 
	 * @param cart - cart DTO
	 * @return - cCnt
	 */
	public Integer selectcCnt(Cart cart);

	/**
	 * 장바구니 수량 업데이트
	 * 
	 * @param cart - cart DTO
	 * @return - UPDATE 결과
	 */
	public int updatecCnt(Cart cart);
	
	/**
	 * 장바구니 추가
	 * 
	 * @param cart - cart DTO
	 * @return - INSERT 결과
	 */
	public int insertCart(Cart cart);

	/**
	 * 구매 상품 정보 조회
	 * 
	 * @param prdCode - 조회할 상품의 prdCode
	 * @return - 조회결과
	 */
	public CartOrder selectCartOrder(String prdCode);

	public int insertReview(UpcyReview review);



}