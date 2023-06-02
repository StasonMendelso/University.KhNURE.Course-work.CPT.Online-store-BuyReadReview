-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: course_work
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (1,'Іван','Багряний',NULL),(2,'Іван ','Франко',NULL);
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `author_book`
--

LOCK TABLES `author_book` WRITE;
/*!40000 ALTER TABLE `author_book` DISABLE KEYS */;
INSERT INTO `author_book` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `author_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (304,'Тигролови','Іван Багряний (1906—1963; справжнє прізвище — Лозов’ягін) — український письменник, який зазнав жорстокості сталінських репресій. Митця було ув’язнено, заслано до Далекого Сходу, потім була еміграція. Лише після своєї смерті він був реабілітований, став лауреатом Шевченківської премії 1992 року, і його книги почали перевидавати в Україні. Герой роману «Тигролови», молодий український інженер-авіатор Григорій Многогрішний, катований, голодний, засуджений на 25 років ув’язнення, втік по дорозі до концтабору прямо посеред суворої сибірської тайги. Майор НКВС Медвин — «професійний тигролов» — розпочинає полювання на втікача…','978-966-03-9620-3',0,195.00000000,20,'UKR000000000104582',1,1),(248,'Захар Беркут: образ громадського життя Карпатської Русі в XIII віці: історична повість','«Захар Беркут» — найвідоміший твір І. Франка на історичну тему про боротьбу давніх карпатських общин проти нашестя монголів та соціального гноблення. Повість було написано на конкурс, оголошений журналом «Зоря», та вперше видано 1883 р. в цьому ж журналі. На основі повісті знято однойменний фільм.','978-966-10-4756-2',0,129.00000000,10,'UKR000000000012101',2,2);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `book_file`
--

LOCK TABLES `book_file` WRITE;
/*!40000 ALTER TABLE `book_file` DISABLE KEYS */;
INSERT INTO `book_file` VALUES (1,1),(2,2);
/*!40000 ALTER TABLE `book_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `book_genre`
--

