package com.awt;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

import com.bl.DiffChecker;

public class AwtClass extends Frame{

	public AwtClass(){
		DiffChecker dc = new DiffChecker();
		Label firstString = new Label("First Text");
		firstString.setBounds(20, 50, 80, 20);  
		Label secondString = new Label("Second Text");
		secondString.setBounds(550, 50, 80, 20);  
		
		TextArea firstStringIp = new TextArea();  
		firstStringIp.setBounds(20, 80, 500, 200); 
		TextArea secondStringIp = new TextArea();  
		secondStringIp.setBounds(550, 80, 500, 200);  
		
		JRadioButton chkByChar = new JRadioButton("Check character wise", true);
		chkByChar.setBounds(300, 320, 200, 20);
		JRadioButton chkByWord = new JRadioButton("Check word wise");
		chkByWord.setBounds(550, 320, 200, 20);
		ButtonGroup bg=new ButtonGroup();
		bg.add(chkByChar);
		bg.add(chkByWord);
		
		Button btn = new Button("Check Differences");
		btn.setBounds(450, 400, 80, 30);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean checkByChar = true;
				checkByChar = chkByChar.isSelected();
				
				String s1 = "", s2 = "";
				firstStringIp.disable();
				secondStringIp.disable();
				List<List<String>> resultantDifference = dc.diffCheckerMain(firstStringIp.getText(), secondStringIp.getText(), checkByChar);
				
				StringBuilder sbl = new StringBuilder("");
				boolean isClosed = false;
				boolean isOpened = false;
				for(String s : resultantDifference.get(0)) {
					if(s.startsWith("+")) {
						//color with green
						if(!isOpened) {
							sbl.append("`");
							isOpened = true;
							isClosed = false;
						}
						sbl.append(s.substring(1));
					}else {
						if(isOpened && !isClosed)sbl.append("`");
						isOpened = false;
						isClosed = true;
						
						if(s.startsWith("-")) {
							//sbl.append(s.substring(1));
						}else {
							sbl.append(s);
						}
					}
				}
				if(isOpened && !isClosed)sbl.append("`");
				s1 = sbl.toString();
				firstStringIp.setText(s1);
				
				// for second string
				
				isClosed = false;
				isOpened = false;
				sbl = new StringBuilder("");
				for(String s : resultantDifference.get(1)) {
					if(s.startsWith("+")) {
						//color with green
						if(!isOpened) {
							sbl.append("`");
							isOpened = true;
							isClosed = false;
						}
						sbl.append(s.substring(1));
					}else {
						if(isOpened && !isClosed)sbl.append("`");
						isOpened = false;
						isClosed = true;
						
						if(s.startsWith("-")) {
							//sbl.append(s.substring(1));
						}else {
							sbl.append(s);
						}
					}
				}
				
				if(isOpened && !isClosed)sbl.append("`");
				s2 = sbl.toString();
				secondStringIp.setText(s2);
				
				//secondStringIp.setText(resultantDifference.get(1).toString());
			}
		});
		
		Button reset = new Button("Reset");
		reset.setBounds(620, 400, 80, 30);
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstStringIp.enable();
				secondStringIp.enable();
				firstStringIp.setText("");
				secondStringIp.setText("");
				System.out.print(firstStringIp.getText());
			}
		});
		add(firstString);  
		add(secondString);  
		add(firstStringIp);
		add(secondStringIp);
		add(btn);
		add(reset);
		add(chkByChar);
		add(chkByWord);
		
		setSize(300,300);  
		setLayout(null);  
		setVisible(true);  
	}

		
}
