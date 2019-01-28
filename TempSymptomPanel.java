package views;

import org.h2.mvstore.MVMap;

import model.Condition;
import model.PatientSymptoms;
import storage.DatabaseInterface;
import storage.FileStoreInterface;
import web.WebRequest;
import web.WebResponse;

public class TempSymptomPanel extends DynamicWebPage
{

	public TempSymptomPanel(DatabaseInterface db,FileStoreInterface fs)
	{
		super(db,fs);
	}

	public boolean process(WebRequest toProcess)
	{
		if(toProcess.path.equalsIgnoreCase("TempSymptomPanel.html"))
		{
			
			MVMap<String, PatientSymptoms> symptoms = db.s.openMap("symptoms");
			
			PatientSymptoms symptom = new PatientSymptoms();
			symptom.uniqueid = ""+System.currentTimeMillis();
			symptom.ageRange = toProcess.params.get("age");
			symptom.gender = toProcess.params.get("gender");

			if(toProcess.params.containsKey("Symptoms")) {
				String[] splitSymptoms = toProcess.params.get("Symptoms").split(",");
				for(String symptomString : splitSymptoms) {
					symptom.symptoms.add(symptomString);
				}
			}
			
			symptoms.put(symptom.uniqueid, symptom);
			
			
			if(symptoms.size()==0) {
				
				PatientSymptoms symptoms1 = new PatientSymptoms();
				symptoms1.uniqueid = ""+System.currentTimeMillis()+1;
				symptoms1.ageRange="6-12";
				symptoms1.gender="Male";
				symptoms1.symptoms.add("Head hurts");
				symptoms1.symptoms.add("Sore stomach");
				symptoms.put(symptoms1.uniqueid, symptoms1);

				db.commit();
			}
			
			PatientSymptoms storedSymptoms = symptoms.get(symptom.uniqueid);
			
			String stringToSendToWebBrowser = "";
			stringToSendToWebBrowser += "<html>\n";
			stringToSendToWebBrowser += "  \n";
			stringToSendToWebBrowser += "  <head>\n";
			stringToSendToWebBrowser += "    <title>Medicheck - Search results</title>\n";
			stringToSendToWebBrowser += "    <meta charset=\"utf-8\">\n";
			stringToSendToWebBrowser += "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n";
			stringToSendToWebBrowser += "    <script type=\"text/javascript\" src=\"http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js\"></script>\n";
			stringToSendToWebBrowser += "    <script type=\"text/javascript\" src=\"http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js\"></script>\n";
			stringToSendToWebBrowser += "    <link href=\"http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css\"\n";
			stringToSendToWebBrowser += "    rel=\"stylesheet\" type=\"text/css\">\n";
			stringToSendToWebBrowser += "    <link href=\"http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css\"\n";
			stringToSendToWebBrowser += "    rel=\"stylesheet\" type=\"text/css\">\n";
			stringToSendToWebBrowser += "  </head>\n";
			stringToSendToWebBrowser += "  \n";
			stringToSendToWebBrowser += "  <body>\n";
			stringToSendToWebBrowser += "    <div class=\"section\">\n";
			stringToSendToWebBrowser += "      <div class=\"container\">\n";
			stringToSendToWebBrowser += "        <div class=\"row\">\n";
			stringToSendToWebBrowser += "          <div class=\"col-md-12\">\n";
			stringToSendToWebBrowser += HomePage.displayNavbar(toProcess);
			stringToSendToWebBrowser += "			<br>\n";
			stringToSendToWebBrowser += "            <div class=\"panel panel-primary\">\n";
			stringToSendToWebBrowser += "              <div class=\"panel-heading\">\n";
			stringToSendToWebBrowser += "                <h3 class=\"panel-title text-center\">Entered Symptoms</h3>\n";
			stringToSendToWebBrowser += "              </div>\n";
			stringToSendToWebBrowser += "              <div class=\"panel-body\">\n";
			stringToSendToWebBrowser += "                <p>Age: " + symptom.ageRange + "\n";
			stringToSendToWebBrowser += "                  <br>\n";
			stringToSendToWebBrowser += "                  <br>Gender: " + symptom.gender + "\n";
			stringToSendToWebBrowser += "                  <br>\n";
			stringToSendToWebBrowser += "                  <br>Symptoms:</p>\n";
						for(int symNo = 0; symNo < symptom.symptoms.size(); symNo++) {
				stringToSendToWebBrowser += symptom.symptoms.get(symNo);
				stringToSendToWebBrowser += "<br>";
			}
			stringToSendToWebBrowser += "              </div>\n";
			stringToSendToWebBrowser += "            </div>\n";
			stringToSendToWebBrowser += "			<br>\n";
			stringToSendToWebBrowser += "            <div class=\"panel panel-primary\">\n";
			stringToSendToWebBrowser += "              <div class=\"panel-heading\">\n";
			stringToSendToWebBrowser += "                <h3 class=\"panel-title text-center\">Possible Conditions</h3>\n";
			stringToSendToWebBrowser += "              </div>\n";
			stringToSendToWebBrowser += "              <div class=\"panel-body\">\n";
			stringToSendToWebBrowser += "            <ul>\n";
			/*MVMap<String, Condition> databaseConditions = db.s.openMap("conditions");
			for(String key : databaseConditions.keyList()) {
				Condition condition = databaseConditions.get(key);
				stringToSendToWebBrowser += " <li>\n";
				stringToSendToWebBrowser += "                <br><a href=\"ForumIndex" + condition.conditionName.replaceAll(" ", "") + ".html\">" + condition.conditionName + "</a>\n";
				}
			stringToSendToWebBrowser += " </li>\n";*/
			stringToSendToWebBrowser += "            </ul>\n";
			stringToSendToWebBrowser += "              </div>\n";
			stringToSendToWebBrowser += "          </div>\n";
			stringToSendToWebBrowser += "        </div>\n";
			stringToSendToWebBrowser += "      </div>\n";
			stringToSendToWebBrowser += "    </div>\n";
			stringToSendToWebBrowser += "  </body>\n";
			stringToSendToWebBrowser += "\n";
			stringToSendToWebBrowser += "</html>\n";
			

			toProcess.r = new WebResponse( WebResponse.HTTP_OK, WebResponse.MIME_HTML, stringToSendToWebBrowser );
			return true;
		}
		return false;
	}

}
