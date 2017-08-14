package oauth;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
 




import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 



import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.StoredCredential;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeServlet;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.BasicAuthentication;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStore;
import com.google.api.client.util.store.FileDataStoreFactory;




import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Servlet implementation class Oauth2
 */
@WebServlet("/Oauth2")
public class Oauth2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String GOOGLE_CLIENT_ID = "client_id=887035268171-aj2it5ntjg6mdqaj1qodbff7cgf05r0n.apps.googleusercontent.com";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Oauth2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		  URL urlObtainToken =  new URL("https://accounts.google.com/o/oauth2/token");
		  HttpURLConnection connectionObtainToken =  (HttpURLConnection) urlObtainToken.openConnection();
		    
	
		  connectionObtainToken.setRequestMethod("POST");
		  connectionObtainToken.setDoOutput(true);
		   
          String code = request.getParameter("code");
          System.out.println("CODE:::" + code);
		  OutputStreamWriter writer  = new OutputStreamWriter(connectionObtainToken.getOutputStream());
		  writer.write("code="+request.getParameter("code")+"&");  
		  writer.write("client_id=887035268171-dugsr28kko6il2perm94jp3pffgreot2.apps.googleusercontent.com"); 
		  writer.write("client_secret=7F6PmtwvrVOPRgtowyaeixJI");  
		  writer.write("redirect_uri=http://localhost:8080/TaimeiProject/index.jsp");   
		  writer.write("grant_type=authorization_code");  
		  writer.close();
		  System.out.println(connectionObtainToken.getResponseCode());
		  if (connectionObtainToken.getResponseCode() == HttpURLConnection.HTTP_OK){
			  System.out.println("afa");
		   StringBuilder sbLines   = new StringBuilder("");
		   
		   BufferedReader reader = 
		       new BufferedReader(new InputStreamReader(connectionObtainToken.getInputStream(),"utf-8"));
		   String strLine = "";
		   while((strLine=reader.readLine())!=null){
		    sbLines.append(strLine);
		   }
		  
		   try {
	
		    JSONObject jo = new JSONObject(sbLines.toString());
		    
		    System.out.println(jo.getString("access_token"));
		    response.getWriter().println(jo.getString("access_token")); 
		   } catch (JSONException e) {
		    e.printStackTrace();
		   }catch (IOException e) {
	            System.out.println( e);
	       }
		  }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        
	}

}




