

CREATE database bdPedidosSegracsa;
USE bdPedidosSegracsa;
CREATE TABLE `categoria` (
  `id_cat` int(11) NOT NULL,
  `codigoCat` varchar(6) NOT NULL,
  `nombreCat` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------



CREATE TABLE `cliente` (
  `codigo_cli` int(11) NOT NULL,
  `nombreCli` varchar(25) NOT NULL,
  `apellidoPCli` varchar(25) NOT NULL,
  `apellidoMCli` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `comprobante` (
  `codigo_com` int(11) NOT NULL,
  `codigo_tipocom` varchar(6) NOT NULL,
  `fechaCom` date NOT NULL,
  `codigo_cli` int(11) NOT NULL,
  `precioTotal` double(5,2) DEFAULT NULL,
  `serieCom` varchar(4) DEFAULT NULL,
  `numeracionCom` varchar(4) DEFAULT NULL,
  `subTotal` decimal(5,2) NOT NULL,
  `igv` decimal(5,2) NOT NULL,
  `codigo_seguridad` char(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------
select*from Usuario;

CREATE TABLE `detallecomp` (
  `id_producto` int(11) NOT NULL,
  `codigo_com` int(11) NOT NULL,
  `cantidadPro` int(11) DEFAULT NULL,
  `subtotal` double(5,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------
select*from Categoria;


CREATE TABLE `inventariodetalle` (
  `idInventario` int(11) NOT NULL,
  `fechaMov` date NOT NULL,
  `id_producto` int(11) NOT NULL,
  `tipoMovimiento` varchar(10) NOT NULL,
  `observacion` varchar(400) NOT NULL,
  `entrada` int(11) NOT NULL DEFAULT 0,
  `salida` int(11) NOT NULL DEFAULT 0,
  `stock` int(11) NOT NULL,
  `precio` decimal(11,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `producto` (
  `id_producto` int(11) NOT NULL,
  `codigoPro` varchar(6) NOT NULL,
  `nombrePro` varchar(70) NOT NULL,
  `precioPro` double(5,2) NOT NULL,
  `id_cat` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `tipocomprobante` (
  `codigo_tipocom` varchar(6) NOT NULL,
  `nombreCom` varchar(30) NOT NULL,
  `valorComp` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `password` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;



INSERT INTO `usuario` (`id_usuario`, `nombre`, `correo`, `password`) VALUES
(0, 'admin', 'admin@gmail.com', '123456');


ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id_cat`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`codigo_cli`);


ALTER TABLE `comprobante`
  ADD PRIMARY KEY (`codigo_com`),
  ADD KEY `fk_clientecomp` (`codigo_cli`),
  ADD KEY `fk_tipocomcomp` (`codigo_tipocom`);


ALTER TABLE `detallecomp`
  ADD PRIMARY KEY (`id_producto`,`codigo_com`),
  ADD KEY `fk_compdetallecomp` (`codigo_com`);


ALTER TABLE `inventariodetalle`
  ADD PRIMARY KEY (`idInventario`),
  ADD KEY `fk_productoinvdet` (`id_producto`);


ALTER TABLE `producto`
  ADD PRIMARY KEY (`id_producto`),
  ADD KEY `fk_categoriaproducto` (`id_cat`);


ALTER TABLE `tipocomprobante`
  ADD PRIMARY KEY (`codigo_tipocom`);

ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `correo` (`correo`);


ALTER TABLE `categoria`
  MODIFY `id_cat` int(11) NOT NULL AUTO_INCREMENT;


ALTER TABLE `cliente`
  MODIFY `codigo_cli` int(11) NOT NULL AUTO_INCREMENT;


ALTER TABLE `comprobante`
  MODIFY `codigo_com` int(11) NOT NULL AUTO_INCREMENT;


ALTER TABLE `inventariodetalle`
  MODIFY `idInventario` int(11) NOT NULL AUTO_INCREMENT;


ALTER TABLE `producto`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT;


ALTER TABLE `comprobante`
  ADD CONSTRAINT `comprobante_ibfk_1` FOREIGN KEY (`codigo_cli`) REFERENCES `cliente` (`codigo_cli`),
  ADD CONSTRAINT `comprobante_ibfk_2` FOREIGN KEY (`codigo_tipocom`) REFERENCES `tipocomprobante` (`codigo_tipocom`);


ALTER TABLE `detallecomp`
  ADD CONSTRAINT `detallecomp_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`),
  ADD CONSTRAINT `detallecomp_ibfk_2` FOREIGN KEY (`codigo_com`) REFERENCES `comprobante` (`codigo_com`);


ALTER TABLE `inventariodetalle`
  ADD CONSTRAINT `inventariodetalle_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`);


ALTER TABLE `producto`
  ADD CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`id_cat`) REFERENCES `categoria` (`id_cat`);
COMMIT;

INSERT INTO tipocomprobante (codigo_tipocom, nombreCom, valorComp) VALUES
('BOLETA', 'Boleta de venta', 'BB01'),
('FACTUR', 'Factura de venta', 'FF01');