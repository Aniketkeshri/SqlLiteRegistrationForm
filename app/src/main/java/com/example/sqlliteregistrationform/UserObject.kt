package com.example.sqlliteregistrationform

class UserObject {    // creating user class --1st step
   //Declaring variable name that want to Register
   var id : Int=0
   var username:String=""
   var useremail:String=""
   var userpassword:String=""
   var userphone:String=""
   var userdob:String=""
   var usergender:String=""

// Declaring a constructor in this user class
   constructor(
    username: String,
    useremail: String,
    userpassword: String,
    userphone:String,
    userdob: String,
    usergender: String,){
      //
      this.username=username
      this.useremail=useremail
      this.userpassword=userpassword
      this.userphone=userphone
      this.userdob=userdob
      this.usergender=usergender
   }

}