class AbstractLabor implements Serializable {
	
	String name
	LaborTermType laborTerm
	Integer durationInHours
	Integer laborerCapacity = 1

	static hasMany = [wishers:User, assignees:User]
	static belongsTo = User
	
    static constraints = {
		name(blank:false)
		laborTerm(nullable:false)
		durationInHours(nullable:false, range:0..17)
		laborerCapacity(range:1..200)
    }

	boolean equals(labor) {
		getClass().isInstance(labor) && labor.name == name && labor.laborTerm == laborTerm
	}
	
	int hashCode() {
		name.charAt(0) * laborTerm.ordinal() - name.charAt(1)
	}

	def filled = { 
		assert assignees.size() <= laborerCapacity
		assignees?.size() == laborerCapacity
	}
}
