package produktClass.elektronikArtikelClass

import java.io.File

class FridgesArtikel(dateFile: File): ElektronikArtikel(dateFile) {

    var fridgesPrivatSizeListe: MutableList<Int> = mutableListOf()
    var fridgesFabrikSizeListe: MutableList<Int> = mutableListOf()



}