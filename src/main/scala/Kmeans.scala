import scala.collection.mutable.ArrayBuffer

class Kmeans(fichierDonnees: String, fichierAttributs: String) {
  private val data: Data = new Data(fichierDonnees, fichierAttributs)
  private val exemples: Array[Exemple] = data.getNormalizedData
  private var clusters: Array[Cluster] = _
  private val dNormalizedData = data.getNormalizedData
  private val dClasses = data.getNbClasses
  private val dData = data.getData
  private var stop: Boolean = false
  private var previousCentroid : Array[Individu] = _

  private var qualiteClustering: Double = _
  private var interDistance: Double = _


  //def getClusters: Array[Cluster] = this.clusters

  def clustering(k: Int): Unit =
    this.clusters = new Array[Cluster](k)

    println("\nDÉBUT DU CLUSTERING...\n")
    println("CHOIX DES CENTRES INITIAUX...\n")
    println(s"$k CLUSTERS :\n")

    for (i <- 0 until k)
      this.clusters(i) = new Cluster("cluster " + i, exemples, this.data.nbAttributes)
      println(this.clusters(i))


    while (stop == false) {
      previousCentroid = this.clusters.map(_.centroid.copy)
      //previousCentroid.foreach(i => println(i))

      println(s"\nIteration $iteration")

      this.clusters.foreach(_.empty)
      for (i <- 0 until k)

      // Affectation de chaque données aux centres initiaux les plus proche
        for (j <- 0 until this.exemples.length)

        // MAJ des centres
        this.clusters.foreach(cluster =>
          cluster.computeCentroid
            cluster.computeClassCluster
          cluster.computeClusterError
          cluster.computeIntraDistance
        )

        previousCentroid.indices.foreach(i =>
          if this.previousCentroid(i).distance(this.clusters(i).centroid) == 0.0 || this.previousCentroid(i).distance(this.clusters(i).centroid).isNaN then stop = true)

        for (i <- 0 until k)
          println(this.clusters(i))

        iteration += 1
      }

      def displayAllData: Unit = this.data.displayAllData()

      def displayClasses: Unit = this.data.displayClasses()

      def displayData: Unit = this.data.displayData()

      def displayNormalizedData: Unit = this.data.displayNormalizedData()

      def displayStats: Unit = this.data.displayStats()

      private def qualite: Double = this.qualiteClustering

      private def computeQuality(): Unit =
        this.computeInterDistance()
      this.qualiteClustering = this.interDistance / (this.clusters.map(_.intraDistance).sum / this.clusters.length.toDouble)

      private def computeInterDistance(): Unit =
      val k = this.clusters.length

      this.interDistance = 0.0
      for i <- 0 until k - 1 do
        for j <- i + 1 until k do
          this.interDistance += this.clusters(i).centroid.distance(this.clusters(j).centroid)

      //    (0 until k - 1).foreach(i => (i + 1 until k).foreach(j => this.interDistance += this.clusters(i).centroid.distance(this.clusters(j).centroid)))
      this.interDistance /= (k * (k - 1) / 2)

