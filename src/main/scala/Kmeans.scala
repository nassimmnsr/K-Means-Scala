import scala.collection.mutable.ArrayBuffer

class Kmeans(fichierDonnees: String, fichierAttributs: String) {

  private val data: Data = new Data(fichierDonnees, fichierAttributs)
  private val exemples: Array[Exemple] = data.getNormalizedData
  private var clusters: Array[Cluster] = _

  private val dNormalizedData = data.getNormalizedData
  private val dClasses = data.getNbClasses
  private val dData = data.getData
  private var stop: Boolean = false


  private var qualiteClustering: Double = _
  private var interDistance: Double = _


 // def getClusters: Array[Cluster] = this.clusters

  def clustering(k: Int): Unit =
    this.clusters = new Array[Cluster](k)

    println("\nDÉBUT DU CLUSTERING...\n")
    println(s"$k CLUSTERS :\n")

    for (i <- 0 until k) do
      this.clusters(i) = new Cluster("cluster " + i, exemples, this.data.nbAttributes)
      println(this.clusters(i))

    var iteration: Int = 0
    var listDistance = new ArrayBuffer[Double]
    var distanceMin: Double = 0.0


    while (stop == false) {

      println(s"\nIteration $iteration")
      val previousCentroid: Array[Individu] = this.clusters.map(_.centroid.copy)
      this.clusters.foreach(_.empty)

      // Affectation de chaque données aux centres initiaux les plus proche
      for (j <- 0 until this.exemples.length)
        listDistance.clear()
        for (i <- 0 until k)
          listDistance.append(this.clusters(i).centroid.distance(this.exemples(j)))

        //println(listDistance)
        distanceMin = listDistance.min
        for (i <- 0 until k)
          if (listDistance(i) == distanceMin)
            this.clusters(i).add(j)


      // MAJ des centres
      this.clusters.foreach(cluster =>
          cluster.computeCentroid;
          (0 until cluster.centroid.nbAttributes).foreach(i =>
            if cluster.centroid.get(i).isNaN then
              print("AAAAAAAAAAAAAAAAAAAAAAAAAAAA    ")
                println(cluster.centroid.get(i))
                Thread.sleep(40000))
          cluster.computeClassCluster;
          cluster.computeClusterError;
          cluster.computeIntraDistance;
      )


      if previousCentroid.zipWithIndex.forall((centroid, i) => centroid.distance(this.clusters(i).centroid) == 0.0) then stop = true

      //this.clusters.indices.foreach(i => if clusters(i).intraDistance.isInfinite then this.clusters(i) = null)

      for (i <- 0 until k)
        println(this.clusters(i))

      iteration += 1
      }

    println("Fin du Clustering")
    this.computeQuality()
    println(s"Qualite du K-Mims: $qualiteClustering")
    println("Erreurs des clusters")
    this.clusters.indices.foreach(i => println(s"Cluster $i:\terror: ${this.clusters(i).erreur}\tnumber of examples: ${this.clusters(i).size}"))


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


}