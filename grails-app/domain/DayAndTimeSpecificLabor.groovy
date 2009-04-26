class DayAndTimeSpecificLabor extends DaySpecificLabor {
	
	Integer startTime
	Integer numberOfLaborers = 1

	static constraints = {
		startTime(nullable:false, range:6..22)
		numberOfLaborers(nullable:false, range:1..200)
    }
}
