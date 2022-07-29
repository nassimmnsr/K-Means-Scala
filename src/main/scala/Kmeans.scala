import scala.collection.mutable.ArrayBuffer
import scala.language.postfixOps


class Kmeans(fichierDonnees: String, fichierAttributs: String) {

  private val data: Data = new Data(fichierDonnees, fichierAttributs)
  private val exemples: Array[Exemple] = data.getNormalizedData
  private var clusters: Array[Cluster] = _

  private val dNormalizedData = data.getNormalizedData
  private val dClasses = data.getNbClasses
  private val dData = data.getData
  private var stop: Boolean = false
  private var previousCentroid : Array[Individu] = _


  def getClusters: Array[Cluster] = this.clusters

  def clustering(k: Int): Unit =
    this.clusters = new Array[Cluster](k)

    println("\nDÉBUT DU CLUSTERING...\n")
    println("CHOIX DES CENTRES INITIAUX...\n")
    println(s"$k CLUSTERS :\n")

    for (i <- 0 until k)
      this.clusters(i) = new Cluster("cluster " + i, exemples, this.data.nbAttributes)
      println(this.clusters(i))

    var iteration: Int = 0
    var listDistance = new ArrayBuffer[Double]
    var distanceMin: Double = 0.0


    while (stop == false) {
      previousCentroid = this.clusters.map(_.centroid.copy)
      //previousCentroid.foreach(i => println(i))

      println(s"\nIteration $iteration")

      this.clusters.foreach(_.empty)


      // Affectation de chaque données aux centres initiaux les plus proche
      for (j <- this.exemples.indices)
        listDistance.clear()
        for (i <- this.clusters.indices)
          listDistance.append(this.clusters(i).centroid.distance(this.exemples(j)))

        //println(listDistance)
        distanceMin = listDistance.min
        for (i <- this.clusters.indices)
          if (listDistance(i) == distanceMin)
            this.clusters(i).add(j)

      this.clusters = this.clusters.filter(_.size > 0)

      // MAJ des centres
      this.clusters.foreach(cluster =>
        cluster.computeCentroid
        cluster.computeClassCluster
        cluster.computeClusterError
        cluster.computeIntraDistance
      )

      for (i <- this.clusters.indices)
        if this.previousCentroid(i).distance(this.clusters(i).centroid) == 0.0 || this.previousCentroid(i).distance(this.clusters(i).centroid).isNaN then stop = true

      for (i <- this.clusters.indices)
        println(this.clusters(i))

      iteration += 1
    }

  def displayAllData: Unit = this.data.displayAllData()

  def displayClasses: Unit = this.data.displayClasses()

  def displayData: Unit = this.data.displayData()

  def displayNormalizedData: Unit = this.data.displayNormalizedData()

  def displayStats: Unit = this.data.displayStats()
}