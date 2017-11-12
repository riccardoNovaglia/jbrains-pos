package pos

class PointOfSale(priceDisplay: Display) {

  def onBarcode(str: String): Unit = {
    priceDisplay.render("$1")
  }

}
