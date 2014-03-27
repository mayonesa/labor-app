<g:applyLayout name="laborRegistrationWizard">
	<gui:resources components="draggableListWorkArea,draggableList" />
	<g:if test='${user.originalWishListAndScheduleConflict}'>
		<span class='message'><g:message code='original.wish.list.and.schedule.conflict.message' /></span>
	</g:if>
    <g:hasErrors bean="${user}"> 
        <div class="errors"> 
            <g:renderErrors bean="${user}"></g:renderErrors> 
        </div> 
    </g:hasErrors>	
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
			height:268px;
			overflow:auto;
			border: 1px solid #ccc;
	        /*
	           The bottom padding provides the cushion that makes the empty
	           list targetable.  Alternatively, we could leave the padding
	           off by default, adding it when we detect that the list is empty.
	        */
	        padding-bottom: 20px;
	    }

	    ul.draglist li {
	        margin: 1px;
	        cursor: move;
	    }

	    li.selected {
	        background-color: #D1E6EC;
	        border: 1px solid #7EA6B2;
//			font:13px/1.231 arial,helvetica,clean,sans-serif;
	    }

	    li.available {
	        background-color: #D8D4E2;
	        border: 1px solid #6B4C86;
//			font:13px/1.231 arial,helvetica,clean,sans-serif;
	    }
	</style>
	<myGui:draggableListWorkArea formReady='true' maxSelect='4' class='yui-skin-sam'>
		<div>
			<span class='labels' style="position:relative; left:10px; top:9px">Wish List (${User.WISH_LIST_LIMIT} max):</span>
			<myGui:selectList class="selected" prepend="${UserController.WISH_LIST_PARAM_PREPEND}">
				<g:each in="${wishList}" var="desiredLabor">
					<li id="${desiredLabor.id}">${desiredLabor.name} (${desiredLabor.durationInHours})</li>
				</g:each>
			</myGui:selectList>
		</div>
		<div style="position: relative; top: -17px; left: 13px">
			<span class='labels' style='position:relative; left:10px; top:9px'>Available Labors:</span>
			<myGui:availableList class="available" prepend="${UserController.WISH_LIST_PARAM_PREPEND}">
			 	<g:each in="${availableLabors}" var="availableLabor">
					<li id="${availableLabor.id}">${availableLabor.name} (${availableLabor.durationInHours})</li>
				</g:each>
			</myGui:availableList>
		</div>
	</myGui:draggableListWorkArea>

	<div style="padding: 7px; float: left; width: 400px">
		<span class='labels'>Comments (${User.COMMENT_MAX_LENGTH} max characters):</span>
		<g:textArea name="comment" value="${comment}" onkeypress="return imposeMaxLength(this, ${User.COMMENT_MAX_LENGTH});" rows="2" cols="73" max='260' style='overflow: auto' />
	</div>	
</g:applyLayout>