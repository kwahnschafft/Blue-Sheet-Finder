/**
 * BlueSheet
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
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
  private JLabel rule;
  String blueColor = "#" + "B8DFEF";
  String[] rules = {"<html>" +
	        "<b>III. Do not use the first or second person('I,' 'me,' 'my'; 'we,' 'us,' 'our'; 'you,' 'your') in critical writing.</b>" +
	        "<ul>" + "<li style='list-style-type: none'><b>(Incorrect)</b> <i>I think that</i> Holden Caulfield, the hero of <u>The Catcher in the Rye</u>, is actually a hypocrite.</li>" 
	         + "<li style='list-style-type:none'></li>"
	         + "<li style='list-style-type: none'><b>(Correct)</b> Holden Caulfield, the hero of <u>The Catcher in the Rye</u>, is actually a hypocrite.</li>" + "</ul" + "</html>",
	         "<html>" +
	     	 "<b>IV. Do not use 'this' or 'which' to refer to a clause.</b>" +
	     	  "<ul>" + "<li style='list-style-type: none'><b>(Incorrect)</b> In Dr. Seuss's <u>Horton Hears a Who</u>, Horton the elephant says that he hears a voice. <i>This</i> causes his friends to accuse him of being insane.</li>" 
	     	         + "<li style='list-style-type:none'></li>"
	     	         + "<li style='list-style-type: none'><b>(Correct)</b> In Dr. Seuss's <u>Horton Hears a Who</u>, Horton the elephant says that he hears a voice. <i>This claim</i> causes his friends to accuse him of being insane.</li>" + "</ul" + "</html>",
	     	  "<html>" +
	     	   "<b>VI. Put pronouns in the appropriate case (subjective, objective, possessive).</b>" +
	     	  	 "<ul>" + "<li style='list-style-type: none'><b>(Incorrect)</b> She is the last person <i>who</i> I would suspect.</li>" 
	     	  	     	         + "<li style='list-style-type:none'></li>"
	     	  	     	         + "<li style='list-style-type: none'><b>(Correct)</b> She is the last person <i>whom</i> I would suspect.</li>" + "</ul" + "</html>",
     	     "<html>" +
     	     	 "<b>VII. Avoid ambiguos pronouns.</b>" +
     	     	  "<ul>" + "<li style='list-style-type: none'><b>(Incorrect)</b> Oedipus and the shepherd argue about whether <i>he</i> should know the truth.</li>" 
     	     	         + "<li style='list-style-type:none'></li>"
     	     	         + "<li style='list-style-type: none'><b>(Correct)</b> Oedipus and the shepherd argue about whether <i>Oedipus</i> should know the truth.</li>" + "</ul" + "</html>"
	         };
  
  public BlueSheet() {

    setJMenuBar(new MenuBar(this, new DecodeAction()));

    JPanel p1 = new JPanel();
    p1.setPreferredSize(new Dimension(400, 81));
    p1.setLayout(new GridLayout(5, 1));
    p1.setBorder(new LineBorder(Color.BLACK));
    ButtonGroup bluesheets = new ButtonGroup();
    JRadioButton one = new JRadioButton("III. Do not use 'this' or 'which' to refer to a clause.");
    JRadioButton two = new JRadioButton("II. Write complete");
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
    p1.setBackground(Color.decode(blueColor));
   
    one.addActionListener(new CustomActionListenerOne());
    two.addActionListener(new CustomActionListenerTwo());
    three.addActionListener(new CustomActionListenerThree());
    four.addActionListener(new CustomActionListenerFour());
    five.addActionListener(new CustomActionListenerFive());
    
    JPanel p2 = new JPanel();
    p2.setPreferredSize(new Dimension(200, 60));
    p2.setLayout(new GridBagLayout());
    p2.setBorder(new LineBorder(Color.BLACK));
    p2.setBorder(new EmptyBorder(10, 10, 0, 0));
    GridBagConstraints p2gbc = new GridBagConstraints();
    p2gbc.gridx = 0;
    p2gbc.gridy = 0;
    p2gbc.gridwidth = 1;
	p2gbc.gridheight = 1;
	p2gbc.weightx = 1.0;
	p2gbc.weighty = 1.0;
	p2gbc.anchor = GridBagConstraints.NORTHWEST;
	p2gbc.fill = GridBagConstraints.HORIZONTAL;
    rule = new JLabel("");
    p2.setBackground(Color.decode(blueColor));
    p2.add(rule, p2gbc);

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
    
    sentence = new JTextArea(8, 20);
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
    	  String III = rules[0];
    	  rule.setText(III);
      }
   }
  class CustomActionListenerTwo implements ActionListener{
      public void actionPerformed(ActionEvent e) {
    	  String IV = rules[1];
    	  rule.setText(IV);
      }
   }
  
  class CustomActionListenerThree implements ActionListener{
      public void actionPerformed(ActionEvent e) {
    	  String VI = rules[2];
    	  rule.setText(VI);
      }
   }
  
  class CustomActionListenerFour implements ActionListener{
      public void actionPerformed(ActionEvent e) {
          String VII = rules[3];
          rule.setText(VII);
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

