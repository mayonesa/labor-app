import grails.test.*

class LaborRegistrationControllerTests extends ControllerUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testIndex() {
		mockController(UserController)
		assertEquals 'I am labor registration flow', new UserController(laborRegistrationFlow: { render 'I am labor registration flow' }).index()
    }
}
