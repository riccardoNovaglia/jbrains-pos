package pos

class PointOfSale(priceDisplay: Display, priceLookup: PriceLookup) {

  def onBarcode(barcode: String): Unit = {
    if (barcode.isEmpty) {
      priceDisplay.displayEmptyBarcodeError()
    } else {
      lookupAndDisplayPrice(barcode)
    }
  }

  private def lookupAndDisplayPrice(barcode: String): Unit = {
    try {
      val price = priceLookup.priceForBarcode(barcode)
      priceDisplay.displayPrice(price)
    } catch {
      case _: PriceNotFoundException  => priceDisplay.displayNoPriceFoundForItem()
      case _: Exception               => priceDisplay.displayErrorOccurredInPriceLookup()
    }
  }
}
