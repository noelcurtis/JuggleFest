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
        this.pruneJugglersFromCircuits();
    }


    private void pruneJugglersFromCircuits() throws Exception{
        for(Juggler juggler : jugglers){
            ArrayList<Circuit> conflictedJugglerCircuits = new ArrayList<Circuit>();
            int  lowestJugglerPosition = ((ArrayList<Circuit>)this.circuits).get(0).getSortedJugglers().indexOf(juggler);
            Circuit lowestPositionCircuit =((ArrayList<Circuit>)this.circuits).get(0);
            for(Circuit circuit : circuits){
                if(circuit.getSortedJugglers().indexOf(juggler) < lowestJugglerPosition
                        && circuit != ((ArrayList<Circuit>)this.circuits).get(0)){
                    lowestJugglerPosition=circuit.getSortedJugglers().indexOf(juggler);
                    lowestPositionCircuit = circuit;
                }
                else if(circuit.getSortedJugglers().indexOf(juggler) == lowestJugglerPosition
                        && circuit != ((ArrayList<Circuit>)this.circuits).get(0)){
                    conflictedJugglerCircuits.add(circuit);
                }
            }
            if(!conflictedJugglerCircuits.isEmpty()){
                conflictedJugglerCircuits.add(lowestPositionCircuit);
                int lowestCircuitPreference = ((LinkedList<String>)juggler.getActualCircuitRankingNames()).indexOf(conflictedJugglerCircuits.get(0).getName());
                Circuit currentLowestCircuit = conflictedJugglerCircuits.get(0);
                for(Circuit conflictedCircuit : conflictedJugglerCircuits){
                    if(((LinkedList<String>)juggler.getActualCircuitRankingNames()).indexOf(conflictedCircuit.getName()) < lowestCircuitPreference){
                        currentLowestCircuit = conflictedCircuit;
                        lowestCircuitPreference = ((LinkedList<String>)juggler.getActualCircuitRankingNames()).indexOf(conflictedCircuit.getName());
                    }
                }
                currentLowestCircuit.assignJuggler(juggler);

            }else{
                lowestPositionCircuit.assignJuggler(juggler);
            }
        }
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
     * @throws Exception
     */
    private void calculateCircuitScores()throws Exception{

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
    private void insertJugglerForCircuits(Juggler jugglerToInsert) throws Exception{
        for(Circuit circuit : this.circuits){
            if(circuit.getSortedJugglers() == null){
                circuit.setSortedJugglers(new LinkedList<Juggler>());
                circuit.getSortedJugglers().addFirst(jugglerToInsert);
            }else if(circuit.getSortedJugglers().size() == 1){
                if (jugglerToInsert.getCircuitScores().get(circuit.getName()) > circuit.getSortedJugglers().getFirst().getCircuitScores().get(circuit.getName()))
                {
                    circuit.getSortedJugglers().addFirst(jugglerToInsert);
                }else{
                    circuit.getSortedJugglers().add(1, jugglerToInsert);
                }
            }
            else{
                Boolean insertedFlag = false;
                for(int i = 0; i < circuit.getSortedJugglers().size() ;i++){
                    if(jugglerToInsert.getCircuitScores().get(circuit.getName()) > circuit.getSortedJugglers().get(i).getCircuitScores().get(circuit.getName())){
                        circuit.getSortedJugglers().add(i, jugglerToInsert);
                        insertedFlag = true;
                        break;
                    }
                }
                if(!insertedFlag){
                    circuit.getSortedJugglers().addLast(jugglerToInsert);
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
