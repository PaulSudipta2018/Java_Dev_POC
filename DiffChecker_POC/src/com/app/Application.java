package com.app;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.swing.SwingDemo;

public class Application {

	public static void main(String[] args) throws IOException {
		SwingDemo sd = new SwingDemo();

		JFrame frame = sd.frame;
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      Container container = sd.frame.getContentPane();
	      JTextPane textPane = new JTextPane();
	      textPane.setBackground(Color.red);
	      SimpleAttributeSet attributeSet = new SimpleAttributeSet();
	      StyleConstants.setItalic(attributeSet, true);
	      textPane.setCharacterAttributes(attributeSet, true);
	      textPane.setText("Learn with Text and "); Font font = new Font("Verdana", Font.BOLD, 22);
	      textPane.setFont(font);
	      StyledDocument doc = textPane.getStyledDocument();
	      Style style = textPane.addStyle("", null);
	      StyleConstants.setForeground(style, Color.orange);
	      StyleConstants.setBackground(style, Color.black);
	      try {
			doc.insertString(doc.getLength(), "Video Tutorials ", style);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      JScrollPane scrollPane = new JScrollPane(textPane);
	      container.add(scrollPane, BorderLayout.CENTER);
	      frame.setSize(550, 300);
	      frame.setVisible(true);
	}
}
