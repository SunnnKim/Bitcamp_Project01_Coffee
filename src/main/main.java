package main;

import db.DBConnection;
import singleton.Singleton;

public class main {
	public static void main(String[] args) {
		DBConnection.initConnection();
		Singleton s = Singleton.getInstance();
		s.memCtrl.loginView();
//		s.ordCtrl.orderView();
//		new PriceView(s.ordCtrl.getMenu());
	
	}

}
