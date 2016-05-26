/**
 * Menu for Cryptogram Solver
 */

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;

public class MenuBar extends JMenuBar
{
  private BlueSheetChecker bluesheet;
  private JMenuItem openItem, saveItem, exitItem;

  public MenuBar(BlueSheetChecker checker, ActionListener essayAction)
  {
    bluesheet = checker;

    JMenu fileMenu = new JMenu("File");
    fileMenu.setMnemonic('F');

    FileAction fileAction = new FileAction();
    openItem = new JMenuItem("Open...");
    openItem.setMnemonic('O');
    openItem.addActionListener(fileAction);
    JMenuItem decodeItem = new JMenuItem("Insert...");
    decodeItem.setMnemonic('D');
    decodeItem.addActionListener(essayAction);
    saveItem = new JMenuItem("Save...");
    saveItem.setMnemonic('S');
    saveItem.addActionListener(fileAction);
    exitItem = new JMenuItem("Exit");
    exitItem.setMnemonic('x');
    exitItem.addActionListener(fileAction);
    fileMenu.add(openItem);
    fileMenu.add(decodeItem);
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
        int result = fileChooser.showOpenDialog(bluesheet);
        if (result == JFileChooser.CANCEL_OPTION)
          return;

        File file = fileChooser.getSelectedFile();
        if (file != null)
          pathName = file.getAbsolutePath();

        
        FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(pathName);
		} catch (FileNotFoundException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
        InputStreamReader inputStreamReader = null;
		try {
			inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
		} catch (UnsupportedEncodingException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}      
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String result1 = "";
        String line = null;
        try {
			while ((line = bufferedReader.readLine()) != null) {
			   result1 += line;
			}
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
        try {
			bufferedReader.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        parseAndCreateEssay(result1);
      }
      else if (m == saveItem)
      {
        JFileChooser fileChooser = new JFileChooser(pathName);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showSaveDialog(bluesheet);
        if (result == JFileChooser.CANCEL_OPTION)
          return;

        File file = fileChooser.getSelectedFile();
        if (file != null)
          pathName = file.getAbsolutePath();

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
        }

        String text = bluesheet.getEssayText();
        outputFile.print(text);
        outputFile.close();
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
      
      System.out.println(result1);
      
      bluesheet.makeEssayAndTree(result1);
      bluesheet.setEssayText(result1);
  }
}