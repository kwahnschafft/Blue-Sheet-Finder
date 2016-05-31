/**
 * MenuBar.java
 * Creates the Menu bar for AutoHirsch, allowing the user to open a file,
 * insert text, save the edited text, or exit the program
 * Citation: Used parts of Cryptogram menu to write this class
 * 
 * Written By: Kiara Wahnschafft, Kelly Finke, Shannon Wing extend Java Methods
 * Date: 5/31/16
 */

//import statements
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class MenuBar extends JMenuBar
{
  private AutoHirsch bluesheet;
  private JMenuItem openItem, saveItem, exitItem;
  public static final Charset UTF_8 = StandardCharsets.UTF_8;

  /*
   * creates the menu bar of the AutoHirsch program
   * adds a drop down menu to the bar that allows the
   * user to insert text from a word document, type in
   * text, or quit the program
   */
  public MenuBar(AutoHirsch checker, ActionListener essayAction)
  {
    bluesheet = checker;

    JMenu fileMenu = new JMenu("File");
    fileMenu.setMnemonic('F');

    FileAction fileAction = new FileAction();
    //open menu item
    openItem = new JMenuItem("Open...");
    openItem.setMnemonic('O');
    openItem.addActionListener(fileAction);
    //insert menu item
    JMenuItem insertItem = new JMenuItem("Insert...");
    insertItem.setMnemonic('D');
    insertItem.addActionListener(essayAction);
    //save menu item
    saveItem = new JMenuItem("Save...");
    saveItem.setMnemonic('S');
    saveItem.addActionListener(fileAction);
    //exit menu item
    exitItem = new JMenuItem("Exit");
    exitItem.setMnemonic('x');
    exitItem.addActionListener(fileAction);
    //add the open, insert, and save menu items to the file menu 
    fileMenu.add(openItem);
    fileMenu.add(insertItem);
    fileMenu.add(saveItem);
    fileMenu.addSeparator();
    fileMenu.add(exitItem);

    //add the file menu to the AutoHirsch window
    add(fileMenu);
  }

  /******************************************************************/
  /***************          Action Listeners         ****************/
  /******************************************************************/

  private class FileAction implements ActionListener
  {
    private String pathName = System.getProperty("user.dir") + "/";

    public void actionPerformed(ActionEvent e)
    {
      JMenuItem m = (JMenuItem)e.getSource();
      //opens a word document that the user chooses, saves its text as
      //a string and calls the parseAndCreateEssay helper method on this
      //text
      if (m == openItem)
      {
        JFileChooser fileChooser = new JFileChooser(pathName);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Microsoft Word Document (.docx)", "docx"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        int result = fileChooser.showOpenDialog(bluesheet);
        if (result == JFileChooser.CANCEL_OPTION)
          return;

        File file = fileChooser.getSelectedFile();
        pathName = file.getAbsolutePath();
        
      //ensure that the document being opened is a .docx document
        if(!(pathName.substring(pathName.length() - 5).equals(".docx"))) {
        	JOptionPane.showMessageDialog(bluesheet, "Please choose a .docx word document.");
        }
        //if the document the user chooses is a word document
        else if(pathName.substring(pathName.length() - 5).equals(".docx")) {
	        FileInputStream fis = null;
			try {
				fis = new FileInputStream(file.getAbsolutePath());
			} catch (FileNotFoundException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
	       XWPFDocument document = null;
			try {
				document = new XWPFDocument(fis);
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
	        XWPFWordExtractor extractor = null;
			extractor = new XWPFWordExtractor(document);
	        String rawText = extractor.getText();
	        String displayText = WordExtractor.stripFields(rawText);
	        parseAndCreateEssay(displayText);
        }
      }
      //save the edited text as a word file (.docx) or text file (.txt)
      else if (m == saveItem)
      {
        JFileChooser fileChooser = new JFileChooser(pathName);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Microsoft Word Document (.docx)", "docx"));
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text File (.txt)", "txt", "text"));
        int result = fileChooser.showSaveDialog(bluesheet);
        if (result == JFileChooser.CANCEL_OPTION)
          return;

        File file = fileChooser.getSelectedFile();
        if (file != null) {
          pathName = file.getAbsolutePath();
        }
        
        //ensure that the document is going to be saved either as .docx or .txt
        if(!(pathName.substring(pathName.length() - 5).equals(".docx")) && !(pathName.substring(pathName.length() - 4).equals(".txt"))) {
        	JOptionPane.showMessageDialog(bluesheet, "Please write either .docx or .txt after your file name.");
        }
        else {
	        //write to a new word file (.docx) or text file (.txt)
	        XWPFDocument document = new XWPFDocument();
	        XWPFParagraph tmpParagraph = document.createParagraph();
	        XWPFRun tmpRun = tmpParagraph.createRun();
	        tmpRun.setText(bluesheet.getEssayText());
	        try {
				document.write(new FileOutputStream(new File(pathName)));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
      }
      //exit the program
      else if (m == exitItem)
      {
        System.exit(0);
      }

    }
  }
  
  /*
   * ensures that special characters are correct if the user is
   * on a mac, calls auto hirsch's makeEssayAndTree method on the
   * text in the inserted document or the inserted text and sets the
   * essay panel's text to the text in the inserted document or 
   * the inserted text
   */
  public void parseAndCreateEssay(String str) {
	  String osName = System.getProperty("os.name").toLowerCase();
	  boolean isMacOs = osName.startsWith("mac os x");
	  String result1 = str;
	  if (isMacOs) { //mac-specific character encoding
		  result1 = result1.replace( (char)2424, (char)'\n');
		  result1 = result1.replace( (char)145, (char)'\'');
	      result1 = result1.replace( (char)8216, (char)'\''); // left single quote
	      result1 = result1.replace( (char)146, (char)'\'');
	      result1 = result1.replace( (char)8217, (char)'\''); // right single quote
	      result1 = result1.replace( (char)147, (char)'\"');
	      result1 = result1.replace( (char)148, (char)'\"');
	      result1 = result1.replace( (char)8220, (char)'\"'); // left double
	      result1 = result1.replace( (char)8221, (char)'\"'); // right double
	      result1 = result1.replace( (char)8211, (char)'-' ); // em dash   
	      result1 = result1.replace( (char)150, (char)'-' );
	  }
      bluesheet.makeEssayAndTree(result1);
      bluesheet.setEssayText(result1);
  }
}