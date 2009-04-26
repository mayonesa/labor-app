class LaborService {
	
	def userService

    boolean transactional = true

    def possibleLaborers = { labor ->
    	def usersNeedingLabor = userService.usersNeedingLabor()
    	if (labor instanceof DayAndTimeSpecificLabor) {
    		def laborOneHourSlots = generateOneHourSlots(labor)
    		usersNeedingLabor.collect() { userNeedingLabor ->
    			laborOneHourSlots.each() { laborOneHourSlot ->
    				if (userNeedingLabor.unavailableSchedule.contains(laborOneHourSlot)) {
    					return userNeedingLabor
    				}
    			}
    		}
    	} else {
    		usersNeedingLabor
    	}
    }
    
    def conflicts = { user, dayAndTimeLabor ->
    	generateOneHourSlots(dayAndTimeLabor).each() {
    	    if (user.unavailableSchedule.contains(it)) {
    			return true
    		}
    	}
		false
    }

	def generateOneHourSlots = { dayAndTimeLabor ->
		OneHourSlots.generateOneHourSlots dayAndTimeLabor.day, dayAndTimeLabor.startHour,dayAndTimeLabor.durationInHours
	}
}
