<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
  <link rel="stylesheet" href="style/dgs.css" type="text/css"/>
  <SCRIPT type="text/javascript" LANGUAGE="JavaScript">
   
  </SCRIPT> 
  <title>Document Generation System</title>

</head>
<body id="login" >
<div id="mainContent">
  <div id="screenBackground">
    <s:form name="loginForm" method="save" action="useraction" >
      <div id="screenHeader">Login Page</div>
               
        <table class="inputForm">
          <tr>
            <td width="45%" class="align-right"><s:textfield name="name" label="Name" value="" /></td>
          </tr>
          <tr>
             <td width="45%" class="align-right"><s:textfield name="address" label="Address" value="" /></label></td>
          </tr>
        </table>
      </div>
      <div class="buttons">
        <s:submit value="Login"></s:submit>
      </div>
  </div>
</div>
</s:form>
</body>
</html>
