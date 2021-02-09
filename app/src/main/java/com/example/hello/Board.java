package com.example.hello;

class Board {
    String food;
    String content;

    public Board(){}

    Board(String food, String content){
        this.food = food;
        this.content = content;
    }
    public String getfood(){return food;}
    public void setfood(String food){
        this.food = food;
    }
    public void setcontent(String content){
        this.content = content;
    }
    public String getcontent(){return content;}
}
