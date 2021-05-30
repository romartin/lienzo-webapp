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

import static org.kie.lienzo.client.test.JsLienzoExecutor.JS_LIENZO;
import static org.kie.lienzo.client.test.JsLienzoExecutor.RETURN;

public class JsLienzoWiresShapeExecutor {

    static final String GET_SHAPE = JS_LIENZO + ".getWiresShape(arguments[0])";

    private final JsLienzoExecutor executor;
    private final String id;
    private final JsLienzoShapeExecutor shapeExecutor;

    public JsLienzoWiresShapeExecutor(JsLienzoExecutor executor, String id) {
        this.executor = executor;
        this.id = id;
        this.shapeExecutor = new JsLienzoShapeExecutor(executor, id);
    }

    public String getID() {
        return id;
    }

    public double getX() {
        return shapeExecutor.getX();
    }

    public double getY() {
        return shapeExecutor.getY();
    }

    public double getComputedX() {
        return getComputedX(id);
    }

    public double getComputedY() {
        return getComputedY(id);
    }

    public JsLienzoShapeExecutor getShape() {
        return shapeExecutor;
    }

    public JsLienzoWiresShapeExecutor getParent() {
        String parentId = (String) executor.executeScript(RETURN + GET_SHAPE + ".getParentID()", id);
        if (null != parentId) {
            return new JsLienzoWiresShapeExecutor(executor, parentId);
        }
        return null;
    }

    private double getComputedX(String id) {
        return getDoubleProperty(id, "getComputedLocation().x");
    }

    private double getComputedY(String id) {
        return getDoubleProperty(id, "getComputedLocation().y");
    }

    @SuppressWarnings("all")
    private <T> T getProperty(String id, String property) {
        T value = (T) executor.executeScript(RETURN + GET_SHAPE + "." + property, id);
        return value;
    }

    @SuppressWarnings("all")
    private double getDoubleProperty(String id, String property) {
        Object value = executor.executeScript(RETURN + GET_SHAPE + "." + property, id);
        if (value instanceof Long) {
            return ((Long) value).doubleValue();
        }
        return (double) value;
    }

}
