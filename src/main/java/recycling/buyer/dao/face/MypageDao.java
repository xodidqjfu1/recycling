package recycling.buyer.dao.face;

import java.util.List;
import java.util.Map;

import recycling.dto.buyer.Buyer;
import recycling.dto.buyer.BuyerLogin;
import recycling.dto.buyer.Oto;
import recycling.dto.buyer.OtoCt;
import recycling.dto.buyer.OtoFile;
import recycling.util.PagingAndCtg;

// 마이페이지 - 내 게시물 관련 DB 처리

public interface MypageDao {
	
	/**
	 * 로그인한 구매자 문의글 전체 조회
	 * 
	 * @param paging - 구매자 정보
	 * @return 문의글 리스트
	 */
	public List<Map<String, Object>> selectQnaBybCode(PagingAndCtg paging);
	
	/**
	 * 로그인한 구매자 후기글 전체 조회
	 * 
	 * @param paging - 구매자 정보
	 * @return 후기글 리스트
	 */
	public List<Map<String, Object>> selectRvwBybCode(PagingAndCtg paging);
	
	/**
	 * 문의글 페이징 처리
	 * 
	 * @param paging - 페이지 및 검색어/카테고리/사용자 정보
	 * @return 총 페이지 개수
	 */
	public int selectCntQna(PagingAndCtg upPaging);
	
	/**
	 * 후기 페이징 처리
	 * 
	 * @param paging - 페이지 및 검색어/카테고리/사용자 정보
	 * @return 총 페이지 개수
	 */
	public int selectCntRvw(PagingAndCtg unPaging);

	/**
	 * 1:1 문의 상세 조회
	 * 
	 * @param otoCode - 1:1 문의 코드
	 * @return 1:1 문의 상세 내용
	 */
	public Oto getByOtoCode(String otoCode);
	
	/**
	 * 1:1 문의 분류
	 * 
	 * @return 1:1 문의 분류 리스트
	 */
	public List<OtoCt> getAllOct();
	
	/**
	 * 1:1 문의 첨부 파일 리스트 조회
	 * 
	 * @param otoCode - 1:1 문의 코드
	 * @return 조회된 첨부 파일 리스트
	 */
	public List<OtoFile> getOtoFiles(String otoCode);

	/**
	 * 1:1 문의 상세페이지 접속 시 조회수 증가
	 * 
	 * @param otoCode - 1:1 문의 코드
	 */
	public void updateOtoHit(String otoCode);

	/**
	 * 
	 * 
	 * @param bId
	 * @return
	 */
	public Buyer getBuyerDetail(String bId);

	/**
	 * 1:1 문의 게시글 작성을 위한 객체
	 * 
	 * @param oto - 1:1 문의글
	 * @return
	 */
	public int insertOto(Oto oto);

	/**
	 * 파일 정보 DB에 삽입
	 * 
	 * @param otoFile - 파일 정보
	 * @return 0: 실패, 1: 성공
	 */
	public int insertOtoFiles(OtoFile otoFile);
	
	/**
	 * 1:1 문의 삭제
	 * 
	 * @param otoCode - 1:1 문의 코드
	 * @return 삭제된 행의 수
	 */
	public int deleteOto(String otoCode);

}