package produktClass.elektronikArtikelClass

import produktClass.Produkt
import java.io.File

open class ElektronikArtikel(dateFile: File): Produkt(dateFile) {

    private val garantieListe: MutableList <String> = mutableListOf()

    private val garantieZeitListe: MutableList<Int> = mutableListOf()
    private val garantieBooleansListe: MutableList<Boolean> = mutableListOf()

    // TODO: zeit berechenen

    init {
        dateFile.forEachLine { garantieListe.add(it)}

        for (i in garantieListe.indices) {

            this.garantieZeitListe.add(garantieListe[i].split(" ")[6].toInt())
            this.garantieBooleansListe.add(garantieListe[i].split(" ")[7].toBoolean())
        }
    }

    fun returnGarantieZeit(): MutableList<Int> {
        return garantieZeitListe
    }
    fun returnGarantieBooleans(): MutableList<Boolean> {
        return garantieBooleansListe
    }

    fun printGarantieZeitInfo(){


        var garantieZeitIndex = searchAndPrintProdukt()

        if (returnGarantieBooleans()[garantieZeitIndex]) {
            println("| Die garantie Zeit für das produkt beträgt ${returnGarantieZeit()[garantieZeitIndex]} Jahre")
        } else {
            println("| Das ausgewählte produkt hat keine garantie zeit ")
        }



    }
}