object testKmeans:
  def main(args: Array[String]): Unit =
    val kmeans = new Kmeans("ressources/data/iris.data", "ressources/data/irisAttributesNames.txt")

    kmeans.clustering(3)

    kmeans.getLabels.foreach(println)