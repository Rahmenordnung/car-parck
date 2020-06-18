package com.mycompany.jerseytutorial;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/temp")
public class TempConverter {
    @GET
    //tempType is "fahrenheit" or "celsius", tempVal is String temperature
    @Path("/{tempType}/{tempVal}")
    public Response convert(@PathParam("tempType") String tempType, @PathParam("tempVal") String temp) {
        String output = "";
        double tempValue;
        double convertedTemp;
        
        //catch numberExceptions when setting tempValue
        try { tempValue = Double.parseDouble(temp); }
        catch (NumberFormatException e) {
            output += "The temperature entered is not a number.";
            return Response.status(200).entity(output).build();
        }
        
        switch (tempType) {
            case "fahrenheit":
                convertedTemp = (tempValue - 32) * 5 / 9;
                //round to 2 decimal places
                convertedTemp = (double) Math.round(convertedTemp*100)/100;
                output += "The temperature " + tempValue + " in " + tempType + " is " + convertedTemp + ".";
                break;
            case "celsius":
                convertedTemp = (tempValue * 9 / 5) + 32;
                //round to 2 decimal places
                convertedTemp = (double) Math.round(convertedTemp*100)/100;
                output += "The temperature " + tempValue + " in " + tempType + " is " + convertedTemp + ".";
                break;
            default:
                output += "This is not a valid temperature type!\n";
                output += "The first parameter should be 'fahrenheit' or 'celsius'.";
        }
        return Response.status(200).entity(output).build();
    }
}
