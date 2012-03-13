package com.noelcurtis.jugglefest.model.integration;

import com.noelcurtis.jugglefest.model.Circuit;
import com.noelcurtis.jugglefest.model.Juggler;
import org.junit.Test;
import com.noelcurtis.jugglefest.controller.InputParser;

import java.util.Collection;
import java.util.Dictionary;
import org.junit.Assert;

/**
 * User: noelcurtis
 * Date: 3/13/12
 * Time: 12:54 AM
 * To change this template use File | Settings | File Templates.
 */
public class InputParserTest {

    @Test
    public void testInputParser(){
        try{
            Dictionary<Class, Collection> parsedInput = InputParser.parseInputFile("data/input_file1.txt");
            assert parsedInput != null;
            assert parsedInput.get(Juggler.class) != null;
            assert parsedInput.get(Circuit.class) != null;
            assert parsedInput.get(Juggler.class).size() == 12;
            assert parsedInput.get(Circuit.class).size() == 3;
        }catch (Exception ex){
            Assert.fail(ex.toString());
        }

    }
}
