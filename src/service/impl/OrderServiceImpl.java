package service.impl;

import java.util.ArrayList;

import dao.OrderDao;
import dao.impl.OrderDaoImpl;
import dto.MenuDto;
import service.OrderService;

public class OrderServiceImpl implements OrderService {
	OrderDao dao = new OrderDaoImpl();

	@Override
	public ArrayList<MenuDto> getMenu() {
		return dao.getMenu();
	}

	@Override
	public boolean addOrder(String id, int menuNum, String cupSize, String syrup, int shot, int whip, int cups,
	        int total) {
		return dao.addOrder(id, menuNum, cupSize, syrup, shot, whip, cups, total);
	}
	
	
}
