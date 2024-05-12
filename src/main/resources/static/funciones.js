function borrarP(id) {
    swal({
        title: "¿Está seguro de eliminar?",
        text: "Una vez eliminado, no podrá recuperar el registro.",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
            .then((OK) => {
                if (OK) {
                    $.ajax({
                        url: "/borrarP/" + id,
                        success: function (res) {
                            console.log(res);
                            swal("¡Los datos del Producto han sido eliminados!", {
                                icon: "success",
                            }).then((OK) => {
                                if (OK) {
                                    location.href = "/listarP";
                                }
                            });
                        },
                        error: function (xhr, status, error) {
                            console.error(xhr.responseText);
                            swal("¡No se puedo borrar Producto,, contiene un inventario!", {
                                icon: "error",
                            });
                        }
                    });
                } else {
                    swal("¡Los datos ingresados están a salvo!");
                }
            });
}