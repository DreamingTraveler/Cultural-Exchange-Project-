package oauth;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonObject;

/**
 * Servlet implementation class Oauth2callback
 */
@WebServlet("/Oauth2callback")
public class Oauth2callback extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Oauth2callback() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("entering doGet");
//		String code = request.getParameter("code");
//        System.out.println("code:" + code);
//
//        try {
//            //post parameters
//            URL url = new URL("https://accounts.google.com/o/oauth2/token");
//            URLConnection urlConn =url.openConnection();
//            urlConn.setDoOutput(true);
//            
//            String urlParameters = "code="
//                    + code
//                    + "&client_id=887035268171-dugsr28kko6il2perm94jp3pffgreot2.apps.googleusercontent.com"
//                    + "&client_secret=7F6PmtwvrVOPRgtowyaeixJI"
//                    + "&redirect_uri=http://localhost:8080/TaimeiProject/index.jsp"
//                    + "&grant_type=authorization_code";
//            OutputStreamWriter writer = new OutputStreamWriter(
//                    urlConn.getOutputStream());
//            writer.write(urlParameters);
//            writer.flush();
//            
//            //get output in outputString 
//            String line, outputString = "";
//            BufferedReader reader = new BufferedReader(new InputStreamReader(
//                    urlConn.getInputStream()));
//            while ((line = reader.readLine()) != null) {
//                outputString += line;
//            }
//            System.out.println(outputString);
//            
//            //get Access Token 
//            JsonObject json = (JsonObject)new JsonParser().parse(outputString);
//            String access_token = json.get("access_token").getAsString();
//            System.out.println(access_token);

            //get User Info 
//            url = new URL(
//                    "https://www.googleapis.com/oauth2/v1/userinfo?access_token="
//                            + access_token);
//            urlConn = url.openConnection();
//            outputString = "";
//            reader = new BufferedReader(new InputStreamReader(
//                    urlConn.getInputStream()));
//            while ((line = reader.readLine()) != null) {
//                outputString += line;
//            }
//            System.out.println(outputString);
            
            // Convert JSON response into Pojo class
//            GooglePojo data = new Gson().fromJson(outputString, GooglePojo.class);
//            System.out.println(data);
//            writer.close();
//            reader.close();
//            
//        } catch (MalformedURLException e) {
//            System.out.println( e);
//        } catch (ProtocolException e) {
//            System.out.println( e);
//        } catch (IOException e) {
//            System.out.println( e);
//        }
//        System.out.println("leaving doGet");
		System.out.println("entering doGet");
		String authCode = request.getParameter("code");
		String redirect_uri = "http://localhost:8080/TaimeiProject/index.jsp";
		String CLIENT_SECRET_FILE = "C:/Web Test/Taimei/TaimeiProject/client_secrets.json";

		// Exchange auth code for access token
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(), 
				new FileReader(CLIENT_SECRET_FILE));
		GoogleTokenResponse tokenResponse = new GoogleAuthorizationCodeTokenRequest(
				new NetHttpTransport(),
				JacksonFactory.getDefaultInstance(),
				"https://www.googleapis.com/oauth2/v4/token",
				clientSecrets.getDetails().getClientId(),
				clientSecrets.getDetails().getClientSecret(),
				authCode,
				redirect_uri).execute();
		String accessToken = tokenResponse.getAccessToken();
		System.out.println("ACC:" + accessToken);
//		File file = new File("client_secrets.json");
//		 System.out.println("µ´¹ï¸ô®| : " + file.getAbsolutePath());
//	     System.out.println("¸ô®| : " + file.toPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	}

}
