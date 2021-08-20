import java.util.ArrayList;

public class Member {
	private String name = new String();
	private String serial = "00000000";
	private ArrayList<String> rentalList;
	public Member(String s, String n) {
		name= n;
		serial = s;
		rentalList = new ArrayList<String>();
	}
	public String getName() {
		return name;
	}
	public String getSerial() {
		return serial;
	}
	public  ArrayList<String> getRentalList() {
		return rentalList;
	}
	public int countList(String title) {
		int count=0;
		for(int i=0;i<rentalList.size();i++) {
			if(rentalList.get(i).equals(title)) {
				count++;
			}
		}
		return count;
	}
	public void set(String s, String n) {
		name = n;
		serial = s;
	}
	public void addList(String title,int n) {
		for(int i=0; i<n;i++) {
			rentalList.add(title);
		}
	}
	public void removeList(String title, int n) {
		int count = n;
		for(int i=0;i<count;i++ ) {
			rentalList.remove(title);
		}
	}
	
	
}
