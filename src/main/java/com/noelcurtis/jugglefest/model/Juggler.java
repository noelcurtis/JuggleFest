package com.noelcurtis.jugglefest.model;

import java.util.*;

/**
 * User: noelcurtis
 * Date: 3/12/12
 * Time: 5:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class Juggler{
    
    private int handEyeCoordination;
    private int endurance;
    private int pizzazz;
    private Collection<String> preferredCircuitNames;
    private Dictionary<String, Integer> circuitScores;
    private Collection<String> actualCircuitRankingNames;
    private String name;

    public Juggler(){

    }

    /**
     * Use to create a Circuit from a String
     * @param parameterString: "J J0 H:3 E:9 P:2 C2,C0,C1"
     */
    public Juggler(String parameterString){
        String[] stringSplit = parameterString.split(" ");
        this.name = stringSplit[1];
        this.handEyeCoordination = Integer.parseInt(stringSplit[2].split(":")[1]);
        this.endurance = Integer.parseInt(stringSplit[3].split(":")[1]);
        this.pizzazz = Integer.parseInt(stringSplit[4].split(":")[1]);
        this.preferredCircuitNames = Arrays.asList(stringSplit[5].split(","));
    }

    public Juggler(String name,int handEyeCoordination, int endurance, int pizzazz, Collection<String> preferredCircuitNames)
    {
        this.handEyeCoordination = handEyeCoordination;
        this.endurance = endurance;
        this.pizzazz = pizzazz;
        this.name = name;
        this.preferredCircuitNames = preferredCircuitNames;
    }

    public int getHandEyeCoordination() {
        return handEyeCoordination;
    }

    public void setHandEyeCoordination(int handEyeCoordination) {
        this.handEyeCoordination = handEyeCoordination;
    }

    public int getEndurance() {
        return endurance;
    }

    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public int getPizzazz() {
        return pizzazz;
    }

    public void setPizzazz(int pizzazz) {
        this.pizzazz = pizzazz;
    }

    public Collection<String> getPreferredCircuitNames() {
        return preferredCircuitNames;
    }

    public void setPreferredCircuitNames(Collection<String> preferredCircuitNames) {
        this.preferredCircuitNames = preferredCircuitNames;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dictionary<String, Integer> getCircuitScores() {
        return circuitScores;
    }

    public void setCircuitScores(Dictionary<String, Integer> circuitScores) {
        this.circuitScores = circuitScores;
        this.setupActualCircuitRanking();
    }

    public Collection<String> getActualCircuitRankingNames() {
        return actualCircuitRankingNames;
    }

    public void setupActualCircuitRanking(){
        LinkedList<String> actualCircuitRankings = new LinkedList<String>();

        for(String circuitName : this.preferredCircuitNames){

            if(actualCircuitRankings.size() == 0){
                actualCircuitRankings.addFirst(circuitName);
            }else if(actualCircuitRankings.size() == 1){
                if (this.getCircuitScores().get(circuitName) > this.getCircuitScores().get(actualCircuitRankings.getFirst()))
                {
                    actualCircuitRankings.addFirst(circuitName);
                }else{
                    actualCircuitRankings.add(1, circuitName);
                }
            }
            else{
                Boolean insertedFlag = false;
                for(int i = 0; i < actualCircuitRankings.size() ;i++){
                    if(this.getCircuitScores().get(circuitName) > this.getCircuitScores().get(actualCircuitRankings.get(i))){
                        actualCircuitRankings.add(i, circuitName);
                        insertedFlag = true;
                        break;
                    }
                }
                if(!insertedFlag){
                    actualCircuitRankings.addLast(circuitName);
                }
            }
        }
        this.actualCircuitRankingNames = actualCircuitRankings;
    }

    @Override
    public String toString(){
        String description = "Name: " + this.name + " Hand Eye Coordination: " + this.handEyeCoordination + " Endurance: "+ this.endurance + " Pizzaz: "+ this.pizzazz;
        String circuitScores = "";
        if(this.circuitScores != null){
            for(String key : ((Hashtable<String, Integer>)this.circuitScores).keySet()){
                circuitScores += key+ ":" + this.circuitScores.get(key) + " ";
            }
        }
        return description + "\n" + circuitScores;
    }

    public String getCircuitScoresAsString(){
        String circuitScores = "";
        if(this.circuitScores != null){
            for(String key : ((Hashtable<String, Integer>)this.circuitScores).keySet()){
                circuitScores += key+ ":" + this.circuitScores.get(key) + ",";
            }
        }
        return circuitScores;
    }
}
