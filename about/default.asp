﻿<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<!-- #BeginTemplate "../master.dwt" -->

<head>
<!-- #BeginEditable "doctitle" -->
<title>About : Crimson Racing | Formula SAE</title>
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
								<td class="aboutBar"><a href="./">About 
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
						<!-- #BeginEditable "breadcrumb" -->About<!-- #EndEditable --></span></td>
					</tr>
				</table>
				<!--Body-->
				<table class="body" cellpadding="0" cellspacing="0">
					<tr>
						<td>
						<!-- #BeginEditable "body" -->
						<h1>What is Formula SAE?</h1>
						<p>The largest racing series in the world, Formula SAE is 
						a collegiate design competition sponsored by the Society 
						of Automotive Engineers. Over 140 teams from around the 
						world compete in 7 competitions in the United States, Australia, 
						Europe, and Brazil.</p>
						<p>The Formula SAE® competition is for SAE student members 
						to conceive, design, fabricate, and compete with small formula-style 
						racing cars. The restrictions on the car frame and engine 
						are limited so that the knowledge, creativity, and imagination 
						of the students are challenged. The cars are built with 
						a team effort over a period of about one year and are taken 
						to the annual competition for judging and comparison with 
						approximately 120 other vehicles from colleges and universities 
						throughout the world.</p>
						<p>At competition students not only race the car in dynamic 
						events, they give several presentations that test their 
						knowledge of the engineering involved in a race car, their 
						ability to manage a budget and build the car affordably, 
						and to sell the car to the targeted consumer market, the 
						weekend autocrosser. The dynamic events consist of acceleration 
						testing, skidpad testing, solo autocrossing, and a 22 kilometer 
						endurance race. The end result is a great experience for 
						young engineers in a meaningful engineering project as well 
						as the opportunity of working in a dedicated team effort.</p>
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
