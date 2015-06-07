package sn.analytics

import com.typesafe.config.{Config, ConfigFactory}
import org.apache.spark.SparkContext
import org.apache.spark.api.java.JavaSparkContext
import org.apache.spark.sql.SQLContext
import spark.jobserver.{SparkSqlJob, SparkJob, SparkJobValid, SparkJobValidation}

/**
 * Created by Sumanth on 06/06/15.
 */
object QueryExecutor extends SparkSqlJob{
  def main(args: Array[String]) {
    val config = ConfigFactory.parseString("")


    //val results = runJob(sc, config)

  }

  override def validate(sc: SQLContext, config: Config): SparkJobValidation = {
    SparkJobValid;
  }

  override def runJob(sc: SQLContext, config: Config): Any = {

    val sqlQueryRunner = new SqlQueryRunner()

    val queryStr = config.getString("queryStr")


    val res= sqlQueryRunner.runSqlQuery(sc,"/tmp/fileset",queryStr);
    System.out.println("Launching SQL Context");
    sc.clearCache()
    sc.sparkContext.stop()
    return res;

  }

}
