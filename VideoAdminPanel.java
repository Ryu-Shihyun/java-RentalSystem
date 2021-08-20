import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class VideoAdminPanel extends JPanel {
	DefaultListModel<String> videoList = new DefaultListModel<String>();
	JList<String> list = new JList<String>(videoList);
	JLabel label = new JLabel("Video Admin");
	public JButton backButton = new JButton("Back");
	public ArrayList<Video> videos = new ArrayList<Video>();
	VideoRegister videoRegister = new VideoRegister();
	
	public VideoAdminPanel() {
		label.setFont(new Font("Arial",Font.PLAIN, 30));
		backButton.setFont(new Font("Arial",Font.PLAIN, 30));
		this.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
		this.setLayout(new GridLayout(0,1));
		
		this.add(label);
		this.add(videoRegister);
		
		JScrollPane scrollPane=new JScrollPane(list);
		scrollPane.createVerticalScrollBar();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		this.add(scrollPane);
		this.add(backButton);
	}
	
	class VideoRegister extends JPanel {
		JLabel questTitle = new JLabel("Title:");
		JTextField inputTitle ;
		JLabel questNum = new JLabel("Stock:");
		JTextField inputNum ;
		JButton inputButton = new JButton("Add");
		JButton editButton = new JButton("Edit");
		JButton deleteButton = new JButton("Delete");
		Video select;
		
		
		public  VideoRegister(){
			inputTitle= new JTextField();
			inputNum = new JTextField();
			InputAction inputAction = new InputAction();
			DeleteAction deleteAction = new DeleteAction();
			EditAction editAction = new EditAction();
			inputButton.addActionListener(inputAction);	
			inputButton.setFont(new Font("Arial",Font.PLAIN, 15));
			deleteButton.addActionListener(deleteAction);
			deleteButton.setFont(new Font("Arial",Font.PLAIN, 15));
			editButton.addActionListener(editAction);	
			editButton.setFont(new Font("Arial",Font.PLAIN, 15));
			questTitle.setFont(new Font("Arial",Font.PLAIN, 15));
			questNum.setFont(new Font("Arial",Font.PLAIN, 15));
			
			this.add(questTitle);
			this.add(inputTitle);
			this.add(Box.createRigidArea(new Dimension(15,15)));
			this.add(questNum);
			this.add(inputNum);
			this.add(Box.createRigidArea(new Dimension(15,15)));
			this.add(inputButton);
			this.add(deleteButton);
			this.add(editButton);
			this.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
			this.setLayout(new GridLayout(1,0));
			printList();
		}
		
		public class InputAction implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				try {
					if(list.getSelectedIndex()>-1) {
						int option = JOptionPane.showConfirmDialog(list,"Really? No 'Edit'? ",
								"Error",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
						if(option == JOptionPane.NO_OPTION) {
							throw new NumberFormatException();
						}
					}
					if(isAnotherSame(inputTitle.getText(),0)) {
						JOptionPane.showMessageDialog(list,"There a is same title in list",
								"Error",JOptionPane.ERROR_MESSAGE);
						throw new NumberFormatException();
					}
					String title = inputTitle.getText();
					int stock = Integer.valueOf(inputNum.getText());
					
					Video video = new Video(title,stock,0);
					
					videos.add(video);
					printList();
					inputTitle.setText("");
					inputNum.setText("");
					JOptionPane.showMessageDialog(list,"done!",
							"RentalSystem",JOptionPane.INFORMATION_MESSAGE);
				}catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(list,"Input wrong value!",
							"Error",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
		class DeleteAction implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				int index = list.getSelectedIndex();
				if( index > -1) {
					videoList.remove(index);
					videos.remove(index);
					JOptionPane.showMessageDialog(list,"done!",
							"RentalSystem",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(list,"Please select what you want to delete one in list",
							"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		public class EditAction implements ActionListener{
			Video video;
			int indexV ;
			public void actionPerformed(ActionEvent e) {
				indexV = list.getSelectedIndex();
				
				if(indexV > -1) {
					try {
						video = videos.get(indexV);
						String saveTitle = video.getTitle();
						video.set(inputTitle.getText(), Integer.valueOf(inputNum.getText()), video.getRentalNum());
						if(isAnotherSame(inputTitle.getText(),1)) {
							video.set(saveTitle,video.getNum(), video.getRentalNum());
							JOptionPane.showMessageDialog(list,"There is a same title in list",
									"Error",JOptionPane.ERROR_MESSAGE);
							throw new NumberFormatException();
						}
						printList();
						inputTitle.setText("");
						inputNum.setText("");
						JOptionPane.showMessageDialog(list,"done!",
								"RentalSystem",JOptionPane.INFORMATION_MESSAGE);
					}catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(list,"Input wrong value!",
								"Error",JOptionPane.ERROR_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(list,"Please select what you want to edit one in list",
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
	}
	boolean isAnotherSame(String title,int permit) {
		int i=0;
		for(Iterator<Video> itr = videos.listIterator();itr.hasNext();) {
			Video v = itr.next();
			if(title.equals(v.getTitle())){
				i++;
			}
			if(i>permit) {
				return true;
			}
		}
		return false;
	}
}
