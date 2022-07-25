object util:
  def ~=(x: Double, y: Double, precision: Double): Boolean = {
    if ((x - y).abs < precision) true else false
  }