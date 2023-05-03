package produktClass.elektronikArtikelClass

class TvArtikel(id: Int,name: String,price: Double,art: String): ElektronikArtikel(id,name,price,art) {
    var sizeZoll: MutableList<Int> = mutableListOf()

}