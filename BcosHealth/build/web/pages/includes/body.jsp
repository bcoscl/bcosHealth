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
<!--        <ul class="nav navbar-nav d-md-down-none">
            <li class="nav-item px-3">
                <a class="nav-link" href="#">Suscripciones</a>
            </li>
            <li class="nav-item px-3">
                <a class="nav-link" href="#">Fichas</a>
            </li>
            <li class="nav-item px-3">
                <a class="nav-link" href="#">Pacientes</a>
            </li>        
        </ul>-->


        <!-- bara superior derecha -->
        <ul class="nav navbar-nav ml-auto">
            <li class="breadcrumb-item d-md-down-none">Dr. Cristobal - Kinesiologo</li>          
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
                    <a class="dropdown-item" href="../base/LoginPage.jsp">
                        <i class="fa fa-lock"></i> Logout</a>
                </div>
            </li>
        </ul>

    </header>
    <div class="app-body">
        <!-- menu Lateral -->
        <div class="sidebar">
            <nav class="sidebar-nav">
                <ul class="nav">
                    <li class="nav-item">
                        <a class="nav-link" href="../base/index.jsp">
                            <i class="nav-icon icon-home"></i> Home

                        </a>
                    </li>
                    
                    
                    
                      <li class="nav-title">SUPER ADMIN</li>
                    <li class="nav-item nav-dropdown">			
                        <a class="nav-link nav-dropdown-toggle" href="#">
                            <i class="nav-icon icon-settings"></i> Advanced</a>
                        <ul class="nav-dropdown-items">
                            <li class="nav-item nav-dropdown">		
                                <a class="nav-link nav-dropdown-toggle ng-scope md-default-theme" href="#">
                                     <i class="nav-icon icon-puzzle"></i>Configuraciones</a>

                                <ul class="nav-dropdown-items">
                                    <li class="nav-item ng-scope" >
                                        <a class="nav-link" href="../Configuraciones/ListarConfiguraciones.jsp">
                                            <i class="nav-icon icon-list"></i>Listar Configuraciones
                                        </a>
                                    </li>
                                    <li class="nav-item ng-scope">
                                        <a class="nav-link" href="../Configuraciones/crearConfiguraciones.jsp">
                                            <i class="nav-icon icon-plus"></i>Crear Configuracion
                                        </a>
                                    </li>


                                </ul>


                            </li>
                            <li class="nav-item nav-dropdown">		
                                <a class="nav-link nav-dropdown-toggle ng-scope md-default-theme" href="#">
                                    <i class="nav-icon icon-tag"></i>Suscripciones</a>

                                <ul class="nav-dropdown-items">
                                    <li class="nav-item ng-scope" >
                                        <a class="nav-link" href="../Suscripciones/ListarSuscripciones.jsp">
                                            <i class="nav-icon icon-list"></i>Listar Suscripciones
                                        </a>
                                    </li>
                                    <li class="nav-item ng-scope">
                                        <a class="nav-link" href="../Suscripciones/crearSuscripcion.jsp">
                                            <i class="nav-icon icon-plus"></i>Crear Suscripciones
                                        </a>
                                    </li>


                                </ul>


                            </li>

                            <li class="nav-item nav-dropdown">		
                                <a class="nav-link nav-dropdown-toggle ng-scope md-default-theme" href="#">
                                    <i class="nav-icon icon-credit-card"></i>Planes</a>

                                <ul class="nav-dropdown-items">
                                    <li class="nav-item ng-scope" >
                                        <a class="nav-link" href="../Planes/ListarPlanes.jsp">
                                            <i class="nav-icon icon-list"></i>Listar Planes
                                        </a>
                                    </li>
                                    <li class="nav-item ng-scope">
                                        <a class="nav-link" href="../Planes/crearPlanes.jsp">
                                            <i class="nav-icon icon-plus"></i>Crear Planes
                                        </a>
                                    </li>


                                </ul>


                            </li>
                            
                             <li class="nav-item nav-dropdown">		
                                <a class="nav-link nav-dropdown-toggle ng-scope md-default-theme" href="#">
                                    <i class="nav-icon icon-credit-card"></i>Roles</a>

                                <ul class="nav-dropdown-items">
                                    <li class="nav-item ng-scope" >
                                        <a class="nav-link" href="../Roles/ListarRoles.jsp">
                                            <i class="nav-icon icon-list"></i>Listar Roles
                                        </a>
                                    </li>
                                    <li class="nav-item ng-scope">
                                        <a class="nav-link" href="../Roles/crearRoles.jsp">
                                            <i class="nav-icon icon-plus"></i>Crear Roles
                                        </a>
                                    </li>


                                </ul>


                            </li>
                           
                        </ul>
                    </li>

                    
                    <li class="nav-title">Administración</li>
                    <li class="nav-item nav-dropdown">			
                        <a class="nav-link nav-dropdown-toggle" href="#">
                            <i class="nav-icon icon-settings"></i> Settings</a>
                        <ul class="nav-dropdown-items">
                            
                          
                            
                            <li class="nav-item nav-dropdown">		
                                <a class="nav-link nav-dropdown-toggle ng-scope md-default-theme" href="#">
                                    <i class="nav-icon icon-credit-card"></i>Profesiones</a>

                                <ul class="nav-dropdown-items">
                                    <li class="nav-item ng-scope" >
                                        <a class="nav-link" href="../Profesiones/ListarProfesiones.jsp">
                                            <i class="nav-icon icon-list"></i>Listar Profesiones
                                        </a>
                                    </li>
                                    <li class="nav-item ng-scope">
                                        <a class="nav-link" href="../Profesiones/crearProfesiones.jsp">
                                            <i class="nav-icon icon-plus"></i>Crear Profesión
                                        </a>
                                    </li>


                                </ul>


                            </li>
                            <li class="nav-item nav-dropdown">	
                        <a class="nav-link nav-dropdown-toggle" href="#">
                            <i class="nav-icon icon-location-pin"></i>Sucursales</a>
                        <ul class="nav-dropdown-items">
                            <li class="nav-item">
                                <a class="nav-link" href="../Sucursales/ListarSucursales.jsp">
                                    <i class="nav-icon icon-list"></i>Listar Sucursales
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="../Sucursales/crearSucursales.jsp">
                                    <i class="nav-icon icon-plus"></i>Crear Sucursales
                                </a>
                            </li>


                        </ul>

                    </li>
                            

                        </ul>
                    </li>

                    <li class="nav-item nav-dropdown">              
                        <a class="nav-link nav-dropdown-toggle" href="#">
                            <i class="nav-icon icon-people"></i> Users</a>

                        <ul class="nav-dropdown-items">
                            <li class="nav-item">
                                <a class="nav-link" href="../Users/ListarUsers.jsp">
                                    <i class="nav-icon icon-list"></i>Listar Users
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="../Users/crearUsers.jsp">
                                    <i class="nav-icon icon-plus"></i>Crear Users
                                </a>
                            </li>


                        </ul>

                    </li>
                    

                    <li class="nav-title">Modulos</li>
                    
                     <li class="nav-item">
                        <a class="nav-link" href="../Atencion/atencionList.jsp">
                            <i class="nav-icon icon-eye"></i> Atención</a>
                        
                    </li>
                    
                    

                    
                    <li class="nav-item nav-dropdown">  
                        <a class="nav-link nav-dropdown-toggle" href="#">
                            <i class="nav-icon icon-emotsmile"></i> Recepción</a>

                        <ul class="nav-dropdown-items">
                            
                            <li class="nav-item">
                                <a class="nav-link" href="../Pacientes/ListarPacientes.jsp">
                                    <i class="nav-icon icon-list"></i>Listar Pacientes
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="../Pacientes/crearPacientes.jsp">
                                    <i class="nav-icon icon-plus"></i>Crear Pacientes
                                </a>
                            </li>
                             <li class="nav-item">
                                <a class="nav-link" href="../Atencion/MedicosList.jsp">
                                    <i class="nav-icon icon-list"></i>Listas de Atención
                                </a>
                            </li>


                        </ul>		


                    </li>
                    
                    <li class="nav-item nav-dropdown">
                        <a class="nav-link nav-dropdown-toggle" href="#">
                            <i class="nav-icon icon-note"></i> Fichas</a>
                        <ul class="nav-dropdown-items">
                            <li class="nav-item">
                                <a class="nav-link" href="../Fichas/listarFichas.jsp">
                                    <i class="nav-icon icon-list"></i>Listar Fichas
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="../Fichas/ExportarFichas.jsp">
                                    <i class="nav-icon icon-arrow-down-circle"></i>Expotar Fichas
                                </a>
                            </li>
