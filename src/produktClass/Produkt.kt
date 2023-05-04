package produktClass
import java.io.File


open class Produkt(private val dateFile: File) {

    private var produktList: MutableList<String> = mutableListOf()

    private var idProduktList: MutableList<Int> = mutableListOf()
    private var nameProduktList:MutableList<String> = mutableListOf()
    private var priceProduktList: MutableList<Double> = mutableListOf()
    private var artProduktList: MutableList<String> = mutableListOf()
    private var lagerBestandList: MutableList<Int> = mutableListOf()
    //TODO: private var kundenRezensionList: MutableList<String> = mutableListOf()

    //------Init Block erstellt listen aus dataFile---------------------------------------------------------------------

    init {
        this.dateFile.forEachLine { produktList.add(it) }

        for (i in produktList){

            this.idProduktList.add(i.split(" ")[0].toInt())
            this.nameProduktList.add(i.split(" ")[1] + " " + i.split(" ")[2])
            this.priceProduktList.add(i.split(" ")[3].toDouble())
            this.artProduktList.add(i.split(" ")[4])
            this.lagerBestandList.add(i.split(" ")[5].toInt())
            //TODO: this.kundenRezensionList.add(i.split(" ")[i.lastIndex])
        }
    }

    //------Return methoden mit Listen----------------------------------------------------------------------------------

    fun returnProduktList(): MutableList<String> {
        return produktList
    }

    fun returnIdProduktList(): MutableList<Int> {
        return idProduktList
    }

    fun returnNameProduktList(): MutableList<String> {
        return nameProduktList
    }

    fun returnPriceProduktList(): MutableList<Double> {
        return priceProduktList.toMutableList()
    }

    fun returnArtProduktList(): MutableList<String> {
        return artProduktList.toMutableList()
    }

    fun returnLagerBestandList(): MutableList<Int> {
        return lagerBestandList.toMutableList()
    }

    //------Return methoden mit sortierten Listen-----------------------------------------------------------------------

    fun returnSortedByIdProduktList(): MutableList<Int> {
        val produktDetailsListe = idProduktList.zip(nameProduktList)
        val produktIdReturnListe = mutableListOf<Int>()
        for (id in produktDetailsListe.sortedBy() { it.first } ){
            produktIdReturnListe.add(id.first)
        }
        return produktIdReturnListe
    }

    fun returnSortedByNameProduktList(): MutableList<Int> {
        val produktDetailsListe = nameProduktList.zip(idProduktList)
        val produktIdReturnListe = mutableListOf<Int>()
        for (id in produktDetailsListe.sortedBy() { it.first } ){
            produktIdReturnListe.add(id.second)
        }
        return produktIdReturnListe
    }

    fun returnSortedByPriceProduktList(): MutableList<Int> {
        val produktDetailsListe: List<Pair<Double,Int>> = priceProduktList.zip(idProduktList)
        val produktIdReturnListe = mutableListOf<Int>()

        for (id in produktDetailsListe.sortedBy() { it.first } ){
            produktIdReturnListe.add(id.second)
        }
        return produktIdReturnListe
    }

    fun returnSortedByLagerBestandList(): MutableList<Int> {
        val produktDetailsListe = lagerBestandList.zip(idProduktList)
        val produktIdReturnListe = mutableListOf<Int>()
        for (id in produktDetailsListe.sortedBy() { it.first } ){
            produktIdReturnListe.add(id.second)
        }
        return produktIdReturnListe
    }

    //------Print methoden mit sortierten Listen------------------------------------------------------------------------

    fun printSortedByIdProduktList() {
        val sortedListe = returnSortedByIdProduktList()

        println("\nSortiere Liste nach ID:")
        Thread.sleep(1000)
        for (i in sortedListe){
            val index = idProduktList.indexOf(i)
            println("${idProduktList[index]} ${nameProduktList[index]} ${priceProduktList[index]} ${artProduktList[index]}")
            Thread.sleep(500)
        }
    }

    fun printSortedByNameProduktList(){
        val sortedListe = returnSortedByNameProduktList()

        println("\nSortiere Liste nach Name:")
        Thread.sleep(1000)
        for (i in sortedListe){
            val index = idProduktList.indexOf(i)
            println("${idProduktList[index]} ${nameProduktList[index]} ${priceProduktList[index]} ${artProduktList[index]}")
            Thread.sleep(500)
        }
    }

    fun printSortedByPriceProduktList(){
        val sortedListe = returnSortedByPriceProduktList()

        println("\nSortiere Liste nach Preis:")
        Thread.sleep(1000)
        for (i in sortedListe){
            val index = idProduktList.indexOf(i)
            println("${idProduktList[index]} ${nameProduktList[index]} ${priceProduktList[index]} ${artProduktList[index]}")
            Thread.sleep(500)
        }

    }

    fun printSortedByLagerBestandList(){
        val sortedListe = returnSortedByLagerBestandList()
        var header: List<String> = listOf("ID", "Name", "Price", "Art", "Lagerbestand")


        var listList: MutableList<MutableList<String>> = mutableListOf()

        var idProduktListInt = mutableListOf<String>()
        var nameProduktListInt = mutableListOf<String>()
        var priceProduktListInt = mutableListOf<String>()
        var artProduktListInt = mutableListOf<String>()
        var lagerBestandListInt = mutableListOf<String>()

        println("\nSortiere Liste nach Lager Bestand:")
        Thread.sleep(1000)
        for (i in sortedListe){
            val index = idProduktList.indexOf(i)

            idProduktListInt.add(idProduktList[index].toString())
            nameProduktListInt.add(nameProduktList[index])
            priceProduktListInt.add(priceProduktList[index].toString())
            artProduktListInt.add(artProduktList[index])
            lagerBestandListInt.add(lagerBestandList[index].toString())

        }

        listList.add(idProduktListInt)
        listList.add(nameProduktListInt)
        listList.add(priceProduktListInt)
        listList.add(artProduktListInt)
        listList.add(lagerBestandListInt)

        printTable(header,listList)
    }


    fun printTable(headers: List<String>, values: List<List<String>>) {

        var idProduktListInt = mutableListOf<String>()
        var nameProduktListInt = mutableListOf<String>()
        var priceProduktListInt = mutableListOf<String>()
        var artProduktListInt = mutableListOf<String>()
        var lagerBestandListInt = mutableListOf<String>()

        // Berechnen der maximalen Breite für jede Spalte
        val columnWidths = IntArray(headers.size) { i -> headers[i].length }
        for (row in values) {
            for (col in row.indices) {
                if (row[col].length > columnWidths[col]) {
                    columnWidths[col] = row[col].length
                }
            }
        }

        // Drucken der Tabellenüberschrift
        for (i in headers.indices) {
            print("||   " +headers[i].padEnd(columnWidths[i] + 2))
        }
        println()
        Thread.sleep(1000)
        // Drucken der Tabelle
        for (row in values) {
            idProduktListInt.add(row[0].padEnd(columnWidths[0]))
            nameProduktListInt.add(row[1].padEnd(columnWidths[0]))
            priceProduktListInt.add(row[2].padEnd(columnWidths[0]))
            artProduktListInt.add(row[3].padEnd(columnWidths[0]))
            lagerBestandListInt.add(row[4].padEnd(columnWidths[0]))
            Thread.sleep(500)
            for (col in row.indices) {

                print("||   " + row[col].padEnd(columnWidths[col] + 2))
            }
            println()
        }
    }

    //TODO:------Save methoden------------------------------------------------------------------------------------------

}

