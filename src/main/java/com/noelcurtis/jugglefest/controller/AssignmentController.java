package com.noelcurtis.jugglefest.controller;

import com.noelcurtis.jugglefest.model.Circuit;
import com.noelcurtis.jugglefest.model.Juggler;

import java.util.*;

/**
 * User: noelcurtis
 * Date: 3/13/12
 * Time: 12:01 AM
 * To change this template use File | Settings | File Templates.
 */
public class AssignmentController {
    private static AssignmentController sharedInstance = new AssignmentController();

    private Collection<Juggler> jugglers;
    private Collection<Circuit> circuits;
    //private Dictionary<String, LinkedList<Juggler>> sortedJugglers;

    public Collection<Juggler> getJugglers() {
        return jugglers;
    }

    public void setJugglers(Collection<Juggler> jugglers) {
        this.jugglers = jugglers;
    }

    public Collection<Circuit> getCircuits() {
        return circuits;
    }

    public void setCircuits(Collection<Circuit> circuits) {
        this.circuits = circuits;
    }

    public static AssignmentController getInstance() {
        return sharedInstance;
    }

    private AssignmentController() {
    }

    /**
     * Wrapper method to generate assignments for Jugglers
     * @param fileName
     * @throws Exception
     */
    public void generateAssignments(String fileName)throws Exception{
        this.createModel(fileName);
        this.calculateCircuitScores();
    }

    /**
     * Use to setup the model
     * @param fileName
     * @throws Exception
     */
    private void createModel(String fileName)throws Exception{
        Dictionary<Class, Collection> models = InputParser.parseInputFile(fileName);
        this.jugglers = models.get(Juggler.class);
        this.circuits = models.get(Circuit.class);
    }

    /**
     * Use to calculate Circuit Scores for all Jugglers
     */
    private void calculateCircuitScores(){

        for(Juggler juggler : this.jugglers){
            Hashtable<String, Integer> circuitScores = new Hashtable<String, Integer>();
            for(Circuit circuit : this.circuits){
                circuitScores.put(circuit.getName(), this.scoreJugglerCircuit(juggler, circuit));
            }
            juggler.setCircuitScores(circuitScores);
            this.insertJugglerForCircuits(juggler);
        }
    }

    /**
     * Use to insert a Juggler into a sorted Circuit List
     * @param jugglerToInsert
     */
    private void insertJugglerForCircuits(Juggler jugglerToInsert){
        for(Circuit circuit : this.circuits){
            if(circuit.getSortedJugglers() == null){
                circuit.setSortedJugglers(new LinkedList<Juggler>());
                circuit.getSortedJugglers().addFirst(jugglerToInsert);
            }
            else{
                for(int i = 0; i < circuit.getSortedJugglers().size() ;i++){
                    if(jugglerToInsert.getCircuitScores().get(circuit.getName()) < circuit.getSortedJugglers().get(i).getCircuitScores().get(circuit.getName())){
                        circuit.getSortedJugglers().add(i+1, jugglerToInsert);
                    }else if(jugglerToInsert.getCircuitScores().get(circuit.getName()) > circuit.getSortedJugglers().get(0).getCircuitScores().get(circuit.getName())){
                        circuit.getSortedJugglers().addFirst(jugglerToInsert);
                        break;
                    }
                }
            }
        }    
    }

    /**
     * Use to find the dot product between a Juggler and a Circuit
     * @param juggler
     * @param circuit
     * @return
     */
    private int scoreJugglerCircuit(Juggler juggler, Circuit circuit){
        return juggler.getEndurance() * circuit.getEndurance() + juggler.getHandEyeCoordination() * circuit.getHandEyeCoordination() + juggler.getPizzazz() * circuit.getPizzazz();
    }

}
