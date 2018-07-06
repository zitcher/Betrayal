let $item_window;
let $omen_window;
let $event_window;

$(document).ready(() => {
	
	$item_window = $("#item");
	$omen_window = $("#omen");
	$event_window = $("#event");

	$('#item').draggable();
	$('#omen').draggable();
	$('#event').draggable();
	
	$item_window.hide();
	$omen_window.hide();
	$event_window.hide();
});

function itemDrawn(data, card_info, room_name) {

	$item_window.show();
	$("#item_info").html(current_char_name + " has found an item in the " + room_name + ".");
	$("#item_name").html("<font size=\"4px\">" + card_info.name + "</font>");
	$("#item_description").html(card_info.description);
	$("#item_logic").html(card_info.logic);
	
	if(current_char != 0) {
		document.getElementById("end_turn").disabled = true;
	} else {
		document.getElementById("end_turn").disabled = false;
	}
	
	$("#end_turn").unbind().click(event => {

		$item_window.hide();
		
		turn_end();
		
		$("#item_info").html("");
		$("#item_name").html("");
		$("#item_description").html("");
		$("#item_logic").html("");
    });
}

function omenDrawn(data, card_info, room_name) {
	
	$omen_window.show();
	$("#omen_info").html(current_char_name + " has found an omen in the " + room_name + ".");
	$("#omen_name").html("<font size=\"4px\">" + card_info.name + "</font>");
	$("#omen_description").html(card_info.description);
	$("#omen_logic").html(card_info.logic);

	if(current_char != 0) {
		document.getElementById("roll_haunt").disabled = true;
	} else {
		document.getElementById("roll_haunt").disabled = false;
	}
	
	$("#roll_haunt").unbind().click(event => {

		document.getElementById("roll_haunt").disabled = true;
		
		var message = {
				type: MESSAGE_TYPE.GAMEMOVE,
				payload: {
					id : userId,
					query : {
						name : "haunt"
					}
				}
	      }

		const json = JSON.stringify(message);
		conn.send(json);
		
    });
    omen++;
    descr.innerHTML = "Omen Count: " + omen;
}

function omenRoll(data) {
	
	var rolls = data.rolls;
	var all_rolls = "<center>You rolled the following values:</center><br /><center>";
	var sum = 0;

	for(roll in rolls) {
		all_rolls += rolls[roll] + " ";
		sum += rolls[roll];
	}
	
	all_rolls += " = " + sum + "</center>" +
		"<center><button type=\"button\" id=\"end_turn_omen\">End Turn</button></center>";
	
	$("#omen_roll").html(all_rolls);
	var omen_info = document.getElementById("omen_cont");
	omen_info.scrollTop = omen_info.scrollHeight;
	
	if(current_char != 0) {
		document.getElementById("end_turn_omen").disabled = true;
	} else {
		document.getElementById("end_turn_omen").disabled = false;
	}
		
	$("#end_turn_omen").unbind().click(event => {

		$omen_window.hide();
		console.log("woah there");
		turn_end();
		
		$("#omen_roll").html("");
		$("#omen_info").html("");
		$("#omen_name").html("");
		$("#omen_description").html("");
		$("#omen_logic").html("");
    });
}

