package oldemo

import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

import ol3.implicits._
import ol3._

/**
 * Created by marci on 12-11-2015.
 */
object Main extends JSApp {
  @JSExport
  def main() = {

    new ol.Map(olx.MapOptions(
      target = dom.document.body,
      layers = js.Array(
        new ol.layer.Tile(olx.layer.TileOptions(
          source = new ol.source.MapQuest(olx.source.MapQuestOptions(
            layer = "sat"
          ))
        ))
      ),
      view = new ol.View(olx.ViewOptions(
        center = ol.proj.pkg.fromLonLat(js.Array(37, 8)),
        zoom = 4.0
      ))
    ))

  }
}
