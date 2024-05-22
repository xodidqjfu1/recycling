package recycling.manager.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import recycling.dto.buyer.ExpRes;
import recycling.dto.manager.ResSchCnt;
import recycling.dto.seller.Exp;
import recycling.dto.seller.ExpFile;
import recycling.dto.seller.ExpSch;
import recycling.manager.service.face.SlsService;


@Controller
@RequestMapping("/manager/sls")
public class SlsController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired private SlsService slsService;
	@Autowired HttpSession session;
	
	//체험단 전체조회
	@GetMapping("/explist")
	public String expList(
			Model model
			) {
		logger.info("controller explist :[Get]");
		
		//전체 Exp 조회기능
		List<Exp> list = slsService.selectAll();
		model.addAttribute("explist", list);
		
		return "/manager/sls/explist";
	}
	
	//세부조회
	@GetMapping("/expdetail")
	public void expDetail(
				@RequestParam("expCode") String expCode
				, ExpFile expFile
				, Model model
				) {
			logger.info("contoller: expDetail[GET]");

			//expCode번호와 동일한 expfile 가져오기
			ExpFile fileimage = slsService.image(expFile);
			model.addAttribute("fileimage", fileimage);
			logger.info("expDetail fileimage:{}", fileimage );
			
	        
			//상세조회
			Exp view = slsService.selectDetail(expCode);
			model.addAttribute("view", view);
			logger.info("expDetail view:{}", view );
	
			//전체 예약 조회기능
			List<ExpSch> schList = slsService.selectSchAll(expCode);
			model.addAttribute("expSchList", schList);
			logger.info("expDetail expSchList:{}", schList );
			
			//예약 인원 조회
			List<ResSchCnt> resCnt =slsService.selectByResCnt(expCode);
			model.addAttribute("resCnt", resCnt);
			logger.info("expDetail resCnt:{}", resCnt );
			
	}
	
	//체험단 등록창
	@GetMapping("/expform")
	private void expform() {}
	
	//체험단 등록
	@PostMapping("/expform")
	public String expformProc(
			Exp exp
			, @RequestParam("schTime") List<String> schTime
			, ExpSch expSch
			, @RequestParam("file") MultipartFile file
			) {

		//test세션
		session.setAttribute("sCode", "SEL0000002");
		String sCode = (String) session.getAttribute("sCode");
		exp.setsCode(sCode);
		slsService.insert(exp, schTime, expSch, file);
		
		
		
		return "redirect:./explist";
	}
	
	//체험단 수정창
	@GetMapping("/expupdate")
	public String expUpdate(
				Exp exp
				, Model model
			) {
		logger.info("controller: expupdate[Get]");

		Exp update = slsService.expUpdateView(exp);
		model.addAttribute("update", update);
		
		//exp정보
		logger.info("controller: update{}",update );
		
		return "/manager/sls/expupdate";
	}
	
	//체험단 수정하기
	@PostMapping("/expupdate")
	public void updateProc(
			Exp exp
			){
		logger.info("controller: updateProc[Get]");
		
		slsService.expUpdateProc(exp);
	}
	
	// 체험단삭제
	@PostMapping("/explistdel")	
	public String empListDelete(@RequestParam("chBox[]") List<String> chBox) {
		logger.info("controller: empListDelete [POST]");
		
		slsService.expListDel(chBox);
		logger.info("데이터 확인 chBox : {}", chBox);
		
		return "redirect:./explist";
	}
	
	// 체험단 예약관리
	@GetMapping("/expresdetail")	
	public void expResDetail(
			Model model
			, String expCode
			, int schNo
			) {
		
		//체험단 조회
		Exp expView = slsService.expResDetail(expCode);
		model.addAttribute("exp", expView);
		
		//체험예약 조회
		ExpSch expSch = slsService.selectExpSchbySchNo(schNo);
		model.addAttribute("expSch", expSch);
		
		//체험단 예약인원조회
		List<ExpRes> resList = slsService.expResDetailRes(schNo);
		model.addAttribute("resList", resList);
		
	}
	
	// 체험단 예약 확정,취소 변경
	@PostMapping("/expresupdate")
	public String expresupdate(@RequestParam("chBox[]") List<String> chBox, @RequestParam String actionType) {
		
		slsService.expResUpdate(chBox, actionType);
		
		return "jsonView";
	}
	

	// 체험단 예약인원 예약변경창
	@GetMapping("/changeexpres")
	public String changeExpRes(
			ResSchCnt resSchCnt
			, Model model
			) {
		logger.info("controller: changeExpRes [GET]");
		
		ResSchCnt expResUpdate = slsService.changeExpRes(resSchCnt);
		model.addAttribute("update", expResUpdate);
		
		logger.info("controller: expResUpdate {}", expResUpdate);
		
		return "/manager/sls/changeexpres";
	}
	
	// 체험단 예약인원 예약변경하기
	@PostMapping("/changeexpres")
	public String changeExpResProc(
			ResSchCnt resSchCnt
			, ExpRes expRes
			){
		logger.info("resSchCnt : {}",resSchCnt);
		logger.info("resSchCnt : {}",expRes);
		
		
		slsService.changeExpResProc(resSchCnt);
		
		
		return "redirect:/manager/sls/expresdetail?expCode="+ resSchCnt.getExpCode() +"&schNo="+ resSchCnt.getSchNo();
	}
	
	// 체험단 시간 날짜 별 인원 변경
	@PostMapping("/cntchangeupdate")
	public String cntChangeUpdate(
			 ExpSch expSch
			, Model model
			) {

		//예약인원 조회
		int resCnt = slsService.getTotalResCnt(expSch);
		logger.info("expSch : {}", resCnt);

		//변경인원
		int total = expSch.getSchCnt();
		
		//예약인원보다 변경할 인원이 적을시 에러
		if(resCnt <= total) {
			//총인원 변경
			slsService.cntChangeUpdate(expSch);
			logger.info("expSch : {}", expSch);
			
			model.addAttribute("success", true);
		}else {
			model.addAttribute("success", false);
		}
		
		return "jsonView";
	}
	
	// 예약관리 삭제
	@PostMapping("/expresdetaillistdel")	
	public String expResDetailListDel(@RequestParam("chBox[]") List<String> chBox) {
		logger.info("controller: expResDetaiLlistDel [POST]");
		
		slsService.expResDetailListDel(chBox);
		logger.info("데이터 확인 chBox : {}", chBox);
		
		return "redirect:./explist";
	}
	
	//expdetail 페이지 리스트 삭제
	@PostMapping("/expdetaillistdel")	
	public String expDetailListDel(@RequestParam("chBox[]") List<String> chBox) {
		logger.info("controller: expDetaiLlistDel [POST]");
		
		slsService.expDetailListDel(chBox);
		logger.info("데이터 확인 chBox : {}", chBox);
		
		return "redirect:./explist";
	}
	
	
}
