package org.kie.lienzo.client;

import com.ait.lienzo.client.core.event.*;
import com.ait.lienzo.client.core.shape.Rectangle;
import com.ait.lienzo.shared.core.types.ColorName;
import elemental2.dom.DomGlobal;
import org.kie.lienzo.client.util.Console;

public class MobileExample extends BaseExample implements Example {

    public MobileExample(final String title) {
        super(title);
    }

    @Override
    public void run() {

        // panel.getElement().style.setProperty("touch-action", "none");

        Rectangle r = new Rectangle(200, 200);
        r.setX(150)
            .setY(100)
            .setFillColor(ColorName.BLACK)
            .setStrokeColor(ColorName.BLACK)
            .setStrokeWidth(1.5);

        boolean draggable = false;
        boolean touchable = true;

        r.setDraggable(draggable);

        if (draggable) {
            r.addNodeDragStartHandler(event -> log(("DRAG START")));
            r.addNodeDragMoveHandler(event -> log(("DRAG MOVE")));
            r.addNodeDragEndHandler(event -> log(("DRAG END")));
        }

        if (touchable) {

            r.addNodeTouchStartHandler(event -> log("TOUCH START"));
            r.addNodeTouchMoveHandler(event -> log("TOUCH MOVE"));
            r.addNodeTouchEndHandler(event -> log("TOUCH END"));
            r.addNodeTouchCancelHandler(event -> log("TOUCH CANCEL"));

            r.addNodeGestureStartHandler(event -> log("GESTURE START"));
            r.addNodeGestureChangeHandler(event -> log("GESTURE CHANGE"));
            r.addNodeGestureEndHandler(event -> log("GESTURE END"));

        }

        r.addNodeMouseClickHandler(event -> log("MOUSE CLICK"));

        layer.add(r);
        layer.draw();
    }

    private void log(String s) {
        console.log(s);
        DomGlobal.console.error(s);
    }
}
