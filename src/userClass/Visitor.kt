package userClass

import produktClass.Produkt
import java.io.File

class Visitor(visitorDataFile: File) : User(visitorDataFile) {


    fun produktAddToShoppingCart(produktDataFile: File,shoppingCart: MutableMap<Int, Int>): MutableMap<Int, Int> {

        val shoppingCartInt: MutableMap<Int, Int> = shoppingCart
        val produktIdList = Produkt(produktDataFile).returnIdProduktList()
        val produktNameListe = Produkt(produktDataFile).returnNameProduktList()
        val produktPriceListe = Produkt(produktDataFile).returnPriceProduktList()
        val produktLagerBestandListe = Produkt(produktDataFile).returnLagerBestandList()

        val wehrt4 = Produkt(produktDataFile).returnArtProduktList()
        val wehrtX = Produkt(produktDataFile).returnProduktList()

        do {

            println("Bitte gebe die ID ein des produkt den du kaufen möchtest")

            val inputIdForBy = readln().toIntOrNull()

            if (inputIdForBy != null) {


                while (produktIdList.contains(inputIdForBy.toInt())) {

                    println("Was ist die Anzahl an Produkten die Sie kaufen möchten? \nBitte eine Zahl eingeben")
                    val inputProduktNumber = readln().toIntOrNull()

                    if(produktLagerBestandListe[produktIdList.indexOf(inputIdForBy)] - inputProduktNumber!!.toInt() > 0){
                        println("Sie können nicht so viele produkte hinzufügen, es sind ${produktLagerBestandListe[produktIdList.indexOf(inputIdForBy)]} Produkte dieser Art im Sortiment")
                        continue
                    }
                    if (!shoppingCartInt.keys.contains(inputProduktNumber)) {
                        shoppingCartInt[inputIdForBy] = 0
                    }

                    if (inputProduktNumber != null) {
                        shoppingCartInt[inputIdForBy] = shoppingCart[inputIdForBy]!!.plus(inputProduktNumber.toInt())
                        println(
                            "Sie haben $inputProduktNumber ${produktNameListe[produktIdList.indexOf(inputIdForBy.toInt())]} im wehrt von " +
                                    "${
                                        shoppingCartInt[inputIdForBy]!! * produktPriceListe[produktIdList.indexOf(
                                            inputIdForBy.toInt()
                                        )]
                                    } € zu eigenen wahren Korb hinzufügt :-)"
                        )

                        produktLagerBestandListe[produktIdList.indexOf(inputIdForBy.toInt())] -= inputProduktNumber

                        produktDataFile.writeText("")

                        var index = 0
                        produktIdList.forEach { _->
                            produktDataFile.appendText("${produktIdList[index]} ${produktNameListe[index]} ${produktPriceListe[index]} " +
                                    "${wehrt4[index]} ${produktLagerBestandListe[index]} " +
                                    "${wehrtX[index].split(" ")[6]} " +
                                    "${wehrtX[index].split(" ")[7]} " +
                                    "${wehrtX[index].split(" ")[8]}\n")
                            index++

                        }
                        index = 0
                        return shoppingCartInt

                    }

                }
                if (!produktIdList.contains(inputIdForBy.toInt())) {
                    println("Die eingegeben ID ist nicht in der Liste.")
                    continue

                }

            }
            if (inputIdForBy == null) {
                println("Die eingabe Mus ein Zahl sein!")
                continue
            }

        } while (true)

    }
    fun produktReturnToShop( shoppingCart: MutableMap<Int, Int>){
        var currentProduktFile = File("")

        val tvDataFail = File("src/Data/ArtikelListen/tvListe")
        val fridgesDataFile = File("src/Data/ArtikelListen/fridgesListe")
        val schuheDataFile = File("src/Data/ArtikelListen/schuheListe")
        val rucksackDataFile = File("src/Data/ArtikelListen/rucksackListe")

        var currentLagerBestandListe: MutableList<Int>

        for (i in shoppingCart.keys) {
            when ((i / 1000)) {
                10 -> currentProduktFile = tvDataFail
                11 -> currentProduktFile = fridgesDataFile
                12 -> currentProduktFile = schuheDataFile
                13 -> currentProduktFile = rucksackDataFile
            }
            currentLagerBestandListe = Produkt(currentProduktFile).returnLagerBestandList()
            currentLagerBestandListe[Produkt(currentProduktFile).returnIdProduktList().indexOf(i)] = currentLagerBestandListe[Produkt(currentProduktFile).returnIdProduktList().indexOf(i)].plus(shoppingCart[i]!!)

            val produktIdList = Produkt(currentProduktFile).returnIdProduktList()
            val produktNameListe = Produkt(currentProduktFile).returnNameProduktList()
            val produktPriceListe = Produkt(currentProduktFile).returnPriceProduktList()
            val wehrt4 = Produkt(currentProduktFile).returnArtProduktList()
            val wehrtX = Produkt(currentProduktFile).returnProduktList()

            var index = 0

            currentProduktFile.writeText("")
            produktIdList.forEach { _->
                currentProduktFile.appendText("${produktIdList[index]} ${produktNameListe[index]} ${produktPriceListe[index]} " +
                        "${wehrt4[index]} ${currentLagerBestandListe[index]} " +
                        "${wehrtX[index].split(" ")[6]} " +
                        "${wehrtX[index].split(" ")[7]} " +
                        "${wehrtX[index].split(" ")[8]}\n")
                index++

            }
            index = 0

        }

    }


}