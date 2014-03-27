class BootStrap {

     def init = { servletContext ->
		new CurrentLaborTerm(laborTerm: LaborTermType.SUMMER).save()
		new User(firstName: 'john', lastName: 'jimenez', roomNumber: '209', username: 'qwertyyu', password: 'qwertyuio').save()
		new FlexLabor(name: 'nothing', laborTerm: LaborTermType.SUMMER, durationInHours: 3).save()
		new DaySpecificLabor(name: 'something', laborTerm: LaborTermType.SUMMER, durationInHours: 3, day: Day.TUESDAY).save()
		new DayAndTimeSpecificLabor(name: 'dayAndTime', laborTerm: LaborTermType.SUMMER, durationInHours: 3, day: Day.TUESDAY, startHour: 10).save()
		new FlexLabor(name: 'nothing1', laborTerm: LaborTermType.SUMMER, durationInHours: 3).save()
		new DaySpecificLabor(name: 'something1', laborTerm: LaborTermType.SUMMER, durationInHours: 3, day: Day.TUESDAY).save()
		new DayAndTimeSpecificLabor(name: 'dayAndTime1', laborTerm: LaborTermType.SUMMER, durationInHours: 3, day: Day.TUESDAY, startHour: 11).save()
		new FlexLabor(name: 'nothing2', laborTerm: LaborTermType.SUMMER, durationInHours: 3).save()
		new DaySpecificLabor(name: 'something2', laborTerm: LaborTermType.SUMMER, durationInHours: 3, day: Day.TUESDAY).save()
		new DayAndTimeSpecificLabor(name: 'dayAndTime2', laborTerm: LaborTermType.SUMMER, durationInHours: 3, day: Day.TUESDAY, startHour: 16).save()
		new FlexLabor(name: 'nothing3', laborTerm: LaborTermType.SUMMER, durationInHours: 3).save()
		new DaySpecificLabor(name: 'something3', laborTerm: LaborTermType.SUMMER, durationInHours: 3, day: Day.TUESDAY).save()
		new DayAndTimeSpecificLabor(name: 'dayAndTime3', laborTerm: LaborTermType.SUMMER, durationInHours: 3, day: Day.MONDAY, startHour: 10).save()
		new FlexLabor(name: 'nothing4', laborTerm: LaborTermType.SUMMER, durationInHours: 3).save()
		new DaySpecificLabor(name: 'something4', laborTerm: LaborTermType.SUMMER, durationInHours: 3, day: Day.TUESDAY).save()
		new DayAndTimeSpecificLabor(name: 'dayAndTime4', laborTerm: LaborTermType.SUMMER, durationInHours: 3, day: Day.TUESDAY, startHour: 10).save()
		new FlexLabor(name: 'nothing5', laborTerm: LaborTermType.SUMMER, durationInHours: 3).save()
		new DaySpecificLabor(name: 'something5', laborTerm: LaborTermType.SUMMER, durationInHours: 3, day: Day.TUESDAY).save()
		new DayAndTimeSpecificLabor(name: 'dayAndTime5', laborTerm: LaborTermType.SUMMER, durationInHours: 3, day: Day.TUESDAY, startHour: 10).save()
		new FlexLabor(name: 'nothing6', laborTerm: LaborTermType.SUMMER, durationInHours: 3).save()
		new DaySpecificLabor(name: 'something6', laborTerm: LaborTermType.SUMMER, durationInHours: 3, day: Day.TUESDAY).save()
		new DayAndTimeSpecificLabor(name: 'dayAndTime6', laborTerm: LaborTermType.SUMMER, durationInHours: 3, day: Day.TUESDAY, startHour: 10).save()
		new FlexLabor(name: 'nothing7', laborTerm: LaborTermType.SUMMER, durationInHours: 3).save()
		new DaySpecificLabor(name: 'something7', laborTerm: LaborTermType.SUMMER, durationInHours: 3, day: Day.TUESDAY).save()
		new DayAndTimeSpecificLabor(name: 'dayAndTime7', laborTerm: LaborTermType.SUMMER, durationInHours: 3, day: Day.TUESDAY, startHour: 10).save()
		new FlexLabor(name: 'nothing8', laborTerm: LaborTermType.SUMMER, durationInHours: 3).save()
		new DaySpecificLabor(name: 'something8', laborTerm: LaborTermType.SUMMER, durationInHours: 3, day: Day.TUESDAY).save()
		new DayAndTimeSpecificLabor(name: 'dayAndTime8', laborTerm: LaborTermType.SUMMER, durationInHours: 3, day: Day.TUESDAY, startHour: 10).save()
		new FlexLabor(name: 'nothing9', laborTerm: LaborTermType.SUMMER, durationInHours: 3).save()
		new DaySpecificLabor(name: 'something9', laborTerm: LaborTermType.SUMMER, durationInHours: 3, day: Day.TUESDAY).save()
		new DayAndTimeSpecificLabor(name: 'dayAndTime9', laborTerm: LaborTermType.SUMMER, durationInHours: 3, day: Day.TUESDAY, startHour: 10).save()
     }
     def destroy = {
     }
} 