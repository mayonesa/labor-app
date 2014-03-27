import grails.test.*

class OneHourSlotTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
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
					  OneHourSlot.generateOneHourSlots(Day.MONDAY, 11, 11))
		assertEquals ([OneHourSlot.MONDAY_11], OneHourSlot.generateOneHourSlots(Day.MONDAY, 11, 1))
    }

	void testGetCivilianStartHour() {
		assertEquals '11am', OneHourSlot.MONDAY_11.getCivilianStartHour()
		assertEquals '12pm', OneHourSlot.MONDAY_12.getCivilianStartHour()
		assertEquals '1pm', OneHourSlot.MONDAY_13.getCivilianStartHour()
	}

	void testGetCivilianEndHour() {
		assertEquals '11am', OneHourSlot.MONDAY_10.getCivilianEndHour()
		assertEquals '12pm', OneHourSlot.MONDAY_11.getCivilianEndHour()
		assertEquals '11pm', OneHourSlot.MONDAY_22.getCivilianEndHour()
	}
}
