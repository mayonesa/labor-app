import grails.test.*

class UtilsTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

	void testProcess() {
		def totalSum = 0
		Utils.process({ value -> totalSum += value }, [[1, 2, 3], [4, 4, 5], [1, 2, 2]])
		assertEquals 24, totalSum
	}

    void testConcatenate() {
		assertEquals ([1,2,3,4,5,6], Utils.concatenate([1, 2 ,3], [4, 5, 6], null))
    }
}
