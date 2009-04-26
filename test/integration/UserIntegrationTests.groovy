import grails.test.*

class UserIntegrationTests extends GroovyTestCase {
    void testUserLabor() {
    	def dbAdmin = new FlexLabor(name: 'db admin', laborTerm: LaborTermType.SUMMER, durationInHours: 2)
    	def john = new User(firstName: 'john', lastName: 'jimenez', roomNumber:'000', username:'jdoe001', password:'passwd1')
    	john.addToDesiredFlexLabors(dbAdmin)
    	john.addToDesiredDaySpecificLabors(new DayAndTimeSpecificLabor(name: 'dishes', laborTerm: LaborTermType.SUMMER, durationInHours: 2, day: Day.MONDAY, startTime: 12, numberOfLaborers: 2))
    	john.save(flush: true)
    	
    	def usersFromDb = User.list()
    	assertEquals 1, usersFromDb.size()
    	def johnFromDb = usersFromDb.get(0)
    	def desiredFlexLabors = johnFromDb.getDesiredFlexLabors()
    	desiredFlexLabors.each() {
    		assertEquals FlexLabor.class, it.class
    	}
    	def desiredDaySpecificLabors = johnFromDb.desiredDaySpecificLabors  	
       	desiredDaySpecificLabors.each() {
    		assertEquals DayAndTimeSpecificLabor.class, it.class
    	}
    	desiredDaySpecificLabors.each() {
    		it.getWishers().each() { wisher ->
    			assertEquals 'john', wisher.firstName
    		}
    	}
    	def george = new User(firstName: 'george', lastName: 'jimenez', roomNumber:'000', username:'jdoe001', password:'passwd1')
    	george.addToDesiredFlexLabors(dbAdmin)
    	george.save(flush:true)
    	
    	def dbAdminWishers = dbAdmin.wishers
    	assertEquals 2, dbAdminWishers.size()
    }
}
