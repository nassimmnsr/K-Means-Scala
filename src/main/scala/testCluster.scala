import util._

object testCluster:
  def main(args: Array[String]): Unit =
    displayTitle("Tests Start")

    // on créé un objet donnees de type Data
    val donnees: Data = new Data("ressources/data/iris.data", "ressources/data/irisAttributesNames.txt")

    // recupere les exemples normalisés de donnees
    val exemples: Array[Exemple] = donnees.getNormalizedData


    // création d'un cluster initialement vide
    // de nom ''cluster1''
    // associé aux exemples normalisés
    // les exemples sont décrits par 4 attributs,
    // et il y a 3 catégories d'exemples
    display("constructor test")
    val cluster = new Cluster("cluster1", exemples, 4)
    println("Test OK")


    // ajoute dans le cluster les numeros de 5 exemples
    // présents dans exemples :
    display("add method test")
    cluster.add(4)
    cluster.add(6)
    cluster.add(123)
    cluster.add(19)
    cluster.add(77)
    // affiche le contenu du cluster
    println("Test OK")


    display("toString method test")
    println(cluster.toString)
    // ou simplement println(cluster)

    display("centroid method test")
    println(s"cluster centroid : ${cluster.centroid}")

    display("centroid_= test")
    cluster.centroid = new Individu(4)
    println("Test OK")

    display("className method test")
    println(s"cluster className : ${cluster.className}")

    display("classNumber method test")
    println(s"cluster classNumber : ${cluster.classNumber}")

    display("computeCentroid method test")
    println(s"cluster centroid before executing the computeCentroid method:\t${cluster.centroid}")
    cluster.computeCentroid()
    println(s"cluster centroid after executing the computeCentroid method:\t${cluster.centroid}")

    display("computeClassCluster method test")
    println(s"classNumber before executing the computeClassCluster method:\t${cluster.classNumber}")
    cluster.computeClassCluster()
    println(s"classNumber after executing the computeClassCluster method:\t${cluster.classNumber}")

    display("computeClusterError method test")
    println(s"cluster error before executing the computeClusterError method:\t${cluster.erreur}")
    cluster.computeClusterError()
    println(s"cluster error after executing the computeClusterError method:\t${cluster.erreur}")

    display("computeIntraDistance method test")
    println(s"cluster intraDistance before executing the computeClusterIntraDistance method:\t${cluster.intraDistance}")
    cluster.computeIntraDistance()
    println(s"cluster intraDistance after executing the computeClusterIntraDistance method:\t${cluster.intraDistance}")



    display("erreur method test")
    println(s"cluster erreur :\t${cluster.erreur}")

    display("get method test")
    println(s"cluster get(0) :\t${cluster.get(0)}")
    println(s"cluster get(1) :\t${cluster.get(1)}")
    println(s"cluster get(2) :\t${cluster.get(2)}")

    display("intraDistance method test")
    println(s"cluster intraDistance :\t${cluster.intraDistance}")

    display("name method test")
    println(s"cluster name :\t${cluster.name}")

    display("nbAttributs method test")
    println(s"cluster nbAttributs :\t${cluster.nbAttributs}")

    display("size method test")
    println(s"cluster size :\t${cluster.size}")

    display("empty method test")
    println(s"cluster size before executing the empty method:\t${cluster.size}")
    cluster.empty()
    println(s"cluster size after executing the empty method:\t${cluster.size}")

    displayTitle("Tests End")
