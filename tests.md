### List of tests to implement

~~Happy path: get a barcode and display some price~~

~~Barcode is empty~~

~~Looking up the barcode raises an error~~

~~Item price for barcode is not found~~

~~Price format needs adjusting~~


### Assumptions and observations
- Whatever stores prices for items is not implemented here.
- The lookup receives a barcode as a string and returns a price. Newlines (mentioned in the requirements) are discarded/ignored by whatever looks up prices.
- All prices are in dollars. Only the value of the price is returned, not the currency. Prices are already formatted (i.e. 2 decimal digits max)
- It might be a database looked up when an item is received and updated in real time, or it might be a static list of prices loaded once a day. It does not matter here.
- It returns some special value or exception if an item looked up is not found.
- It can fail for other mysterious reasons and throw an exception.
- Lookups are done synchronously.
- The display cannot fail, and if it does, the error is handled somewhere else.
