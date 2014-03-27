import grails.test.*

class AbstractLaborTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testEquals() {
		def mop = new FlexLabor(name: 'mop', laborTerm: LaborTermType.SUMMER)
		def dishes = new FlexLabor(name: 'dishes', laborTerm: LaborTermType.SUMMER)
		def clean = new FlexLabor(name: 'clean', laborTerm: LaborTermType.SUMMER)
		def mopFallSpring = new FlexLabor(name: 'mop', laborTerm: LaborTermType.FALL_SPRING)
		def mopFallSpringDay = new DaySpecificLabor(name: 'mop', laborTerm: LaborTermType.FALL_SPRING)
		assertTrue mop.equals(new FlexLabor(name: 'mop', laborTerm: LaborTermType.SUMMER))
		assertFalse mop.equals(dishes)
		assertFalse mop.equals(mopFallSpring)
		assertFalse mopFallSpring.equals(mopFallSpringDay)
    }

	void testFilledWithThree() {
		mockDomain(AbstractLabor)
		def labor = new AbstractLabor()
		labor.name = 'sweep'
		labor.durationInHours = 4
		labor.laborTerm = LaborTermType.SUMMER
		labor.assignees = [new User(firstName:"qwerty", lastName: "asdfg", roomNumber: "000", username: 'qawsed'), 
						   new User(firstName:"qw23ty", lastName: "as2eg", roomNumber: "020", username: 'dsfafvzd'), 
						   new User(firstName:"dffv", lastName: "ddd", roomNumber: "001", username: 'fpewfes')]
		labor.laborerCapacity = 3
		assertTrue labor.filled()
		labor.laborerCapacity = 4
		assertFalse labor.filled()
		labor.laborerCapacity = 2
		shouldFail (AssertionError) { labor.filled() }
	}
}
