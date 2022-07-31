import scala.collection.mutable.ArrayBuffer
import scala.util.Random


class Cluster(val clusterName: String,  val donnees: Array[Exemple], val _nbAttributes: Int) :

   //Variables

   private val clusterDataNum: ArrayBuffer[Int] = new ArrayBuffer[Int]()
   private var clusterCentroid: Individu = Individu.generateRandomIndividu(_nbAttributes, Random)
   private var clusterClassName: String = ""
   private var clusterClassNumber: Int = _
   private var clusterError: Double = _
   private var clusterIntraDistance: Double = _

   //Get

   def centroid: Individu = this.clusterCentroid

   def className: String =
      //method specific to this clustering
      if this.clusterClassNumber == 0 then this.clusterClassName = "Iris-setosa"
      else if this.clusterClassNumber == 0 then this.clusterClassName = "Iris-versicolor"
      else this.clusterClassName = "Iris-virginica"
      this.clusterClassName


   def classNumber: Int = this.clusterClassNumber

   def erreur: Double = this.clusterError

   def intraDistance: Double = this.clusterIntraDistance

   def nameCluster: String = this.clusterName

   def nbAttributs: Int = this._nbAttributes

   def get(i: Int): Int = this.clusterDataNum(i)

   //Methods

   def add(num: Int): Unit = this.clusterDataNum.append(num)


   def centroid_=(c: Individu): Unit = this.clusterCentroid = c


   def computeCentroid: Unit =
      if this.size != 0 then
         val sumAttributes: Array[Double] = new Array[Double](_nbAttributes)
         this.donnees.foreach(exemple => (0 until exemple.nbAttributes).foreach(i => sumAttributes(i) += exemple.get(i)))
         (0 until _nbAttributes).foreach(i => this.clusterCentroid.set(i, sumAttributes(i) / this.size))


   def computeClusterError: Unit =
      this.clusterError = (this.donnees.filter(_.classNumber != this.classNumber).size.toDouble / this.size) * 100


   def computeIntraDistance: Unit =
      this.clusterIntraDistance = this.donnees.map(exemple => math.pow(exemple.distance(this.clusterCentroid), 2)).sum / this.size.toDouble
      if clusterIntraDistance.isNaN then
         println("clusterIntraDistance is NaN")
         println(s"${this.donnees.map(exemple => math.pow(exemple.distance(this.clusterCentroid), 2)).sum} / ${this.size.toDouble} = ${this.clusterIntraDistance}")

   def size : Int = this.clusterDataNum.length


   def computeClassCluster: Unit =
      this.clusterClassNumber = this.donnees.groupBy(_.classNumber).maxBy(_._2.size)._1


   override def toString: String =
      s"Cluster(clusterName: ${this.clusterName}, _nbAttributes: ${this._nbAttributes}, exemplesNums: " +
        s"${this.clusterDataNum}, clusterCentroid: ${this.clusterCentroid}, clusterIntraDistance: " +
        s"${this.clusterIntraDistance}, clusterError: ${this.clusterError}, clusterClassName: ${this.clusterClassName}, " +
        s"clusterClassNumber: ${this.clusterClassNumber})"


   def empty: Unit =
      clusterDataNum.clear()







