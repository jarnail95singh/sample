import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.Font;
import java.awt.Color;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.lang.Math;

public class bot extends JFrame implements KeyListener{

	JPanel p=new JPanel();
	JTextArea dialog=new JTextArea(21,26); //(length,width)
	JTextArea input=new JTextArea(1,27);
	
	
	JScrollPane scroll=new JScrollPane(
		dialog,
		JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
		JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
	);
	
	String[][] chatBot={
		//standard greetings
		{"hi","hello","hola","ola","howdy"},
		{"hi","hello"},
		//name
		{"what's your name", "what's ur name", "whats your name", "whats ur name", "name", "your name",  "ur name"},
		{"Michael"},
		//question greetings
		{"how are you","how r you","how r u","how are u"},
		{"good","doing well"},
		//age
		{"what is your age", "what's your age", "whats your age", "whats ur age", "what's ur age", "your age", "ur age", "age",
		 "how old r u", "how old are you"},
		{"young enough to chill & old enough to be mature", "20"},
		//doing what
		{"what are u doing","what r u doing","what r you doing","what are you doing"},
		{"looks like I'm chatting with you"},
		//going on
		{"what is going on", "what's going on"},
		{"note crisis in India", "Trump in USA", "Holidays in colleges"},
		//yes
		{"yes"},
		{"no","NO","NO!!!!!!!"},
		//location
		{"where r u from", "from"},
		{"I am from India","India"},
		//profession,job
		{"what do you do for living", "what's your job"},
		{"I pass the time of people", "I chat with people"},
		//earning
		{"how much do you earn"},
		{"I don't work to earn rather I prefer to help people"},
		//default
		{"shut up","you're bad","noob","stop talking",
		"(michael is unavailable, due to LOL)"}
	};
	
	public static void main(String[] args){
		new bot();
	}
	
	public bot(){
		super("Chat Bot");
		setSize(330,400);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		Font font = new Font("Verdana", Font.BOLD, 12);
        	dialog.setFont(font);
        	dialog.setForeground(Color.GREEN);

		dialog.setEditable(false);
		input.addKeyListener(this);
	
		p.add(scroll);
		p.add(input);
		p.setBackground(new Color(0,0,200));
		add(p);
		
		setVisible(true);
		
		
		
	}
	
	
	
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			input.setEditable(false);
			//-----grab quote-----------
			String quote=input.getText();
			input.setText("");
			addText("You-->\t"+quote);
			quote.trim();
			while(
				quote.charAt(quote.length()-1)=='!' ||
				quote.charAt(quote.length()-1)=='.' ||
				quote.charAt(quote.length()-1)=='?'
			){
				quote=quote.substring(0,quote.length()-1);
			}
			quote.trim();
			byte response=0;
			/*
			0:we're searching through chatBot[][] for matches
			1:we didn't find anything
			2:we did find something
			*/
			//-----check for matches----
			int j=0;//which group we're checking
			while(response==0){
				if(inArray(quote.toLowerCase(),chatBot[j*2])){
					response=2;
					int r=(int)Math.floor(Math.random()*chatBot[(j*2)+1].length);
					addText("\nMichael-->\t"+chatBot[(j*2)+1][r]);
				}
				j++;
				if(j*2==chatBot.length-1 && response==0){
					response=1;
				}
			}
			
			//-----default--------------
			if(response==1){
				int r=(int)Math.floor(Math.random()*chatBot[chatBot.length-1].length);
				addText("\nMichael-->\t"+chatBot[chatBot.length-1][r]);
			}
			addText("\n");
		}
	}
	
	public void keyReleased(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			input.setEditable(true);
		}
	}
	
	private class CloseListener implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        //DO SOMETHING
        System.exit(0);
    }
    
}
	public void keyTyped(KeyEvent e){}
	
	public void addText(String str){
		dialog.setText(dialog.getText()+str);
	}
	
	public boolean inArray(String in,String[] str){
		boolean match=false;
		for(int i=0;i<str.length;i++){
			if(str[i].equals(in)){
				match=true;
			}
		}
		return match;
	}
}
