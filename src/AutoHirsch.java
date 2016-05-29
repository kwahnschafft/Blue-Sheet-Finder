/**
 * AutoHirsch.java
 * Authors: Kiara Wahnschafft, Shannon Wing, Kelly Finke
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
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;

public class AutoHirsch extends JFrame {

  private JTextArea essay;
  private JTextArea sentence;
  private JTextArea copy;
  private String copied;
  private JLabel rule;
  private TreeMap tree;
  private ListNode2 current;
  private JButton next;
  private JButton previous;
  private JButton change;
  private JButton correct;
  private boolean newInsert;
  private boolean newRemove;
  private JFrame frame;
  private MenuBar thisMenu;
  Essay essayEssay;
  private final String blueColor = "#B8DFEF";
  
  /*
   * constructor that instantiates the gui, creating the whole JPanel 
   */
  public AutoHirsch() throws Exception {

	thisMenu = new MenuBar(this, new EssayAction());
    setJMenuBar(thisMenu);

    //create panel that displays the input text being checked
    Font font = new Font("Monospaced", Font.PLAIN, 12);
    essay = new JTextArea(30, 50);
    essay.setEditable(false);
    essay.setFont(font);
    essay.setLineWrap(true);
    essay.setWrapStyleWord(true);
    JScrollPane areaScrollPaneIn = new JScrollPane(essay);
    areaScrollPaneIn.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    
    //create panel with the blue sheet radio buttons
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
    
    //add the blue sheet radio buttons to one button group 
    //(so that only one button is selected at one)
    bluesheets.add(one);
    bluesheets.add(three);
    bluesheets.add(four);
    bluesheets.add(six);
    bluesheets.add(eight);
    bluesheets.add(nine);
    bluesheets.add(twelve);
    bluesheets.add(thirteen);
   
    //add action listeners to the blue sheet buttons
    one.addActionListener(new CustomActionListenerOne());
    three.addActionListener(new CustomActionListenerThree());
    four.addActionListener(new CustomActionListenerFour());
    six.addActionListener(new CustomActionListenerSix());
    eight.addActionListener(new CustomActionListenerEight());
    nine.addActionListener(new CustomActionListenerNine());
    twelve.addActionListener(new CustomActionListenerTwelve());
    thirteen.addActionListener(new CustomActionListenerThirteen());
    
    //add the blue sheet buttons to the panel
    p1.add(one);
    p1.add(three);
    p1.add(four);
    p1.add(six);
    p1.add(eight);
    p1.add(nine);
    p1.add(twelve);
    p1.add(thirteen);
    p1.setBackground(Color.decode(blueColor));
    
    //create panel that holds the current sentence being changed or deemed correct
    sentence = new JTextArea(5, 20);
    sentence.setFont(font);
    sentence.setLineWrap(true);
    sentence.setWrapStyleWord(true);
    sentence.getDocument().addDocumentListener(new CustomDocumentListenerChangedSentence());
    JScrollPane sentenceScrollPane = new JScrollPane(sentence);
    sentenceScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    
    //create panel that displays the current blue sheet rule
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
    
    //create panel that has the "change" and "correct" buttons
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
    
    //create panel that has the previous and next buttons 
    //and the panel just created with the "change" and "correct" buttons
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
	  
     //create panel for the HirschoMeter
     JPanel p4 = new JPanel();
     p4.setPreferredSize(new Dimension(200, 75));
     p4.setLayout(new GridLayout(1, 5));
     p4.setBorder(new LineBorder(Color.BLACK));
     BufferedImage image = null;
     try
     {
       image = ImageIO.read(new File("leader.jpg"));
     }
     catch (Exception e)
     {
       e.printStackTrace();
       System.exit(1);
     }
     ImageIcon img = new ImageIcon(image);
     JLabel label = new JLabel("");
     label.setIcon(img);
     label.setPreferredSize(new Dimension(300,100));
     p4.add( label, BorderLayout.CENTER );
     
     //create final panel and add all the previous panels to this panel
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
  
  /*
   * reads in the .wav file "welcome.wav" and plays it
   */
  public static void read() throws Exception {

	    //choose the file to use (in this case, welcome.wav)
	    File file = new File("welcome.wav");
	    AudioInputStream ais = AudioSystem.getAudioInputStream(file);

	    //load the file into a Clip object
	    DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat());
	    Clip cl = (Clip) AudioSystem.getLine(info);
	    cl.open(ais);

	    //stop reading when clip ends
	    cl.addLineListener(new LineListener() {
	        public void update(LineEvent event) {
	            if (event.getType() == LineEvent.Type.STOP) {
	                event.getLine().close();
	            }
	        }

	    });

	    //play the sound stored in the clip object
	    cl.start();
	}
  
  public void makeEssayAndTree(String text) {
	  essayEssay = new Essay(text);
	  newRemove = true;
	  tree = essayEssay.getTree();
  }

  public void updateDisplay() {
	  String currentEssay = "";
	  for(ListNode2 node = essayEssay.getSentences(); node != null; node = node.getNext())  {
		  currentEssay += node.getValue() + " ";
	  }
	  essay.setText(currentEssay);
  }
  
  public void checkButtons() {
	  if(current != null && current.getValue() != null) {
		  if(getPreviousNotEmpty() == null) {
			  previous.setEnabled(false);
		  }
		  if(getNextNotEmpty() == null) {
			  next.setEnabled(false);
		  }
		  if(current.getValue() != null && getPreviousNotEmpty() != null) {
			  previous.setEnabled(true);
		  }
		  if(current.getValue() != null && getNextNotEmpty() != null)
			  next.setEnabled(true);
	  }
	  else if (current == null || current.getValue() == null) {
		  previous.setEnabled(false);
		  next.setEnabled(false);
		  change.setEnabled(false);
		  correct.setEnabled(false);
	  }
  }
  public void displaySentences(ListNode2[] array) {
	  next.setEnabled(false);
	  previous.setEnabled(false);
	  createList(array);
	  if(current != null && current.getValue() != null) {
		  displaySentence(current);
		  if(current.getNext() != null)
			  next.setEnabled(true);
	  }
	  else {
		  sentence.setText("");
	  }
	  checkButtons();
	  		
  }
  
  public void createList(ListNode2[] array) {
	  int i = 0;
	  while(i < array.length && array[i] == null){ //find first linked list that is populated
		  i++;
	  }
	  if(i >= array.length){
		  //no populated lists
		  current = null;
		  return;
	  }
	  else{
		  ListNode2 headHead = new ListNode2("Temp Node"); //head of final loop
		  ListNode2 tail = addNodeDuplicates(array[i], headHead);
		  i ++;
		  //add lists from other words
		  while(i < array.length){ //add other lists
			  if(array[i] != null){//add list to headHead
				  tail = addNodeDuplicates(array[i], tail);
			  }
			  i++;
		  }

		  //remove temporary headHead
		  headHead = headHead.getNext();
		  if(headHead == null){
			  //TODO no words, idk what to do here
		  }else{
			  headHead.setPrevious(null);
		  }
		  current = headHead;
	  }
  }
  
  //creates duplicates of nodes to be added to list of potential errors
  private ListNode2 addNodeDuplicates(ListNode2 head, ListNode2 tail){ //head to be copied, tail of larger list
	  ListNode2 node = head;
	  do{
		  tail.setNext(new ListNode2(node.getValue(), tail, null));
		  tail = tail.getNext();
		  node = node.getNext();
	  }while(node != head);
	  return tail;
  }
  
