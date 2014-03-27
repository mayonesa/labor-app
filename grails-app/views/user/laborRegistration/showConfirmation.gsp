<g:applyLayout name="laborRegistrationWizard">
	<div id="unavailableScheduleDiv" style="padding-left: 45px;">
		<g:unavailableScheduleMatrix unavailableSchedule="${unavailableSchedule}"  readOnly='true' />
	</div>
	<div id="wishListInfoDiv" style="border:1px solid #ccc; position: relative; top: 10px">
		<div id='wishListDiv' style='font: 9.5px verdana, arial, helvetica, sans-serif; overflow: auto'>
			<span class='labels'>Wish List:&nbsp;</span>
			<g:each status='i' var="desiredLabor" in="${wishList}">
				${desiredLabor.name}<g:if test='${i < (wishList.size() - 1)}'>,&nbsp;</g:if>
			</g:each>
			<br />
			<span class='labels'>Comments:&nbsp;</span>
			<span>${comment}</span>
		</div>
	</div>
</g:applyLayout>