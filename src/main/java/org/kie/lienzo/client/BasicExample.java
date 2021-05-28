package org.kie.lienzo.client;

import com.ait.lienzo.client.core.event.NodeMouseClickEvent;
import com.ait.lienzo.client.core.event.NodeMouseDoubleClickEvent;
import com.ait.lienzo.client.core.event.NodeMouseDownEvent;
import com.ait.lienzo.client.core.event.NodeMouseEnterEvent;
import com.ait.lienzo.client.core.event.NodeMouseExitEvent;
import com.ait.lienzo.client.core.event.NodeMouseMoveEvent;
import com.ait.lienzo.client.core.event.NodeMouseOutEvent;
import com.ait.lienzo.client.core.event.NodeMouseOverEvent;
import com.ait.lienzo.client.core.event.NodeMouseUpEvent;
import com.ait.lienzo.client.core.shape.Rectangle;
import com.ait.lienzo.shared.core.types.ColorName;
import elemental2.dom.DomGlobal;

public class BasicExample extends BaseExample implements Example {

    public static final String RECTANGLE = "rectangle";
    public static final ColorName COLOR = ColorName.BLACK;
    public static final ColorName OVER_COLOR = ColorName.RED;

    private Rectangle rectangle;

    public BasicExample(final String title) {
        super(title);
    }

    @Override
    public void run() {
        rectangle = new Rectangle(100, 100);

        rectangle
                .setID(RECTANGLE)
                .setX(100)
                .setY(100)
                .setFillColor(COLOR)
                .setStrokeColor(COLOR)
                .setStrokeWidth(1)
                .setAlpha(1)
                .setDraggable(true);

        // rectangle.addNodeMouseDownHandler(this::onRectangleMouseDown);
        // rectangle.addNodeMouseMoveHandler(this::onRectangleMouseMove);
        // rectangle.addNodeMouseUpHandler(this::onRectangleMouseUp);
        rectangle.addNodeMouseClickHandler(this::onRectangleMouseClick);
        rectangle.addNodeMouseDoubleClickHandler(this::onRectangleMouseDoubleClick);
        rectangle.addNodeMouseOverHandler(this::onRectangleMouseOver);
        rectangle.addNodeMouseOutHandler(this::onRectangleMouseOut);
        rectangle.addNodeMouseEnterHandler(this::onNodeMouseEnter);
        rectangle.addNodeMouseExitHandler(this::onNodeMouseExit);

        layer.add(rectangle);
        layer.draw();
    }

    private void onRectangleMouseDown(NodeMouseDownEvent e) {
        log("DOWN!");
    }

    private void onRectangleMouseMove(NodeMouseMoveEvent e) {
        log("MOVE!");
    }

    private void onRectangleMouseUp(NodeMouseUpEvent e) {
        log("UP!");
    }

    private void onRectangleMouseClick(NodeMouseClickEvent e) {
        log("CLICK!");
        rectangle.setWidth(rectangle.getWidth() * 2);
        rectangle.setHeight(rectangle.getHeight() * 2);
        layer.draw();
    }

    private void onRectangleMouseDoubleClick(NodeMouseDoubleClickEvent e) {
        log("DOUBLE CLICK!");
        rectangle.setWidth(rectangle.getWidth() / 2);
        rectangle.setHeight(rectangle.getHeight() / 2);
        layer.draw();
    }

    private void onRectangleMouseOver(NodeMouseOverEvent e) {
        log("OVER!");
        rectangle.setFillColor(OVER_COLOR);
        layer.draw();
    }

    private void onRectangleMouseOut(NodeMouseOutEvent e) {
        log("OUT!");
        rectangle.setFillColor(COLOR);
        layer.draw();
    }

    private void onNodeMouseEnter(NodeMouseEnterEvent event) {
        log("ENTER!");
        rectangle.setFillColor(OVER_COLOR);
        layer.draw();
    }

    private void onNodeMouseExit(NodeMouseExitEvent event) {
        log("EXIT!");
        rectangle.setFillColor(COLOR);
        layer.draw();
    }

    private void log(String s) {
        console.log(s);
        DomGlobal.console.error(s);
    }
}
