import scala.collection.mutable.ArrayBuffer
import scala.math
import scala.util.Random

class Cluster(cName: String, val donnees: Array[Exemple], val _nbAttributes: Int):
  private val exemplesNums: ArrayBuffer[Int] = new ArrayBuffer[Int]()
  private var clusterCentroid: Individu = Individu.generateRandomIndividu(_nbAttributes, Random)
  private val clusterName = cName
  private var clusterIntraDistance: Double = _
  private var clusterError: Double = _
  private val clusterClassName: String = ""
  private var clusterClassNumber: Int = -1

  def add(num: Int): Unit =
    this.exemplesNums.append(num)

  def centroid: Individu = this.clusterCentroid

  def centroid_=(c: Individu): Unit =
    this.clusterCentroid = c

  def className: String = this.clusterClassName

  def classNumber: Int = this.clusterClassNumber

  def computeCentroid(): Unit =
    if this.size != 0 then
      val sumAttributes: Array[Double] = new Array[Double](_nbAttributes)
      this.exemples.foreach(exemple => (0 until exemple.nbAttributes).foreach(i => sumAttributes(i) += exemple.get(i)))
      (0 until _nbAttributes).foreach(i => this.clusterCentroid.set(i, sumAttributes(i) / this.size))

  def computeClassCluster(): Unit =
    if this.size > 0 then this.clusterClassNumber = this.exemples.groupBy(_.classNumber).maxBy(_._2.size)._1

  def computeClusterError(): Unit =
    this.clusterError = (this.exemples.filterNot(_.classNumber == this.classNumber).size.toDouble / this.size) * 100

  def computeIntraDistance(): Unit =
    this.clusterIntraDistance = this.exemples.map(exemple => math.pow(exemple.distance(this.clusterCentroid), 2)).sum / this.size.toDouble
    if clusterIntraDistance.isNaN then
      println("clusterIntraDistance is NaN")
      println(s"${this.exemples.map(exemple => math.pow(exemple.distance(this.clusterCentroid), 2)).sum} / ${this.size.toDouble} = ${this.clusterIntraDistance}")
//      Thread.sleep(4000)

  def empty(): Unit = this.exemplesNums.clear()

  def erreur: Double = this.clusterError

  def get(i: Int): Int = this.exemplesNums(i)

  def intraDistance: Double = this.clusterIntraDistance

  def name: String = this.clusterName

  def nbAttributs: Int = this._nbAttributes

  def size: Int = this.exemplesNums.size

  private def exemples: ArrayBuffer[Exemple] = this.exemplesNums.map(this.donnees(_))

  override def toString: String =
    s"Cluster(clusterName: ${this.clusterName}, _nbAttributes: ${this._nbAttributes}, exemplesNums: " +
      s"${this.exemplesNums}, clusterCentroid: ${this.clusterCentroid}, clusterIntraDistance: " +
      s"${this.clusterIntraDistance}, clusterError: ${this.clusterError}, clusterClassName: TEMPORARILY_UNAVAILABLE, " +
      s"clusterClassNumber: ${this.clusterClassNumber})"