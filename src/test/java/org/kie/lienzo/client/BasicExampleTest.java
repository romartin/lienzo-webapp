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

package org.kie.lienzo.client;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.lienzo.client.test.JsLienzoDriver;
import org.kie.lienzo.client.test.JsLienzoShapeExecutor;

import static org.junit.Assert.assertEquals;
import static org.kie.lienzo.client.BasicExample.RECTANGLE;

@SuppressWarnings("NonJREEmulationClassesInClientCode")
public class BasicExampleTest {

    private JsLienzoDriver lienzoDriver;
    private JsLienzoShapeExecutor rectangle;

    @BeforeClass
    public static void setupClass() {
        JsLienzoDriver.init();
    }

    @Before
    public void openWebapp() {
        lienzoDriver = JsLienzoDriver.build();
        // lienzoDriver = JsLienzoDriver.devMode();
        rectangle = lienzoDriver.forShape(RECTANGLE);
        lienzoDriver.openTest(1);
    }

    @After
    public void closeWebapp() {
        lienzoDriver.closeTest();
    }

    @Test
    public void testMoveShape() {
        assertEquals(100, rectangle.getX());
        assertEquals(100, rectangle.getY());
        rectangle.move( 300, 298);
        assertEquals(300, rectangle.getX());
        assertEquals(298, rectangle.getY());
    }

    @Test
    public void testClickShape() {
        assertEquals(100, rectangle.getWidth());
        assertEquals(100, rectangle.getHeight());
        rectangle.click();
        assertEquals(200, rectangle.getWidth());
        assertEquals(200, rectangle.getHeight());
    }

    @Test
    public void testDoubleClickShape() {
        assertEquals(100, rectangle.getWidth());
        assertEquals(100, rectangle.getHeight());
        rectangle.doubleClick();
        assertEquals(50, rectangle.getWidth());
        assertEquals(50, rectangle.getHeight());
    }

    @Test
    public void testEnterExitShape() {
        assertEquals(BasicExample.COLOR.getValue(), rectangle.getFillColor());
        rectangle.over();
        assertEquals(BasicExample.OVER_COLOR.getValue(), rectangle.getFillColor());
        rectangle.out();
        assertEquals(BasicExample.COLOR.getValue(), rectangle.getFillColor());
    }

}
