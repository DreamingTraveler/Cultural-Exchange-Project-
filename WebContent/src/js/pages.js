$(document).ready(function(){
  var hashtag = location.hash.substring(1, location.hash.length);
  if(hashtag != ""){
	  console.log(hashtag);
	  $('#content').load('pages/' + hashtag + '.html'); 
  }
  else{
	  $('#content').load('pages/carousel.html');
  }
  
  $('.list-child a').click(function(){
    var page = $(this).data('href');
    location.hash = page;
    $('#content').load('pages/' + page + '.html');
    return false;
  });
});
