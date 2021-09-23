package com.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class ShowDiffTextPane extends JPanel {

    private JSplitPane splitPane;
   
    public ShowDiffTextPane() {
        super(new BorderLayout());

        Font font = new Font("Serif", Font.ITALIC, 24);

        //ImageIcon icon = createImageIcon("images/Cat.gif");
        JComponent sd1 = new JComponent() {
		};
        sd1.setMinimumSize(new Dimension(30,30));
        sd1.setFont(font);
        
        //icon = createImageIcon("images/Dog.gif");
        JComponent sd2 = new JComponent() {
		};
        sd2.setMinimumSize(new Dimension(60,60));
        sd2.setFont(font);
        
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                   sd1, sd2);
        splitPane.setResizeWeight(0.5);
        splitPane.setOneTouchExpandable(true);
        splitPane.setContinuousLayout(true);

        add(splitPane, BorderLayout.CENTER);
        //add(createControlPanel(), BorderLayout.PAGE_END);
    }
}

