class Tile {
  constructor(posx, posy, north, east, south, west) {
    this.posx = posx;
    this.posy = posy;
    this.north = north;
    this.east = east;
    this.south = south;
    this.west = west;
  }
}
class Player {
  constructor(posx, posy, floor, north, east, south, west) {
    this.posx = posx;
    this.posy = posy;
    this.floor = floor;
    this.north = north;
    this.east = east;
    this.south = south;
    this.west = west;
  }
}
class Position {
  constructor(posx, posy) {
    this.posx = posx;
    this.posy = posy;
  }
}

let $player_1_turn;
let $player_2_turn;
let $player_3_turn;
let $player_4_turn;
let $player_5_turn;
let $player_6_turn;

const T = 150;
const P = 20;
const D = 50;
const X = 10;
const S = 7.5;
const offx = -375;
const offy = -425;
let allowkeys = false;
let turn = -1;
let rot = 0;
let rottile = null;
let avdoor = 0;
let tempdir = 0;
let turnorder = -1;
let omen = 0;
let colors = [];
const textOff = new Position(5, 135);
const symbOff = new Position(130, 143);
const outside = new Tile(450,600,false,false,false,false);
const ehall = new Tile(600,600,true,true,true,false);
const foyer = new Tile(750,600,true,true,true,true);
const toupper = new Tile(900,600,false,false,false,true);
const sland = new Tile(600,600,true,true,true,true);
const bland = new Tile(600,600,true,true,true,true);
let ftiles = [outside,ehall,foyer,toupper];
let stiles = [sland];
let btiles = [bland];
let posp1 = new Player(600,600,1,true,true,true,false);
let posp2 = new Player(600,600,1,true,true,true,false);
let posp3 = new Player(600,600,1,true,true,true,false);
let posp4 = new Player(600,600,1,true,true,true,false);
let posp5 = new Player(600,600,1,true,true,true,false);
let posp6 = new Player(600,600,1,true,true,true,false);
let positions = [posp1, posp2, posp3, posp4, posp5, posp6];

const offset1 = new Position(25,30);
const offset2 = new Position(105,30);
const offset3 = new Position(25,90);
const offset4 = new Position(105,90);
const offset5 = new Position(65,25);
const offset6 = new Position(65,95);
let offsets = [offset1, offset2, offset3, offset4, offset5, offset6];
const v = new Position(50,10);
const h = new Position(10,50);
function tileExists(posx, posy, floor) {
  let temp;
  if (floor == 0)
    temp = btiles;
  else if (floor == 1)
    temp = ftiles;
  else if (floor == 2)
    temp = stiles;
  for (let i = 0; i < temp.length; i++) {
    if (temp[i].posx === posx && temp[i].posy === posy)
      return i;
  }
  return -1;
}
$( function() {
  $( "#first" ).draggable();
} );
$( function() {
  $( "#second" ).draggable();
} );
$( function() {
  $( "#basement" ).draggable();
} );
$( function() {
  $( "#mfirst" ).draggable();
} );
$( function() {
  $( "#msecond" ).draggable();
} );
$( function() {
  $( "#mbasement" ).draggable();
} );
function endturn() {
  turn_end();
  ending.disabled = true;
}

