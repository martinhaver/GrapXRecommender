import org.apache.spark._
import org.apache.spark.graphx._

case class Products(id1: Long, startNode: Int, id2: Long, endNode: Int, nOfPurchases: Int)

object AdvancedGraph2 {
  def main(args: Array[String]): Unit = {
    def parseProduct(str: String): Products = {
      val line = str.split(",")
      Products(line(0).toInt, line(0).toInt, line(1).toInt, line(1).toInt, line(2).toInt)
    }
    //Input from user(or system) - ID of a product that was purchased
    val targetNode = scala.io.StdIn.readInt()
    //Set up Spark session
    val conf = new SparkConf().setAppName("Products Graph").setMaster("local[*]")
    val sc = new SparkContext(conf)
    //Create RDD with the Amazon data the path should be updated based on data location
    val textRDD = sc.textFile("C:\\Users\\matoh_000\\IdeaProjects\\WordCounter\\data\\Complete2.csv")

    val productsRDD = textRDD.map(parseProduct).cache()

    val products = productsRDD.map(product => (product.id1, product.startNode))

    val purchases = productsRDD.map(product => ((product.id1, product.id2), product.nOfPurchases))

    // Defining the routes as edges
    val edges = purchases.map { case ((startNode, endNode),nOfPurchases) => Edge(startNode.toLong, endNode.toLong, nOfPurchases) }

    //Defining the Graph
    val graph = Graph(products, edges)

    //testing utils
    //graph.triplets.take(10).foreach(println)
    //println(graph.numEdges)

    //final computation of recommended products
    val myArray = Array(graph.triplets.filter(e => e.srcId == targetNode).sortBy(_.attr, ascending=false).map(triplet => triplet.dstAttr).take(10))
    myArray.deep.foreach(println)
  }
}