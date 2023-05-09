package userClass

import java.io.File

open class User(var userDataFile: File) {

    var userIdListe: MutableList<Int> = mutableListOf()
    var userNameListe: MutableList<String> = mutableListOf()
    private var userPasswordListe: MutableList<String> = mutableListOf()
    private var userAgeListe: MutableList<Int> = mutableListOf()

    init {
        userDataFile.forEachLine { userIdListe.add(it.split(" ")[0].toInt()) }
        userDataFile.forEachLine { userNameListe.add(it.split(" ")[1]) }
        userDataFile.forEachLine { userPasswordListe.add(it.split(" ")[2]) }
        userDataFile.forEachLine { userAgeListe . add (it.split(" ")[3].toInt())}
    }

    fun userLogin(): Pair<Boolean,Int>{
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
            if( !userIdListe.contains(idInput.toIntOrNull()) ){
                idInput = null.toString()
                println("Bitte gebe eine gültige ID ein.")
                loginCounter++
                Thread.sleep(500)
                if (loginCounter >3){
                    throw Exception("Die maximal anzahl der versuche ist erreicht, Sie werden zurück zu Hauptmenü geleitet")
                }
                continue
            }


            if( userIdListe.contains(idInput.toIntOrNull()) ){
                idContain = true
            }
        } while (idInput.toIntOrNull() == null)


        do {

            if( idContain ){
                println("Gib bitte dein passwort ein")
                passwordInput = readln()

                if (passwordInput != userPasswordListe[userIdListe.indexOf(idInput.toInt())]){
                    println("Bitte gebe eine gültige Passwort ein.")
                    passwordCounter++

                }
                if (passwordInput == userPasswordListe[userIdListe.indexOf(idInput.toInt())]){
                    println("Der login wahr erfolgreich")
                    loginCheck = true
                }

                if (passwordCounter >= 4 ){
                    throw Exception("Die maximal anzahl der versuche ist erreicht, Sie werden zurück zu Hauptmenü geleitet")
                }

            }

        } while (passwordInput != userPasswordListe[userIdListe.indexOf(idInput.toInt())])
        return Pair(loginCheck,idInput.toInt())
    }

    fun userRegistrieren(){

        val minAge = 12
        var userAge = 0
        var userId = 0
        var userName = ""
        var userPassword = ""

        Thread.sleep(500)
        println("Willkommen zu registrierung Process")
        Thread.sleep(500)
        println("Sie können jeder zeit Registrierung abbrechen in dem sie Exit in Console eingeben")

        do {
            var checkAge = false
            Thread.sleep(500)
            println("Bitte geben Sie ihre Alter ein.")
            val inputAge = readln()

            if (inputAge.lowercase() == "exit"){ throw Exception("User Exit registrierung Process") }

            if(inputAge.toIntOrNull() == null){
                Thread.sleep(500)
                println("Die eingabe mus eine zahl sein")
                continue
            }
            if(inputAge.toIntOrNull()!! <= minAge ){
                Thread.sleep(500)
                throw Exception("Sie sind leider zu jung um sich zu Registrieren")
            }

            checkAge = true
            userAge = inputAge.toInt()

        } while (!checkAge)

        do {
            Thread.sleep(500)
            println("Bitte geben Sie gewünschten User ID ein der nur aus Zahlen besteht. Dieses brauchen Sie später für Den Login")
            var inputId = readln()

            if (inputId.lowercase() == "exit"){ throw Exception("User Exit registrierung Process") }

            if(inputId.toIntOrNull() == null){
                Thread.sleep(500)
                println("Die eingabe mus eine zahl sein")
                continue
            }

            if(this.userIdListe.contains(inputId.toIntOrNull())){
                Thread.sleep(500)
                println("Die ID $inputId ist leider vergeben. Versuchen Sie es mit eine andere Zahlenkombination.")
                continue
                }

            if(inputId.length > 12){
                Thread.sleep(500)
                println("Id darf maximal aus 12 zahlen bestehen")
                continue
            }

            userId = inputId.toInt()

        } while ( userId == 0)

        do {
            Thread.sleep(500)
            println("Bitte geben Sie ihren Name und Vorname ein")
            userName = readln().replace(" ", "_")

            if (userName.lowercase() == "exit"){ throw Exception("User printed Exit registrierung Process") }

        } while (userName == "")

        do {
            Thread.sleep(500)
            println("Bitte geben Sie ihren gewünschten Passwort ein")
            userPassword = readln()

            Thread.sleep(500)
            println("Bitte wiederholen Sie den zu vor eingegeben Passwort")
            val checkPassword  = readln()

            if(userPassword != checkPassword){
                println("Die eingaben stimmen nicht über ein.")
                continue
            }
            userPassword = checkPassword
        } while (userPassword == "")

        this.userIdListe.add(userId)
        this.userNameListe.add(userName)
        this.userPasswordListe.add(userPassword)
        this.userAgeListe.add(userAge)



        userDataFile.delete()
        var index = 0
        userDataFile.delete()
        userIdListe.forEach { _ ->
            userDataFile.appendText("${userIdListe[index]} ${userNameListe[index]} ${userPasswordListe[index]} ${userAgeListe[index]}\n")
            index++
        }
        index = 0

    }




}



