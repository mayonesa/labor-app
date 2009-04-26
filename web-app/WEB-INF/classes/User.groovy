class User {
	String firstName
	String lastName
	String roomNumber
	String username
	String password
	Role role = Role.REGULAR
	List<OneHourSlot> unavailableSchedule
	String comment
	boolean savedWishListAndScheduleConflict
	
	static hasMany = [desiredFlexLabors: FlexLabor, 
	                  assignedFlexLabors: FlexLabor, 
	                  desiredDaySpecificLabors: DaySpecificLabor, 
	                  assignedDaySpecificLabors: DaySpecificLabor, 
	                  desiredDayAndTimeSpecificLabors: DayAndTimeSpecificLabor, 
	                  assignedDayAndTimeSpecificLabors: DayAndTimeSpecificLabor]
	static mappedBy = [desiredFlexLabors: 'wishers', 
	                   assignedFlexLabors: 'assignees', 
	                   desiredDaySpecificLabors: 'wishers', 
	                   assignedDaySpecificLabors: 'assignees', 
	                   desiredDayAndTimeSpecificLabors: 'wishers', 
	                   assignedDayAndTimeSpecificLabors: 'assignees']
	
	static constraints = {
		firstName(blank:false)
		lastName(blank:false)
		
		// should figure the proper pattern for room #'s
		roomNumber(blank:false, matches:/[0-5][0-2][0-9]/)
		
		username(blank:false, unique:true, minLength:6)
		password(blank:false, minLength:6)
		role(nullable:false)
		unavailableSchedule nullable: true
	}
	
	static transients = ['savedWishListAndScheduleConflict']
	
	def getWishList() {
		Utils.concatenate desiredDayAndTimeSpecificLabors, desiredDaySpecificLabors, desiredFlexLabors
	}
}
