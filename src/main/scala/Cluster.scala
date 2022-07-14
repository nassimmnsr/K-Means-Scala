
class Cluster(val name: String,  donnees: Array[Exemple], _nbAttributes: Int) :


   override def toString: String = super.toString + " " + this.name + " " + this.donnees.length + " " + this._nbAttributes