LOCK TABLES `book_genre` WRITE;
/*!40000 ALTER TABLE `book_genre` DISABLE KEYS */;
INSERT INTO `book_genre` VALUES (1,1),(1,2);
/*!40000 ALTER TABLE `book_genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `book_review`
--

LOCK TABLES `book_review` WRITE;
/*!40000 ALTER TABLE `book_review` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `book_review_characteristic`
--

LOCK TABLES `book_review_characteristic` WRITE;
/*!40000 ALTER TABLE `book_review_characteristic` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_review_characteristic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `book_review_comment`
--

LOCK TABLES `book_review_comment` WRITE;
/*!40000 ALTER TABLE `book_review_comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_review_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `book_review_draft`
--

LOCK TABLES `book_review_draft` WRITE;
/*!40000 ALTER TABLE `book_review_draft` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_review_draft` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `book_review_edited`
--

LOCK TABLES `book_review_edited` WRITE;
/*!40000 ALTER TABLE `book_review_edited` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_review_edited` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `book_review_final`
--

LOCK TABLES `book_review_final` WRITE;
/*!40000 ALTER TABLE `book_review_final` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_review_final` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `book_review_request_for_checking`
--

LOCK TABLES `book_review_request_for_checking` WRITE;
/*!40000 ALTER TABLE `book_review_request_for_checking` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_review_request_for_checking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `book_review_request_for_checking_review_requirement`
--

LOCK TABLES `book_review_request_for_checking_review_requirement` WRITE;
/*!40000 ALTER TABLE `book_review_request_for_checking_review_requirement` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_review_request_for_checking_review_requirement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `book_review_request_for_checking_status`
--

LOCK TABLES `book_review_request_for_checking_status` WRITE;
/*!40000 ALTER TABLE `book_review_request_for_checking_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_review_request_for_checking_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `book_review_request_for_publication`
--

LOCK TABLES `book_review_request_for_publication` WRITE;
/*!40000 ALTER TABLE `book_review_request_for_publication` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_review_request_for_publication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `book_review_request_for_publication_cancel`
--

LOCK TABLES `book_review_request_for_publication_cancel` WRITE;
/*!40000 ALTER TABLE `book_review_request_for_publication_cancel` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_review_request_for_publication_cancel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `book_review_request_for_publication_characteristic`
--

LOCK TABLES `book_review_request_for_publication_characteristic` WRITE;
/*!40000 ALTER TABLE `book_review_request_for_publication_characteristic` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_review_request_for_publication_characteristic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `book_review_request_for_publication_criterion_score`
--

LOCK TABLES `book_review_request_for_publication_criterion_score` WRITE;
/*!40000 ALTER TABLE `book_review_request_for_publication_criterion_score` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_review_request_for_publication_criterion_score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `book_review_request_for_publication_status`
--

LOCK TABLES `book_review_request_for_publication_status` WRITE;
/*!40000 ALTER TABLE `book_review_request_for_publication_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_review_request_for_publication_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `book_review_requirement`
--

LOCK TABLES `book_review_requirement` WRITE;
/*!40000 ALTER TABLE `book_review_requirement` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_review_requirement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `book_review_score_criterion`
--

LOCK TABLES `book_review_score_criterion` WRITE;
/*!40000 ALTER TABLE `book_review_score_criterion` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_review_score_criterion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `book_review_tag`
--

LOCK TABLES `book_review_tag` WRITE;
/*!40000 ALTER TABLE `book_review_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_review_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `book_review_tag_category`
--

LOCK TABLES `book_review_tag_category` WRITE;
/*!40000 ALTER TABLE `book_review_tag_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_review_tag_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `book_review_with_tag`
--

LOCK TABLES `book_review_with_tag` WRITE;
/*!40000 ALTER TABLE `book_review_with_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `book_review_with_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `buyer_contact`
--

LOCK TABLES `buyer_contact` WRITE;
/*!40000 ALTER TABLE `buyer_contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `buyer_contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `card_payment`
--

LOCK TABLES `card_payment` WRITE;
/*!40000 ALTER TABLE `card_payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `card_payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `card_returned_funds_payment`
--

LOCK TABLES `card_returned_funds_payment` WRITE;
/*!40000 ALTER TABLE `card_returned_funds_payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `card_returned_funds_payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `cart_item`
--

LOCK TABLES `cart_item` WRITE;
/*!40000 ALTER TABLE `cart_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `cash_on_delivery_payment`
--

LOCK TABLES `cash_on_delivery_payment` WRITE;
/*!40000 ALTER TABLE `cash_on_delivery_payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `cash_on_delivery_payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `cash_payment`
--

LOCK TABLES `cash_payment` WRITE;
/*!40000 ALTER TABLE `cash_payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `cash_payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `cash_returned_funds_payment`
--

LOCK TABLES `cash_returned_funds_payment` WRITE;
/*!40000 ALTER TABLE `cash_returned_funds_payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `cash_returned_funds_payment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `client_note_for_wishes`
--

LOCK TABLES `client_note_for_wishes` WRITE;
/*!40000 ALTER TABLE `client_note_for_wishes` DISABLE KEYS */;
/*!40000 ALTER TABLE `client_note_for_wishes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `courier_delivery`
--

LOCK TABLES `courier_delivery` WRITE;
/*!40000 ALTER TABLE `courier_delivery` DISABLE KEYS */;
/*!40000 ALTER TABLE `courier_delivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `delivery`
--

LOCK TABLES `delivery` WRITE;
/*!40000 ALTER TABLE `delivery` DISABLE KEYS */;
/*!40000 ALTER TABLE `delivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `delivery_info`
--

LOCK TABLES `delivery_info` WRITE;
/*!40000 ALTER TABLE `delivery_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `delivery_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `delivery_item`
--

LOCK TABLES `delivery_item` WRITE;
/*!40000 ALTER TABLE `delivery_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `delivery_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `delivery_method`
--

LOCK TABLES `delivery_method` WRITE;
/*!40000 ALTER TABLE `delivery_method` DISABLE KEYS */;
/*!40000 ALTER TABLE `delivery_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `delivery_request`
--

LOCK TABLES `delivery_request` WRITE;
/*!40000 ALTER TABLE `delivery_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `delivery_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `delivery_request_status`
--

LOCK TABLES `delivery_request_status` WRITE;
/*!40000 ALTER TABLE `delivery_request_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `delivery_request_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `delivery_status`
--

LOCK TABLES `delivery_status` WRITE;
/*!40000 ALTER TABLE `delivery_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `delivery_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `deliveryman_contact`
--

LOCK TABLES `deliveryman_contact` WRITE;
/*!40000 ALTER TABLE `deliveryman_contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `deliveryman_contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `edit_review_permission`
--

LOCK TABLES `edit_review_permission` WRITE;
/*!40000 ALTER TABLE `edit_review_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `edit_review_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `file`
--

LOCK TABLES `file` WRITE;
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
INSERT INTO `file` VALUES (0,'image_not_found.png','png','book-directory/image_not_found.png'),(1,'1_1.jpg','jpg','book-directory/1_1.jpg'),(2,'2_1.jpg','jpg','book-directory/2_1.jpg');
/*!40000 ALTER TABLE `file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES (1,'Класична українська література'),(2,'Проза');
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `nova_poshta_delivery`
--

LOCK TABLES `nova_poshta_delivery` WRITE;
/*!40000 ALTER TABLE `nova_poshta_delivery` DISABLE KEYS */;
/*!40000 ALTER TABLE `nova_poshta_delivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `nova_poshta_delivery_status`
--

LOCK TABLES `nova_poshta_delivery_status` WRITE;
/*!40000 ALTER TABLE `nova_poshta_delivery_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `nova_poshta_delivery_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `order_item`
--

LOCK TABLES `order_item` WRITE;
/*!40000 ALTER TABLE `order_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `order_status`
--

LOCK TABLES `order_status` WRITE;
/*!40000 ALTER TABLE `order_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `payment_info`
--

LOCK TABLES `payment_info` WRITE;
/*!40000 ALTER TABLE `payment_info` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `payment_method`
--

LOCK TABLES `payment_method` WRITE;
/*!40000 ALTER TABLE `payment_method` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `payment_status`
--

LOCK TABLES `payment_status` WRITE;
/*!40000 ALTER TABLE `payment_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `publisher`
--

LOCK TABLES `publisher` WRITE;
/*!40000 ALTER TABLE `publisher` DISABLE KEYS */;
INSERT INTO `publisher` VALUES (1,'Фоліо'),(2,'Навчальна книга Богдан');
/*!40000 ALTER TABLE `publisher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `receiver_contact`
--

LOCK TABLES `receiver_contact` WRITE;
/*!40000 ALTER TABLE `receiver_contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `receiver_contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `returned_funds`
--

LOCK TABLES `returned_funds` WRITE;
/*!40000 ALTER TABLE `returned_funds` DISABLE KEYS */;
/*!40000 ALTER TABLE `returned_funds` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `returned_funds_method`
--

LOCK TABLES `returned_funds_method` WRITE;
/*!40000 ALTER TABLE `returned_funds_method` DISABLE KEYS */;
/*!40000 ALTER TABLE `returned_funds_method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `returned_funds_status`
--

LOCK TABLES `returned_funds_status` WRITE;
/*!40000 ALTER TABLE `returned_funds_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `returned_funds_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `shop_delivery`
--

LOCK TABLES `shop_delivery` WRITE;
/*!40000 ALTER TABLE `shop_delivery` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_delivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `shop_delivery_status`
--

LOCK TABLES `shop_delivery_status` WRITE;
/*!40000 ALTER TABLE `shop_delivery_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_delivery_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-02 20:38:22
