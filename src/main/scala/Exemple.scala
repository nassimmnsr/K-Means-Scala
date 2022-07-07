
/**
 * Gère un Exemple.
 *
 * Un Exemple est un Individu associé à une classe
 *
 * @example pour créer un Exemple avec 4 attributs de type Double, associé à la classe numéro 2
 * {{{
 *  val ex = New Exemple(4,2)
 * }}}
 * @constructor Initialise un Exemple
 * @param _nbAttributes : le nombre d'attributs
 * @param _classNumber  : numéro de la classe associée à l'Exemple
 */

class Exemple(_nbAttributes: Int,
              _classNumber: Int) extends Individu(_nbAttributes) :
  /**
   * @return le numéro de classe de l'Exemple
   */
  def classNumber: Int = _classNumber

  /**
   *
   * @return le String décrivant l'Exemple
   */
  override def toString: String = super.toString + s" classe = ${this.classNumber}"
