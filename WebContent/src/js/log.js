$(document).ready(function(){
    $.ajax({
	    url: "Login",
		type: "get",
		success: function(data){
			console.log(data);
			if(data != ""){
				$('.login').css("display", "none");
				$('#signOut-btn').css("display", "block");
				
				$('#displayed-name').html(data.name);
				$('#image').attr("src", "data:image/jpg;base64,"+data.imageBytes);
				$('.user-info').css("display", "block");
				$('.welcome').html("Welcome~ " + data.name);
			}
			else{
				$('.login').css("display", "block");
				$('.user-info').css("display", "none");
			}
		} 
	});
    
//    function onSignIn(googleUser){
//	  	  var profile = googleUser.getBasicProfile();
//		  var id = profile.getId();
//		  var name = profile.getName();
//		  var imageUrl = profile.getImageUrl();
//		  var email = profile.getEmail(); 
//		  var id_token = googleUser.getAuthResponse().id_token;
//		  
//		  console.log(id_token);
//		  
//		  $('#image').attr("src", imageUrl);
//		  $('#displayed-name').html(name);
//		  $('#email').html(email);
//		  $('#login-btn').css("display", "none");
//		  $('.login').css("display", "none");
//		  $('#signOut-btn').css("display", "block");
//		  $('#status').html('Welcome '+name+'!<a href=success.jsp?email='+email+'&name='+name+'><br>Continue with Google login</a>');
//
//	}
    function signInCallback(authResult) {
    	console.log(authResult['code']);
    	  if (authResult['code']) {
    	    // Send the code to the server
    		$('.login').attr('style', 'display: none');
    	    $.ajax({
    	      type: 'get',
    	      url: 'Oauth2callback',
    	      // Always include an `X-Requested-With` header in every AJAX request,
    	      // to protect against CSRF attacks.
    	      headers: {
    	        'X-Requested-With': 'XMLHttpRequest'
    	      },
    	      contentType: 'application/x-www-form-urlencoded; charset=utf-8',
    	      dataType: "text",
    	      data: {code : authResult['code']},
    	      success: function(result) {
    	    	  alert('TTT');
    	    	  console.log("DATA:"+result);
    	        // Handle or verify the server response.
    	      },
    	      
    	    });
    	  } else {  
    	    // There was an error.
    	  }
    	}
    
    
    $('#login-btn').click(function(){
    	auth2.grantOfflineAccess().then(signInCallback);
//    	window.location = "https://accounts.google.com/o/oauth2/auth?" +
//    			"response_type=code&state=profile&client_id=887035268171-dugsr28kko6il2perm94jp3pffgreot2.apps.googleusercontent.com&" +
//    			"scope=https://www.googleapis.com/auth/userinfo.email+https://www.googleapis.com/auth/userinfo.profile+https://www.googleapis.com/auth/plus.login&" +
//    			"redirect_uri=http://localhost:8080/TaimeiProject/index.jsp"
    	
    	
//    	$.ajax({
//    		url: "Oauth2callback",
//    		type: "get",
//    		success: function(data){
//    			console.log("data" + data);
//    			
//    		}
//    	});
    });
  
    $('#signOut-btn').click(function(){
    	$.ajax({
    		url: "LogOut",
    		type: "post",
    		success: function(data){
    			alert("Sign Out Success!");
    		}
    	});
	    var auth2 = gapi.auth2.getAuthInstance();
		auth2.signOut().then(function () {
		  console.log('User signed out.');
		  $('#login-btn').css("display", "block");
	  	  $('.login').css("display", "block");
	  	  location.reload();
		});
	});
	  
	$('#create-account').click(function(){
		$('.login').animate({opacity: 0}, 300, function(){
	      $(this).css('display', 'none');
	      $('.sign-up').animate({opacity: 1}, 200, function(){
		     $(this).css('display', 'block');
		  });
		});
	});	
	
	$('#uploadBtn').change(function(){
		 var $this = $(this);
		 $('#uploadFile').val($this.val().split('\\').pop());
	});
	
	function isVaild(input, limit){
		if(input < limit){
			return false;
		}
		return true;
	}
	
	function isSignBtnEnabled(){
		var usernameLength = $('#username').val().length;
		var passwordLength = $('#password').val().length;
		var nameLength = $('#name').val().length;
		
		console.log(usernameLength ,passwordLength, nameLength);
		
		if(isVaild(usernameLength, 6) && isVaild(passwordLength, 8) && isVaild(nameLength, 1)){
			$('#signUpBtn').prop('disabled', false);
		}
	}
	
	$('#username').keyup(function(){
		var value = $(this).val();
		
		isSignBtnEnabled();
		if(!isVaild(value.length, 6)){
		  $('.name-info').html('Username must be at least 6 characters long!');
		  $('.name-info').css('color', 'red');
		  $('#signUpBtn').prop('disabled', true);
		  console.log('Error: Username must be at least 6 characters long!');
		}
		else{
		  $('.name-info').html('');
		}
	});
	
	$('#password').keyup(function(){
		var value = $(this).val();
		
		isSignBtnEnabled();
		if(!isVaild(value.length, 8)){
		  $('.pwd-info').html('Password must be at least 8 characters long!');
		  $('.pwd-info').css('color', 'red');
		  $('#signUpBtn').prop('disabled', true);
		}
		else{
		  $('.pwd-info').html('');
		}
	});
	
	$('#name').keyup(function(){
		isSignBtnEnabled();
	});
	
	$('#signIn-btn').click(function(e){
		e.preventDefault();
		var username = $('#dbusername').val();
		var password = $('#dbpassword').val();
		$.ajax({
			url: "Login",
			type: "post",
			data: {"username" : username, "password" : password},
			success: function(data){
				console.log(data);
				if(data != ""){
					userData = data;
					console.log(data.username);
					console.log(data.password);
					console.log(data.name);
					alert('success');
					window.location.reload();
//					$('.login').css("display", "none");
//					$('#signOut-btn').css("display", "block");
//					
//					$('#displayed-name').html(data.name);
//					$('#image').attr("src", "data:image/jpg;base64,"+data.imageBytes);
//					$('#image').css("display", "block");
				}
				else{
					$('.worning-msg').html('Something is wrong!');
					$('.worning-msg').css("color", "red");
				}	
			},
			error: function(){
				alert('error');
			}
		})
	});
});
