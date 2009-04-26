/**
 * @author jimenez
 *
 */
class Utils {	
	static concatenate = { collectionOfCollections ->
		def concatenatedCollection = []
		process({ element ->
					concatenatedCollection.add(element)
				}, collectionOfCollections)
		concatenatedCollection
	}
	
	static process = { elementProcessor, collectionOfCollections ->
		collectionOfCollections?.each() { collection ->
			collection?.each() { element ->
				elementProcessor(element)
			}
		}
	}
}
