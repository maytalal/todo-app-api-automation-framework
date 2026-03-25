package com.todo.base;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Specs {
    public  static String getEnv() {
        String baseURI;
        String env = System.getProperty("env" ,"PROD");
        switch (env) {
            case "PRODUCTION":
                baseURI = "https://dummyjson.com";
                break;
            case "LOCAL":
                baseURI = "https://localhost:8080";
                break;
            default:
                throw  new RuntimeException("Enviroment is not supported");

        }
        return baseURI;


    }
    public static RequestSpecification getRequestSpec(){
        return given().baseUri(getEnv())
                .when().
                contentType(ContentType.JSON).
                log().all();


    }
}
