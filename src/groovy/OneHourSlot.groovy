/**
 * @author jimenez
 *
 */
enum OneHourSlot implements Serializable {
	MONDAY_6(Day.MONDAY, 6), TUESDAY_6(Day.TUESDAY, 6),	WEDNESDAY_6(Day.WEDNESDAY, 6), THURSDAY_6(Day.THURSDAY, 6), FRIDAY_6(Day.FRIDAY, 6), SATURDAY_6(Day.SATURDAY, 6), SUNDAY_6(Day.SUNDAY, 6),
	MONDAY_7(Day.MONDAY, 7), TUESDAY_7(Day.TUESDAY, 7),	WEDNESDAY_7(Day.WEDNESDAY, 7), THURSDAY_7(Day.THURSDAY, 7),	FRIDAY_7(Day.FRIDAY, 7), SATURDAY_7(Day.SATURDAY, 7), SUNDAY_7(Day.SUNDAY, 7),
	MONDAY_8(Day.MONDAY, 8), TUESDAY_8(Day.TUESDAY, 8),	WEDNESDAY_8(Day.WEDNESDAY, 8), THURSDAY_8(Day.THURSDAY, 8),	FRIDAY_8(Day.FRIDAY, 8), SATURDAY_8(Day.SATURDAY, 8), SUNDAY_8(Day.SUNDAY, 8),
	MONDAY_9(Day.MONDAY, 9), TUESDAY_9(Day.TUESDAY, 9), WEDNESDAY_9(Day.WEDNESDAY, 9), THURSDAY_9(Day.THURSDAY, 9), FRIDAY_9(Day.FRIDAY, 9), SATURDAY_9(Day.SATURDAY, 9), SUNDAY_9(Day.SUNDAY, 9),
	MONDAY_10(Day.MONDAY, 10), TUESDAY_10(Day.TUESDAY, 10), WEDNESDAY_10(Day.WEDNESDAY, 10), THURSDAY_10(Day.THURSDAY, 10), FRIDAY_10(Day.FRIDAY, 10), SATURDAY_10(Day.SATURDAY, 10), SUNDAY_10(Day.SUNDAY, 10),
	MONDAY_11(Day.MONDAY, 11), TUESDAY_11(Day.TUESDAY, 11),	WEDNESDAY_11(Day.WEDNESDAY, 11), THURSDAY_11(Day.THURSDAY, 11), FRIDAY_11(Day.FRIDAY, 11), SATURDAY_11(Day.SATURDAY, 11), SUNDAY_11(Day.SUNDAY, 11),
	MONDAY_12(Day.MONDAY, 12), TUESDAY_12(Day.TUESDAY, 12), WEDNESDAY_12(Day.WEDNESDAY, 12), THURSDAY_12(Day.THURSDAY, 12), FRIDAY_12(Day.FRIDAY, 12), SATURDAY_12(Day.SATURDAY, 12), SUNDAY_12(Day.SUNDAY, 12),
	MONDAY_13(Day.MONDAY, 13), TUESDAY_13(Day.TUESDAY, 13), WEDNESDAY_13(Day.WEDNESDAY, 13), THURSDAY_13(Day.THURSDAY, 13), FRIDAY_13(Day.FRIDAY, 13), SATURDAY_13(Day.SATURDAY, 13), SUNDAY_13(Day.SUNDAY, 13),
	MONDAY_14(Day.MONDAY, 14), TUESDAY_14(Day.TUESDAY, 14), WEDNESDAY_14(Day.WEDNESDAY, 14), THURSDAY_14(Day.THURSDAY, 14), FRIDAY_14(Day.FRIDAY, 14), SATURDAY_14(Day.SATURDAY, 14), SUNDAY_14(Day.SUNDAY, 14),
	MONDAY_15(Day.MONDAY, 15), TUESDAY_15(Day.TUESDAY, 15), WEDNESDAY_15(Day.WEDNESDAY, 15), THURSDAY_15(Day.THURSDAY, 15), FRIDAY_15(Day.FRIDAY, 15), SATURDAY_15(Day.SATURDAY, 15), SUNDAY_15(Day.SUNDAY, 15),
	MONDAY_16(Day.MONDAY, 16), TUESDAY_16(Day.TUESDAY, 16), WEDNESDAY_16(Day.WEDNESDAY, 16), THURSDAY_16(Day.THURSDAY, 16), FRIDAY_16(Day.FRIDAY, 16), SATURDAY_16(Day.SATURDAY, 16), SUNDAY_16(Day.SUNDAY, 16),
	MONDAY_17(Day.MONDAY, 17), TUESDAY_17(Day.TUESDAY, 17), WEDNESDAY_17(Day.WEDNESDAY, 17), THURSDAY_17(Day.THURSDAY, 17), FRIDAY_17(Day.FRIDAY, 17), SATURDAY_17(Day.SATURDAY, 17), SUNDAY_17(Day.SUNDAY, 17),
	MONDAY_18(Day.MONDAY, 18), TUESDAY_18(Day.TUESDAY, 18), WEDNESDAY_18(Day.WEDNESDAY, 18), THURSDAY_18(Day.THURSDAY, 18), FRIDAY_18(Day.FRIDAY, 18), SATURDAY_18(Day.SATURDAY, 18), SUNDAY_18(Day.SUNDAY, 18),
	MONDAY_19(Day.MONDAY, 19), TUESDAY_19(Day.TUESDAY, 19), WEDNESDAY_19(Day.WEDNESDAY, 19), THURSDAY_19(Day.THURSDAY, 19), FRIDAY_19(Day.FRIDAY, 19), SATURDAY_19(Day.SATURDAY, 19), SUNDAY_19(Day.SUNDAY, 19),
	MONDAY_20(Day.MONDAY, 20), TUESDAY_20(Day.TUESDAY, 20), WEDNESDAY_20(Day.WEDNESDAY, 20), THURSDAY_20(Day.THURSDAY, 20), FRIDAY_20(Day.FRIDAY, 20), SATURDAY_20(Day.SATURDAY, 20), SUNDAY_20(Day.SUNDAY, 20),
	MONDAY_21(Day.MONDAY, 21), TUESDAY_21(Day.TUESDAY, 21), WEDNESDAY_21(Day.WEDNESDAY, 21), THURSDAY_21(Day.THURSDAY, 21), FRIDAY_21(Day.FRIDAY, 21), SATURDAY_21(Day.SATURDAY, 21), SUNDAY_21(Day.SUNDAY, 21),
	MONDAY_22(Day.MONDAY, 22), TUESDAY_22(Day.TUESDAY, 22), WEDNESDAY_22(Day.WEDNESDAY, 22), THURSDAY_22(Day.THURSDAY, 22), FRIDAY_22(Day.FRIDAY, 22), SATURDAY_22(Day.SATURDAY, 22), SUNDAY_22(Day.SUNDAY, 22)
	
	private final Day day 
	private final int startHour
	
	OneHourSlot(Day day, int startHour) {
		this.day = day
		this.startHour = startHour
	}
	
	def getDay() {
		day
	}
	
	def getStartHour() {
		startHour
	}
	
	def getCivilianStartHour() {
		getCivilianHour(startHour)
	}
	
	def getEndHour() {
		startHour + 1
	}
	
	def getCivilianEndHour() {
		getCivilianHour(getEndHour())
	}
	
	static getCivilianHour(hour) {
		hour - 12 * (int) (hour / 13) + (hour < 12 ? 'am' : 'pm')
	}
	   
	static generateOneHourSlots = { day, startHour, durationInHours -> 
		def oneHourSlots = []
		for (oneHourSlotStartHour in startHour..(startHour + durationInHours - 1)) {
			oneHourSlots.add(valueOf(day.name() + '_' + oneHourSlotStartHour))
		}
		oneHourSlots
	}
}
