<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="Jeremy Palme">
        <meta name="generator" content="Hugo 0.84.0">
        <title>Flow Control</title>

        <!-- Bootstrap core CSS -->
        <link href="src/bootstrap-5.0.2-dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="src/jquery-ui-1.13.0.custom/jquery-ui.min.css" rel="stylesheet">
        <!-- <link href="{{ asset('fontawesome/all.css') }}" rel="stylesheet">src\font-awesome-4.7.0\css -->
        <link href="src/font-awesome-4.7.0/css/font-awesome.min.css" rel="stylesheet">
        <link href="src/jQuery-contextMenu-master/dist/jquery.contextMenu.min.css" rel="stylesheet">
        <meta name="csrf-token" content="{{ csrf_token() }}" />
        
        <meta name="theme-color" content="#7952b3">

        <style>
          .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            user-select: none;
          }

          @media (min-width: 768px) {
            .bd-placeholder-img-lg {
              font-size: 3.5rem;
            }
          }

          body {
              min-height: 75rem;
              padding-top: 3.5rem;
        }
        </style>

        @yield('styles')
    
    </head>

    <body>
    
        <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="https://getbootstrap.com/docs/5.0/examples/navbar-fixed/#">Flow Control</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                  <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarCollapse">
                    <ul class="navbar-nav me-auto mb-2 mb-md-0">
                        <li class="nav-item">
                                <a class="nav-link" href="http://10.10.186.10/flowcontrol">Main</a>
                        </li>
                        <li class="nav-item">
                            
                                <a class="nav-link" href="configurations">Configurations</a>
                            
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <main class="container">
            
            @yield('content')

        </main>

        <script src="src/bootstrap-5.0.2-dist/js/bootstrap.bundle.js"></script>
        <script src="js/jquery-3.6.0.min.js"></script>
        <script src="js/popper.min.js"></script>
        <script src="src/jquery-ui-1.13.0.custom/jquery-ui.min.js"></script>
        <script src="js/moment.js"></script>
        <script src="src/jQuery-contextMenu-master/dist/jquery.contextMenu.js"></script>
        <script src="src/jQuery-contextMenu-master/dist/jquery.ui.position.min.js"></script>
        

        @yield('scripts')

    </body>
</html>


