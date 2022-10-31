package com.miu.assignment4

class User(var firstName: String?, var lastName: String?, var emailId: String, var password:String) :
    java.io.Serializable {
    override fun toString(): String {
        return "User(firstName=$firstName, lastName=$lastName, email=$emailId, password=$password)"
    }
}