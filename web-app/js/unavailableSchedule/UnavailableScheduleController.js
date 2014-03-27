function UnavailableScheduleController(unavailableScheduleModel) { 
	this.unavailableScheduleModel = unavailableScheduleModel;
}

UnavailableScheduleController.prototype.toggleHourSlotAvailability = function(slot, td) {
	var toAvailable = this.unavailableScheduleModel.toggleHourSlotAvailability(slot);
	if (toAvailable) {
		UnavailableScheduleController.markAvailable(td)
	}
	else {
		UnavailableScheduleController.markUnavailable(td)
	}
}

UnavailableScheduleController.markUnavailable = function(td) {
	UnavailableScheduleController.color(td, 'red')
}

UnavailableScheduleController.markAvailable = function(td) {
	UnavailableScheduleController.color(td, 'white')
}

UnavailableScheduleController.color = function(td, color) {
	td.style.backgroundColor=color
}

UnavailableScheduleController.prototype.prepForSubmit = function() {
	var modelSubmittalRepresentation = this.unavailableScheduleModel.toSubmittableStr();
	if (modelSubmittalRepresentation != '') {
		$('unavailableScheduleStr').value = modelSubmittalRepresentation
	}
}