function paintBoard(floor, players) {
  positions = positions.slice(0, players);
  offsets = offsets.slice(0, players);
  if (floor == 0 || floor == -1) {
    ctxb.strokeRect(600,600,T,T);
    ctxb.strokeRect(650,600,D,X);
    ctxb.strokeRect(650,740,D,X);
    ctxb.strokeRect(600,650,X,D);
    ctxb.strokeRect(740,650,X,D);
    ctxb.font = "17px Times New Roman";
    ctxb.fillText("Basement Landing", 600 + textOff.posx, 600 + textOff.posy);
    ctxmb.strokeRect(80,80,P,P);
    if (btiles.length > 1) {
      for (let i = 1; i < btiles.length; i++) {
        ctxb.strokeRect(btiles[i].posx, btiles[i].posy, T, T);
        ctxmb.strokeRect(btiles[i].posx / S, btiles[i].posy / S, P, P);
        if (btiles[i].north)
          ctxb.strokeRect(btiles[i].posx + D, btiles[i].posy, D, X);
        if (btiles[i].east)
          ctxb.strokeRect(btiles[i].posx + T - X, btiles[i].posy + D, X, D);
        if (btiles[i].south)
          ctxb.strokeRect(btiles[i].posx + D, btiles[i].posy + T - X, D, X);
        if (btiles[i].west)
          ctxb.strokeRect(btiles[i].posx, btiles[i].posy + D, X, D);
      }
    }
  }
  if (floor == 1 || floor == -1) {
    ctxf.strokeRect(600,600,T*3,T);
    ctxf.font = "17px Times New Roman";
    ctxf.fillText("Entrance Hall", 600 + textOff.posx, 600 + textOff.posy);
    ctxf.fillText("Foyer", 750 + textOff.posx, 600 + textOff.posy);
    ctxf.fillText("Grand Staircase", 900 + textOff.posx, 600 + textOff.posy);
    ctxf.moveTo(600,750);
    ctxf.arc(637.5,675,75,2*Math.PI/3,4*Math.PI/3);
    ctxf.stroke();
    ctxf.strokeRect(650,600,D,X);
    ctxf.strokeRect(650,740,D,X);
    ctxf.strokeRect(800,600,D,X);
    ctxf.strokeRect(800,740,D,X);
    ctxf.strokeRect(1040,610,X,130);
    ctxf.strokeRect(1030,620,X,110);
    ctxf.strokeRect(1020,652,X,36);
    ctxmf.strokeRect(80,80,P*3,P);
    ctxmf.strokeRect(1040 / S,610 / S,10 / S,130 / S);
    ctxmf.strokeRect(1030 / S,620 / S,10 / S,110 / S);
    ctxmf.strokeRect(1020 / S,652 / S,10 / S,36 / S);
    if (ftiles.length > 4) {
      for (let i = 4; i < ftiles.length; i++) {
        ctxf.strokeRect(ftiles[i].posx, ftiles[i].posy, T, T);
        ctxmf.strokeRect(ftiles[i].posx / S, ftiles[i].posy / S, P, P);
        if (ftiles[i].north)
          ctxf.strokeRect(ftiles[i].posx + D, ftiles[i].posy, D, X);
        if (ftiles[i].east)
          ctxf.strokeRect(ftiles[i].posx + T - X, ftiles[i].posy + D, X, D);
        if (ftiles[i].south)
          ctxf.strokeRect(ftiles[i].posx + D, ftiles[i].posy + T - X, D, X);
        if (ftiles[i].west)
          ctxf.strokeRect(ftiles[i].posx, ftiles[i].posy + D, X, D);
      }
    }
  }
  if (floor == 2 || floor == -1) {
    ctxs.strokeRect(600,600,T,T);
    ctxs.font = "17px Times New Roman";
    ctxs.fillText("Upper Landing", 600 + textOff.posx, 600 + textOff.posy);
    ctxs.strokeRect(650,600,D,X);
    ctxs.strokeRect(650,740,D,X);
    ctxs.strokeRect(600,650,X,D);
    ctxs.strokeRect(740,650,X,D);
    ctxs.strokeRect(640,660,70,X);
    ctxs.strokeRect(650,670,50,X);
    ctxs.strokeRect(660,680,30,X);
    ctxms.strokeRect(80,80,P,P);
    ctxms.strokeRect(640 / S,660 / S,70 / S,10 / S);
    ctxms.strokeRect(650 / S,670 / S,50 / S,10 / S);
    ctxms.strokeRect(660 / S,680 / S,30 / S,10 / S);
    if (stiles.length > 1) {
      for (let i = 1; i < stiles.length; i++) {
        ctxs.strokeRect(stiles[i].posx, stiles[i].posy, T, T);
        ctxms.strokeRect(stiles[i].posx / S, stiles[i].posy / S, P, P);
        if (stiles[i].north)
          ctxs.strokeRect(stiles[i].posx + D, stiles[i].posy, D, X);
        if (stiles[i].east)
          ctxs.strokeRect(stiles[i].posx + T - X, stiles[i].posy + D, X, D);
        if (stiles[i].south)
          ctxs.strokeRect(stiles[i].posx + D, stiles[i].posy + T - X, D, X);
        if (stiles[i].west)
          ctxs.strokeRect(stiles[i].posx, stiles[i].posy + D, X, D);
      }
    }
  }
  for (let i = 0; i < positions.length; i++) {
    if (floor == positions[i].floor || floor == -1) {
      if (positions[i].floor == 0) {
        ctxb.fillStyle = colors[i];
        ctxb.fillRect(positions[i].posx + offsets[i].posx, positions[i].posy + offsets[i].posy, P, P);
        ctxmb.fillRect((positions[i].posx + offsets[i].posx) / S,
          (positions[i].posy + offsets[i].posy) / S, P / S, P / S);
      }
      else if (positions[i].floor == 1) {
        ctxf.fillStyle = colors[i];
        ctxf.fillRect(positions[i].posx + offsets[i].posx, positions[i].posy + offsets[i].posy, P, P);
        ctxmf.fillRect((positions[i].posx + offsets[i].posx) / S,
          (positions[i].posy + offsets[i].posy) / S, P / S, P / S);
      }
      else if (positions[i].floor == 2) {
        ctxs.fillStyle = colors[i];
        ctxs.fillRect(positions[i].posx + offsets[i].posx, positions[i].posy + offsets[i].posy, P, P);
        ctxms.fillRect((positions[i].posx + offsets[i].posx) / S,
          (positions[i].posy + offsets[i].posy) / S, P / S, P / S);
      }
    }
  }
  ctxb.fillStyle = "black";
  ctxf.fillStyle = "black";
  ctxs.fillStyle = "black";
}

function rotate() {
  let ctx;
  if (positions[turn].floor == 0)
    ctx = ctxb;
  else if (positions[turn].floor == 1)
    ctx = ctxf;
  else if (positions[turn].floor == 2)
    ctx = ctxs;
  let rem = 0;
  let befn = rottile.north;
  let befe = rottile.east;
  let befs = rottile.south;
  let befw = rottile.west;
  while (true) {
    if (rottile.north) {
      rem++;
      if (rem == avdoor) {
        if (avdoor == 3)
          rottile.west = true;
        else if (avdoor == 2)
          rottile.west = false;
        rottile.east = true;
        rottile.north = true;
        rottile.south = false;
        break;
      }
    } else
      rem = 0;
    if (rottile.east) {
      rem++;
      if (rem == avdoor) {
        if (avdoor == 3)
          rottile.north = true;
        else if (avdoor == 2)
          rottile.north = false;
        rottile.south = true;
        rottile.east = true;
        rottile.west = false;
        break;
      }
    } else
      rem = 0;
    if (rottile.south) {
      rem++;
      if (rem == avdoor) {
        if (avdoor == 3)
          rottile.east = true;
        else if (avdoor == 2)
          rottile.east = false;
        rottile.west = true;
        rottile.south = true;
        rottile.north = false;
        break;
      }
    } else
      rem = 0;
    if (rottile.west) {
      rem++;
      if (rem == avdoor) {
        if (avdoor == 3)
          rottile.south = true;
        else if (avdoor == 2)
          rottile.south = false;
        rottile.north = true;
        rottile.west = true;
        rottile.east = false;
        break;
      }
    } else
      rem = 0;
  }
  if (rottile.north && !befn)
    ctx.strokeRect(positions[turn].posx + D, positions[turn].posy, D, X);
  if (!rottile.north && befn)
    ctx.clearRect(positions[turn].posx + D - 1, positions[turn].posy + 1, D + 2, X);
  if (rottile.east && !befe)
    ctx.strokeRect(positions[turn].posx + T - X, positions[turn].posy + D, X, D);
  if (!rottile.east && befe)
    ctx.clearRect(positions[turn].posx + T - X - 1, positions[turn].posy + D - 1, X, D + 2);
  if (rottile.south && !befs)
    ctx.strokeRect(positions[turn].posx + D, positions[turn].posy + T - X, D, X);
  if (!rottile.south && befs)
    ctx.clearRect(positions[turn].posx + D - 1, positions[turn].posy + T - X - 1, D + 2, X);
  if (rottile.west && !befw)
    ctx.strokeRect(positions[turn].posx, positions[turn].posy + D, X, D);
  if (!rottile.west && befw)
    ctx.clearRect(positions[turn].posx + 1, positions[turn].posy + D - 1, X, D + 2);
  rot++;
}

