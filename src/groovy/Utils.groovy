/**
 * @author jimenez
 *
 */
class Utils {	
	static concatenate = { Object[] collectionOfCollections ->
		def concatenatedCollection = []
		collectionOfCollections?.each() { collection ->
			collection?.each() { element ->
				concatenatedCollection.add(element)
			}
		}
		concatenatedCollection
	}
	
	static process = { collectionOfCollections, elementProcessor ->
		collectionOfCollections?.each() { collection ->
			collection?.each() { element ->
				elementProcessor(element)
			}
		}
	}
}
