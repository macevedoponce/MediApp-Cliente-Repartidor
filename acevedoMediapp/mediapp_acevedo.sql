-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-11-2022 a las 19:07:40
-- Versión del servidor: 10.4.25-MariaDB
-- Versión de PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `mediapp_acevedo`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `estado` int(11) NOT NULL,
  `imagen_url` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`id`, `nombre`, `estado`, `imagen_url`) VALUES
(1, 'Farmacia', 1, 'imagenes/farmacia.png'),
(2, 'Mamá y Bebé', 1, 'imagenes/mama_bebe.png'),
(3, 'Adulto Mayor', 1, 'imagenes/adull_mayor.png'),
(4, 'Nutricion para Todos', 1, 'imagenes/nutri_para_todos.png'),
(5, 'Salud', 1, 'imagenes/salud.png'),
(6, 'Cuidado Personal', 1, 'imagenes/cuid_perso.png'),
(7, 'Nutricion deportiva', 1, 'imagenes/nutri_depor.png'),
(8, 'Dispositivos Médicos', 1, 'imagenes/dispo_medic.png'),
(9, 'Productos Naturales', 1, 'imagenes/prod_natural.png'),
(10, 'Mundo Infantil', 1, 'imagenes/mundo_infantil.png');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `det_venta`
--

