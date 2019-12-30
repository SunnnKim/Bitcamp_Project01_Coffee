package view;

import java.awt.Color;import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.CellRendererPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import dto.MenuDto;
import dto.OrderDto;
import singleton.Singleton;

public class BucketView extends JFrame implements ActionListener {
	Singleton s;
	private JLabel title;
	private String columnNames[] = {
			"Espresso Beverages", "시럽", "사이즈" , "샷추가", "휘핑크림","잔", "총액"
	};
	
	private Object rowData[][];
	private DefaultTableModel model;	// 테이블의 넓이, 폭 등을 설정하기 위해 필요함
	private JTable jtable;
	private JScrollPane jscrPane;
	private ArrayList<OrderDto> list;
	
	public BucketView() {
		super("BucketList");
		s = Singleton.getInstance();
		
		JPanel frame = new JPanel();
		frame.setBounds(0, 0, 640, 400);
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setLayout(null);
		add(frame);
		
		title = new JLabel("장 바 구 니");
		title.setBounds(0, 10, 640, 30);
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setFont(new Font(null, Font.BOLD, 30));
		frame.add(title);
		
		s = Singleton.getInstance();
		this.list = s.bucketList;
		rowData = new Object[list.size()][7];
		// list에서 테이블로 데이터를 삽입하기 위한 처리
		for (int i = 0; i < list.size(); i++) {
			OrderDto dto = list.get(i);
			rowData[i][0] = dto.getMenuName(); 	// 메뉴 이름
			rowData[i][1] = dto.getSyrup(); 	// 시럽
			rowData[i][2] = dto.getCupSize();	// 사이즈 
			rowData[i][3] = dto.getShot(); 	// 샷추가 
			rowData[i][4] = dto.getWhip(); 	// 휘핑크림
			rowData[i][5] = dto.getCups(); 	// 잔
			rowData[i][6] = dto.getTotalPrice();
		}
		
		// Table 관련 
		// 1. 테이블 폭을 설정하기 위한 Model
		model = new DefaultTableModel(columnNames, 0) {	// (폭,높이)
			@Override
			public boolean isCellEditable(int row, int column) {  //수정, 입력 불가
				return false;
			}
		};
		
		model.setDataVector(rowData, columnNames); // (실제데이터:2차원배열, 범주)
		
		// Jtable
		jtable = new JTable(model);

		
		// column의 폭을 설정
		jtable.getColumnModel().getColumn(0).setMaxWidth(200);// 메뉴 이름
		jtable.getColumnModel().getColumn(1).setMaxWidth(80);// 시럽
		jtable.getColumnModel().getColumn(2).setMaxWidth(80);// 사이즈
		jtable.getColumnModel().getColumn(3).setMaxWidth(50);// 샷추가
		jtable.getColumnModel().getColumn(4).setMaxWidth(50);// 휘핑크림
		jtable.getColumnModel().getColumn(5).setMaxWidth(50);// 잔
		jtable.getColumnModel().getColumn(6).setMaxWidth(100);// 총액
		
		
		// 테이블 크기와 위치 설정
		jscrPane = new JScrollPane(jtable);
		jscrPane.setBounds(10, 50, 600, 200);
		frame.add(jscrPane);
		
		
		setVisible(true);
		setBounds(800, 230, 640, 400);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