/*  //combines two doubly linked circular lists
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
  
  private ListNode2 duplicate(ListNode2 node){
	  return new ListNode2(node.getValue());
  }*/

  public void displaySentence(ListNode2 node) {
	  WordLoc sentenceLoc = (WordLoc)(node.getValue());
	  String displayedSentence = ((String)(sentenceLoc).getSentenceNode().getValue());
	  sentence.setText(displayedSentence);
	  Highlighter highlighter = sentence.getHighlighter();
      int start = sentenceLoc.getWordIndex();
      int end = start;
      //apostrophe error highlight
      if(displayedSentence.charAt(start) == '\'') {
    	  while(end < displayedSentence.length() && displayedSentence.charAt(end) == '\'')
    		  end++;
      }
      //quotation error highlight
      else if(displayedSentence.charAt(start) == '\"') {
    	  while(end < displayedSentence.length() && displayedSentence.charAt(end) == '\"')
    		  end++;
      }
      //other error highlight
      else {
	      while(end < displayedSentence.length() && displayedSentence.charAt(end) != '.' && displayedSentence.charAt(end) != ' ' && Character.isLetter(displayedSentence.charAt(end))) {
	    	  end++;
	      }
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
    essay.setText(text.toString());
    essay.setCaretPosition(0);
  }

  public void setSentenceText(String text) {
    sentence.setCaretPosition(0);
  }
  
  /******************************************************************/
  /***************     Action Listener Classes     ****************/

  /*
  * Set the rule box to the past tense rule + use the past tense strategy
  * to obtain and display the sentences with potential past tense errors
  */
  class CustomActionListenerOne implements ActionListener{ //database strategy
      public void actionPerformed(ActionEvent e) {
    	  newInsert = false;
    	  PastTenseStrategy past = new PastTenseStrategy();
    	  rule.setText(past.getRule());
          if(!essay.getText().equals("")) {
        	  ArrayList<ListNode2> list = past.findInDatabase(tree);
        	  ListNode2[] array = new ListNode2[list.size()];
        	  for(int i = 0; i < list.size(); i++) {
        		  array[i] = list.get(i);
        	  }
        	  displaySentences(array);
          }
      }
   }
  
  /*
   * Set the rule box to the first or second person rule + use the first 
   * or second person strategy to obtain and display the sentences with 
   * potential first or second person errors
   */
  class CustomActionListenerThree implements ActionListener{ //essay strategy
      public void actionPerformed(ActionEvent e) {
    	  newInsert = false;
    	  FirstSecondPersonStrategy firstSecond = new FirstSecondPersonStrategy();
    	  rule.setText(firstSecond.getRule());
    	  if(!essay.getText().equals("")) {
          	  ListNode2[] array = firstSecond.findInEssay(tree);
          	  displaySentences(array);
          }
    	  
    	  //TODO delete this 
    	 /*
    	  ListNode2 head = essayEssay.getTree().get("me");
    	  ListNode2 node = head;
    	  do{
    		  System.out.println(((WordLoc)node.getValue()).toString());
    		  System.out.println("Prev: " + node.getPrevious().getValue());
    		  System.out.println("Next: " + node.getNext().getValue());
    		  System.out.println("------------------------------------------");
    		  node = node.getNext();
    	  }while(node != head);
    	  */
      }
   }
  
  /*
   * Set the rule box to the this, which rule + use the this, which strategy
   * to obtain and display the sentences with potential this, which errors
   */
  class CustomActionListenerFour implements ActionListener{ //essay strategy
      public void actionPerformed(ActionEvent e) {
    	  newInsert = false;
    	  ThisWhichStrategy tw = new ThisWhichStrategy();
          rule.setText(tw.getRule());
          if(!essay.getText().equals("")) {
          	  ListNode2[] array = tw.findInEssay(tree);
          	  //System.out.println(((WordLoc)(array[0].getValue())).getSentenceString());
          	  displaySentences(array);
          }
      }
   }
  
  /*
   * Set the rule box to the pronoun case rule + use the past tense strategy
   * to obtain and display the sentences with potential pronoun case errors
   */
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
  
  /*
   * Set the rule box to the pronoun case rule + use the pronoun case strategy
   * to obtain and display the sentences with potential pronoun case errors
   */
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
  
  /*
   * Set the rule box to the passive voice rule + use the passive voice strategy
   * to obtain and display the sentences with potential passive voic errors
   */
  class CustomActionListenerNine implements ActionListener{ //database strategy
      public void actionPerformed(ActionEvent e) {
    	  newInsert = false;
    	  PassiveVoiceStrategy passiveVoice = new PassiveVoiceStrategy();
          rule.setText(passiveVoice.getRule());
          if(!essay.getText().equals("")) {
        	  ArrayList<ListNode2> list = passiveVoice.findInDatabase(tree);
        	  ListNode2[] array = new ListNode2[list.size()];
        	  for(int i = 0; i < list.size(); i++) {
        		  array[i] = list.get(i);
        	  }
        	  displaySentences(array);
          }
      }
   }
  class CustomActionListenerTwelve implements ActionListener{ //database strategy
      public void actionPerformed(ActionEvent e) {
    	  newInsert = false;
    	  ProgressiveTenseStrategy progressive = new ProgressiveTenseStrategy();
          rule.setText(progressive.getRule());
          if(!essay.getText().equals("")) {
        	  ArrayList<ListNode2> list = progressive.findInDatabase(tree);
        	  ListNode2[] array = new ListNode2[list.size()];
        	  for(int i = 0; i < list.size(); i++) {
        		  array[i] = list.get(i);
        	  }
        	  displaySentences(array);
          }
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
		  String changedSentence = sentence.getText();
		  ListNode2 nodeBeingChanged = current;
		  if(next.isEnabled())
			  next.doClick();
		  else if(previous.isEnabled())
			  previous.doClick();
		  else {
			  sentence.setText("");
		  }
    	  essayEssay.disconnectAndAdd(nodeBeingChanged, changedSentence);
    	  System.out.println(changedSentence);
    	  checkButtons();
    	  updateDisplay();
      }
  }
  
  class CustomActionListenerCorrect implements ActionListener {
	  public void actionPerformed(ActionEvent e) {
		  ListNode2 nodeBeingRemoved = current;
		  if(next.isEnabled())
			  next.doClick();
		  else if(previous.isEnabled())
			  previous.doClick();
		  else {
			  sentence.setText("");
		  }
    	  essayEssay.removeCorrected(nodeBeingRemoved);
    	  checkButtons();
    	  updateDisplay();
      }
  }
  
  class CustomDocumentListenerChangedSentence implements DocumentListener{
	    public void insertUpdate(DocumentEvent e) {
	        if(!newInsert) {
	        	correct.setEnabled(true);
	        	change.setEnabled(false);
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
	        	change.setEnabled(true);
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
  
  class CustomDocumentListenerCopyMade implements DocumentListener{
	    public void insertUpdate(DocumentEvent e) {
	    	copied = copy.getText();
	    }
	    public void removeUpdate(DocumentEvent e) {
	    	copied = copy.getText();
	    }
	    public void changedUpdate(DocumentEvent e) {
	        //Plain text components do not fire these events
	    }
 }
  
  class CustomActionListenerPrevious implements ActionListener{
	  public void actionPerformed(ActionEvent e) {
		  newInsert = false;
		  newRemove = false;
		  current = getPreviousNotEmpty();
		  correct.setEnabled(true);
		  change.setEnabled(false);
		  displaySentence(current);
		  checkButtons();
      }
   }
  
  class CustomActionListenerNext implements ActionListener{
	  public void actionPerformed(ActionEvent e) {
		  newInsert = false;
		  newRemove = false;
		  current = getNextNotEmpty();
		  displaySentence(current);
		  correct.setEnabled(true);
		  change.setEnabled(false);
		  checkButtons();
      }
   }
  
  private ListNode2 getNextNotEmpty() {
	  ListNode2 node = current;
	  do{
		  node= node.getNext();
		  if(node == null){
			  return null;
		  }
	  }while(node.getValue() == null);
	  return node;
  }
  
  private ListNode2 getPreviousNotEmpty() {
	  ListNode2 node = current;
	  do{
		  node= node.getPrevious();
		  if(node == null){
			  return null;
		  }
	  }while(node.getValue() == null);
	  return node;
  }
  class CustomActionListenerPaste implements ActionListener{
	  public void actionPerformed(ActionEvent e) {
		  frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
		  thisMenu.parseAndCreateEssay(copied);
      }
  }
  
  private class EssayAction implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      String cmd = ((AbstractButton)e.getSource()).getActionCommand();

      if ("Insert...".equals(cmd)) {
    	  frame = new JFrame("Insert your essay here");
    	  frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	  JPanel p = new JPanel();
    	  p.setLayout(new GridBagLayout());
    	  copy = new JTextArea(30,50);
    	  Font font = new Font("Monospaced", Font.PLAIN, 12);
    	  copy.setFont(font);
    	  copy.setLineWrap(true);
    	  copy.setWrapStyleWord(true);
    	  copy.getDocument().addDocumentListener(new CustomDocumentListenerCopyMade());
    	  JScrollPane sentenceScrollPane = new JScrollPane(copy);
    	  sentenceScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    	  JButton closeAndPaste = new JButton("Insert Text");
    	  closeAndPaste.addActionListener(new CustomActionListenerPaste());
    	  GridBagConstraints gbc = new GridBagConstraints();
    	  gbc.gridx = 0;
    	  gbc.gridy = 0;
    	  gbc.weightx = 1.0;
    	  gbc.weighty = 1.0;
    	  gbc.gridheight = 2;
    	  gbc.anchor = GridBagConstraints.NORTHWEST;
    	  gbc.fill = GridBagConstraints.BOTH;
    	  p.add(sentenceScrollPane, gbc);
    	  gbc.gridy = 2;
    	  gbc.gridheight = 1;
    	  p.add(closeAndPaste, gbc);
    	  frame.add(p);
  
    	  frame.pack();
    	  frame.setVisible(true);
    	  frame.setMinimumSize(frame.getSize());
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
    AutoHirsch window = new AutoHirsch();
    window.setDefaultCloseOperation(EXIT_ON_CLOSE);
    window.pack();
    window.setVisible(true);
    window.setMinimumSize(window.getSize());
    read();
  }
}
