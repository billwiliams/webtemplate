import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

    val appName         = "template"
    val appVersion      = "1.0"
    val appDependencies = Seq(
		javaCore,
		javaJdbc,
		javaEbean,
        "securesocial" %% "securesocial" % "master-SNAPSHOT",
        "com.typesafe" %% "play-plugins-mailer" % "2.0.4",
         "postgresql" % "postgresql" % "9.1-901.jdbc4",
         "org.hibernate" % "hibernate-entitymanager" % "3.6.9.Final"
    )

    val main = play.Project(appName, appVersion, appDependencies).settings(
	resolvers += Resolver.url("sbt-plugin-snapshots", new URL("http://repo.scala-sbt.org/scalasbt/sbt-plugin-snapshots/"))(Resolver.ivyStylePatterns)
    )
}
