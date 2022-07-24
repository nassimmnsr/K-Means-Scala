object testKmeans {
  def main(args : Array[String]) : Unit =
  {
    // on créé un objet donnees de type Data
    // qui collecte les données depuis les fichiers
    // iris.data et irisAttributes.names
    val donnees = new Data("ressources/iris.data", "ressources/irisAttributesNames.txt")

    val exemples = donnees.getNormalizedData
    val classesNames = donnees.getClassesNames

    val cluster = new Cluster("cluster 1", exemples, 4)


    //println(" ")
    //println(cluster.toString)
    println(" ")
    cluster.add(4)
    cluster.add(6)
    cluster.add(123)
    cluster.add(19)
    cluster.add(77)

    println(cluster.centroid)
    println(cluster.className)
    println(cluster.classNumber)
    println(cluster.clusterName)
    cluster.computeCentroid
    println(cluster)
    cluster.computeClusterError
    cluster.computeIntraDistance



    println(cluster)




  }
}
