package userClass

import java.io.File

open class User(var userDataFile: File) {


    var userId: MutableList<Int> = mutableListOf()
    var userName: MutableList<String> = mutableListOf()
    var userPassword: MutableList<String> = mutableListOf()

    init {
        userDataFile.forEachLine { userId.add(it.split(" ")[0].toInt()) }
        userDataFile.forEachLine { userName.add(it.split(" ")[1]) }
        userDataFile.forEachLine { userPassword.add(it.split(" ")[2]) }
    }

    fun userLogin(): Boolean{
        println("Willkommen zu OnlineStore von \"Golden Syntax\" \n\n")
        var idInput = ""
        var passwordInput = ""
        var loginCounter = 0
        var idContain = false
        var passwordCheck = false
        var passwordCounter = 0
        var loginCheck = false

        do {

        println("Bitte gebe jetzt dein Kunde ID ein")
            idInput = readln()
            if (idInput.toIntOrNull() == null) {
                println("Die eingabe mus eine zahl Sein")
                continue
            }
            if( !userId.contains(idInput.toIntOrNull()) ){
                idInput = null.toString()
                println("Bitte gebe eine gültige ID ein.")
                loginCounter++
                Thread.sleep(500)
                if (loginCounter >3){
                    throw Exception("Die maximal anzahl der versuche ist erreicht, Sie werden zurück zu Hauptmenü geleitet")
                }
                continue
            }


            if( userId.contains(idInput.toIntOrNull()) ){
                idContain = true
            }
        } while (idInput.toIntOrNull() == null)


        do {

            if( idContain ){
                println("Gib bitte dein passwort ein")
                passwordInput = readln()

                if (passwordInput != userPassword[userId.indexOf(idInput.toInt())]){
                    println("Bitte gebe eine gültige Passwort ein.")
                    passwordCounter++

                }
                if (passwordInput == userPassword[userId.indexOf(idInput.toInt())]){
                    println("Der login wahr erfolgreich")
                    loginCheck = true
                }

                if (passwordCounter >= 4 ){
                    throw Exception("Die maximal anzahl der versuche ist erreicht, Sie werden zurück zu Hauptmenü geleitet")
                }

            }

        } while (passwordInput != userPassword[userId.indexOf(idInput.toInt())])
        return loginCheck
    }

}

