import smile.plot.swing.{LinePlot, PlotPanel}
import util.{display, displayTitle}

import java.awt.GridLayout
import javax.swing.JFrame

object experimentation:
  def main(args: Array[String]): Unit =
    val kmeans: Kmeans = new Kmeans("resources/data/iris.data", "resources/data/irisAttributesNames.txt")
    val n = 9
    val moyennesDesQualites: Array[Double] = new Array[Double](n)

    (2 to 10).foreach(i => (1 to 20).foreach(j =>
      kmeans.clustering(i)
      println("kmeans qualite" + kmeans.qualite)
      moyennesDesQualites (i - 2) += kmeans.qualite)
    )

    displayTitle(s"Moyenne des qualités :")

    moyennesDesQualites.indices.foreach(i =>
      moyennesDesQualites(i) /= 20
      println (s"Moyenne des qualités (k = ${i + 2}) : ${moyennesDesQualites(i)}")
    )

    val j = 8
    val interDistances: Array[Double] = new Array[Double](j)

    (2 to 9).foreach(i =>
      kmeans.clustering(i);
      kmeans.computeInterDistance()
      interDistances(i - 2) = kmeans.getInterDistance
    )

    val points: Array[Array[Double]] = Array.ofDim(8, 2)

    points.indices.foreach(i =>
      points(i) = Array(i + 2, interDistances(i))
    )

    points.indices.foreach(i => println(points(i).mkString(", ")))

    val frame: JFrame = new JFrame("Optimal value of k")
    val line: PlotPanel = LinePlot.of(points).canvas().setTitle("Elbow method visualization").panel()
    frame.setLayout(new GridLayout())
    frame.add(line)
    frame.setSize(1700, 750)
    frame.revalidate()
    frame.setVisible(true)
