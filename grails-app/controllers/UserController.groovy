class UserController {
	
	public static final WISH_LIST_PARAM_PREPEND = 'labor_'

	def userService
	
	static final NEXT = 'next'
	static final BACK = 'back'
	static final SAVE = 'save'
	static final FINISH = 'finish'

    def index = {
		redirect action: 'laborRegistration'
	}
	
	def launchPad = { }
	
	def laborRegistrationFlow = {
		populateUnavailableSchedule {
			action {
				this.populateUnavailableScheduleStateTransients(flow)
				[unavailableSchedule: this.getUser().unavailableSchedule]
			}
			on('success').to 'showUnavailableSchedule'
		}
		showUnavailableSchedule {
			on(NEXT) { 
				def unavailbleSchedStr = params.unavailableScheduleStr ?: ''
				flow.unavailableSchedule = unavailbleSchedStr.split().collect { oneHourSlotStr ->
					OneHourSlot.valueOf oneHourSlotStr
				}
			}.to 'populateWishListInfo'
		}
		populateWishListInfo {
			action {
				def user = this.getUser()
				def schedulableLabors = userService.getSchedulableLabors(flow.unavailableSchedule)
				def wishList = userService.getRevisedWishList(user, flow.wishList ?: user.wishList, schedulableLabors)
				def availableLabors = schedulableLabors.findAll { !wishList.contains(it) }
				def comment = flow.comment ?: user.comment
				this.populateWishListStateTransients(flow)
				[wishList: wishList, availableLabors: availableLabors, comment: comment, user: user]
			}
			on('success').to 'showWishList'
		}
		showWishList {
			on(NEXT) {
				if (flow.user.validate()) {
					return success()
				}
				return error()
			}.to 'review'
			on(BACK).to 'fromWishListToUnavailableSchedule'
		}
		review {
			action {
				this.wishListInfoIntoFlow(flow)
				this.populateReviewStateTransients(flow)
			}
			on('success').to 'showReview'
		}
		fromWishListToUnavailableSchedule {
			action {
				this.wishListInfoIntoFlow(flow)
				this.populateUnavailableScheduleStateTransients(flow)
			}
			on('success').to 'showUnavailableSchedule'
		}
		showReview {
			on('reviseUnavailableSchedule').to 'fromReviewToUnavailableSchedule'
			on('reviseWishList').to 'fromReviewToWishList'			
			on(SAVE) {
				def user = this.getUser()
				user.unavailableSchedule = flow.unavailableSchedule
				user.wishList = flow.wishList
				user.comment = flow.comment
				if (user.validate()) {
					user.save()
					this.populateConfirmationStateTransients(flow) 
					success()
				}
				else {
					error()
				}
			}.to 'showConfirmation'
			on(BACK).to 'fromReviewToWishList'
		}
		fromReviewToWishList {
			action {
				this.populateWishListStateTransients(flow)
			}
			on('success').to 'showWishList'
		}
		fromReviewToUnavailableSchedule {
			action {
				this.populateUnavailableScheduleStateTransients(flow)
			}
			on('success').to 'showUnavailableSchedule'
		}
		showConfirmation {
			on(FINISH).to 'logout'
		}
		logout()
	}

	private getUser = {
//		session.user
		User.get(1)
	}
	
	private wishListInfoIntoFlow = { flow ->
		flow.wishList = params[MyDragAndDropTagLib.SELECTED_IDS].split().collect {
			AbstractLabor.get(it[WISH_LIST_PARAM_PREPEND.length()..<it.length()])
		}
		flow.comment = params.comment
	}
	
	private populateUnavailableScheduleStateTransients = { flow ->
		populateStateTransients(StateTransients.UNAVAILABLE_SCHEDULE, flow)
	}
	
	private populateWishListStateTransients = { flow ->
		populateStateTransients(StateTransients.WISH_LIST, flow)
	}
	
	private populateReviewStateTransients = { flow ->
		populateStateTransients(StateTransients.REVIEW, flow)
	}
	
	private populateConfirmationStateTransients = { flow ->
		populateStateTransients(StateTransients.CONFIRMATION, flow)
	}
	
	private populateStateTransients = { stateTransients, flow ->
		stateTransients.populate(flow)
	}	
}

enum StateTransients {
	UNAVAILABLE_SCHEDULE(null, null, UserController.NEXT, 'Continue', 'unavailableSchedule.header', 'unavailableSchedule.instructions', null, 'controller.prepForSubmit()'),
	WISH_LIST(UserController.BACK, 'Back', UserController.NEXT, 'Continue', 'wishList.header', 'wishList.instructions', [User.WISH_LIST_LIMIT], null),
	REVIEW(UserController.BACK, 'Back', UserController.SAVE, 'Register', 'review.header', 'review.instructions', null, null),
	CONFIRMATION(null, null, UserController.FINISH, 'Logout', 'confirmation.header', 'confirmation.instructions', null, null)
	
	private leftName,
		    leftValue,
		    rightName,
		    rightValue,
		    header,
			instructions,
			instructionArgs,
			onSubmitJs
	
	private StateTransients(leftName, leftValue, rightName, rightValue, header, instructions, instructionArgs, onSubmitJs) {
		this.leftName = leftName
		this.leftValue = leftValue
		this.rightName = rightName
		this.rightValue = rightValue
		this.header = header
		this.instructions = instructions
		this.instructionArgs = instructionArgs
		this.onSubmitJs = onSubmitJs
	}
	
	def populate = { flow ->
		flow.leftName = leftName
		flow.leftValue = leftValue
		flow.rightName = rightName
		flow.rightValue = rightValue
		flow.header = header
		flow.instructions = instructions
		flow.instructionArgs = instructionArgs
		flow.onSubmitJs = onSubmitJs
	}	
}
