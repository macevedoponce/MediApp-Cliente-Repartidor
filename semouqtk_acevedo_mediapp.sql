-- phpMyAdmin SQL Dump
-- version 4.9.7
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 05-12-2022 a las 19:30:05
-- Versión del servidor: 10.5.16-MariaDB-cll-lve
-- Versión de PHP: 7.4.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `semouqtk_acevedo_mediapp`
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
(2, 2, 11, 3),
(3, 3, 11, 3),
(4, 4, 1, 2),
(5, 5, 1, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos_entregados`
--

CREATE TABLE `pedidos_entregados` (
  `id` int(11) NOT NULL,
  `id_pedido` int(11) NOT NULL,
  `id_repartidor` int(11) DEFAULT NULL,
  `img_url` text DEFAULT NULL,
  `estado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pedidos_entregados`
--

INSERT INTO `pedidos_entregados` (`id`, `id_pedido`, `id_repartidor`, `img_url`, `estado`) VALUES
(1, 1, 2, 'imagenes/entregas/1.jpg', 2),
(2, 2, NULL, NULL, 0),
(3, 3, NULL, NULL, 0),
(4, 4, NULL, NULL, 0),
(5, 5, NULL, NULL, 0);

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
(20, 2, 'SKU 000020', 'Fórmula Infantil Enfamil Confort Premium - Caja 1.1 KG', 183.9, 100, 'CAJA 1 G', 'imagenes/productos/024373L.jpg', 1, 'Es una Fórmula Clínicamente Comprobada con proteínas pre-digeridas (parcialmente hidrolizadas) y con menos lactosa, lo que facilita la digestión.'),
(23, 3, 'SKU 000030', 'Pañales para Adultos Secos Premium Talla G - Bolsa 21 UN', 64.9, 100, 'BOLSA 21 UN', 'imagenes/productos/026384L.jpg', 1, 'Los pañales para adultos Secos Premium talla G te brindan mayor absorción y comodidad, cuenta con sistema Ultra-Grip con cintas para un mejor ajuste, tiene una zona con gel ultra absorbente para mantenerte seco y cómodo. Su tecnología Total Sec gelatiniza los líquidos reteniéndolos en el interior del pañal, neutraliza los olores.'),
(24, 3, 'SKU 000031', 'Ensure Advance Sabor Vainilla - Lata 850 G', 95.9, 100, 'LATA 850 G', 'imagenes/productos/010808L.jpg', 1, 'Suplemento Alimenticio completo y equilibrado, diseñado para complementar la alimentación de adultos mayores con malnutrición, pérdida de masa y fuerza muscular.'),
(25, 3, 'SKU 000032', 'Pañales Adultos Talla M Secos Premium - Bolsa 21 UN', 60.9, 100, 'BOLSA 21 UN', 'imagenes/productos/026385L.jpg', 1, 'Los pañales para adultos Secos Premium talla G te brindan mayor absorción y comodidad, cuenta con sistema Ultra-Grip con cintas para un mejor ajuste, tiene una zona con gel ultra absorbente para mantenerte seco y cómodo. Su tecnología Total Sec gelatiniza los líquidos reteniéndolos en el interior del pañal, neutraliza los olores.'),
(26, 3, 'SKU 000033', 'Pañales TENA para Adultos Slip Talla L - Bolsa 21 UN', 86.5, 100, 'BOLSA 21 UN', 'imagenes/productos/008845L.jpg', 1, 'Los pañales Tena talla L fueron desarrollados para personas con incontinencia urinaria severa, diseño seguro y con capacidad de movimiento nula. Además posee una cubierta tipo tela, barrera anti fugas doble cinta adhesiva.'),
(27, 3, 'SKU 000034', 'Pañales para Adultos Secos Pants Talla M - Bolsa 10 UN', 26.9, 100, 'BOLSA 10 UN', 'imagenes/productos/026386L.jpg', 1, 'Los pañales Secos Adultos son en forma de ropa interior, su diseño la brinda mayor comodidad y discreción, tiene mejor ajuste gracias a sus ligas que se adaptan perfectamente al cuerpo. Tiene una zona con gel ultra absorbente para mantenerte seco y cómodo. Protección y seguridad a pesar de las distintas causas de incontinencia urinaria, gracias a su tecnología Total Sec gelatiniza los líquidos reteniéndolos en el interior del pañal, neutraliza los olores. Urinaria Incontinencia.\r\n\r\n'),
(28, 4, 'SKU 000041', 'Vitagel Colágeno Hidrolizado Fortiflex Polvo', 119.6, 100, 'FRASCO 450 G', 'imagenes/productos/027697L.jpg', 1, 'Regeneración muscular. Complemento para dieta de deportistas. Disminución de dolor articulares y musculares luego del ejercicio o día de mucha actividad. Ayuda al control de peso, efecto saciante, lo que hace que la gente ingiera menos calorías. Datos importantes: Bajo en sodio.'),
(29, 4, 'SKU 000042', 'Gomitas More Sleep Melatonina', 45.6, 100, 'FRASCO 100 UN', 'imagenes/productos/034422L.jpg', 1, 'Agua, Emulsionantes: Jarabe de maltitol (SIN 965(ii)) y Maltitol (SIN 965(i)), Gelatina, Azúcar, Regulador de la acidez: Ácido cítrico (SIN 330), Saborizante a Cola, Melatonina, Edulcorante: Sucralosa (SIN 955), Sustancia Conservadora: Sorbato de potasio (SIN 202) y Colorante: Caramelo (SIN 150d).'),
(30, 4, 'SKU 000043', 'Magnesol Polvo Efervescente, Naranja', 32.01, 100, 'CAJA 33 UN', 'imagenes/productos/009570L.jpg', 1, 'Cloruro de Magnesio Hexahidratado 1.860g|Carbonato de Magnesio 0.124g|Óxido de Zinc 0.016g'),
(31, 4, 'SKU 000044', 'Magnesol Polvo', 25.08, 100, 'CAJA 33 UN', 'imagenes/productos/406833L.jpg', 1, 'Cloruro de Magnesio Hexahidratado 1.860g|Carbonato de Magnesio 0.124g|Óxido de Zinc 0.016g'),
(32, 5, 'SKU 000051', 'Mascarilla de Tela Universitario Crema - Bolsa 1 UN', 12.6, 100, 'BOLSA 1 UN', 'imagenes/productos/032564L.jpg', 1, 'La mascarilla de tela Unviersitario de Deportes te ayuda con la protección y cuidado contra agentes externos del medioambiente.'),
(33, 5, 'SKU 000052', 'Mascarilla Desechable 3 Pliegues - Caja 50 UN', 45.6, 0, 'CAJA 50 UN', 'imagenes/productos/030310L.jpg', 0, 'La mascarilla desechable de 3 pliegues te ayuda con la protección contra agentes externos y reduce el riesgo de contaminación.'),
(34, 5, 'SKU 000053', 'Minoxidil 5% Solución Cutánea', 43.5, 100, 'FRASCO 60 ML', 'imagenes/productos/522012L.jpg', 1, 'Minoxidil 5g'),
(35, 5, 'SKU 000054', 'Avamys 27.5mcg/Dosis Suspensión en Spray Nasal - Frasco 120 DSS', 54.5, 100, 'FRASCO 120 DSS', 'imagenes/productos/428010L.jpg', 1, 'FUROATO DE FLUTICASONA 27.5mcg'),
(36, 5, 'SKU 000051', 'Mascarilla de Tela Universitario Crema - Bolsa 1 UN', 12.6, 100, 'BOLSA 1 UN', 'imagenes/productos/032564L.jpg', 1, 'La mascarilla de tela Unviersitario de Deportes te ayuda con la protección y cuidado contra agentes externos del medioambiente.'),
(37, 6, 'sku 000061', 'Mascarilla para el Cabello Fructis Fortificante Hair Food de Plátano - Pote 350 ML', 20.5, 100, 'POTE 350 ML', 'imagenes/productos/025141L.jpg', 1, 'Los productos Fructis Hair Food ofrecen máximo cuidado capilar para un super cabello, gracias al poder de las frutas fortificantes y su fórmula libre de parabenos. Fructis brinda una transformación real del cabello y resultados visibles, gracias a sus nutrientes de 98% origen natural.'),
(38, 6, 'SKU 000062', 'Alcohol en Gel Antibacterial Aval Hand Natural - Tubo 120 ML', 2.4, 100, 'TUBO 120 ML', 'imagenes/productos/019142L.jpg', 1, 'Alcohol en Gel antibacterial Aval Natural elimina el 99.99% de bacterias dejando tu piel protegida y con suavidad. Sin necesidad del uso de agua.');

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
(1, '41142091', 'Elizabeth Doris', 'Ponce', 'De la Cruz', '987137124', '2022-08-26', 0, 'dorisponce260872@gmail.com', '$2y$12$FRY6.TKBEaWmTvVN3.1h2.EbBhWWJuSc4wouvorsJufyIEE6y1Q0i', 1, 0, 'https://jrxvaljn.lucusvirtual.es/acevedoMediapp/imagenes/user_default.jpg'),
(2, '73122365', 'Miguel Angel', 'Acevedo', 'ponce', '982126861', '1999-05-30', 1, 'xdmigue222@gmail.com', '$2y$12$MI67Ik5kU5Ac6.IzoSkoUe3IZSH9agBnym4YxhHhZOX9Kud9T8vtG', 2, 0, 'https://jrxvaljn.lucusvirtual.es/acevedoMediapp/imagenes/usuarios/73122365.jpg'),
(3, '70344080', 'nath', 'Gutierrez ', 'cari', '969165939', '1998-04-27', 0, 'nathaly@gmail.com', '$2y$12$FyeUw90yiXS6V77GDtwWM.vkBP0zt4q2Muqz6pnZi.KaU1GH0RzKe', 1, 0, 'https://jrxvaljn.lucusvirtual.es/acevedoMediapp/imagenes/usuarios/70344080.jpg'),
(4, '75186135', 'Juan Eduardo ', 'Carlos ', 'Campos', '914266082', '0000-00-00', 0, 'jecc.2212@gmail.com', '$2y$12$HzClSBv/ltDx8z87YMiyLOs6bIlGJ0.DSYYK8fKjXWc93sdGQfrg2', 1, 0, 'https://jrxvaljn.lucusvirtual.es/acevedoMediapp/imagenes/user_default.jpg'),
(5, '75186135', 'Juan Eduardo ', 'Carlos ', 'Campos', '914266082', '0000-00-00', 0, 'jecc.2212@gmail.com', '$2y$12$LV1if5xsUTPMDVePTinvPuF2VBMmA5.BI2uFk3FJkMC6wP2LNWKwm', 1, 0, 'https://jrxvaljn.lucusvirtual.es/acevedoMediapp/imagenes/user_default.jpg'),
(6, '75186135', 'Juan Eduardo ', 'Carlos ', 'Campos', '914266082', '0000-00-00', 0, 'juan@gmail.com', '$2y$12$7H6gZ0EwH2DSY0R63YycxuKG..2.b2dBobR133JeWksaRTCMwXFKu', 2, 0, 'https://jrxvaljn.lucusvirtual.es/acevedoMediapp/imagenes/user_default.jpg'),
(7, '88888888', 'Juan Eduardo ', 'Carlos', 'Campos', '914266082', '0000-00-00', 0, 'juca@gmail.com', '$2y$12$X8yUxFbKbgs0B09lU8F/1unjwbSnhT2fsslMsDpTQ9DNOrwYh6jvi', 1, 0, 'https://jrxvaljn.lucusvirtual.es/acevedoMediapp/imagenes/user_default.jpg');

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
(1, 1, -75.2111142128706, -12.069090078746669, 'Ponce De la Cruz Elizabeth Doris', 987137124, 5.4, '2022-12-05', '00:40:14'),
(2, 1, -75.21141696721315, -12.070360225689754, 'Ponce De la Cruz Elizabeth Doris', 987137124, 188.7, '2022-12-05', '00:40:54'),
(3, 1, -75.21309938281775, -12.065997308058309, 'Ponce De la Cruz Elizabeth Doris', 987137124, 188.7, '2022-12-05', '00:50:32'),
(4, 3, -75.20866334438324, -12.07120217832119, 'Gutierrez  cari nath', 969165939, 3.6, '2022-12-05', '07:40:05'),
(5, 4, -75.2099571749568, -12.068752705198703, 'Carlos  Campos Juan Eduardo ', 914266082, 3.6, '2022-12-05', '08:33:25');

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
-- Indices de la tabla `pedidos_entregados`
--
ALTER TABLE `pedidos_entregados`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_pedido` (`id_pedido`),
  ADD KEY `id_repartidor` (`id_repartidor`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `pedidos_entregados`
--
ALTER TABLE `pedidos_entregados`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `venta`
--
ALTER TABLE `venta`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

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
-- Filtros para la tabla `pedidos_entregados`
--
ALTER TABLE `pedidos_entregados`
  ADD CONSTRAINT `pedidos_entregados_ibfk_1` FOREIGN KEY (`id_repartidor`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `pedidos_entregados_ibfk_2` FOREIGN KEY (`id_pedido`) REFERENCES `venta` (`id`);

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
