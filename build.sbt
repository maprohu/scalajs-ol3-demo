import SiteKeys._

lazy val fullOpt = SettingKey[Boolean]("full-opt")

lazy val root = (project in file("."))
  .enablePlugins(ScalaJSPlugin, SbtWeb)
  .settings(
    name := "oldemo",
    scalaVersion := "2.11.7",
    resolvers += Resolver.sonatypeRepo("snapshots"),

    libraryDependencies ++= Seq(
      "com.github.maprohu" %%% "scalajs-ol3" % "0.1.0-SNAPSHOT"
    ),

    persistLauncher := true,
    git.remoteRepo := "git@github.com:maprohu/scalajs-ol3-demo.git",

    site.settings,
    ghpages.settings,
    fullOpt := true,
    siteMappings ++= Def.taskDyn {
      def mapFile(f: File) : File = new File(f.getParentFile, f.getName + ".map")
      val fastSite = Def.task {
        val fast = (fastOptJS in Compile).value.data

        val genJS = Seq(
          fast,
          mapFile(fast),
          (packageScalaJSLauncher in Compile).value.data,
          (packageJSDependencies in Compile).value
        ) pair relativeTo(Seq(
          (crossTarget in fastOptJS).value,
          (crossTarget in packageScalaJSLauncher).value,
          (crossTarget in packageJSDependencies).value
        ))

        genJS ++ (mappings in Assets).value
      }

      if (!fullOpt.value) {
        fastSite
      } else {
        Def.task {
          val full = (fullOptJS in Compile).value.data
          fastSite.value ++ (Seq(
            full,
            mapFile(full)
          ) pair relativeTo((crossTarget in fullOptJS).value))
        }
      }
    }.value



  )
