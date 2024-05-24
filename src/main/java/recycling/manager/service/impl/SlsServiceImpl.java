package recycling.manager.service.impl;

import java.util.List;
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
import org.springframework.web.multipart.MultipartFile;

import recycling.dto.seller.Seller;
import recycling.dto.buyer.ExpRes;
import recycling.dto.seller.Exp;
import recycling.dto.seller.ExpFile;
import recycling.dto.seller.ExpSch;
import recycling.manager.dao.face.SlsDao;
import recycling.manager.service.face.SlsService;
import recycling.util.Paging;

@Service
public class SlsServiceImpl implements SlsService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SlsDao slsDao;
	
	@Autowired
	private ServletContext servletContext;

	@Override
	public List<Seller> main(Paging paging) {
		return slsDao.main(paging);
	}

	@Override
	public Paging getPaging(Paging pagingParam) {
		
		// 총 게시글 수 조회
		int totalCount = slsDao.getPaging();

		// 페이징 계산
		Paging paging = new Paging(totalCount, pagingParam.getCurPage(), pagingParam.getSearch());

		return paging;
	}
	
	@Override
	public  List<Map<String, Object>> selectBysChk() {
		return slsDao.selectBysChk();
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
	public Exp selectDetail(Exp exp) {
		logger.info("SlsService: selectDetail");

//		조회수 증감
//		slsDao.hit(exp); //관리자 미구현
		
		//세부사항 조회
		return slsDao.selectDetail(exp);
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
		logger.info("service slsUpdate[Get]");
		slsDao.expUpdateProc(exp);
	}

	//체험예약페이지 체험정보 조회
	@Override
	public Exp expResDetail(String expCode) {
		return slsDao.expResDetail(expCode);
	}

	//체험예약 페이지 예약정보 조회
	@Override
	public List<ExpRes> expResDetailRes(ExpRes expRes) {
		return slsDao.expResDetailRes(expRes);
	}

	//체험예약 리스트 전체삭제
	@Override
	public int expListDel(List<String> chBox) {
		int result = 0;
		
		for(int i = 0; i < chBox.size(); i++) {
			String expCode = chBox.get(i);
			logger.info("{}", expCode);
			
			result += slsDao.expListDel(expCode);
		}
		return result;
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
	
	@Override
	public void insert(Exp exp, ExpSch expSch, MultipartFile file) {}

}//main
