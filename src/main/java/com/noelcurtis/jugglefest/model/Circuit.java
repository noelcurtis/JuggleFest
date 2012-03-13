package com.noelcurtis.jugglefest.model;

import com.sun.deploy.util.StringQuoteUtil;

import java.util.*;

/**
 * User: noelcurtis
 * Date: 3/12/12
 * Time: 5:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class Circuit {

    private int handEyeCoordination;
    private int endurance;
    private int pizzazz;
    private String name;
    private LinkedList<Juggler> sortedJugglers;

    public Circuit(){

    }

    /**
     * Use to create a Circuit from a String
     * @param parameterString: "C C2 H:7 E:6 P:4"
     */
    public Circuit(String parameterString){
        String[] stringSplit = parameterString.split(" ");
        this.name = stringSplit[1];
        this.handEyeCoordination = Integer.parseInt(stringSplit[2].split(":")[1]);
        this.endurance = Integer.parseInt(stringSplit[3].split(":")[1]);
        this.pizzazz = Integer.parseInt(stringSplit[4].split(":")[1]);
    }

    public Circuit(String name, int handEyeCoordination, int endurance, int pizzazz)
    {
        this.handEyeCoordination = handEyeCoordination;
        this.endurance = endurance;
        this.pizzazz = pizzazz;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LinkedList<Juggler> getSortedJugglers() {
        return sortedJugglers;
    }

    public void setSortedJugglers(LinkedList<Juggler> sortedJugglers) {
        this.sortedJugglers = sortedJugglers;
    }

    @Override
    public String toString(){
        String description= "Name: " + this.name + " Hand Eye Coordination: " + this.handEyeCoordination + " Endurance: "+ this.endurance + " Pizzaz: "+ this.pizzazz + "\n";
        String jugglers = "";
        for(Juggler juggler : this.sortedJugglers){
            jugglers += juggler.getName() + " " + juggler.getCircuitScores().get(this.name) + " ";
        }
        return description + jugglers;
    }
}
