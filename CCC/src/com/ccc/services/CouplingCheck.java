package com.ccc.services;
import java.util.Scanner;
import java.io.*;

public class CouplingCheck {

    private Scanner scanner;
    
    public void openFile(){
        try{
            scanner = new Scanner(new File("WebContent/uploads/helloworld.java"));
        }catch(Exception e){
            System.out.println("Couldn't find the file.");
        }
    }
    
    public void readFile(){
        while(scanner.hasNext()){
            System.out.println(scanner.nextLine());
        }
    }
    
    public void closeFile(){
        scanner.close();
    }
    
    public void process(){
        openFile();
        readFile();
        closeFile();
    }

}
