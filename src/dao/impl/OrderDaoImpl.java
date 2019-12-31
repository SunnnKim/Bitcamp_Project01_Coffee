package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.OrderDao;
import db.DBConnection;
import dto.MenuDto;
import dto.OrderDto;
import singleton.Singleton;
import view.BucketView;
import view.HistoryView;

public class OrderDaoImpl implements OrderDao {
/*
CREATE TABLE C_ORDER(
	SEQ NUMBER(8) PRIMARY KEY,
	ID VARCHAR2(50) NOT NULL,
    MENUNAME VARCHAR2(500)NOT NULL,
	CUPSIZE VARCHAR2(50) NOT NULL,
	SYRUP VARCHAR2(50),
	SHOT NUMBER(8),
	WHIP NUMBER(8),
	CUPS NUMBER(8) NOT NULL,
	TOTAL NUMBER(20) NOT NULL,
    ODATE DATE NOT NULL
);
 */
	
	// 메뉴 불러오기
	@Override
	public ArrayList<MenuDto> getMenu() {

		String sql =  " SELECT * "
					+ " FROM COFFEEMENU "
					+ " ORDER BY MENUNUM ASC "; 
		System.out.println("sql : " + sql);
		
		
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ArrayList<MenuDto> list = null;
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			list = new ArrayList<MenuDto>();
			
			while(rs.next()) {
				MenuDto dto = new MenuDto();
				dto.setSeq(rs.getInt(1));
				dto.setName(rs.getString(2));
				dto.setPrice(rs.getInt(3));
				
				list.add(dto);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	// 주문 추가하기
	@Override
	public boolean addOrder(OrderDto dto) {
		
		String sql = " INSERT INTO C_ORDER ( SEQ, ID, MENUNAME, CUPSIZE, SYRUP, SHOT, WHIP, CUPS, TOTAL, ODATE ) "
					+" VALUES ( SEQ_ORDER.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE ) ";
		
		String id = dto.getId();
		String menuName = dto.getMenuName();
		String cupSize = dto.getCupSize();
		String syrup = dto.getSyrup();
		int shot = dto.getShot();
		int whip = dto.getWhip();
		int cups = dto.getCups();
		int total = dto.getTotalPrice();
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		boolean b = false;
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, id);
			psmt.setString(2, menuName);
			psmt.setString(3, cupSize);
			psmt.setString(4, syrup);
			psmt.setInt(5, shot);
			psmt.setInt(6, whip);
			psmt.setInt(7, cups);
			psmt.setInt(8, total);
			 
			
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				b= true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return b;
	
	}
	// 장바구니에 추가하기
	@Override
	public boolean addBucket(OrderDto dto) {
		Singleton s = Singleton.getInstance();
		if(dto!=null) {
			s.bucketList.add(dto);
			return true;
		}
		return false;
	}
	// 장바구니 뷰 열기
	@Override
	public void bucketView() {
		new BucketView();
	}
	// 전체주문 뷰 열기
	@Override
	public void HistoryView() {
		new HistoryView();
	}
	// 모든 주문 내역 가져오기
	@Override
	public ArrayList<OrderDto> getAllOrder(String id) {
		String sql =  " SELECT SEQ, MENUNAME, CUPSIZE, SYRUP, SHOT, WHIP, CUPS, TOTAL, ODATE  "
				+ " FROM C_ORDER "
				+ " WHERE ID = ? "
				+ " ORDER BY ODATE DESC "; 
		System.out.println("sql : " + sql);
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		ArrayList<OrderDto> list = null;
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			
			rs = psmt.executeQuery();
			list = new ArrayList<OrderDto>();
			
			while(rs.next()) {
				OrderDto dto = new OrderDto();
				// SEQ, MENUNAME, CUPSIZE, SYRUP, SHOT, WHIP, CUPS, TOTAL, ODATE 
				dto.setSequence(rs.getInt(1));
				dto.setMenuName(rs.getString(2));
				dto.setCupSize(rs.getString(3));
				dto.setSyrup(rs.getString(4));
				dto.setShot(rs.getInt(5));
				dto.setWhip(rs.getInt(6));
				dto.setCups(rs.getInt(7));
				dto.setTotalPrice(rs.getInt(8));
				dto.setoDate(rs.getString(9));
				
				list.add(dto);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
}
