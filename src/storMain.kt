import userClass.User
import java.io.File
import kotlin.system.exitProcess

fun main() {
   do{
        val visitorDataFile: File = File("src/Data/UserListe/vesitorListe")
        val tvDataFile: File = File("src/Data/UserListe/")
        Thread.sleep(500)
        println("\n\nWillkommen im Online Shop \"GoldenSyntax\"\n")
        Thread.sleep(500)
        println("Menü: \n1 -> Einlogen \n2 -> Registrieren \n\n0 -> Online Store Verlassen")

        var input = readln()
        var test = false

       if (input == "1") {
           test = User(visitorDataFile).userLogin()
           if(test){
               storeMainScreen()
               continue
           }

       }

       try {
           if (input == "2") {
               User(visitorDataFile).userRegistrieren()
               continue
           }
       } catch (ex: Exception){
           println(ex.message)
           continue
       }

       if (input == "0") {
           Thread.sleep(500)
           println("Auf wiedersehen und bis bald")
           exitProcess(9)
       }

       else {
           Thread.sleep(500)
           println("Die eingabe wurde nicht verstanden!")
           continue
       }

   }while(true)
}

fun storeMainScreen(){




}