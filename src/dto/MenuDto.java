package dto;

public class MenuDto {
	private int seq; 		// 메뉴의 고유번호
	private String name;	// 메뉴이름
	private int price;		// 메뉴 가격
	
	
	public MenuDto() {
	}
	
	public MenuDto(int seq, String name, int price) {
		super();
		this.seq = seq;
		this.name = name;
		this.price = price;
	}
	
	// getter/setter
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
	@Override
	public String toString() {
		return "MenuDto [seq=" + seq + ", name=" + name + ", price=" + price + "]";
	}
	
	
	
	
	
}
