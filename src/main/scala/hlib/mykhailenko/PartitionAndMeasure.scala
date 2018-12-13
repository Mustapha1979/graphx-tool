package hlib.mykhailenko

import org.apache.spark.SparkContext
import org.apache.spark._
import org.apache.spark.graphx._
import java.io.PrintWriter
import java.io.FileWriter

import org.apache.spark.storage.StorageLevel

object PartitionAndMeasure {

  def main(args: Array[String]) {

    require(args.length == 5, """|Wrong argument number.
                                 |Should be 5. Usage: <pathToGrpah> <filenameWithResult> <parititonerName> <minEdgePartitions> <shortGraphName>""".stripMargin)

    val pathToGrpah = args(0)
    val filenameWithResult = args(1)
    val partitionerName = args(2)
    val minEdgePartitions = args(3).toInt
    val graphName = args(4)

    println(System.getenv("SPARK_HOME"));
    val sc = new SparkContext(new SparkConf()
        .setSparkHome(System.getenv("SPARK_HOME"))
        .setMaster("local")
        .setAppName("PartitionAndMeasure")
        .setJars(SparkContext.jarOfClass(this.getClass).toList))

    var graph: Graph[Int, Int] = null

    graph = GraphLoader.edgeListFile(sc, pathToGrpah, false, edgeStorageLevel = StorageLevel.MEMORY_AND_DISK,
      vertexStorageLevel = StorageLevel.MEMORY_AND_DISK, minEdgePartitions = minEdgePartitions)

    if(partitionerName != "no"){
        graph = graph.partitionBy(PartitionStrategy.fromString(partitionerName))
    }
    graph.numEdges

    val jaylo = new JsonLogger0(sc)
    val mapa = jaylo.metrics(graph)

    val out = new PrintWriter(new FileWriter(filenameWithResult, true));
    val arr = Array(
        "numberVertices",
        "numberEdges",
        "numberPartitions",
        "balance",
        "NEWlargestPartition",
        "NSTDEV",
        "vertexCut",
        "communicationCost"

      )




    val vals = graphName :: partitionerName :: arr.map(x => mapa.get(x).getOrElse("")).toList //:: graph.numVertices.toString :: graph.numEdges.toString
    val s = vals.mkString(";")

    out.println(s)
    out.close

  }
}
