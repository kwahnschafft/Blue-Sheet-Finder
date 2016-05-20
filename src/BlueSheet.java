/**
 * BlueSheet
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

public class BlueSheet extends JFrame {

  private JTextArea essay;
  private JTextArea sentence;
  
  public BlueSheet() {

    setJMenuBar(new MenuBar(this, new DecodeAction()));

    JPanel p1 = new JPanel();
    p1.setPreferredSize(new Dimension(400, 81));
    p1.setLayout(new GridLayout(5, 1));
    p1.setBorder(new LineBorder(Color.BLACK));
    ButtonGroup bluesheets = new ButtonGroup();
    JRadioButton one = new JRadioButton("first");
    JRadioButton two = new JRadioButton("second");
    JRadioButton three = new JRadioButton("second");
    JRadioButton four = new JRadioButton("second");
    JRadioButton five = new JRadioButton("second");
    bluesheets.add(one);
    bluesheets.add(two);
    bluesheets.add(three);
    bluesheets.add(four);
    bluesheets.add(five);
    p1.add(one);
    p1.add(two);
    p1.add(three);
    p1.add(four);
    p1.add(five);
   
    
    one.addActionListener(new CustomActionListenerOne());
    two.addActionListener(new CustomActionListenerTwo());
    three.addActionListener(new CustomActionListenerThree());
    four.addActionListener(new CustomActionListenerFour());
    five.addActionListener(new CustomActionListenerFive());
    
    JPanel p2 = new JPanel();
    p2.setPreferredSize(new Dimension(200, 60));
    p2.setLayout(new GridLayout(3, 5));
    p2.setBorder(new LineBorder(Color.BLACK));

    Font font = new Font("Monospaced", Font.PLAIN, 12);

    essay = new JTextArea(20, 50);
    essay.setFont(font);
    essay.setLineWrap(true);
    essay.setWrapStyleWord(true);
    JScrollPane areaScrollPaneIn = new JScrollPane(essay);
    areaScrollPaneIn.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    
    JPanel changes = new JPanel();
    changes.setPreferredSize(new Dimension(100,50));
    changes.setLayout(new GridLayout(2,1));
    JButton correct = new JButton("Correct");
    correct.setEnabled(false);
    JButton change = new JButton("Change");
    change.setEnabled(false);
    changes.add(correct);
    changes.add(change);
    
    JPanel p3 = new JPanel();
    p3.setPreferredSize(new Dimension(100, 50));
    p3.setLayout(new GridLayout(1,3));
    p3.setBorder(new LineBorder(Color.BLACK));
    JButton previous = new JButton("Previous");
    previous.setEnabled(false);
    p3.add(previous);
    p3.add(changes);
    JButton next = new JButton("Next");
    next.setEnabled(false);
    p3.add(next);
    
    sentence = new JTextArea(4, 20);
    sentence.setFont(font);
    sentence.setLineWrap(true);
    sentence.setWrapStyleWord(true);
    JScrollPane sentenceScrollPane = new JScrollPane(sentence);
    sentenceScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
  
	  
	 JPanel panel = new JPanel(new GridBagLayout());
	 GridBagConstraints gbc = new GridBagConstraints();
	 gbc.gridx = 0;
	 gbc.gridy = 0;
	 gbc.gridwidth = 1;
	 gbc.gridheight = 1;
	 gbc.weightx = 1.0;
	 gbc.weighty = 1.0;
	 gbc.fill = GridBagConstraints.BOTH;
	 gbc.anchor = GridBagConstraints.CENTER;
	 panel.add(areaScrollPaneIn, gbc);
	 
	 gbc.gridx = 1;
	 gbc.gridy = 0;
	 gbc.gridwidth = 1;
	 gbc.gridheight = 1;
	 panel.add(p1, gbc);

	 gbc.gridx = 0;
	 gbc.gridy = 1;
	 gbc.gridheight = 1;
	 panel.add(sentenceScrollPane, gbc);
	 
	 gbc.gridx = 1;
	 gbc.gridy = 1;
	 gbc.gridheight = 3;
	 panel.add(p2, gbc);
	 
	 gbc.gridx = 0;
	 gbc.gridy = 3;
	 gbc.gridheight = 1;
	 panel.add(p3, gbc);
	
	 Container c = getContentPane();
	 c.add(panel, BorderLayout.CENTER);
	 c.setMinimumSize(c.getSize());
	 
  }
	
  class CustomActionListenerOne implements ActionListener{
      public void actionPerformed(ActionEvent e) {
          essay.setBackground(Color.magenta);
      }
   }
  class CustomActionListenerTwo implements ActionListener{
      public void actionPerformed(ActionEvent e) {
          essay.setBackground(Color.blue);
      }
   }
  
  class CustomActionListenerThree implements ActionListener{
      public void actionPerformed(ActionEvent e) {
          essay.setBackground(Color.yellow);
      }
   }
  
  class CustomActionListenerFour implements ActionListener{
      public void actionPerformed(ActionEvent e) {
          essay.setBackground(Color.green);
      }
   }
  
  class CustomActionListenerFive implements ActionListener{
      public void actionPerformed(ActionEvent e) {
          essay.setBackground(Color.orange);
      }
   }
  
  
  public String getEssayText() {
    return essay.getText();
  }
  
  public String getSentenceText() {
	  return sentence.getText();
  }

  public void setEssayText(String text) {
    essay.setText(text);
    essay.setCaretPosition(0);
  }

  public void setSentenceText(String text) {
    sentence.setCaretPosition(0);
  }

  private class DecodeAction implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      String cmd = ((AbstractButton)e.getSource()).getActionCommand();

      if ("Run".equals(cmd))
      {
  
      }
      else if ("Clear".equals(cmd))
      {

      }
      else if ("Encode".equals(cmd))
      {
 
      }
     // setTextOut(getTextIn());
    }
  }

  /******************************************************************/
  /***************                  main             ****************/
  /******************************************************************/

  public static void main(String[] args)
  {
    BlueSheet window = new BlueSheet();
    window.setDefaultCloseOperation(EXIT_ON_CLOSE);
    window.pack();
    window.setVisible(true);
    window.setMinimumSize(window.getSize());
  }
}

