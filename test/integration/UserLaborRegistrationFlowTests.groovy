import grails.test.*

class UserLaborRegistrationFlowTests extends WebFlowTestCase {
	
	private controller = new UserController()
	private user
	def userService
	
	def getFlow() {
		controller.laborRegistrationFlow
	}
	
    protected void setUp() {
        super.setUp()
		user = new User(wishList: [new FlexLabor()], unavailableSchedule: [OneHourSlot.MONDAY_8])
//		user.metaClass.getSchedulableLabors = { [new FlexLabor()] }
		userService = new UserService()
		controller.metaClass.getUser = { user }
		userService.metaClass.getSchedulableLabors = { [new FlexLabor(name: 'program', laborTerm: LaborTermType.SUMMER), 
													    new DaySpecificLabor(name: 'DISHES', laborTerm: LaborTermType.SUMMER), 
												        new DayAndTimeSpecificLabor(name: 'sweep', laborTerm: LaborTermType.SUMMER),
												        new DayAndTimeSpecificLabor(name: 'mop', laborTerm: LaborTermType.SUMMER)] }
		userService.metaClass.getRevisedWishList = { [new FlexLabor(name: 'program', laborTerm: LaborTermType.SUMMER), 
													  new DaySpecificLabor(name: 'DISHES', laborTerm: LaborTermType.SUMMER), 
													  new DayAndTimeSpecificLabor(name: 'sweep', laborTerm: LaborTermType.SUMMER)] }
		controller.userService = userService
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testRegistrationFlow() {
		startFlow()
		assertCurrentStateEquals 'showUnavailableSchedule'
//		println getFlow().class.name
//		println "owner: ${getFlow().owner.class.name}"
//		assertEquals([OneHourSlot.MONDAY_8], getFlow().unavailableSchedule)
		controller.params.unavailableSchedule = [OneHourSlot.MONDAY_12, OneHourSlot.MONDAY_13]
		signalEvent 'continue'
		assertEquals([OneHourSlot.MONDAY_12, OneHourSlot.MONDAY_13], getFlow().unavailableSchedule)
		assertEquals([new FlexLabor(name: 'program', laborTerm: LaborTermType.SUMMER), 
					  new DaySpecificLabor(name: 'DISHES', laborTerm: LaborTermType.SUMMER), 
					  new DayAndTimeSpecificLabor(name: 'sweep', laborTerm: LaborTermType.SUMMER)],
					 getFlow().wishList)
		assertEquals([new DayAndTimeSpecificLabor(name: 'mop', laborTerm: LaborTermType.SUMMER)], getFlow().availableLabors)
		assertCurrentStateEquals 'populateWishListInfo'
		
    }
}
