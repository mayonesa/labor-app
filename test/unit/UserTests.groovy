import grails.test.*

class UserTests extends GrailsUnitTestCase {
	
	protected void setUp() {
		super.setUp()
	}
	
	protected void tearDown() {
		super.tearDown()
	}

	// have to figure the right pattern for room #'s
/*	void testRoomNumberMatchesPattern() {
		mockDomain(User)
		def user = new User(firstName:'john', lastName:'doe', roomNumber:'000', username:'jdoe001', password:'passwd1')
		
		assertTrue 'room number should match pattern', user.validate()
	}

	void testRoomNumberDoesNotMatchPattern() {
		mockDomain(User)
		def user = new User(roomNumber:'600')
		
		assertFalse 'room number should not match pattern', user.validate()

		def roomError = user.errors.getFieldError('roomNumber')
		assertNotNull roomError
		assertTrue 'user.roomNumber.matches.invalid' in roomError.codes
		assertEquals 'matches',user.errors.roomNumber
	}
*/	
	void testWishList() {
		mockDomain(User)
		def user = new User(firstName: 'pedro', lastName: 'garcia', roomNumber: '111', username: 'something', password: 'something')
		user.addToWishList(new DaySpecificLabor(name: 'dishes', laborTerm: LaborTermType.SUMMER))
		user.addToWishList(new DaySpecificLabor(name: 'clean', laborTerm: LaborTermType.SUMMER))
		user.addToWishList(new DaySpecificLabor(name: 'program', laborTerm: LaborTermType.SUMMER))
		user.addToWishList(new FlexLabor(name: 'cook', laborTerm: LaborTermType.SUMMER))
		assertTrue 'wish list size not too big', user.validate()
		assertEquals 4, user.wishList.size()
		user.addToWishList(new DayAndTimeSpecificLabor(name: 'cook', laborTerm: LaborTermType.SUMMER))
		assertEquals 5, user.wishList.size()
		assertFalse 'wish list should be too big', user.validate() 
	}
		
//	void testWishListLimit() {
//		mockDomain User
//		def user = new User()
//		user.addToDesiredDayAndTimeSpecificLabors(new DayAndTimeSpecificLabor())
//		user.addToDesiredDaySpecificLabors(new DaySpecificLabor())
//		user.addToDesiredDaySpecificLabors(new DaySpecificLabor())
//		user.addToDesiredDaySpecificLabors(new DaySpecificLabor())
//		user.addToDesiredFlexLabors(new FlexLabor())
//		
//		assertFalse user.validate()
//		println user.errors
//		assertEquals 'maxSize', user.errors.wishList
//	}
	
	void testRole() {
		mockDomain User
		def user = new User()
		assertEquals Role.REGULAR, user.role
	}
	
	void testEquals() {
		def pedro = new User(firstName: 'pedro', lastName: 'garcia', roomNumber: '444', username: 'something')
		def pedro2 = new User(firstName: 'pedro', lastName: 'garcia', roomNumber: '444', username: 'pgarcia444')
		def someoneElse = new User(firstName: 'someone', lastName: 'garcia', roomNumber: '444')
		assertTrue pedro == pedro2
		assertFalse pedro == someoneElse
		assertFalse pedro2 == someoneElse
	}
}
