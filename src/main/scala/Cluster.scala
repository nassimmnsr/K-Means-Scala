import scala.collection.mutable.ArrayBuffer
import scala.math.pow

class Cluster(cName: String, val donnees: Array[Exemple], val _nbAttributes: Int):
  private val exemplesNums: ArrayBuffer[Int] = new ArrayBuffer[Int]()
  private var clusterCentroid: Individu = new Individu(_nbAttributes)
  private val clusterName = cName

  def add(num: Int): Unit =
    this.exemplesNums.append(num)

  def centroid: Individu = this.clusterCentroid

  def computeCentroid(): Unit =
    val sumAttributes: Array[Double] = new Array[Double](_nbAttributes)

    this.exemples.foreach(exemple =>
      (0 until _nbAttributes).foreach(j => sumAttributes(j) += exemple.get(j))
    )

    (0 until _nbAttributes).foreach(i => this.clusterCentroid.set(i, sumAttributes(i) / this.size))

  def centroid_=(c: Individu): Unit =
    this.clusterCentroid = c

  def className: String =
    this.exemples.maxBy(_.classNumber).classNumber.toString

  def erreur: Double = (this.exemples.filterNot(_.classNumber == this.className).size / this.size) * 100


  def get(i: Int): Int = this.exemplesNums(i)

  def intraDistance: Double = this.exemples.map(exemple => pow(exemple.distance(this.clusterCentroid), 2)).sum / this.size

  def name: String = this.clusterName

  def nbAttributs: Int = this._nbAttributes

  def size: Int = this.exemplesNums.size

  private def exemples: ArrayBuffer[Exemple] = this.exemplesNums.map(this.donnees(_))

  override def toString: String =
    s"Cluster(name: ${this.name}, _nbAttributes: ${this._nbAttributes}, exemplesNums: ${this.exemplesNums}, " +
      s"clusterCentroid: ${this.clusterCentroid})"
