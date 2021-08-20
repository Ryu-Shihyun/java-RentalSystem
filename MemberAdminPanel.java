import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

public class MemberAdminPanel extends JPanel{
	DefaultListModel<String> memberList = new DefaultListModel<String>();
	JList<String> list = new JList<String>(memberList);
	JLabel label = new JLabel("Member Admin");
	public JButton backButton = new JButton("Back");
	public ArrayList<Member> members = new ArrayList<Member>();
	MemberRegister memberRegister = new MemberRegister();
	
	public MemberAdminPanel() {
		label.setFont(new Font("Arial",Font.PLAIN, 30));
		backButton.setFont(new Font("Arial",Font.PLAIN, 30));
		System.out.println(backButton.getSize());
		this.setLayout(new GridLayout(0,1));
		this.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
		this.add(label);
		this.add(memberRegister);
		
		JScrollPane scrollPane=new JScrollPane(list);
		scrollPane.createVerticalScrollBar();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		this.add(scrollPane);
		this.add(backButton);
		
	}
	class MemberRegister extends JPanel {
		JLabel questSerial = new JLabel("Serial Number:");
		JTextField inputSerial = new JTextField(); ;
		JLabel questName= new JLabel("Name:");
		JTextField inputName = new JTextField(); ;
		JButton inputButton = new JButton("Add");
		JButton editButton = new JButton("Edit");
		JButton deleteButton = new JButton("Delete");
		Member select;
		
		public MemberRegister() {
			inputButton.addActionListener(new InputAction());
			inputButton.setFont(new Font("Arial",Font.PLAIN, 15));
			deleteButton.addActionListener(new DeleteAction());	
			deleteButton.setFont(new Font("Arial",Font.PLAIN, 15));
			editButton.addActionListener(new EditAction());
			editButton.setFont(new Font("Arial",Font.PLAIN, 15));
			questSerial.setFont(new Font("Arial",Font.PLAIN, 15));
			questName.setFont(new Font("Arial",Font.PLAIN, 15));
			this.add(questSerial);
			this.add(inputSerial);
			this.add(Box.createRigidArea(new Dimension(15,60)));
			this.add(questName);
			this.add(inputName);
			this.add(Box.createRigidArea(new Dimension(15,60)));
			this.add(inputButton);
			this.add(deleteButton);
			this.add(editButton);
			this.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
			this.setLayout(new GridLayout(1,0));
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
					if(isAnotherSame(inputSerial.getText(),0)) {
						JOptionPane.showMessageDialog(list,"There is a same serial number in list",
								"Error",JOptionPane.ERROR_MESSAGE);
						throw new NumberFormatException();
					}
					String serial = inputSerial.getText();
					int confirm = Integer.valueOf(serial);
					String  name = inputName.getText();
					
					Member people = new Member(serial,name);
					
					members.add(people);
					printList();
					inputSerial.setText("");
					inputName.setText("");
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
					memberList.remove(index);
					members.remove(index);
					JOptionPane.showMessageDialog(list,"done!",
							"RentalSystem",JOptionPane.INFORMATION_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(list,"Please select what you want to delete one in list",
							"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		public class EditAction implements ActionListener{
			Member people;
			int index ;
			public void actionPerformed(ActionEvent e) {
				index = list.getSelectedIndex();
				
				if(index > -1) {
					try {
						
							people = members.get(index);
							int confirm = Integer.valueOf(inputSerial.getText());
							people.set(inputSerial.getText(), inputName.getText());
							if(isAnotherSame(confirm+"",1)) {
								people.set(confirm+"", people.getName());
								JOptionPane.showMessageDialog(list,"There is a same serial number in list",
										"Error",JOptionPane.ERROR_MESSAGE);
								throw new NumberFormatException();
							}
							printList();
							inputSerial.setText("");
							inputName.setText("");
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
			memberList.removeAllElements();
			for(int i=0;i<members.size();i++) {
				String el = "No."+(i+1)+" | "+ "Serial Number:"+members.get(i).getSerial()
						+" Name:"+members.get(i).getName()+" Videos:"+members.get(i).getRentalList();
				memberList.addElement(el);
			}
	}
	boolean isAnotherSame(String serial,int permit) {
		int i=0;
		for(Iterator<Member> itr = members.listIterator();itr.hasNext();) {
			Member m = itr.next();
			if(serial.equals(m.getSerial())){
				i++;
			}
			if(i>permit) {
				return true;
			}
		}
		return false;
	}

}
