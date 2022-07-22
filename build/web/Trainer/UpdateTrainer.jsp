<%-- 
    Document   : UpdateTrainer
    Created on : Jul 5, 2022, 10:48:09 PM
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
        <title>Update Trainer</title>
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

        <c:set var="id" value="${param.id}"/>

        <sql:query var="result" dataSource="${myDatasource}">
            SELECT TRAINERID, FIRSTNAME, LASTNAME, DOB, PHONENO, GENDER, EMAIL, TRAINER.SHIFTID, SHIFT.SHIFTTITLE FROM TRAINER JOIN SHIFT ON TRAINER.SHIFTID=SHIFT.SHIFTID WHERE TRAINERID =?
            <sql:param value="${id}"/>
        </sql:query>
            
        <sql:query var="result2" dataSource="${myDatasource}">
            SELECT * FROM APP.SHIFT
        </sql:query>
            

        <c:forEach var="table" items="${result.rows}">
            <c:set var="firstname" value="${table.firstname}"/>
            <c:set var="lastname" value="${table.lastname}"/>
            <c:set var="dob" value="${table.dob}"/>
            <c:set var="phoneno" value="${table.phoneno}"/>
            <c:set var="gender" value="${table.gender}"/>
            <c:set var="email" value="${table.email}"/>
            <c:set var="shiftid" value="${table.shiftid}"/>
            <c:set var="shifttitle" value="${table.shifttitle}"/>
        </c:forEach>

        <div class="container-fluid">
            <div class="row justify-content-center" style="margin-top: 10px">
                <div class="col-md-6">
                    <div class="card mb-3" >
                        <div class="card-header">
                            Update Trainer
                        </div>
                        <div class="card-body">
                            <form action='../updateTrainer.Servlet' method='POST'>
                                <div class="mb-3">
                                    <label  class="form-label">First Name: </label>
                                    <input type="hidden" value="${id}" name="id">
                                    <input type="text" name="firstname" value="${firstname}" class="form-control" required>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Last Name: </label>
                                    <input type="text" name="lastname" value="${lastname}" class="form-control" required>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Date of Birth: </label>
                                    <input type="date" name="dob" value="${dob}" class="form-control" required>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Phone Number: </label>
                                    <input type="number" name="phoneNum" value="${phoneno}" class="form-control" required>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Gender: </label>
                                    <select class="form-select" name="gender">
                                        <option disabled>Choose Gender:</option>
                                        <c:if test="${(gender == 'male')}" var="var">
                                            <option selected value="male">Male</option>
                                            <option value="female">Female</option>
                                        </c:if>
                                        <c:if test="${(gender == 'female')}" var="var">
                                            <option selected value="female">Female</option>
                                            <option value="male">Male</option>
                                        </c:if>
                                    </select>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Email: </label>
                                    <input type="email" name="email" class="form-control" value="${email}" required/>
                                </div>
                                <div class="mb-3">
                                    <label class="form-label">Choose Shift: </label>
                                    <select class="form-select" name="shift">
                                    <option disabled>Choose Shift:</option>
                                    <c:forEach var="table" items="${result.rows}">
                                        <option selected value="${table.SHIFTID}"><c:out value="${table.SHIFTTITLE}"/></option>
                                    </c:forEach>
                                    <c:forEach var="table2" items="${result2.rows}">
                                        <c:if test="${(table2.shiftid != shiftid)}">
                                            <option value="${table2.SHIFTID}"><c:out value="${table2.SHIFTTITLE}"/></option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                                </div>
                                
                                <button type="submit" class="btn btn-primary">Update Trainer</button>
                            </form>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </body>
</html>
