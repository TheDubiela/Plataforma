var AtividadeController = new function() {

    this.list = function() {
        $('#atividadesTableBody').empty();

        $.get( "/atividades", function( data ) {
            $.each(data, function(i, item) {
                $('<tr>').append(
                    $('<td>').text(item.idAtividade),
                    $('<td>').text(item.titulo),
                    $('<td>').text(item.descricao),
                    $('<td>').text(item.projeto.idProjeto),
                    $('<td>').text(item.cliente.idCliente),
                    $('<td class="actions"><a class="btn btn-warning btn-xs" onclick="AtividadeController.edit('+item.idAtividade+')">Editar</a><a class="btn btn-danger btn-xs" onclick="AtividadeController.delete('+item.idAtividade+')">Excluir</a></td>')
                ).appendTo('#atividadesTableBody');
            });
        });
    }

    this.atividadeList = function(id) {
        $('#listaDeAtividades').modal('show');
        $('#atividadesListTableBody').empty();
        $.get( "/atividades", function( data ) {
            $.each(data, function(i, item) {
                if(item.projeto.idProjeto == id){
                    $('<tr>').append(
                        $('<td>').text(item.idAtividade),
                        $('<td>').text(item.titulo),
                        $('<td>').text(item.descricao),
                        $('<td>').text(item.cliente.idCliente)).appendTo("#atividadesListTableBody");
                }
            });
        });
    }

    this.delete = function(idAtividade) {
        $.ajax({
            url: '/atividades/'+idAtividade,
            method: 'DELETE',
            contentType: 'application/json',
            success: function(result) {
                AtividadeController.list();
            },
            error: function(request,msg,error) {
                alert('erro ao deletar');
            }
        });
    }

    this.save = function() {
        var idAtividadeToEdit = $("#atividadeId").val();

        if (idAtividadeToEdit == null || idAtividadeToEdit == "") {
            var atividade = this.getDadosAtividadeModal();

            $.ajax({
                url: '/atividades',
                type: 'POST',
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                data: JSON.stringify(atividade),
                success: function() {
                    $("#atividadeId").val("");
                    $('#cadastrarAtividade').modal('hide');
                    AtividadeController.list();
                    AtividadeController.limparDadosAtividadeModal();
                },
                error: function(request,msg,error) {
                    $("#atividadeId").val("");
                    $('#cadastrarAtividade').modal('hide');
                    AtividadeController.list();
                    AtividadeController.limparDadosAtividadeModal();
                }
            });
        }
        else {
            AtividadeController.update(idAtividadeToEdit);
        }

    }

    this.update = function(idAtividade) {
        var atividade = this.getDadosAtividadeModal();

        $.ajax({
            url: '/atividades/'+idAtividade,
            method: 'PUT',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(atividade),
            success: function(result) {
                $("#atividadeId").val("");
                $('#cadastrarAtividade').modal('hide');
                AtividadeController.list();
                AtividadeController.limparDadosAtividadeModal();
            },
            error: function(request,msg,error) {
                $("#atividadeId").val("");
                $('#cadastrarAtividade').modal('hide');
                AtividadeController.list();
                AtividadeController.limparDadosAtividadeModal();
            }
        });
    }

    this.edit = function(idAtividade) {
        $("#atividadeId").val(idAtividade);

        $.get( "/atividades/" + idAtividade, function( data ) {
            $('#cadastrarAtividade').modal('show');
            AtividadeController.setDadosAtividadeModal(data);
        });
    }

    this.setDadosAtividadeModal = function(projeto) {
        $('#atividadeTitulo').val(projeto.titulo),
            $('#atividadeDescricao').val(projeto.descricao),
            $('#projetoSelectList').val(projeto.projeto.idProjeto),
            $('#clienteSelectList').val(projeto.cliente.idCliente)
    }

    this.limparDadosAtividadeModal = function() {
        $('#atividadeTitulo').val(''),
            $('#atividadeDescricao').val(''),
            $('#projetoSelectList').val(''),
            $('#clienteSelectList').val('')
    }

    this.getDadosAtividadeModal = function() {
        var atividade = {
            titulo: $('#atividadeTitulo').val(),
            descricao: $('#atividadeDescricao').val(),
            projeto : {
                idProjeto : $('#projetoSelectList').val()
            },
            cliente : {
                idCliente : $('#clienteSelectList').val()
            }
        }

        return atividade;
    }

}