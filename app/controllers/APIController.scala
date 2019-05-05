package controllers

import javax.inject.Inject

import play.api.mvc._
import play.api.db._

class APIController @Inject()(db: Database, cc: ControllerComponents) (implicit assetsFinder: AssetsFinder)
  extends AbstractController(cc) {

  def get_block(x: Long, y: Long) = Action {
    val conn = db.getConnection()

    var outstring = ""

    try {
      val stmt = conn.createStatement
      val rs = stmt.executeQuery("select type from blocks where x=" + x + " and y=" + y + ";")
      while (rs.next()) {
        outstring += rs.getString("type")
      }
    } finally {
      conn.close()
    }

    Ok(outstring)
  }

}
