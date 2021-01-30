/*
 * Copyright 2021 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

 const animation_inc = 10;
 const canvas_size = 300;
 var animation_dx = 0;
 var animation_size = 50;

function runAnimationOffscreenTest() {
    var offscreen = getCanvas().transferControlToOffscreen();
    animation_dx = 0;
    animationTest(offscreen);
    //var worker = new Worker("./offscreencanvas-worker.js");
    //worker.postMessage({canvas: offscreen}, [offscreen]);
}

function runAnimationTest() {
    animation_dx = 0;
    var canvas = getCanvas();
    animationTest(canvas);
}

function animationTest(canvas) {
    if (animation_dx == 0) {
        console.log("STARTING ANIMATION");
    }
    var context = canvas.getContext('2d');
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
        requestAnimationFrame(function() {
            animationTest(canvas);
        });
    } else {
        console.log("STOPPING ANIMATION");
    }
 }

function drawImage() {
    var canvas = getCanvas();
    var context = getContext();
    var img = new Image();
    img.onload = function() {
        context.drawImage(img, 0, 0, canvas.clientWidth, canvas.clientHeight    );
    };
    img.src = "img/standard_model_cup.png";
}

 function drawSomething() {
    var context = getContext();
    context.moveTo(0, 0);
    context.lineTo(50, 50);
    context.stroke();    
 }

 function clearCanvas() {
     var canvas = getCanvas();
     getContext().clearRect(0, 0, canvas.clientWidth, canvas.clientHeight);
 }

 function getContext() {
    return getCanvas().getContext('2d');
}

 function getCanvas() {
    return document.getElementById('canvas');
}