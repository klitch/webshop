$(document).on('keyup', "input[name='first_name']",  function() {
	var regexp = /^[a-zA-Zа-яА-ЯёЁ\s]+$/;
	validate(regexp, this, "#firstNameMessage", "name_incorrect");
});

$(document).on('keyup', "input[name='last_name']", function() {
	var regexp = /^[a-zA-Zа-яА-ЯёЁ\s]+$/;
	validate(regexp, this, "#lastNameMessage", "lastname_incorrect");
});

$(document).on('keyup', "input[name='email']", function() {
	var regexp = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
	validate(regexp, this, "#emailMessage", "email_incorrect");
});

$(document).on('keyup', "input[name='password']", function() {
	var regexp = /^(?=.*\d)(?=.*[a-zA-Zа-яА-ЯёЁ])\w{6,}$/;
	validate(regexp, this, "#passMessage", "pass_incorrect");
});

function validate(regexp, field, messageBox, message){
	if(regexp.test($(field).val())){
		$(messageBox).text("");
		$(field).removeClass("error");
        $(field)[0].setCustomValidity("");
		return true;
	} else{
		$(messageBox).text(translate[message]);
        $(field)[0].setCustomValidity(message);
		$(field).addClass("error");
	}
}
/*

$("form[name='register_form']").submit(function( event ) {
    $("form[name='register_form'] :input").each(function(){
        this.focus();
    });
    event.preventDefault();
});*/
