class Kmeans(fichierDonnees: String, fichierAttributs: String):
  private val data: Data = new Data(fichierDonnees, fichierAttributs)
  private val normalizedData: Array[Exemple] = data.getNormalizedData
  private var clusters: Array[Cluster] = _
  private var qualiteClustering: Double = _
  private var interDistance: Double = _
  private var k: Int = _

  def clustering(k: Int): Unit =
    println("Clustering...")
    this.clusters = new Array[Cluster](k)

    (0 until k).foreach(i => this.clusters(i) = new Cluster(s"cluster$i", this.data.getNormalizedData, this.data.nbAttributes))

    var stop: Boolean = false
    var iteration: Int = 0
    while !stop do
      println(s"Iteration $iteration")
      val initialCentroids: Array[Individu] = this.clusters.map(_.centroid.copy)
      this.clusters.foreach(_.empty())
      this.normalizedData.indices.foreach(i => this.clusters.minBy(cluster => cluster.centroid.distance(normalizedData(i))).add(i))
      this.clusters.foreach(cluster =>
        cluster.computeCentroid()
        cluster.computeClassCluster()
        cluster.computeClusterError()
        cluster.computeIntraDistance()
      )
//      if iteration == 0 then
//        this.computeQuality()
//        println(s"Qualite du clustering: $qualiteClustering")

      iteration += 1
      if initialCentroids.zipWithIndex.forall((centroid, i) => centroid.distance(this.clusters(i).centroid) == 0.0 || centroid.distance(this.clusters(i).centroid).isNaN) then stop = true
//        println(s"Centroid $i: ${centroid.distance(this.clusters(i).centroid)}")

//      initialCentroids.indices.foreach(i =>

//        if initialCentroids(i).distance(this.clusters(i).centroid) < 1E-20 then stop = true else stop = false
//      )
    println("Fin du Clustering")
    this.computeQuality()
    println(s"Qualite du K-Mims: $qualiteClustering")
    println("Erreurs des clusters")
    this.clusters.indices.foreach(i => println(s"Cluster $i:\terror: ${this.clusters(i).erreur}\tnumber of examples: ${this.clusters(i).size}"))



  def displayAllData(): Unit = this.data.displayData()

  def displayClasses(): Unit = this.data.displayClasses()

  def displayData(): Unit = this.data.displayData()

  def displayNormalizedData(): Unit = this.data.displayNormalizedData()

  def displayStats(): Unit = this.data.displayStats()

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
