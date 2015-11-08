package main;

import com.google.gson.*;

public class JSONConverter {
    
    private Gson gson;
    
    public JSONConverter() {
        gson = new Gson();
    }
    
    public ParseResultStorage getParsedData(String string) {
        return gson.fromJson(string, ParseResultStorage.class); 
    }
    
    public String getJSONString(ParseResultStorage result) {
        return gson.toJson(result);
    }

}
