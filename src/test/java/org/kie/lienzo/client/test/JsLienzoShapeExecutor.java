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

package org.kie.lienzo.client.test;

import static org.kie.lienzo.client.test.JsLienzoExecutor.GET_SHAPE;
import static org.kie.lienzo.client.test.JsLienzoExecutor.JS_LIENZO;
import static org.kie.lienzo.client.test.JsLienzoExecutor.RETURN;

public class JsLienzoShapeExecutor {

    private final JsLienzoExecutor executor;
    private final String id;

    public JsLienzoShapeExecutor(JsLienzoExecutor executor, String id) {
        this.executor = executor;
        this.id = id;
    }

    // ************* PROPERTIES ****************************

    public long getX() {
        return getX(id);
    }

    public long getY() {
        return getY(id);
    }

    public long getWidth() {
        return getWidth(id);
    }

    public long getHeight() {
        return getHeight(id);
    }

    public String getFillColor() {
        return getFillColor(id);
    }

    public String getStrokeColor() {
        return getStrokeColor(id);
    }

    // ************* EVENTS ****************************

    public void doubleClick() {
        doubleClick(id);
    }

    public void click() {
        click(id);
    }

    public void over() {
        over(id);
    }

    public void out() {
        out(id);
    }

    public void move(double tx, double ty) {
        move(id, tx, ty);
    }

    // ************* GENERIC ****************************

    private long getX(String id) {
        return getProperty(id, "x");
    }

    private long getY(String id) {
       return getProperty(id, "y");
    }

    private long getWidth(String id) {
        return getProperty(id, "width");
    }

    private long getHeight(String id) {
        return getProperty(id, "height");
    }

    private String getFillColor(String id) {
        return getProperty(id, "fillColor");
    }

    private String getStrokeColor(String id) {
        return getProperty(id, "strokeColor");
    }

    @SuppressWarnings("all")
    private <T> T getProperty(String id, String property) {
        T value = (T) executor.executeScript(RETURN + GET_SHAPE + "." + property, id);
        return value;
    }

    private void click(String id) {
        executor.executeScript(JS_LIENZO + ".click(" + GET_SHAPE + ")", id);
    }

    private void doubleClick(String id) {
        executor.executeScript(JS_LIENZO + ".doubleClick(" + GET_SHAPE + ")", id);
    }

    private void over(String id) {
        executor.executeScript(JS_LIENZO + ".over(" + GET_SHAPE + ")", id);
    }

    private void out(String id) {
        executor.executeScript(JS_LIENZO + ".out(" + GET_SHAPE + ")", id);
    }

    private void move(String id, double tx, double ty) {
        executor.executeScript(JS_LIENZO + ".move(" + GET_SHAPE + ", arguments[1], arguments[2])",
                               id,
                               tx, ty);
    }
}
