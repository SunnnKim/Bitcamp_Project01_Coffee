package dao;

import java.util.ArrayList;

import dto.MenuDto;

public interface OrderDao {
	public ArrayList<MenuDto> getMenu();	// 메뉴 목록 가져오기
	public boolean addOrder(String id, int menuNum, String cupSize, String syrup, int shot, int whip, int cups, int total);
	// 주문 추가
}