function eventDrawn(data, card_info, room_name) {
	
	$event_window.show();
	$("#event_info").html(current_char_name + " has encountered an event in the " + room_name + ".");
	$("#event_name").html("<font size=\"4px\">" + card_info.name + "</font>");
	$("#event_description").html(card_info.description);
	$("#event_logic").html(card_info.logic);
	
	console.log(JSON.parse(data.payload));
	
	var event_buttons = "<center>";
	var speed_button = false;
	var might_button = false;
	var know_button = false;
	var sanity_button = false;
	
	for(type_index in card_info.usable) {
		if(card_info.usable[type_index] === "SPEED") {
			event_buttons += "<button type=\"button\" id=\"roll_speed\">Speed Roll</button>";

			speed_button = true;
		}
		if(card_info.usable[type_index] === "MIGHT") {
			event_buttons += "<button type=\"button\" id=\"roll_might\">Might Roll</button>";

			might_button = true;
		}
		if(card_info.usable[type_index] === "KNOWLEGE") {
			event_buttons += "<button type=\"button\" id=\"roll_know\">Knowledge Roll</button>";

			know_button = true;
		}
		if(card_info.usable[type_index] === "SANITY") {
			event_buttons += "<button type=\"button\" id=\"roll_sanity\">Sanity Roll</button>";

			sanity_button = true;
		}
	}
	
	event_buttons += "</center>";
	
	console.log(event_buttons);
	
	$("#action_rolls").html(event_buttons);
	
	if(speed_button) {
		if(current_char != 0) {
			document.getElementById("roll_speed").disabled = true;
		} else {
			document.getElementById("roll_speed").disabled = false;
		}
	}
	
	if(might_button) {
		if(current_char != 0) {
			document.getElementById("roll_might").disabled = true;
		} else {
			document.getElementById("roll_might").disabled = false;
		}
	}
	
	if(know_button) {
		if(current_char != 0) {
			document.getElementById("roll_know").disabled = true;
		} else {
			document.getElementById("roll_know").disabled = false;
		}
	}
	
	if(sanity_button) {
		if(current_char != 0) {
			document.getElementById("roll_sanity").disabled = true;
		} else {
			document.getElementById("roll_sanity").disabled = false;
		}
	}
	
	$("#roll_speed").unbind().click(event => {
		
		document.getElementById("roll_speed").disabled = true;
		
		if(might_button) {
			document.getElementById("roll_might").disabled = true;
		}
		if(know_button) {
			document.getElementById("roll_know").disabled = true;
		}
		if(sanity_button) {
			document.getElementById("roll_sanity").disabled = true;
		}
		
		var message = {
				type: MESSAGE_TYPE.GAMEMOVE,
				payload: {
					id : userId,
					query : {
						name : "event",
						event : card_info.name,
						stat : "SPEED"
					}
				}
	      }

		const json = JSON.stringify(message);
		conn.send(json);
    });
	
	$("#roll_might").unbind().click(event => {
		
		document.getElementById("roll_might").disabled = true;
		
		if(speed_button) {
			document.getElementById("roll_speed").disabled = true;
		}
		if(know_button) {
			document.getElementById("roll_know").disabled = true;
		}
		if(sanity_button) {
			document.getElementById("roll_sanity").disabled = true;
		}
		
		var message = {
				type: MESSAGE_TYPE.GAMEMOVE,
				payload: {
					id : userId,
					query : {
						name : "event",
						event : card_info.name,
						stat : "MIGHT"
					}
				}
	      }

		const json = JSON.stringify(message);
		conn.send(json);
    });
	
	$("#roll_know").unbind().click(event => {
		
		document.getElementById("roll_know").disabled = true;

		if(might_button) {
			document.getElementById("roll_might").disabled = true;
		}
		if(speed_button) {
			document.getElementById("roll_speed").disabled = true;
		}
		if(sanity_button) {
			document.getElementById("roll_sanity").disabled = true;
		}
		
		var message = {
				type: MESSAGE_TYPE.GAMEMOVE,
				payload: {
					id : userId,
					query : {
						name : "event",
						event : card_info.name,
						stat : "KNOWLEDGE"
					}
				}
	      }

		const json = JSON.stringify(message);
		conn.send(json);
    });
	
	$("#roll_sanity").unbind().click(event => {
		
		document.getElementById("roll_sanity").disabled = true;
		
		if(might_button) {
			document.getElementById("roll_might").disabled = true;
		}
		if(know_button) {
			document.getElementById("roll_know").disabled = true;
		}
		if(speed_button) {
			document.getElementById("roll_speed").disabled = true;
		}
		
		var message = {
				type: MESSAGE_TYPE.GAMEMOVE,
				payload: {
					id : userId,
					query : {
						name : "event",
						event : card_info.name,
						stat : "SANITY"
					}
				}
	      }

		const json = JSON.stringify(message);
		conn.send(json);
    });
}


function eventRoll(data) {
	
	var rolls = data.rolls;
	var all_rolls = "<center>You rolled the following values:</center><br /><center>";
	var sum = 0;

	for(roll in rolls) {
		all_rolls += rolls[roll] + " ";
		sum += rolls[roll];
	}
	
	all_rolls += " = " + sum + "</center>" +
		data.result + 
		"<center><button type=\"button\" id=\"end_turn_event\">End Turn</button></center>";
	
	$("#action_result").html(all_rolls);
	var event_info = document.getElementById("event_cont");
	event_info.scrollTop = event_info.scrollHeight;
	
	if(current_char != 0) {
		document.getElementById("end_turn_event").disabled = true;
	} else {
		document.getElementById("end_turn_event").disabled = false;
	}
	
	$("#end_turn_event").unbind().click(event => {

		$event_window.hide();
		
		turn_end();
		
		$("#action_result").html("");
		$("#action_rolls1").html("");
		$("#event_info").html("");
		$("#event_name").html("");
		$("#event_description").html("");
		$("#event_logic").html("");
    });
}

