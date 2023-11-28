import  org.apache.spark.SparkConf
import org.apache.spark.SparkContext
object Main extends App {
  println("Hello, World!")

  val conf = new SparkConf().setMaster("local[2]").setAppName("AppHelloWorld")
  val sc = new SparkContext(conf)

  //RDD 
  // Créer un RDD à partir d'un objet collection
  val stringList = Array("Spark is awesome","Spark is cool")
  val stringRDD = sc.parallelize(stringList)

  println(s"SortByKey")
  val data=sc.parallelize(Seq(("maths",52),("english",75),("science",82), ("computer",65),("maths",85)))
  val sorted = data.sortByKey()
  sorted.foreach(println)

  stringRDD.foreach(println)

  //Créer un RDD à partir d'un fichier comme source de données
  //val fileRDD = sc.textFile("../data/data.txt")

  // Opération

  // Map
  println(s"Map")
  val allCapsRDD = stringRDD.map(line => line.toUpperCase)
  allCapsRDD.collect().foreach(println)

  // Map & fonction
  println(s"Map & fonction")
  def toUpperCase(line:String) : String = {  line.toUpperCase }
  stringRDD.map(l => toUpperCase(l)).collect.foreach(println)

  // Map & Transformation
  case class Contact(id:Long, name:String, email:String)
  val contactData = Array[String]("1#John Doe#jdoe@domain.com", "2#Mary Jane#mjane@domain.com")
  val contactDataRDD = sc.parallelize(contactData)
  val contactRDD = contactDataRDD.map(l => {
          val contactArray = l.split("#")
          Contact(contactArray(0).toLong, contactArray(1), contactArray(2))
  })
  contactRDD.collect.foreach(println)

  //Transforming from a Collection of Strings to a Collection of Integers
  val stringLenRDD = stringRDD.map(l => l.length)
  stringLenRDD.collect.foreach(println)
}