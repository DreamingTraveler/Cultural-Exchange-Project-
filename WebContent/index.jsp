<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <meta name="google-signin-scope" content="profile email">
  <meta name="google-signin-client_id" content="887035268171-dugsr28kko6il2perm94jp3pffgreot2.apps.googleusercontent.com">
  <title>臺美文化線上交流計畫</title>

  <link type="text/css" rel="stylesheet" media="screen" href="http://oss.rainman.me/rainman.me/js/sakura/jquery-sakura.css" />
  <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
  
  <link type="text/css" href="dist/css/style.min.css" rel="stylesheet">
  
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  
  <script src="http://www.vedfolnir.com/resource/javascript/snowstorm.js" type="text/javascript"></script>
  <script src="https://apis.google.com/js/platform.js" async defer></script>
  <script src="https://apis.google.com/js/client:platform.js?onload=start" async defer></script>
  <script>
    function start() {
      console.log("START");
      gapi.load('auth2', function() {
        auth2 = gapi.auth2.init({
          client_id: '887035268171-dugsr28kko6il2perm94jp3pffgreot2.apps.googleusercontent.com',
          cookiepolicy: 'single_host_origin',
          scope: 'profile email'
        });
        
      });
    }
  </script>
  <script src="dist/js/script.min.js"></script>
</head>
<body>
  <div class="container">
    
    <div class="row">
      <p class="welcome"></p>
      <h1 class="title">臺美文化線上交流計畫</h1>
      <span id="quill" class="icon-button"><p class="menu-text">Menu</p></span>
      <span id="book" class="icon-button"><p class="book-text">Lariat</p></span>

    </div>
        
    <div class="sidenav" id="mySidenav">
      <div class="user-info">
        <img id="image">
        <p id="displayed-name"></p>
        <p id="email"></p>
        
        <p id="status"></p>
        <button id="signOut-btn">Sign Out</button>
      </div>
      
      <div class="sign-up">
    	<div class="login-box animated fadeInUp">
    		<div class="box-header">
    			<h2>Sign Up</h2>
    		</div>
    		<form action="signUp.jsp" enctype="multipart/form-data" method="post">
	    		<label style="color:red">* </label><label for="username">Username</label>
	    		<br/>
	    		<input type="text" id="username" name="username">
	    		<br/>
	    		<div class="name-info"></div>
	    		<br/>
	    		
	    		<label style="color:red">* </label><label for="password">Password</label>
	    		<br/>
	    		<input type="password" id="password" name="password">
	    		<br/>
	    		<div class="pwd-info"></div>
	    		<br/>
	    		
	    		<label style="color:red">* </label><label>Name</label>
	    		<br/>
	    		<input type="text" id="name" name="name">
	    		<br/>
	    		
	    		<label for="profile">Profile Image</label>
	    		<br/>
	    		<input id="uploadFile" placeholder="Choose File" disabled="disabled" />
	    		<div class="fileUpload btn btn-primary">
				    <span>Upload</span>
				    <input type="file" id="uploadBtn" class="upload" name="photo"/>
				</div>
	    		<br/>
	    		<button type="submit" id="signUpBtn" disabled>Sign up</button>
	    		<br/>
    		</form>
    	</div>
      </div>
      
      <div class="login">
    	<div class="login-box animated fadeInUp">
    		<div class="box-header">
    			<h2>Log In</h2>
    		</div>
    		
    		  <label for="username">Username</label>
    			<br/>
    			<input type="text" id="dbusername" name="username" required = "required">
    			<br/>
    			<label for="password">Password</label>
    			<br/>
    			<input type="password" id="dbpassword" name="password" required = "required">
    			<br/> 
    			<form action="Login" method="post"><button type="submit" id="signIn-btn">Sign In</button></form>
    			<br/>
    		    <div class="worning-msg"></div>
    		 
            <button id="login-btn"></button>
            <!--<a href="https://accounts.google.com/o/oauth2/auth?response_type=code&state=profile&
