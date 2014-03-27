import grails.test.*

class CurrentLaborTermServiceTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testGetLaborTerm() {
		mockDomain(CurrentLaborTerm)
		def currentLaborTerm = new CurrentLaborTerm(laborTerm: LaborTermType.FALL_SPRING)
		currentLaborTerm.save()
		
		mockFor(CurrentLaborTermService)
		assertEquals LaborTermType.FALL_SPRING, new CurrentLaborTermService().getLaborTerm()
    }
}
