/*
 * Written By: Shannon Wing
 * Date: 5/15/16
 */
public class ProgressiveTenseStrategy implements DatabaseSearchStrategy{

	private static String rule = "<html>" +
	"<b>XII. Avoid progressive tenses.</b>" +
	"<ul>" + "<li style='list-style-type: none'><b>(Incorrect)</b> Sensing God's desire to destroy Sodom, Abraham <i>is negotiating</i> for a less apocalyptic punishment.</li>" 
	 + "<li style='list-style-type:none'></li>"
	 + "<li style='list-style-type: none'><b>(Correct)</b> Sensing God's desire to destroy Sodom, Abraham <i>negotiates</i> for a less apocalyptic punishment.</li>" + "</ul" + "</html>";
	@Override
	public void findInDatabase() {
		// TODO Auto-generated method stub
		
	}
	
	public String getRule() {
		return rule;
	}

}
