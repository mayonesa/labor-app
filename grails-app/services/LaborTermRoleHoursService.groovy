class LaborTermRoleHoursService {

	def currentLaborTermService

	private roleHoursRequiredMap
	
    boolean transactional = true

    def getRoleHoursRequired(role) {
    	if (!roleHoursRequiredMap) {
    		buildRoleHoursRequiredMap()
    	}
   		roleHoursRequiredMap[role]    		
    }
	
	private def buildRoleHoursRequiredMap() {
		roleHoursRequiredMap = [:]
		LaborTermRoleHours.findAllByLaborTerm(currentLaborTermService.getLaborTerm()).each() {
			roleHoursRequiredMap.put(it.role, it.hours)
		}
	}
}
