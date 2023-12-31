package  com.training.bdd

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.DataFrame
import java.sql.{SQLException, SQLSyntaxErrorException}
import java.util.Properties
import com.mysql.cj.jdbc.exceptions.CommunicationsException


object SparkAndMysql {

     val properties = new java.util.Properties();
        properties.put("user","root")
        properties.put("password", "Makeda18!")

  val traceLog: org.apache.log4j.Logger = org.apache.log4j.LogManager.getLogger("Spark_MySQL_Logger")
  val mySqlDriver = "com.mysql.cj.jdbc.Driver"
  val prefixUrl = "jdbc:mysql://"

  def run(): Unit = {
    val sparkSession = com.training.utils.UtilsSpark.sparkSession(true)

    testConnectionDbMysql(sparkSession)
    // joinTables(sparkSession)
  }

  /**
   * @author Serigne
   * @param sparkSession Spark session for defining dataframe
   * @see readDataFrameFromMySqlDb
   */
  def testConnectionDbMysql(sparkSession: SparkSession) = {

    val dfMysql = readDataFrameFromMySqlDb(sparkSession, "localhost:3306", "DB_ORDERS","table_orders", "root",
    "Makeda18!")
    if (dfMysql != null) {
      dfMysql.printSchema()
      traceLog.info("SHOW DATAFRAME FROM TABLE table_orders")
      dfMysql.show()
    }

    val dfQuery = readDataFrameFromMySqlDb(sparkSession, "localhost:3306",
      "DB_ORDERS",
      "(select * from DB_ORDERS.table_orders group by state, city) table_summary",
      "root",
      "Makeda18!")
    if (dfQuery != null) {
      traceLog.info("SHOW DATAFRAME FROM TABLE orders WITH QUERY")
      dfQuery.show()
    }
  }

//   /**
//    * This method is to join two dataframes
//    * @author KANDJI
//    * @param sparkSession  SparkSession for defining Dataframe
//    * @see readDataFrameFromMySqlDb
//    */
//   def joinTables(sparkSession: SparkSession) = {
//     /*val dfOrders = sparkSession.read.jdbc("jdbc:mysql://127.0.0.1:3361/bigdata_test_db",
//       "bigdata_test_db.orders", properties)*/

//     val dfOrders = readDataFrameFromMySqlDb(sparkSession, "localhost:3361", "bigdata_test_db","orders", "userBigdata",
//       "6gUSN9N_/.mJ686")

//     val dfOrderLine = readDataFrameFromMySqlDb(sparkSession, "localhost:3361", "bigdata_test_db","orderline", "userBigdata",
//       "6gUSN9N_/.mJ686")

//     if (dfOrderLine != null && dfOrders != null) {
//       val dfJoinOrders = dfOrderLine.join(dfOrders, dfOrders.col("orderid") ===
//         dfOrderLine.col("orderid"), "inner").groupBy("customerid").count().withColumnRenamed(
//         "count", "totalorders"
//       )

//       dfJoinOrders.show()
//     }
//   }

  /**
   * Read data from MySql Database
   * @author Serigne
   * @param sparkSession  Session of Spark
   * @param url           Url of database e.g: (localhost:3361)
   * @param dbName        Name of database we want to use
   * @param tableName     Name of table where we read data
   * @param userName      User for connecting to database
   * @param password      Password for connecting to database
   * @return              Return DataFrame object or null value
   */
  def readDataFrameFromMySqlDb(sparkSession: SparkSession, url: String, dbName: String, tableName: String,
                               userName: String, password: String): DataFrame = {
    try {
      val dfRead = sparkSession.read
        .format("jdbc")
        //.option("driver", mySqlDriver)
        .option("url", prefixUrl + url + "/" + dbName)
        .option("dbtable", s"${tableName}")
        .option("user", s"${userName}")
        .option("password", s"${password}")
        .load()

      return dfRead
    } catch {
      case exception: SQLSyntaxErrorException => traceLog.error(s"SQL Syntax Error: ${exception.getMessage}")
      case exception: ClassNotFoundException => traceLog.error(s"Class not found: ${exception.getMessage}")
      case exception: SQLException => traceLog.error(s"SQL Exception: ${exception.getMessage}")
      case exception: CommunicationsException => traceLog.error(s"Communication link failure: ${exception.getMessage}")
      case exception: Exception => traceLog.error(s"Exception: ${exception.getMessage}")
    }
    null
  }

  /**
   * Write data in MySQL Database
   * @author Serigne
   * @param dataFrame  Dataframe which we want to save in Database
   * @param url        Url for connecting to Database
   * @param dbName     DbName which we use
   * @param tableName  TableName with we write data from dataframe
   * @param userName   User for connecting to Database
   * @param password   Password for connecting to Database
   */
  def writeDataFrameToMySqlDb(dataFrame: DataFrame, url: String, dbName: String, tableName: String,
                              userName: String, password: String) = {
    try {
      dataFrame.write
        .format("jdbc")
        .option("url", s"jdbc:mysql://${url}/${dbName}")
        .option("dbtable", s"${tableName}")
        .option("user", s"${userName}")
        .option("password", s"${password}")
        .save()
    }
    catch {
      case exception: SQLSyntaxErrorException => traceLog.error(s"SQL Syntax Error: ${exception.getMessage}")
      case exception: ClassNotFoundException => traceLog.error(s"Class not found: ${exception.getMessage}")
      case exception: SQLException => traceLog.error(s"SQL Exception: ${exception.getMessage}")
      case exception: CommunicationsException => traceLog.error(s"Communication link failure: ${exception.getMessage}")
      case exception: Exception => traceLog.error(s"Exception: ${exception.getMessage}")
    }
  }
  
}
