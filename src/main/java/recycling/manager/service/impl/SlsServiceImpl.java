package recycling.manager.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import recycling.dto.buyer.ExpRes;
import recycling.dto.manager.ResSchCnt;
import recycling.dto.seller.Exp;
import recycling.dto.seller.ExpFile;
import recycling.dto.seller.ExpSch;
import recycling.dto.seller.Seller;
import recycling.manager.dao.face.SlsDao;
import recycling.manager.service.face.SlsService;
import recycling.util.Paging;
import recycling.util.PagingAndCtg;

@Service
@Transactional
public class SlsServiceImpl implements SlsService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired private ServletContext servletContext;
	@Autowired private SlsDao slsDao;
	
	@Override
	public List<Seller> main(PagingAndCtg upPaging) {
		return slsDao.main(upPaging);
	}

	@Override
	public int getPaging(PagingAndCtg upPaging) {
		return slsDao.getPaging(upPaging);
	}
	
	//전체조회
	@Override
	public List<Map<String, Object>> selectBysChk(PagingAndCtg paging) {
		return slsDao.selectBysChk(paging);
	}
	
	@Override
	public int selectCntSeller() {
		return slsDao.selectCntSeller();
	}
	
	@Override
	public String selectBysCode(String sCode) {
		return slsDao.selectBysCode(sCode);
	}
	
	@Override
	public Map<String, Object> selectPriSeller(String bCode) {
		return slsDao.selectPriSeller(bCode);
	}
	
	@Override
	public Map<String, Object> selectCmpSeller(String bCode) {
		return slsDao.selectCmpSeller(bCode);
	}
	
	@Override
	public int updateSelOut(String sCode) {
		return slsDao.updateSelOut(sCode);
	}
	
	@Override
	public int updateSelChk(Seller seller) {
		return slsDao.updateSelChk(seller);
	}
	
	@Override
	public int selectCntRpt(String sCode) {
		return slsDao.selectCntRpt(sCode);
	}
	
	@Override
	public int selectCntOrd(String sCode) {
		return slsDao.selectCntOrd(sCode);
	}
	
	//전체조회
	@Override
	public List<Exp> selectAll() {
		logger.info("SlsService: selectAll");
		
		return slsDao.selectAll();
	}

	//체험일정 선택조회
	@Override
	public List<ExpSch> selectSchAll(String expCode) {
		return slsDao.selectSchAll(expCode);
	}
	
	//세부사항 조회
	@Override
	public Exp selectDetail(String expCode) {
		logger.info("SlsService: selectDetail");

//		조회수 증감
//		slsDao.hit(exp); //관리자 미구현
		
		//세부사항 조회
		return slsDao.selectDetail(expCode);
	}

	//글쓰기
	@Override
	public void insert(Exp exp, List<String> schTime, ExpSch expSch, MultipartFile file) {
		//게시판 글쓰기
		slsDao.insert(exp);
		
		//expCode에 exp_code만 가져오기
		String expCode = exp.getExpCode();
		
		//체험 일정등록 체험코드 가져오기[반복]
		for(String time : schTime) {
			expSch.setExpCode(expCode);
			expSch.setSchTime(time);
			slsDao.expschUp(expSch);
		}
		
	//		if( file.getSize() <= 0 ) {
	//			logger.info("파일의 크기가 0, 처리 중단!");
	//			
	//			//파일 처리 메소드 filesave() 중단
	//			return;
	//		}
			
			//파일이 저장될 경로 - RealPath
			String storedPath = servletContext.getRealPath("upload");
			logger.info("storedPath:{}", storedPath);
			
			//upload폴더가 존재하지 않으면 생성하기
			File storedFolder = new File(storedPath);
			storedFolder.mkdir();
					
			//업로드된 파일이 저장될 이름
			String storedName = null;
			
			//저장될 파일 객체
			File dest = null;
			
			//저장될 파일명이 중복되지 않도록 반복
			do {
				storedName = file.getOriginalFilename(); //원본 파일명
				storedName += UUID.randomUUID().toString().split("-")[4]; //UUID추가
				logger.info("storedName : {}", storedName);
				
				dest = new File( storedFolder, storedName );
			} while( dest.exists() );
			//----------------------------------------------------------------
				
			try {
				//업로드된 임시 파일을 upload 폴더로 옮기기
				file.transferTo(dest);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
				
			//----------------------------------------------------------------
			
			//DB에 기록하기
			ExpFile expFile = new ExpFile();
			
			expFile.setOriginName( file.getOriginalFilename() );
			expFile.setStoredName( storedName );
			

			// 파일업로드 체험코드 가져오기
			expFile.setExpCode(expCode);
			
			//test 데이터
			expFile.setCtPflNo(610);
			
			//파일 업로드
			slsDao.fileup(expFile);
	}

	//체험 수정항목 조회
	@Override
	public Exp expUpdateView(Exp exp) {
		logger.info("service expUpdateView[Get]");
		return slsDao.expUpdateView(exp);
	}

	//체험 수정하기
	@Override
	public void expUpdateProc(Exp exp) {
	    logger.info("service: expUpdateProc");
	    slsDao.expUpdateProc(exp);
	}

	//체험예약페이지 체험정보 조회
	@Override
	public Exp expResDetail(String expCode) {
		return slsDao.expResDetail(expCode);
	}

	//체험예약 페이지 예약정보 조회
	@Override
	public List<ExpRes> expResDetailRes(int schNo) {
		return slsDao.expResDetailRes(schNo);
	}

	//체험단 리뷰 파일삭제
	@Override
	public int expReviewListDel(String expCode) {
		return slsDao.expReviewListDel(expCode);
	}
	
	//체험 리스트 파일삭제
	@Override
	public int expFileListDel(String expCode) {
		return slsDao.expFileListDel(expCode);
	}

	//체험 리스트 스케줄삭제
	@Override
	public int expSchListDel(String expCode) {

		return slsDao.expSchListDel(expCode);
	}
	//체험 리스트 전체삭제
	@Override
	public int expListDel(String expCode) {
	    return slsDao.expListDel(expCode);
	}
	
	
	//디테일부분 파일 조회
	@Override
	public ExpFile image(ExpFile expFile) {
		return slsDao.image(expFile);
	}

	//예약 버튼에 따른 상태변경
		@Override
		public int expResUpdate(List<String> chBox, String actionType) {

			int result = 0;
			
			for(int i = 0; i < chBox.size(); i++) {
				String resCode = chBox.get(i);
				
		        if ("complete".equals(actionType)) {
		        	 // 예약완료 메서드 호출
		            result += slsDao.expResCnf(resCode);
		   
		        } else if ("cancel".equals(actionType)) {
		        
		        	// 예약취소 메서드 호출
		            result += slsDao.expResCnl(resCode); 
		        }
			}
			
			return result;
			
		}

	//체험 예약조회
	@Override
	public ExpSch selectExpSchbySchNo(int schNo) {
		return slsDao.selectExpSchbySchNo(schNo);
	}

	//체험 예약,인원 조인
	@Override
	public List<ResSchCnt> selectByResCnt(String expCode) {
		logger.info("ResSchCnt : service[Get]");
		
		
		return slsDao.selectByResCnt(expCode);
		
	}

	//체험단 예약인원 예약변경창
	@Override
	public ResSchCnt changeExpRes(ResSchCnt resSchCnt) {
		return slsDao.changeExpRes(resSchCnt);
	}

	//체험단 예약인원
	@Override
	public List<ExpSch> changeExpSch() {
		return slsDao.changeExpSch();
	}
	
	@Override
	public void changeExpResProc(ResSchCnt resSchCnt) {
		slsDao.changeExpResProc(resSchCnt);
	}

	//인원변경
	@Override
	public int cntChangeUpdate(ExpSch expSch) {
		
		return slsDao.cntChangeUpdate(expSch);
	}

	@Override
	public int getTotalResCnt(ExpSch expSch) {
		return slsDao.getTotalResCnt(expSch);
	}

	@Override
	public int expResDetailListDel(List<String> chBox) {
		int result = 0;
		
		for(int i = 0; i < chBox.size(); i++) {
			String resCode = chBox.get(i);
			logger.info("{}", resCode);
			
			result += slsDao.expResDetailListDel(resCode);
		}
		return result;
	}

	//expdetail페이지 삭제기능
	@Override
	public int expDetailListDel(List<String> chBox) {

		int res = 0;
			
			for(int i = 0; i < chBox.size(); i++) {
				String schNo = chBox.get(i);
				
				int resCnt = slsDao.getResCntBySchNo(schNo);
				
				if (resCnt != 0) {
					//내부에 인원이 존재하는 경우 삭제 불가능	        		
					return -1;
				}
		        //예약 내부에 인원이 존재할경우 삭제수행
		        res += slsDao.expDetailListDel(schNo);
		        
			}//for
			return res;
	}	
	//판매자 기업조회기능
	@Override
	public List<Map<String, Object>> sellerSelect(String getbCode) {
		return slsDao.sellerSelect(getbCode);
	}

	//업데이트 프로필 조회하기
	@Override
	public ExpFile expUpdateProfile(ExpFile expFile) {
		logger.info("service expcode: {}",expFile);
		return slsDao.expUpdateProfile(expFile);
	}

	//업데이트 파일 조회하기
	@Override
	public List<ExpFile> expUpdateFile(ExpFile expFile) {
		return slsDao.expUpdateFile(expFile);
	}

	//업데이트 파일 인서트
	@Override
	public ExpFile updateFile(MultipartFile expfileUpdate, Exp exp) {
		if(expfileUpdate.getSize() <= 0) {
			logger.info("파일 없음");
			
			return null;
		}
		
		String storedPath = servletContext.getRealPath("upload");
		File storedFolder = new File(storedPath);
		storedFolder.mkdir();
		String storedName = null;
		File dest = null;
		do {
			storedName = expfileUpdate.getOriginalFilename(); // 원본 파일명
			storedName += UUID.randomUUID().toString().split("-")[4]; // UUID 추가
			dest = new File(storedFolder, storedName);			
		} while(dest.exists());
		
		try {
			expfileUpdate.transferTo(dest);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		ExpFile expFile = new ExpFile();
		expFile.setExpCode(expFile.getExpCode());
		expFile.setOriginName(expfileUpdate.getOriginalFilename());
		expFile.setStoredName(storedName);
		
		return expFile;
	}
	
	//멀티 업데이트 파일 가져오기
	@Override
	public void updateMutiFile(List<MultipartFile> expMultiFileUpdate, Exp exp) {
		logger.info("{}",expMultiFileUpdate);
		
		
	    String storedPath = servletContext.getRealPath("upload");
	    File storedFolder = new File(storedPath);
	    if (!storedFolder.exists()) {
	        storedFolder.mkdir();
	    }
	    String expCode = exp.getExpCode();
	    logger.info("expCode 22222{}",expCode);
	    List<ExpFile> expFiles = new ArrayList<>();
	    logger.info("1111111111service : {}", expFiles);
	    

	    // 메인 파일 저장
	    for (MultipartFile mainFile : expMultiFileUpdate) {
	        String storedName = saveFile(mainFile, storedFolder);
	        if (storedName != null) {
	        	logger.info("service : {}", mainFile);
	            ExpFile expFile = new ExpFile();
	            expFile.setOriginName(mainFile.getOriginalFilename());
	            expFile.setStoredName(storedName);
	            expFile.setExpCode(expCode);
	            expFile.setCtPflNo(610);
	            expFiles.add(expFile);
	            logger.info("service : {}", expFile);
	        }
	    }
	    
	    for (ExpFile expFile : expFiles) {
	        slsDao.expUpdateMultiFileProc(expFile);
	        logger.info("exp fileup service : {}", expFile);
	    }
	    
	}

	
	//업데이트 수정하기
	@Override
	public void expUpdatefileProc(ExpFile expfile) {
		slsDao.expUpdatefileProc(expfile);
	}

	//파일 저장소
	private String saveFile(MultipartFile file, File storedFolder) {
	    logger.info("SERVICE : SAVEFILE[GET]");
		if (file.isEmpty()) {
	        return null;
	    }

	    String storedName;
	    File dest;
	    do {
	        storedName = UUID.randomUUID().toString().split("-")[4] + "_" + file.getOriginalFilename();
	        dest = new File(storedFolder, storedName);
	    } while (dest.exists());

	    try {
	        file.transferTo(dest);
	        return storedName;
	    } catch (IllegalStateException | IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	//판매자 상품 조회
	@Override
	public List<SellerOrderJoin> selectAllPrdList(PagingAndCtg upPaging) {
		return slsDao.selectAllPrdList(upPaging);
	}

	//판매자 상품 조회 페이징
	@Override
	public int selectCntAllPrdList(PagingAndCtg upPaging) {
		return slsDao.selectCntAllPrdList(upPaging);
	}
	
	//판매자 판매 조회
	@Override
	public List<SellerOrderJoin> selectAllSellList(PagingAndCtg unPaging) {
		return slsDao.selectAllSellList(unPaging);
	}

	//판매자 판매조회 페이징
	@Override
	public int selectCntAllSellList(PagingAndCtg unPaging) {
		return slsDao.selectCntAllSellList(unPaging);
	}
	//판매자 정보 조회
	@Override
	public List<Map<String, Object>> sellerAllSeller(String getsCode) {
		return slsDao.sellerAllSeller(getsCode);
	}

	//판매자 상품세부조회
	@Override
	public Prd selectDetailPrd(String prdCode) {
		return slsDao.selectDetailPrd(prdCode);
	}

	//판매자 상품업데이트
	@Override
	public int slsPrdUpdate(Prd prd) {
		return slsDao.slsPrdUpdate(prd);
	}

	//판매자 상품삭제
	@Override
	public int slsDeletePrd(String prdCode) {
		return slsDao.slsDeletePrd(prdCode);
	}

	@Override
	public MyOrder orderdetailPrd(String orddtCode) {
		return slsDao.orderdetailPrd(orddtCode);
	}

}//main
