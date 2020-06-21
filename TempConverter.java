package com.mycompany.jerseytutorial;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/temp")
public class TempConverter {
    String output = "";
    double tempValue;
    double convertedTemp;

    //@GET
    //tempType is "fahrenheit" or "celsius", tempVal is String temperature
    //@Path("/{tempType}/{tempVal}")
    public Response convert(String tempType, String temp) {
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
    
    //curl -vi -H "Accept: text/plain" -X GET -G "http://localhost:49000/api/temp/celsius/32.2"
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/{tempType}/{tempVal}")
        public Response getTempPlain(@PathParam("tempType") String tempType, @PathParam("tempVal") String temp) { 
            Response r1 = convert(tempType, temp);
            return r1;
        }

    //curl -vi -H "Accept: text/xml" -X GET -G "http://localhost:49000/api/temp/celsius/32.2"
    @GET
    @Produces(MediaType.TEXT_XML)
    @Path("/{tempType}/{tempVal}")
        public String getTempXML(@PathParam("tempType") String tempType, @PathParam("tempVal") String temp) { 
            Response r1 = convert(tempType, temp);
            return "<?xml version=\"1.0\"?>" + "<temperature>" + output + "</temperature>";
        }

    //curl -vi -H "Accept: text/html" -X GET -G "http://localhost:49000/api/temp/celsius/32.2"
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/{tempType}/{tempVal}")
        public String getTempHTML(@PathParam("tempType") String tempType, @PathParam("tempVal") String temp) { 
            Response r1 = convert(tempType, temp);
            return "<html><title>Temperature Conversion</title><body><h1>Temperature Conversion</h1><p>" + output + "</p></body></html>";
        }
}
