<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    
 <!-- Modal -->
<div class="modal fade" id="exampleModalUsuario" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Pesquisar Usuário</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
               <div class="col-md-4 mb-3">
			      <label for="validationServer01">Nome</label>
			      <input type="text" class="form-control is-valid" id="validationServer01" placeholder="Nome" required>
			      <div class="valid-feedback">
			        Looks good!
                  </div>
               </div>
               <button class="btn btn-primary" type="submit" onclick="buscarUsuario();">Pesquisar</button>
          <div style="height:300px;overflow:scroll;">
			  <table id="table_resultados" class="table table-dark">
				  <thead>
				    <tr>
				      <th scope="col">ID</th>
				      <th scope="col">NOME</th>
				      <th scope="col">EMAIL</th>
				      <th scope="col">PERFIL</th>
				    </tr>
				  </thead>
				  <tbody>
				      
				  </tbody>
	           </table>
		   </div>
      </div>
      <span id="total_results"></span>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fechar</button>
      </div>
    </div>
  </div>
</div>

