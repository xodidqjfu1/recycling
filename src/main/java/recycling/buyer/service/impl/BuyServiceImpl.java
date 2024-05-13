package recycling.buyer.service.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import recycling.buyer.dao.face.BuyDao;
import recycling.buyer.service.face.BuyService;
import recycling.dto.buyer.Buyer;
import recycling.dto.buyer.BuyerLogin;

@Service
public class BuyServiceImpl implements BuyService{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired private BuyDao buyDao;
//	@Autowired private BCryptPasswordEncoder pwEncoder;
	
	@Override
	public Buyer priProc(Buyer buyer, String bEmail2, String mPhone, String lPhone) {
		// 회원 코드
		buyer.setbCtCode("P");
		
		// 비밀번호 암호화
//		String enPw = pwEncoder.encode(buyer.getbPw());
//		buyer.setbPw(enPw);
		
		// 이메일
		buyer.setbEmail(buyer.getbEmail() + bEmail2);
		
		// 핸드폰 번호
		buyer.setbPhone(buyer.getbPhone() + mPhone + lPhone);
		
		// 광고 수신 여부
		if(buyer.getAdEmail() == null) {
			buyer.setAdEmail("N");
		}
		
		if(buyer.getAdSms() == null) {
			buyer.setAdSms("N");
		}
		
		return buyer;
	}
	
	@Override
	public int insertBuyer(Buyer buyer) {
		return buyDao.insertBuyer(buyer);
	}
	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		logger.info("buyService.loadUserByUsername()");
//		
//		BuyerLogin buyerLogin = buyDao.selectById(username);		
//
//		if(buyerLogin == null) {
//			logger.info("login Fail");
//			throw new UsernameNotFoundException("Not Found Id");
//		}
//		
//		buyerLogin = chkAuth(buyerLogin);
//		logger.info("{}", buyerLogin);
//		
//		Buyers buyer = new Buyers(buyerLogin);
//		
//		logger.info("Login buyer : {}", buyer);
//		
//		return buyer;
//	}
	
	@Override
	public BuyerLogin chkAuth(BuyerLogin buyerLogin) {
		logger.info("buyService.chkAuth()");
		
		Collection<SimpleGrantedAuthority> auth = new ArrayList<SimpleGrantedAuthority>();
		if (buyerLogin.getbOut().equals("N") && buyerLogin.getsCode() == null) {                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
			auth.add(new SimpleGrantedAuthority("ROLE_BUYER"));			
		} else if(buyerLogin.getbOut().equals("N") && buyerLogin.getsChk().equals("Y") && buyerLogin.getsOut().equals("N")) {
			auth.add(new SimpleGrantedAuthority("ROLE_SELLER"));
			auth.add(new SimpleGrantedAuthority("ROLE_BUYER"));			
		} else if (buyerLogin.getbOut().equals("Y") && buyerLogin.getsChk().equals("Y") && buyerLogin.getsOut().equals("N")) {
			auth.add(new SimpleGrantedAuthority("ROLE_SELLER"));
		} else if (buyerLogin.getbOut().equals("N") && buyerLogin.getsChk().equals("N") && buyerLogin.getsOut().equals("N")) {
			auth.add(new SimpleGrantedAuthority("ROLE_BUYER"));			
		}
		
		buyerLogin.setAuth(auth);
		
		return buyerLogin;
	}
	
	// 로그인 처리(임시)
	@Override
	public BuyerLogin selectBybIdbPw(Buyer buyer) {
		return buyDao.selectBybIdbPw(buyer);
	}
}