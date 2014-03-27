import grails.test.*

class LaborRegistrationTagLibTests extends TagLibUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testWishListSelect() {
		tagLib.wishListSelect(wishList: [new FlexLabor(id: 1, name: 'dishes'), new FlexLabor(id: 3, name: 'floors')], availableLabors: [new FlexLabor(id: 21, name: 'smiling'), new FlexLabor(id: 10, name: 'wiping')]) {
			assertEquals '<li id=\'1\'>dishes</li><li id=\'3\'>floors</li>', tagLib.out.toString()
		}
    }
}
