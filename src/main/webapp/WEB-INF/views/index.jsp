<%@page
	import="org.hibernate.type.descriptor.java.MutabilityPlanExposer"%>
<%@page import="org.dev.InsuranceReportApp.entity.CitizenPlan"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<!DOCTYPE html>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Report Application</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
	integrity="sha384-xOolHFLEh07PJGoPkLv1IbcEPTNtaed2xpHsD9ESMhqIYd0nLMwNLD69Npy4HI+N"
	crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
	<div class="container">

		<div class="">
			<h3 class="pb-3">Report Application</h3>
			<form:form method="POST" action="/search"
				modelAttribute="searchRequest">

				<table class="table table-borderless ">
					<tr>
						<td><form:label path="planName">PlanName : </form:label> <form:select
								path="planName">
								<form:option value="" >-Select-</form:option>
								<form:options items="${planNames }" path="planName" />
							</form:select></td>
						<td><form:label path="planStatus">PlanStatus :</form:label> <form:select
								path="planStatus">
								<form:option value="" >-Select-</form:option>
								<form:options items="${planStatuses }" path="planStatus" />
							</form:select></td>
						<td><form:label path="gender">Gender</form:label> <form:select
								path="gender">
								<form:option value="" >-Select-</form:option>
								<form:option value="male" path="gender"></form:option>
								<form:option value="female" path="gender"></form:option>
							</form:select></td>
					</tr>
					<tr>
						<td><form:label path="planStartDate">Start Date :</form:label>
							<form:input type="date" path="planStartDate" /></td>
						<td><form:label path="planEndDate">End Date :</form:label>
				    	<form:input	type="date" path="planEndDate" /></td>
						<td></td>
					</tr>
					<tr>
					    <td><a href="/welcome" class="btn btn-secondary">Reset</a>
						<input type="submit" value="Search"
							class="btn btn-primary" /></td>

						<td></td>
						<td></td>
					</tr>
				</table>
			</form:form>
		</div>
		<hr>
		<div>
			<h4 class="text-center">Citizens List</h4>

			<table class="table table-borderless">
				<tr class="thead-dark">
				    <th>S.No</th>
					<th>Citizen Name</th>
					<th>Plan Name</th>
					<th>Plan Status</th>
					<th>Start Date</th>
					<th>End Date</th>
					<th>Denial Reason</th>
					<th>Termination Date</th>
					<th>Termination Reason</th>
					<th>Gender</th>
					<th>Benefit Amt</th>
				</tr>
				<%
				List<CitizenPlan> citizens = (List<CitizenPlan>) request.getAttribute("citizens");
                int count=1;
				// Check if the list is not null or empty
				if (citizens != null && !citizens.isEmpty()) {
					for (CitizenPlan citizen : citizens) {

				%>
				<tr>
				    <td><%=count++ %></td>
					<td><%=citizen.getCitizenName()%></td>
					<td><%=citizen.getPlanName()%></td>
					<td><%=citizen.getPlanStatus()%></td>
					<td><%=citizen.getPlanStartDate()%></td>
					<td><%=citizen.getPlanEndDate()%></td>
					<td><%=citizen.getDenialReason()%></td>
					<td><%=citizen.getTerminatedDate()%></td>
					<td><%=citizen.getTerminationReason()%></td>
					<td><%=citizen.getGender()%></td>
					<td><%=citizen.getBenefitAmount()%></td>
				</tr>

				<%
				}
				} else {
				%>
					<tr>
						<td colspan="10" style="color:red" class="text-center">No record Found !!</td>
					</tr>
				<%
				}
				%>
			</table>
		</div>

		<div>
			<table class="table">
				<tr>
					<td>Export :
					    <button id="excelButton" onclick="excelMailBox()">Excel</button>
					    <button id="pdfButton" onclick="pdfMailBox()">Pdf</button>
					</td>
				</tr>
			</table>
		</div>
		<hr>
	</div>

	    <!-- Pdf Modal -->
        <div class="modal fade" id="pdfModal" tabindex="-1" aria-labelledby="successModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="successModalLabel">Mail Details To Send Pdf</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                     <div class="modal-body">
                        <div class="container">
                             <form:form method="GET" action="/exportPdf"
                                            modelAttribute="mailDetails">

                                            <table class="table table-borderless ">
                                                <tr>
                                                    <td><form:label path="to"><sup>*</sup>To</form:label></td>
                                                    <td><form:input path="to" required="true" /></td>
                                                </tr>
                                                <tr>
                                                    <td><form:label path="subject"><sup>*</sup>Subject</form:label></td>
                                                    <td><form:input path="subject" required="true" /></td>
                                                </tr>
                                                <tr>
                                                    <td><form:label path="body">Body</form:label></td>
                                                    <td><form:textarea path="body" /></td>
                                                </tr>
                                                <tr>
                                                    <td></td>
                                                    <td></td>
                                                    <td>
                                                        <button type="submit" class="btn btn-primary" data-bs-dismiss="modal">Send Mail</button>
                                                    </td>
                                                </tr>
                                            </table>
                             </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Excel Modal -->
                <div class="modal fade" id="excelModal" tabindex="1" aria-labelledby="successModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="successModalLabel">Mail Details To Send Excel</h5>
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                <div class="container">
                                     <form:form method="GET" action="/exportExcel"
                                     				modelAttribute="mailDetails">

                                     				<table class="table table-borderless ">
                                     					<tr>
                                     						<td><form:label path="to"><sup>*</sup>To</form:label></td>
                                     						<td><form:input path="to" required="true" /></td>
                                     					</tr>
                                     					<tr>
                                                            <td><form:label path="subject"><sup>*</sup>Subject</form:label></td>
                                                            <td><form:input path="subject" required="true" /></td>
                                                        </tr>
                                                        <tr>
                                                            <td><form:label path="body">Body</form:label></td>
                                                            <td><form:textarea path="body" /></td>
                                                        </tr>
                                                        <tr>
                                                            <td></td>
                                                            <td></td>
                                                            <td>
                                                                <button type="submit" class="btn btn-primary" data-bs-dismiss="modal">Send Mail</button>
                                                            </td>
                                                        </tr>
                                                    </table>
                                     </form:form>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>

        <script>

            function pdfMailBox(){

                 const pdfModal = new bootstrap.Modal(document.getElementById('pdfModal'));
                 pdfModal.show();

            }

             function excelMailBox(){

                  const excelModal = new bootstrap.Modal(document.getElementById('excelModal'));
                  excelModal.show();

             }

             document.addEventListener("DOMContentLoaded", function() {
                         const isData = <%= (Boolean.TRUE.equals(request.getAttribute("isData")) ? "true" : "false") %>;
                         console.log(isData);
                         if(!isData){
                            document.getElementById('pdfButton').disabled = true;
                            document.getElementById('excelButton').disabled = true;
                         }
                   });
        </script>

	<script
		src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-Fy6S3B9q64WdZWQUiU+q4/2Lc9npb8tCaSX9FK7E8HnRr0Jz8D6OP9dO5Vg3Q9ct"
		crossorigin="anonymous"></script>

		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>