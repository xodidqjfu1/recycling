package recycling.seller.service.face;

import java.util.List;

import recycling.dto.seller.Exp;

// 상품-판매 관련 처리

public interface SellingService {

	public List<Exp> selectMyExpList();

}
