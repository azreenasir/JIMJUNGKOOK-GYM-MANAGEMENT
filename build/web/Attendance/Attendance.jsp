<%-- 
    Document   : Attendance
    Created on : Jul 10, 2022, 5:57:28 PM
    Author     : azree
--%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<sql:setDataSource var="myDatasource" 
                   driver="org.apache.derby.jdbc.ClientDriver" 
                   url="jdbc:derby://localhost:1527/GymDb" user="app" 
                   password="app"/>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Attendance</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" ></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
    </head>
    <body>

        <nav class="navbar navbar-expand-lg navbar-light bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="home.jsp" style="color: white">JIMJUNGKOOK GYM MANAGEMENT</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="../home.jsp" style="color: white">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="../Client/Client.jsp" style="color: white">Client</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="../Trainer/Trainer.jsp" style="color: white">Trainer</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="../Shift/Shift.jsp" style="color: white">Shift</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="../Attendance/Attendance.jsp" style="color: white">Attendance</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="../Package/Package.jsp" style="color: white">Package</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="../Payment/Payment.jsp" style="color: white">Payment</a>
                        </li>
                    </ul>
                    <div class="d-flex me-5 pe-5" >
                        <ul class="navbar-nav">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" style="color: white" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    ${name}
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <li><a class="dropdown-item" href="../report.jsp">Report</a></li>
                                    <li><a class="dropdown-item" href="../sales.jsp">Generate sales</a></li>
                                    <li><hr class="dropdown-divider"></li>
                                    <li><a class="dropdown-item" href="../Logout.Servlet">Logout</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>

        <sql:query var="res" dataSource="${myDatasource}">
            SELECT ATTENDANCEID, DATE, ATTENDANCEDESC, ATTENDANCE.CLIENTID, CLIENT.FIRSTNAME, CLIENT.LASTNAME FROM ATTENDANCE JOIN CLIENT ON ATTENDANCE.CLIENTID = CLIENT.CLIENTID
        </sql:query>
        <div class="container">
            <div class="d-flex justify-content-between m-4">
                <div>
                    <h3>Attendance Record</h3>
                </div>
                <div>
                    <a href="AddAttendance.jsp" class="btn btn-primary">Make new Attendance</a>
                </div>
            </div>
            <hr>
            <div class="row justify-content-center" style="margin-top: 10px">
                <div class="col-md-12">
                    <div class="card mb-3" >
                        <div class="card-header">
                            Attendance
                        </div>
                        <div class="card-body">
                            <table class="table table-dark table-striped table-hover">
                                <tr>
                                    <th><center>ATTENDANCE DATE</center></th>
                                <th><center>DESCRIPTION</center></th>
                                <th><center>CLIENT NAME</center></th>
                                </tr>
                                <c:forEach var="attend" items="${res.rows}">
                                    <tr>
                                        <td><center><c:out value="${attend.date}" /></center></td>
                                    <td><center><c:out value="${attend.attendancedesc}" /></center></td>
                                    <td><center> <c:out value="${attend.firstname}" /> <c:out value="${attend.lastname}" /> </center></td>
                                    </tr>
                                </c:forEach>
                            </table>     
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </body>
</html>