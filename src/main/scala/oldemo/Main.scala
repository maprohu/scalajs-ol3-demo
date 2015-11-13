package oldemo

import ol3._
import implicits._
import ol.View
import ol.layer.Tile
import ol.source.MapQuest
import olx.layer.TileOptions
import olx.{ViewOptions, MapOptions}
import olx.source.MapQuestOptions
import ol.proj.pkg._


import org.scalajs.dom
import scala.scalajs.js
import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport


object Main extends JSApp {
  @JSExport
  def main() = {

    new ol.Map(MapOptions(
      target = dom.document.body,
      layers = js.Array(
        new Tile(TileOptions(
          source = new MapQuest(MapQuestOptions(
            layer = "sat"
          ))
        ))
      ),
      view = new View(ViewOptions(
        center = fromLonLat(js.Array(37, 8)),
        zoom = 4
      ))
    ))


  }
}
