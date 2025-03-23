package com.eazybytes.task1_service3.Exceptions;

public class JsonParsingException extends Exception{
    private String attr;
    public JsonParsingException(String attr) {
        this.attr = attr;

    }

    public String getAttr() {
        return attr;
    }


}
