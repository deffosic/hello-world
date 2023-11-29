package com.training.utils



import org.apache.spark.sql.SparkSession

import java.io.FileNotFoundException
import org.apache.log4j.LogManager
import org.apache.log4j.Logger

object UtilsSpark {

  var ss: SparkSession = null
  var traceLog: Logger = LogManager.getLogger("Logger_Console")

  def sparkSession(env: Boolean = true): SparkSession = {
    try {
      if (env == true) {
        //System.setProperty("hadoop.home.dir", "C:/Hadoop") // sur windows = "C:/Hadoop/"
        ss = SparkSession.builder()
          .master("local[*]")
          .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
          .config("spark.sql.crossJoin.enabled", "true")
          //.enableHiveSupport()
          .getOrCreate()
      } else {
        ss = SparkSession.builder()
          .appName("Mon application Spark")
          .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
          .config("spark.sql.crossJoin.enabled", "true")
          //.enableHiveSupport()
          .getOrCreate()
      }
      traceLog.info("DEMARRAGE DE LA SESSION SPARK")
    } catch {
      case ex: FileNotFoundException => traceLog.error(ex.printStackTrace())
      case ex: Exception => traceLog.error(ex.printStackTrace())
    }

    ss

  }
}