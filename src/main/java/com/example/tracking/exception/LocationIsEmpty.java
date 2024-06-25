package com.example.tracking.exception;


public class LocationIsEmpty extends Exception{

    public LocationIsEmpty(){
        
    }
    
    public LocationIsEmpty(String message){
        super(message);
    }

}
