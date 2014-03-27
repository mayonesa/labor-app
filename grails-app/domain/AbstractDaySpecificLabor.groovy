class AbstractDaySpecificLabor extends AbstractLabor {
	
	Day day
	
    static constraints = {
		day(nullable:true)
    }

	boolean equals(labor) {
		super.equals(labor) && day == labor.day
	}
	
	int hashCode() {
		super.hashCode() + day.ordinal()
	}
}
