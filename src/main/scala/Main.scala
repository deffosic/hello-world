import  org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import  org.apache.spark.sql.functions._
import org.apache.spark.sql.Row
import org.apache.spark.sql.types._
import org.apache.spark.sql.SparkSession
import scala.util.Random

object Main extends App {
  println("Hello, World!")

  // val conf = new SparkConf().setMaster("local[2]").setAppName("AppHelloWorld")
  // val sc = new SparkContext(conf)

  //RDD 
  // Créer un RDD à partir d'un objet collection
  // val stringList = Array("Spark is awesome","Spark is cool")
  // val stringRDD = sc.parallelize(stringList)

  // println(s"SortByKey")
  // val data=sc.parallelize(Seq(("maths",52),("english",75),("science",82), ("computer",65),("maths",85)))
  // val sorted = data.sortByKey()
  // sorted.foreach(println)

  // stringRDD.foreach(println)

  // //Créer un RDD à partir d'un fichier comme source de données
  // //val fileRDD = sc.textFile("../data/data.txt")

  // // Opération

  // // Map
  // println(s"Map")
  // val allCapsRDD = stringRDD.map(line => line.toUpperCase)
  // allCapsRDD.collect().foreach(println)

  // // Map & fonction
  // println(s"Map & fonction")
  // def toUpperCase(line:String) : String = {  line.toUpperCase }
  // stringRDD.map(l => toUpperCase(l)).collect.foreach(println)

  // // Map & Transformation
  // case class Contact(id:Long, name:String, email:String)
  // val contactData = Array[String]("1#John Doe#jdoe@domain.com", "2#Mary Jane#mjane@domain.com")
  // val contactDataRDD = sc.parallelize(contactData)
  // val contactRDD = contactDataRDD.map(l => {
  //         val contactArray = l.split("#")
  //         Contact(contactArray(0).toLong, contactArray(1), contactArray(2))
  // })
  // contactRDD.collect.foreach(println)

  // //Transforming from a Collection of Strings to a Collection of Integers
  // val stringLenRDD = stringRDD.map(l => l.length)
  // stringLenRDD.collect.foreach(println)

  // println("************************************************reduceByKey*****************************************")


  // val candyTx = sc.parallelize(List(("candy1", 5.2), ("candy2", 3.5), ("candy1", 2.0), ("candy2", 6.0), ("candy3", 3.0)))

  // val somTx = candyTx.reduceByKey((total, value)=>total + value)

  // somTx.collect().foreach(println)

  // sc.stop()


  //#######################################################################################Data Frame#################################################

  val ss = new SparkSession.Builder().appName("AppHelloWorld").master("local[*]").getOrCreate()
  println(s"Spark Session: ${ss}")

  val rdd = ss.sparkContext.parallelize(1 to 10).map(x => (x, Random.nextInt(100)*x))

  import ss.implicits._

  val kvDF = rdd.toDF("key","value")

  println("############################ Schéma################################")

  kvDF.printSchema()

  println("############################ Valeurs################################")

  kvDF.show()


  println("############################ DataFrame people################################")


  val peopleRDD = ss.sparkContext.parallelize(Array(Row(1L, "JohnDoe",  30L),Row(2L, "Mary Jane", 25L)))

  val schema = StructType(Array(
        StructField("id", LongType, false),
        StructField("name", StringType, true),
        StructField("age", LongType, true)
        ))

  val peopleDF = ss.createDataFrame(peopleRDD, schema)

  peopleDF.printSchema()

  peopleDF.show()


println("############################ SparSession range################################")

val df = ss.range(5,50).toDF("Val").show()

println("############################ DF from collection################################")

val films = Seq(("Damon, Matt", "The Bourne Ultimatum", 2007L),("Damon, Matt", "Good Will Hunting", 1997L))

val filmsDF = films.toDF("Acteur","Titre du film", "Année")

filmsDF.printSchema()

filmsDF.show()

println("############################ DF colomn ################################")

val df2 = Seq((1,2),(2,3)).toDF("key","value")

df2.columns

df2.select("key").show()

df2.select(col("key")).show()

df2.select(column("key")).show()

df2.select($"key").show()

df2.select('key).show()

df2.select(df2.col("key")).show()

df2.select('key, 'key > 1).show()



  


}