<!doctype html>
<html lang="en">
<head>
  <title>Betrayal at House on the Hill</title>
  <style>
  #first, #second, #basement {position: relative; top:-425px; left:-375px;}
  #second, #basement {display: none;}
  #box {width:800px; height:514px; border: solid black 1px; overflow: hidden; border-width: 0px 0px 2px 0; position: relative; z-index: 0;}
  #mapf, #maps, #mapb {overflow: hidden; border:solid black 1px; border-width: 0 2px 2px 0; width:170px; height:170px;}
  #map {display: flex; flex-direction:column;}
  #container {display:flex; flex-direction:row;}
  #descr {position: absolute; right: 0; bottom: 0; width:150px; height:514px;
    background: #eeeeee; border: solid black 1px; border-width: 1px 0 0 2px; z-index: 1;}
  #scription {position: absolute; bottom: 0; font: 30px Times New Roman;}
  .objective {position: relative;}
  button {padding:5px; margin:5px; margin-bottom:10px; background:#eeeeee; font-size:14px; width:130px; height:40px;}
  </style>
  <link href="https://fonts.googleapis.com/css?family=Squada+One" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Scada" rel="stylesheet">
  <link rel="stylesheet" href="css/main.css">
  <script src="js/jquery-3.1.1.js"></script>
  <script src="js/jquery-ui.js"></script>
  <script src="js/websocket.js"></script>
  <script src="js/begin_game.js"></script>
  <script src="js/all_functions.js"></script>
  <script src="js/menu.js"></script>
  <script src="js/card_draw.js"></script>
</head>
<body>


<div class="game">

	<div id="temp_lobby" class="main_menu">
		<div class="option wrapper">
			<p class="title">Betrayal</p>
        <div class="contents">

          <div class = "text_normal">
            <h2>
    			       <div class = "text_title">Username*: <input type="text" id="username" name="name" placeholder="" class = "text_normal"></p></div>
            </h2>
            <div id="menu_error" class="message"></div>

          </div>

          <div id="lobbies" class="wrapper text_normal"></div>

          <div class="menu_button">
  			       <button type="button" id="create_game" class="myButton">Create Game</button>
               <button type="button" id="join_game"  class="myButton">Join Game</button>
          </div>
		   </div>
    </div>
  </div>

  <div class="title_row">
    <div class="gameTitle">
       Betrayal at House on the Hill
    </div>
  </div>

  <div class="player_row box text_basic">

    <div id="player_2" class="player">
       <div class="image">
          <div id="icon_2" class="icon">
            <center><img src="http://www.thecatalystagency.com/wp-content/uploads/2015/06/placeholder-user-anon.png" alt="Player 2" style="width:75px;height:75px;border:2px solid black;"></center>
        </div>

        <div id="stats_2" class="stats"><center> X </center></div>
        <div id="stats_2_popup" class="popup_stats"></div>
      </div>

      <div class="info">
          <div id="name_2" class="name"></div>

          <div id="items_2" class="items"></div>
        </div>
    </div>

    <div id="player_3" class="player">
        <div class="image">
          <div id="icon_3" class="icon">
            <center><img src="http://www.thecatalystagency.com/wp-content/uploads/2015/06/placeholder-user-anon.png" alt="Player 3" style="width:75px;height:75px;border:2px solid black;"></center>
        </div>

        <div id="stats_3" class="stats"><center> X </center></div>
        <div id="stats_3_popup" class="popup_stats"></div>
      </div>

      <div class="info">
          <div id="name_3" class="name"></div>

          <div id="items_3" class="items"></div>
        </div>
    </div>

    <div id="player_4" class="player">
       <div class="image">
          <div id="icon_4" class="icon">
            <center><img src="http://www.thecatalystagency.com/wp-content/uploads/2015/06/placeholder-user-anon.png" alt="Player 4" style="width:75px;height:75px;border:2px solid black;"></center>
        </div>

        <div id="stats_4" class="stats"><center> X </center></div>
        <div id="stats_4_popup" class="popup_stats"></div>
      </div>

      <div class="info">
          <div id="name_4" class="name"> </div>

          <div id="items_4" class="items"></div>
        </div>
    </div>

    <div id="player_5" class="player">
       <div class="image">
          <div id="icon_5" class="icon">
            <center><img src="http://www.thecatalystagency.com/wp-content/uploads/2015/06/placeholder-user-anon.png" alt="Player 5" style="width:75px;height:75px;border:2px solid black;"></center>
        </div>

        <div id="stats_5" class="stats"><center> X </center></div>
        <div id="stats_5_popup" class="popup_stats"></div>
      </div>

      <div class="info">
          <div id="name_5" class="name"> </div>

          <div id="items_5" class="items"></div>
        </div>
    </div>

    <div id="player_6" class="player">
       <div class="image">
          <div id="icon_6" class="icon">
            <center><img src="http://www.thecatalystagency.com/wp-content/uploads/2015/06/placeholder-user-anon.png" alt="Player 6" style="width:75px;height:75px;border:2px solid black;"></center>
        </div>

        <div id="stats_6" class="stats"><center> X </center></div>
        <div id="stats_6_popup" class="popup_stats"></div>
      </div>

      <div class="info">
          <div id="name_6" class="name"> </div>

          <div id="items_6" class="items"></div>
        </div>
    </div>
  </div>

  <div class="main_game text_basic">
    <div id="player_1" class="player_1 box">

      <div id="name_1" class="name"><center> X </center></div>

      <div id="icon_1" class="icon_1">
          <center><img src="http://www.thecatalystagency.com/wp-content/uploads/2015/06/placeholder-user-anon.png" alt="Player 1" style="width:100px;height:100px;border-style:solid;border-width:2px;"></center>
      </div>
	
		Speed:
      <center><div id="speed_1" class="stat"><center> X </center></div></center>

		Might:
      <center><div id="might_1" class="stat"><center> X </center></div></center>

		Sanity:
      <center><div id="sanity_1" class="stat"><center> X </center></div></center>
	
		Knowledge:
      <center><div id="knowledge_1" class="stat"><center> X </center></div></center>
      
      <center><div id="items_1"></div></center>

    </div>

    <div class="board">

    <div id="item" class="card" draggable="true">

      <center><div id="item_name" class="card_name"></div></center>

      <div id="item_cont" class="card_cont">
      	<center><div id="item_info" class="card_info"></div></center>
	  	<center><div id="item_description" class="card_description"></div></center>
	  	<div id="item_logic" class="card_logic"></div>
	  </div>
	  <center><button type="button" id="end_turn">End Turn</button></center>

	</div>

	<div id="omen" class="card" draggable="true">
	    <center><div id="omen_name" class="card_name"></div></center>

        <div id="omen_cont" class="card_cont">
        	<center><div id="omen_info" class="card_info"></div></center>
	    	<center><div id="omen_description" class="card_description"></div></center>
	    	<div id="omen_logic" class="card_logic"></div>
	    	<center><button type="button" id="roll_haunt">Roll for Haunt</button></center>
	    	<div id="omen_roll" class="omen_roll"></div>
	    </div>

	</div>

	<div id="event" class="card" draggable="true">
	    <center><div id="event_name" class="card_name"></div></center>

      <div id="event_cont" class="card_cont">
      	<center><div id="event_info" class="card_info"></div></center>
      	<center><div id="event_description" class="card_description"></div></center>
      	<div id="event_logic" class="card_logic"></div>
	  	<center><div id="action_rolls"></div></center>
	    <div id="action_result"></div>
      </div>

	</div>
	
<div id="container">
  <div id="map">
    <div id="mapf"><canvas id="mfirst"></canvas></div>
    <div id="maps"><canvas id="msecond"></canvas></div>
    <div id="mapb"><canvas id="mbasement"></canvas></div>
  </div>
  <div id="box">
    <canvas id="first"></canvas>
    <canvas id="second"></canvas>
    <canvas id="basement"></canvas>
    <div id="descr">
    <center><div> Speed Left: <span id="moves">10</span></div></center>
    <center><button id="end" onclick="endturn();">End Turn</button></center>
    <!-- <center><button disabled>Attack</button></center>
    <center><button disabled>Pick up Items</button></center> -->
    <!-- <center><button disabled>Interact w/Room</button></center> -->
    <center><button id="plat" onclick="placeTile();" disabled>Place Tile</button></center>
    <center><button id="rot" onclick="rotate();" disabled>Rotate Clockwise</button></center>
    </div>
  </div>
</div>
</div>

    <div class="interaction">
      <div class="objective box">
        <div class="text_title center big">Objectives</div>

        <div id="objective_description" class="obj_info"></div>
        <center><div id="scription"></div></center>
      </div>

      <div class="chat box">
        <div class="text_title center big">Chat</div>
        <div class="log dark_bg" id="chat_log"></div>

        <div class="user_text center">
          <input type="text" id="chat_text" name="chat_text" placeholder="Enter Message" class = "text_normal">
        </div>

      </div>
    </div>
  </div>
</div>
<script src="js/movements.js"></script>
</body>
</html>
