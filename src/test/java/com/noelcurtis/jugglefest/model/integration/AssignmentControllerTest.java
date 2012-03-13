package com.noelcurtis.jugglefest.model.integration;

import com.noelcurtis.jugglefest.model.Circuit;
import com.noelcurtis.jugglefest.model.Juggler;
import org.junit.Test;
import com.noelcurtis.jugglefest.controller.AssignmentController;
import org.junit.Assert;

import java.util.ArrayList;

/**
 * User: noelcurtis
 * Date: 3/13/12
 * Time: 12:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class AssignmentControllerTest {

    @Test
    public void testGenerateAssignments(){
        try{
            java.io.File f = new java.io.File(".");
            System.out.println(f.getCanonicalPath());
            AssignmentController.getInstance().generateAssignments("data/input_file1.txt");
            ArrayList<Juggler> jugglers = (ArrayList<Juggler>)AssignmentController.getInstance().getJugglers();
            for(Juggler juggler : jugglers){
                System.out.println(juggler.toString());
            }

            System.out.println("");
            System.out.println("");
            System.out.println("Overall Circuit Scores:");
            System.out.println("");
            ArrayList<Circuit> circuits = (ArrayList<Circuit>)AssignmentController.getInstance().getCircuits();
            for(Circuit circuit : circuits){
                System.out.println(circuit.toString());
            }

        }catch (Exception ex){
            Assert.fail(ex.toString());
        }
    }
}