function placeTile() {
  console.log("numrot:" + rot);
  if (tempdir == 0) {
    if (!rottile.south)
      return;
  }
  if (tempdir == 1) {
    if (!rottile.west)
      return;
  }
  if (tempdir == 2) {
    if (!rottile.north)
      return;
  }
  if (tempdir == 3) {
    if (!rottile.east)
      return;
  }
  let flo;
  if (positions[turn].floor == 0)
    flo = btiles;
  else if (positions[turn].floor == 1)
    flo = ftiles;
  else if (positions[turn].floor == 2)
    flo = stiles;
  flo.push(rottile);
  positions[turn].north = rottile.north;
  positions[turn].east = rottile.east;
  positions[turn].south = rottile.south;
  positions[turn].west = rottile.west;
  game_move({name: "move", rotations: "" + rot});
  rotation.disabled = true;
  placet.disabled = true;
  ending.disabled = false;
  rot = 0;
}

function receiveCard(data) {
  if (positions[turn].floor == 0)
    ctx = ctxb;
  else if (positions[turn].floor == 1)
    ctx = ctxf;
  else if (positions[turn].floor == 2)
    ctx = ctxs;
	var ro = JSON.parse(data.payload);
  ctx.clearRect(positions[turn].posx + D - 1, positions[turn].posy + 1, D + 2, X);
  ctx.clearRect(positions[turn].posx + T - X - 1, positions[turn].posy + D - 1, X, D + 2);
  ctx.clearRect(positions[turn].posx + D - 1, positions[turn].posy + T - X - 1, D + 2, X);
  ctx.clearRect(positions[turn].posx + 1, positions[turn].posy + D - 1, X, D + 2);
  if (ro.newTile.availableDoors.indexOf("NORTH") !== -1) {
    ctx.strokeRect(positions[turn].posx + D, positions[turn].posy, D, X);
    positions[turn].north = true;
  } else 
    positions[turn].north = false;
  if (ro.newTile.availableDoors.indexOf("EAST") !== -1) {
    ctx.strokeRect(positions[turn].posx + T - X, positions[turn].posy + D, X, D);
    positions[turn].east = true;
  } else
    positions[turn].east = true;
  if (ro.newTile.availableDoors.indexOf("SOUTH") !== -1) {
    ctx.strokeRect(positions[turn].posx + D, positions[turn].posy + T - X, D, X);
    positions[turn].south = true;
  } else 
    positions[turn].south = false;
  if (ro.newTile.availableDoors.indexOf("WEST") !== -1)  {
    ctx.strokeRect(positions[turn].posx, positions[turn].posy + D, X, D);
    positions[turn].west = true;
  } else 
    positions[turn].west = false;
  if (ro.item.length > 0 || ro.omen.length > 0 || ro.event.length > 0) {
    if (ro.item.length > 0)
      itemDrawn(data, ro.item[0], ro.newTile.name);
    else if (ro.omen.length > 0)
      omenDrawn(data, ro.omen[0], ro.newTile.name);
    else if (ro.event.length > 0)
      eventDrawn(data, ro.event[0], ro.newTile.name);
    ending.disabled = true;
    console.log("moving " + moves);
    moves = 0;
    movesp.innerHTML = 0;
  }
}

