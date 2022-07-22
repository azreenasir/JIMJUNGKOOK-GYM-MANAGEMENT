<%-- 
    Document   : login
    Created on : Jul 4, 2022, 10:45:30 PM
    Author     : azree
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>GYM MANAGEMENT</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="#" style="color: white">JIMJUNGKOOK GYM MANAGEMENT</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
            </div>
        </nav>
        <div class="container">
            <div class="row justify-content-center" style="margin-top: 40px">
                <div class="col-md-8">
                    <div class="card">
                        <div class="card-header">Login</div>

                        <div class="card-body">
                            <form method="POST" action="login.servlet">
                                <div class="row mb-3">
                                    <label for="email" class="col-md-4 col-form-label text-md-end">Username</label>

                                    <div class="col-md-6">
                                        <input id="email" type="text" class="form-control" name="username"  required autocomplete="username" autofocus>
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <label for="password" class="col-md-4 col-form-label text-md-end">Password</label>

                                    <div class="col-md-6">
                                        <input id="password" type="password" class="form-control" name="password" required autocomplete="current-password">

                                    </div>
                                </div>
                                <div class="row mb-0">
                                    <div class="col-md-8 offset-md-4">
                                        <button type="submit" class="btn btn-primary">
                                            Login
                                        </button>

                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>

