package produktClass.elektronikArtikelClass

import java.io.File

class TvArtikel(dateFile: File): ElektronikArtikel(dateFile) {
    var zolListe: MutableList<Int> = mutableListOf()

    init {
        header = listOf("ID", "Name", "Price", "Art", "Artikel im Lager", "Garantie Zeit", "Grösse in Zoll")
        dateFile.forEachLine { zolListe.add(it.split(" ")[8].toInt()) }
    }

    fun returnZolListe(): MutableList<Int> {
        return zolListe
    }

    fun returnSortedByZolListe(): MutableList<Any> {
        val produktDetailsListe = idProduktList.zip(returnZolListe())
        val produktIdReturnListe = mutableListOf<Any>()
        for (id in produktDetailsListe.sortedBy() { it.second }) {
            produktIdReturnListe.add(id.first)
        }
        return produktIdReturnListe
    }


    fun printKompletTvListe() {
        println("\nTv Artikel Liste:")
        val sortedListe = returnIdProduktList()
        val listList: MutableList<List<String>> = mutableListOf()

        val idProduktListInt = mutableListOf<String>()
        val nameProduktListInt = mutableListOf<String>()
        val priceProduktListInt = mutableListOf<String>()
        val artProduktListInt = mutableListOf<String>()
        val lagerBestandListInt = mutableListOf<String>()
        val garantieZeitListeInt = mutableListOf<String>()
        val zolListeInt = mutableListOf<String>()

        for (i in sortedListe) {
            val index = idProduktList.indexOf(i.toString().toInt())

            idProduktListInt.add(idProduktList[index].toString())
            nameProduktListInt.add(nameProduktList[index])
            priceProduktListInt.add(priceProduktList[index].toString())
            artProduktListInt.add(artProduktList[index])
            lagerBestandListInt.add(lagerBestandList[index].toString())
            garantieZeitListeInt.add(garantieZeitListe[index].toString())
            zolListeInt.add(zolListe[index].toString())
        }

        listList.add(idProduktListInt)
        listList.add(nameProduktListInt)
        listList.add(priceProduktListInt)
        listList.add(artProduktListInt)
        listList.add(lagerBestandListInt)
        listList.add(garantieZeitListeInt)
        listList.add(zolListeInt)

        listList.toList()
        printTable(header, listList)
    }

    fun printSortedByZolKompletTvListe() {
        println("\nSortiere Liste nach Zol:")
        val sortedListe = returnSortedByZolListe()
        val listList: MutableList<List<String>> = mutableListOf()

        val idProduktListInt = mutableListOf<String>()
        val nameProduktListInt = mutableListOf<String>()
        val priceProduktListInt = mutableListOf<String>()
        val artProduktListInt = mutableListOf<String>()
        val lagerBestandListInt = mutableListOf<String>()
        val garantieZeitListeInt = mutableListOf<String>()
        val zolListeInt = mutableListOf<String>()

        for (i in sortedListe) {
            val index = idProduktList.indexOf(i.toString().toInt())

            idProduktListInt.add(idProduktList[index].toString())
            nameProduktListInt.add(nameProduktList[index])
            priceProduktListInt.add(priceProduktList[index].toString())
            artProduktListInt.add(artProduktList[index])
            lagerBestandListInt.add(lagerBestandList[index].toString())
            garantieZeitListeInt.add(garantieZeitListe[index].toString())
            zolListeInt.add(zolListe[index].toString())
        }

        listList.add(idProduktListInt)
        listList.add(nameProduktListInt)
        listList.add(priceProduktListInt)
        listList.add(artProduktListInt)
        listList.add(lagerBestandListInt)
        listList.add(garantieZeitListeInt)
        listList.add(zolListeInt)

        listList.toList()
        printTable(header,listList)
    }

}





