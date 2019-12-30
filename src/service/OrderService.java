package service;

import java.util.ArrayList;

import dto.MenuDto;

public interface OrderService {
	public ArrayList<MenuDto> getMenu();
	public boolean addOrder(String id, int menuNum, String cupSize, String syrup, int shot, int whip, int cups, int total);
	// 주문 추가
}
