import grails.test.*

class UserTests extends GrailsUnitTestCase {
	
	protected void setUp() {
		super.setUp()
	}
	
	protected void tearDown() {
		super.tearDown()
	}

	// have to figure the right pattern for room #'s
	void testRoomNumberMatchesPattern() {
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
	
	void testWishList() {
		mockDomain(User)
		def user = new User(firstName: 'johny'/*desiredFlexLabors: [new FlexLabor(), new FlexLabor()], 
							desiredDaySpecificLabors: [new DaySpecificLabor()],
							desiredDayAndTimeSpecificLabors: [new DayAndTimeSpecificLabor()]*/)
//		user.validate()
//		assertEquals 'wish list size should be fine', 
		user.addToDesiredDayAndTimeSpecificLabors(new DaySpecificLabor())
		user.addToDesiredDaySpecificLabors(new DaySpecificLabor())
		user.addToDesiredDaySpecificLabors(new DaySpecificLabor())
		user.addToDesiredDaySpecificLabors(new DaySpecificLabor())
		user.addToDesiredFlexLabors(new FlexLabor())
		assertFalse 'wish list size should be too big', user.validate()
//		user.getWishList().each() {
//			println it.class
//		}
		assertEquals 5, user.getWishList().size()
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
}
