package produktClass.elektronikArtikelClass

class FridgesArtikel(id: Int,name: String,price: Double,art: String): ElektronikArtikel(id,name,price,art) {

    var fridgesPrivatSizeListe: MutableList<Int> = mutableListOf()
    var fridgesFabrikSizeListe: MutableList<Int> = mutableListOf()



}