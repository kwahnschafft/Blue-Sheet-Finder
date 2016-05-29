/**
 * Menu for Cryptogram Solver
 * 
 * Written By: Kiara Wahnschafft, Kelly Finke, Shannon Wing
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
import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class MenuBar extends JMenuBar
{
  private AutoHirsch bluesheet;
  private JMenuItem openItem, saveItem, exitItem;
  public static final Charset UTF_8 = StandardCharsets.UTF_8;

  public MenuBar(AutoHirsch checker, ActionListener essayAction)
  {
    bluesheet = checker;

    JMenu fileMenu = new JMenu("File");
    fileMenu.setMnemonic('F');

    FileAction fileAction = new FileAction();
    openItem = new JMenuItem("Open...");
    openItem.setMnemonic('O');
    openItem.addActionListener(fileAction);
    JMenuItem insertItem = new JMenuItem("Insert...");
    insertItem.setMnemonic('D');
    insertItem.addActionListener(essayAction);
    saveItem = new JMenuItem("Save...");
    saveItem.setMnemonic('S');
    saveItem.addActionListener(fileAction);
    exitItem = new JMenuItem("Exit");
    exitItem.setMnemonic('x');
    exitItem.addActionListener(fileAction);
    fileMenu.add(openItem);
    fileMenu.add(insertItem);
    fileMenu.add(saveItem);
    fileMenu.addSeparator();
    fileMenu.add(exitItem);

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
        String stuff = "";
     
        
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
        //String text = bluesheet.getEssayText();
        parseAndCreateEssay(displayText);
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
        if (file != null)
          pathName = file.getAbsolutePath() + ".docx";

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
        
        /*
        PrintWriter outputFile;
        try
        {
          outputFile = new PrintWriter(new FileWriter(pathName, false));
        }
        catch (IOException ex)
        {
          JOptionPane.showMessageDialog(bluesheet, "Invalid File Name",
                      "Cannot create " + pathName, JOptionPane.ERROR_MESSAGE);
          return;
        }*/
      }
      else if (m == exitItem)
      {
        System.exit(0);
      }
    }
  }
  public void parseAndCreateEssay(String str) {
	  String osName = System.getProperty("os.name").toLowerCase();
	  boolean isMacOs = osName.startsWith("mac os x");
	  String result1 = str;
	  if (isMacOs) { //mac-specific character encoding
		  System.out.println("hey");
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
      
      System.out.println("result 1 " + result1);
      
      bluesheet.makeEssayAndTree(result1);
      bluesheet.setEssayText(result1);
  }
}