CREATE TABLE `det_venta` (
  `id` int(11) NOT NULL,
  `id_pedido` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `det_venta`
--

INSERT INTO `det_venta` (`id`, `id_pedido`, `id_producto`, `cantidad`) VALUES
(1, 1, 1, 3),
(2, 2, 11, 2),
(3, 3, 2, 3),
(4, 4, 19, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id` int(11) NOT NULL,
  `id_categoria` int(11) NOT NULL,
  `codigo` varchar(50) NOT NULL,
  `nombre` text NOT NULL,
  `previo_venta` double NOT NULL,
  `stock` int(11) NOT NULL,
  `presentacion` varchar(50) NOT NULL,
  `ruta_imagen` text NOT NULL,
  `estado` int(11) NOT NULL,
  `descripcion` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id`, `id_categoria`, `codigo`, `nombre`, `previo_venta`, `stock`, `presentacion`, `ruta_imagen`, `estado`, `descripcion`) VALUES
(1, 1, 'SKU 000001', 'Alcohol Medicinal 70% - Frasco 120 ML', 1.8, 100, 'FRASCO 120 ML', 'imagenes/productos/029832L.jpg', 1, 'El alcohol medicinal 70% es antiséptico ayuda a eliminar gran cantidad de bacterias cuando es aplicado sobre la piel, superficies u objetos inertes.'),
(2, 1, 'SKU 000002', 'Panadol Antigripal NF Tableta', 2.06, 100, 'SOBRE X2 TABS 1 UN', 'imagenes/productos/025891L.jpg', 1, 'Paracetamol 500mg+Clorhidrato de fenilefrina 5mg+Maleato de clorfenamina 2mg'),
(3, 1, 'SKU 000003', 'Alcohol Medicinal 70% con Atomizador', 7.9, 100, 'FRASCO 500 ML', 'imagenes/productos/034472L.jpg', 1, 'Antiséptico diseñado a base de alcohol y agua purificada, que actúa sobre manos y piel, los cuales cumplen la acción antiséptica y desinfectante, liquido libre de partículas en suspensión, libre de partículas extrañas, de aspecto limpio y traslucido.'),
(4, 1, 'SKU 000004', 'Panbio COVID-19 Antigen Self-Test', 24.5, 100, 'CAJA 1 UN', 'imagenes/productos/036697L.jpg', 1, 'Panbio™ COVID-19 Antigen Self-Test es un inmunoensayo rápido de lectura visual in vitro (fuera del cuerpo) de un solo uso que utiliza una muestra de hisopado nasal humano para la detección cualitativa del antígeno (Ag) proteico de la nucleocápside del SARS-CoV-2'),
(5, 1, 'SKU 000005', 'Vitapyrena Forte Antigripal Sabor Miel y Limón - Caja 50 UN', 2.21, 100, 'GRANULADO 1 UN', 'imagenes/productos/327091L.jpg', 1, 'Vick VitaPyrena Forte alivia los síntomas asociados al resfriado como la congestión nasal, el dolor de cabeza, la fiebre, dolor de garganta.'),
(6, 1, 'SKU 000006', 'Vick Vaporub Ungüento tópico', 2.48, 100, 'UNIDAD 1 UN', 'imagenes/productos/324071L.jpg', 1, 'Vick VapoRub es un ungüento que combina mentol, eucalipto y alcanfor en un ungüento, que ayuda a descongestionar las vías respiratorias, calmar la tos y alivio temporal de dolores musculares menores.'),
(7, 1, 'SKU 000007', 'Abrilar Jarabe', 19.3, 100, 'FRASCO 100 ML', 'imagenes/productos/932007L.jpg', 1, 'Extracto seco de hojas de hiedra (Hedera helix) 0,7g'),
(8, 1, 'SKU 000008', 'Vick 44 Jarabe', 15.1, 100, 'FRASCO 120 ML', 'imagenes/productos/264050L.jpg', 1, 'Vick 44 es un jarabe que alivia la tos seca y con flema, su efecto es antitusígeno y expectorante. No genera somnolencia.'),
(9, 1, 'SKU 000009', 'Crema Regeneradora Bepanthen', 56.6, 100, 'TUBO 100 G', 'imagenes/productos/010197L.jpg', 1, 'Crema regeneradora para la piel, gracias a sus ingredientes activos: provitamina B5, lanolina y potasio.'),
(10, 1, 'SKU 000010', 'Apronax 275mg Tableta Recubierta', 8.9, 100, 'CAJA 8 UN', 'imagenes/productos/025764L.jpg', 1, 'Naproxeno sódico 275mg'),
(11, 2, 'SKU 000011', 'Pañales Huggies XXG Bigpack Natural Care - Bolsa 58 UN', 62.9, 100, 'BOLSA 58 UN', 'imagenes/productos/036337L.jpg', 1, 'Los pañales Huggies cuentan con la tecnología Xtra-Care con canales suaves y súper respirables. Un cuidado superior para la delicada piel de tu bebé, con fibras naturales suaves como el algodón. Dermatológicamente comprobado.'),
(12, 2, 'SKU 000012', 'Pañales Huggies XG Bigpack Natural Care - Bolsa 60 UN', 62.9, 100, 'BOLSA 60 UN', 'imagenes/productos/036336L.jpg', 1, 'Los pañales Huggies cuentan con la tecnología Xtra-Care con canales suaves y súper respirables. Un cuidado superior para la delicada piel de tu bebé, con fibras naturales suaves como el algodón. Dermatológicamente comprobado.'),
(13, 2, 'SKU 000013', 'Pañales Huggies XXG Bigpack Active Sec - Bolsa 62 UN', 62.9, 100, 'BOLSA 62 UN', 'imagenes/productos/036340L.jpg', 1, 'Los pañales Huggies cuentan con la tecnología Xtra-Care con canales suaves y súper respirables. Un cuidado superior para la delicada piel de tu bebé, con fibras naturales suaves como el algodón. Dermatológicamente comprobado.'),
(14, 2, 'SKU 000014', 'Pañales Ninet Talla XL - Bolsa 48 UN', 46.9, 100, 'BOLSA 48 UN', 'imagenes/productos/036784L.jpg', 1, 'Los pañales Huggies cuentan con la tecnología Xtra-Care con canales suaves y súper respirables. Un cuidado superior para la delicada piel de tu bebé, con fibras naturales suaves como el algodón. Dermatológicamente comprobado.'),
(15, 2, 'SKU 000015', 'Pañales Babysec Super Premium XXG - Paquete 68 UN', 61.6, 100, 'BOLSA 68 UN', 'imagenes/productos/034973L.jpg', 1, 'Pañal Babysec Super Premium con extra suavidad en las cubiertas y con hasta 12 horas de absorción. Cintas elastizadas, centro super absorvente y cintura super alta.'),
(16, 2, 'SKU 000016', 'Similac 3 Pro Sensitive - Lata 850 G', 65, 100, 'LATA 850 G', 'imagenes/productos/036337L.jpg', 1, 'La fórmula infantil Similac 3 Pro Sensitive para niños en crecimiento científicamente diseñada para brindar la mejor tolerancia gracias a su mezcla única de grasas, libre de palma oleína con prebióticos y probióticos.'),
(17, 2, 'SKU 000017', 'Pañal Ninet Bebe Talla XXL - Bolsa 44 UN', 46.9, 100, 'BOLSA 44 UN', 'imagenes/productos/024270L.jpg', 1, 'Ninet pañales brindan comodidad toda la noche. 12 horas de protección. Absorbe rápidamente y mantiene seca la piel de tu bebé.'),
(18, 2, 'SKU 000018', 'Pediasure Triplesure Sabor Vainilla - Lata 900 G', 97.5, 100, 'LATA 900 G', 'imagenes/productos/023808L.jpg', 1, 'Alimentación balanceada para complementar las necesidades de alimentación de niños de 2 a 10 años.'),
(19, 2, 'SKU 000019', 'Babylac Pro 3 Neuro Advance - Lata 1800 G', 126.9, 100, 'LATA 1800 G', 'imagenes/productos/024414L.jpg', 1, 'La fórmula Babylac 3 es una mezcla láctea compuesta con 41 nutrientes, vitaminas y minerales que ayudan a promover el desarrollo integral: DHA, ARA, Fosfolípidos y Colina que promueven el aprendizaje.'),
(20, 2, 'SKU 000020', 'Fórmula Infantil Enfamil Confort Premium - Caja 1.1 KG', 183.9, 100, 'CAJA 1 G', 'imagenes/productos/024373L.jpg', 1, 'Es una Fórmula Clínicamente Comprobada con proteínas pre-digeridas (parcialmente hidrolizadas) y con menos lactosa, lo que facilita la digestión.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `descripcion` text NOT NULL,
  `estado` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`id`, `nombre`, `descripcion`, `estado`) VALUES
(1, 'comprador', 'Usuario final que hace uso del aplicativo movil para hacer compras', 1),
(2, 'repartidor', 'usuario final que hace uso de la aplicación de repartidores', 1),
(3, 'prueba', 'prueba', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `documento` varchar(50) NOT NULL,
  `nombres` varchar(50) NOT NULL,
  `apPaterno` varchar(50) NOT NULL,
  `apMaterno` varchar(50) NOT NULL,
  `celular` varchar(9) NOT NULL,
  `f_nacimiento` date NOT NULL,
  `genero` tinyint(1) NOT NULL,
  `correo` varchar(255) NOT NULL,
  `password` text NOT NULL,
  `tipo_user` int(11) NOT NULL,
  `tipo_documento` int(11) NOT NULL,
  `img_url` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id`, `documento`, `nombres`, `apPaterno`, `apMaterno`, `celular`, `f_nacimiento`, `genero`, `correo`, `password`, `tipo_user`, `tipo_documento`, `img_url`) VALUES
(1, '41142091', 'Elizabeth Doris', 'Ponce', 'De la Cruz', '987137124', '1972-11-21', 0, 'dorisponce260872@gmail.com', '$2y$12$7FOP88oTPQEOXGMewR8Xw.LZOBL5e6Dj9dq6nEfun2pjUUJVjcFmq', 1, 0, 'https://w0.peakpx.com/wallpaper/92/942/HD-wallpaper-mujer-actriz-antiguo-chica-colombia-famosa-joker-modelo-starwars.jpg'),
(2, '7645578', 'Mauricio ', 'navarro', 'perez ', '968575445', '2003-06-17', 1, 'repartidor@rep.com', '$2y$12$pHoL/SfvHfQJ9CoatEla.O11NANiCl0dc80OSb7CA5lsYgIDFDR0.', 2, 0, ''),
(3, '76458545', 'pedro', 'Suazo ', 'Marticorena ', '954784565', '1976-03-30', 1, 'cliente@gmail.com', '$2y$12$AOcf5QRHWlybCjXSWZG71u4TwHc.Rwj4l1uVe9z8AubhGgY87y1L2', 1, 0, 'https://img.olympicchannel.com/images/image/private/f_auto/t_1-1_300/primary/wfrhxc0kh2vvq77sonki');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

CREATE TABLE `venta` (
  `id` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `longitud` double NOT NULL,
  `latitud` double NOT NULL,
  `persona_recepcion` varchar(100) NOT NULL,
  `celular` int(9) NOT NULL,
  `total` double NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `venta`
--

INSERT INTO `venta` (`id`, `id_cliente`, `longitud`, `latitud`, `persona_recepcion`, `celular`, `total`, `fecha`, `hora`) VALUES
(1, 3, -75.20333882421255, -12.059827725582743, 'Suazo  Marticorena  pedro', 954784565, 5.4, '2022-11-24', '07:14:13'),
(2, 1, -75.24285413324833, -12.081144101077742, 'Ponce De la Cruz Elizabeth Doris', 987137124, 125.8, '2022-11-24', '07:14:41'),
(3, 1, -75.21038465201855, -12.068762869033861, 'Ponce De la Cruz Elizabeth Doris', 987137124, 6.18, '2022-11-25', '07:55:58'),
(4, 1, -75.20807392895222, -12.063756969633676, 'Ponce De la Cruz Elizabeth Doris', 987137124, 253.8, '2022-11-25', '07:57:12');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `det_venta`
--
ALTER TABLE `det_venta`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_pedido` (`id_pedido`),
  ADD KEY `id_producto` (`id_producto`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_categoria` (`id_categoria`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `venta`
--
ALTER TABLE `venta`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_cliente` (`id_cliente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `det_venta`
--
ALTER TABLE `det_venta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `venta`
--
ALTER TABLE `venta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `det_venta`
--
ALTER TABLE `det_venta`
  ADD CONSTRAINT `det_venta_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `det_venta_ibfk_2` FOREIGN KEY (`id_pedido`) REFERENCES `venta` (`id`);

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`id_categoria`) REFERENCES `categorias` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `venta`
--
ALTER TABLE `venta`
  ADD CONSTRAINT `venta_ibfk_2` FOREIGN KEY (`id_cliente`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
