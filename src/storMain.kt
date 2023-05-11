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

        try {
            if (input == "1") {
                val (logInCheckInput, idCheckInput) = User(visitorDataFile).userLogin()

                logInCheck = logInCheckInput
                idCheck = idCheckInput

                if (logInCheck) {
                    storeMainScreen(idCheck, visitorDataFile)
                    continue
                }

            }
        }   catch (ex: Exception){
            println(ex.message)
            continue
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
            "\nWillkommen im Online Shop \"GoldenSyntax\" ${
                User(visitorDataFile).userNameListe[User(visitorDataFile).userIdListe.indexOf(
                    userIdInput
                )].replace("_", " ")
            }\n"
        )


        Thread.sleep(500)
        printLowInfoVisitorShoppingCart(visitorShoppingCart)
        Thread.sleep(500)
        println("Hauptmenü:\n")

        Thread.sleep(500)
        println("Folgende Produkt Artikel haben wir für sie im auswahl:")

        Thread.sleep(500)
        println("1 -> Elektronik Artikel \n2 -> Mode Artikel \n\n8 -> Wahren Korb\n\n0 -> Ausloggen ")
        val inputHauptMenu = readln()

        while (inputHauptMenu == "1") {
            Thread.sleep(500)
            printLowInfoVisitorShoppingCart(visitorShoppingCart)

            Thread.sleep(500)
            println("Elektronil Artikel:\n")

            Thread.sleep(500)
            println("Folgende Elektronik Artikel haben wir für Sie im auswahl:")

            Thread.sleep(500)
            println("1 -> Tv Artikel \n2 -> Waschmaschinen \n\n8 -> Waren Korb\n\n0 -> Zu Haupt Menü ")
            val inputElektronikArtikel = readln()

            while (inputElektronikArtikel == "1") {
                Thread.sleep(500)
                printLowInfoVisitorShoppingCart(visitorShoppingCart)

                Thread.sleep(500)
                println("Tv Artikel Menü:")

                Thread.sleep(500)
                println("1 -> Alle Tv Artekel Ansehen")

                Thread.sleep(500)
                println("2 -> Artikel nach Name Sortieren")

                Thread.sleep(500)
                println("3 -> Artikel nach Preis Sortieren")

                Thread.sleep(500)
                println("4 -> Artikel nach Garantie Zeit Sortieren")

                Thread.sleep(500)
                println("5 -> Nach Zol Sortieren ")

                Thread.sleep(500)
                println("\n7 -> Artikel Zu wahren korb hinzufügen ")

                Thread.sleep(500)
                println("8 -> Wahren Korb")

                Thread.sleep(500)
                println("\n0 -> Zu Elektronick Artikel")

                val inputTvArtikelMenu = readln()

                if (inputTvArtikelMenu == "1") TvArtikel(tvArtikelListe).printKompletTvListe()
                if (inputTvArtikelMenu == "2") Produkt(tvArtikelListe).printSortedByNameProduktList()
                if (inputTvArtikelMenu == "3") Produkt(tvArtikelListe).printSortedByPriceProduktList()
                if (inputTvArtikelMenu == "4") TvArtikel(tvArtikelListe).printSortedTabelleGarantieZeitList()
                if (inputTvArtikelMenu == "5") TvArtikel(tvArtikelListe).printSortedByZolKompletTvListe()
                if (inputTvArtikelMenu == "7") visitorShoppingCart = Visitor(visitorDataFile).produktAddToShoppingCart(tvArtikelListe,visitorShoppingCart)
                if (inputTvArtikelMenu == "8") visitorShoppingCart = wahrenKorb(visitorShoppingCart,visitorDataFile)

                if (inputTvArtikelMenu == "0") break
                else continue

            }

            //TODO: Kühlschrank
            if (inputElektronikArtikel == "2") {
                Thread.sleep(500)
                println("Die Option ist noch nicht verfügbar.")
                continue
            }
            if(inputElektronikArtikel == "8") visitorShoppingCart = wahrenKorb(visitorShoppingCart,visitorDataFile)
            if (inputElektronikArtikel == "0") break

        }
        //TODO: Mode Artikel
        if (inputHauptMenu == "2") {
            Thread.sleep(500)
            println("Die Option ist noch nicht verfügbar.")
            continue
        }

        if (inputHauptMenu == "8"){
            visitorShoppingCart = wahrenKorb(visitorShoppingCart,visitorDataFile)
        }

        if (inputHauptMenu == "0") {
            Visitor(visitorDataFile).produktReturnToShop(visitorShoppingCart)
            throw Exception("Sie haben Sich erfolgreich Ausgeloggt.")
        }

    } while (true)

}

fun printLowInfoVisitorShoppingCart(visitorShoppingCart: MutableMap<Int,Int>):MutableMap<Int,Int>{

    var currentProduktFile = File("")

    val tvDataFail = File("src/Data/ArtikelListen/tvListe")
    val fridgesDataFile = File("src/Data/ArtikelListen/fridgesListe")
    val schuheDataFile = File("src/Data/ArtikelListen/schuheListe")
    val rucksackDataFile = File("src/Data/ArtikelListen/rucksackListe")

    var totalPrice = 0.0
    var articleNumber = 0

    for (j in visitorShoppingCart.keys){
        articleNumber += visitorShoppingCart[j]!!
    }

    for (i in visitorShoppingCart.keys) {
        when ((i / 1000)) {
            10 -> currentProduktFile = tvDataFail
            11 -> currentProduktFile = fridgesDataFile
            12 -> currentProduktFile = schuheDataFile
            13 -> currentProduktFile = rucksackDataFile
        }
    }

    for (j in visitorShoppingCart.keys){
        totalPrice += Produkt(currentProduktFile).returnPriceProduktList()[Produkt(currentProduktFile).returnIdProduktList().indexOf(j)] * visitorShoppingCart[j]!!
    }

    println("Warenkorb: Artikelmenge $articleNumber Gesamtpreis $totalPrice €\n")
    return visitorShoppingCart
}