function updateEvent(data) {
	if(current_char === 0) {
		update_player_1(data);
	} else if(current_char === 1) {
		update_player_2(data);
	} else if(current_char === 2) {
		update_player_3(data);
	} else if(current_char === 3) {
		update_player_4(data);
	} else if(current_char === 4) {
		update_player_5(data);
	} else if(current_char === 5) {
		update_player_6(data);
	} else {
		console.log("ERROR: should never get here, bad # of players");
	}
}

function addItem(data, card_info) {
	///refresh values
	
	console.log("refresh game and add item");
	
	if(current_char === 0) {
		document.getElementById("items_1").innerHTML += "<div id=\"" + card_info.name + "\" class=\"item\">" + card_info.name + "</div>" +
		"<div id=\"" + card_info.name + "_popup\" class=\"popup_stats\" style=\"top:500px\">" + card_info.logic + "</div>";
		
		update_player_1(data);
		
	} else if(current_char === 1) {
		document.getElementById("items_2").innerHTML += "<div id=\"" + card_info.name + "\" class=\"item\">" + card_info.name + "</div>" +
		"<div id=\"" + card_info.name + "_popup\" class=\"popup_stats\" style=\"top:150px\">" + card_info.logic + "</div>";
		
		update_player_2(data);
		
	} else if(current_char === 2) {
		document.getElementById("items_3").innerHTML += "<div id=\"" + card_info.name + "\" class=\"item\">" + card_info.name + "</div>" +
		"<div id=\"" + card_info.name + "_popup\" class=\"popup_stats\" style=\"top:150px\">" + card_info.logic + "</div>";
		
		update_player_3(data);
		
	} else if(current_char === 3) {
		document.getElementById("items_4").innerHTML += "<div id=\"" + card_info.name + "\" class=\"item\">" + card_info.name + "</div>" +
		"<div id=\"" + card_info.name + "_popup\" class=\"popup_stats\" style=\"top:150px\">" + card_info.logic + "</div>";
		
		update_player_4(data);
		
	} else if(current_char === 4) {
		document.getElementById("items_5").innerHTML += "<div id=\"" + card_info.name + "\" class=\"item\">" + card_info.name + "</div>" +
		"<div id=\"" + card_info.name + "_popup\" class=\"popup_stats\" style=\"top:150px\">" + card_info.logic + "</div>";
		
		update_player_5(data);
		
	} else if(current_char === 5) {
		document.getElementById("items_6").innerHTML += "<div id=\"" + card_info.name + "\" class=\"item\">" + card_info.name + "</div>" +
		"<div id=\"" + card_info.name + "_popup\" class=\"popup_stats\" style=\"top:150px\">" + card_info.logic + "</div>";
		
		update_player_6(data);
		
	} else {
		console.log("ERROR: should never get here, bad # of players");
	}

	console.log(document.getElementById(card_info.name));
	
	document.getElementById(card_info.name).onmouseover = function() {
        document.getElementById(card_info.name + "_popup").style.display = 'block';
    };
   
    document.getElementById(card_info.name).onmouseout = function() {
        document.getElementById(card_info.name + "_popup").style.display = 'none';
    };
}

function addOmen(data, card_info) {

	console.log("refresh game and add omen");
	
	if(current_char === 0) {
		document.getElementById("items_1").innerHTML += "<div id=\"" + card_info.name + "\" class=\"omen\">" + card_info.name + "</div>" +
		"<div id=\"" + card_info.name + "_popup\" class=\"popup_stats\" style=\"top:500px\">" + card_info.logic + "</div>";
		
		update_player_1(data);
		
	} else if(current_char === 1) {
		document.getElementById("items_2").innerHTML += "<div id=\"" + card_info.name + "\" class=\"omen\">" + card_info.name + "</div>" +
		"<div id=\"" + card_info.name + "_popup\" class=\"popup_stats\" style=\"top:150px\">" + card_info.logic + "</div>";
		
		update_player_2(data);
		
	} else if(current_char === 2) {
		document.getElementById("items_3").innerHTML += "<div id=\"" + card_info.name + "\" class=\"omen\">" + card_info.name + "</div>" +
		"<div id=\"" + card_info.name + "_popup\" class=\"popup_stats\" style=\"top:150px\">" + card_info.logic + "</div>";
		
		update_player_3(data);
		
	} else if(current_char === 3) {
		document.getElementById("items_4").innerHTML += "<div id=\"" + card_info.name + "\" class=\"omen\">" + card_info.name + "</div>" +
		"<div id=\"" + card_info.name + "_popup\" class=\"popup_stats\" style=\"top:150px\">" + card_info.logic + "</div>";
		
		update_player_4(data);
		
	} else if(current_char === 4) {
		document.getElementById("items_5").innerHTML += "<div id=\"" + card_info.name + "\" class=\"omen\">" + card_info.name + "</div>" +
		"<div id=\"" + card_info.name + "_popup\" class=\"popup_stats\" style=\"top:150px\">" + card_info.logic + "</div>";
		
		update_player_5(data);
		
	} else if(current_char === 5) {
		document.getElementById("items_6").innerHTML += "<div id=\"" + card_info.name + "\" class=\"omen\">" + card_info.name + "</div>" +
		"<div id=\"" + card_info.name + "_popup\" class=\"popup_stats\" style=\"top:150px\">" + card_info.logic + "</div>";
		
		update_player_6(data);
		
	} else {
		console.log("ERROR: should never get here, bad # of players");
	}

	document.getElementById(card_info.name).onmouseover = function() {
        document.getElementById(card_info.name + "_popup").style.display = 'block';
    };
   
    document.getElementById(card_info.name).onmouseout = function() {
        document.getElementById(card_info.name + "_popup").style.display = 'none';
    };
}

