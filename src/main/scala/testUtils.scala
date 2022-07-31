object testUtils:
  def main(args: Array[String]): Unit = {
    val kmeans = new Kmeans("ressources/data/iris.data", "ressources/data/irisAttributesNames.txt")

    val visu = new Visualisation(kmeans, 3)
    visu.display
  }