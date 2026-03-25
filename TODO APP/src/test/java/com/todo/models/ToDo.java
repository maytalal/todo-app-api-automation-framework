package com.todo.models;

import com.fasterxml.jackson.annotation.JsonInclude;

public class ToDo {

    private  String todo ;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private  Boolean iscompleted ;
    private   int userId ;


    public ToDo(String todo ,
                Boolean iscompleted ,
                int userId ){
        this.iscompleted = iscompleted ;
        this.userId = userId ;
        this.todo = todo ;

    }
    public ToDo(String todo ){
        this.todo = todo ;
    }
    public ToDo (){}

    public Boolean getIscompleted() {
        return iscompleted;
    }

    public void setIscompleted(Boolean iscompleted) {
        this.iscompleted = iscompleted;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }





}
