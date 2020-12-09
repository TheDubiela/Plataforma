var ProjetoController = new function() {

    this.list = function() {
        $('#projetosTableBody').empty();

        $.get( "/projetos", function( data ) {
            $.each(data, function(i, item) {
                $('<tr>').append(
                    $('<td>').text(item.idProjeto),
                    $('<td>').text(item.titulo),
                    $('<td>').text(item.descricao),
                    $('<td>').text(item.status),
                    $('<td>').text(item.cliente.idCliente),
                    $('<td class="actions"><a class="btn btn-warning btn-xs" onclick="AtividadeController.atividadeList('+item.idProjeto+')">Atividades</a>'),
                    $('<td class="actions"><a class="btn btn-warning btn-xs" onclick="ProjetoController.edit('+item.idProjeto+')">Editar</a><a class="btn btn-danger btn-xs" onclick="ProjetoController.delete('+item.idProjeto+')">Excluir</a></td>')
                ).appendTo('#projetosTableBody');
            });
        });
    }

    this.projetosList = function() {
        $('#projetoSelectList').empty();
        $.get( "/projetos", function( data ) {
            $.each(data, function(i, item) {
                $('<option value='+item.idProjeto+'>'+item.titulo+'</option>').appendTo('#projetoSelectList');
            });
        });
    }

    this.clienteListByProjetoId = function() {
        $("#projetoSelectList").change(function() {
            $('#clienteSelectList').empty();
            var id = $(this).val();
            $.get( "/projetos", function( data ) {
                $.each(data, function(i, item) {
                    if (item.idProjeto == id) {
                        $('<option value='+ item.cliente.idCliente +'>'+ item.cliente.idCliente+'</option>').appendTo('#clienteSelectList');
                    }
                });
            });
        });
    }

    this.delete = function(idProjeto) {
        $.ajax({
            url: '/projetos/'+idProjeto,
            method: 'DELETE',
            contentType: 'application/json',
            success: function(result) {
                ProjetoController.list();
            },
            error: function(request,msg,error) {
                alert('erro ao deletar');
            }
        });
    }

    this.save = function() {
        var idProjetoToEdit = $("#projetoId").val();

        if (idProjetoToEdit == null || idProjetoToEdit == "") {
            var projeto = this.getDadosProjetoModal();

            $.ajax({
                url: '/projetos',
                type: 'POST',
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                data: JSON.stringify(projeto),
                success: function() {
                    $("#projetoId").val("");
                    $('#cadastrarProjeto').modal('hide');
                    ProjetoController.list();
                    ProjetoController.limparDadosProjetoModal();
                },
                error: function(request,msg,error) {
                    $("#projetoId").val("");
                    $('#cadastrarProjeto').modal('hide');
                    ProjetoController.list();
                    ProjetoController.limparDadosProjetoModal();
                }
            });
        }
        else {
            ProjetoController.update(idProjetoToEdit);
        }

    }

    this.update = function(idProjeto) {
        var projeto = this.getDadosProjetoModal();

        $.ajax({
            url: '/projetos/'+idProjeto,
            method: 'PUT',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(projeto),
            success: function(result) {
                $("#projetoId").val("");
                $('#cadastrarProjeto').modal('hide');
                ProjetoController.list();
                ProjetoController.limparDadosProjetoModal();
            },
            error: function(request,msg,error) {
                $("#projetoId").val("");
                $('#cadastrarProjeto').modal('hide');
                ProjetoController.list();
                ProjetoController.limparDadosProjetoModal();
            }
        });
    }

    this.edit = function(idProjeto) {
        $("#projetoId").val(idProjeto);

        $.get( "/projetos/" + idProjeto, function( data ) {
            $('#cadastrarProjeto').modal('show');
            ProjetoController.setDadosProjetoModal(data);
        });
    }

    this.setDadosProjetoModal = function(projeto) {
        $('#projetoTitulo').val(projeto.titulo),
            $('#projetoDescricao').val(projeto.descricao),
            $('#projetoStatus').val(projeto.status),
            $('#clienteSelectList').val(projeto.cliente.idCliente)
    }

    this.limparDadosProjetoModal = function() {
        $('#projetoTitulo').val(''),
            $('#projetoDescricao').val(''),
            $('#projetoStatus').val(''),
            $('#clienteSelectList').val('')
    }

    this.getDadosProjetoModal = function() {
        var projeto = {
            titulo: $('#projetoTitulo').val(),
            descricao: $('#projetoDescricao').val(),
            status: $('#projetoStatus').val(),
            cliente : {
                idCliente : $('#clienteSelectList').val()
            }
        }

        return projeto;
    }

}