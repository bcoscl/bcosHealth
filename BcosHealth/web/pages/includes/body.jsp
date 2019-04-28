<script src="../../pages/includes/js/body.js"></script>
<body class="app header-fixed sidebar-fixed aside-menu-fixed sidebar-lg-show">
    
    
    
    
     <header class="app-header navbar">
        <button class="navbar-toggler sidebar-toggler d-lg-none mr-auto" type="button" data-toggle="sidebar-show">
            <span class="navbar-toggler-icon"></span>
        </button>
        <a class="navbar-brand" href="#">
            <img class="navbar-brand-full" src="../../comun/img/brand/logo.png" width="89" height="40" alt=" Logo full">
            <img class="navbar-brand-minimized" src="../../comun/img/brand/logo.png" width="30" height="30" alt=" Logo minimized">
        </a>
        <button class="navbar-toggler sidebar-toggler d-md-down-none" type="button" data-toggle="sidebar-lg-show">
            <span class="navbar-toggler-icon"></span>
        </button>


        <!-- opciones superiores -->
                <ul class="nav navbar-nav d-md-down-none">
                    <li class="nav-item px-3">
                        <a class="nav-link" href="javascript:SuccesNotify();">Notify Success</a>
                    </li>
                    <li class="nav-item px-3">
                        <a class="nav-link" href="javascript:DangerNotify();">Notify Danger</a>
                    </li>
<!--                    <li class="nav-item px-3">
                        <a class="nav-link" href="#">Fichas</a>
                    </li>
                    <li class="nav-item px-3">
                        <a class="nav-link" href="#">Pacientes</a>
                    </li>        -->
                </ul>


        <!-- bara superior derecha -->
        <ul class="nav navbar-nav ml-auto">
            <li class="breadcrumb-item d-md-down-none"><span id="usuarioSession_header"></span></li>          
            <li class="nav-item dropdown">		
                <a class="nav-link" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                    <img class="img-avatar" src="../../comun/img/avatars/6.jpg" alt="admin@bootstrapmaster.com">
                </a>
                <div class="dropdown-menu dropdown-menu-right">
                    <div class="dropdown-header text-center">
                        <strong>Account</strong>
                    </div>
                    <a class="dropdown-item" href="../../ServletUserProfileRedirect">
                        <i class="fa fa-user"></i> Profile</a>
                    <a class="dropdown-item" href="../Perfil/ChangePass.jsp">
                        <i class="fa fa-key"></i> Cambiar Clave</a>
                    <a class="dropdown-item" href="../../ServletLogOut">
                        <i class="fa fa-lock"></i> Logout</a>
                </div>
            </li>
        </ul>
    </header>
    <div class="app-body" >
        <!-- menu Lateral -->
        <div class="sidebar" >
           
           <nav class="sidebar-nav" id="menuContent">
            </nav>
            <button class="sidebar-minimizer brand-minimizer" type="button"></button>
            
            
        </div>
        <main class="main">
            <!-- Breadcrumb-->
            <ol class="breadcrumb d-md-none">
                <li class="breadcrumb-item"><span id="usuarioSession_header2"></span></li>  
            </ol>
            <ol class="breadcrumb d-none d-lg-block"></ol>

            <div class="container-fluid"  >