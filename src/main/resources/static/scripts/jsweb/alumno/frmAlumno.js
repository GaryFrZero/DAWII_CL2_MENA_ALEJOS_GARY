$(document).on("click", "#btnagregar", function(){
    $("#txtnombre").val("");
    $("#txtapellido").val("");
    $("#txtlocales").val("");
    $("#hddidalumno").val("0");
    $("#cboespecialidad").empty();
    $.ajax({
        type: "GET",
        url: "/Especialidad/listarEspecialidades",
        dataType: "json",
        success: function(resultado){
            $.each(resultado, function(index, value){
                $("#cboespecialidad").append(
                    `<option value="${value.idesp}">${value.nomesp}</option>`
                )
            })
        }
    })
    $("#modalNuevo").modal("show");
});

$(document).on("click", ".btnactualizar", function(){
    $("#txtnombre").val($(this).attr("data-nomalumno"));
    $("#txtapellido").val($(this).attr("data-apealumno"));
    $("#txtlocales").val($(this).attr("data-locales"));
    $("#hddidalumno").val($(this).attr("data-idalumno"));
    $("#cboespecialidad").empty();
    var idespecialidad = $(this).attr("data-idesp");
    $.ajax({
        type: "GET",
        url: "/Especialidad/listarEspecialidades",
        dataType: "json",
        success: function(resultado){
            $.each(resultado, function(index, value){
                $("#cboespecialidad").append(
                    `<option value="${value.idesp}">${value.nomesp}</option>`
                )
            })
            $("#cboespecialidad").val(idespecialidad);
        }
    })
    $("#modalNuevo").modal("show");
});


$(document).on("click", "#btnguardar", function(){
    $.ajax({
        type: "POST",
        url: "/Alumno/registrarAlumno",
        contentType: "application/json",
        data: JSON.stringify({
            idalumno: $("#hddidalumno").val(),
            nomalumno: $("#txtnombre").val(),
            apealumno: $("#txtapellido").val(),
            locales: $("#txtlocales").val(),
            idesp: $("#cboespecialidad").val()
        }),
        success: function(resultado){
            if(resultado.respuesta){
                ListarAlumno();
            }
            alert(resultado.mensaje);
        }
    });
    $("#modalNuevo").modal("hide");
});

$(document).on("click", ".btneliminar", function(){
    var idAlumno = $(this).attr("data-idalumno");
    $.ajax({
        type: "DELETE",
        url: "/Alumno/eliminarAlumno",
        contentType: "application/json",
        data: JSON.stringify({
            idalumno: idAlumno
        }),
        success: function(resultado){
            if(resultado.respuesta){
                ListarAlumno();
            }
            alert(resultado.mensaje);
        }
    });
});

function ListarAlumno(){
    $.ajax({
        type: "GET",
        url: "/Alumno/listarAlumnos",
        dataType: "json",
        success: function(resultado){
            $("#tblalumno > tbody").html("");
            $.each(resultado, function(index, value){
                $("#tblalumno > tbody").append("<tr>"+
                    "<td>"+value.idalumno+"</td>"+
                    "<td>"+value.nomalumno+"</td>"+
                    "<td>"+value.apealumno+"</td>"+
                    "<td>"+value.especialidad.idesp+"</td>"+
                    "<td>"+value.locales+"</td>"+
                    "<td>"+
                        "<button type='button' class='btn btn-info btnactualizar'"+
                                     "data-IdAlumno='"+value.idalumno+"'"+
                                     "data-NomAlumno='"+value.nomalumno+"'"+
                                     "data-ApeAlumno='"+value.apealumno+"'"+
                                     "data-Locales='"+value.locales+"'"+
                                     "data-IdEsp='"+value.idesp+"'>Actualizar</button>"+
                    "</td>"+
                    "<td>"+
                        "<button type='button' class='btn btn-danger btneliminar'"+
                                     "data-IdAlumno='"+value.idalumno+"'>Eliminar</button>"+
                    "</td></tr>");
            })
        }
    })
}


