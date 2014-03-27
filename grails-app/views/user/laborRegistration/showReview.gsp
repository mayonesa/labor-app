<g:applyLayout name="laborRegistrationWizard">
	<div id="unavailableScheduleDiv" style="padding-left: 5px; border: 1px solid #ccc; width: 465px">
		<g:unavailableScheduleMatrix unavailableSchedule="${unavailableSchedule}" readOnly='true' />
		<g:link event="reviseUnavailableSchedule" style='position: relative; left: 417px; bottom: 415px'>Revise</g:link>
	</div>
	<div id="wishListInfoDiv" style="border: 1px solid #ccc; width: 470px">
		<div id='wishListCommentDiv' style='font: 9.5px verdana, arial, helvetica, sans-serif; overflow: auto'>
			<span class='labels'>Wish List:&nbsp;</span>
			<g:each status='i' var="desiredLabor" in="${wishList}">
				${desiredLabor.name}<g:if test='${i < (wishList.size() - 1)}'>,&nbsp;</g:if>
			</g:each>
			<br />
			<span class='labels'>Comments:&nbsp;</span>
			<span>${comment}</span>
		</div>
		<g:link event="reviseWishList" style='position: absolute; left: 449px; bottom: 85px'>Revise</g:link>
	</div>
</g:applyLayout>