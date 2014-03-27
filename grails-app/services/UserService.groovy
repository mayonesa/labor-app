class UserService {

	def laborService,
	    currentLaborTermService
	
    boolean transactional = true
    
    def getRevisedWishList = { user, originalWishList, schedulableLabors ->
    	def corroboratedWishList = originalWishList.findAll { schedulableLabors.contains it }
		user.originalWishListAndScheduleConflict = false
   		if (originalWishList.size() != corroboratedWishList.size()) {
    		user.originalWishListAndScheduleConflict = true
    	}
    	corroboratedWishList
    }
    
    def getSchedulableLabors = { unavailableSchedule ->
    	Utils.concatenate(laborService.allDayAndTimeSpecificLaborForCurrentTerm().findAll { labor ->
			!laborService.conflicts(unavailableSchedule, labor)
    	}, laborService.allDaySpecificLaborForCurrentTerm(), laborService.allFlexLaborForCurrentTerm())
	}
}
