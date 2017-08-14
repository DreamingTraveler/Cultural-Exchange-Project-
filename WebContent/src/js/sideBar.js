$(document).ready(function(){
  $('.menu-group').mouseover(function(){
    var target = $(this);
    // var target2 = $('.menu-group');
    target.find('.text-hover').css('display', 'block');
    target.find('.text-normal').css('display', 'none');
    target.find('.group-title').css('margin-bottom', '0');
    target.find('.list-child').css('display', 'block');
  }).mouseleave(function(){
    var target = $(this);
    target.find('.text-hover').css('display', 'none');
    target.find('.text-normal').css('display', 'block');
    target.find('.group-title').css('margin-bottom', '30px');
    target.find('.list-child').css('display', 'none');
  });

  function closeNav(){
    $("#mySidenav").css("width", "0");
    $(".container").css("background-color", "rgba(255, 255, 255, 0.3)");
    $("#quill").css('background-image', 'url(image/quill-icon2.png)');
    $("#quill").val("off");
  }

  function openNav(){
    $("#mySidenav").css("width", "50%");
    $(".container").css("background-color", "rgba(0,0,0,0.4)");
    $("#quill").css('background-image', 'url(image/quill-icon.png)');
    $("#quill").val("on");
  }

  $('#quill').on('click', function(e){
    var isOpened = $('#quill').val();
    console.log(isOpened);
    if(isOpened == "on"){
      closeNav();
    }
    else{
      openNav();
    }
  });

  $('.list-group-item').click(function(){
    closeNav();
    $('.carousel').css('display', 'none');
    $('.content').css('display', 'block');
  })
});
