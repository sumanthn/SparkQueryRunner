name := "queryrun"

version := "1.0.0"

scalacOptions ++= Seq("-deprecation")

lazy val buildSettings = Seq(
  version := "0.1-SNAPSHOT",
  organization := "sn.analytics",
  scalaVersion := "2.10.4"
)

resolvers += "Job Server Bintray" at "https://dl.bintray.com/spark-jobserver/maven"

libraryDependencies ++= Seq (
  "joda-time" % "joda-time" % "2.7",
  "org.joda" % "joda-convert" % "1.2",
  ("org.apache.spark" %% "spark-core" % "1.3.1").
    exclude("org.mortbay.jetty", "servlet-api").
    exclude("commons-beanutils", "commons-beanutils-core").
    exclude("commons-collections", "commons-collections").
    exclude("com.esotericsoftware.minlog", "minlog").
    exclude("junit", "junit").
    exclude("org.slf4j", "log4j12"),
  ("org.apache.spark" %% "spark-sql" % "1.3.1").
    exclude("org.mortbay.jetty", "servlet-api").
    exclude("commons-beanutils", "commons-beanutils-core").
    exclude("commons-collections", "commons-collections").
    exclude("com.esotericsoftware.minlog", "minlog").
    exclude("junit", "junit").
    exclude("org.slf4j", "log4j12"),
    "spark.jobserver" %% "job-server-api" % "0.5.1" % "provided",
    "spark.jobserver" %% "job-server-extras" % "0.5.1" % "provided"
)
