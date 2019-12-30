package dao;

import java.util.ArrayList;

import dto.MenuDto;
import dto.OrderDto;

public interface OrderDao {
	public ArrayList<MenuDto> getMenu();	// 메뉴 목록 가져오기
	public boolean addBucket(OrderDto dto);				// 장바구니 
	public boolean addOrder(String id, int menuNum, String cupSize, String syrup, int shot, int whip, int cups, int total);
	// 디비에 주문 추가 (전체주문)
}
