package org.kie.lienzo.client;

import com.ait.lienzo.client.core.event.NodeMouseDownEvent;
import com.ait.lienzo.client.core.event.NodeMouseDownHandler;
import com.ait.lienzo.client.core.shape.IPrimitive;
import com.ait.lienzo.client.core.shape.MultiPath;
import com.ait.lienzo.client.core.shape.Rectangle;
import com.ait.lienzo.client.core.shape.wires.WiresManager;
import com.ait.lienzo.client.core.shape.wires.WiresShape;
import com.ait.lienzo.client.core.shape.wires.event.WiresMoveEvent;
import com.ait.lienzo.client.core.shape.wires.event.WiresMoveHandler;
import com.ait.lienzo.client.core.types.Point2D;
import com.ait.lienzo.client.widget.panel.LienzoPanel;
import com.ait.lienzo.shared.core.types.ColorName;
import com.google.gwt.dom.client.Style;
import elemental2.dom.*;

public class JsLienzoExample extends BaseExample implements Example {

    private HTMLButtonElement button1;
    private HTMLButtonElement button2;
    private Rectangle r;

    public JsLienzoExample(final String title) {
        super(title);
    }

    @Override
    public void init(LienzoPanel panel, HTMLDivElement topDiv) {
        super.init(panel, topDiv);
        if (false) {
            topDiv.style.display = Style.Display.INLINE_BLOCK.getCssName();
            button1 = createButton("Draw", this::onButton1);
            topDiv.appendChild(button1);
            button2 = createButton("Simulate Events", this::onButton2);
            topDiv.appendChild(button2);
        }
        panel.getElement().style.border = "1px solid";
    }

    private void onButton1() {
        layer.draw();
    }

    private void onButton2() {
        // Primitive Shape
        // IPrimitive<?> r = jsLienzo.getShape("r");
        // IPrimitive<?> shape = jsLienzo.getShape("r");
        // jsLienzo.click(r);
        // jsLienzo.move(r, 100, 100);

        // Wires Shape
        IPrimitive<?> rectangleRed = jsLienzo.getShape("rectangleRed");
        jsLienzo.click(rectangleRed);
        jsLienzo.move(rectangleRed, 100, 100);

    }

    @Override
    public void run() {

        r = new Rectangle(100, 100);
        r.setX(100)
                .setID("r")
                .setY(100)
                .setFillColor(ColorName.BLACK)
                .setStrokeColor(ColorName.BLACK)
                .setStrokeWidth(1);

        r.setDraggable(true);

        r.addNodeMouseClickHandler(e -> log("CLICK! "));
        r.addNodeMouseDownHandler(e -> log("DOWN! "));
        r.addNodeMouseMoveHandler(e -> log("MOVE! "));
        r.addNodeMouseUpHandler(e -> log("UP! "));

        jsLienzo.add(r);

        /*WiresManager wires_manager = WiresManager.get(layer);
        WiresShape rectangleRed =
                new WiresShape(new MultiPath()
                        .rect(0, 0, 100, 100)
                        .setStrokeColor("#FF0000")
                        .setFillColor("#FF0000"))
                        .setDraggable(true);
        rectangleRed.setID("rectangleRed");
        rectangleRed.setLocation(new Point2D(350, 150));
        wires_manager.register(rectangleRed);
        wires_manager.getMagnetManager().createMagnets(rectangleRed);

        rectangleRed.getGroup().addNodeMouseClickHandler(e -> log("CLICK!"));
        rectangleRed.addWiresMoveHandler(new WiresMoveHandler() {
            @Override
            public void onShapeMoved(WiresMoveEvent event) {
                log("MOVED TO [" + event.getX() + ", " + event.getY() + "]");
            }
        });*/

        jsLienzo.draw();

        layer.addNodeMouseDownHandler(event -> log("LAYER MOUSE DOWN AT [" +event.getX() + ", " + event.getY() + "]" ));
        layer.addNodeMouseMoveHandler(event -> log("LAYER MOUSE MOVE AT [" +event.getX() + ", " + event.getY() + "]" ));
        layer.addNodeMouseUpHandler(event -> log("LAYER MOUSE UP AT [" +event.getX() + ", " + event.getY() + "]" ));

    }

    private void log(String s) {
        console.log(s);
        DomGlobal.console.error(s);
    }
}
