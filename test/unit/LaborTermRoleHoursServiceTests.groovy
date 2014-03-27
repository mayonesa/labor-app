import grails.test.*

class LaborTermRoleHoursServiceTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testGetRoleHoursRequired() {
		mockDomain(LaborTermRoleHours)
		def fallSpringRegularHours = new LaborTermRoleHours()
		fallSpringRegularHours.role = Role.REGULAR
		fallSpringRegularHours.laborTerm = LaborTermType.FALL_SPRING
		fallSpringRegularHours.hours = 4
		fallSpringRegularHours.save()
		def fallSpringOfficerHours = new LaborTermRoleHours()
		fallSpringOfficerHours.role = Role.OFFICER
		fallSpringOfficerHours.laborTerm = LaborTermType.FALL_SPRING
		fallSpringOfficerHours.hours = 0
		fallSpringOfficerHours.save()
		def summerOfficerHours = new LaborTermRoleHours()
		summerOfficerHours.role = Role.OFFICER
		summerOfficerHours.laborTerm = LaborTermType.SUMMER
		summerOfficerHours.hours = 2
		summerOfficerHours.save()
		
		mockDomain(CurrentLaborTerm)
		def currentLaborTerm = new CurrentLaborTerm(laborTerm: LaborTermType.FALL_SPRING)
		currentLaborTerm.save()
		
		mockFor(LaborTermRoleHoursService)
		def laborTermHoursService = new LaborTermRoleHoursService(currentLaborTermService: new CurrentLaborTermService())
		assertEquals 4, laborTermHoursService.getRoleHoursRequired(Role.REGULAR)
		assertEquals 0, laborTermHoursService.getRoleHoursRequired(Role.OFFICER)
		assertNull laborTermHoursService.getRoleHoursRequired(Role.BREAKTHROUGH)		
    }
}
