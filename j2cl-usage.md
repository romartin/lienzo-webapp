    // Webapp URLs    
    file:////home/roger/development/romartin/lienzo-webapp/target/lienzo-webapp-7.48.0-SNAPSHOT/LienzoShowcase.html
    http://localhost/lienzo-webapp-7.48.0-SNAPSHOT/LienzoShowcase.html

    // Examples webapp usage
    window.jsLienzoExamples.goToExample(0);

    // Example: Create a new rectangle
    var r1 = window.jsLienzo.RECT('r1', 0, 0, 100, 100);
    window.jsLienzo.add(r1);
    window.jsLienzo.draw();

    // JsLienzo - Move shape
    window.jsLienzo.move(window.jsLienzo.getShape('r'), 100, 100);

    // JsLienzo - Click & Move shape
    var r = window.jsLienzo.getShape('r');
    window.jsLienzo.click(r);
    window.jsLienzo.move(r, 100, 100);
