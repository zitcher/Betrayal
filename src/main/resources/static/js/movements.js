let m1 = false;
  $("#mfirst").mousedown(function(){m1=false;}).mousemove(function(){m1=true;}).mouseup(function(event){
    if(!m1){
        first.style.display = 'block';
        second.style.display = 'none';
        basement.style.display = 'none';
     }
  });
  let m2 = false;
  $("#msecond").mousedown(function(){m2=false;}).mousemove(function(){m2=true;}).mouseup(function(event){
    if(!m2){
        first.style.display = 'none';
        second.style.display = 'block';
        basement.style.display = 'none';
     }
  });
  let m3 = false;
  $("#mbasement").mousedown(function(){m3=false;}).mousemove(function(){m3=true;}).mouseup(function(event){
    if(!m3){
        first.style.display = 'none';
        second.style.display = 'none';
        basement.style.display = 'block';
     }
  });
//  let b1 = false;
//  $("#first").mousedown(function(){b1=false;}).mousemove(function(){b1=true;}).mouseup(function(event){
//    if(!b1){
//        const x = event.pageX - first.offsetLeft - 336;
//        const y = event.pageY - first.offsetTop - 204;
//        const xpos = x - (x % T) + edgex[1];
//        const ypos = y - (y % T) + edgex[1];
//        descr.innerHTML = xpos + " " + ypos;
//     }
//  });
//  let b2 = false;
//  $("#second").mousedown(function(){b2=false;}).mousemove(function(){b2=true;}).mouseup(function(event){
//    if(!b2){
//        const x = event.pageX - second.offsetLeft - 336;
//        const y = event.pageY - second.offsetTop - 204;
//        const xpos = x - (x % T) + edgex[2];
//        const ypos = y - (y % T) + edgey[2];
//        descr.innerHTML = xpos + " " + ypos;
//     }
//  });
//  let b3 = false;
//  $("#basement").mousedown(function(){b3=false;}).mousemove(function(){b3=true;}).mouseup(function(event){
//    if(!b3){
//        const x = event.pageX - basement.offsetLeft - 336;
//        const y = event.pageY - basement.offsetTop - 204;
//        const xpos = x - (x % T) + edgex[0];
//        const ypos = y - (y % T) + edgey[0];
//        descr.innerHTML = xpos + " " + ypos;
//     }
//  });
    let moves = -1;
    const first = document.getElementById("first");
    const second = document.getElementById("second");
    const basement = document.getElementById("basement");
    const descr = document.getElementById("scription");
    const movesp = document.getElementById("moves");
    const mfirst = document.getElementById("mfirst");
    const msecond = document.getElementById("msecond");
    const mbasement = document.getElementById("mbasement");
    const rotation = document.getElementById("rot");
    const placet = document.getElementById("plat");
    const ending = document.getElementById("end");
    descr.innerHTML = "Omen Count: 0";

    first.width = 1350;
    first.height = 1350;
    second.width = 1350;
    second.height = 1350;
    basement.width = 1350;
    basement.height = 1350;
    mfirst.width = 180;
    mfirst.height = 180;
    msecond.width = 180;
    msecond.height = 180;
    mbasement.width = 180;
    mbasement.height = 180;
    const ctxf = first.getContext("2d");
    const ctxs = second.getContext("2d");
    const ctxb = basement.getContext("2d");
    const ctxmf = mfirst.getContext("2d");
    const ctxms = msecond.getContext("2d");
    const ctxmb = mbasement.getContext("2d");
    let edgex = [0, 0, 0];
    let edgey = [0, 0, 0];
  $(document).ready(() => {
    $(document).keyup(event => {
      if (moves > 0 && rotation.disabled && placet.disabled && allowkeys && turnIndex == turn) {
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
        let temp;
        if (positions[turn].posx == 900 && positions[turn].posy == 600 &&
          positions[turn].floor == 1 && event.which == 69) {
          const postParameters = {name: "move", direction: "UP"};
          game_move(postParameters);
          first.style.display = 'none';
          second.style.display = 'block';
          basement.style.display = 'none';
          second.style.top = '-425px';
          second.style.left = '-375px';
        } else if (positions[turn].posx == 600 && positions[turn].posy == 600 &&
          positions[turn].floor == 2 && event.which == 69) {
          const postParameters = {name: "move", direction: "DOWN"};
          game_move(postParameters);
          first.style.display = 'block';
          second.style.display = 'none';
          basement.style.display = 'none';
          first.style.top = '-425px';
          first.style.left = '-375px';
        } else if (event.which == 87) {
          if (positions[turn].posy - T < edgey[positions[turn].floor]) {
            lev.height += 150;
            edgey[positions[turn].floor] -= 150;
            ctx.translate(Math.abs(edgex[positions[turn].floor]), Math.abs(edgey[positions[turn].floor]));
            levm.height += 20;
            ctxm.translate(Math.abs(edgex[positions[turn].floor]) / S,
              Math.abs(edgey[positions[turn].floor]) / S);
            paintBoard(positions[turn].floor, positions.length);
          }
          if (((temp = tileExists(positions[turn].posx, positions[turn].posy - T,
           positions[turn].floor)) == -1 || flo[temp].south) && positions[turn].north) {
            const postParameters = {name: "move", direction: "NORTH"};
            game_move(postParameters);
            const xpos = offx - (positions[turn].posx - 600) + edgex[positions[turn].floor];
            const ypos = offy - (positions[turn].posy - T - 600) + edgey[positions[turn].floor];
            lev.style.top = ypos + 'px';
            lev.style.left = xpos + 'px';       
          }
        } else if (event.which == 68) {
          if (positions[turn].posx + T >= lev.width + edgex[positions[turn].floor]) {
            lev.width += 150;
            ctx.translate(Math.abs(edgex[positions[turn].floor]), Math.abs(edgey[positions[turn].floor]));
            levm.width += 20;
            ctxm.translate(Math.abs(edgex[positions[turn].floor]) / S,
             Math.abs(edgey[positions[turn].floor]) / S);
            paintBoard(positions[turn].floor, positions.length);
          }
          if (((temp = tileExists(positions[turn].posx + T, positions[turn].posy,
           positions[turn].floor)) == -1 || flo[temp].west) && positions[turn].east) {
            const postParameters = {name: "move", direction: "EAST"};
            game_move(postParameters);
            const xpos = offx - (positions[turn].posx + T - 600) + edgex[positions[turn].floor];
            const ypos = offy - (positions[turn].posy - 600) + edgey[positions[turn].floor];
            lev.style.top = ypos + 'px';
            lev.style.left = xpos + 'px'; 
          }
        } else if (event.which == 83) {
          if (positions[turn].posy + T >= lev.height + edgey[positions[turn].floor]) {
            lev.height += 150;
            ctx.translate(Math.abs(edgex[positions[turn].floor]), Math.abs(edgey[positions[turn].floor]));
            levm.height += 20;
            ctxm.translate(Math.abs(edgex[positions[turn].floor]) / S,
              Math.abs(edgey[positions[turn].floor]) / S);
            paintBoard(positions[turn].floor, positions.length);
          }
          if (((temp = tileExists(positions[turn].posx, positions[turn].posy + T,
           positions[turn].floor)) == -1 || flo[temp].north) && positions[turn].south) {
            const postParameters = {name: "move", direction: "SOUTH"};
            game_move(postParameters);
            const xpos = offx - (positions[turn].posx - 600) + edgex[positions[turn].floor];
            const ypos = offy - (positions[turn].posy + T - 600) + edgey[positions[turn].floor];
            lev.style.top = ypos + 'px';
            lev.style.left = xpos + 'px';
          }
        } else if (event.which == 65) {
          if (positions[turn].posx - T < edgex[positions[turn].floor]) {
            lev.width += 150;
            edgex[positions[turn].floor] -= 150;
            ctx.translate(Math.abs(edgex[positions[turn].floor]), Math.abs(edgey[positions[turn].floor]));
            levm.width += 20;
            ctxm.translate(Math.abs(edgex[positions[turn].floor]) / S,
             Math.abs(edgey[positions[turn].floor]) / S);
            paintBoard(positions[turn].floor, positions.length);
          }
          if (((temp = tileExists(positions[turn].posx - T, positions[turn].posy,
           positions[turn].floor)) == -1 || flo[temp].east) && positions[turn].west) {
            console.log("just in case");
            const postParameters = {name: "move", direction: "WEST"};
            game_move(postParameters);
            const xpos = offx - (positions[turn].posx - T - 600) + edgex[positions[turn].floor];
            const ypos = offy - (positions[turn].posy - 600) + edgey[positions[turn].floor];
            lev.style.top = ypos + 'px';
            lev.style.left = xpos + 'px';
          }
        }
      }
    });
  });