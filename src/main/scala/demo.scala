import util._
import smile.plot.swing.LinePlot

import javax.swing.{JFrame, JPanel}

object demo:
  def main(args: Array[String]): Unit = {
    util.displayTitle("K = 3")
    val kMeans: Kmeans = new Kmeans("resources/data/iris.data", "resources/data/irisAttributesNames.txt")
    kMeans.clusteringWithViz(3)
  }