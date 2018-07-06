$(document).ready(() => {

	$("#join_game").click(event => {

		console.log("join");

		const $username = $("#username");

		if($username.val() === "") {
			$("#menu_error").html("<center>ERROR: Please enter a username before you join a game!</center>");
			document.getElementById("menu_error").style.color = "red";
		} else {
			if(current_lobby_name != "") {
				create_name($username.val());
				display_lobby();
				join_lobby();
			} else {
				$("#menu_error").html("<center>ERROR: Please select a lobby before you join a game!</center>");
				document.getElementById("menu_error").style.color = "red";
			}
		}

	});

	$("#create_game").click(event => {
		const $username = $("#username");

		if($username.val() === "") {
			$("#menu_error").html("<center>ERROR: Please enter a username in order to create a game!</center>");
			document.getElementById("menu_error").style.color = "red";
		} else {

			create_name($username.val());
			create_lobby();
			display_lobby();
		}

	});
});

function display_lobby() {

	console.log("entering lobby");

	$("#temp_lobby").html("");

	var lobby_text = `
	<div class="option wrapper">
		<p class="title">` + current_lobby_name + `</p>
		<div class="contents">
				<div id="members"></div>
	`;

	/*
	lobby_text += "<div class=\"option\" style=\"width:100%\">";
	lobby_text += "<center><font size=\"5\">You've entered the lobby of " + current_lobby_name + "</font></center>";
	lobby_text += "<center><div id=\"members\"></div></center>";
	*/
	if(game_host) {
		lobby_text += "<div class=\"menu_button\"><button type=\"button\" id=\"start_game\" class=\"myButton\">Start Game</button></div>";
	}

	lobby_text += "</div></div>";

	$("#temp_lobby").html(lobby_text);

	$("#start_game").click(event => {

		console.log("start clicked");

		start_game();
	});
}
