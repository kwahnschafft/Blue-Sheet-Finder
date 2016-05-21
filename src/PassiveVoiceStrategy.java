/*
 * Written By: Shannon Wing
 * Date: 5/15/16
 */
public class PassiveVoiceStrategy implements DatabaseSearchStrategy{

	private static String rule = "<html>" +
		    "<b>IX. Write in the active voice; avoid the passive voice.</b>" +
		  	"<ul>" + "<li style='list-style-type: none'><b>(Incorrect)</b> Gulliver is <i>taught</i> many lessons in rational behavior.</li>" 
		  	 + "<li style='list-style-type:none'></li>"
		  	 + "<li style='list-style-type: none'><b>(Correct)</b> The Houyhnhnms <i>teach</i> Gulliver many lessons in rational behavior.</li>" + "</ul" + "</html>";
			
	@Override
	public void findInDatabase() {
		// TODO Auto-generated method stub
		
	}

	public static String getRule() {
		return rule;
	}

}
