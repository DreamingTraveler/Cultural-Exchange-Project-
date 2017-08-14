$(document).ready(function(){
  $('.icon-button').mouseover(function(){
    var target = $(this);
    target.find('.book-text').css('display', 'block');
    target.find('.menu-text').css('display', 'block');
  }).mouseleave(function(){
    var target = $(this);
    target.find('.book-text').css('display', 'none');
    target.find('.menu-text').css('display', 'none');
  });
});
