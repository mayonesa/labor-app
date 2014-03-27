import grails.test.*

class LaborServiceIntegrationTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }
	
	void testUsersNeedingLabor() {
		def pedro = new User()
		pedro.firstName = 'Pedro'
		pedro.lastName = 'Garzon'
		pedro.roomNumber = '113'
		pedro.username = 'pgarzon'
		pedro.password = '622ew543'
		pedro.addToAssignedFlexLabors(new FlexLabor(name: 'someFlexLabor', laborTerm: LaborTermType.FALL_SPRING, durationInHours: 3))
		pedro.addToAssignedDayAndTimeSpecificLabors(new DayAndTimeSpecificLabor(name: 'someLabor', laborTerm: LaborTermType.FALL_SPRING, day: Day.MONDAY, durationInHours: 2))
		pedro.save()
		def juan = new User()
		juan.firstName = 'Juan'
		juan.lastName = 'Cuchillo'
		juan.roomNumber = '412'
		juan.username = 'jcuchillo'
		juan.password = '654sss3'
		juan.addToAssignedFlexLabors(new FlexLabor(name: 'someOtherFlexLabor', laborTerm: LaborTermType.FALL_SPRING, durationInHours: 1))
		juan.addToAssignedDayAndTimeSpecificLabors(new DayAndTimeSpecificLabor(name: 'anotherLabor', laborTerm: LaborTermType.FALL_SPRING, day: Day.MONDAY, durationInHours: 2))
		juan.save()
		def toston = new User()
		toston.firstName = 'Toston'
		toston.lastName = 'Jimenez'
		toston.roomNumber = '302'
		toston.username = 'tjimenez'
		toston.password = '6fjsldfj543'
		toston.addToAssignedFlexLabors(new FlexLabor(name: 'yetANotherFlexLabor', laborTerm: LaborTermType.FALL_SPRING, durationInHours: 1))
		toston.addToAssignedDayAndTimeSpecificLabors(new DayAndTimeSpecificLabor(name: 'anotherSpecLabor', laborTerm: LaborTermType.FALL_SPRING, day: Day.MONDAY, durationInHours: 3))
		toston.save()
		def tito = new User()
		tito.firstName = 'Tito'
		tito.lastName = 'Titinillo'
		tito.roomNumber = '003'
		tito.username = 'ttinino'
		tito.password = '6dsjflf543'
		tito.save()
		def pepe = new User()
		pepe.firstName = 'Pepe'
		pepe.lastName = 'Popillin'
		pepe.roomNumber = '103'
		pepe.username = 'ppppppp'
		pepe.password = '6dsjflf543'
		pepe.role = Role.OFFICER
		pepe.save()
		def joel = new User()
		joel.firstName = 'Toston'
		joel.lastName = 'Jimenez'
		joel.roomNumber = '302'
		joel.username = 'tjimenez'
		joel.password = '6fjsldfj543'
		joel.addToAssignedFlexLabors(new FlexLabor(name: 'yetANotherFlexLabor', laborTerm: LaborTermType.SUMMER, durationInHours: 1))
		joel.addToAssignedDayAndTimeSpecificLabors(new DayAndTimeSpecificLabor(name: 'anotherSpecLabor', laborTerm: LaborTermType.SUMMER, day: Day.MONDAY, durationInHours: 2))
		joel.save()
		
		def fallSpringRegularHours = new LaborTermRoleHours(laborTerm: LaborTermType.FALL_SPRING, role: Role.REGULAR, hours: 4)
		fallSpringRegularHours.save()
		def fallSpringOfficerHours = new LaborTermRoleHours(laborTerm: LaborTermType.FALL_SPRING, role: Role.OFFICER, hours: 0)
		fallSpringOfficerHours.save()
		def summerRegularHours = new LaborTermRoleHours(laborTerm: LaborTermType.SUMMER, role: Role.REGULAR, hours: 6)
		summerRegularHours.save()
		def summerOfficerHours = new LaborTermRoleHours(laborTerm: LaborTermType.SUMMER, role: Role.OFFICER, hours: 2)
		summerOfficerHours.save()

		new CurrentLaborTerm(laborTerm: LaborTermType.FALL_SPRING).save()
		
		def usersNeedingLabor = new LaborService(laborTermRoleHoursService: new LaborTermRoleHoursService(currentLaborTermService: new CurrentLaborTermService())).usersNeedingLabor()
		assertEquals 2, usersNeedingLabor.size()
		assertTrue usersNeedingLabor.contains(juan)
		assertTrue usersNeedingLabor.contains(tito)
	}
}
