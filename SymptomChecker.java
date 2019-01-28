package views;

import java.util.Map;

import org.h2.mvstore.MVMap;

import model.PatientSymptoms;
import storage.DatabaseInterface;
import storage.FileStoreInterface;
import web.WebRequest;
import web.WebResponse;

public class SymptomChecker extends DynamicWebPage
{

	public SymptomChecker(DatabaseInterface db,FileStoreInterface fs)
	{
		super(db,fs);
	}

	public boolean process(WebRequest toProcess)
	{
		if(toProcess.path.equalsIgnoreCase("SymptomChecker.html"))
		{	
			
			/*
			 * required
			 * if(document.getElementById('id).value==''
			 * alert('alert')
			 * style=\"float: right;\"
			*/
			String stringToSendToWebBrowser = "";
			stringToSendToWebBrowser += "<html>\n";
			stringToSendToWebBrowser += "  \n";
			stringToSendToWebBrowser += "  <head>\n";
			stringToSendToWebBrowser += "    <title>MediCheck - Symptom Checker</title>\n";
			stringToSendToWebBrowser += "    <meta charset=\"utf-8\">\n";
			stringToSendToWebBrowser += "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n";
			stringToSendToWebBrowser += "    <script type=\"text/javascript\" src=\"http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js\"></script>\n";
			stringToSendToWebBrowser += "    <script type=\"text/javascript\" src=\"http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js\"></script>\n";
			stringToSendToWebBrowser += "    <link href=\"http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.3.0/css/font-awesome.min.css\"\n";
			stringToSendToWebBrowser += "    rel=\"stylesheet\" type=\"text/css\">\n";
			stringToSendToWebBrowser += "    <link href=\"http://pingendo.github.io/pingendo-bootstrap/themes/default/bootstrap.css\"\n";
			stringToSendToWebBrowser += "    rel=\"stylesheet\" type=\"text/css\">\n";
			stringToSendToWebBrowser += "       <script src=\"jquery-3.3.1.min.js\"></script>\n";
			stringToSendToWebBrowser += "  </head>\n";
			stringToSendToWebBrowser += "  \n";
			stringToSendToWebBrowser += "  <body>\n";
			stringToSendToWebBrowser += "    <div class=\"section\">\n";
			stringToSendToWebBrowser += "      <div class=\"container\">\n";
			stringToSendToWebBrowser += "        <div class=\"row\">\n";
			stringToSendToWebBrowser += HomePage.displayNavbar(toProcess);
			stringToSendToWebBrowser += "        </div>\n";
			stringToSendToWebBrowser += "      </div>\n";
			stringToSendToWebBrowser += "    </div>\n";
			stringToSendToWebBrowser += "    <div class=\"section\">\n";
			stringToSendToWebBrowser += "      <div class=\"container\">\n";
			stringToSendToWebBrowser += "        <div class=\"row\">\n";
			stringToSendToWebBrowser += "          <div class=\"col-md-12\">\n";
			stringToSendToWebBrowser += "            <h1 class=\"text-center\">Symptom Checker</h1>\n";
			stringToSendToWebBrowser += "          </div>\n";
			stringToSendToWebBrowser += "        </div>\n";
			stringToSendToWebBrowser += "      </div>\n";
			stringToSendToWebBrowser += "    </div>\n";
			stringToSendToWebBrowser += "    <div class=\"section\">\n";
			stringToSendToWebBrowser += "      <div class=\"container\">\n";
			stringToSendToWebBrowser += "        <div class=\"row\">\n";
			stringToSendToWebBrowser += "          <div class=\"col-md-12\">\n";
			stringToSendToWebBrowser += "            <form role=\"form\" method=\"POST\" action=\"TempSymptomPanel.html\">\n";
			stringToSendToWebBrowser += "              <div>\n";
			stringToSendToWebBrowser += "                <label class=\"control-label\" for=\"age\" data-toggle=\"tooltip\" title=\"Please select the most appropriate age range\">Age</label>\n";
			stringToSendToWebBrowser += "                <br>\n";
			stringToSendToWebBrowser += "                <input id =\"age\" type=\"radio\" name=\"age\" value=\"1-5\" required>1-5\n";
			stringToSendToWebBrowser += "                <br>\n";
			stringToSendToWebBrowser += "                <input type=\"radio\" name=\"age\" value=\"7-12\">6-12\n";
			stringToSendToWebBrowser += "                <br>\n";
			stringToSendToWebBrowser += "                <input type=\"radio\" name=\"age\" value=\"13-16\">13-16\n";
			stringToSendToWebBrowser += "                <br>\n";
			stringToSendToWebBrowser += "                <input type=\"radio\" name=\"age\" value=\"17-29\">17-29\n";
			stringToSendToWebBrowser += "                <br>\n";
			stringToSendToWebBrowser += "                <input type=\"radio\" name=\"age\" value=\"30-39\">30-39\n";
			stringToSendToWebBrowser += "                <br>\n";
			stringToSendToWebBrowser += "                <input type=\"radio\" name=\"age\" value=\"40-49\">40-49\n";
			stringToSendToWebBrowser += "                <br>\n";
			stringToSendToWebBrowser += "                <input type=\"radio\" name=\"age\" value=\"50-64\">50-64\n";
			stringToSendToWebBrowser += "                <br>\n";
			stringToSendToWebBrowser += "                <input type=\"radio\" name=\"age\" value=\"65+\">65+\n";
			stringToSendToWebBrowser += "                <br>\n";
			stringToSendToWebBrowser += "              </div>\n";
			stringToSendToWebBrowser += "              <div>\n";
			stringToSendToWebBrowser += "                <br>\n";
			stringToSendToWebBrowser += "                <label class=\"control-label\" for=\"gender\" data-toggle=\"tooltip\" title=\"Please select the most appropriate gender\">Gender</label>\n";
			stringToSendToWebBrowser += "                <br>\n";
			stringToSendToWebBrowser += "                <input id=\"gender\" type=\"radio\" name=\"gender\" value=\"Male\" required>Male\n";
			stringToSendToWebBrowser += "                <br>\n";
			stringToSendToWebBrowser += "                <input type=\"radio\" name=\"gender\" value=\"Female\">Female\n";
			stringToSendToWebBrowser += "                <br>\n";
			stringToSendToWebBrowser += "                <input type=\"radio\" name=\"gender\" value=\"Other\">Other\n";
			stringToSendToWebBrowser += "                <br>\n";
			stringToSendToWebBrowser += "              </div>\n";
			stringToSendToWebBrowser += "              <div class=\"form-group\">\n";
			stringToSendToWebBrowser += "                <br>\n";
			stringToSendToWebBrowser += "                <label class=\"control-label\" for=\"Symptoms\" data-toggle=\"tooltip\" title=\"Please enter you symptoms, seperated by a comma\">Symptoms</label>\n";
			stringToSendToWebBrowser += "                <input class=\"form-control\" id=\"symptoms\" name=\"Symptoms\" pattern=\"[A-Za-z]+(,\\s*[A-Za-z]+)*\" title=\"Each entry must be seperated by a comma eg. Sore head, inflamed throat.\"\n";
			stringToSendToWebBrowser += "                placeholder=\"Enter your symptoms here\" type=\"text\" required>\n";
			stringToSendToWebBrowser += "              </div>\n";
			stringToSendToWebBrowser += "              <button type=\"submit\" class=\"btn btn-default\"onclick=\"validation()\"  onclick=\"textVal()\" >Search</button>\n";
			stringToSendToWebBrowser += "            </form>\n";
			stringToSendToWebBrowser += "          </div>\n";
			stringToSendToWebBrowser += "        </div>\n";
			stringToSendToWebBrowser += "      </div>\n";
			stringToSendToWebBrowser += "    </div>\n";
			
			
			
			
			stringToSendToWebBrowser += "             <script>\n";
			stringToSendToWebBrowser += "                function validation(){\n";
			stringToSendToWebBrowser += "                var ages = document.getElementsByName(\"age\");\n";
			stringToSendToWebBrowser += "                if (ages[0].checked ==false && ages[1].checked ==false && ages[2].checked ==false && ages[3].checked ==false && ages[4].checked ==false && ages[5].checked ==false && ages[6].checked ==false) {\n";
			stringToSendToWebBrowser += "                   alert(\"Please select an age range\") }\n";
			stringToSendToWebBrowser += "                  genVal();\n";
			stringToSendToWebBrowser += "                  textVal(); }\n";
			stringToSendToWebBrowser += "             </script>\n";
			stringToSendToWebBrowser += "               <script>\n";
			stringToSendToWebBrowser += "                       function textVal(){\n";
			stringToSendToWebBrowser += "                 if(document.getElementById(\"symptoms\").value ==''){\n";
			stringToSendToWebBrowser += "                           alert(\"Please enter a symptom(s)\")}\n";
			stringToSendToWebBrowser += "                        }\n";
			stringToSendToWebBrowser += "               </script>\n";
			stringToSendToWebBrowser += "               <script>\n";
			stringToSendToWebBrowser += "                   function genVal() {\n"; 
			stringToSendToWebBrowser += "                var genders = document.getElementsByName(\"gender\");\n"; 
			stringToSendToWebBrowser += "                if (genders[0].checked == false && genders[1].checked == false && genders[2].checked == false) {\n";
			stringToSendToWebBrowser += "                   alert(\"Please select a gender\")\n";
			stringToSendToWebBrowser += "                }\n"; 
			stringToSendToWebBrowser += "                return true;\n";
			stringToSendToWebBrowser += "            }\n";
			stringToSendToWebBrowser += "             </script>\n";
			stringToSendToWebBrowser += " </body>\n";

			toProcess.r = new WebResponse( WebResponse.HTTP_OK, WebResponse.MIME_HTML, stringToSendToWebBrowser );
			return true;
		}
		return false;
	}

}