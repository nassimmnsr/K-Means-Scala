//import smile.plot.swing.{Canvas, Palette, PlotPanel, ScatterPlot}
//import smile.projection.PCA
//
//import java.awt.{Color, GridLayout}
//import javax.swing.{JComponent, JFrame, JPanel}
//
//class Visualisation(val kMeans: Kmeans, val k: Int):
//  val data: Array[Array[Double]] = kMeans.getData.getNormalizedData.map(exemple =>
//    val attributes: Array[Double] = new Array[Double](exemple.nbAttributes)
//    (0 until exemple.nbAttributes).foreach(i =>
//      attributes(i) = exemple.get(i)
//    )
//      attributes
//  )
//  val matriceDeProjection = PCA.fit(data).setProjection(2).project(data)
//
//  def display: Unit =
//    kMeans.clusteringWithViz(k)
//    //    val signs = Array('*', '#', 'Q', '*', '*')
//    //    val colors = Array(Color.BLUE, Color.RED, Color.BLACK, Color.CYAN, Color.DARK_GRAY, Color.GRAY, Color.GREEN)
//    val frame: JFrame = new JFrame("K-Means Visualizations")
//    val clustersConnus: PlotPanel = ScatterPlot.of(matriceDeProjection, kMeans.getKnownLabels, '*')
//      .canvas()
//      .setTitle("Clusters connus")
//      .setAxisLabel(0, "Composante Principale 1")
//      .setAxisLabel(1, "Composante Principale 2")
//      .panel()
//    frame.setLayout(new GridLayout())
//    frame.add(clustersConnus)
//    frame.setVisible(true)
//    frame.setSize(1500, 750)
//
//
//    //    println("Known labels, my labels")
//    //    kMeans.getKnownLabels.indices.foreach(i =>
//    //      println(kMeans.getKnownLabels(i) + " " + kMeans.getLabels(i))
//    //    )
//    val kMeansClusters: PlotPanel = ScatterPlot.of(matriceDeProjection, kMeans.getLabels, '*')
//      .canvas()
//      .setTitle("Clusters obtenus par notre K-Means")
//      .setAxisLabel(0, "Composante Principale 1")
//      .setAxisLabel(1, "Composante Principale 2")
//      .panel()
//    //    val frame: JFrame = canvas.window()
//    frame.add(kMeansClusters)
//    frame.revalidate()
//
//    println(matriceDeProjection.map(elt => elt(0)).length)
//    println(s"matriceDeProjection(0).max : ${matriceDeProjection.map(elt => elt(0)).max} matriceDeProjection(0).min: ${matriceDeProjection.map(elt => elt(0)).min}")
//    println(s"matriceDeProjection(1).max : ${matriceDeProjection.map(elt => elt(1)).max} matriceDeProjection(1).min: ${matriceDeProjection.map(elt => elt(1)).min}")
//
