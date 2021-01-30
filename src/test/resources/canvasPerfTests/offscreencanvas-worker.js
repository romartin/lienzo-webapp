const animation_inc = 10;
 const canvas_size = 300;
 var animation_dx = 0;
 var animation_size = 50;

 function animationWorkerTest(context) {
    if (animation_dx == 0) {
        console.log("STARTING ANIMATION");
    }
    context.save();
    context.clearRect(0, 0, canvas_size, canvas_size);
    context.fillStyle = "rgba(255,0,0,1)";
    context.beginPath();
    context.rect(animation_dx, 0, animation_size, animation_size);
    context.stroke();
    context.fill();
    context.restore();
    animation_dx = animation_dx + animation_inc;
    if ((animation_dx + 50) <= canvas_size) {
        return true;
    } else {
        return false;
    }
 }

onmessage = function(evt) {
    const canvas = evt.data.canvas;
    const context = canvas.getContext("2d");
  
    function render(time) {
        if (animationWorkerTest(context)) {
            requestAnimationFrame(render);
        } else {
            console.log("STOPPING ANIMATION");
        }      
    }
    requestAnimationFrame(render);
  };