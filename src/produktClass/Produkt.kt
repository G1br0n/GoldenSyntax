package produktClass
import java.io.File


open class Produkt(private val dateFile: File) {
    private val header: List<String> = listOf("ID", "Name", "Price", "Art", "Artikel im Lager")

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

    fun returnSortedByIdProduktList(): MutableList<Any> {
        val produktDetailsListe = idProduktList.zip(nameProduktList)
        val produktIdReturnListe = mutableListOf<Any>()
        for (id in produktDetailsListe.sortedBy() { it.first } ){
            produktIdReturnListe.add(id.first)
        }
        return produktIdReturnListe
    }

    fun returnSortedByNameProduktList(): MutableList<Any> {
        val produktDetailsListe = nameProduktList.zip(idProduktList)
        val produktIdReturnListe = mutableListOf<Any>()
        for (id in produktDetailsListe.sortedBy() { it.first } ){
            produktIdReturnListe.add(id.second)
        }
        return produktIdReturnListe
    }

    fun returnSortedByPriceProduktList(): MutableList<Any> {
        val produktDetailsListe: List<Pair<Double,Int>> = priceProduktList.zip(idProduktList)
        val produktIdReturnListe = mutableListOf<Any>()

        for (id in produktDetailsListe.sortedBy() { it.first } ){
            produktIdReturnListe.add(id.second)
        }
        return produktIdReturnListe
    }

    fun returnSortedByLagerBestandList(): MutableList<Any> {
        val produktDetailsListe = lagerBestandList.zip(idProduktList)
        val produktIdReturnListe = mutableListOf<Any>()
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

        val header: List<String> = this.header
        val listListIntern = returnListList(sortedListe)

        printTable(header,listListIntern)
    }

    fun printSortedByNameProduktList(){
        val sortedListe:MutableList<Any> = returnSortedByNameProduktList()

        println("\nSortiere Liste nach Name:")
        Thread.sleep(1000)

        val listListIntern = returnListList(sortedListe)

        printTable(header,listListIntern)
    }

    fun printSortedByPriceProduktList(){
        val sortedListe = returnSortedByPriceProduktList()

        println("\nSortiere Liste nach Preis:")
        Thread.sleep(1000)

        val header: List<String> = this.header

        val listListIntern = returnListList(sortedListe)

        printTable(header,listListIntern)

    }

    fun printSortedByLagerBestandList(){
        println("\nSortiere Liste nach Lager Bestand:")
        Thread.sleep(1000)

        val sortedListe = returnSortedByLagerBestandList()
        val header: List<String> = this.header

        val listListIntern = returnListList(sortedListe)

        printTable(header,listListIntern)
    }

    //------ab hier private methoden------------------------------------------------------------------------------------

    //Tabellen Drucker
    private fun printTable(headers: List<String>, values: List<List<String>>) {

        var druckLange = 20

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
            print("| " + headers[i].padEnd(columnWidths[i] + 3))
        }
        println("|")
        Thread.sleep(250)

        var index = 0
        // Drucken der Tabelle

        for ( i in values.indices) {
            for (j in headers.indices) {
                print("| " + values[j][i].padEnd(columnWidths[j] + 3))
            }
            println("|")
            Thread.sleep(250)
        }
    }

    private fun returnListList(sortedListe: MutableList<Any>):List<List<String>> {

        val listList: MutableList<List<String>> = mutableListOf()

        val idProduktListInt = mutableListOf<String>()
        val nameProduktListInt = mutableListOf<String>()
        val priceProduktListInt = mutableListOf<String>()
        val artProduktListInt = mutableListOf<String>()
        val lagerBestandListInt = mutableListOf<String>()


        for (i in sortedListe) {
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
        listList.toList()
        return listList
    }

}





