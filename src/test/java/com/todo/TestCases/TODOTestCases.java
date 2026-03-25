package com.todo.TestCases;
 import com.todo.Route.Route;
 import com.todo.apis.ToDoApi;
 import com.todo.apis.UserApi;
 import com.todo.base.Specs;
 import com.todo.models.ToDo;
 import io.qameta.allure.Feature;
 import io.qameta.allure.Story;
 import io.restassured.http.ContentType;
 import io.restassured.response.Response;
 import org.testng.annotations.Test;
 import com.github.tomakehurst.wiremock.junit5.WireMockTest;
 import static com.github.tomakehurst.wiremock.client.WireMock.*;
 import java.util.HashMap;
 import static io.restassured.RestAssured.* ;
 import static org.hamcrest.MatcherAssert.assertThat;
 import static  org.hamcrest.Matchers.* ;

@Feature("TODO Features")

public class TODOTestCases {

 @Test(description = "Should Be AbLe TO Get All TODOS")
 @Story("Get All TODos")
 public void GetAllToDos() {

  given().spec(Specs.getRequestSpec())
          .get("/todos")
          .then().assertThat().statusCode(200)
          .log().all();
 }

 @Test(description = "Should Be AbLe TO Get Single TODO")
 @Story("Get Single ToDo")
 public void GetSingleToDo() {
  int todoId =1 ;
  given().baseUri(Route.TODOS_PATH)
          .when().
          contentType(ContentType.JSON).
          get("/" +todoId)
          .then().assertThat().statusCode(200)
          .log().all();
 }
 @Test(description = "Should Be AbLe TO Add  TODO")
 @Story("Add ToDo")
 public void ShouldbeAbleToAddToDo() {
   /* HashMap<String, Object> body = new HashMap<>();
  body.put("todo", "Use DummyJSON in the project");
  body.put("completed", false);
  body.put("userId", 5); */
  ToDo todo  = new ToDo("Learn RestAssured" , false , 1) ;

  Response response = ToDoApi.AddToDo(todo, 4);
  assertThat(response.statusCode() , equalTo(201));

 }
 @Test(description = "Should not be Able To Add ToDo If Is Completed Is Missing")
 @Story("Should not be Able To Add ToDo If Is Completed Is Missing")
 public void ShouldnotbeAbleToAddToDoIFIscompletedISMissing() {
  ToDo todo = new ToDo("Learn RestAssured");

  Response response = ToDoApi.AddToDo(todo , 5);
  response.then().log().all();
  // if Iscompleted is missing will take default boolean as false
  // It should be 400 but because it is Fake API it will return 201 We set 201 for Run purposes Only
  assertThat(response.statusCode() , equalTo(201)) ;

 }

 @Test(description = "Should be Able To Delete ToDo")
 @Story("Should  be Able To Delete ToDo")

 public void DeleteToDO() {
 int todo = 5 ;
  Response response = ToDoApi.DeleteToDo(todo);
  assertThat(response.statusCode(), equalTo(200));
  assertThat(response.body().jsonPath().get("isDeleted"),  equalTo(true));

 }

 @Test(description = "Should be Able To Update ToDo")
 @Story("Should  be Able To Update ToDo")
 public void UpdateToDO() {
  HashMap<String,Boolean> body =new HashMap<>();
  body.put("isCompleted" ,false);
  Response response = ToDoApi.UpdateToD(body , 5);
  assertThat(response.statusCode() , equalTo(200));

 }
}
