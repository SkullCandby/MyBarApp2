package com.firstapp.myapplication

class Rec {
    var user_email = ""
    var user_text = ""
    var time = ""
    var coord_x = 0
    var coord_y = 0
    constructor(user_text:String, time:String, user_email:String, coord_x: Int, coord_y: Int){

        this.user_email = user_email
        this.user_text = user_text
        this.time = time
        this.coord_x = coord_x
        this.coord_y = coord_y
    }
}