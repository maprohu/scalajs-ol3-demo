
lazy val root = (project in file("."))
  .enablePlugins(ScalaJSPlugin, SbtWeb)
  .settings(
    site.settings
      ++
    ghpages.settings
  )
  .settings(
    name := "oldemo",
    scalaVersion := "2.11.7",
    resolvers += Resolver.sonatypeRepo("snapshots"),

    libraryDependencies ++= Seq(
      "com.github.maprohu" %%% "scalajs-ol3" % "0.1.0-SNAPSHOT"
    ),

    persistLauncher := true,

    crossTarget in fastOptJS := WebKeys.stagingDirectory.value,
    crossTarget in fullOptJS := WebKeys.stagingDirectory.value,
    crossTarget in packageScalaJSLauncher := WebKeys.stagingDirectory.value,
    crossTarget in packageJSDependencies := WebKeys.stagingDirectory.value,
    WebKeys.stage := {
      (fastOptJS in Compile).value
      (fullOptJS in Compile).value
      WebKeys.stage.value
    },

    git.remoteRepo := "git@github.com:maprohu/scalajs-ol3-demo.git",
    SiteKeys.siteSourceDirectory := WebKeys.stagingDirectory.value


  )
