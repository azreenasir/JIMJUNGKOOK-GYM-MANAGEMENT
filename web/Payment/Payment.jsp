<%-- 
    Document   : Payment
    Created on : Jul 9, 2022, 10:19:13 PM
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
        <title>Payment</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" ></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
    </head>
    <body>
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
            SELECT PAYMENTID, PAYMENTAMOUNT, PAYMENTDESC, PAYMENTDATE, PAYMENT.CLIENTID, CLIENT.FIRSTNAME,CLIENT.LASTNAME, PAYMENT.PACKAGEID, PACKAGE.PACKAGETITLE FROM PAYMENT JOIN CLIENT ON PAYMENT.CLIENTID=CLIENT.CLIENTID 
            JOIN PACKAGE ON PAYMENT.PACKAGEID = PACKAGE.PACKAGEID
        </sql:query>


        <div class="container">
            <div class="d-flex justify-content-between m-4">
                <div>
                    <h3>Record of Payment</h3>
                </div>
                <div>
                    <a href="AddPayment.jsp" class="btn btn-primary">Make new Payment</a>
                </div>
            </div>
            <hr>
            <div class="row justify-content-center" style="margin-top: 10px">
                <div class="col-md-12">
                    <div class="card mb-3" >
                        <div class="card-header">
                            Payment Record
                        </div>
                        <div class="card-body">
                            <table class="table table-bordered table-dark table-striped table-hover">
                                <!-- column headers -->
                                <tr>
                                    <th><center>PAYMENT AMOUNT</center></th>
                                <th><center>PAYMENT DESC</center></th>
                                <th><center>PAYMENT DATE</center></th>
                                <th><center>CLIENT NAME</center></th>
                                <th><center>PACKAGE NAME</center></th>
                                </tr>
                                <!-- column data -->
                                <c:forEach var="table" items="${res.rows}">
                                    <tr>
                                        <td><center><c:out value="${table.paymentamount}" /></center></td>
                                    <td><center><c:out value="${table.paymentdesc}" /></center></td>
                                    <td><center><c:out value="${table.paymentdate}" /></center></td>
                                    <td><center> <c:out value="${table.firstname}" /> <c:out value="${table.lastname}" /> </center></td>
                                    <td><center><c:out value="${table.packagetitle}" /></center></td>
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
