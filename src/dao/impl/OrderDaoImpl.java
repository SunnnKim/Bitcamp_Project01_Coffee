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

public class OrderDaoImpl implements OrderDao {
/*
 CREATE TABLE C_ORDER(
	SEQ NUMBER(8) PRIMARY KEY,
	ID VARCHAR2(50) NOT NULL,
	MENUNUM NUMBER(8) NOT NULL,
	CUPSIZE VARCHAR2(50) NOT NULL,
	SYRUP VARCHAR2(50),
	SHOT NUMBER(8),
	WHIP VARCHAR(10),
	CUPS NUMBER(8) NOT NULL,
	TOTAL NUMBER(20) NOT NULL
); 
 */
	
	// 메뉴 불러오기
	@Override
	public ArrayList<MenuDto> getMenu() {

		String sql =  " SELECT * "
					+ " FROM COFFEEMENU "; 
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
	// 메뉴 추가하기
	@Override
	public boolean addOrder(String id, int menuNum, String cupSize, String syrup, int shot, int whip,
	        int cups, int total) {
		
		String sql = " INSERT INTO C_ORDER ( SEQ, ID, MENUNUM, CUPSIZE, SYRUP, SHOT, WHIP, CUPS, TOTAL, ODATE ) "
					+" VALUES ( SEQ_ORDER.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, SYSDATE ) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		boolean b = false;
		
		try {
			conn = DBConnection.getConnection();
			psmt = conn.prepareStatement(sql);
			
			psmt.setString(1, id);
			psmt.setInt(2, menuNum);
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
	
	
}
