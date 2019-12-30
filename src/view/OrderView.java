package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dto.MenuDto;
import singleton.Singleton;

public class OrderView extends JFrame implements ActionListener {
	
	JComboBox<String> menuChoice;
	
	JPanel option[];
	JLabel opLabel[];
	JRadioButton opButton[][];
	JRadioButton shortS, tall, grande;
	JRadioButton vanila, caramel, hazelnut;
	
	ButtonGroup groupRd[];
	JCheckBox addOption[];

	JLabel cupsLab;
	JTextField cupTxt;
	
	JButton showMenu, orderMenu;
	JButton showBasket,ordHistory;
	boolean openMenu=false;
	
	Singleton s;
	
	
	public OrderView() {
		super("Order");
		
		s = Singleton.getInstance();
		
		// 메인 프레임
		JPanel frame = new JPanel();
		frame.setBounds(0, 0, 660, 400);
		frame.setBackground(Color.GRAY);
		frame.setLayout(null);
		add(frame);
		
		JLabel title = new JLabel("COFFEE ORDER");
		title.setBounds(0, 10, 660, 30);
		title.setFont(new Font(null, Font.BOLD, 20));
		title.setHorizontalAlignment(JLabel.CENTER);
		frame.add(title);
		
		
		
		
		// 메뉴보기 버튼 
		showMenu = new JButton("메뉴보기");
		showMenu.setBounds(510, 50, 100, 20);
		showMenu.addActionListener(this);
		frame.add(showMenu);
		
		// 메뉴 콤보박스
		int menuNum = s.ordCtrl.getMenu().size();
		String menustr[]= new String[menuNum+1];
		ArrayList<MenuDto> list = s.ordCtrl.getMenu();
		menustr[0] = "---";
		for (int i = 1; i < menustr.length; i++) {
			menustr[i] = list.get(i-1).getName();
		}
		
		menuChoice = new JComboBox<String>(menustr);
		menuChoice.setBounds(30, 50,460, 20);
		frame.add(menuChoice);
		
		
		
		
		// 옵션 패널
		option = new JPanel[3];
		opLabel = new JLabel[3];
		
		String opStr[] = {" 사 이 즈", " 시 럽", " 기 타"};
		int opNum[] = { 3,4,2 };
		for (int i = 0; i < option.length; i++) {
			option[i] = new JPanel();
			option[i].setBounds(30+(i*200),100, 180, 200);
			option[i].setBackground(Color.white);
			option[i].setLayout(new GridLayout(5, 0 ));
			frame.add(option[i]);
			
			opLabel[i] = new JLabel(opStr[i]);
			opLabel[i].setFont(new Font(null, Font.BOLD, 20));
			option[i].add(opLabel[i]);
		}
		
		String opbtnStr[][] = {
				{"short", "Tall", "Grande"},
				{"바닐라", "카라멜", "헤이즐넛", "없음"}
		};
		addOption = new JCheckBox[2];
		addOption[0] = new JCheckBox("샷 추가");
		addOption[0].setBackground(Color.white);
		addOption[1] = new JCheckBox("휘핑크림");
		addOption[1].setBackground(Color.white);
		
		groupRd = new ButtonGroup[2];
		// 라디오 버튼 추가하기
		for (int i = 0; i < 2; i++) {
			opButton = new JRadioButton[3][opNum[i]];
			groupRd[i] = new ButtonGroup();
			for (int j = 0; j < opButton[i].length; j++) {
				opButton[i][j] = new JRadioButton(opbtnStr[i][j]);
				opButton[i][j].setSelected(true);
				option[i].add(opButton[i][j]);
				opButton[i][j].setBackground(Color.white);
				groupRd[i].add(opButton[i][j]);
			}
			
		}
		
		
		// 기타 추가사항 (체크박스)
		option[2].add(addOption[0]);
		option[2].add(addOption[1]);
		
		
		// 잔 수 
		cupTxt = new JTextField("1");
		cupTxt.setHorizontalAlignment(JTextField.CENTER);
		cupTxt.setBounds(350, 320, 35, 30);
		frame.add(cupTxt);
		cupsLab = new JLabel("잔");
		cupsLab.setBounds(390, 320, 30, 30);
		frame.add(cupsLab);
		
		
		// 주문하기 버튼
		ordHistory = new JButton("전체주문내역");
		ordHistory.setBounds(30, 320, 120, 30);
		ordHistory.addActionListener(this);
		frame.add(ordHistory);
		
		
		showBasket = new JButton("장바구니");
		showBasket.setBounds(430, 320, 100, 30);
		showBasket.addActionListener(this);
		frame.add(showBasket);
		
		
		orderMenu = new JButton("추가");
		orderMenu.setBounds(540, 320, 70, 30);
		orderMenu.addActionListener(this);
		frame.add(orderMenu);
	
		
		
		
		
		
		setVisible(true);
		setBounds(300, 100, 650, 420);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
		
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		if( btn == showMenu) {
			if(openMenu) { // 창 닫기
				s.pv.setVisible(false);
				openMenu = false;
				showMenu.setText("메뉴보기");
			}
			else {	// 메뉴 보기 
				openMenu = true;
				s.priceView(openMenu);
				showMenu.setText("창닫기");
				
			}
		}
		// 주문하기
		if( btn == orderMenu) {
			if(menuChoice.getSelectedIndex() == 0) {
				JOptionPane.showMessageDialog(null, "메뉴를 선택해주세요!");
				return;
			}
			int menuNum = menuChoice.getSelectedIndex();
			String id = s.loginId;
			String cupSize = "";
			String syrup = "";
			int shot=0;
			int whip=0;
			int cups = Integer.parseInt(cupTxt.getText());
			int price = s.ordCtrl.getMenu().get(menuNum-1).getPrice();
			
		    Enumeration<AbstractButton> enum1 = groupRd[0].getElements();
		    while(enum1.hasMoreElements()){ //hasMoreElements() Enum에 더 꺼낼 개체가 있는지
			    AbstractButton ab = enum1.nextElement();          
			    JRadioButton jb = (JRadioButton)ab;         
			    if(jb.isSelected()) {
			    	cupSize = jb.getText();
			    	if(cupSize.equals("Tall")) {
			    		price += 500;
			    	}
			    	else if(cupSize.equals("Grande")) {
			    		price += 1000;
			    	}
			    }
		    }
		   
			
			Enumeration<AbstractButton> enum2 = groupRd[1].getElements();
			while(enum2.hasMoreElements()){ //hasMoreElements() Enum에 더 꺼낼 개체가 있는지
			    AbstractButton ab = enum2.nextElement();          
			    JRadioButton jb = (JRadioButton)ab;         
			    if(jb.isSelected()) {
			    	syrup = jb.getText();
					
			    }
			}
			// 샷추가 여부 
			if(addOption[0].isSelected()) {
				shot++;
			}
			// 휘핑크림 추가?
			if(addOption[1].isSelected()) {
				whip++;
			}
			int total = price * cups;
			
			
			String str = " 주문한 메뉴 : " + menuChoice.getSelectedItem() + "\n"
					+" 사이즈 : " + cupSize + "\n";
			str += " 시럽 : " + syrup+"\n"
					+ " 추가 샷 : " + shot + "\n" + " 휘핑크림 추가 : " + whip + "\n"+
					 " 총 : " + whip + " 잔\n"
					+ " Total : " + total;
			int result = JOptionPane.showConfirmDialog(null, str);
			System.out.println("menuNum: " + menuNum);
		if(result == JOptionPane.YES_NO_OPTION) {
			boolean b = s.ordCtrl.addOrder(id, menuNum, cupSize, syrup, shot, whip, cups, total);
			if(b) JOptionPane.showMessageDialog(null, "메뉴 추가완료");
			else JOptionPane.showMessageDialog(null, "실패!");
		}
	}
		if(btn  == ordHistory) {
			// 현재까지 주문내역 보기 
			
		}

}}
