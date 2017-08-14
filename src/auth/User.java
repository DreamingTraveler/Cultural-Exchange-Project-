package auth;

public class User {
	private String username;
	private String password;
	private String name;
	private String imageBytes;
	
    public User(String username, String password, String name, String imageBytes){
    	setUsername(username);
    	setPassword(password);
    	setName(name);
    	setImage(imageBytes);
    }
    
	public void setUsername(String username){
    	this.username = username;
    }
    
    public void setPassword(String password){
    	this.password = password;
    }
    
    public void setName(String name) {
		this.name = name;
	}
    
    public void setImage(String imageBytes){
    	this.imageBytes = imageBytes;
    }

    
    public String getUsername(){
    	return this.username;
    }
    
    public String getPassword(){
    	return this.password;
    }
    
    public String getName(){
    	return this.name;
    }
    
    public String getImage(){
    	return this.imageBytes;
    }
}
