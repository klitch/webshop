$(document).ready(function() {
    var lang = $("#cyka").text().trim();
      $.ajax({
            type: "POST",
            url: "style/js/" + lang +".js",
            cache: false,
            success: function(html) {
              console.log(html);
            }
        });
});