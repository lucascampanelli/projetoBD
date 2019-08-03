-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 11-Set-2018 às 13:36
-- Versão do servidor: 5.7.17
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

create database lojainfo;
--
-- Database: `lojainfo`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_clientes`
--
use lojainfo;
CREATE TABLE `tb_clientes` (
  `id_cliente` int(11) NOT NULL,
  `nome` varchar(50) NOT NULL,
  `endereco` varchar(100) DEFAULT NULL,
  `fone` varchar(15) DEFAULT NULL,
  `email` varchar(70) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tb_clientes`
--

INSERT INTO `tb_clientes` (`id_cliente`, `nome`, `endereco`, `fone`, `email`) VALUES
(1, 'Jeferson Roberto de Lima', 'Av. Aguia de Haia', '1120454000', 'jefersonrl@icloud.com');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_hardware`
--

CREATE TABLE `tb_hardware` (
  `id_hardware` int(11) NOT NULL,
  `descricao` varchar(50) NOT NULL,
  `preco_unit` decimal(10,0) DEFAULT NULL,
  `qtde_atual` int(11) DEFAULT NULL,
  `qtde_minima` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_vendas`
--

CREATE TABLE `tb_vendas` (
  `id_venda` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `data` date NOT NULL,
  `vlr_total` decimal(8,2) NOT NULL,
  `desconto` decimal(8,2) DEFAULT NULL,
  `vlr_pago` decimal(8,2) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_vendas_itens`
--

CREATE TABLE `tb_vendas_itens` (
  `id_item` int(11) NOT NULL,
  `id_venda` int(11) NOT NULL,
  `id_hardware` int(11) NOT NULL,
  `qtde_item` int(11) NOT NULL,
  `total_item` decimal(8,2) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_clientes`
--
ALTER TABLE `tb_clientes`
  ADD PRIMARY KEY (`id_cliente`);

--
-- Indexes for table `tb_hardware`
--
ALTER TABLE `tb_hardware`
  ADD PRIMARY KEY (`id_hardware`);

--
-- Indexes for table `tb_vendas`
--
ALTER TABLE `tb_vendas`
  ADD PRIMARY KEY (`id_venda`),
  ADD KEY `fk_vda_cli` (`id_cliente`);

--
-- Indexes for table `tb_vendas_itens`
--
ALTER TABLE `tb_vendas_itens`
  ADD PRIMARY KEY (`id_item`),
  ADD KEY `fk_itens_vda` (`id_venda`),
  ADD KEY `fk_itens_hardware` (`id_hardware`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_clientes`
--
ALTER TABLE `tb_clientes`
  MODIFY `id_cliente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `tb_hardware`
--
ALTER TABLE `tb_hardware`
  MODIFY `id_hardware` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tb_vendas`
--
ALTER TABLE `tb_vendas`
  MODIFY `id_venda` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `tb_vendas_itens`
--
ALTER TABLE `tb_vendas_itens`
  MODIFY `id_item` int(11) NOT NULL AUTO_INCREMENT;COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
