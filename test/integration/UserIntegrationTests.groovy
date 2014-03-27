import grails.test.*

class UserIntegrationTests extends GroovyTestCase {
	
	def dbAdmin, john, george
	
    protected void setUp() {
        super.setUp()
    	dbAdmin = new FlexLabor(name: 'db admin', laborTerm: LaborTermType.SUMMER, durationInHours: 2)
    	john = new User(firstName: 'john', lastName: 'jimenez', roomNumber:'000', username:'jdoe001', password:'passwd1')
    	john.addToWishList(dbAdmin)
    	john.addToWishList(new DayAndTimeSpecificLabor(name: 'dishes', laborTerm: LaborTermType.SUMMER, durationInHours: 2, day: Day.MONDAY, startHour: 12, numberOfLaborers: 2))
    	john.save()
    	george = new User(firstName: 'george', lastName: 'jimenez', roomNumber:'000', username:'jdoe002', password:'passwd1')
    	george.addToWishList(dbAdmin)
    	george.save()
    }

    protected void tearDown() {
        super.tearDown()
    }
	
    void testUserLabor() {
    	def usersFromDb = User.list()
    	assertEquals 2, usersFromDb.size()
    	def johnFromDb = usersFromDb.find { user ->
			user.firstName == 'john'
		}
    	def wishList = johnFromDb.wishList
    	assertEquals 2, wishList.find { labor ->
			labor.name == 'db admin'
		}.getWishers().size()
    	
    	def dbAdminWishers = dbAdmin.wishers
    	assertEquals 2, dbAdminWishers.size()
    }

	void testWishList() {
		assertEquals 1, george.getWishList().size()
		assertEquals 2, john.getWishList().size()
	}
}