function actualMovement(responseJSON) {
  let ctx;
  let flo;
  let lev;
  let ctxm;
  let levm;
  if (positions[turn].floor == 0) {
    ctx = ctxb;
    flo = btiles;
    lev = basement;
    ctxm = ctxmb;
    levm = mbasement;
  }
  else if (positions[turn].floor == 1) {
    ctx = ctxf;
    flo = ftiles;
    lev = first;
    ctxm = ctxmf;
    levm = mfirst;
  }
  else if (positions[turn].floor == 2) {
    ctx = ctxs;
    flo = stiles;
    lev = second;
    ctxm = ctxms;
    levm = msecond;
  }
  ctx.fillStyle = colors[turn];
  ctxm.fillStyle = colors[turn];
  responseObject = JSON.parse(responseJSON.payload);
  console.log(responseObject);
  switch(responseObject.direction) {
    case "NORTH":
      ctx.clearRect(positions[turn].posx + offsets[turn].posx - 1,
        positions[turn].posy + offsets[turn].posy - 1, P + 2, P + 2);
      ctx.fillRect(positions[turn].posx + offsets[turn].posx,
        positions[turn].posy - T + offsets[turn].posy, P, P);
      ctxm.clearRect((positions[turn].posx + offsets[turn].posx) / S - 1,
        (positions[turn].posy + offsets[turn].posy) / S - 1, P / S + 2, P / S + 2);
      ctxm.fillRect((positions[turn].posx + offsets[turn].posx) / S,
        (positions[turn].posy - T + offsets[turn].posy) / S, P / S, P / S);
      ctx.fillStyle = "black";
      ctxm.fillStyle = "black";
      if (!responseObject.finished) {
        ctx.strokeRect(positions[turn].posx, positions[turn].posy - T, T, T);
        ctxm.strokeRect(positions[turn].posx / S, (positions[turn].posy - T) / S, P, P);
        positions[turn].posy = positions[turn].posy - T;
        if (responseObject.newtile.availableDoors.length == 4) {
          ctx.strokeRect(positions[turn].posx + D, positions[turn].posy, D, X);
          ctx.strokeRect(positions[turn].posx + T - X, positions[turn].posy + D, X, D);
          ctx.strokeRect(positions[turn].posx + D, positions[turn].posy + T - X, D, X);
          ctx.strokeRect(positions[turn].posx, positions[turn].posy + D, X, D);
          game_move({name: "move", rotations: "0"});
          positions[turn].north = true;
          positions[turn].east = true;
          positions[turn].south = true;
          positions[turn].west = true;
          const ntile = new Tile(positions[turn].posx, positions[turn].posy, true, true, true, true);
          flo.push(ntile);
        } else if (responseObject.newtile.availableDoors.length == 3) {
          ctx.strokeRect(positions[turn].posx + D, positions[turn].posy , D, X);
          ctx.strokeRect(positions[turn].posx + T - X, positions[turn].posy + D, X, D);
          ctx.strokeRect(positions[turn].posx + D, positions[turn].posy + T - X, D, X);
          if (turnIndex == turn) {
            rotation.disabled = false;
            placet.disabled = false;
            ending.disabled = true;
          }
          rot = 0;
          avdoor = 3;
          tempdir = 0;
          rottile = new Tile(positions[turn].posx, positions[turn].posy, true, true, true, false);
          positions[turn].north = true;
          positions[turn].east = true;
          positions[turn].south = true;
          positions[turn].west = false;
        } else if (responseObject.newtile.availableDoors.length == 2) {
          if ((responseObject.newtile.availableDoors.indexOf("NORTH") != -1 &&
            responseObject.newtile.availableDoors.indexOf("SOUTH") != -1) ||
            (responseObject.newtile.availableDoors.indexOf("EAST") != -1 &&
            responseObject.newtile.availableDoors.indexOf("WEST") != -1)) {
            ctx.strokeRect(positions[turn].posx + D, positions[turn].posy, D, X);
            ctx.strokeRect(positions[turn].posx + D, positions[turn].posy + T - X, D, X);
            game_move({name: "move", rotations: "0"});
            positions[turn].north = true;
            positions[turn].east = false;
            positions[turn].south = true;
            positions[turn].west = false;    
          } else {
            ctx.strokeRect(positions[turn].posx + T - X, positions[turn].posy + D, X, D);
            ctx.strokeRect(positions[turn].posx + D, positions[turn].posy + T - X, D, X);
            if (turnIndex == turn) {
              rotation.disabled = false;
              placet.disabled = false;
              ending.disabled = true;
            }
            rot = 1;
            avdoor = 2;
            tempdir = 0;
            rottile = new Tile(positions[turn].posx, positions[turn].posy, false, true, true, false);
            positions[turn].north = false;
            positions[turn].east = true;
            positions[turn].south = true;
            positions[turn].west = false;
          }

        } else if (responseObject.newtile.availableDoors.length == 1) {
          ctx.strokeRect(positions[turn].posx + D, positions[turn].posy + T - X, D, X);
          game_move({name: "move", rotations: "2"});
          positions[turn].north = false;
          positions[turn].east = false;
          positions[turn].south = true;
          positions[turn].west = false;
          const ntile = new Tile(positions[turn].posx, positions[turn].posy, false, false, true, false);
          flo.push(ntile);
        }
        ctx.font = "17px Times New Roman";
        ctx.fillText(responseObject.newtile.name, positions[turn].posx + textOff.posx,
          positions[turn].posy + textOff.posy);
        ctx.font = "25px Times New Roman";
        if (responseObject.newtile.eventCount > 0)
          ctx.fillText("E", positions[turn].posx + symbOff.posx, positions[turn].posy + symbOff.posy);
        else if (responseObject.newtile.itemCount > 0)
          ctx.fillText("I", positions[turn].posx + symbOff.posx + 6, positions[turn].posy + symbOff.posy);
        else if (responseObject.newtile.omenCount > 0)
          ctx.fillText("O", positions[turn].posx + symbOff.posx - 2, positions[turn].posy + symbOff.posy);
      } else {
          positions[turn].posy = positions[turn].posy - T;
          const temp = tileExists(positions[turn].posx, positions[turn].posy, positions[turn].floor);
          positions[turn].north = flo[temp].north;
          positions[turn].east = flo[temp].east;
          positions[turn].south = flo[temp].south;
          positions[turn].west = flo[temp].west;
      }
      break;
    case "EAST":
      ctx.clearRect(positions[turn].posx + offsets[turn].posx - 1,
        positions[turn].posy + offsets[turn].posy - 1, P + 2, P + 2);
      ctx.fillRect(positions[turn].posx + T + offsets[turn].posx,
        positions[turn].posy + offsets[turn].posy, P, P);
      ctxm.clearRect((positions[turn].posx + offsets[turn].posx) / S - 1,
        (positions[turn].posy + offsets[turn].posy) / S - 1, P / S + 2, P / S + 2);
      ctxm.fillRect((positions[turn].posx + T + offsets[turn].posx) / S,
        (positions[turn].posy + offsets[turn].posy) / S, P / S, P / S);
      ctx.fillStyle = "black";
      ctxm.fillStyle = "black";
      if (!responseObject.finished) {
        ctx.strokeRect(positions[turn].posx + T, positions[turn].posy, T, T);
        ctxm.strokeRect((positions[turn].posx + T) / S, positions[turn].posy / S, P, P);
        positions[turn].posx = positions[turn].posx + T;
        if (responseObject.newtile.availableDoors.length == 4) {
          ctx.strokeRect(positions[turn].posx + D, positions[turn].posy, D, X);
          ctx.strokeRect(positions[turn].posx + T - X, positions[turn].posy + D, X, D);
          ctx.strokeRect(positions[turn].posx + D, positions[turn].posy + T - X, D, X);
          ctx.strokeRect(positions[turn].posx, positions[turn].posy + D, X, D);
          game_move({name: "move", rotations: "0"});
          positions[turn].north = true;
          positions[turn].east = true;
          positions[turn].south = true;
          positions[turn].west = true;
          const ntile = new Tile(positions[turn].posx + T, positions[turn].posy, true, true, true, true);
          flo.push(ntile);
        } else if (responseObject.newtile.availableDoors.length == 3) {
          ctx.strokeRect(positions[turn].posx + T - X, positions[turn].posy + D, X, D);
          ctx.strokeRect(positions[turn].posx + D, positions[turn].posy + T - X, D, X);
          ctx.strokeRect(positions[turn].posx, positions[turn].posy + D, X, D);
          if (turnIndex == turn) {
            rotation.disabled = false;
            placet.disabled = false;
            ending.disabled = true;
          }
          rot = 1;
          avdoor = 3;
          tempdir = 1;
          rottile = new Tile(positions[turn].posx, positions[turn].posy, false, true, true, true);
          positions[turn].north = false;
          positions[turn].east = true;
          positions[turn].south = true;
          positions[turn].west = true;
        } else if (responseObject.newtile.availableDoors.length == 2) {
          if ((responseObject.newtile.availableDoors.indexOf("NORTH") != -1 &&
            responseObject.newtile.availableDoors.indexOf("SOUTH") != -1) ||
            (responseObject.newtile.availableDoors.indexOf("EAST") != -1 &&
            responseObject.newtile.availableDoors.indexOf("WEST") != -1)) {
            ctx.strokeRect(positions[turn].posx + T - X, positions[turn].posy + D, X, D);
            ctx.strokeRect(positions[turn].posx, positions[turn].posy + D, X, D);
            game_move({name: "move", rotations: "1"});
            positions[turn].north = false;
            positions[turn].east = true;
            positions[turn].south = false;
            positions[turn].west = true;    
          } else {
            ctx.strokeRect(positions[turn].posx + D, positions[turn].posy + T - X, D, X);
            ctx.strokeRect(positions[turn].posx, positions[turn].posy + D, X, D);
            if (turnIndex == turn) {
              rotation.disabled = false;
              placet.disabled = false;
              ending.disabled = true;
            }
            rot = 2;
            avdoor = 2;
            tempdir = 1;
            rottile = new Tile(positions[turn].posx, positions[turn].posy, false, false, true, true);
            positions[turn].north = false;
            positions[turn].east = false;
            positions[turn].south = true;
            positions[turn].west = true;
          }

        } else if (responseObject.newtile.availableDoors.length == 1) {
          ctx.strokeRect(positions[turn].posx, positions[turn].posy + D, X, D);
          game_move({name: "move", rotations: "3"});
          positions[turn].north = false;
          positions[turn].east = false;
          positions[turn].south = false;
          positions[turn].west = true;
          const ntile = new Tile(positions[turn].posx, positions[turn].posy, false, false, false, true);
          flo.push(ntile);
        }
        ctx.font = "17px Times New Roman";
        ctx.fillText(responseObject.newtile.name, positions[turn].posx + textOff.posx,
          positions[turn].posy + textOff.posy);
        ctx.font = "25px Times New Roman";
        if (responseObject.newtile.eventCount > 0)
          ctx.fillText("E", positions[turn].posx + symbOff.posx, positions[turn].posy + symbOff.posy);
        else if (responseObject.newtile.itemCount > 0)
          ctx.fillText("I", positions[turn].posx + symbOff.posx + 6, positions[turn].posy + symbOff.posy);
        else if (responseObject.newtile.omenCount > 0)
          ctx.fillText("O", positions[turn].posx + symbOff.posx - 2, positions[turn].posy + symbOff.posy);
      } else {
          positions[turn].posx = positions[turn].posx + T;
          const temp = tileExists(positions[turn].posx, positions[turn].posy, positions[turn].floor);
          positions[turn].north = flo[temp].north;
          positions[turn].east = flo[temp].east;
          positions[turn].south = flo[temp].south;
          positions[turn].west = flo[temp].west;
      }
      break;
    case "SOUTH":
      ctx.clearRect(positions[turn].posx + offsets[turn].posx - 1,
        positions[turn].posy + offsets[turn].posy - 1, P + 2, P + 2);
      ctx.fillRect(positions[turn].posx + offsets[turn].posx,
        positions[turn].posy + T + offsets[turn].posy, P, P);
      ctxm.clearRect((positions[turn].posx + offsets[turn].posx) / S - 1,
        (positions[turn].posy + offsets[turn].posy) / S - 1, P / S + 2, P / S + 2);
      ctxm.fillRect((positions[turn].posx + offsets[turn].posx) / S,
        (positions[turn].posy + T + offsets[turn].posy) / S, P / S, P / S);
      ctx.fillStyle = "black";
      ctxm.fillStyle = "black";
      if (!responseObject.finished) {
        ctx.strokeRect(positions[turn].posx, positions[turn].posy + T, T, T);
        ctxm.strokeRect(positions[turn].posx / S, (positions[turn].posy + T) / S, P, P);
        positions[turn].posy = positions[turn].posy + T;
        if (responseObject.newtile.availableDoors.length == 4) {
          ctx.strokeRect(positions[turn].posx + D, positions[turn].posy, D, X);
          ctx.strokeRect(positions[turn].posx + T - X, positions[turn].posy + D, X, D);
          ctx.strokeRect(positions[turn].posx + D, positions[turn].posy + T - X, D, X);
          ctx.strokeRect(positions[turn].posx, positions[turn].posy + D, X, D);
          game_move({name: "move", rotations: "0"});
          positions[turn].north = true;
          positions[turn].east = true;
          positions[turn].south = true;
          positions[turn].west = true;
          const ntile = new Tile(positions[turn].posx, positions[turn].posy, true, true, true, true);
          flo.push(ntile);
        } else if (responseObject.newtile.availableDoors.length == 3) {
          ctx.strokeRect(positions[turn].posx + D, positions[turn].posy, D, X);
          ctx.strokeRect(positions[turn].posx + T - X, positions[turn].posy + D, X, D);
          ctx.strokeRect(positions[turn].posx + D, positions[turn].posy + T - X, D, X);
          if (turnIndex == turn) {
            rotation.disabled = false;
            placet.disabled = false;
            ending.disabled = true;
          }
          rot = 0;
          avdoor = 3;
          tempdir = 2;
          rottile = new Tile(positions[turn].posx, positions[turn].posy, true, true, true, false);
          positions[turn].north = true;
          positions[turn].east = true;
          positions[turn].south = true;
          positions[turn].west = false;
        } else if (responseObject.newtile.availableDoors.length == 2) {
          if ((responseObject.newtile.availableDoors.indexOf("NORTH") != -1 &&
            responseObject.newtile.availableDoors.indexOf("SOUTH") != -1) ||
            (responseObject.newtile.availableDoors.indexOf("EAST") != -1 &&
            responseObject.newtile.availableDoors.indexOf("WEST") != -1)) {
            ctx.strokeRect(positions[turn].posx + D, positions[turn].posy, D, X);
            ctx.strokeRect(positions[turn].posx + D, positions[turn].posy + T - X, D, X);
            game_move({name: "move", rotations: "0"});
            positions[turn].north = true;
            positions[turn].east = false;
            positions[turn].south = true;
            positions[turn].west = false;    
          } else {
            ctx.strokeRect(positions[turn].posx + D, positions[turn].posy, D, X);
            ctx.strokeRect(positions[turn].posx + T - X, positions[turn].posy + D, X, D);
            if (turnIndex == turn) {
              rotation.disabled = false;
              placet.disabled = false;
              ending.disabled = true;
            }
            rot = 0;
            avdoor = 2;
            tempdir = 2;
            rottile = new Tile(positions[turn].posx, positions[turn].posy, true, true, false, false);
            positions[turn].north = true;
            positions[turn].east = true;
            positions[turn].south = false;
            positions[turn].west = false;
          }

        } else if (responseObject.newtile.availableDoors.length == 1) {
          ctx.strokeRect(positions[turn].posx + D, positions[turn].posy, D, X);
          game_move({name: "move", rotations: "0"});
          positions[turn].north = true;
          positions[turn].east = false;
          positions[turn].south = false;
          positions[turn].west = false;
          const ntile = new Tile(positions[turn].posx, positions[turn].posy, true, false, false, false);
          flo.push(ntile);
        }
        ctx.font = "17px Times New Roman";
        ctx.fillText(responseObject.newtile.name, positions[turn].posx + textOff.posx, positions[turn].posy + textOff.posy);
        ctx.font = "25px Times New Roman";
        if (responseObject.newtile.eventCount > 0)
          ctx.fillText("E", positions[turn].posx + symbOff.posx, positions[turn].posy + symbOff.posy);
        else if (responseObject.newtile.itemCount > 0)
          ctx.fillText("I", positions[turn].posx + symbOff.posx + 6, positions[turn].posy + symbOff.posy);
        else if (responseObject.newtile.omenCount > 0)
          ctx.fillText("O", positions[turn].posx + symbOff.posx - 2, positions[turn].posy + symbOff.posy);
      } else {
          positions[turn].posy = positions[turn].posy + T;
          const temp = tileExists(positions[turn].posx, positions[turn].posy, positions[turn].floor);
          positions[turn].north = flo[temp].north;
          positions[turn].east = flo[temp].east;
          positions[turn].south = flo[temp].south;
          positions[turn].west = flo[temp].west;
      }
      break;
    case "WEST":
      ctx.clearRect(positions[turn].posx + offsets[turn].posx - 1,
        positions[turn].posy + offsets[turn].posy - 1, P + 2, P + 2);
      ctx.fillRect(positions[turn].posx - T + offsets[turn].posx,
        positions[turn].posy + offsets[turn].posy, P, P);
      ctxm.clearRect((positions[turn].posx + offsets[turn].posx) / S - 1,
        (positions[turn].posy + offsets[turn].posy) / S - 1, P / S + 2, P / S + 2);
      ctxm.fillRect((positions[turn].posx  - T + offsets[turn].posx) / S,
        (positions[turn].posy + offsets[turn].posy) / S, P / S, P / S);
      ctx.fillStyle = "black";
      ctxm.fillStyle = "black";
      if (!responseObject.finished) {
        ctx.strokeRect(positions[turn].posx - T, positions[turn].posy, T, T);
        ctxm.strokeRect((positions[turn].posx - T) / S, positions[turn].posy / S, P, P);
        positions[turn].posx = positions[turn].posx - T;
        if (responseObject.newtile.availableDoors.length == 4) {
          ctx.strokeRect(positions[turn].posx + D, positions[turn].posy, D, X);
          ctx.strokeRect(positions[turn].posx + T - X, positions[turn].posy + D, X, D);
          ctx.strokeRect(positions[turn].posx + D, positions[turn].posy + T - X, D, X);
          ctx.strokeRect(positions[turn].posx, positions[turn].posy + D, X, D);
          game_move({name: "move", rotations: "0"});
          positions[turn].north = true;
          positions[turn].east = true;
          positions[turn].south = true;
          positions[turn].west = true;
          const ntile = new Tile(positions[turn].posx, positions[turn].posy, true, true, true, true);
          flo.push(ntile);
        } else if (responseObject.newtile.availableDoors.length == 3) {
          ctx.strokeRect(positions[turn].posx + D, positions[turn].posy, D, X);
          ctx.strokeRect(positions[turn].posx + T - X, positions[turn].posy + D, X, D);
          ctx.strokeRect(positions[turn].posx + D, positions[turn].posy + T - X, D, X);
          if (turnIndex == turn) {
            rotation.disabled = false;
            placet.disabled = false;
            ending.disabled = true;
          }
          rot = 0;
          avdoor = 3;
          tempdir = 3;
          rottile = new Tile(positions[turn].posx, positions[turn].posy, true, true, true, false);
          positions[turn].north = true;
          positions[turn].east = true;
          positions[turn].south = true;
          positions[turn].west = false;
        } else if (responseObject.newtile.availableDoors.length == 2) {
          if ((responseObject.newtile.availableDoors.indexOf("NORTH") != -1 &&
            responseObject.newtile.availableDoors.indexOf("SOUTH") != -1) ||
            (responseObject.newtile.availableDoors.indexOf("EAST") != -1 &&
            responseObject.newtile.availableDoors.indexOf("WEST") != -1)) {
            ctx.strokeRect(positions[turn].posx - X, positions[turn].posy + D, X, D);
            ctx.strokeRect(positions[turn].posx - T, positions[turn].posy + D, X, D);
            game_move({name: "move", rotations: "1"});
            positions[turn].north = false;
            positions[turn].east = true;
            positions[turn].south = false;
            positions[turn].west = true;    
          } else {
            ctx.strokeRect(positions[turn].posx + D, positions[turn].posy, D, X);
            ctx.strokeRect(positions[turn].posx + T - X, positions[turn].posy + D, X, D);
            if (turnIndex == turn) {
              rotation.disabled = false;
              placet.disabled = false;
              ending.disabled = true;
            }
            rot = 0;
            avdoor = 2;
            tempdir = 3;
            rottile = new Tile(positions[turn].posx, positions[turn].posy, true, true, false, false);
            positions[turn].north = true;
            positions[turn].east = true;
            positions[turn].south = false;
            positions[turn].west = false;
          }
        } else if (responseObject.newtile.availableDoors.length == 1) {
          ctx.strokeRect(positions[turn].posx - X, positions[turn].posy + D, X, D);
          game_move({name: "move", rotations: "1"});
          positions[turn].north = false;
          positions[turn].east = true;
          positions[turn].south = false;
          positions[turn].west = false;
          const ntile = new Tile(positions[turn].posx, positions[turn].posy, false, true, false, false);
          flo.push(ntile);
        }
        ctx.font = "17px Times New Roman";
        ctx.fillText(responseObject.newtile.name, positions[turn].posx + textOff.posx,
          positions[turn].posy + textOff.posy);
        ctx.font = "25px Times New Roman";
        if (responseObject.newtile.eventCount > 0)
          ctx.fillText("E", positions[turn].posx + symbOff.posx, positions[turn].posy + symbOff.posy);
        else if (responseObject.newtile.itemCount > 0)
          ctx.fillText("I", positions[turn].posx + symbOff.posx + 6, positions[turn].posy + symbOff.posy);
        else if (responseObject.newtile.omenCount > 0)
          ctx.fillText("O", positions[turn].posx + symbOff.posx - 2, positions[turn].posy + symbOff.posy);
      } else {
          positions[turn].posx = positions[turn].posx - T;
          const temp = tileExists(positions[turn].posx, positions[turn].posy, positions[turn].floor);
          positions[turn].north = flo[temp].north;
          positions[turn].east = flo[temp].east;
          positions[turn].south = flo[temp].south;
          positions[turn].west = flo[temp].west;
      }
      break;
    case "UP":
      ctxs.fillStyle = colors[turn];
      ctxms.fillStyle = colors[turn];
      ctx.clearRect(900 + offsets[turn].posx - 1, 600 + offsets[turn].posy - 1, P + 2, P + 2);
      ctxs.fillRect(600 + offsets[turn].posx, 600 + offsets[turn].posy, P, P);
      ctxm.clearRect((900 + offsets[turn].posx) / S - 1,
        (600 + offsets[turn].posy) / S - 1, P / S + 2, P / S + 2);
      ctxms.fillRect((600 + offsets[turn].posx) / S,
        (600 + offsets[turn].posy) / S, P / S, P / S);
      ctx.fillStyle = "black";
      ctxm.fillStyle = "black";
      positions[turn].posx = 600;
      positions[turn].floor = 2;
      positions[turn].north = true;
      positions[turn].east = true;
      positions[turn].south = true;
      positions[turn].west = true; 
      break;
    case "DOWN":
      ctxf.fillStyle = colors[turn];
      ctxmf.fillStyle = colors[turn];
      ctx.clearRect(600 + offsets[turn].posx - 1, 600 + offsets[turn].posy - 1, P + 2, P + 2);
      ctxf.fillRect(900 + offsets[turn].posx, 600 + offsets[turn].posy, P, P);
      ctxm.clearRect((600 + offsets[turn].posx) / S - 1,
        (600 + offsets[turn].posy) / S - 1, P / S + 2, P / S + 2);
      ctxmf.fillRect((900 + offsets[turn].posx) / S,
        (600 + offsets[turn].posy) / S, P / S, P / S);
      ctx.fillStyle = "black";
      ctxm.fillStyle = "black";
      positions[turn].posx = 900;
      positions[turn].floor = 1;
      positions[turn].north = false;
      positions[turn].east = false;
      positions[turn].south = false;
      positions[turn].west = true;
      break;
  }
  console.log("changing:" + moves);
  moves--;
  movesp.innerHTML = moves;
}


