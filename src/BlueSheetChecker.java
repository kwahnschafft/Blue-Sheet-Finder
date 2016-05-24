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
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.swing.text.Highlighter.HighlightPainter;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

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
  private ListNode2 current;
  private JButton next;
  private JButton previous;
  private JButton change;
  private JButton correct;
  private boolean newInsert;
  private boolean newRemove;
  Essay essayEssay;
  String blueColor = "#B8DFEF";
  
  public BlueSheetChecker() throws Exception {

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

    essay = new JTextArea(30, 50);
    essay.setEditable(false);
    essay.setFont(font);
    essay.setLineWrap(true);
    essay.setWrapStyleWord(true);
    JScrollPane areaScrollPaneIn = new JScrollPane(essay);
    areaScrollPaneIn.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    
    JPanel changes = new JPanel();
    changes.setPreferredSize(new Dimension(100,50));
    changes.setLayout(new GridLayout(2,1));
    correct = new JButton("Correct");
    correct.addActionListener(new CustomActionListenerCorrect());
    correct.setEnabled(false);
    change = new JButton("Change");
    change.addActionListener(new CustomActionListenerChange());
    change.setEnabled(false);
    changes.add(correct);
    changes.add(change);
    
    JPanel p3 = new JPanel();
    p3.setPreferredSize(new Dimension(100, 50));
    p3.setLayout(new GridLayout(1,3));
    p3.setBorder(new LineBorder(Color.BLACK));
    previous = new JButton("Previous");
    previous.addActionListener(new CustomActionListenerPrevious());
    previous.setEnabled(false);
    p3.add(previous);
    p3.add(changes);
    next = new JButton("Next");
    next.addActionListener(new CustomActionListenerNext());
    next.setEnabled(false);
    p3.add(next);
    
    JPanel p4 = new JPanel();
    p4.setPreferredSize(new Dimension(200, 75));
    p4.setBorder(new LineBorder(Color.BLACK));
    
    sentence = new JTextArea(5, 20);
    sentence.setFont(font);
    sentence.setLineWrap(true);
    sentence.setWrapStyleWord(true);
    sentence.getDocument().addDocumentListener(new CustomDocumentListenerChangedSentence());
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
	 
	 gbc.gridx = 0;
	 gbc.gridy = 4;
	 gbc.gridheight = 1;
	 gbc.gridwidth = 2;
	 panel.add(p4, gbc);
	 
	 Container c = getContentPane();
	 c.add(panel, BorderLayout.CENTER);
	 c.setMinimumSize(c.getSize());
  }
  
    public static void read()
    {
      try {
    	  System.out.println("hey");
    	  Clip clip = (Clip) AudioSystem.getClip();
    	  clip.open(AudioSystem.getAudioInputStream(new File("hi.wav")));
    	  clip.start();
      }
      catch (Exception ex) {
    	  
      }
  }
  
  public void createStuff(String text) {
	  essayEssay = new Essay(text);
	  newRemove = true;
	  tree = essayEssay.getTree();
  }
	
  class CustomActionListenerOne implements ActionListener{ //database strategy
      public void actionPerformed(ActionEvent e) {
    	  newInsert = false;
    	  PastTenseStrategy past = new PastTenseStrategy();
    	  rule.setText(past.getRule());
      }
   }
  
  class CustomActionListenerThree implements ActionListener{ //essay strategy
      public void actionPerformed(ActionEvent e) {
    	  newInsert = false;
    	  FirstSecondPersonStrategy firstSecond = new FirstSecondPersonStrategy();
    	  rule.setText(firstSecond.getRule());
    	  if(!essay.getText().equals("")) {
          	  ListNode2[] array = firstSecond.findInEssay(tree);
          	  displaySentences(array);
          }
      }
   }
  
  class CustomActionListenerFour implements ActionListener{ //essay strategy
      public void actionPerformed(ActionEvent e) {
    	  newInsert = false;
    	  ThisWhichStrategy tw = new ThisWhichStrategy();
          rule.setText(tw.getRule());
          if(!essay.getText().equals("")) {
          	  ListNode2[] array = tw.findInEssay(tree);
          	  displaySentences(array);
          }
      }
   }
  
  class CustomActionListenerSix implements ActionListener{ //essay strategy
      public void actionPerformed(ActionEvent e) {
    	  newInsert = false;
    	  AppropriateCasePronounsStrategy approp = new AppropriateCasePronounsStrategy();
          rule.setText(approp.getRule());
          if(!essay.getText().equals("")) {
        	  ListNode2[] array = approp.findInEssay(tree);
              displaySentences(array);
          }
      }
   }
  
  class CustomActionListenerEight implements ActionListener{ //essay strategy
      public void actionPerformed(ActionEvent e) {
    	  newInsert = false;
    	  ApostropheStrategy apost = new ApostropheStrategy();
          rule.setText(apost.getRule());
    	  if(!essay.getText().equals("")) {
    		  ListNode2[] array = apost.findInEssay(tree);
              displaySentences(array);
    	  }
      }
   }
  class CustomActionListenerNine implements ActionListener{ //database strategy
      public void actionPerformed(ActionEvent e) {
    	  newInsert = false;
    	  PassiveVoiceStrategy passiveVoice = new PassiveVoiceStrategy();
          rule.setText(passiveVoice.getRule());
      }
   }
  class CustomActionListenerTwelve implements ActionListener{ //database strategy
      public void actionPerformed(ActionEvent e) {
    	  newInsert = false;
    	  ProgressiveTenseStrategy progressive = new ProgressiveTenseStrategy();
          rule.setText(progressive.getRule());
      }
   }
  class CustomActionListenerThirteen implements ActionListener{ //essay strategy
      public void actionPerformed(ActionEvent e) {
    	  newInsert = false;
    	  QuotationStrategy quotation = new QuotationStrategy();
          rule.setText(quotation.getRule());
          if(!essay.getText().equals("")) {
	          ListNode2[] array = quotation.findInEssay(tree);
	          displaySentences(array);
          }
      }
   }
 
  class CustomActionListenerChange implements ActionListener {
	  public void actionPerformed(ActionEvent e) {
    	  essayEssay.disconnectAndAdd(current, sentence.getText());
      }
  }
  class CustomActionListenerCorrect implements ActionListener {
	  public void actionPerformed(ActionEvent e) {
		  ListNode2 nodeBeingRemoved = current;
		  next.doClick();
    	  essayEssay.removeCorrected(nodeBeingRemoved);
    	  checkButtons();
      }
  }
  class CustomDocumentListenerChangedSentence implements DocumentListener{
	  String newString = "";
	    public void insertUpdate(DocumentEvent e) {
	        if(!newInsert) {
	        	correct.setEnabled(true);
	        	newInsert = true;
	        }
	        else {
	        	correct.setEnabled(false);
	        	change.setEnabled(true);
	        }
	    }
	    public void removeUpdate(DocumentEvent e) {
	    	if(!newRemove) {
	        	correct.setEnabled(true);
	        	newRemove = true;
	        }
	        else {
	        	correct.setEnabled(false);
	        	change.setEnabled(true);
	        }
	    	
	    }
	    public void changedUpdate(DocumentEvent e) {
	        //Plain text components do not fire these events
	    }
   }
  
  class CustomActionListenerPrevious implements ActionListener{
	  public void actionPerformed(ActionEvent e) {
		  newInsert = false;
		  newRemove = false;
		  current = current.getPrevious();
		  correct.setEnabled(true);
		  change.setEnabled(false);
		  System.out.println("prev button pressed");
		  displaySentence(current);
		  if(current.getNext() == null)
			  next.setEnabled(false);
		  else
			  next.setEnabled(true);
		  if(current.getPrevious() == null)
			  previous.setEnabled(false);
      }
   }
  class CustomActionListenerNext implements ActionListener{
	  public void actionPerformed(ActionEvent e) {
		  newInsert = false;
		  newRemove = false;
		  current = current.getNext();
		  displaySentence(current);
		  correct.setEnabled(true);
		  change.setEnabled(false);
		  if(current.getPrevious() == null)
			  previous.setEnabled(false);
		  else
			  previous.setEnabled(true);
		  if(current.getNext() == null)
			  next.setEnabled(false);
      }
   }
  
  public void checkButtons() {
	  if(current != null && current.getPrevious() == null)
		  previous.setEnabled(false);
	  if(current != null && current.getNext() == null)
		  next.setEnabled(false);
  }
  public void displaySentences(ListNode2[] array) {
	  next.setEnabled(false);
	  previous.setEnabled(false);
	  createList(array);
	  if(current != null) {
		  displaySentence(current);
		  if(current.getNext() != null)
			  next.setEnabled(true);
	  }
	  		
  }
  
  public void createList(ListNode2[] array) {
	  int i = 0;
	  while(i < array.length && array[i] == null){ //find first linked list that is populated
		  i++;
	  }
	  if(i >= array.length){
		  //no populated lists
		  return;
	  }
	  else{
		  ListNode2 headHead = array[i]; //head of final loop
		  while(i < array.length){ //add other lists
			  if(array[i] != null){//add list to headHead
				  addLinkedLists(array[i], headHead);
			  }
			  i++;
		  }
		  //make non-circular
		  headHead.getPrevious().setNext(null);
		  headHead.setPrevious(null);
		  current = headHead;
	  }
  }
  
  //combines two doubly linked circular lists
  private void addLinkedLists(ListNode2 subList, ListNode2 bigList){
	  ListNode2 subLast = subList.getPrevious();
	  ListNode2 bigLast = bigList.getPrevious();
	  if(bigList != null) 
		  bigList.setPrevious(subLast);
	  if(subLast != null)
		  subLast.setNext(bigList);
	  if(bigLast != null)
		  bigLast.setNext(subList);
	  if(subList != null)
		  subList.setPrevious(bigLast);
  }

  public void displaySentence(ListNode2 node) {
	  WordLoc sentenceLoc = (WordLoc)(node.getValue());
	  String displayedSentence = ((String)(sentenceLoc).getSentenceNode().getValue());
	  sentence.setText(displayedSentence);
	  Highlighter highlighter = sentence.getHighlighter();
      int start = sentenceLoc.getWordIndex();
      int end = start;
      while(end < displayedSentence.length() && displayedSentence.charAt(end) != '.' && displayedSentence.charAt(end) != ' ') {
    	  end++;
      }
      try {
		highlighter.addHighlight(start, end, new DefaultHighlighter.DefaultHighlightPainter(Color.pink));
	  } 
      catch (BadLocationException e) {
		// Auto-generated catch block
		e.printStackTrace();
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
  /**
 * @throws Exception ****************************************************************/

  public static void main(String[] args) throws Exception
  {
    BlueSheetChecker window = new BlueSheetChecker();
    read();
    window.setDefaultCloseOperation(EXIT_ON_CLOSE);
    window.pack();
    window.setVisible(true);
    window.setMinimumSize(window.getSize());
  }
}
