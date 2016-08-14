$(document).ready(function(){

 $(".lang_select input").click(function(event){
  event.preventDefault()
  var lang = $(this).val();
  var url = window.location.href;
  var pos = url.indexOf("language=");
  if(pos != -1){
   url = url.substring(0, pos-1);
  }
  if(url.indexOf("?") == -1) {
   url += "?language=" + lang;
  } else {
   url += "&language=" + lang;
  }
  window.location.assign(url);
 });
});