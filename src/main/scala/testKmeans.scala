import testUtils._

object testKmeans {
  def main(args : Array[String]) : Unit =
  {
    displayTitle("Tests Start")
    // on crée un objet kmeans de type Kmeans
    val kmeans = new Kmeans("ressources/iris.data", "ressources/irisAttributesNames.txt")
    

    // affiche l'ensemble des données (initiales et normalisées)
    displayTitle("displays all data (initial and normalized)")
    println(s"\ndisplays all data (initial and normalized) : ${kmeans.displayAllData}")

    // affiche les classes
    displayTitle("displays classes")
    println(s"\ndisplays classes : ${kmeans.displayClasses}")

    // affiche les données normalisées
    displayTitle("displays normalized data")
    println(s"\ndisplays normalized data : ${kmeans.displayNormalizedData}")

    // affiche les données initiales
    displayTitle("displays initial data")
    println(s"\ndisplays initial data : ${kmeans.displayData}")

    // affiche les statistifiques liées aux données
    displayTitle("displays statistics related to the data")
    println(s"\ndisplays statistics related to the data : ${kmeans.displayStats}")

    // effectue un k clustering des données
    displayTitle("performs a k clustering of the data")
    println(s"\nperforms a k clustering of the data : ${kmeans.clustering(3)}")

    displayTitle("Tests End")

  }
}
