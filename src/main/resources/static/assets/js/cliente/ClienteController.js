var ClienteController = new function() {

    this.list = function() {
        $('#clientesTableBody').empty();

        $.get( "/clientes", function( data ) {
            $.each(data, function(i, item) {
                $('<tr>').append(
                    $('<td>').text(item.idCliente),
                    $('<td>').text(item.nome),
                    $('<td>').text(item.cpf),
                    $('<td>').text(item.email),
                    $('<td class="actions"><a class="btn btn-warning btn-xs" onclick="ClienteController.edit('+item.idCliente+')">Editar</a><a class="btn btn-danger btn-xs" onclick="ClienteController.delete('+item.idCliente+')">Excluir</a></td>')
                ).appendTo('#clientesTableBody');
            });
        });
    }

    this.clienteList = function() {
        $('#clienteSelectList').empty();
        $.get( "/clientes", function( data ) {
            $.each(data, function(i, item) {
                $('<option value='+item.idCliente+'>'+item.nome+'</option>').appendTo('#clienteSelectList');
            });
        });
    }

    this.delete = function(idCliente) {
        $.ajax({
            url: '/clientes/'+idCliente,
            method: 'DELETE',
            contentType: 'application/json',
            success: function(result) {
                ClienteController.list();
            },
            error: function(request,msg,error) {
                alert('erro ao deletar');
            }
        });
    }

    this.save = function() {
        var idClienteToEdit = $("#clienteId").val();

        if (idClienteToEdit == null || idClienteToEdit == "") {
            var cliente = this.getDadosClienteModal();

            $.ajax({
                url: '/clientes',
                type: 'POST',
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                data: JSON.stringify(cliente),
                success: function() {
                    $("#clienteId").val("");
                    $('#cadastrarCliente').modal('hide');
                    ClienteController.list();
                    ClienteController.limparDadosClienteModal();
                },
                error: function(request,msg,error) {
                    $("#clienteId").val("");
                    $('#cadastrarCliente').modal('hide');
                    ClienteController.list();
                    ClienteController.limparDadosClienteModal();
                }
            });
        }
        else {
            ClienteController.update(idClienteToEdit);
        }

    }

    this.update = function(idCliente) {
        var cliente = this.getDadosClienteModal();

        $.ajax({
            url: '/clientes/'+idCliente,
            method: 'PUT',
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            data: JSON.stringify(cliente),
            success: function(result) {
                $("#clienteId").val("");
                $('#cadastrarCliente').modal('hide');
                ClienteController.list();
                ClienteController.limparDadosClienteModal();
            },
            error: function(request,msg,error) {
                $("#clienteId").val("");
                $('#cadastrarCliente').modal('hide');
                ClienteController.list();
                ClienteController.limparDadosClienteModal();
            }
        });
    }

    this.edit = function(idCliente) {
        $("#clienteId").val(idCliente);

        $.get( "/clientes/" + idCliente, function( data ) {
            $('#cadastrarCliente').modal('show');
            ClienteController.setDadosClienteModal(data);
        });
    }

    this.setDadosClienteModal = function(cliente) {
        $('#clienteNome').val(cliente.nome),
            $('#clienteEmail').val(cliente.email),
            $('#clienteCpf').val(cliente.cpf)
    }

    this.limparDadosClienteModal = function() {
        $('#clienteNome').val(''),
            $('#clienteEmail').val(''),
            $('#clienteCpf').val('')
    }

    this.getDadosClienteModal = function() {
        var cliente = {
            nome: $('#clienteNome').val(),
            cpf: $('#clienteCpf').val(),
            email: $('#clienteEmail').val(),
        }

        return cliente;
    }

}