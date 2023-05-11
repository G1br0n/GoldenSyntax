package produktClass.elektronikArtikelClass

import produktClass.Produkt
import java.io.File

open class ElektronikArtikel(dateFile: File): Produkt(dateFile) {

    val garantieListe: MutableList <String> = mutableListOf()

    val garantieZeitListe: MutableList<Int> = mutableListOf()
    val garantieBooleansListe: MutableList<Boolean> = mutableListOf()

    // TODO: zeit berechenen

    init {
        header = listOf ("ID", "Name", "Price", "Art", "Artikel im Lager","Garantie Zeit", "Garantie Booleans")
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
    fun returnSortedGarantieZeit(): MutableList<Any> {
        val produktDetailsListe = idProduktList.zip(returnGarantieZeit())
        val produktIdReturnListe = mutableListOf<Any>()
        for (id in produktDetailsListe.sortedBy() { it.second } ){
            produktIdReturnListe.add(id.first)
        }
        return produktIdReturnListe
    }
    fun printGarantieZeitInfo(){


        var garantieZeitIndex = searchAndPrintProdukt()

        if (returnGarantieBooleans()[garantieZeitIndex]) {
            println("| Die garantie Zeit für das produkt beträgt ${returnGarantieZeit()[garantieZeitIndex]} Jahre")
        } else {
            println("| Das ausgewählte produkt hat keine garantie zeit ")
        }
    }

    fun printTabelleGarantieZeitList(){
        val sortedListe = returnIdProduktList()
        val listList: MutableList<List<String>> = mutableListOf()

        val idProduktListInt = mutableListOf<String>()
        val nameProduktListInt = mutableListOf<String>()
        val priceProduktListInt = mutableListOf<String>()
        val artProduktListInt = mutableListOf<String>()
        val lagerBestandListInt = mutableListOf<String>()
        val garantieZeitListeInt = mutableListOf<String>()
        val garantieBooleansListeInt = mutableListOf<String>()

        for (i in sortedListe) {
            val index = idProduktList.indexOf(i.toString().toInt())

            idProduktListInt.add(idProduktList[index].toString())
            nameProduktListInt.add(nameProduktList[index])
            priceProduktListInt.add(priceProduktList[index].toString())
            artProduktListInt.add(artProduktList[index])
            lagerBestandListInt.add(lagerBestandList[index].toString())
            garantieZeitListeInt.add(garantieZeitListe[index].toString())
            garantieBooleansListeInt.add(garantieBooleansListe[index].toString())
        }

        listList.add(idProduktListInt)
        listList.add(nameProduktListInt)
        listList.add(priceProduktListInt)
        listList.add(artProduktListInt)
        listList.add(lagerBestandListInt)
        listList.add(garantieZeitListeInt)
        listList.add(garantieBooleansListeInt)

        listList.toList()
        printTable(header,listList)

    }

    fun printSortedTabelleGarantieZeitList(){
        val sortedListe = returnSortedGarantieZeit()
        val listList: MutableList<List<String>> = mutableListOf()

        val idProduktListInt = mutableListOf<String>()
        val nameProduktListInt = mutableListOf<String>()
        val priceProduktListInt = mutableListOf<String>()
        val artProduktListInt = mutableListOf<String>()
        val lagerBestandListInt = mutableListOf<String>()
        val garantieZeitListeInt = mutableListOf<String>()
        val garantieBooleansListeInt = mutableListOf<String>()

        for (i in sortedListe) {
            val index = idProduktList.indexOf(i.toString().toInt())

            idProduktListInt.add(idProduktList[index].toString())
            nameProduktListInt.add(nameProduktList[index])
            priceProduktListInt.add(priceProduktList[index].toString())
            artProduktListInt.add(artProduktList[index])
            lagerBestandListInt.add(lagerBestandList[index].toString())
            garantieZeitListeInt.add(garantieZeitListe[index].toString())
            garantieBooleansListeInt.add(garantieBooleansListe[index].toString())
        }

        listList.add(idProduktListInt)
        listList.add(nameProduktListInt)
        listList.add(priceProduktListInt)
        listList.add(artProduktListInt)
        listList.add(lagerBestandListInt)
        listList.add(garantieZeitListeInt)
        listList.add(garantieBooleansListeInt)

        listList.toList()
        printTable(header,listList)

    }

}


