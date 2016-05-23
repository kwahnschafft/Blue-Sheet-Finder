/*
 * 
<<<<<<< HEAD
 * Written By: Shannon Wing
=======
 * Written By: Kiara
=======
 * Written By: Shannon Wing, Kelly Finke, and Kiara Wahnschafft
>>>>>>> origin/master
>>>>>>> origin/master
 * Date: 5/15/16
 */
public class PastTenseStrategy implements DatabaseSearchStrategy{

<<<<<<< HEAD
=======
	private static String rule = "<html>" +
	"<b>I. Use the present tense in writing about a literary work.</b>" +
	"<ul>" + "<li style='list-style-type: none'><b>(Incorrect)</b> Macbeth <i>hastened</i> home to tell his wife of the king's approach.</li>" 
	 + "<li style='list-style-type:none'></li>"
	 + "<li style='list-style-type: none'><b>(Correct)</b> Macbeth <i>hastens</i> home to tell his wife of the king's approach.</li>" 
	 + "</ul" + "</html>";
	
	@Override
>>>>>>> origin/master
	public void findInDatabase() {
		// TODO Auto-generated method stub
		
	}
	
<<<<<<< HEAD
	@Override
	public String getRule() {
		// TODO Auto-generated method stub
		return null;
	}
=======
	public static String getRule() {
		return rule;
	}

>>>>>>> origin/master
}
