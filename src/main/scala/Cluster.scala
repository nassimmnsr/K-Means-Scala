import scala.collection.mutable.ArrayBuffer
import scala.util.Random


class Cluster(val name: String,  val donnees: Array[Exemple], val _nbAttributes: Int, val nbClasses: Int) :

   //Variables

   private val clusterDataNum = new ArrayBuffer[Int]
   private val clusterDataExemple = new ArrayBuffer[Exemple]

   private var centroid: Individu = _
   private var centroidNum: Int = _

   // Get

   def size : Int = this.clusterDataNum.length

   def nbAttributes: Int = this._nbAttributes

   def get(i: Int): Int = this.clusterDataNum(i)

   def getClusterData(i: Int): Exemple = this.clusterDataExemple(i)

   def getClusterDataExemple(): ArrayBuffer[Exemple] = this.clusterDataExemple

   def getCentroid: Individu = this.centroid

   def getCentroidNum: Int = this.centroidNum

   def classNumber(i: Int): Int = this.donnees(i).classNumber

   def classNumber: Int = this.computeClassCluster

   // Method

   override def toString: String =
      var s = "Nom du cluster : " + this.name + "\nNombres d'attributs des éléments du cluster : " + this._nbAttributes +  "\nNuméros des données du cluster : " + this.clusterDataNum +"\nDonnées du cluster : " + this.clusterDataExemple
      return s

   def add(num: Int): Unit =
      this.clusterDataNum.append(num)
      for (i <- 0 until this.clusterDataNum.length)
         this.clusterDataExemple.append(this.donnees(this.clusterDataNum(i)))

   def computeCentroid: Unit =
      val random = new Random()
      this.centroidNum = clusterDataNum(random.nextInt(clusterDataNum.length))
      this.centroid = this.donnees(centroidNum)

   def computeClassCluster: Int =
      var categorie1 = 0
      var categorie2 = 0
      var categorie3 = 0

      var classe = 0
      var categorie = 0

      for (i <- 0 until this.clusterDataNum.length)
         classe = this.classNumber(clusterDataNum(i))
         if classe == 0 then
            categorie1 += 1
         else if classe == 1 then
            categorie2 += 1
         else
            categorie3 += 1

      if categorie1 > categorie2 && categorie1 > categorie3 then
         categorie = 0
         return categorie

      else if categorie2 > categorie1 && categorie2 > categorie3 then
         categorie = 1
         return categorie

      else
         categorie = 2
         return categorie

   def centroidUpd(c: Int): Unit =
      this.centroidNum = c
      this.centroid = this.donnees(c)

   def className(i: Int): String =
      var classeNumber = this.donnees(i).classNumber
      if classeNumber == 0 then
         return "Iris-setosa"
      else if classeNumber == 1 then
         return "Iris-versicolor"
      else
         return "Iris-virginica"

   def className: String =
      var classeNumberMaj = this.classNumber
      if classeNumberMaj == 0 then
         return "Iris-setosa"
      else if classeNumberMaj == 1 then
         return "Iris-versicolor"
      else
         return "Iris-virginica"

   def intraDistance: Double =
      var sommeDonneeCluster: Double = 0
      for (i <- 0 until this.clusterDataExemple.length)
         sommeDonneeCluster += scala.math.pow(this.clusterDataExemple(i).distance(centroid),2)
      return (1.0/this.clusterDataExemple.length)*sommeDonneeCluster

   def empty: Unit =
      clusterDataNum.clear()
      clusterDataExemple.clear()
      this.add(centroidNum)

   //def erreur: Double =
   //def computeClusterError(): Unit




