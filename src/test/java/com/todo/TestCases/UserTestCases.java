package com.todo.TestCases;
import com.todo.Steps.UserSteps;
import com.todo.apis.UserApi;
import com.todo.base.Specs;
import com.todo.models.User;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.* ;
import static org.hamcrest.MatcherAssert.assertThat;
import static  org.hamcrest.Matchers.* ;

@Feature("User Features")
public class UserTestCases {
    @Test(description = "Should Be AbLe TO Get All Users")
    @Story("Get All Users")
    public void getAllUsers () {
    Response response = given().spec(Specs.getRequestSpec())
             .when().get("/users")
             .then()
             .log().all().extract().response();
    assertThat(response.statusCode() , equalTo(200));
    }

    @Test(description = "Should Be AbLe TO Register User")
    @Story("Should Be AbLe TO Register User")
    public void ShouldBeAbleToRegisterUser() {
   Response response = UserSteps.registerUser();
   User reutunedUSer =response.as(User.class);
   Response resp  = UserApi.register(reutunedUSer);
   assertThat(resp.statusCode() , equalTo(201));
   assertThat(resp.getBody().jsonPath().get("firstName") , equalTo(reutunedUSer.getFirstName()));
   resp.then().log().all();

    }

    @Test(description = "Should Be AbLe TO Login ")
    @Story("Should Be AbLe TO Login")
    public void LoginUser() {
    /* Instead of this method , we will use POJO classes
        HashMap<String ,String>body =new HashMap();
        body.put( "username" , "emilys" );
        body.put("password", "emilyspass");
        body.put("expiresInMins": 30 ); */
        Response response  = UserSteps.registerUser();
        response.then().log().all();
        // we will use seperate API classes
        User returnedUser = response.as(User.class);
        response = UserSteps.LoginUser(returnedUser);
        response.then().log().all();
        // assertThat(response.statusCode() , equalTo(200)) ;
        // assertThat(response.getBody().jsonPath().get("acessToken"),  not(equalTo(empty()))) ;

    }
    @Test(description = "Should Be AbLe TO Get User")
    @Story("Should Be AbLe TO Get User")
    public void GetAsingleUser() {
     String accessToken= "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJlbWlseXMiLCJlbWFpbCI6ImVtaWx5LmpvaG5zb25AeC5kdW1teWpzb24uY29tIiwiZmlyc3ROYW1lIjoiRW1pbHkiLCJsYXN0TmFtZSI6IkpvaG5zb24iLCJnZW5kZXIiOiJmZW1hbGUiLCJpbWFnZSI6Imh0dHBzOi8vZHVtbXlqc29uLmNvbS9pY29uL2VtaWx5cy8xMjgiLCJpYXQiOjE3Nz";
     Response response  =   given().spec(Specs.getRequestSpec())
             .auth().oauth2(accessToken)
                .when().
                contentType(ContentType.JSON).
                get("/users/1") ;

        User returneduser = response.body().as(User.class);
        assertThat(response.statusCode() , equalTo(200));
        assertThat(returneduser.getFirstName() , equalTo("Emily"));



    }
    // Deserilize Error Message
    @Test(description = "Should Not Be AbLe TO Login User")
    @Story("Should Not Be AbLe TO Login User")
    public void ShouldNotbeAbletoLoginUser() {
       User user  = new User("Mai" , "maipass");
       Response response   = UserApi.login(user);
       Error errorReturned  = response.body().as(Error.class);
       assertThat(errorReturned.getMessage(), equalTo("Invalid credentials"));
       assertThat(response.statusCode() , equalTo(400));


    }


}