function update_turn(currentTurn) {
	
	if(currentTurn != current_turn) {
		console.log(currentTurn + " current turn given vs local " + current_turn);
	
		if (current_char === total_players - 1)
			current_char = 0;
	      else
	    	  current_char++;
	
		if (current_turn === total_players - 1)
			current_turn = 0;
	      else
	    	  current_turn++;
		
		console.log(current_char);
		console.log(current_turn);

		$('#item').hide();
		$('#omen').hide();
		$('#event').hide();
		
		$("#item_info").html("");
		$("#item_name").html("");
		$("#item_description").html("");
		$("#item_logic").html("");
		$("#omen_roll").html("");
		$("#omen_info").html("");
		$("#omen_name").html("");
		$("#omen_description").html("");
		$("#omen_logic").html("");
		$("#action_result").html("");
		$("#action_rolls1").html("");
		$("#event_info").html("");
		$("#event_name").html("");
		$("#event_description").html("");
		$("#event_logic").html("");
		
		if(current_char === 0) {
		     
			current_char_name = $data_1.name;
	        moves = $data_1.speedScale[$data_1.speed];
	        movesp.innerHTML = $data_1.speedScale[$data_1.speed];
	        document.getElementById("player_1").style.borderColor = "yellow";
	        document.getElementById("player_2").style.borderColor = "black";
	        document.getElementById("player_3").style.borderColor = "black";
	        document.getElementById("player_4").style.borderColor = "black";
	        document.getElementById("player_5").style.borderColor = "black";
	        document.getElementById("player_6").style.borderColor = "black";
	    }
	   
	    if(current_char === 1) {

			current_char_name = $data_2.name;
	        moves = $data_2.speedScale[$data_2.speed];
	        movesp.innerHTML = $data_2.speedScale[$data_2.speed];
	        document.getElementById("player_1").style.borderColor = "black";
	        document.getElementById("player_2").style.borderColor = "yellow";
	        document.getElementById("player_3").style.borderColor = "black";
	        document.getElementById("player_4").style.borderColor = "black";
	        document.getElementById("player_5").style.borderColor = "black";
	        document.getElementById("player_6").style.borderColor = "black";
	    }
	   
	    if(current_char === 2) {

			current_char_name = $data_3.name;
	        moves = $data_3.speedScale[$data_3.speed];
	        movesp.innerHTML = $data_3.speedScale[$data_3.speed];
	        document.getElementById("player_1").style.borderColor = "black";
	        document.getElementById("player_2").style.borderColor = "black";
	        document.getElementById("player_3").style.borderColor = "yellow";
	        document.getElementById("player_4").style.borderColor = "black";
	        document.getElementById("player_5").style.borderColor = "black";
	        document.getElementById("player_6").style.borderColor = "black";
	    }
	   
	    if(current_char === 3) {

			current_char_name = $data_4.name;
	        moves = $data_4.speedScale[$data_4.speed];
	        movesp.innerHTML = $data_4.speedScale[$data_4.speed];
	        document.getElementById("player_1").style.borderColor = "black";
	        document.getElementById("player_2").style.borderColor = "black";
	        document.getElementById("player_3").style.borderColor = "black";
	        document.getElementById("player_4").style.borderColor = "yellow";
	        document.getElementById("player_5").style.borderColor = "black";
	        document.getElementById("player_6").style.borderColor = "black";
	    }
	   
	    if(current_char === 4) {

			current_char_name = $data_5.name;
	        moves = $data_5.speedScale[$data_5.speed];
	        movesp.innerHTML = $data_5.speedScale[$data_5.speed];
	        document.getElementById("player_1").style.borderColor = "black";
	        document.getElementById("player_2").style.borderColor = "black";
	        document.getElementById("player_3").style.borderColor = "black";
	        document.getElementById("player_4").style.borderColor = "black";
	        document.getElementById("player_5").style.borderColor = "yellow";
	        document.getElementById("player_6").style.borderColor = "black";
	    }
	   
	    if(current_char === 5) {

			current_char_name = $data_6.name;
	        moves = $data_6.speedScale[$data_6.speed];
	        movesp.innerHTML = $data_6.speedScale[$data_6.speed];
	        document.getElementById("player_1").style.borderColor = "black";
	        document.getElementById("player_2").style.borderColor = "black";
	        document.getElementById("player_3").style.borderColor = "black";
	        document.getElementById("player_4").style.borderColor = "black";
	        document.getElementById("player_5").style.borderColor = "black";
	        document.getElementById("player_6").style.borderColor = "yellow";
	    }
    console.log(turn);
    if (turn + 1 == numPlayers) 
      turn = 0;
    else
      turn++;
    console.log("xt:" + turn);
    console.log("xti: " + turnIndex);
    if (turn == turnIndex) {
      ending.disabled = false;
      if (positions[turn].floor == 0) {
        first.style.display = 'none';
        second.style.display = 'none';
        basement.style.display = 'block';
        const xpos = offx - (positions[turn].posx - 600) + edgex[0];
        const ypos = offy - (positions[turn].posy - 600) + edgey[0];
        basement.style.top = ypos + 'px';
        basement.style.left = xpos + 'px';
      } else if (positions[turn].floor == 1) {
        first.style.display = 'block';
        second.style.display = 'none';
        basement.style.display = 'none';
        const xpos = offx - (positions[turn].posx - 600) + edgex[1];
        const ypos = offy - (positions[turn].posy - 600) + edgey[1];
        first.style.top = ypos + 'px';
        first.style.left = xpos + 'px';
      } else if (positions[turn].floor == 2) {
        first.style.display = 'none';
        second.style.display = 'block';
        basement.style.display = 'none';
        const xpos = offx - (positions[turn].posx - 600) + edgex[2];
        const ypos = offy - (positions[turn].posy - 600) + edgey[2];
        second.style.top = ypos + 'px';
        second.style.left = xpos + 'px';
      }
    }

	}
}

function turn_end() {
	
	var message = {
			type: MESSAGE_TYPE.GAMEMOVE,
			payload: {
				id : userId,
				query : {
					name : "end"
				}
			}
      }

	const json = JSON.stringify(message);
	conn.send(json);
}