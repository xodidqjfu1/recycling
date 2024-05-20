package recycling.seller.dao.face;

import java.util.List;

import recycling.dto.buyer.Qst;
import recycling.dto.buyer.QstA;
import recycling.dto.seller.Qna;

// 판매자 문의 관련 DB

public interface QnaDao {

	/**
	 * sCode가 판매중인 prd의 문의 조회
	 * 
	 * @param sCode - 조회할 sCode
	 * @return - sCode와 연관된 Qst 객체 리스트
	 */
	public List<QstA> selecBysCode(String sCode);
	
	/**
	 * qstCode와 일치하는 Qst 조회
	 * 
	 * @param qstCode - 조회할 qstCode
	 * @return - 조회된 Qst 객체
	 */
	public Qst selectQstByqstCode(String qstCode);

	/**
	 * qna 답변 등록
	 * 
	 * @param qna - 답변 내용이 담긴 qna DTO 객체
	 * @return - INSERT 결과
	 */
	public int insertQna(Qna qna);
	
	/**
	 * qstCode와 일치하는 Qna 조회
	 * 
	 * @param qstCode - 조회할 qstCode
	 * @return - 조회된 Qna 객체
	 */
	public Qna selectQnaByqstCode(String qstCode);

	/**
	 * qnaContent Update
	 * 
	 * @param qna - UPDATE 될 내용을 가진 DTO 객체
	 * @return - UPDATE 결과
	 */
	public int updateQna(Qna qna);

	/**
	 * qnaCode가 일치하는 qna 삭제
	 * 
	 * @param qnaCode - 삭제하는 qnaCode 
	 * @return - DELETE 결과
	 */
	public int deleteQna(String qnaCode);

}