function update_player_1(data) {
	
	const $player_1_speed = $("#speed_1");
    var $speed = "<center>";
  
    for (let i = 0, len = data.speedScale.length; i < len; i++) {
       if(i === data.speed) {
           $speed += "<font color=\"red\">" + data.speedScale[i] + "</font> ";
       } else {
           $speed += data.speedScale[i] + " ";
       }
    }
  
    const $player_1_might = $("#might_1");
    var $might = "<center>";
  
    for (let i = 0, len = data.mightScale.length; i < len; i++) {
       if(i === data.might) {
           $might += "<font color=\"red\">" + data.mightScale[i] + "</font> ";
       } else {
           $might += data.mightScale[i] + " ";
       }
    }
  
    const $player_1_sanity = $("#sanity_1");
    
    console.log($player_1_sanity);
    var $sanity = "<center>";

    for (let i = 0, len = data.sanityScale.length; i < len; i++) {
       if(i === data.sanity) {
           $sanity += "<font color=\"red\">" + data.sanityScale[i] + "</font> ";
       } else {
           $sanity += data.sanityScale[i] + " ";
       }
    }
    console.log($player_1_sanity);

    const $player_1_knowledge = $("#knowledge_1");
    var $knowledge = "<center>";
  
    for (let i = 0, len = data.knowledgeScale.length; i < len; i++) {
       if(i === data.knowledge) {
           $knowledge += "<font color=\"red\">" + data.knowledgeScale[i] + "</font> ";
       } else {
           $knowledge += data.knowledgeScale[i] + " ";
       }
    }
    $player_1_speed.html("");
    $player_1_might.html("");
    $player_1_knowledge.html("");
    $player_1_sanity.html("");
    $player_1_speed.html($speed + "</center>");
    $player_1_might.html($might + "</center>");
    $player_1_knowledge.html($knowledge + "</center>");
    $player_1_sanity.html($sanity + "</center>");
}

function update_player_2(data) {
	
	const $player_2_stats = $("#stats_2");
	$player_2_stats.html("");

	$player_2_stats.html("<center>" + data.speedScale[data.speed] + " " +
		data.mightScale[data.might] + " " + 
		data.sanityScale[data.sanity] + " " +
		data.knowledgeScale[data.knowledge] + " " + "</center>");
}

function update_player_3(data) {
	
	const $player_3_stats = $("#stats_3");
	$player_3_stats.html("");

	$player_3_stats.html("<center>" + data.speedScale[data.speed] + " " +
		data.mightScale[data.might] + " " + 
		data.sanityScale[data.sanity] + " " +
		data.knowledgeScale[data.knowledge] + " " + "</center>");
}

function update_player_4(data) {
	
	const $player_4_stats = $("#stats_4");
	$player_4_stats.html("");

	$player_4_stats.html("<center>" + data.speedScale[data.speed] + " " +
		data.mightScale[data.might] + " " + 
		data.sanityScale[data.sanity] + " " +
		data.knowledgeScale[data.knowledge] + " " + "</center>");
}

function update_player_5(data) {
	
	const $player_5_stats = $("#stats_5");
	$player_5_stats.html("");

	$player_5_stats.html("<center>" + data.speedScale[data.speed] + " " +
		data.mightScale[data.might] + " " + 
		data.sanityScale[data.sanity] + " " +
		data.knowledgeScale[data.knowledge] + " " + "</center>");
}

function update_player_6(data) {
	
	const $player_6_stats = $("#stats_6");
	$player_6_stats.html("");

	$player_6_stats.html("<center>" + data.speedScale[data.speed] + " " +
		data.mightScale[data.might] + " " + 
		data.sanityScale[data.sanity] + " " +
		data.knowledgeScale[data.knowledge] + " " + "</center>");
}


