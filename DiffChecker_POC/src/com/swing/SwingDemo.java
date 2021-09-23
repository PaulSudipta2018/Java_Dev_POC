package com.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.bl.DiffChecker;

public class SwingDemo {

	public JFrame frame = new JFrame("Difference Checker");

	public SwingDemo(){

		DiffChecker dc = new DiffChecker();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container container = frame.getContentPane();
		JLabel firstString = new JLabel("First Text");
		firstString.setBounds(20, 50, 100, 20);  
		firstString.setForeground(Color.MAGENTA);
		JLabel secondString = new JLabel("Second Text");
		secondString.setBounds(550, 50, 100, 20);  
		secondString.setForeground(Color.MAGENTA);
		
		JTextArea firstStringIp = new JTextArea();  
		firstStringIp.setBounds(20, 80, 500, 200); 
		JTextArea secondStringIp = new JTextArea();  
		secondStringIp.setBounds(550, 80, 500, 200);  

		JRadioButton chkByChar = new JRadioButton("Check character wise", true);
		chkByChar.setBounds(300, 320, 200, 20);
		JRadioButton chkByWord = new JRadioButton("Check word wise");
		chkByWord.setBounds(550, 320, 200, 20);
		ButtonGroup bg=new ButtonGroup();
		bg.add(chkByChar);
		bg.add(chkByWord);

		JButton btn = new JButton("Check Differences");
		btn.setBounds(450, 400, 200, 30);
		btn.setBorderPainted(true);
		
		btn.addActionListener(new ActionListener() {
			//ShowDiffTextPane pane = new ShowDiffTextPane();
			
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

		JButton reset = new JButton("Check New Difference");
		reset.setBounds(620, 400, 250, 30);
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				firstStringIp.enable();
				secondStringIp.enable();
				firstStringIp.setText("");
				secondStringIp.setText("");
				System.out.print(firstStringIp.getText());
			}
		});

		frame.add(firstString);
		frame.add(secondString);
		frame.add(firstStringIp);
		frame.add(secondStringIp);
		frame.add(chkByWord);
		frame.add(chkByChar);
		frame.add(btn);
		frame.add(reset);
		frame.setLayout(null);  
		frame.setSize(200, 300);
		frame.setVisible(true);

	}
}
