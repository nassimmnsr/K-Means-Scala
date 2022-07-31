object testVisualization:
  def main(args: Array[String]): Unit = {
    val kmeans: Kmeans = new Kmeans("ressources/data/iris.data", "ressources/data/irisAttributesNames.txt")
    kmeans.clusteringWithViz(3)
//    val visu = new Visualisation(kmeans, 3)
//    visu.display
  }