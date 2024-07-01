-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Lip 01, 2024 at 09:03 PM
-- Wersja serwera: 10.4.32-MariaDB
-- Wersja PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pojazdy`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `motor`
--

CREATE TABLE `motor` (
  `numer_vin` bigint(20) NOT NULL,
  `pojemnosc_silnika` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `motor`
--

INSERT INTO `motor` (`numer_vin`, `pojemnosc_silnika`) VALUES
(18273645901827365, 0.998),
(36481927364509182, 0.599),
(51729384612093847, 1.103);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `osobowy`
--

CREATE TABLE `osobowy` (
  `numer_vin` bigint(20) NOT NULL,
  `liczba_miejsc` int(11) NOT NULL,
  `klimatyzacja` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `osobowy`
--

INSERT INTO `osobowy` (`numer_vin`, `liczba_miejsc`, `klimatyzacja`) VALUES
(49381627451928375, 5, 1),
(49381627451928376, 5, 1),
(72164830927134582, 5, 0);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `pojazdy`
--

CREATE TABLE `pojazdy` (
  `numer_vin` bigint(17) NOT NULL,
  `marka` varchar(50) NOT NULL,
  `model` varchar(50) NOT NULL,
  `typ` enum('Osobowy','Motor') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pojazdy`
--

INSERT INTO `pojazdy` (`numer_vin`, `marka`, `model`, `typ`) VALUES
(18273645901827365, 'Yamaha', 'YZF-R1', 'Motor'),
(36481927364509182, 'Honda', 'CBR600RR', 'Motor'),
(49381627451928375, 'Toyota', 'Corolla', 'Osobowy'),
(49381627451928376, 'Honda', 'Civic', 'Osobowy'),
(51729384612093847, 'Ducati', 'Panigale V4', 'Motor'),
(72164830927134582, 'Ford', 'Focus', 'Osobowy');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `userName` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `account` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `userName`, `password`, `account`) VALUES
(1, 'admin', 'qwerty123', 'ADMINISTRATOR');

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `motor`
--
ALTER TABLE `motor`
  ADD PRIMARY KEY (`numer_vin`);

--
-- Indeksy dla tabeli `osobowy`
--
ALTER TABLE `osobowy`
  ADD PRIMARY KEY (`numer_vin`);

--
-- Indeksy dla tabeli `pojazdy`
--
ALTER TABLE `pojazdy`
  ADD PRIMARY KEY (`numer_vin`);

--
-- Indeksy dla tabeli `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pojazdy`
--
ALTER TABLE `pojazdy`
  MODIFY `numer_vin` bigint(17) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=98765432198765434;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `motor`
--
ALTER TABLE `motor`
  ADD CONSTRAINT `motor_ibfk_1` FOREIGN KEY (`numer_vin`) REFERENCES `pojazdy` (`numer_vin`);

--
-- Constraints for table `osobowy`
--
ALTER TABLE `osobowy`
  ADD CONSTRAINT `osobowy_ibfk_1` FOREIGN KEY (`numer_vin`) REFERENCES `pojazdy` (`numer_vin`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
