abstract class AbstractLabor {
	
	String name
	LaborTermType laborTerm
	Integer durationInHours
	
	static hasMany = [wishers:User, assignees:User]
	static belongsTo = User
	
    static constraints = {
		name(blank:false)
		laborTerm(nullable:false)
		durationInHours(nullable:false, range:0..17)
    }
	
    static mapping = { 
        tablePerHierarchy false 
    } 
}
