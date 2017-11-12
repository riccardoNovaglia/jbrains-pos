package pos

abstract class PriceLookup {

  def priceForBarcode(barcode: String): Float

}

class PriceNotFoundException(barcode: String)
  extends RuntimeException(s"No price found for $barcode")

