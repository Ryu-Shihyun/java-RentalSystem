
public class Video {
	private String title = new String();
	private int num = 0;
	private int rentalNum = 0;
	public Video(String s, int n, int r) {
		set(s,n,r);
	}
	public String getTitle() {
		return title;
	}
	public int getNum() {
		return num;
	}
	public int getRentalNum() {
		return rentalNum;
	}
	void set(String s, int n, int r) {
		title = s;
		num = n;
		rentalNum = r;
	}
	
}
