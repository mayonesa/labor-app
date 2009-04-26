import grails.test.*

class FlexLaborTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testFlexLabor() {
    	mockDomain(FlexLabor)
    	def flexLabor = new FlexLabor()
    	assertNotNull flexLabor
    }
}
