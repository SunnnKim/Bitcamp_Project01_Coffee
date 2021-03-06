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
	// 메뉴판 뷰 열기 
	public void priceView() {
		new PriceView();
	}
	// 장바구니 뷰 열기
	public void bucketView() {
		ordService.bucketView();
	}
	
	
	// 디비에서 메뉴판 목록 꺼내오기 
	public ArrayList<MenuDto> getMenu(){
		return ordService.getMenu();
	}
	
	// 디비에 주문 추가하기
	public boolean addOrder(OrderDto dto) {
		return ordService.addOrder(dto);
	}
	// 장바구니에 주문 추가하기
	public boolean addBucket(OrderDto dto) {
		return ordService.addBucket(dto);
	}
	// 전체 주문내역뷰 오픈
	public void HistoryView() {
		ordService.HistoryView();
	}
	// 전체 주문내역 가져오기
	public ArrayList<OrderDto> getAllOrder(String id) {
		return ordService.getAllOrder(id);
	}
	
	
}
