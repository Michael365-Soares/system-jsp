<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
   
   <jsp:include page="head.jsp"></jsp:include>
   
  <body>
      
      <jsp:include page="theme-loader.jsp"></jsp:include>
      
  <!-- Pre-loader end -->
  <div id="pcoded" class="pcoded">
      <div class="pcoded-overlay-box"></div>
      <div class="pcoded-container navbar-wrapper">
      
          <jsp:include page="barra-navegacao.jsp"></jsp:include>

          <div class="pcoded-main-container">
              <div class="pcoded-wrapper">
              
                    <jsp:include page="navbar-main-menu.jsp"></jsp:include>
                    
                  <div class="pcoded-content">
                      <!-- Page-header start -->
                         <jsp:include page="page-header.jsp"></jsp:include>
                      <!-- Page-header end -->
                        <div class="pcoded-inner-content">
                            <!-- Main-body start -->
                            <div class="main-body">
                                <div class="page-wrapper">
                                    <!-- Page-body start -->
                                    <div class="page-body">
                                           
                                           <div class="row">
                                              <div class="col-sm-12">
                                                   <!-- Basic Form Inputs card start -->
                                                  <div class="card">
                                                    <div class="card-header">
                                                         <h3>Cadastro de Usuário</h3>
                                                    </div>
		                                               <div class="card-block">

			                                               <div class="col-md-6">
                                                <div class="card">
                                                    <div class="card-header">
                                                        <h5>Cad. User</h5>
                                                        <!--<span>Add class of <code>.form-control</code> with <code>&lt;input&gt;</code> tag</span>-->
                                                    </div>
                                                    <div class="card-block">
                                                        <form class="form-material" action="<%= request.getContextPath() %>/ServletUsuarioController" method="post">
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="number" name="identificador" class="form-control" required="">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">ID</label>
                                                            </div>
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="text" name="login" class="form-control" placeholder="Login" required="">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Login</label>
                                                            </div>
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="email" name="email" class="form-control" placeholder="Email" required="">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Email</label>
                                                            </div>
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="password" name="senha" class="form-control" placeholder="Senha" required="">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Senha</label>
                                                            </div>
                                                            
                                                            <button class="btn btn-primary waves-effect waves-light">Salvar</button>
                                                            <button class="btn btn-primary waves-effect waves-light">Atualizar</button>
                                                            <button class="btn btn-primary waves-effect waves-light">Excluir</button>
                                                             ${msg}
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                                   </div>
                                                </div>
                                             </div>
                                         </div>
                                    </div>
                                    <!-- Page-body end -->
                                </div>
                                <div id="styleSelector"> </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Required Jquery -->
    <jsp:include page="required-jquery.jsp"></jsp:include>
</body>

</html>
