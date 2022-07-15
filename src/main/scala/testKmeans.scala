object testKmeans {
  def main(args : Array[String]) : Unit =
  {
    // on créé un objet donnees de type Data
    // qui collecte les données depuis les fichiers
    // iris.data et irisAttributes.names
    val donnees = new Data("ressources/iris.data", "ressources/irisAttributesNames.txt")

    val exemples = donnees.getNormalizedData

    val cluster = new Cluster("cluster 1", exemples, 4)


    //println(" ")
    //println(cluster.toString)
    println(" ")
    cluster.add(4)
    cluster.add(6)
    cluster.add(123)
    cluster.add(19)
    cluster.add(77)
    println("\nContenue du cluster" + cluster.toString)
    println("\nNombre d'éléments du cluster : " + cluster.size)
    println("\nNombre d'attributs des éléments du cluster : " + cluster.nbAttributes)

  }
}
