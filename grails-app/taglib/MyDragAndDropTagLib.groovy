class MyDragAndDropTagLib {

    static namespace = "myGui"
	
    def grailsUITagLibService
    def prepend = ''
    def lists

	private static final SELECTED = 'selected'
	private static final AVAILABLE = 'available'
	private static final LIST_FORM_NAME_APPENDAGE = 'input'

	public static final SELECTED_IDS = SELECTED + '_' + LIST_FORM_NAME_APPENDAGE
	
    /**
     * You must define a draggableListWorkArea to surround any draggableLists you want.  This takes only one optional
     * parameter:
     *
     * formReady: if true, writes out hidden inputs that contain dynamic list data for a form submission
     *
     */
    def draggableListWorkArea = {attrs, body ->
        attrs = grailsUITagLibService.establishDefaultValues(
                [
                        id: grailsUITagLibService.getUniqueId(),
                        formReady: false,
                ],
                attrs
        )
        // clear any old lists
        lists = [:]
        // process inner list tags
        out << body()
        
        def jsid = grailsUITagLibService.toJS(attrs.id)

        def hiddenInputData = [:]
        if (attrs.formReady) {
            hiddenInputData[SELECTED] = "<input type='hidden' id='${SELECTED_IDS}' name='${SELECTED_IDS}' />\n"
        }

        out << """
        	${hiddenInputData.values().join('');}
	        <script>
	            GRAILSUI.${jsid} = {
	                init: function() {
	                    ${
	                        lists.collect { listItem ->
	                            def result = "new YAHOO.util.DDTarget('${listItem.key}');\n"
	                            listItem.value.each {
	                                result += "var ${it}_dli = new GRAILSUI.DraggableListItem('${it}');\n"
	                                result += "${it}_dli.on('dragDropEvent', this.populateHiddenInput);\n"
	                            }
	                            result
	                        }.join('')
	                    }
	                    this.populateHiddenInput();
	                },

	                populateHiddenInput: function() {
	                    var items = YAHOO.util.Dom.get('${SELECTED}').getElementsByTagName("li");
	                    var out = "";
	                    for (i=0;i<items.length;i=i+1) {
	                        out += items[i].id + " ";
	                    }
	                    YAHOO.util.Dom.get('${SELECTED_IDS}').value = out;
						${
							if (attrs.maxSelect) {
								"""
								var selectTarget = this.DDM.ids.default['${SELECTED}'];
							 	if (items.length >= ${attrs.maxSelect}) {
									if (!selectTarget.isLocked()) {
										selectTarget.lock();
									}
								}
								else if (selectTarget.isLocked()) {
									selectTarget.unlock();
								}
								"""
							}
						}
	                }
	            };
	            YAHOO.util.Event.onDOMReady(GRAILSUI.${jsid}.init, GRAILSUI.${jsid}, true);
	        </script>
	        """
    }

	def selectList = { attrs, body ->
		draggableList attrs, body, SELECTED
	}

	def availableList = { attrs, body ->
		draggableList attrs, body, AVAILABLE
	}

    /**
     * Used to create a list with items that can be dragged to all other draggableLists.
     *
     * class: (optional) for styling
     */
    private draggableList = {attrs, body, listName ->
        attrs = grailsUITagLibService.establishDefaultValues(
                [
                        id: grailsUITagLibService.getUniqueId(),
                        prepend: '',
                        'class':''
                ],
                attrs
        )
        // reset prepend
        prepend = attrs.remove('prepend')
        def liPattern = /<li\b[^>]*>(.*?)<\/li>/
        def rawBody = body()
        def liMatcher = rawBody =~ liPattern
        def parsedListContents = ''
        def listItem
        def count = 0
        def listItemIds = []
        for (match in liMatcher) {
            if (match instanceof List) {
                match = match[0]
            }
            // check for an li id
            def firstLi = match.substring(0,match.indexOf('>'))
            def itemId = "${prepend}${listName}_${count++}"
            if (firstLi.contains('id=')) {
                // TODO: this could be a problem if someone types "id = '" with spaces
                itemId = prepend + firstLi.substring(firstLi.indexOf("id=")+4,firstLi.size()-1)
            }
            // TODO: check for an incoming class tag here in the future
            // remove the original li tags
            match = match.substring(match.indexOf('>')+1,match.indexOf('</li>'))
            parsedListContents += "<li id='${itemId}' class='${attrs.'class'}'>$match</li>\n"
            listItemIds << itemId
        }
        lists."${listName}" = listItemIds
        out << """
        <div id="${listName}_workarea" class="workarea">
            <ul id="${listName}" class="draglist">
                ${parsedListContents}
            </ul>
        </div>
        """
    }
}
