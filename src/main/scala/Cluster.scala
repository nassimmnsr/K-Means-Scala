import scala.collection.mutable.ArrayBuffer
import scala.util.Random


class Cluster(val clusterName: String,  val donnees: Array[Exemple], val _nbAttributes: Int) :

   //Variables

   private val clusterDataNum: ArrayBuffer[Int] = new ArrayBuffer[Int]()
   private var clusterCentroid: Individu = new Individu(_nbAttributes)
   private val clusterClassName: String =""
   private var clusterClassNumber: Int = 0
   private var clusterError: Double = 0
   private var clusterIntraDistance: Double = 0

   //Get

   def centroid: Individu = this.clusterCentroid

   def className: String = this.clusterClassName

   def classNumber: Int = this.clusterClassNumber

   def erreur: Double = this.clusterError

   def intraDistance: Double =
      this.clusterIntraDistance = this.computeIntraDistance
      return this.computeIntraDistance

   def nameCluster: String = this.clusterName

   def nbAttributs: Int = this._nbAttributes

   def get(i: Int): Int = this.clusterDataNum(i)

   //Methods

   def add(num: Int): Unit = this.clusterDataNum.append(num)


   def centroid_=(c: Individu): Unit = this.clusterCentroid = c


   def computeCentroid: Unit =
      val sumAttributes: Array[Double] = new Array[Double](_nbAttributes)

      this.donnees.foreach(exemple =>
      (0 until _nbAttributes).foreach(j => sumAttributes(j) += exemple.get(j))
      )

      (0 until _nbAttributes).foreach(i => this.clusterCentroid.set(i, sumAttributes(i) / this.size))


   def computeClusterError: Unit =
      this.clusterError = (this.donnees.filter(_.classNumber != this.classNumber).size.toDouble / this.size) * 100


   def computeIntraDistance: Double =
      var sommeDonneeCluster: Double = 0
      for (i <- 0 until this.size)
         sommeDonneeCluster += scala.math.pow(this.donnees(i).distance(centroid),2)
      return (1.0/this.size)*sommeDonneeCluster


   def size : Int = this.clusterDataNum.length


   def computeClassCluster(): Unit =
      this.clusterClassNumber = this.donnees.groupBy(_.classNumber).maxBy(_._2.size)._1


   override def toString: String =
      s"Cluster(clusterName: ${this.clusterName}, _nbAttributes: ${this._nbAttributes}, exemplesNums: " +
        s"${this.clusterDataNum}, clusterCentroid: ${this.clusterCentroid}, clusterIntraDistance: " +
        s"${this.clusterIntraDistance}, clusterError: ${this.clusterError}, clusterClassName: ${this.clusterClassName}, " +
        s"clusterClassNumber: ${this.clusterClassNumber})"


   def empty: Unit =
      clusterDataNum.clear()