fun wahrenKorb(visitorShoppingCart: MutableMap<Int, Int>, visitorDataFile: File): MutableMap<Int, Int> {
    var warenkorb = true

    do {

        for (i in visitorShoppingCart.keys) {
            if (i == 0) visitorShoppingCart.remove(i)
        }


        var currentProduktFile = File("")

        val tvDataFail = File("src/Data/ArtikelListen/tvListe")
        val fridgesDataFile = File("src/Data/ArtikelListen/fridgesListe")
        val schuheDataFile = File("src/Data/ArtikelListen/schuheListe")
        val rucksackDataFile = File("src/Data/ArtikelListen/rucksackListe")

        var currentLagerBestandListe: MutableList<Int>
        var inputMenge = ("").toIntOrNull()

        Thread.sleep(500)
        println("\nDetails zu deinem wahren Korb:")

        Thread.sleep(500)
        printLowInfoVisitorShoppingCart(visitorShoppingCart)

        for (i in visitorShoppingCart.keys) {
            when ((i / 1000)) {
                10 -> currentProduktFile = tvDataFail
                11 -> currentProduktFile = fridgesDataFile
                12 -> currentProduktFile = schuheDataFile
                13 -> currentProduktFile = rucksackDataFile
            }
            currentLagerBestandListe = Produkt(currentProduktFile).returnLagerBestandList()
            currentLagerBestandListe[Produkt(currentProduktFile).returnIdProduktList().indexOf(i)] =
                currentLagerBestandListe[Produkt(currentProduktFile).returnIdProduktList()
                    .indexOf(i)] + visitorShoppingCart[i]!!

            val produktIdList = Produkt(currentProduktFile).returnIdProduktList()
            val produktNameListe = Produkt(currentProduktFile).returnNameProduktList()
            val produktPriceListe = Produkt(currentProduktFile).returnPriceProduktList()

            Thread.sleep(500)
            println(
                "ID: $i Name: ${produktNameListe[produktIdList.indexOf(i)]} Einzel Preis: ${
                    produktPriceListe[produktIdList.indexOf(
                        i
                    )]
                } € " +
                        "Menge ${visitorShoppingCart[i]} Preis für diese Artikel: ${
                            produktPriceListe[produktIdList.indexOf(
                                i
                            )] * visitorShoppingCart[i]!!
                        } €"
            )

            if (visitorShoppingCart[i]!! <= 1) {
                println()
            }

        }
        Thread.sleep(500)
        println("\nWarenkorb Menü:")

        Thread.sleep(500)
        println("1 -> Produkte bestellen und bezahlen")

        Thread.sleep(500)
        println("2 -> Produkt aus wahren Korb entfernen")

        Thread.sleep(500)
        println("3 -> Wahren korb Lehren")

        Thread.sleep(500)
        println("\n0 -> Zurück zu Produkte")

        val inputWarenKorbMenu = readln()

        if (inputWarenKorbMenu == "1") {
            Thread.sleep(500)
            println("Deine Auftrag wurde entgegen genomen. Sie bekommen eine Rechnung per Post zusammen mit Ihre Wahre")
            visitorShoppingCart.clear()
            //TODO: Rechnung erstellen und absenden
        }
        if (inputWarenKorbMenu == "2") {
            do {
                Thread.sleep(500)
                println("Welches Produkt aus dem wahren Korb möchten Sie entfernen? Bitte ID eingeben\n0 -> Zu wahren Korb")
                val inputId = readln().toIntOrNull()

                if (inputId == 0) break

                if (inputId != null) {
                    if (visitorShoppingCart.containsKey(inputId.toInt())) {
                        do {
                            Thread.sleep(500)
                            println("Bitte geben Sie die anzahl des Produktartikel den Sie entfernen möchten")
                            inputMenge = readln().toIntOrNull()

                            if (inputMenge != null && inputMenge.toInt() <= visitorShoppingCart[inputId.toInt()]!!) {

                                val currentVisitorShoppingCart = mutableMapOf<Int, Int>()

                                currentVisitorShoppingCart[inputId] = inputMenge.toInt()

                                visitorShoppingCart[inputId] = visitorShoppingCart[inputId]!! - inputMenge.toInt()

                                Visitor(visitorDataFile).produktReturnToShop(currentVisitorShoppingCart)

                                Thread.sleep(500)
                                println("$inputMenge Artikel wurden aus Ihren wahren korb entfernt")

                                break
                            } else {
                                Thread.sleep(500)
                                println("Die eingabe mus eine Zahl sein, oder Sie versuchen mehr Artikel zu entfernen als Sie haben")
                                continue
                            }

                        } while (true)

                    }
                    if (!visitorShoppingCart.containsKey(inputId.toInt())) {
                        Thread.sleep(500)
                        println("Die eingegebene ID Befindet sich nicht in deinem wahren Korb")
                        continue
                    }

                }
                if (inputId == null) {
                    Thread.sleep(500)
                    println("Die eingabe mus eine Zahl sein")
                    continue
                }

            } while (true)

        }

        if (inputWarenKorbMenu == "3") {
            Visitor(visitorDataFile).produktReturnToShop(visitorShoppingCart)
            visitorShoppingCart.clear()
            Thread.sleep(500)
            println("Dein wahren Korb ist jetzt leer")

        }

        if (inputWarenKorbMenu == "0") {
            warenkorb = false
            return visitorShoppingCart
        }
    } while (warenkorb)
    return visitorShoppingCart
}