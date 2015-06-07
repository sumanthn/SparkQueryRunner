package sn.analytics

import java.util.UUID

import scala.collection.JavaConverters._
import com.typesafe.config.{ConfigFactory, Config}
import spark.jobserver.{SparkJobInvalid, SparkJobValid, SparkJobValidation, SparkSqlJob}

/**
 * Created by Sumanth on 07/06/15.
 */
object QueryExecutionDriver extends SparkSqlJob{
  override def runJob(sc: QueryExecutionDriver.C, config: Config): Any = {

    val jsonCfg = ConfigFactory.load(config)
    val queryStr = config.getString("queryStr")
	//input only those required files
    val useFiles = jsonCfg.getStringList("filePaths").asScala


    //val df = sc.parquetFile("/tmp/dataset/dd1")
    val df = sc.parquetFile(useFiles:_*)
    df.registerTempTable("factdata")

    val dfRes = df.sqlContext.sql(queryStr)
	//this can be configurable too
    val resDir = "/tmp/resdump/res-" + UUID.randomUUID().toString;
    dfRes.javaRDD.saveAsTextFile(resDir)

	//save as parquet
    //dfRes.save(resDir);

    return resDir




  }

  override def validate(sc: QueryExecutionDriver.C, config: Config): SparkJobValidation = {
    try {
      println("invoking validate")
      val jsonCfg = ConfigFactory.load(config)
      val queryStr = jsonCfg.getString("queryStr")
      if (queryStr != null) {
        println("Execute query to be " + queryStr)

        SparkJobValid
      } else {
        SparkJobInvalid("Incorrect Query params")
      }
    }catch {
      case e: Exception => SparkJobInvalid("Exception in parsing Query Parameters")
    }
  }
}
