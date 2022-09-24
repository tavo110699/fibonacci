package com.marlic.fibonacci.controllers;

import com.marlic.fibonacci.models.Fibonacci;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@RestController
public class fibonacciController {

    private Object List;
    private Object Fibonacci;

        //Obtiene todos los registros del archivo json
        @GetMapping("fibonacci/getAll")
        public JSONArray getAll(){
            JSONArray jsonArray = new JSONArray();
            JSONParser jsonParser = new JSONParser();
            try {
                FileReader fileReader = new FileReader("data.json");
                Object object = jsonParser.parse(fileReader);
                JSONArray requests = (JSONArray) object;
                for( Object request : requests){
                    jsonArray.add(request);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return jsonArray;
            
        }

    //genera la serie 
    @GetMapping("fibonacci/get/{n}")
    public Fibonacci getFibonacci(@PathVariable String n){
        String response = "";
        if(isNumeric(n)){
            int value = Integer.parseInt(n);
            if(value>=0){
                for (int i = 0; i < value; i++) {
                    response += (fibonacci(i)) + (" ");
                }
                saveRequest(n);
            } else {
                response = ("Error, número negativo");
            }
        } else {
            response = ("Error, valor incorrecto");
        }

        return new Fibonacci(response);
    }

    // Elimina el registro del archivo json
    @DeleteMapping("fibonacci/delete/{n}")
    public String removeRequest(@PathVariable String n){
        if(deleteRequest(n))
            return "Eliminado con exito";
        return "No se encontro registro";
    }

    //Calcula la serie
    public int fibonacci(int n) {
        if (n>1){
            return fibonacci(n-1) + fibonacci(n-2); 
        }
        else if (n==1) {
            return 1;
        }
        else if (n==0){
            return 0;
        }
        else{
            return -1;
        }
    }

    //verifica si el numero es mayor a 0 
    public boolean isNumeric(String str) {

        boolean res;

        try {
            Integer.parseInt(str);
            res = true;
        } catch (NumberFormatException excepcion) {
            res = false;
        }

        return res;
    }

    //Guarda el registro en el json
    public void saveRequest(String n) {
        JSONArray jsonArray = new JSONArray();
        jsonArray = readFile();
        JSONObject request = new JSONObject();
        request.put("n", n);
        request.put("date", LocalDate.now().toString());
        jsonArray.add(request);

        try {
            FileWriter fileWriter = new FileWriter("data.json");
            fileWriter.write(jsonArray.toJSONString());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Leer el archivo Json
    public JSONArray readFile() {
        JSONArray jsonArray = new JSONArray();
        JSONParser jsonParser = new JSONParser();
        try {
            FileReader fileReader = new FileReader("data.json");
            Object object = jsonParser.parse(fileReader);
            JSONArray requests = (JSONArray) object;
            for( Object request : requests){
                jsonArray.add(request);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    // Eliminar del archivo Json
    public boolean deleteRequest(String n) {
        JSONArray jsonArray = new JSONArray();
        jsonArray = readFile();
        for(Object object : jsonArray) {
            JSONObject jsonObject1 = (JSONObject) object;
            if(jsonObject1.get("n").equals(n)){
                jsonArray.remove(jsonObject1);
                try {
                    FileWriter fileWriter = new FileWriter("data.json");
                    fileWriter.write(jsonArray.toJSONString());
                    fileWriter.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            }
        }
        return  false;
    }


}
