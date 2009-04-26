class LaborTermRoleHoursService {

	private roleHoursRequiredMap
	
    boolean transactional = true

    def getRoleHoursRequired(role) {
    	if (!roleHoursRequiredMap) {
    		buildRoleHoursRequiredMap()
    	}
    	roleHoursRequiredMap.role    		
    }
	
	def buildRoleHoursRequiredMap() {
		roleHoursRequiredMap = [:]
		LaborTermRoleHoursService.list().each() {
			roleRoleHourRequiredMap.put(it.role, it.hours)
		}
	}
}
