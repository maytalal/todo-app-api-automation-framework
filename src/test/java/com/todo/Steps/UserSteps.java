package com.todo.Steps;

import com.github.javafaker.Faker;
import com.todo.apis.UserApi;
import com.todo.models.User;
import io.restassured.response.Response;

public class UserSteps {
    public static User generateUser(){
        Faker faker = new Faker() ;
        String firstName   = faker.name().firstName() ;
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String password  = faker.internet().password();
        String username  = faker.name().username();
        return new User(firstName,lastName,email,password,username) ;
    }
    public static Response registerUser(){
        User user  = generateUser() ;
       return UserApi.register(user);



    }
    public static Response LoginUser(User user){
        System.out.println("Hello from LoginUSer");
       return UserApi.login(user);

    }

    public static Response getUSerToken(){
        User user  = generateUser() ;
        Response response= UserApi.register(user);
        return response.body().path("accessToken");

    }



}
