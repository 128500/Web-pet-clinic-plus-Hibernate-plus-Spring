package com.llisovichok.models;

import java.util.Objects;

/**
 * Created by KUDIN ALEKSANDR on 21.07.2017.
 */
public class Message {

    private Integer id;
    private User user;
    private String text;

    public Message(){}

    public Message(String text){
        this.text = text;
    }

    public Integer getId(){
        return this.id;
    }

    public User getUser(){
        return this.user;
    }

    public String getText(){
        return this.text;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public void setUser(User user){
        this.user = user;
    }

    public void setText(String text){
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(id, message.id) &&
                Objects.equals(user, message.user) &&
                Objects.equals(text, message.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, text);
    }
}
