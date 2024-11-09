var text = "";
//var baseURL = 'http://'+top.location.hostname+ (top.location.port == "") ? "" : (':' + top.location.port);
var baseURL = "http://localhost";
var dbList = (window.databases) ? window.databases : '!';

function calldict(word){
	var myDB = dbList;
	if (top.document.urlForm.db) {
		//myDB = top.document.urlForm.db.value;
	}
	tddt = window.open(baseURL+'/td?db='+myDB+'&word='+encodeURIComponent(word),'TDDT','toolbar=no,status=no,scrollbars=yes,location=no,menubar=no,directories=no,titlebar=no,width=600,height=400');
	tddt.focus();
}
function doLookupIE() {
	if (!window.event.ctrlKey) return true
	text = document.selection.createRange().text;
	if (text != "") {
		lookupWord(text);
		return false;
	}
	var r = document.body.createTextRange();
	r.moveToPoint(window.event.x, window.event.y);
	r.expand("word");
	if (r.text)
	{
		lookupWord(r.text);
	}
	return false;
}
function nocontextmenu()  // this function only applies to IE4, ignored otherwise.
{
	event.cancelBubble = true
	event.returnValue = false;
	return false;
}

function doLookup(e) // This function is used by all others
{
	if (window.Event) { // Netscape
		if (e.which == 2 || e.which == 3) {
			lookupSelected();
			return false;
		}
	} else {
		doLookupIE();
		event.cancelBubble = true
		event.returnValue = false;
		return false;
	}
}
function getSelectedText() {
	if (window.getSelection) return window.getSelection();
	else if (document.getSelection) return document.getSelection();
	else if (document.selection) {
		var sel = document.selection.createRange().text;
		return sel.replace(new RegExp('([\\f\\n\\r\\t\\v ])+', 'g')," ");
	}
	else return "";
}

function lookupSelected() {
	var sel = getSelectedText();
	if (sel == "") return;
	lookupWord(sel);
}

function lookupWord(word) {
	if (top.document.tdForm) {
		top.document.tdForm.word.value=word;
		top.document.tdForm.submit();
	} else {
		calldict(word);
	}
}

if (window.Event) { // Netscape
	document.captureEvents(Event.MOUSEUP);
}
//document.oncontextmenu = nocontextmenu;  // suppress context menu for IE5+
document.onmousedown = doLookup;  // for all others;