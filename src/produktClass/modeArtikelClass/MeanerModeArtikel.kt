package produktClass


class MeanerModeArtikel(id: Int,name: String,price: Double,art: String): ModeArtikel(id,name,price,art) {
    var stofArt: MutableList<String> = mutableListOf("Seide","Leder","Lukra","Wolle")

}