import smile.plot.swing.{Canvas, Palette, ScatterPlot}
import smile.projection.PCA

import java.awt.{Color, GridLayout}
import javax.swing.{JComponent, JFrame, JPanel}

class Visualisation(val kMeans: Kmeans, val k: Int):
  val data: Array[Array[Double]] = kMeans.getData.getNormalizedData.map(exemple =>
    val attributes: Array[Double] = new Array[Double](exemple.nbAttributes)
    (0 until exemple.nbAttributes).foreach(i =>
      attributes(i) = exemple.get(i)
    )
      attributes
  )
  val matriceDeProjection = PCA.fit(data).setProjection(2).project(data)

  def display: Unit =
    kMeans.clustering(k)
    val signs = Array('*', '#', 'Q', '*', '*')
    val colors = Array(Color.BLUE, Color.RED, Color.BLACK, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN)
    val toto = matriceDeProjection
    println("Taille de matrice de projection: " + toto(0).length)
    println("Taille de labels: " + kMeans.getLabels.length)
    val scatterPlot: ScatterPlot = ScatterPlot.of(matriceDeProjection, kMeans.getLabels, signs(0))
    val canvas: Canvas = scatterPlot.canvas()
    canvas.setTitle("Visualization")
    val frame = canvas.window()
    frame.setTitle("K-Means")
    frame.setLayout(new GridLayout())
    frame.setVisible(true)
    frame.setSize(1500, 750)


