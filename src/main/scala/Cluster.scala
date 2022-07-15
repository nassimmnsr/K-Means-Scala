import scala.collection.mutable.ArrayBuffer

class Cluster(val name: String,  donnees: Array[Exemple], _nbAttributes: Int) :

   private val clusterData = new ArrayBuffer[Exemple]

   override def toString: String = "Nom du cluster : " + this.name + "\nDonnées du cluster : " + this.clusterData + "\nNombres d'attributs des éléments du cluster : " + this._nbAttributes

   def add(num: Int): Unit = this.clusterData.append(this.donnees(num))

