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

    init {
        this.dateFile.forEachLine { produktList.add(it) }

        for (i in produktList){
            this.idProduktList.add(i.split(" ")[0].toInt())
            this.nameProduktList.add(i.split(" ")[1] + " " + i.split(" ")[2])
            this.priceProduktList.add(i.split(" ")[3].toDouble())
            this.artProduktList.add(i.split(" ")[4])
            this.lagerBestandList.add(i.split(" ")[4].toInt())
            //TODO: this.kundenRezensionList.add(i.split(" ")[i.lastIndex])
        }

    }

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

    fun returnSortedByPriceProduktList(): MutableList<Int> {
        val produktDetailsListe = idProduktList.zip(nameProduktList)
        var produktReturnListe = mutableListOf<Int>()
        produktDetailsListe.sortedBy { it.second }
        for (id in produktDetailsListe ){
            produktReturnListe.add(id.first)
        }
        return produktReturnListe
    }







}

