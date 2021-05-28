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

import org.openqa.selenium.JavascriptExecutor;

public class JsLienzoExecutor {

    final JavascriptExecutor executor;
    static final String RETURN = "return ";
    static final String JS_LIENZO = "window.jsLienzo";
    static final String GET_SHAPE = JS_LIENZO + ".getShape(arguments[0])";

    public JsLienzoExecutor(JavascriptExecutor executor) {
        this.executor = executor;
    }

    public JsLienzoExecutor doubleClickAt(double x, double y) {
        executor.executeScript(JS_LIENZO + ".doubleClickAt(arguments[0], arguments[1])",
                               x, y);
        return this;
    }

    public JsLienzoExecutor clickAt(double x, double y) {
        executor.executeScript(JS_LIENZO + ".clickAt(arguments[0], arguments[1])",
                               x, y);
        return this;
    }

    public JsLienzoExecutor drag(double sx, double sy, double tx, double ty) {
        executor.executeScript(JS_LIENZO + ".drag(arguments[0], arguments[1], arguments[2], arguments[3])",
                               sx, sy,
                               ty, ty);
        return this;
    }

    public JsLienzoExecutor sleep() {
        sleep(200);
        return this;
    }

    public JsLienzoShapeExecutor forShape(String id) {
        return new JsLienzoShapeExecutor(this, id);
    }

    Object executeScript(String script, Object... args) {
        return executor.executeScript(script, args);
    }

    public static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            log(e.getMessage());
        }
    }

    public static void log(String s) {
        System.err.println(s);
    }
}
