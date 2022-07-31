import util.{display, displayTitle}

object experimentation:
  def main(args: Array[String]): Unit =
    val kmeans: Kmeans = new Kmeans("ressources/data/iris.data", "ressources/data/irisAttributesNames.txt")
    val n = 9
    val moyennesDesQualites: Array[Double] = new Array[Double](n)

    (2 to 10).foreach(i => (1 to 20).foreach(j =>
      kmeans.clustering(i)
      moyennesDesQualites (i - 2) += kmeans.qualite)
    )

    displayTitle(s"Moyenne des qualités :")

    moyennesDesQualites.indices.foreach(i =>
      moyennesDesQualites(i) /= 20
      println (s"Moyenne des qualités (k = ${i + 2}) : ${moyennesDesQualites(i)}")
    )
