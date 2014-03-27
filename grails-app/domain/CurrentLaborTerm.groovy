class CurrentLaborTerm implements Serializable {
	LaborTermType laborTerm
	
	static constraints = {
		laborTerm(nullable:false)
	}
}
