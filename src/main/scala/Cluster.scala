import scala.collection.mutable.ArrayBuffer

class Cluster(val name: String,  donnees: Array[Exemple], _nbAttributes: Int) :

   private val clusterDataNum = new ArrayBuffer[Int]
   private val clusterDataExemple = new ArrayBuffer[Exemple]

   override def toString: String =
      for (i <- 0 until this.clusterDataNum.length)
        this.clusterDataExemple.append(this.donnees(this.clusterDataNum(i)))
      var s = "Nom du cluster : " + this.name + "\nNombres d'attributs des éléments du cluster : " + this._nbAttributes +  "\nNuméros des données du cluster : " + this.clusterDataNum +"\nDonnées du cluster : " + this.clusterDataExemple
      return s


   def add(num: Int): Unit = this.clusterDataNum.append(num)

   def size : Int = this.clusterDataNum.length

   def nbAttributes: Int = this._nbAttributes


