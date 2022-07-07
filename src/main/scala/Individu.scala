import scala.util.Random

/**
 * Gère un Individu possedant des attributs de type Double.
 *
 * @param _nbAttributes : le nombre d'attributs
 * @example pour créer un Individu avec 4 attributs de type Double :
 * {{{
 *  val ind = New Individu(4)
 * }}}
 * @constructor Initialise un Individu avec ''_nbAttributes'' attributs de type Double
 *
 */
class Individu(private val _nbAttributes: Int):

  private val donnee = new Array[Double](this.nbAttributes)

  /**
   * @return le nombre d'attributs de l'Individu
   */
  def nbAttributes: Int = this._nbAttributes

  /**
   * @return la valeur de l'attribut numéro ''j''
   */
  def get(j: Int): Double = this.donnee(j)

  /**
   * affecte l'attribut ''j'' avec la valeur value ''value'' */
  def set(j: Int, value: Double): Unit =
    this.donnee(j) = value

  /**
   *
   * @return le String décrivant l'Individu */
  override def toString: String =
    var s = ""

    for (attribut <- this.donnee)
      s = s + f"$attribut%.3f "

    return s


  /**
   * @return la distance euclidienne entre l'Individu courant et l'Individu ''ind''
   */
  def distance(ind: Individu): Double =
    var dist: Double = 0

    for (j <- this.donnee.indices)
      dist += (this.get(j) - ind.get(j)) * (this.get(j) - ind.get(j))

    return math.sqrt(dist)

// fin classe Individu

object Individu:
  /**
   * @param nbAttributs :nombre d'attributs
   * @param alea        :
   * @return un Individu dont les ''nbAttributs'' valeurs d'attributs ont été tirées au hasard
   */
  def generateRandomIndividu(nbAttributs: Int, alea: Random): Individu =
    val individu = new Individu(nbAttributs)

    for (j <- 0 until nbAttributs)
      individu.set(j, alea.nextDouble)

    return individu