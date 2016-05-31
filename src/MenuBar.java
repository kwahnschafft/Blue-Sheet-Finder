/**
 * Menu for AutoHirsch
 * Citation: Used parts of Cryptogram menu to write this class
 * 
 * Written By: Kiara Wahnschafft, Kelly Finke, Shannon Wing extends Java Methods
 * Date: 5/31/16
 */

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JFileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;

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
      //opens a word document or text document that the user chooses, 
      //parses its text and creates an essay object from this text
      if (m == openItem)
      {
        JFileChooser fileChooser = new JFileChooser(pathName);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Microsoft Word Document (.docx)", "docx"));
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Text File (.txt)", "txt", "text"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        int result = fileChooser.showOpenDialog(bluesheet);
        if (result == JFileChooser.CANCEL_OPTION)
          return;

        File file = fileChooser.getSelectedFile();
        pathName = file.getAbsolutePath();
        
        if(!(pathName.substring(pathName.length() - 5).equals(".docx")) && !(pathName.substring(pathName.length() - 4).equals(".txt"))) {
        	JOptionPane.showMessageDialog(bluesheet, "Please choose a word document or text document.");
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
        //if the document that the user chooses is a text document
        else if(pathName.substring(pathName.length() - 4).equals(".txt")) {
        	BufferedReader inputFile;
            try
            {
              inputFile = new BufferedReader(new FileReader(pathName), 1024);
            }
            catch (FileNotFoundException ex)
            {
              return;
            }

            StringBuffer buffer = new StringBuffer((int)file.length());

            try
            {
              while (inputFile.ready())
                {
                  buffer.append((char)inputFile.read());
                }
            }
            catch (IOException ex)
            {
              System.err.println("Error reading " + pathName + "\n");
              return;
            }

            try
            {
              inputFile.close();
            }
            catch (IOException ex)
            {
              System.err.println("Error closing " + pathName + "\n");
              return;
            }

            String text = buffer.toString();
            parseAndCreateEssay(text);
        }
      }
      else if (m == saveItem)
      {
        JFileChooser fileChooser = new JFileChooser(pathName);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Microsoft Word Document (.docx)", "docx"));
        fileChooser.setAcceptAllFileFilterUsed(false);
        int result = fileChooser.showSaveDialog(bluesheet);
        if (result == JFileChooser.CANCEL_OPTION)
          return;

        File file = fileChooser.getSelectedFile();
        if (file != null) {
          pathName = file.getAbsolutePath();
        }
        
        if(!(pathName.substring(pathName.length() - 5).equals(".docx")) && !(pathName.substring(pathName.length() - 4).equals(".txt"))) {
        	JOptionPane.showMessageDialog(bluesheet, "Please write either .docx or .txt after your file name.");
        }
        //write to a new word file (.docx)
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
	      result1 = result1.replace( (char)8211, (char)'-' ); // em dash??    
	      result1 = result1.replace( (char)150, (char)'-' );
	  }
      bluesheet.makeEssayAndTree(result1);
      bluesheet.setEssayText(result1);
  }
}