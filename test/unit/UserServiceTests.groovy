import grails.test.*

class UserServiceTests extends GrailsUnitTestCase {
	
	def user = new User()
	def userService = new UserService()
	def schedulableLabors = [new FlexLabor(name: 'dishes', laborTerm: LaborTermType.SUMMER), 
	  						 new DaySpecificLabor(name: 'clean', laborTerm: LaborTermType.SUMMER), 
	  						 new DayAndTimeSpecificLabor(name: 'mop', 
								  						 laborTerm: LaborTermType.SUMMER, 
								  						 durationInHours: 1, 
								  						 startHour: 8, 
								  						 day: Day.MONDAY),
						    new DayAndTimeSpecificLabor(name: 'sweep', 
													    laborTerm: LaborTermType.SUMMER, 
														durationInHours: 1, 
													    startHour: 18, 
													    day: Day.MONDAY)]
	
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }
	
	void testGetRevisedWishListWithOneScheduleConflict() {
		def originalWishList = [new FlexLabor(name: 'dishes', laborTerm: LaborTermType.SUMMER), 
								new DaySpecificLabor(name: 'clean', laborTerm: LaborTermType.SUMMER), 
								new DayAndTimeSpecificLabor(name: 'paint', 
								                            laborTerm: LaborTermType.SUMMER, 
															durationInHours: 1, 
															startHour: 7, 
															day: Day.MONDAY),
								new DayAndTimeSpecificLabor(name: 'mop', 
															laborTerm: LaborTermType.SUMMER, 
															durationInHours: 1, 
															startHour: 8, 
															day: Day.MONDAY)]
		def wishList = userService.getRevisedWishList(user, originalWishList, schedulableLabors)
		assertEquals ([new FlexLabor(name: 'dishes', laborTerm: LaborTermType.SUMMER), 
					   new DaySpecificLabor(name: 'clean', laborTerm: LaborTermType.SUMMER), 
					   new DayAndTimeSpecificLabor(name: 'mop', 
												   laborTerm: LaborTermType.SUMMER, 
												   durationInHours: 1, 
												   startHour: 8, 
												   day: Day.MONDAY)], 
					  wishList)
		assertTrue user.originalWishListAndScheduleConflict
	}

	void testGetRevisedWishListWithEmptyOriginal() {
		assertEquals ([], userService.getRevisedWishList(user, [], schedulableLabors))
		assertFalse user.originalWishListAndScheduleConflict
	}
	
	void testGetRevisedWishListWithNoConflict() {
		assertEquals ([new FlexLabor(name: 'dishes', laborTerm: LaborTermType.SUMMER), 
					   new DaySpecificLabor(name: 'clean', laborTerm: LaborTermType.SUMMER), 
					   new DayAndTimeSpecificLabor(name: 'mop', laborTerm: LaborTermType.SUMMER)], 
			          userService.getRevisedWishList(user, 
													 [new FlexLabor(name: 'dishes', laborTerm: LaborTermType.SUMMER), 
													  new DaySpecificLabor(name: 'clean', 
																		   laborTerm: LaborTermType.SUMMER), 
													  new DayAndTimeSpecificLabor(name: 'mop', 
																				  laborTerm: LaborTermType.SUMMER, 
																				  durationInHours: 1, 
																				  startHour: 8, 
																				  day: Day.MONDAY)],
													 schedulableLabors))
		assertFalse user.originalWishListAndScheduleConflict
	}
	
	void testGetSchedulableLabors() {
		def laborService = new LaborService()
		laborService.metaClass.allDaySpecificLaborForCurrentTerm = {
			[new DaySpecificLabor(name: 'clean', laborTerm: LaborTermType.SUMMER)] 
		}
		laborService.metaClass.allFlexLaborForCurrentTerm = { 
			[new FlexLabor(name: 'encourage', laborTerm: LaborTermType.SUMMER)] 
		}
		laborService.metaClass.allDayAndTimeSpecificLaborForCurrentTerm = {-> 
			[new DayAndTimeSpecificLabor(name: 'compute', laborTerm: LaborTermType.SUMMER),
			 new DayAndTimeSpecificLabor(name: 'sweep', laborTerm: LaborTermType.SUMMER)] 
		}
		laborService.metaClass.conflicts = { unavailableSchedule, dayAndTimeLabor -> 
			dayAndTimeLabor.name.charAt(0) == 'c'
		}
		def fakeUserService = new UserService(laborService: laborService)
		assertEquals([new DayAndTimeSpecificLabor(name: 'sweep', laborTerm: LaborTermType.SUMMER),
					  new DaySpecificLabor(name: 'clean', laborTerm: LaborTermType.SUMMER),
					  new FlexLabor(name: 'encourage', laborTerm: LaborTermType.SUMMER)], 
					 fakeUserService.getSchedulableLabors([]))
	}
}
