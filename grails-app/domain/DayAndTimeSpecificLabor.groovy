class DayAndTimeSpecificLabor extends AbstractDaySpecificLabor {
	
	Integer startHour

	static constraints = {
		startHour(range: 6..22)
    }

	boolean equals(labor) {
		super.equals(labor) && startHour == labor.startHour
	}
	
	int hashCode() {
		super.hashCode() * startHour
	}
}
