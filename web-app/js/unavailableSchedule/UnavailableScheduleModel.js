function UnavailableScheduleModel(unavailableSchedule) { 
	this.unavailableSchedule = unavailableSchedule;
}

UnavailableScheduleModel.prototype.toggleHourSlotAvailability = function(slot, td) {
	var searchResult = this.unavailableSchedule.indexOf(slot);
	if (searchResult == -1) {
		this.unavailableSchedule.push(slot);
	}
	else {
		this.unavailableSchedule.splice(searchResult, 1);
	}
	return searchResult != -1
}

UnavailableScheduleModel.prototype.toSubmittableStr = function() {
	var unavailableSchedStr = '';
	if (this.unavailableSchedule.length > 0) {
		for(i = 0; i < this.unavailableSchedule.length; i++) {
			unavailableSchedStr += this.unavailableSchedule[i]
			if (i < this.unavailableSchedule.length - 1) {
				unavailableSchedStr += ' '
			}
		}
	}
	return unavailableSchedStr
}
