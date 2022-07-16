object testKmeans {
  def main(args : Array[String]) : Unit =
  {
    // on créé un objet donnees de type Data
    // qui collecte les données depuis les fichiers
    // iris.data et irisAttributes.names
    val donnees = new Data("ressources/iris.data", "ressources/irisAttributesNames.txt")

    val exemples = donnees.getNormalizedData
    val classesNames = donnees.getClassesNames

    val cluster = new Cluster("cluster 1", exemples, 4, 3)


    //println(" ")
    //println(cluster.toString)
    println(" ")
    cluster.add(4)
    cluster.add(6)
    cluster.add(123)
    cluster.add(19)
    cluster.add(77)
    println("\nInformations du cluster : \n" + cluster.toString)
    println("\nNombre d'éléments du cluster : " + cluster.size)
    println("\nNombre d'attributs des éléments du cluster : " + cluster.nbAttributes)
    println("\nNumero de l'Exemple situé au ième rang du Cluster : " + cluster.get(1))
    println("\nExemple situé au ième rang du Cluster : " + cluster.getClusterData(1))
    cluster.computeCentroid
    println("\nCentroid initial : " + cluster.getCentroid + ", Numéro du centroid initial : " + cluster.getCentroidNum)
    cluster.centroidUpd(123) //Modification du centroïd
    println("\nCentroid initial : " + cluster.getCentroid + ", Numéro du centroid initial : " + cluster.getCentroidNum)
    println("\nContenue Cluster : " + cluster.getClusterDataExemple())

    println("\nClasse Number : " + cluster.classNumber(123))
    println("\nClasse Number majoritaire : " + cluster.classNumber)

    println("\nClasse Name : " + cluster.className(123))
    println("\nClasse Name majoritaire : " + cluster.className)

    println("\nInertie du Cluster : " + cluster.intraDistance)
    //cluster.empty //Vider le cluster
    //println("\nCluster vidé sauf le centroid : " + cluster)
    println("\nCalcule le numero de classe majoritaire du cluster : " + cluster.computeClassCluster)







  }
}
