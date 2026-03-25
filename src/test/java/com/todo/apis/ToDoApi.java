package com.todo.apis;

import com.todo.Route.Route;
import com.todo.models.ToDo;
import com.todo.models.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class ToDoApi {
public static Response AddToDo (ToDo todo  , int userId){

     todo.setUserId(userId);
     return given().baseUri(Route.TODO_ADD_PATH)
            .when().
            contentType(ContentType.JSON).
            body(todo).
            post();

}
    public static Response DeleteToDo (int todo ) {
        return given().baseUri(Route.TODOS_PATH)
                .when().
                contentType(ContentType.JSON).
                delete("/"+ todo) ;

    }
    public static  Response UpdateToD(HashMap<String ,Boolean> body , int todo) {

      return  given().baseUri(Route.TODOS_PATH)
                .when().
                contentType(ContentType.JSON).
                body(body).
                put("/"+ todo);


    }



}
