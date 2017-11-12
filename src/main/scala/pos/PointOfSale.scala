package pos

class PointOfSale(priceDisplay: Display, priceLookup: PriceLookup) {

  def onBarcode(barcode: String): Unit = {
    if (barcode.isEmpty) {
      priceDisplay.render("Barcode read was empty")
    } else {
      val price = priceLookup.priceForBarcode(barcode)
      priceDisplay.render("$" + price)
    }
  }

}
