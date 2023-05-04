import produktClass.Produkt
import java.io.File

fun main() {
    val testData: File = File("src/Data/ArtikelListen/fridgesListe")

    Produkt(testData).printSortedByNameProduktList()



}
