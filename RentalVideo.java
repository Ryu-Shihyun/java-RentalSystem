import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
public class RentalVideo {
	public JPanel cardPanel=new JPanel();
	public CardLayout layout=new CardLayout();
	VideoAdminPanel video = new VideoAdminPanel();
	VideoRentalPanel rental = new VideoRentalPanel();
	MemberAdminPanel member = new MemberAdminPanel();
	public static void main(String[] args) {
		JFrame frame = new JFrame("LentalViedo");
		frame.setSize(900, 600);
		RentalVideo app = new RentalVideo();
		Component contents= app.createComponents();
		
		frame.getContentPane().add(contents,BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		
	}
	
	public Component createComponents() {
		cardPanel.setLayout(layout);
		
		
		PortalPanel portal = new PortalPanel();
		ButtonAction back = new ButtonAction();
		
		video.backButton.addActionListener(back);
		video.backButton.setActionCommand("portal");
		member.backButton.addActionListener(back);
		member.backButton.setActionCommand("portal");
		rental.backButton.addActionListener(back);
		rental.backButton.setActionCommand("portal");
		
		
		rental.videos = video.videos;
		rental.members = member.members;
		
		
		
		cardPanel.add(portal,"portal");
		cardPanel.add(video,"video");
		cardPanel.add(rental,"rental");
		cardPanel.add(member,"member");
		layout.show(cardPanel,"portal");
		return cardPanel;
	}
	
	public class PortalPanel extends JPanel{
		public PortalPanel() {
			JButton videoButton = new JButton("Video Admin");
			JButton rentalButton = new JButton("Rental Manage");
			JButton memberButton = new JButton("Member Admin");
			
			ButtonAction func = new ButtonAction();
			
			videoButton.addActionListener(func);
			videoButton.setActionCommand("video");
			videoButton.setFont(new Font("Arial",Font.PLAIN, 30));
			rentalButton.addActionListener(func);
			rentalButton.setActionCommand("rental");
			rentalButton.setFont(new Font("Arial",Font.PLAIN, 30));
			memberButton.addActionListener(func);
			memberButton.setActionCommand("member");
			memberButton.setFont(new Font("Arial",Font.PLAIN, 30));
			System.out.println(this);
			this.setBorder(BorderFactory.createEmptyBorder(100,80,50,80));
			this.setLayout(new GridLayout(0,1));
			this.add(videoButton);
			this.add(Box.createRigidArea(new Dimension(10,20)));
			this.add(rentalButton);
			this.add(Box.createRigidArea(new Dimension(10,20)));
			this.add(memberButton);
			
			
		}
	}
	
	public class ButtonAction implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			
			if(cmd.equals("video")) {
				video.printList();
			}
			
			else if(cmd.equals("member")) {
				member.printList();
			}
			if(cmd.equals("rental")) {
				rental.printList();
			}
			layout.show(cardPanel, cmd);
		}
	}
	
}
