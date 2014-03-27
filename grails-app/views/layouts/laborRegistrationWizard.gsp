<g:applyLayout name="main"> 
	<g:render template="/user/laborRegistration/registrationBodyTop" 
			  model="${pageScope.variables}" />
	<div style="margin-left:25px;width:85%;">
		<g:form name='laborRegistrationForm' action='laborRegistration'>
	       	<g:layoutBody />		
			<g:render template="/user/laborRegistration/registrationNavigation"
				 	  model="${pageScope.variables}" />
		</g:form>
    </div>
</g:applyLayout>