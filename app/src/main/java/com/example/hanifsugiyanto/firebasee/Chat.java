package com.example.hanifsugiyanto.firebasee;

/**
 * Created by hanifsugiyanto on 01/11/15.
 */
public class Chat {

    public String user;
    public String message;

    //firebase deserialisasi merubah tipe data json menjadi objek
    //harus dikasih konstruktor kosong

    public Chat(){

    }


    public Chat(String user, String message) {
        this.user = user;
        this.message = message;



    }
}
