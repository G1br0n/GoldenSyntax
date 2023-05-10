package userClass

import produktClass.Produkt
import java.io.File

class Visitor(visitorDataFile: File) : User(visitorDataFile) {
    private var shoppingCart: MutableMap<Int, Int> = mutableMapOf()

    fun produktAddToShoppingCart(produktDataFile: File): MutableMap<Int, Int> {

        val produktIdList = Produkt(produktDataFile).returnIdProduktList()
        val produktNameListe = Produkt(produktDataFile).returnNameProduktList()
        val produktPriceListe = Produkt(produktDataFile).returnPriceProduktList()
        val produktLagerBestandListe = Produkt(produktDataFile).returnLagerBestandList()


        do {

            println("Bitte gebe die ID ein des produkt den du kaufen möchtest")

            val inputIdForBy = readln().toIntOrNull()
            if (inputIdForBy != null) {

                if (!produktIdList.contains(inputIdForBy.toInt())) {
                    shoppingCart[inputIdForBy] = 0
                }

                while (produktIdList.contains(inputIdForBy.toInt())) {

                    println("Was ist die Anzahl an Produkten die Sie kaufen möchten? \nBitte eine Zahl eingeben")
                    val inputProduktNumber = readln().toIntOrNull()
                    if (!shoppingCart.keys.contains(inputProduktNumber))
                        shoppingCart[inputIdForBy] = 0
                    if (inputProduktNumber != null) {
                        shoppingCart[inputIdForBy] = shoppingCart[inputIdForBy]!! + inputProduktNumber.toInt()
                        println(
                            "Sie haben $inputProduktNumber ${produktNameListe[produktIdList.indexOf(inputIdForBy.toInt())]} im wehrt von " +
                                    "${
                                        shoppingCart[inputIdForBy]!! * produktPriceListe[produktIdList.indexOf(
                                            inputIdForBy.toInt()
                                        )]
                                    }"
                        )
                        break
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

}