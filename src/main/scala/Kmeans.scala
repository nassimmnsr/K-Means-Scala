import scala.collection.mutable.ArrayBuffer
import scala.language.postfixOps


class Kmeans(fichierDonnees: String, fichierAttributs: String) {

  private val data: Data = new Data(fichierDonnees, fichierAttributs)
  private val exemples: Array[Exemple] = data.getNormalizedData
  private var clusters: Array[Cluster] = _

  private val dNormalizedData = data.getNormalizedData
  private val dClasses = data.getNbClasses
  private val dData = data.getData

  private var qualiteClustering: Double = _
  private var interDistance: Double = _
  private var k: Int = _


  def getClusters: Array[Cluster] = this.clusters

  def clustering(k: Int): Unit =
    var stop: Boolean = false
    this.clusters = new Array[Cluster](k)

    println("\nDÉBUT DU CLUSTERING...\n")
    println("CHOIX DES CENTRES INITIAUX...\n")
    println(s"$k CLUSTERS :\n")

    for (i <- 0 until k)
      this.clusters(i) = new Cluster("cluster " + i, this.data.getNormalizedData, this.data.nbAttributes)
      println(this.clusters(i))

    var iteration: Int = 0
    val listDistance = new ArrayBuffer[Double]
    var distanceMin: Double = 0.0


    while !stop do

      val previousCentroid: Array[Individu] = this.clusters.map(_.centroid.copy)
      //previousCentroid.foreach(i => println(i))

      println(s"\nIteration $iteration")

      this.clusters.foreach(_.empty)

      // Affectation de chaque données aux centres initiaux les plus proche
      for (j <- 0 until this.exemples.length)
        listDistance.clear()
        for (i <- 0 until this.clusters.length)
          listDistance.append(this.clusters(i).centroid.distance(this.exemples(j)))

        //println(listDistance)
        distanceMin = listDistance.min
        for (i <- 0 until this.clusters.length)
          if (listDistance(i) == distanceMin)
            this.clusters(i).add(j)

      //Supprime les clusters vides
      this.clusters = this.clusters.filter(_.size > 0)

      // MAJ des centres
      for i <- this.clusters.indices do
        this.clusters(i).computeCentroid
        this.clusters(i).computeClassCluster
        this.clusters(i).computeClusterError
        this.clusters(i).computeIntraDistance
        if previousCentroid(i).distance(this.clusters(i).centroid) == 0.0 then stop = true
        println(this.clusters(i))

      iteration += 1

    println("\nFin du Clustering")
    this.computeQuality()
    println("Qualite du K-Means: " + this.qualiteClustering)
    println("Erreurs des clusters :")
    for (i <- 0 until this.clusters.length)
      println("Cluster " + i + ", erreur : " + this.clusters(i).erreur + ", nombre de données :" + this.clusters(i).size)


  def displayAllData: Unit = this.data.displayAllData()

  def displayClasses: Unit = this.data.displayClasses()

  def displayData: Unit = this.data.displayData()

  def displayNormalizedData: Unit = this.data.displayNormalizedData()

  def displayStats: Unit = this.data.displayStats()

  private def qualite: Double = this.qualiteClustering


  private def computeQuality(): Unit =
    this.computeInterDistance()
    this.clusters.foreach(_.computeIntraDistance)
    val intraDistance: Double = this.clusters.map(_.intraDistance).sum / this.clusters.length.toDouble
    this.qualiteClustering = this.interDistance / (this.clusters.map(_.intraDistance).sum / this.clusters.length.toDouble)
    if this.qualiteClustering.isNaN then
      println("Erreur de calcul de la qualite")
      println(s"Interdistance: $interDistance")
      println(s"IntraDistance: $intraDistance")


  private def computeInterDistance(): Unit =
    val k = this.clusters.length
    this.interDistance = 0.0
    //    for i <- 0 until k - 1 do
    //      for j <- i + 1 until k do
    //        this.interDistance += this.clusters(i).centroid.distance(this.clusters(j).centroid)
    (0 until k - 1).foreach(i => (i + 1 until k).foreach(j => this.interDistance += this.clusters(i).centroid.distance(this.clusters(j).centroid)))
    this.interDistance /= (k * (k - 1) / 2)

}