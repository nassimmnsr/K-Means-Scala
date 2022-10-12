import util._

object testKmeans:
  def main(args: Array[String]): Unit =
    displayTitle("Tests Start")
    val kmeans = new Kmeans("resources/data/iris.data", "resources/data/irisAttributesNames.txt")

    display("clustering method test")
    kmeans.clustering(3)

    display("clusteringWithViz method test")
    kmeans.clusteringWithViz(3)

    display("displayAllData method test")
    kmeans.displayAllData()

    display("displayClasses method test")
    kmeans.displayClasses()

    display("displayData method test")
    kmeans.displayData()

    display("displayNormalizedData method test")
    kmeans.displayNormalizedData()

    display("displayStats method test")
    kmeans.displayStats()

    display("qualite method test")
    println(kmeans.qualite)

    display("computeQuality method test")
    kmeans.computeQuality()
    println(kmeans.qualite)

    display("computeInterDistance method test")
    kmeans.computeInterDistance()
    println(kmeans.getInterDistance)

    display("getLabels method test")
    println(kmeans.getLabels.mkString("Array(", ", ", ")"))

    display("getKnownLabels method test")
    println(kmeans.getKnownLabels.mkString("Array(", ", ", ")"))

    displayTitle("Tests End")