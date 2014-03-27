class LaborService {
	
	def laborTermRoleHoursService,
		currentLaborTermService
	
    boolean transactional = true

	def allFlexLaborForCurrentTerm = {
		FlexLabor.findAllByLaborTerm(currentLaborTermService.getLaborTerm())
	}

	def allDaySpecificLaborForCurrentTerm = {
		DaySpecificLabor.findAllByLaborTerm(currentLaborTermService.getLaborTerm())
	}

	def allDayAndTimeSpecificLaborForCurrentTerm = {
		DayAndTimeSpecificLabor.findAllByLaborTerm(currentLaborTermService.getLaborTerm())
	}

    def possibleLaborers = { labor ->
    	def usersNeedingLabor = usersNeedingLabor()
    	if (labor instanceof DayAndTimeSpecificLabor) {
    		def laborOneHourSlots = generateOneHourSlots(labor)
    		usersNeedingTheseManyLaborHours(labor).findAll { userNeedingLabor ->
		    	noConflictProcessor(userNeedingLabor.unavailableSchedule, laborOneHourSlots)
			}
    	} 
		else {
    		usersNeedingLabor
    	}
    }

	private noConflictProcessor = { unavailableSchedule, oneHourSlots ->
    	conflictProcessor(unavailableSchedule, oneHourSlots, true)
	}
    
    def conflicts = { unavailableSchedule, dayAndTimeLabor ->
    	conflictProcessor(unavailableSchedule, generateOneHourSlots(dayAndTimeLabor))
    }

	private def conflictProcessor = { unavailableSchedule, oneHourSlots, conflictNegation = false ->
		oneHourSlots.each() { oneHourSlot ->
			if (unavailableSchedule.contains(oneHourSlot)) {
				conflictNegation = !conflictNegation
				return
			}	
		}
		conflictNegation
	}

	def generateOneHourSlots = { dayAndTimeLabor ->
		OneHourSlot.generateOneHourSlots dayAndTimeLabor.day, dayAndTimeLabor.startHour, dayAndTimeLabor.durationInHours
	}
    
    private usersNeedingTheseManyLaborHours = { labor ->
		allUserHourNeedsProcessor { hoursNeeded ->
			hoursNeeded >= labor.durationInHours
		}
    } 

	def usersNeedingLabor = {
		allUserHourNeedsProcessor { hoursNeeded ->
			hoursNeeded > 0
		}
	}

    private allUserHourNeedsProcessor = { userHourNeedProcess ->
		User.list()?.findAll { user ->
			userHourNeedProcess(hoursNeeded(user))
		}
	}
	
	private hoursNeeded = { user ->
		getHoursRequired(user) - getAssignedLaborHours(user)
	}

	def getAssignedLaborHours(user) {
		user.wishList?.sum { labor ->
			labor.durationInHours
		} ?: 0
	}
	
    def getHoursRequired(user) {
    	laborTermRoleHoursService.getRoleHoursRequired(user.role)
    }
}