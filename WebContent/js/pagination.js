function goTo()
{
	var goPageNumber=document.getElementById("goto");
	window.location.href="cutList.jsp?number=<%=number%>&pageNumber="+goPageNumber.value;
}
function number()
{
	var number=document.getElementById("number");
	window.location.href="cutList.jsp?number="+number.value;
}