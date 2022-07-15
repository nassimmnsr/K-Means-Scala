import scala.collection.mutable.ArrayBuffer
import scala.util.Random


class Cluster(val name: String,  val donnees: Array[Exemple], val _nbAttributes: Int) :

   private val clusterDataNum = new ArrayBuffer[Int]
   private val clusterDataExemple = new ArrayBuffer[Exemple]

   private var centroid: Individu = _

   override def toString: String =
      for (i <- 0 until this.clusterDataNum.length)
        this.clusterDataExemple.append(this.donnees(this.clusterDataNum(i)))
      var s = "Nom du cluster : " + this.name + "\nNombres d'attributs des éléments du cluster : " + this._nbAttributes +  "\nNuméros des données du cluster : " + this.clusterDataNum +"\nDonnées du cluster : " + this.clusterDataExemple
      return s


   def add(num: Int): Unit = this.clusterDataNum.append(num)

   def size : Int = this.clusterDataNum.length

   def nbAttributes: Int = this._nbAttributes

   def get(i: Int): Int = this.clusterDataNum(i)

   def initCentroid: Unit =
      val random = new Random()
      this.centroid = clusterDataExemple(random.nextInt(clusterDataNum.length))

   def getCentroid: Individu = this.centroid


