import org.apache.spark._
import org.apache.spark.graphx._
import org.apache.spark.rdd.RDD

case class Products3(id1: Long, startNode: Int, id2: Long, endNode: Int, nOfPurchases: Int)

object ParallelGraph2 {
  def main(args: Array[String]): Unit = {

    def parseProduct(str: String): Products3 = {
      val line = str.split(",")
      Products3(line(0).toInt, line(0).toInt, line(1).toInt, line(1).toInt, line(2).toInt)
    }
    //input from user(or system) - ID of a product that was purchased
    val targetNode = scala.io.StdIn.readInt()
    //Set up Spark session
    val conf = new SparkConf().setAppName("Products Graph").setMaster("local[*]")
    val sc = new SparkContext(conf)
    //Create RDD with the Amazon data, the path should be updated based on data location
    val textRDD = sc.textFile("C:\\Users\\matoh_000\\IdeaProjects\\WordCounter\\data\\Complete2.csv")

    val productsRDD = textRDD.map(parseProduct).cache()

    val products = productsRDD.map(product => (product.id1, product.startNode))

    val purchases = productsRDD.map(product => ((product.id1, product.id2), product.nOfPurchases))

    // Defining the routes as edges
    val edges = purchases.map { case ((startNode, endNode),nOfPurchases) => Edge(startNode.toLong, endNode.toLong, nOfPurchases) }

    //Defining the Graph
    val graph = Graph(products, edges)

    var RDD = sc.parallelize(Array[EdgeTriplet[Int, Int]]())
    //filter triplets, sort them but take only edge with highest nOfPurchases because subsequent operations only takes one
    val triplet = graph.triplets.filter(e => e.srcId == targetNode).sortBy(_.attr, ascending=false).first
    RDD = RDD.union(sc.parallelize(Array(triplet)))
    //print the ID of a node with highest number of edges incoming from targetNode
    RDD.collect.foreach(p => println(p.dstId))
    }
}
