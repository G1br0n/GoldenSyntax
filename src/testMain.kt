import produktClass.Produkt
import java.io.File

fun main() {
    val testData: File = File("src/Data/ArtikelListen/fridgesListe")

    //TODO: returnList (geben generierte Listen ausEinmal Einmal nicht gesplittete liste und je-walls gesplittete auf ID NAME PRICE ART LAGERS_BESTAND listen wieder)
    //Produkt(testData).returnProduktList()

    //Produkt(testData).returnIdProduktList()
    //Produkt(testData).returnNameProduktList()
    //Produkt(testData).returnPriceProduktList()
    //Produkt(testData).returnArtProduktList()
    //Produkt(testData).returnLagerBestandList()


    //TODO: returnSortedBy" "Listen Methoden (Geben nur Ids zurück in der richtige Reihenfolge)
    //Produkt(testData).returnSortedByIdProduktList().forEach { println(it) }
    //Produkt(testData).returnSortedByNameProduktList().forEach { println(it) }
    //Produkt(testData).returnSortedByPriceProduktList().forEach { println(it) }
    //Produkt(testData).returnSortedByLagerBestandList().forEach { println(it) }


    //TODO: printSortedBy" "Listen Methoden
    //Produkt(testData).printSortedByIdProduktList()
    //Produkt(testData).printSortedByNameProduktList()
    //Produkt(testData).printSortedByPriceProduktList()
    Produkt(testData).printSortedByLagerBestandList()

}
