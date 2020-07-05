function toggleShowPassword() {
	
	var inputSecret = $(".passwordInput");
	
	console.log(inputSecret);
	
	if(inputSecret.attr("type") == "password") {
		inputSecret.attr("type", "text");
	} else {
		inputSecret.attr("type", "password");
	}
	
	var icon = inputSecret.parent("div").children("span").children("button").children("span").children("i")
	
	icon.toggleClass("glyphicon-eye-close").toggleClass("glyphicon-eye-open");
}