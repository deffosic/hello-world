import  org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import  org.apache.spark.sql.functions._
import org.apache.spark.sql.Row
import org.apache.spark.sql.types._
import org.apache.spark.sql.SparkSession
import scala.util.Random
import org.apache.spark.sql.SaveMode

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
  //Arrête d'afficher les logs
  ss.sparkContext.setLogLevel("OFF")
  println(s"Spark Session: ${ss}")

//   val rdd = ss.sparkContext.parallelize(1 to 10).map(x => (x, Random.nextInt(100)*x))

//   import ss.implicits._

//   val kvDF = rdd.toDF("key","value")

//   println("############################ Schéma################################")

//   kvDF.printSchema()

//   println("############################ Valeurs################################")

//   kvDF.show()


//   println("############################ DataFrame people################################")


//   val peopleRDD = ss.sparkContext.parallelize(Array(Row(1L, "JohnDoe",  30L),Row(2L, "Mary Jane", 25L)))

//   val schema = StructType(Array(
//         StructField("id", LongType, false),
//         StructField("name", StringType, true),
//         StructField("age", LongType, true)
//         ))

//   val peopleDF = ss.createDataFrame(peopleRDD, schema)

//   peopleDF.printSchema()

//   peopleDF.show()


// println("############################ SparSession range################################")

// val df = ss.range(5,50).toDF("Val").show()

// println("############################ DF from collection################################")

// val films = Seq(("Damon, Matt", "The Bourne Ultimatum", 2007L),("Damon, Matt", "Good Will Hunting", 1997L))

// val filmsDF = films.toDF("Acteur","Titre du film", "Année")

// filmsDF.printSchema()

// filmsDF.show()

// println("############################ DF colomn ################################")

// val df2 = Seq((1,2),(2,3)).toDF("key","value")

// df2.columns

// df2.select("key").show()

// df2.select(col("key")).show()

// df2.select(column("key")).show()

// df2.select($"key").show()

// df2.select('key).show()

// df2.select(df2.col("key")).show()

// df2.select('key, 'key > 1).show()


println("############################ DF from csv ################################")

// val dataDF = ss.read
// .option("header","true")
// .option("sep",",")
// .option("inferSchema", "true")
// .csv("/home/ubuntu/workspace/hello-world/datas/data.csv")



//   dataDF.printSchema()

//   dataDF.show(10)

//   dataDF.selectExpr("*", "(ai0 * ai0) as AI0_square").show()

//   dataDF.selectExpr("*", "(ai0 * ai0 + ai1*ai1 + ai2*ai2 +ai3*ai3 + ai4*ai4 +ai5*ai5) as Somme_quadratique").show()


  println("############################ DF from text file orders ################################")




  // val schema_order = StructType(Array(
  //   StructField("orderid",StringType, false),
  //   StructField("customerid", IntegerType, false),
  //   StructField("campaignid", IntegerType, true),
  //   StructField("orderdate", TimestampType, true),
  //   StructField("city", StringType, true),
  //   StructField("state", StringType, true),
  //   StructField("zipcode", StringType, true),
  //   StructField("paymenttype", StringType, true),
  //   StructField("totalprice", DoubleType, true),
  //   StructField("numorderlines", IntegerType, true),
  //   StructField("numunits", IntegerType, true)
  // ))

  // val df_orders_db = ss.read
  //     .format("com.databricks.spark.csv")
  //     .option("delimiter", ";")
  //     .option("header","true")
  //     .load("/home/ubuntu/workspace/hello-world/datas/csv/db_orders.csv")

  // println("Schema : Orders_DB")

  // df_orders_db.printSchema()


  // val df_orders_db_good = df_orders_db.withColumnRenamed("status","_status")
  //                                     .withColumnRenamed("address","_address")
  //                                     .withColumnRenamed("function","_function")
  //                                     .withColumnRenamed("total_ht2","total_ht")
  //                                     .withColumnRenamed("total_ht46","_total_ht")


  // println("Schema : Orders_DB_good")

  // df_orders_db_good.printSchema()

  // println("##########################Save to Mysql################################")
  // val jdbcUrl_mysql = "jdbc:mysql://localhost:3306/DB_ORDERS"
  // val jdbcTable_mysql = "table_orders"
  // val properties_mysql = new java.util.Properties()
  //     properties_mysql.setProperty("user", "root")
  //     properties_mysql.setProperty("password", "Makeda18!")
  //     properties_mysql.setProperty("driver", "com.mysql.cj.jdbc.Driver")

  // df_orders_db_good.write.mode("append").jdbc(jdbcUrl_mysql, jdbcTable_mysql, properties_mysql)

  // println("Good Mysql")

  // println("##########################Save to Postgresql################################")
  // val jdbcUrl_postgres = "jdbc:postgresql://localhost:5432/DB_ORDERS"
  // val jdbcTable_postgres = "table_orders"
  // val properties_postgres = new java.util.Properties()
  //     properties_postgres.setProperty("user", "cedric")
  //     properties_postgres.setProperty("password", "Makeda18!")
  //     properties_postgres.setProperty("driver", "org.postgresql.Driver")
  
  // df_orders_db_good.write.mode("append").jdbc(jdbcUrl_postgres, jdbcTable_postgres, properties_postgres)

  // println("Good Postgresql   :)²")


  



  // val df_orders = ss.read
  //     .format("com.databricks.spark.csv")
  //     .option("delimiter", "\t")
  //     .option("header","true")
  //     .schema(schema_order)
  //     .load("/home/ubuntu/workspace/hello-world/datas/csv/orders.txt")

  // println("Schema : Orders")

  // df_orders.printSchema()

  // df_orders.show(10)

  // val df_products = ss.read
  //     .format("com.databricks.spark.csv")
  //     .option("delimiter", "\t")
  //     .option("header","true")
  //     .load("/home/ubuntu/workspace/hello-world/datas/csv/product.txt")
    
  // println("Schema : Products")

  // df_products.printSchema

  // val df_orderlines = ss.read
  //     .format("com.databricks.spark.csv")
  //     .option("delimiter", "\t")
  //     .option("header","true")
  //     .load("/home/ubuntu/workspace/hello-world/datas/csv/orderline.txt")

  // println("Schema : OrdersLines")

  // df_orderlines.printSchema


  // val df_orders_good = df_orders.withColumnRenamed("numunits", "numunits_orders")
  // .withColumnRenamed("totalprice", "totalprice_orders")
  
  // println("Schema : Orders Good")

  // df_orders_good.printSchema()


  // val df_join_ordres = df_orderlines.join(df_orders_good, df_orders_good.col("orderid")===df_orderlines.col("orderid"), "inner")

  // println("Schema : OrdersLine Join with Orders")

  // df_join_ordres.printSchema()

  // df_join_ordres.show(5)

  // val df_join_ordres_products = df_join_ordres.join(df_products,df_products.col("productid")===df_join_ordres.col("productid"), "inner")

  // println("Schema : OrdersLine Join with Orders and Join with Products")

  // df_join_ordres_products.printSchema()

  // df_join_ordres_products.show(5)


  // val df_state_city = df_join_ordres_products.withColumn("total_amount", round(col("numunits_orders") * col("totalprice_orders"), 3))
  //     .groupBy("state", "city")
  //     .sum("total_amount")
      
  // df_state_city.show()

  // println("####################Save DF###################")

  // df_state_city.repartition(1)
  // .write
  // .format("com.databricks.spark.csv")
  // .mode(SaveMode.Overwrite)
  // .option("header","true")
  // .csv("/home/ubuntu/workspace/hello-world/datas/saves/csv")
  // //.save("/home/ubuntu/workspace/hello-world/datas/saves/csv")


  
