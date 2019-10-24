/**
 * Created by aman on 06-02-2018.
 */
package com.im.nothuman.talkingMorse;
public class user {
    public String title;
    public String description;
    public String status;
    public String to;
    public  String from;
    public user(){

    }
    public user(String title, String description, String status, String to, String from) {
       this.title = title;
        this.description = description;
        this.status = status;
        this.to = to;
        this.from = from;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {return from;}
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setStatus(String status) {this.status =status;}
    public void setTo(String to) { this.to = to;}

    public void setFrom(String from) {
        this.from = from;
    }
}
