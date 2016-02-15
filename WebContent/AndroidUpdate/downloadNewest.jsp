<jsp:useBean id="mySmartUpload" class="com.jspsmart.upload.SmartUpload"/><%
String fileName=request.getParameter("fileName");
mySmartUpload.initialize(pageContext);
mySmartUpload.setContentDisposition(null);
mySmartUpload.downloadFile("/apk/DeforestationManagementNewest.apk");%>