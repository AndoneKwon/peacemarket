package com.hanium.pay.util;


import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class FastParser {
    
    JsonElement element;

    public FastParser(JsonElement element){
        this.element = element;
    }
    
    public static FastParser create(String result){
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(result);
        return new FastParser(element);
    }

    public String getString(String key){
        return element.getAsJsonObject().get(key).getAsString();
    }
    
}
