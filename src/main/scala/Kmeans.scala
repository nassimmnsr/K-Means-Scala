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
      this.clusters = this.clusters.filter(_.size > 0)
      this.clusters.foreach(cluster =>
        cluster.computeCentroid();
//        (0 until cluster.centroid.nbAttributes).foreach(i =>
//        if cluster.centroid.get(i).isNaN then
//          print("AAAAAAAAAAAAAAAAAAAAAAAAAAAA    ")
//          println(cluster.centroid.get(i))
//          Thread.sleep(40000))
        cluster.computeClassCluster()
        cluster.computeClusterError()
        cluster.computeIntraDistance()
      )

      //      if iteration == 0 then
//        this.computeQuality()
//        println(s"Qualite du clustering: $qualiteClustering")

      iteration += 1
      if initialCentroids.zipWithIndex.forall((centroid, i) => centroid.distance(this.clusters(i).centroid) == 0.0) then stop = true

//        println(s"Centroid $i: ${centroid.distance(this.clusters(i).centroid)}")

//      initialCentroids.indices.foreach(i =>

//        if initialCentroids(i).distance(this.clusters(i).centroid) < 1E-20 then stop = true else stop = false
//      )
//    this.data.plotAttributesValues()
    println("Fin du Clustering")
    this.computeQuality()
    println(s"Qualite du K-Mims: ${this.qualiteClustering}")
    println("Erreurs des clusters")
    this.clusters.indices.foreach(i => println(s"Cluster $i:\terror: ${this.clusters(i).erreur}\t\t\t\tnumber of examples: ${this.clusters(i).size}"))



  def displayAllData(): Unit = this.data.displayData()

  def displayClasses(): Unit = this.data.displayClasses()

  def displayData(): Unit = this.data.displayData()

  def displayNormalizedData(): Unit = this.data.displayNormalizedData()

  def displayStats(): Unit = this.data.displayStats()

  def qualite: Double = this.qualiteClustering

  private def computeQuality(): Unit =
    this.computeInterDistance()
    this.clusters.foreach(_.computeIntraDistance())
    val intraDistance: Double = this.clusters.map(_.intraDistance).sum / this.clusters.length.toDouble
//    if intraDistance.isNaN then
//      println("IntraDistance is NaN")
//      println(s"${this.clusters.map(_.intraDistance).sum} / ${this.clusters.length.toDouble} = $intraDistance")
//      Thread.sleep(4000)
    this.qualiteClustering = this.interDistance / (this.clusters.map(_.intraDistance).sum / this.clusters.length.toDouble)
    if this.qualiteClustering.isNaN then
      println("Erreur de calcul de la qualite")
      println(s"Interdistance: $interDistance")
      println(s"IntraDistance: $intraDistance")
//      Thread.sleep(8000)

  def getData: Data = this.data

  private def computeInterDistance(): Unit =
    val k = this.clusters.length

    this.interDistance = 0.0
//    for i <- 0 until k - 1 do
//      for j <- i + 1 until k do
//        this.interDistance += this.clusters(i).centroid.distance(this.clusters(j).centroid)

    (0 until k - 1).foreach(i => (i + 1 until k).foreach(j => this.interDistance += this.clusters(i).centroid.distance(this.clusters(j).centroid)))
    this.interDistance /= (k * (k - 1) / 2)

  def getLabels: Array[Int] =
    val labels: Array[Int] = new Array[Int](this.normalizedData.length)
    this.clusters.foreach(cluster =>
      (0 until cluster.size).foreach(i =>
        val numExemple: Int = cluster.get(i)
        labels(numExemple) = cluster.classNumber
//      )
    ))

    labels
