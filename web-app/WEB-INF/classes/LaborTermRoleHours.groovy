class LaborTermRoleHours {
	
	LaborTermType laborTerm
	Role role
	Integer hours
	
    static constraints = {
		laborTerm(nullable:false)
		role(nullable:false)
		hours(nullable:false, range:0..17)
    }
}
