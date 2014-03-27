import grails.test.*

class LaborServiceTests extends GrailsUnitTestCase {
    void setUp() {
        super.setUp()
    }

    void tearDown() {
        super.tearDown()
    }

    void testGenerateOneHourSlots() {
		assertEquals ([OneHourSlot.MONDAY_11, 
					   OneHourSlot.MONDAY_12,
					   OneHourSlot.MONDAY_13, 
					   OneHourSlot.MONDAY_14, 
					   OneHourSlot.MONDAY_15, 
					   OneHourSlot.MONDAY_16, 
					   OneHourSlot.MONDAY_17, 
					   OneHourSlot.MONDAY_18,
					   OneHourSlot.MONDAY_19,
					   OneHourSlot.MONDAY_20,
					   OneHourSlot.MONDAY_21], 
					  new LaborService().generateOneHourSlots(new DayAndTimeSpecificLabor(day: Day.MONDAY, 
																						  startHour: 11, 
																						  durationInHours: 11)))
    }

	void testGetAssignedLaborHours() {
		mockDomain(User)
		def user = new User()
		user.role = Role.REGULAR
		user.addToWishList(new FlexLabor(name: 'culipunteando', laborTerm: LaborTermType.SUMMER, durationInHours: 3))
		user.addToWishList(new DayAndTimeSpecificLabor(name: 'nada', laborTerm: LaborTermType.SUMMER, durationInHours: 2))
		assertEquals 5, new LaborService().getAssignedLaborHours(user)
	}
	
	void testGetAssignedLaborHoursWithNotLabor() {
		mockDomain(User)
		def user = new User()
		assertEquals 0, new LaborService().getAssignedLaborHours(user)
	}

	void testUsersNeedingLabor() {
		mockDomain(User)
		def pedro = new User()
		pedro.firstName = 'Pedro'
		pedro.lastName = 'Garzon'
		pedro.roomNumber = '123'
		pedro.username = 'pgarzon'
		pedro.password = '622ew543'
		pedro.save()
		def juan = new User()
		juan.firstName = 'Juan'
		juan.lastName = 'Cuchillo'
		juan.roomNumber = '002'
		juan.username = 'jcuchillo'
		juan.password = '654sss3'
		juan.save()
		def toston = new User()
		toston.firstName = 'Toston'
		toston.lastName = 'Jimenez'
		toston.roomNumber = '302'
		toston.username = 'tjimenez'
		toston.password = '6fjsldfj543'
		toston.save()
		def pepin = new User()
		pepin.firstName = 'Pepin'
		pepin.lastName = 'Jimenez'
		pepin.roomNumber = '302'
		pepin.username = 'kpepinji'
		pepin.password = '6fjsldfj543'
		pepin.role = Role.OFFICER
		pepin.save()
		
		def tito = new User()
		tito.firstName = 'Tito'
		tito.lastName = 'Titinillo'
		tito.roomNumber = '003'
		tito.username = 'ittinino'
		tito.password = '6dsjflf543'
		tito.save()
		
		def laborService = new LaborService()
		laborService.metaClass.getAssignedLaborHours = { user ->
			switch(user.username.charAt(0)) {
				case 'p': return 3
				case 'j':return  4
				case 't': return 2
				case 'k': return 1
				case 'i': return 0
			}
		}
		laborService.metaClass.getHoursRequired = { user ->
			switch(user.username.charAt(0)) {
				case 'p': return 4
				case 'j': return 4
				case 't': return 4
				case 'k': return 0
				case 'i': return 4
			}
		}
		def usersNeedingLabor = laborService.usersNeedingLabor()
		assertEquals 3, usersNeedingLabor.size()
		assertTrue usersNeedingLabor.contains(pedro)
		assertTrue usersNeedingLabor.contains(toston)
		assertTrue usersNeedingLabor.contains(tito)
	}
	
	void testConflicts() {
		def user = new User(unavailableSchedule: [OneHourSlot.MONDAY_8, OneHourSlot.MONDAY_9])

		def mon_6_3 = new DayAndTimeSpecificLabor(name: 'dishes', laborTerm: LaborTermType.SUMMER, startHour: 6, durationInHours: 3, day: Day.MONDAY)
		def mon_6_1 = new DayAndTimeSpecificLabor(name: 'sweep', laborTerm: LaborTermType.SUMMER, startHour: 6, durationInHours: 1, day: Day.MONDAY)
		
		def laborService = new LaborService()
		assertTrue laborService.conflicts(user.unavailableSchedule, mon_6_3)
		assertFalse laborService.conflicts(user.unavailableSchedule, mon_6_1)
	}
	
	void testPossibleLaborers() {
		mockDomain(User)
		def pedro = new User()
		pedro.firstName = 'Pedro'
		pedro.lastName = 'Garzon'
		pedro.roomNumber = '123'
		pedro.username = 'pgarzon'
		pedro.password = '622ew543'
		pedro.save()
		def juan = new User()
		juan.firstName = 'Juan'
		juan.lastName = 'Cuchillo'
		juan.roomNumber = '002'
		juan.username = 'jcuchillo'
		juan.password = '654sss3'
		juan.save()
		def toston = new User()
		toston.firstName = 'Toston'
		toston.lastName = 'Jimenez'
		toston.roomNumber = '302'
		toston.username = 'tjimenez'
		toston.password = '6fjsldfj543'
		toston.save()
		def pepin = new User()
		pepin.firstName = 'Pepin'
		pepin.lastName = 'Jimenez'
		pepin.roomNumber = '302'
		pepin.username = 'kpepinji'
		pepin.password = '6fjsldfj543'
		pepin.role = Role.OFFICER
		pepin.save()
		
		def tito = new User()
		tito.firstName = 'Tito'
		tito.lastName = 'Titinillo'
		tito.roomNumber = '003'
		tito.username = 'ittinino'
		tito.password = '6dsjflf543'
		tito.save()
		
		def laborService = new LaborService()
		laborService.metaClass.getAssignedLaborHours = { user ->
			switch(user.username.charAt(0)) {
				case 'p': return 3
				case 'j': return 4
				case 't': return 2
				case 'k': return 1
				case 'i': return 0
			}
		}
		laborService.metaClass.getHoursRequired = { user ->
			switch(user.username.charAt(0)) {
				case 'p': return 4
				case 'j': return 4
				case 't': return 4
				case 'k': return 3
				case 'i': return 4
			}
		}
		
		laborService.metaClass.generateOneHourSlots = { [] }
		laborService.metaClass.conflictProcessor = { unavailableSched, slot, bool -> true }
		def possibleLaborers = laborService.possibleLaborers(new DayAndTimeSpecificLabor(durationInHours: 2))
		assertEquals 3, possibleLaborers.size()
		assertTrue 'Pedro should be a possible laborer', possibleLaborers.contains(toston)
		assertTrue 'Pepin should be a possible laborer', possibleLaborers.contains(pepin)
		assertTrue 'Tito should be a possible laborer', possibleLaborers.contains(tito)
	}
}
