package com.example.assignment2.model

class Users {
    var id:Int=1
    var name:String?=null
    var password:String?=null

    constructor()
    constructor(name:String,password: String){
        this.name=name
        this.password=password
    }
}
