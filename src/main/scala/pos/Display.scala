package pos

abstract class Display {
  protected def render(messageToDisplay: String): Unit

  def displayPrice(price: Float): Unit = {
    render("$%.2f".format(price))
  }

  def displayEmptyBarcodeError(): Unit = {
    render("Barcode read was empty")
  }

  def displayNoPriceFoundForItem(): Unit = {
    render("No item was found for the barcode requested")
  }

  def displayErrorOccurredInPriceLookup(): Unit = {
    render("An error occurred looking up the price for the barcode requested")
  }
}
