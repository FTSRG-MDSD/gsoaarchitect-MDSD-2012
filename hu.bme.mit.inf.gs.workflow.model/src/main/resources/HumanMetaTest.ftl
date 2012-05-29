<html>
<body>
<h2>Human ApplicationMetadata test task</h2>
<hr>
<p>Please verify, that the Application Metadata is valid and can be used in the GS AppStore!

<p><strong>Application details</strong></p>
<ul>
	<li>Application name: ${applicationName}</li>
	<li>Application ID: ${applicationID}</li>
	<li>Version timestamp: ${versionTimestamp}</li>
</ul>
<form action="complete" method="post"
enctype="multipart/form-data">
Acceptance result: <select name="acceptance"><option value="1">ACCEPT</option><option value="2">DENY</option><option value="3" selected>UNKNOWN</option></select>
<input type="submit" value="Complete">
</form>
<br>
<br>
</body>
</html>