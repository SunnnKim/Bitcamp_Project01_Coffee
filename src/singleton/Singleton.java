package singleton;

import controller.MemberController;
import controller.OrderController;
import dto.MemberDto;
import view.PriceView;

public class Singleton {
	
	private static Singleton s = null;
	public MemberController memCtrl = null;
	public OrderController ordCtrl = null;
	public MemberDto loginDto = null;
	public String loginId;
	
	// 메뉴창 닫기/열기
	public PriceView pv;
	
	
	// 로그인 아이디 getter / setter
	public MemberDto getLoginDto() {
		return loginDto;
	}
	public void setLoginDto(MemberDto loginDto) {
		this.loginDto = loginDto;
		if(loginDto != null) this.loginId = loginDto.getId();
		else this.loginId = "";	// 로그아웃 했을 때
	}
	
	
	private Singleton() {
		memCtrl = new MemberController();
		ordCtrl = new OrderController();
		
	}
	public static Singleton getInstance() {
		if(s == null ) s = new Singleton();
		return s;
	}
	
	public void priceView(boolean b) {
		pv = new PriceView(); 	// 윈도우 화면 설정
		pv.setVisible(b);
	}
	
}
