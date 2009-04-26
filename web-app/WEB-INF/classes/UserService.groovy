class UserService {

	def laborTermRoleHoursService,
	    laborService
	
    boolean transactional = true

    def getLaborAvailableForWishList = { user, schedulableLabor, corroboratedWishList ->
    	Utils.concatenate schedulableLabor - corroboratedWishList,
    					  user.desiredDaySpecificLabors,
    					  desiredFlexLabors
    }
    
    def getWishList = { user, schedulableLabors ->
    	def savedWishList = user.getWishList
    	def corroboratedWishList = savedWishList - schedulableLabors
    	if (savedWishList.size() != corroboratedWishList) {
    		user.savedWishListAndScheduleConflict = true
    	}
    	corroboratedWishList
    }
    
    def getSchedulableList = { user ->
    	DayAndTimeSpecificLabor.list().collect() {
    		if (!laborService.conflicts(user, it)) {
    			it
    		}
    	}
    }
    
    def usersNeedingLabor() {
    	User.list().collect() {
    		if (getHoursRequired(it) > getAssignedLaborHours(it)) {
    			it
    		}
    	}
    } 
    
    def getAssignedLaborHours(user) {
    	def assignedLaborHours = 0
    	Utils.process ({ labor ->
    						assignedLaborHours += labor.durationInHours
    				   }, 
    				   user.assignedDayAndTimeSpecificLabors, 
    				   user.assignedDaySpecificSpecificLabors, 
    				   user.assignedFlexLabors)
    	assignedLaborHours
    }
    
    def getHoursRequired(user) {
    	laborTermRoleHoursService.getRoleHoursRequired(user.role)
    }
}
