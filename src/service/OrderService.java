package service;

import java.util.ArrayList;

import dto.MenuDto;
import dto.OrderDto;

public interface OrderService {
	public ArrayList<MenuDto> getMenu();
	public boolean addBucket(OrderDto dto);				// 장바구니 
	public boolean addOrder(String id, int menuNum, String cupSize, String syrup, int shot, int whip, int cups, int total);
	// 주문 추가
}
