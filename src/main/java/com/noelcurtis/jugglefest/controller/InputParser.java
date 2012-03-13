package com.noelcurtis.jugglefest.controller;

import com.noelcurtis.jugglefest.model.Circuit;
import com.noelcurtis.jugglefest.model.Juggler;

import java.io.*;
import java.util.*;

/**
 * User: noelcurtis
 * Date: 3/12/12
 * Time: 5:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class InputParser {

    /**
     * Use to read a file and populate models.
     * @param fileName: name of file you want to read.
     * @return Dictionary: with key: Type of object, value: Collection of objects
     * @throws Exception
     */
    public static Dictionary<Class, Collection> parseInputFile(String fileName)throws Exception{
        try{
            Hashtable<Class, Collection> parameters = new Hashtable<Class, Collection>();
            ArrayList<Juggler> jugglers = new ArrayList<Juggler>();
            ArrayList<Circuit> circuits = new ArrayList<Circuit>();
            
            // Open the file that is the first
            // command line parameter
            FileInputStream fstream = new FileInputStream(fileName);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;
            //Read File Line By Line
            while ((strLine = br.readLine()) != null)   {
                if(!strLine.equals("")){
                    Object parsedObject = InputParser.parseLine(strLine);
                    if(parsedObject instanceof Circuit){
                        circuits.add((Circuit)parsedObject);
                    }else{
                        jugglers.add((Juggler)parsedObject);
                    }
                }
            }
            //Close the input stream
            in.close();
            parameters.put(Circuit.class, circuits);
            parameters.put(Juggler.class, jugglers);
            return parameters;
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * Use to parse a line of the form:
     * @param line: String with parameters
     * @return Object created from the String
     * @throws Exception
     */
    private static Object parseLine(String line)throws Exception{
       String[] stringSplit =  line.split(" ");
       if(stringSplit[0].toLowerCase().equals("c")){
            return new Circuit(line);
       }else if(stringSplit[0].toLowerCase().equals("j")){
           return new Juggler(line);
       }else{
            throw new Exception("Im not sure what object to create for this line!");
       }
    }
}
