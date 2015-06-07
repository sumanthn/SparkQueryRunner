package sn.analytics;

import org.apache.spark.sql.DataFrame;
import org.apache.spark.sql.SQLContext;
import scala.Function1;
import scala.Tuple2;
import scala.collection.Seq;
import scala.collection.mutable.*;
import scala.collection.mutable.StringBuilder;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by Sumanth on 06/06/15.
 */
public class SqlQueryRunner implements Serializable {
    public static Object main(String[] args) throws Exception {

        return null;
    }

    public Object runSqlQuery(SQLContext sqlContext, final String query, final String filePaths){


/*
        Seq<String> allFiles = scala.collection.JavaConversions.asScalaBuffer(filePaths);

        System.out.println("Using file names as ");
        while(allFiles.iterator().hasNext()){
            System.out.println(allFiles.iterator());
            allFiles.iterator().next();
        }*/

        DataFrame df = sqlContext.parquetFile(filePaths);

        sqlContext.registerDataFrameAsTable(df, "factdata");

        DataFrame res = sqlContext.sql("SELECT * from factdata where responseStatusCode > 399");
        final String resDir = "/tmp/resdump/res-" + UUID.randomUUID().toString();
        res.saveAsParquetFile(resDir);
        return resDir;
    }
}
