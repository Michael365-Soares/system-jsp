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
                                                        <form class="form-material" action="<%= request.getContextPath() %>/ServletUsuarioController" id="formUser" method="post">
                                                            <input type="hidden" name="acao" id="acao" value="">
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="text" name="id" id="id" class="form-control" readonly="readonly" value="${modelLogin.id}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">ID</label>
                                                            </div>
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="text" name="login" class="form-control" placeholder="Login" required="" value="${modelLogin.login}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Login</label>
                                                            </div>
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="email" name="email" class="form-control" placeholder="Email" required="" value="${modelLogin.email}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Email</label>
                                                            </div>
                                                            <div class="form-group form-default form-static-label">
                                                                <input type="password" name="senha" class="form-control" placeholder="Senha" required="" value="${modelLogin.senha}">
                                                                <span class="form-bar"></span>
                                                                <label class="float-label">Senha</label>
                                                            </div>
                                                            
                                                            <button class="btn btn-success waves-effect waves-light">Salvar</button>
                                                            <button type="button" class="btn btn-primary waves-effect waves-light" onclick="limparForm();">Novo</button>
                                                            <button type="button" class="btn btn-info waves-effect waves-light" onclick="criarDeleteComAjax();">Excluir</button>
                                                            
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
    
    <script type="text/javascript">
          
          function criarDeleteComAjax(){
        	  var action=document.getElementById("formUser").action;
        	  var userId=document.getElementById("id").value;
        	  if(confirm("Deseja realmente deletar este usuário?")){ 
	        	  $.ajax({
	        		  method:"get",
	        	      url:action,
	        	      data:"id=" + userId + "&acao=deletarComAjax",
	        	      success:function(response){
	        	    	  alert(response);
	        	      }
	        	  }).fail(function(xhr,status,errorThrown){
	        		  alert("Error ao deletar usuário por id: "+xhr.responseText);
	        	  });
        	  }
          }
          
	      function criarDelete(){  
	    	 if(confirm("Deseja efetuar a ação de excluir???")){
		         document.getElementById("formUser").method='get';
		         document.getElementById("acao").value='deletar';
		         document.getElementById("formUser").submit();
	    	 }
	      }
	      
	      function limparForm(){
	         var elementos=document.getElementById("formUser").elements;
	         for(p=0;p<elementos.length;p++){
	        	 elementos[p].value='';
	         }
	      } 
	         
    </script>
    
</body>

</html>
