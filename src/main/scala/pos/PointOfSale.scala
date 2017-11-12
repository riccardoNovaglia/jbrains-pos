package pos

class PointOfSale(priceDisplay: Display, priceLookup: PriceLookup) {

  def onBarcode(barcode: String): Unit = {
    if (barcode.isEmpty) {
      priceDisplay.render("Barcode read was empty")
    } else {
      try {
        val price = priceLookup.priceForBarcode(barcode)
        priceDisplay.render("$" + price)
      } catch {
        case _: Exception => priceDisplay.render("No item was found for the the barcode requested")
      }
    }
  }

}
