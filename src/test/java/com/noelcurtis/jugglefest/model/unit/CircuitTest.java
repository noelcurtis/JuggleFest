package com.noelcurtis.jugglefest.model.unit;

import com.noelcurtis.jugglefest.model.Circuit;
import org.junit.Test;

/**
 * User: noelcurtis
 * Date: 3/12/12
 * Time: 7:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class CircuitTest {

    @Test
    public void testCreatingCircuit() throws Exception {
        Circuit circuit = new Circuit("new_circuit", 1,2,3);
        assert circuit.getHandEyeCoordination() == 1;
        assert  circuit.getEndurance() == 2;
        assert circuit.getPizzazz() == 3;
        System.out.println(circuit.toString());
    }

    @Test
    public void testCreatingCircuitFromString() throws Exception{
        Circuit circuit = new Circuit("C C0 H:7 E:7 P:10");
        assert circuit.getHandEyeCoordination() == 7;
        assert  circuit.getEndurance() == 7;
        assert circuit.getPizzazz() == 10;
        System.out.println(circuit.toString());
    }
}
