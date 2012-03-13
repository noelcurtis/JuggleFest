package com.noelcurtis.jugglefest.model.unit;

import com.noelcurtis.jugglefest.model.Juggler;
import org.junit.Test;

import java.util.ArrayList;

/**
 * User: noelcurtis
 * Date: 3/12/12
 * Time: 7:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class JugglerTest {

    @Test
    public void testCreatingJuggler() throws Exception {
        ArrayList<String> preferredCircuitList = new ArrayList<String>();
        preferredCircuitList.add("C1");
        preferredCircuitList.add("C2");
        preferredCircuitList.add("C3");
        Juggler juggler = new Juggler("new_juggler", 1,2,3, preferredCircuitList);
        assert juggler.getHandEyeCoordination() == 1;
        assert  juggler.getEndurance() == 2;
        assert juggler.getPizzazz() == 3;
        System.out.println(juggler.toString());
    }

    @Test
    public void testCreatingJugglerFromString() throws Exception{
        Juggler juggler = new Juggler("J J0 H:3 E:9 P:2 C2,C0,C1");
        assert juggler.getHandEyeCoordination() == 3;
        assert  juggler.getEndurance() == 9;
        assert juggler.getPizzazz() == 2;
        assert juggler.getPreferredCircuitNames().size() == 3;
        System.out.println(juggler.toString());
    }
}
