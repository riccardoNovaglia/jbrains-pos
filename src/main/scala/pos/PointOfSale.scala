package pos

class PointOfSale(priceDisplay: Display) {

  def onBarcode(str: String): Unit = {
    if (str.isEmpty) {
      priceDisplay.render("Barcode read was empty")
    } else {
      priceDisplay.render("$1")
    }
  }

}
