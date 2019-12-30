package controller;

import java.util.ArrayList;

import dto.MenuDto;
import dto.OrderDto;
import service.OrderService;
import service.impl.OrderServiceImpl;
import view.OrderView;
import view.PriceView;

public class OrderController {
	OrderService ordService = new OrderServiceImpl();
	// 주문창 열기 
	public void orderView() {
		new OrderView();
	}
	// 디비에서 메뉴판 목록 꺼내오기 
	public ArrayList<MenuDto> getMenu(){
		return ordService.getMenu();
	}
	// 메뉴판보기 
	public void priceView() {
		new PriceView();
	}
	// 디비에 주문 추가하기
	public boolean addOrder(String id, int menuNum, String cupSize, String syrup, int shot, int whip, int cups, int total) {
		return ordService.addOrder(id, menuNum, cupSize, syrup, shot, whip, cups, total);
	}
	//
	public boolean addBucket(OrderDto dto) {
		return ordService.addBucket(dto);
	}
	
	
}
