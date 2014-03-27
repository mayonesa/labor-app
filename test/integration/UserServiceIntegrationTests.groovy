import grails.test.*

class UserServiceIntegrationTests extends GrailsUnitTestCase {
	def dbAdmin, john, george
	
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testGetSchedulableList() {
		new DayAndTimeSpecificLabor(name: 'dishes', laborTerm: LaborTermType.SUMMER, durationInHours: 2, day: Day.FRIDAY, startHour: 7, numberOfLaborers: 2).save()
		new DayAndTimeSpecificLabor(name: 'mop', laborTerm: LaborTermType.SUMMER, durationInHours: 2, day: Day.FRIDAY, startHour: 7, numberOfLaborers: 2).save()
		new DayAndTimeSpecificLabor(name: 'program', laborTerm: LaborTermType.SUMMER, durationInHours: 2, day: Day.FRIDAY, startHour: 7, numberOfLaborers: 2).save()
		
		new CurrentLaborTerm(laborTerm: LaborTermType.SUMMER).save()
		
		def laborService = new LaborService(conflicts: { user, labor -> labor.name.contains('p') })
	
		def schedulableList = new UserService(laborService: laborService, currentLaborTermService: new CurrentLaborTermService()).getSchedulableList()
		
		assertEquals 1, schedulableList.size()
		assertEquals 'dishes', schedulableList.get(0).name
    }

	void testGetWishList() {
		def userService = new UserService()
		def wishList = userService.getWishList(user, [new FlexLabor(name: 'dishes', laborTerm: LaborTermType.SUMMER), new DaySpecificLabor(name: 'clean', laborTerm: LaborTermType.SUMMER), new DayAndTimeSpecificLabor(name: 'paint', laborTerm: LaborTermType.SUMMER)])
		assertEquals ([new FlexLabor(name: 'dishes', laborTerm: LaborTermType.SUMMER), new DaySpecificLabor(name: 'clean', laborTerm: LaborTermType.SUMMER)], wishList)
		assertEquals ([], userService.getWishList(user, []))
	}
}
