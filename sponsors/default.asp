﻿<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<!-- #BeginTemplate "../master.dwt" -->

<head>
<!-- #BeginEditable "doctitle" -->
<title>Sponsors : Crimson Racing | Formula SAE</title>
<!-- #EndEditable -->
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<link href="../default.css" rel="stylesheet" type="text/css" />
</head>

<body>

<table cellpadding="0" cellspacing="0" class="mastertable">
	<tr>
		<td class="shadow-top-left"></td>
		<td class="shadow-top"></td>
		<td class="shadow-top-right"></td>
	</tr>
	<tr>
		<td class="shadow-left" rowspan="2">
		<img src="/images/spacer.gif" width="10" alt="" /></td>
		<td>
		<!--Header-->
		<table cellpadding="0" cellspacing="0" class="header">
			<tr>
				<td style="vertical-align: top;"><a href="/">
				<img src="/images/header.png" alt="Crimson Racing" style="width: 455px; height: 130px; border: 0px;padding-bottom:1px" /></a><br />
				<!--Navigation-->
				<div class="linkbardiv">
					<table style="margin-left: 0px" cellpadding="0" cellspacing="0">
						<tr>
							<td>
							<table cellpadding="0" cellspacing="0" class="linkbar">
								<tr>
									<td><a href="">Home</a></td>
									<td><a href="#">Project</a></td>
									<td><a href="#">Download</a></td>
									<td><a href="#">Team</a></td>
								</tr>
							</table>
							</td>
							<td>
							<img src="/images/linkbar_diag.gif" alt="" width="35" height="35" /></td>
						</tr>
					</table>
				</div>
				<!--End Navigation--></td>
				<td class="headerCenter">
				<img src="/images/vertical_divide.gif" alt="" /></td>
				<td class="headerRight">
				<div style="border-bottom: 1px #9ECE00 solid">
					<div>
						<table cellpadding="0" cellspacing="0">
							<tr>
								<td style="width: 16px">
								<img src="/images/aboutbar_diagonal.gif" alt="" /></td>
								<td class="aboutBar"><a href="../about/">About 
								Formula SAE</a> | <a href="#">Contact</a></td>
							</tr>
						</table>
					</div>
					<!-- #BeginEditable "header" --><%
					''''''''''''''''''''''''''''''''''''''''
					' pick random image
					''''''''''''''''''''''''''''''''''''''''
					
					Function RandomNumber(intHighestNumber)
						Randomize
						RandomNumber = Int(Rnd * intHighestNumber)
					End Function
		
					Filename = "/images/header/header_data.txt"
					set fso = server.createObject("Scripting.FileSystemObject")
					Filepath = Server.MapPath(Filename)
					if fso.FileExists(Filepath) Then
						Set ts = fso.OpenTextFile(Filepath, 1)
						
						numHeaders = ts.ReadLine
						numHeaders = CInt(numHeaders)
						
						displayHeader = RandomNumber(numHeaders)
						
						For i=1 to displayHeader
							ts.ReadLine
						Next
						
						headerImgPath = ts.ReadLine
						
						ts.Close()
					End If
				%> <img src="<%=headerImgPath%>" alt="" width="480" height="165" /><!-- #EndEditable --></div>
				</td>
			</tr>
		</table>
		</td>
		<td class="shadow-right" rowspan="2">
		<img alt="" src="/images/spacer.gif" width="10" /></td>
	</tr>
	<tr>
		<td>
		<table style="width: 100%" cellpadding="0" cellspacing="0">
			<tr>
				<td style="vertical-align: top">
				<!--Breadcrumb-->
				<table class="breadcrumb" cellpadding="0" cellspacing="0">
					<tr>
						<td style="width: 23px">
						<img alt="" src="/images/top_right_diag.gif" /></td>
						<td style="background-image: url(/images/breadcrumb.gif)">
						<span style="margin-left: 20px; font-size: x-small; font-weight: bold">
						<a href="" style="color: red">Crimson Racing</a>&nbsp;&nbsp; 
						»&nbsp;&nbsp;
						<!-- #BeginEditable "breadcrumb" -->Sponsors<!-- #EndEditable --></span></td>
					</tr>
				</table>
				<!--Body-->
				<table class="body" cellpadding="0" cellspacing="0">
					<tr>
						<td>
						<!-- #BeginEditable "body" -->
						<h1>Sponsors</h1>
						<p>The Formula SAE program demands a large amount of capitol 
						for success and could not operate without the help of our 
						generous sponsors. We would like to thank each and every 
						one of the sponsors below for their contributions to our 
						racing team. We would like to encourage you to visit their 
						websites and find out more about each of these great companies.</p>
						<p>If you are interested in becoming a sponsor for our racing 
						team, please
						<a href="/files/2009_UA-FSAE_Sponsorship_Brochure.pdf">
						view our sponsorship brochure</a> and contact our sponsorship 
						coordinator, Marc Hansen, at (205) 936-2726. Thank you for 
						your support.</p>
						<hr />
						<table>
						<%
						''''''''''''''''''''''''''''''''''''''''
						' generate sponsor table
						''''''''''''''''''''''''''''''''''''''''
						
						Filename = "/images/sponsors/sidebar/sponsor_data.txt"
						set fso = server.createObject("Scripting.FileSystemObject")
						Filepath = Server.MapPath(Filename)
						If fso.FileExists(Filepath) Then
							Set ts = fso.OpenTextFile(Filepath, 1)
						
							numSponsors = ts.ReadLine
							numSponsors = CInt(numSponsors)
							currentSponsor = 0
							numSponsorCols = 4
							
							numSponsorRows = numSponsors / numSponsorCols
							
							If numSponsors Mod numSponsorCols <> 0 Then
								numSponsorRows = numSponsorRows + 1
							End If
						
							For i=1 to numSponsorRows
						%>
						<tr>
						<%
								For c=1 to numSponsorCols
									currentSponsor = currentSponsor + 1
									If currentSponsor >= numSponsors Then
						%>
						<td></td>
						<%
									Else
										sponsorImgPath = ts.ReadLine
										sponsorToolTip = ts.ReadLine
										sponsorLinkPath = ts.ReadLine
						%>
						<td><a href="<%=sponsorLinkPath%>" target="_blank">
						<img src="<%=sponsorImgPath%>" alt="<%=sponsorToolTip%>" width="135" height="75" style="border: 1px #9ECE00 solid" /></a></td>
						<%
									End If
								Next
						%>
						</tr>
						<% 
							Next
						
							ts.Close()
						End If
						%>
						</table>
						<!-- #EndEditable --></td>
					</tr>
				</table>
				</td>
				<!--Sidebar-->
				<td class="sidebar"><b>&#39;09 FSAE Car Status:</b><br />
				<b style="color: yellow; font-size: x-small">Stage 1 - Design</b><br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#" style="font-size: x-small">view details »</a> <br />
				<br />
				<hr /><br />
				<b>Upcoming Events</b><br />
				<ul class="eventlist">
					<li><a href="#">Sample Event 1</a><br />
					<span>September 9, 2008</span></li>
					<li><a href="#">Sample Event 2</a><br />
					<span>September 30, 2008</span></li>
					<li><a href="#">Sample Event 3</a><br />
					<span>November 11, 2008</span></li>
				</ul>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href="#" style="font-size: x-small">view all events »</a>
				<br />
				<br />
				<hr /><br />
				<%
					''''''''''''''''''''''''''''''''''''''''
					' pick random sponsor
					''''''''''''''''''''''''''''''''''''''''
					
					Function RandomNumber(intHighestNumber)
						Randomize
						RandomNumber = Int(Rnd * intHighestNumber)
					End Function
		
					Filename = "/images/sponsors/sidebar/sponsor_data.txt"
					Const ForReading = 1
					set fso = server.createObject("Scripting.FileSystemObject")
					Filepath = Server.MapPath(Filename)
					if fso.FileExists(Filepath) Then
						Set ts = fso.OpenTextFile(Filepath, ForReading)
						
						numSponsors = ts.ReadLine
						numSponsors = CInt(numSponsors)
						
						displaySponsor = RandomNumber(numSponsors)
						
						For i=1 to displaySponsor
							ts.ReadLine
							ts.ReadLine
							ts.ReadLine
						Next
						
						sponsorImgPath = ts.ReadLine
						sponsorToolTip = ts.ReadLine
						sponsorLinkPath = ts.ReadLine
						
						ts.Close()
					End If
				%> <span style="font-size: xx-small">Sponsor:</span>
				<a href="<%=sponsorLinkPath%>" target="_blank">
				<img src="<%=sponsorImgPath%>" alt="<%=sponsorToolTip%>" width="135" height="75" style="border: 1px #9ECE00 solid" /></a>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td class="shadow-bottom-left"></td>
		<td class="shadow-bottom"></td>
		<td class="shadow-bottom-right"></td>
	</tr>
</table>
<!--Footer-->
<table>
	<tr>
		<td>
		<table class="footer" cellpadding="0" cellspacing="0">
			<tr>
				<td style="width: 200px">
				<img alt="Valid XHTML 1.0 Transitional" src="../images/xhtml-button.gif" style="border: 0px; padding-bottom: 2px" /><br />
				<img alt="Valid CSS 2.1" src="../images/css-button.gif" style="border: 0px" />
				</td>
				<td style="width: 550px; text-align: center"><a href="">Home</a> 
				| <a href="#">News</a> | <a href="#">Media</a> | <a href="#">Team</a> 
				| <a href="#">Cars</a> | <a href="/sponsors/">Sponsors</a></td>
				<td style="width: 200px; text-align: right">© 2008 Crimson Racing<br />
				<span style="font-size: xx-small">Site Design by Brian Connell</span></td>
			</tr>
		</table>
		</td>
	</tr>
</table>

</body>

<!-- #EndTemplate -->

</html>
