/**
 * BlueSheet
 */

import java.awt.Color;
import java.awt.Font;
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

  private JTextArea textAreaIn, textAreaOut;

  public BlueSheet() {

    setJMenuBar(new CryptogramMenu(this, new DecodeAction()));

    JPanel p1 = new JPanel();
    p1.setPreferredSize(new Dimension(100, 81));
    p1.setLayout(new BoxLayout(p1, BoxLayout.Y_AXIS));
    p1.setBorder(new LineBorder(Color.BLACK));
    ButtonGroup bluesheets = new ButtonGroup();
    JRadioButton one = new JRadioButton("first");
    JRadioButton two = new JRadioButton("second");
    bluesheets.add(one);
    bluesheets.add(two);
    p1.add(one);
    p1.add(two);
   
    JPanel p2 = new JPanel();
    p2.setPreferredSize(new Dimension(100, 81));
    p2.setLayout(new BoxLayout(p2, BoxLayout.Y_AXIS));
    //p2.setLayout(new GridLayout(3, 26, 3, 3));
    p2.setBorder(new LineBorder(Color.BLACK));

    /*Box b1 = Box.createVerticalBox();
    b1.add(p1);
    //b1.add(Box.createHorizontalStrut(10));
    b1.add(p2);*/

    JPanel p123 = new JPanel();
    p123.setPreferredSize(new Dimension(400, 540));
    p123.setLayout(new GridLayout(2, 1));
    p123.setBorder(new LineBorder(Color.BLACK));
    p123.add(p1);
    p123.add(p2);

    Font font = new Font("Monospaced", Font.PLAIN, 12);

    textAreaIn = new JTextArea(20, 50);
    textAreaIn.setFont(font);
    textAreaIn.setLineWrap(true);
    textAreaIn.setWrapStyleWord(true);
    JScrollPane areaScrollPaneIn = new JScrollPane(textAreaIn);
    areaScrollPaneIn.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    /*
    textAreaOut = new JTextArea(20, 30);
    textAreaOut.setFont(font);
    textAreaOut.setLineWrap(true);
    textAreaOut.setWrapStyleWord(true);
    textAreaOut.setEditable(false);
    JScrollPane areaScrollPaneOut = new JScrollPane(textAreaOut);
    areaScrollPaneOut.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); */

    Box b2 = Box.createHorizontalBox();
    b2.add(areaScrollPaneIn);
   // b2.add(areaScrollPaneOut);
    
    JPanel p3 = new JPanel();
    p3.setPreferredSize(new Dimension(100, 81));
    p3.setLayout(new GridLayout(2,1));
    p3.setBorder(new LineBorder(Color.BLACK));
    
    textAreaIn = new JTextArea(10, 100);
    textAreaIn.setFont(font);
    textAreaIn.setLineWrap(true);
    textAreaIn.setWrapStyleWord(true);
    JScrollPane sentenceScrollPane = new JScrollPane(textAreaIn);
    
    Box b3 = Box.createHorizontalBox();
    b3.add(p3);
    b3.add(sentenceScrollPane);

    Container c = getContentPane();
    c.add(p123, BorderLayout.EAST);
    c.add(b2, BorderLayout.CENTER);
    c.add(b3, BorderLayout.SOUTH);

  }

  public String getTextIn()
  {
    return textAreaIn.getText();
  }

  public String getTextOut()
  {
    return textAreaOut.getText();
  }

  public void setTextIn(String text)
  {
    textAreaIn.setText(text);
    textAreaIn.setCaretPosition(0);
  }

  public void setTextOut(String text)
  {
    textAreaOut.setCaretPosition(0);
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
    window.setBounds(30, 30, 800, 600);
    window.setVisible(true);
  }
}

