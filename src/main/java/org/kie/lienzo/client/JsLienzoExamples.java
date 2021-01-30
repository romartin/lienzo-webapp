package org.kie.lienzo.client;

import jsinterop.annotations.JsType;
import org.kie.lienzo.client.BaseLienzoExamples;

@JsType
public class JsLienzoExamples {

    BaseLienzoExamples examples;

    public void goToExample(int index) {
        examples.goToTest(index);
    }

}