client_id=887035268171-dugsr28kko6il2perm94jp3pffgreot2.apps.googleusercontent.com&scope=https://www.googleapis.com/auth/userinfo.email+
https://www.googleapis.com/auth/userinfo.profile+https://www.googleapis.com/auth/plus.login&redirect_uri=http://localhost:8080/TaimeiProject/index.jsp"
id="login-btn">
Connect google account</a>-->
            
            <!-- 
            <div class="g-signin2" data-onsuccess="onSignIn" ></div>
			    <script>
			      function onSignIn(googleUser) {
			        // Useful data for your client-side scripts:
			        var profile = googleUser.getBasicProfile();
			        console.log("ID: " + profile.getId()); // Don't send this directly to your server!
			        console.log('Full Name: ' + profile.getName());
			        console.log('Given Name: ' + profile.getGivenName());
			        console.log('Family Name: ' + profile.getFamilyName());
			        console.log("Image URL: " + profile.getImageUrl());
			        console.log("Email: " + profile.getEmail());
			
			        // The ID token you need to pass to your backend:
			        var id_token = googleUser.getAuthResponse().id_token;
			        console.log("ID Token: " + id_token);
			        $.ajax({
			        	dataType: "json",
			            url: "Oauth2callback",
			            data: {client_id:'887035268171-aj2it5ntjg6mdqaj1qodbff7cgf05r0n.apps.googleusercontent.com', client_secret:'Ub_iwq3xIIRrbFHz29lGBare', redirect_uri:'http://localhost:8080/TaimeiProject/index.jsp'},
			            type:"GET",
			            contentType:"application/x-www-form-urlencoded",
			            success:function(data) {
			                alert(data);
			            },
			            error: function(jqXHR, exception) {
			                console.log(jqXHR);

			            }
			        })
			       
			      };
			      
			      
			    </script>
			-->
    	    <button id="create-account">Create an account</button>
    	</div>
      </div>
      
      
      <div class="menu-group">
        <a href="#" class="group-title title1" data-toggle="collapse">
          <span class="text-normal" id="t1">Topic 1</span>
          <span class="text-hover" id="t1h">Chinese Fēng-shuǐ (風水)</span>
        </a>
        <div class="list-child" id="item-1">
          <a data-href="video1" class="list-group-item" id="video1">Video</a>
          <a data-href="materials1" class="list-group-item" id="materials1">Materials</a>
          <a data-href="documents1" class="list-group-item" id="documents1">Documents</a>
        </div>
      </div>


      <div class="menu-group">
        <a href="#" class="group-title title2" data-toggle="collapse">
          <span class="text-normal" id="t2">Topic 2</span>
          <span class="text-hover" id="t2h">Chinese Gōng-fu (功夫)</span>
        </a>
        <div class="list-child" id="item-1">
          <a data-href="video2" class="list-group-item">Video</a>
          <a data-href="materials2" class="list-group-item">Materials</a>
          <a data-href="documents2" class="list-group-item">Documents</a>
        </div>
      </div>

      <div class="menu-group">
        <a href="#" class="group-title title2" data-toggle="collapse">
          <span class="text-normal" id="t2">Topic 3</span>
          <span class="text-hover" id="t2h">Cultural Taboos (文化禁忌)</span>
        </a>
        <div class="list-child" id="item-1">
          <a data-href="#" class="list-group-item">Video</a>
          <a data-href="#" class="list-group-item">Materials</a>
          <a data-href="#" class="list-group-item">Documents</a>
        </div>
      </div>

      <div class="menu-group">
        <a href="#" class="group-title title2" data-toggle="collapse">
          <span class="text-normal" id="t2">Topic 4</span>
          <span class="text-hover" id="t2h">Movies (電影文化)</span>
        </a>
        <div class="list-child" id="item-1">
          <a data-href="#" class="list-group-item">Video</a>
          <a data-href="#" class="list-group-item">Materials</a>
          <a data-href="#" class="list-group-item">Documents</a>
        </div>
      </div>

      <div class="menu-group">
        <a href="#" class="group-title title2" data-toggle="collapse">
          <span class="text-normal" id="t2">Topic 5</span>
          <span class="text-hover" id="t2h">Medicine (中西醫)</span>
        </a>
        <div class="list-child" id="item-1">
          <a data-href="#" class="list-group-item">Video</a>
          <a data-href="#" class="list-group-item">Materials</a>
          <a data-href="#" class="list-group-item">Documents</a>
        </div>
      </div>

      <div class="menu-group">
        <a href="#" class="group-title title2" data-toggle="collapse">
          <span class="text-normal" id="t2">Topic 6</span>
          <span class="text-hover" id="t2h">Hospitality (請客文化)</span>
        </a>
        <div class="list-child" id="item-1">
          <a data-href="#" class="list-group-item">Video</a>
          <a data-href="#" class="list-group-item">Materials</a>
          <a data-href="#" class="list-group-item">Documents</a>
        </div>
      </div>

      <div class="menu-group">
        <a href="#" class="group-title title2" data-toggle="collapse">
          <span class="text-normal" id="t2">Topic 7</span>
          <span class="text-hover" id="t2h">Transportation (交通工具)</span>
        </a>
        <div class="list-child" id="item-1">
          <a data-href="#" class="list-group-item">Video</a>
          <a data-href="#" class="list-group-item">Materials</a>
          <a data-href="#" class="list-group-item">Documents</a>
        </div>
      </div>

      <div class="menu-group">
        <a href="#" class="group-title title2" data-toggle="collapse">
          <span class="text-normal" id="t2">Topic 8</span>
          <span class="text-hover" id="t2h">Concept of Beauty (瘦身美容)</span>
        </a>
        <div class="list-child" id="item-1">
          <a data-href="#" class="list-group-item">Video</a>
          <a data-href="#" class="list-group-item">Materials</a>
          <a data-href="#" class="list-group-item">Documents</a>
        </div>
      </div>


    </div><!--sidenav-->
    

    

    <div id="content"></div>
    
    <!-- <div class="content" id="content1-video">
      <h1 class="content-title">Video</h1>
      <video src="video/ch1.mp4" autoplay poster="posterimage.jpg">

      </video>
    </div> -->

    <!-- <div class="ppt-content">
       <embed width="640" height="480" src="SS.swf">
    </div> -->


  </div><!--container-->
  <!-- <object width="400" height="50" data="SS.swf"></object> -->
<script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script>
</body>

</html>
