import produktClass.Produkt
import produktClass.elektronikArtikelClass.TvArtikel
import userClass.User
import userClass.Visitor
import java.io.File
import kotlin.system.exitProcess


fun main() {
    do {
        val visitorDataFile = File("src/Data/UserListe/visitorListe")

        Thread.sleep(500)
        println("\n\nWillkommen im Online Shop \"GoldenSyntax\"\n")

        Thread.sleep(500)
        println("Menü: \n1 -> Einlogen \n2 -> Registrieren \n\n0 -> Online Store Verlassen")

        val input = readln()
        var logInCheck: Boolean
        var idCheck: Int

        if (input == "1") {
            val (logInCheckInput, idCheckInput) = User(visitorDataFile).userLogin()

            logInCheck = logInCheckInput
            idCheck = idCheckInput

            if (logInCheck) {
                storeMainScreen(idCheck, visitorDataFile)
                continue
            }

        }

        try {
            if (input == "2") {
                User(visitorDataFile).userRegistrieren()
                continue
            }
        } catch (ex: Exception) {
            println(ex.message)
            continue
        }

        if (input == "0") {
            Thread.sleep(500)
            println("Auf wiedersehen und bis bald")
            exitProcess(9)
        } else {
            Thread.sleep(500)
            println("Die eingabe wurde nicht verstanden!")
            continue
        }

    } while (true)
}

fun storeMainScreen(userIdInput: Int, visitorDataFile: File) {

    val tvArtikelListe = File("src/Data/ArtikelListen/tvListe")
    var visitorShoppingCart: MutableMap<Int, Int> = mutableMapOf()

    do {
        Thread.sleep(500)
        println(
            "Willkommen im Online Shop \"GoldenSyntax\" ${
                User(visitorDataFile).userNameListe[User(visitorDataFile).userIdListe.indexOf(
                    userIdInput
                )].replace("_", " ")
            }\n"
        )

        Thread.sleep(500)
        println("Hauptmenü:\n")

        Thread.sleep(500)
        println("Folgende Produkt Artikel haben wir für sie im auswahl:")

        Thread.sleep(500)
        println("1 -> Elektronik Artikel \n2 -> Mode Artikel \n\n0 -> Ausloggen ")
        val inputHauptMenu = readln()

        while (inputHauptMenu == "1") {

            Thread.sleep(500)
            println("\n\nElektronil Artikel:\n")

            Thread.sleep(500)
            println("Folgende Elektronik Artikel haben wir für Sie im auswahl:\n")

            Thread.sleep(500)
            println("1 -> Tv Artikel \n2 -> Waschmaschinen \n\n0 -> Zu Haupt Menü ")
            val inputElektronikArtikel = readln()

            while (inputElektronikArtikel == "1") {
                Thread.sleep(500)
                println("\nTv Artikel Menü:\n")

                Thread.sleep(500)
                println("1 -> Alle Tv Artekel Ansehen")

                Thread.sleep(500)
                println("2 -> Artikel nach Name Sortieren")

                Thread.sleep(500)
                println("3 -> Artikel nach Preis Sortieren")

                Thread.sleep(500)
                println("4 -> Artikel nach Garantie Zeit Sortieren")

                Thread.sleep(500)
                println("\n5 -> Artikel Zu wahren korb hinzufügen ")

                Thread.sleep(500)
                println("\n0 -> Zu Elektronick Artikel")
                val inputTvArtikelMenu = readln()

                if (inputTvArtikelMenu == "1") TvArtikel(tvArtikelListe).printKompletTvListe()
                if (inputTvArtikelMenu == "2") Produkt(tvArtikelListe).printSortedByNameProduktList()
                if (inputTvArtikelMenu == "3") Produkt(tvArtikelListe).printSortedByPriceProduktList()
                if (inputTvArtikelMenu == "4") TvArtikel(tvArtikelListe).printSortedByZolKompletTvListe()
                if (inputTvArtikelMenu == "5") visitorShoppingCart = Visitor(visitorDataFile).produktAddToShoppingCart(tvArtikelListe)
                if (inputTvArtikelMenu == "0") break
                else continue

            }

            //TODO: Kühlschrank
            if (inputElektronikArtikel == "2") {
                Thread.sleep(500)
                println("Die Option ist noch nicht verfügbar.")
                continue
            }
            if (inputElektronikArtikel == "0") break

        }
        //TODO: Mode Artikel
        if (inputHauptMenu == "2") {
            Thread.sleep(500)
            println("Die Option ist noch nicht verfügbar.")
            continue
        }

        if (inputHauptMenu == "0") {
            throw Exception("Sie haben Sich erfolgreich Ausgeloggt.")
        }

    } while (true)

}