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
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
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
		String redirect_uri = "postmessage";
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
		System.out.println("ACCESS TOKEN:" + accessToken);
		
		GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);

		// Get profile info from ID token
		GoogleIdToken idToken = tokenResponse.parseIdToken();
		GoogleIdToken.Payload payload = idToken.getPayload();
		String userId = payload.getSubject();  // Use this value as a key to identify a user.
		String email = payload.getEmail();
		boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
		String name = (String) payload.get("name");
		String pictureUrl = (String) payload.get("picture");
		String locale = (String) payload.get("locale");
		String familyName = (String) payload.get("family_name");
		String givenName = (String) payload.get("given_name");
		
		System.out.println(idToken);
		System.out.println("USER ID:" + userId);
		System.out.println("EMAIL: " + email);
		System.out.println("EMAIL VERIFIED: " + emailVerified);
		System.out.println("NAME: " + name);
		System.out.println("PICTURE URL: " + pictureUrl);
		System.out.println("LOCALE: " + locale);
		System.out.println("FAMILY NAME: " + familyName);
		System.out.println("GIVEN NAME: " + givenName);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	}

}
