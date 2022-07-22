<%-- 
    Document   : AddPayment
    Created on : Jul 9, 2022, 11:30:04 PM
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
        <title>Make Payment</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet" />
        <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
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
                                    
                                    <li><hr class="dropdown-divider"></li>
                                    <li><a class="dropdown-item" href="../Logout.Servlet">Logout</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>
        <sql:query var="result" dataSource="${myDatasource}">
            SELECT * FROM CLIENT
        </sql:query>

        <sql:query var="result1" dataSource="${myDatasource}">
            SELECT * FROM PACKAGE
        </sql:query>


        <div class="container-fluid">
            <div class="row justify-content-center" style="margin-top: 10px">
                <div class="col-md-6">
                    <div class="card mb-3" >
                        <div class="card-header">
                            Make Payment
                        </div>
                        <div class="card-body">
                            <form action='../addPayment.Sevlet' method='POST'>
                                <div class="mb-3">
                                    <label class="form-label">Client: </label>
                                    <p>
                                        <select class="select2" name="client">
                                            <option selected disabled>Choose Client:</option>
                                            <c:forEach var="user" items="${result.rows}">
                                                <option value="${user.clientid}"><c:out value="${user.firstname}"/> <c:out value="${user.lastname}"/></option>
                                            </c:forEach>
                                        </select>
                                    </p>
                                </div>

                                <div class="mb-3">
                                    <label class="form-label">Package: </label>
                                    <select class=" form-control" name="package">
                                        <option selected disabled>Choose Package:</option>
                                        <c:forEach var="columnName" items="${result1.columnNames}">
                                            <c:if test="${columnName.equals('PACKAGETITLE')}">
                                                <c:forEach var="row" items="${result1.rowsByIndex}">
                                                    <c:forEach var="column" items="${row}">
                                                        <c:if test="${column == row[1]}">
                                                            <option value="${row[0]}"><c:out value="${column}"/></option>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:forEach>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>

                                <div class="mb-3">
                                    <label  class="form-label">Payment Amount: </label>
                                    <input type="number" name="pymtAmount" step="any" class="form-control" required>
                                </div>

                                <div class="mb-3">
                                    <label class="form-label">Payment Description: </label>
                                    <textarea name="pymtDesc" class="form-control" required></textarea>
                                </div>

                                <div class="mb-3">
                                    <label class="form-label">Payment Date: </label>
                                    <input type="date" name="pymtDate" class="form-control" required>
                                </div>

                                <button type="submit" class="btn btn-primary">Confirm Payment</button>
                            </form>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <script type="text/javascript">
            $(document).ready(function () {
                $('.select2').select2();
            });
        </script>
    </body>
</html>

