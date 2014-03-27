class User implements Serializable {
	
	public static final WISH_LIST_LIMIT = 4
	public static final COMMENT_MAX_LENGTH = 256
	
	String firstName
	String lastName
	String roomNumber
	String username
	String password
	Role role = Role.REGULAR
	Set<OneHourSlot> unavailableSchedule
	String comment
	boolean originalWishListAndScheduleConflict
	
	static hasMany = [wishList: AbstractLabor, 
	                  assignedLabors: AbstractLabor]
	static mappedBy = [wishList: 'wishers', 
	                   assignedLabors: 'assignees']
	
	static constraints = {
		firstName(blank:false)
		lastName(blank:false)
		
		// should figure the proper pattern for room #'s
		roomNumber(blank:false, matches:/[0-5][0-2][0-9]/)
		
		username(blank:false, unique:true, minLength:6)
		password(blank:false, minLength:6)
		role(nullable:false)
		unavailableSchedule nullable: true
		comment nullable: true, maxLength: COMMENT_MAX_LENGTH
		unavailableSchedule nullable: true
		wishList maxSize: WISH_LIST_LIMIT
	}
	
	static transients = ['savedWishListAndScheduleConflict']
	
	boolean equals(User user) {
		(user && user instanceof User && user.firstName == firstName && user.lastName == lastName 
		&& user.roomNumber == roomNumber)
	}
	
	int hashCode() {
		lastName.length() - firstName.length() * roomNumber.charAt(0) + username.charAt(5)
	}
}
