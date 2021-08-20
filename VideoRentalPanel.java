import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
public class VideoRentalPanel extends JPanel {
	ArrayList<Video> videos;
	ArrayList<Member> members;
	DefaultListModel<String> videoList = new DefaultListModel<String>();
	JList<String> listV = new JList<String>(videoList);
	DefaultListModel<String> memberList = new DefaultListModel<String>();
	JList<String> listM = new JList<String>(memberList);
	JLabel label = new JLabel("Rental Manage");
	JScrollPane scrollVideoPane=new JScrollPane(listV);
	JScrollPane scrollMemberPane = new JScrollPane(listM);
	JLabel videoLabel = new JLabel("Video List");
	JLabel memberLabel = new JLabel("Member List");
	public JButton backButton = new JButton("Back"); 

	public VideoRentalPanel() {
		label.setFont(new Font("Arial",Font.PLAIN, 30));
		videoLabel.setFont(new Font("Arial",Font.PLAIN, 15));
		memberLabel.setFont(new Font("Arial",Font.PLAIN, 15));
		backButton.setFont(new Font("Arial",Font.PLAIN, 30));
		
		this.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
		this.setLayout(new GridLayout(0,1));
		
		RentalPane rentalPane = new RentalPane();
		scrollVideoPane.createVerticalScrollBar();
		scrollVideoPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollMemberPane.createVerticalScrollBar();
		scrollMemberPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		this.add(label);
		this.add(rentalPane);
		this.add(videoLabel);
		this.add(scrollVideoPane);
		this.add(memberLabel);
		this.add(scrollMemberPane);
		this.add(backButton);
	}
	class RentalPane extends JPanel{
		JLabel questNum = new JLabel("How many:");
		JTextField inputNum = new JTextField();
		JButton rentButton = new JButton("Rend");
		JButton returnButton = new JButton("Return");
		RentalPane(){
			rentButton.addActionListener(new RentAction());
			rentButton.setFont(new Font("Arial",Font.PLAIN, 15));
			returnButton.addActionListener(new ReturnAction());
			returnButton.setFont(new Font("Arial",Font.PLAIN, 15));
			questNum.setFont(new Font("Arial",Font.PLAIN, 15));
			this.add(questNum);
			this.add(inputNum);
			this.add(Box.createRigidArea(new Dimension(15,60)));
			this.add(rentButton);
			this.add(returnButton);
			this.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
			this.setLayout(new GridLayout(1,0));
		}
		class RentAction implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				try {
					int videoIndex = listV.getSelectedIndex();
					int memberIndex = listM.getSelectedIndex();
					int num = Integer.valueOf(inputNum.getText());
					Video video = videos.get(videoIndex);
					Member people = members.get(memberIndex);
					
					if(num > video.getNum()) {
						JOptionPane.showMessageDialog(listV,"Too many videos!",
								"Error",JOptionPane.ERROR_MESSAGE);
					}else {
						video.set(video.getTitle(), video.getNum()-num, video.getRentalNum()+num);
						people.addList(video.getTitle(), num);
						inputNum.setText("");
						printList();
						JOptionPane.showMessageDialog(listV,"done!",
								"RentalSystem",JOptionPane.INFORMATION_MESSAGE);
					}
				} catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(listV,"Input wrong value!",
							"Error",JOptionPane.ERROR_MESSAGE);
				} catch(IndexOutOfBoundsException ei) {
					JOptionPane.showMessageDialog(listV,"Please select somthing in Video List and Member List.",
							"Error",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
		class ReturnAction implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				try {
					int videoIndex = listV.getSelectedIndex();
					int memberIndex = listM.getSelectedIndex();
					int num = Integer.valueOf(inputNum.getText());
					Video video = videos.get(videoIndex);
					Member people = members.get(memberIndex);
					
					if(num > people.countList(video.getTitle())) {
						JOptionPane.showMessageDialog(listV,"Too many videos!",
								"Error",JOptionPane.ERROR_MESSAGE);
					}else {
						video.set(video.getTitle(), video.getNum()+num, video.getRentalNum()-num);
						people.removeList(video.getTitle(), num);
						inputNum.setText("");
						printList();
						JOptionPane.showMessageDialog(listV,"done!",
								"RentalSystem",JOptionPane.INFORMATION_MESSAGE);
					}
				} catch(NumberFormatException ex) {
					JOptionPane.showMessageDialog(listV,"Input wrong value!",
							"Error",JOptionPane.ERROR_MESSAGE);
				} catch(IndexOutOfBoundsException ei) {
					JOptionPane.showMessageDialog(listV,"Please select somthing in Video List and Member List.",
							"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
	public void printList() {
		videoList.removeAllElements();
		for(int i=0;i<videos.size();i++) {
			String el = "No."+(i+1)+" | "+ "Title:"+videos.get(i).getTitle()
					+" Stock:"+videos.get(i).getNum()+" Rent:"+videos.get(i).getRentalNum();
			videoList.addElement(el);
		}
		memberList.removeAllElements();
		for(int i=0;i<members.size();i++) {
			String el = "No."+(i+1)+" | "+ "Serial Nubmer:"+members.get(i).getSerial()
					+" Name:"+members.get(i).getName()+" Videos:"+members.get(i).getRentalList();
			memberList.addElement(el);
		}
	}
	
}

