# SparkQueryRunner
Spark Sql Query runner within SparkJobServer<br>
Goal is execute the Sql queries on raw files with aid of SparkJobServer<br>
Setup<br>
SparkJobserver 0.5.1 with Spark 1.3<br>
Storage : Parquet<br>

Steps
----------
`sbt compile package` <br>
Would get artifact `queryexecutor_2.10-1.0.0.jar` <br>
Start jobserver with job-server-extras, to load the SparkSqlJob <br>
`sbt` <br>
`project job-server-extras` <br>
`reStart` <br>
can also start job-server to connect to master by providing a different configuration <br>

Create the SqlContext for executing the queries <br>
`curl -d "" 'localhost:8090/contexts/sql-context?context-factory=spark.jobserver.context.SQLContextFactory'` <br>

Upload the jar <br>
`curl --data-binary @queryexecutor_2.10-1.0.0.jar localhost:8090/jars/queryrunner` <br>

Running the query <br>
Query currently takes 2 params, <br>
Query as queryStr & the set of files/directories to be used, <br>
sample json, <br>
Extract all the rows where responsecode > 399 <br>
`{"queryStr":"SELECT * FROM factdata WHERE responseStatusCode \u003e 399","filePaths":["/tmp/dataset/dd1"]}` <br>

Run the query <br>
`curl --data-binary @queryparams.json 'localhost:8090/jobs?appName=queryrunner&classPath=sn.analytics.QueryExecutionDriver&context=sql-context&sync=true' ` <br>

Parquet file generation can be done using factdatagenerator <br>
