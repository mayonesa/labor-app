<div id='nav_buttons'>
	<g:if test='${leftValue}'>
		<g:submitButton name='${leftName}' value='${leftValue}' onclick='${onSubmitJs}' class='buttons' style='position:fixed; left:45px; bottom: 9px' />
	</g:if>
	<g:if test='${rightValue}'>
		<g:submitButton name='${rightName}' value='${rightValue}' onclick='${onSubmitJs}' class='buttons' style='position:fixed; right:55px; bottom: 9px' />
	</g:if>
</div>