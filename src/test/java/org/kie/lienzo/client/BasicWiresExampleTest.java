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
import org.kie.lienzo.client.test.JsLienzoWiresShapeExecutor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.kie.lienzo.client.BasicWiresExample.PARENT;
import static org.kie.lienzo.client.BasicWiresExample.RED_RECTANGLE;

@SuppressWarnings("NonJREEmulationClassesInClientCode")
public class BasicWiresExampleTest {

    private JsLienzoDriver lienzoDriver;

    @BeforeClass
    public static void setupClass() {
        JsLienzoDriver.init();
    }

    @Before
    public void openWebapp() {
        // lienzoDriver = JsLienzoDriver.build();
        lienzoDriver = JsLienzoDriver.devMode();
        lienzoDriver.openTest(2);
    }

    @After
    public void closeWebapp() {
        lienzoDriver.closeTest();
    }

    @Test
    public void testDragRedShape() {
        JsLienzoWiresShapeExecutor wiresShape = lienzoDriver.forWiresShape(RED_RECTANGLE);
        assertNull(wiresShape.getParent());
        JsLienzoShapeExecutor shape = wiresShape.getShape();
        assertEquals(100d, shape.getX(), 0d);
        assertEquals(50d, shape.getY(), 0d);
        shape.drag( 150, 150);
        assertEquals(150d, shape.getX(), 0d);
        assertEquals(150d, shape.getY(), 0d);
        assertNull(wiresShape.getParent());
    }

    @Test
    public void testDragRedShapeIntoParent() {
        JsLienzoWiresShapeExecutor redWiresShape = lienzoDriver.forWiresShape(RED_RECTANGLE);
        assertNull(redWiresShape.getParent());
        JsLienzoShapeExecutor redShape = redWiresShape.getShape();
        redShape.drag( 400, 400);
        JsLienzoWiresShapeExecutor parent = redWiresShape.getParent();
        assertNotNull(parent);
        assertEquals(PARENT, parent.getID());
        assertEquals(350d, redWiresShape.getX(), 0d);
        assertEquals(100d, redWiresShape.getY(), 0d);
        assertEquals(400d, redWiresShape.getComputedX(), 0d);
        assertEquals(400d, redWiresShape.getComputedY(), 0d);
    }

    @Test
    public void testDragRedShapeIntoParentAndThenMoveParent() {
        testDragRedShapeIntoParent();
        JsLienzoWiresShapeExecutor wiresParent = lienzoDriver.forWiresShape(PARENT);
        JsLienzoShapeExecutor parent = wiresParent.getShape();
        parent.drag( 50, 200);
        assertEquals(50d, wiresParent.getX(), 0d);
        assertEquals(200d, wiresParent.getY(), 0d);
        assertEquals(50d, wiresParent.getComputedX(), 0d);
        assertEquals(200d, wiresParent.getComputedY(), 0d);
        JsLienzoWiresShapeExecutor redShape = lienzoDriver.forWiresShape(RED_RECTANGLE);
        assertEquals(350d, redShape.getX(), 0d);
        assertEquals(100d, redShape.getY(), 0d);
        assertEquals(400d, redShape.getComputedX(), 0d);
        assertEquals(300d, redShape.getComputedY(), 0d);
    }


}
