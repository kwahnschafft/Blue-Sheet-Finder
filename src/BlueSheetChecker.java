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
import javax.swing.JTextPane;
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

public class BlueSheetChecker extends JFrame {

  private JTextArea essay;
  private JTextArea sentence;
  private JLabel rule;
  private TreeMap tree;
  String blueColor = "#" + "B8DFEF";
  
  public BlueSheetChecker() {

    setJMenuBar(new MenuBar(this, new DecodeAction()));

    JPanel p1 = new JPanel();
    p1.setPreferredSize(new Dimension(400, 81));
    p1.setLayout(new GridLayout(5, 1));
    p1.setBorder(new LineBorder(Color.BLACK));
    ButtonGroup bluesheets = new ButtonGroup();
    JRadioButton one = new JRadioButton("I. Past Tense");
    JRadioButton three = new JRadioButton("III. First or Second Person");
    JRadioButton four = new JRadioButton("IV. Vague 'this' or 'which'");
    JRadioButton six = new JRadioButton("VI. Pronoun Case");
    JRadioButton eight = new JRadioButton("VIII. Apostrophe Problem");
    JRadioButton nine = new JRadioButton("IX. Avoid passive voice.");
    JRadioButton twelve = new JRadioButton("XII. Progressive Tense");
    JRadioButton thirteen = new JRadioButton("XIII. Problem in Quotation Form");
    
    bluesheets.add(one);
    bluesheets.add(three);
    bluesheets.add(four);
    bluesheets.add(six);
    bluesheets.add(eight);
    bluesheets.add(nine);
    bluesheets.add(twelve);
    bluesheets.add(thirteen);
    
    p1.add(one);
    p1.add(three);
    p1.add(four);
    p1.add(six);
    p1.add(eight);
    p1.add(nine);
    p1.add(twelve);
    p1.add(thirteen);
    p1.setBackground(Color.decode(blueColor));
   
    one.addActionListener(new CustomActionListenerOne());
    three.addActionListener(new CustomActionListenerThree());
    four.addActionListener(new CustomActionListenerFour());
    six.addActionListener(new CustomActionListenerSix());
    eight.addActionListener(new CustomActionListenerEight());
    nine.addActionListener(new CustomActionListenerNine());
    twelve.addActionListener(new CustomActionListenerTwelve());
    thirteen.addActionListener(new CustomActionListenerThirteen());
    
    rule = new JLabel();
    rule.setVerticalAlignment(JLabel.TOP);
    rule.setPreferredSize(new Dimension(100,350));
    JScrollPane scroller = new JScrollPane(rule, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    rule.setOpaque(true);
    rule.setBackground(Color.decode(blueColor));
    JPanel p2 = new JPanel();
    p2.setPreferredSize(new Dimension(200, 60));
    p2.setLayout(new GridBagLayout());
    p2.setBorder(new LineBorder(Color.BLACK));
    GridBagConstraints p2gbc = new GridBagConstraints();
    p2gbc.gridx = 0;
    p2gbc.gridy = 0;
    p2gbc.gridwidth = 1;
	p2gbc.gridheight = 1;
	p2gbc.weightx = 1.0;
	p2gbc.weighty = 1.0;
	p2gbc.anchor = GridBagConstraints.NORTHWEST;
	p2gbc.fill = GridBagConstraints.BOTH;
    p2.add(scroller, p2gbc);
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
  
  public void createStuff(String text) {
	  Essay essay = new Essay(text);
	  tree = essay.getTree();
  }
	
  class CustomActionListenerOne implements ActionListener{
      public void actionPerformed(ActionEvent e) {
    	  rule.setText(PastTenseStrategy.getRule());
      }
   }
  
  class CustomActionListenerThree implements ActionListener{
      public void actionPerformed(ActionEvent e) {
    	  rule.setText(FirstSecondPersonStrategy.getRule());
      }
   }
  
  class CustomActionListenerFour implements ActionListener{
      public void actionPerformed(ActionEvent e) {
          rule.setText(ThisWhichStrategy.getRule());
          ThisWhichStrategy thisWhich = new ThisWhichStrategy();
          ListNode2[] array = thisWhich.findInEssay(tree);
          displaySentences(array);
      }
   }
  
  class CustomActionListenerSix implements ActionListener{
      public void actionPerformed(ActionEvent e) {
          rule.setText(AppropriateCasePronounsStrategy.getRule());
          AppropriateCasePronounsStrategy appCase = new  AppropriateCasePronounsStrategy();
          ListNode2[] array = appCase.findInEssay(tree);
          displaySentences(array);
      }
   }
  
  class CustomActionListenerEight implements ActionListener{
      public void actionPerformed(ActionEvent e) {
    	  rule.setText(ApostropheStrategy.getRule());
    	  ApostropheStrategy apostrophe = new  ApostropheStrategy();
          ListNode2[] array = apostrophe.findInEssay(tree);
          displaySentences(array);
      }
   }
  class CustomActionListenerNine implements ActionListener{
      public void actionPerformed(ActionEvent e) {
          rule.setText(PassiveVoiceStrategy.getRule());
      }
   }
  class CustomActionListenerTwelve implements ActionListener{
      public void actionPerformed(ActionEvent e) {
    	  rule.setText(ProgressiveTenseStrategy.getRule());
      }
   }
  class CustomActionListenerThirteen implements ActionListener{
      public void actionPerformed(ActionEvent e) {
          rule.setText(QuotationStrategy.getRule());
          QuotationStrategy quote = new QuotationStrategy();
          ListNode2[] array = quote.findInEssay(tree);
          displaySentences(array);
      }
   }
  
  public void displaySentences(ListNode2[] array) {
	  for(int i = 0; i < array.length; i++) {
		  ListNode2 node = array[i];
		  sentence.setText((String)(((WordLoc)node.getValue()).getSentence().getValue()));
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
    BlueSheetChecker window = new BlueSheetChecker();
    window.setDefaultCloseOperation(EXIT_ON_CLOSE);
    window.pack();
    window.setVisible(true);
    window.setMinimumSize(window.getSize());
  }
}