//   println("###########################################################################Window###################")
//   val df_aapl_us = ss.read
//       .format("com.databricks.spark.csv")
//       .option("delimiter", ",")
//       .option("header","true")
//       .option("inferSchema", "true")
//       .load("/home/ubuntu/workspace/hello-world/datas/csv/aapl_us.csv")


//   val df_aapl_us_good = df_aapl_us.select(to_date(col("Date"), "MM/dd/yyyy").as("Date"), col("Open"), col("High"), col("Low"), col("Close"), col("Volume"))


//   df_aapl_us_good.printSchema()


//   val applaWeekAvgDF = df_aapl_us_good.groupBy(window(col("Date"), "1 week")).agg(avg("Close").as("Weekly_avg"))

//   applaWeekAvgDF.printSchema


//   applaWeekAvgDF.orderBy("window.start").selectExpr("window.start","window.end", "round(weekly_avg,2) as weekly_avg").show()


//   val appleMonthlyAvgDF = df_aapl_us_good.groupBy(window(col("Date"), "4 week", "1 week")).agg(avg("Close").as("monthly_avg"))

//   appleMonthlyAvgDF.orderBy("window.start").selectExpr("window.start", "window.end","round(monthly_avg, 2) as monthly_avg").show()




// println("###########################################DataSet###################################")
// import ss.implicits._

// case class Movie(actor_name:String, movie_title:String, produced_year:Long)

// val films = Seq(("Damon, Matt", "The Bourne Ultimatum", 2007L),("Damon, Matt", "Good Will Hunting", 1997L))

// val filmsDF = films.toDF("actor_name","movie_title", "produced_year")

// val filmDS = filmsDF.as[Movie]




// //val localMovies_raw = Seq( Array("John Doe", "Awesome Movie", 2018L), Array("Mary Jane", "Awesome Movie", 2018L))



// val localMovies = Seq( Movie("John Doe", "Awesome Movie", 2018L), Movie("Mary Jane", "Awesome Movie", 2018L))

// val localMoviesDS1 = ss.createDataset(localMovies)

// val localMoviesDS2 = localMovies.toDS()


// println("FilmDS")
// filmDS.show

// println("localMoviesDS1")
// localMoviesDS1.show

// println("localMoviesDS2")
// localMoviesDS2.show


// println("Filter")
// localMoviesDS1.filter(movie => movie.produced_year== 2018).show

// println(filmDS.first.movie_title)

// println("#####################SQL############################################")

// ss.catalog.listTables().show()

// df_aapl_us_good.createOrReplaceTempView("Stock_Apple")

// localMoviesDS2.createOrReplaceTempView("Movies")

// ss.catalog.listTables().show()

// ss.catalog.listColumns("Movies").show()

println("Sql Movies")

ss.sql("select * from Movies where actor_name like '%Mary%' and produced_year > 2009").show()

println("Sql Stock Apple")

ss.sql("select * from Stock_Apple where Open > 150 and Close > 180").show()








}