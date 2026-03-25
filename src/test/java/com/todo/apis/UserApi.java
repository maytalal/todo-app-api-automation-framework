package com.todo.apis;

import com.todo.Route.Route;
import com.todo.models.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class UserApi {

    public static Response register(User user) {

      return  given().baseUri(Route.REGISTER_PATH)
                .when().body(user)
                .contentType(ContentType.JSON)
                .post() .
                then().extract().response();


    }
    public static Response login(User user) {
        System.out.println("Hello from login USerAPI");
        HashMap<String ,String> body =new HashMap();
        body.put( "username" , user.getUsername() );
        body.put("password", user.getPassword());
        System.out.println("Username");
        System.out.println(user.getUsername());
       return given().baseUri(Route.LOGIN_PATH)
                .when().
                contentType(ContentType.JSON)
                .body(body).
                post()
               .then().extract().response();

    }

}
