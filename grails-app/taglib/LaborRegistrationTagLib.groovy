class LaborRegistrationTagLib {

	def unavailableScheduleMatrix = { attrs ->
		def unavailableSched = attrs.unavailableSchedule as Set
		def readOnly = attrs.readOnly
		if (!readOnly) {
			def applicationUri = grailsAttributes.getApplicationUri(request)
			out << g.javascript library: 'prototype/prototype'
			 	 + g.javascript library: 'unavailableSchedule/UnavailableScheduleModel'
			 	 + g.javascript library: 'unavailableSchedule/UnavailableScheduleController'
			 	 + '''<script language='javascript'>
					controller = new UnavailableScheduleController(new UnavailableScheduleModel([
				'''
			unavailableSched.eachWithIndex { unavailableHourSlot, i ->
				out << "'$unavailableHourSlot'"
				if (i < unavailableSched.size()) {
					out << ', '
				}
			}		
			out << ''']))
				</script>'''
		}
		out << '<table '
		if (!readOnly) {
			out << 'class="schedule" '
		}
		out << """style="width: 350px">
				<tr>
					<th style='color: #006dba'>${attrs.label ?: 'Unavail Sched'}</th>
					<th>Mo</th>
					<th>Tu</th>
					<th>We</th>
					<th>Th</th>
					<th>Fr</th>
					<th>Sa</th>
					<th>Su</th>
				</tr>
		"""
		def startHour
		OneHourSlot.values().each { oneHourSlot ->
			if (oneHourSlot.startHour != startHour) {
				if (startHour) {
					out << '</tr>'
				}
				out << """
					<tr>
						<th>${oneHourSlot.civilianStartHour} - ${oneHourSlot.civilianEndHour}</th>
				"""
				startHour = oneHourSlot.startHour
			}
			out << '<td'
			if (!readOnly) {
				out << " onclick='controller.toggleHourSlotAvailability(\"${oneHourSlot}\", this)'"
			}
			if (unavailableSched?.contains(oneHourSlot)) {
				out << ' style="background-color:red"'
			}
			out << ' />\n'
		}
		out << '</tr></table>'
		if (!readOnly) {
			out << g.hiddenField(name: 'unavailableScheduleStr')
		}
	}
	
/*	def wishListSelect = { attrs ->
			out << gui.resources(components:"draggableListWorkArea,draggableList")
			
			out << '''
		    <style>
		    div.workarea {
		        padding: 10px;
		        float: left
		    }

		    ul.draglist {
		        position: relative;
		        width: 200px;
		        list-style: none;
		        margin: 0;
		        padding: 0;
				height:250px;
				overflow:auto;
*/		        /*
		           The bottom padding provides the cushion that makes the empty
		           list targetable.  Alternatively, we could leave the padding
		           off by default, adding it when we detect that the list is empty.
		        */
/*		        padding-bottom: 20px;
		    }

		    ul.draglist li {
		        margin: 1px;
		        cursor: move;
		    }

		    li.selected {
		        background-color: #D1E6EC;
		        border: 1px solid #7EA6B2;
		    }

		    li.available {
		        background-color: #D8D4E2;
		        border: 1px solid #6B4C86;
		    }
		</style>'''
		out << myGui.draggableListWorkArea(formReady: 'true',
		   	myGui.draggableList(id: 'wishList', class: 'selected', prepend: 'labor_',
				attrs.wishList?.each { desiredLabor ->
					"<li id='${desiredLabor.id}'>${desiredLabor.name}</li>"
				}) +
		   	myGui.draggableList(id: 'availableLabors', class: 'selected', prepend: 'labor_',
				attrs.availableLabors?.each { availableLabor ->
					"<li id='${availableLabor.id}' class='available'>${availableLabor.name}</li>"
				}))
	} */
}
