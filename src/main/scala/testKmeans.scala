object testKmeans {
  def main(args : Array[String]) : Unit =
  {

    val kmeans = new Kmeans("ressources/iris.data", "ressources/irisAttributesNames.txt")

    //Test méthode display

    //kmeans.displayAllData
    //kmeans.displayClasses
    //kmeans.displayNormalizedData
    //kmeans.displayData
    //kmeans.displayStats

    kmeans.clustering(3)
    // println(kmeans.getClusters)


  }
}
