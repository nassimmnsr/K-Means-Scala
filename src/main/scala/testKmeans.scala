object testKmeans {
  def main(args : Array[String]) : Unit =
  {
    // on créé un objet donnees de type Data
    // qui collecte les données depuis les fichiers
    // iris.data et irisAttributes.names
    val donnees = new Data("ressources/iris.data", "ressources/irisAttributesNames.txt")

    donnees.displayAllData()
    println(" ")
    println(donnees.nbData)

  }
}
