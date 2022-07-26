object testData {
  def main(args: Array[String]): Unit = {
    // on créé un objet donnees de type Data
    // qui collecte les données depuis les fichiers
    // iris.data et irisAttributes.names

    val donnees = new Data("ressources/data/iris.data", "ressources/data/irisAttributesNames.txt")
    donnees.displayNormalizedData()
//    println(donnees.getData(112))
  }
}