<!--                            <li class="nav-item">
                                <a class="nav-link" href="../Fichas/crearFichas.jsp">
                                    <i class="nav-icon icon-plus"></i>Crear Fichas
                                </a>
                            </li>-->


                        </ul>


                    </li>
                    <li class="nav-item nav-dropdown">
                        <a class="nav-link nav-dropdown-toggle" href="#">
                            <i class="nav-icon icon-paper-clip"></i> Examenes</a>
                        <ul class="nav-dropdown-items">
                            <li class="nav-item">
                                <a class="nav-link" href="../Examenes/listarExamenes.jsp">
                                    <i class="nav-icon icon-list"></i>Listar Examenes
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="../Examenes/crearExamenes.jsp">
                                    <i class="nav-icon icon-plus"></i>Crear Examenes
                                </a>
                            </li>


                        </ul>

                    </li>
                    

                </ul>
            </nav>
            <button class="sidebar-minimizer brand-minimizer" type="button"></button>
        </div>
        <main class="main">
            <!-- Breadcrumb-->
            <ol class="breadcrumb d-md-none">
                <li class="breadcrumb-item">Dr. Cristobal - Kinesiologo</li>  
            </ol>
            <ol class="breadcrumb d-none d-lg-block"></ol>

            <div class="container-fluid"  >