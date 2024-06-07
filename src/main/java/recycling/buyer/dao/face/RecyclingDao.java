package recycling.buyer.dao.face;

import java.util.List;
import java.util.Map;

import recycling.dto.buyer.Buyer;
import recycling.dto.buyer.Rcy;
import recycling.dto.seller.Prd;
import recycling.dto.seller.PrdFile;

// 재활용품 관련 DB 처리

import recycling.dto.seller.Seller;

// 재활용품 관련 DB 처리
public interface RecyclingDao {

	/**
	 * 판매자 찾기
	 * 
	 * @param sCode
	 * @return
	 */
	public List<Seller> findSeller();
	
<<<<<<< HEAD
=======
	/**
	 * 판매자 코드로 재활용품 조회
	 * 
	 * @param sCode
	 * @return
	 */
	public List<Prd> findRcyBySellerCode(String sCode);
	
>>>>>>> main
	public List<Prd> selectPrdList();
	public List<Prd> selectLatestList();
	public List<Prd> selectHitList();
	
	public List<PrdFile> selectPrdImage();
	public List<PrdFile> selectLatestPrdImage();
	public List<PrdFile> selectHitPrdImage();

	public Prd selectPrd(String prdCode);

	public Seller selectSellerProfByCode(String sCode);

	public Seller selectSeller(String getsCode);
	
//	public SellerProf selectSellerProfByCode(String sCode);

	public Buyer selectBuyerByBCode(String getbCode);

	public int selectShipCnt(String getsCode);
	
	public List<Map<String, Object>> selectQnaList(String prdCode);
	
	/**
	 * 후기 작성자 이름 불러오기
	 * @param bCode 구매자 코드
	 * @return bName (구매자 이름)
	 */

	public Buyer selectBuyerBybId(String bId);
	
<<<<<<< HEAD
	
	public int insertRcy(Rcy rcy);



=======
	public int insertOto(Oto oto);
>>>>>>> main

//	public SellerQST selectSellerQST(String qstCode);

//	public int insertSellerQST(SellerQST sellerQST);

//	public int updateSellerQST(SellerQST sellerQST);

	public int deleteSellerQST(String qstCode);

//	public List<SellerAns> selectSellerAnswers(String qstCode);

//	public int insertSellerAnswer(SellerAns sellerAns);

//	public int updateSellerAnswer(SellerAns sellerAns);

	public int deleteSellerAnswer(String qnaCode);

}