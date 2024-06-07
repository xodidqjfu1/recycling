package recycling.buyer.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import recycling.buyer.dao.face.RecyclingDao;
import recycling.buyer.service.face.RecyclingService;
import recycling.dto.seller.Seller;
import recycling.dto.buyer.Buyer;
import recycling.dto.buyer.Rcy;
import recycling.dto.seller.Prd;
import recycling.dto.seller.PrdFile;

@Service
@Transactional
public class RecyclingServiceImpl implements RecyclingService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired private RecyclingDao recyclingDao;
	
	@Override
	public List<Seller> findSeller() {
		return recyclingDao.findSeller();
	}
	
	@Override
    public List<Prd> findRcyBySellerCode(String sCode) {
        return recyclingDao.findRcyBySellerCode(sCode);
    }
	
	@Override
	public List<Prd> selectPrdList() {
		
		List<Prd> prdList = recyclingDao.selectPrdList();
		logger.info("selectPrdList() - prdList size: {}", prdList.size());
		for(Prd prd : prdList) {
	        logger.info("selectPrdList() - Prd: {}", prd);
	    }
		
		return prdList;
	}
	
	@Override
	public List<Prd> selectLatestList() {
		return recyclingDao.selectLatestList();
	}

	@Override
	public List<Prd> selectHitList() {
		return recyclingDao.selectHitList();
	}

    @Override
    public List<String> selectPrdImageThum(String prdCode) {
        return recyclingDao.selectPrdImageThum(prdCode);
    }

    @Override
    public List<String> selectLatestPrdImageThum(String prdCode) {
        return recyclingDao.selectLatestPrdImageThum(prdCode);
    }

    @Override
    public List<String> selectHitPrdImageThum(String prdCode) {
        return recyclingDao.selectHitPrdImageThum(prdCode);
    }
    
	@Override
	public List<String> selectPrdImageDetail(String prdCode) {
		return recyclingDao.selectPrdImageDetail(prdCode);
	}


	@Override
	public Prd view(String prdCode) {
		
		Prd prd = recyclingDao.selectPrd(prdCode);
		
		if (prd != null) {
            logger.info("viewByPrdCode() - Prd found: {}", prd);
        } else {
            logger.info("viewByPrdCode() - No Prd found with prdCode: {}", prdCode);
        }
		
		return prd;
	}

	@Override
	public Seller getSeller(String sCode) {
		return recyclingDao.selectSellerProfByCode(sCode);
	}

	public Seller selectSeller(String getsCode) {
		return recyclingDao.selectSeller(getsCode);
	}
	
	@Override
	public Buyer selectBuyerByBCode(String getbCode) {
		return recyclingDao.selectBuyerByBCode(getbCode);
	}

	@Override
	public int selectShipCnt(String getsCode) {
		return recyclingDao.selectShipCnt(getsCode);
	}
	
	@Override
	public List<Map<String, Object>> selectQnaList(String prdCode) {
	    
	    List<Map<String, Object>> qnaList = recyclingDao.selectQnaList(prdCode);
	    if (qnaList != null && !qnaList.isEmpty()) {
	        logger.info("selectQnaList() - qna list found for product code: {}", prdCode);
	        for (Map<String, Object> qna : qnaList) {
	            String bCode = (String) qna.get("B_CODE");
	            Buyer buyer = recyclingDao.selectBuyerByBCode(bCode);
	            
	            if (buyer != null) {
	                qna.put("B_NAME", buyer.getbName());
	            } else {
	                qna.put("B_NAME", "Unknown");
	            }
	            
	            logger.info("selectQnaList() - qna: {}", qna);
	        }
	    } else {
	        logger.info("selectQnaList() - No QnA found for product code: {}", prdCode);
	    }
	    
	    return qnaList; // 수정된 qnaList를 반환
	}

	
	@Override
	public Buyer selectBuyerDetail(String bId) {
		return recyclingDao.selectBuyerBybId(bId);
	}

	@Override
	public int insertRcy(Rcy rcy) {
		logger.info("Inserting Rcy: {}", rcy);
		return recyclingDao.insertRcy(rcy);
	}







}

