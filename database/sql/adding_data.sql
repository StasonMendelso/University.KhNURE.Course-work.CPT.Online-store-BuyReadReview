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
INSERT INTO `author` VALUES (1,'Іван','Багряний',NULL),(2,'Іван ','Франко',NULL),(3,'Діна','Бухольц',NULL),(4,'Анатолій','Дімаров',NULL),(5,'Микола','Хвильовий',NULL),(6,'Василь','Крас',NULL),(7,'Ольга','Саліпа',NULL),(8,'Кузьма','Скрябін',NULL),(9,'Євген','Шишащьцикй',NULL),(10,'Ольга','Кобилянська',NULL),(11,'Пантелеймон','Куліш',NULL),(12,'Іван','Багряний',NULL),(13,'Іван','Котляревський',NULL),(14,'Григорій','Сковорода',NULL),(15,'Микола','Гоголь',NULL),(16,'Михайло','Коцюбинський',NULL),(17,'Володимир','Винниченко',NULL),(18,'Дара','Корній',NULL);
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `author_book`
--

LOCK TABLES `author_book` WRITE;
/*!40000 ALTER TABLE `author_book` DISABLE KEYS */;
INSERT INTO `author_book` VALUES (1,1),(13,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,8),(10,9),(11,10),(12,11),(14,13),(15,14),(16,16),(17,16),(18,16),(19,16),(20,17),(21,17),(22,17),(23,17),(24,18),(25,18),(26,18),(27,18),(28,18);
/*!40000 ALTER TABLE `author_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (304,'Тигролови','Іван Багряний (1906—1963; справжнє прізвище — Лозов’ягін) — український письменник, який зазнав жорстокості сталінських репресій. Митця було ув’язнено, заслано до Далекого Сходу, потім була еміграція. Лише після своєї смерті він був реабілітований, став лауреатом Шевченківської премії 1992 року, і його книги почали перевидавати в Україні. Герой роману «Тигролови», молодий український інженер-авіатор Григорій Многогрішний, катований, голодний, засуджений на 25 років ув’язнення, втік по дорозі до концтабору прямо посеред суворої сибірської тайги. Майор НКВС Медвин — «професійний тигролов» — розпочинає полювання на втікача…','978-966-03-9620-3',0,195.00000000,20,'UKR000000000104582',1,1),(248,'Захар Беркут: образ громадського життя Карпатської Русі в XIII віці: історична повість','«Захар Беркут» — найвідоміший твір І. Франка на історичну тему про боротьбу давніх карпатських общин проти нашестя монголів та соціального гноблення. Повість було написано на конкурс, оголошений журналом «Зоря», та вперше видано 1883 р. в цьому ж журналі. На основі повісті знято однойменний фільм.','978-966-10-4756-2',0,129.00000000,10,'UKR000000000012101',2,2),(240,'Неофіційна кулінарна книга Гаррі Поттера','Прихильники книг про Гаррі Поттера тепер зможуть потрапити у казковий світ, де живуть маги й чарівники. Для цього потрібно докласти трошечки зусиль, використати дещицю продуктів і обрати рецепт із «Неофіційної кулінарної книги Гаррі Поттера». Смакуючи гарбузовим печивом, пончиками з джемом, солодкою помадкою або чимось іншим, ви зможете на деякий час стати одним із героїв захопливих історій про хлопчика-чарівника. Подарункове видання, оформлене під старовину,  містить докладні рецепти, зібрані в десяти розділах. Завдяки точним інструкціям ви легко зможете приготувати будь-яку страву.','978-617-548-061-8',0,600.00000000,4,'UKR000000000104734',3,5),(976,'І будуть люди','Пророчі слова великого Тараса, що «на оновленій землі врага не буде, супостата, а буде син, і буде мати, і будуть люди на землі» стали непохитним переконанням і Анатолія Дімарова: останній рядок цього твердження він узяв за назву одного з кращих своїх творінь. Хоч у ньому йдеться про найтрагічніші сторінки життя українського селянства періоду колективізації-Голодомору, хоч до нашої Незалежності ще залишалися довгі десятиліття. Річ у тім, що беручись за написання  роману, а це початок 1960-х, письменник уже позбувся облуди радянщини. Зате вона цупко трималася за свої наративи — третину твору цензура скоротила. Нині ця правдива історія започаткувала нову сторінку у своєму життєписі: видавництво «Фоліо» перевидає її без купюр, а недавня 12-серійна екранізація роману здобула визнання як перша в країні історична родинна сага.','978-966-03-9593-0',0,720.00000000,9,'UKR000000000027412',4,1),(384,'Я (Романтика)','До видання увійшли вибрані твори українського прозаїка, поета, публіциста, одного з основоположників пореволюційної прози Миколи Хвильового (1893–1933), справжнє ім’я якого — Микола Фітільов. Письменник показав вадикомуністичного суспільства, правдиво зобразив «юних фанатиків комуни», «вірних псів революції», «липових комуністів».','978-966-10-5989-3',0,249.00000000,5,'UKR000000000105894',5,2),(608,'Руїна','«Руїна» розповідає про події 1658 року. Громадянська війна в Україні, початок Руїни. Головні герої роману, побратими українець Максим Перепечай та поляк Янек Сулковський, проходять нелегкий, сповнений пригод шлях , який приводить одного до Блискучої Порти, а згодом — і до чорноморських піратів, а другого у стан болгарських гайдуків , перш, ніж вони знову зустрінуться у горах Старої Планини. Запорожець Тарас Завірюха зі побратимами-козаками очолює курінь особистої охорони гетьмана Івана Виговського. Їм протистоять агенти Таємного приказу і зрадники-лиходії, які допомагають Пушкарю і намагаються зірвати домовленості між Гетьманатом і Річчю Посполитою.','978-966-03-9381-3',0,195.00000000,30,'UKR000000000027237',6,1),(224,'Оля','Поки одні співчувають її самотності, інші на пальцях перераховують її чоловіків… З дитинства Ольга розуміє, що вона «не така». Її сила лякає і притягує, її почуття і пристрасть горять вогнем. Але вона сама — згоряє чи гартується? Ось він, ідеал: розумний, інтелігентний Маковей. Негідник чи рятівник? Злочинець чи жертва? Чи витримає кохання сильної жінки? Бо ж власні «скелети в шафі» не дають про себе забути. На шляху їхнього спільного щастя багато тіней з минулого. Кобилянська. Про неї знають все, але її не знає ніхто. Щоденники, листи, твори... Найбільша містифікація української літератури, створена нею самою. Роман «Оля», володар Гран-прі «Коронації слова-2020», не про письменницю, а про жінку. Пристрасну, вперту, але з власними комплексами та помилками, живу... Це дебютний роман Ольги Саліпи, що відома як поетка і авторка малої прози.','978-966-03-9413-1',0,190.00000000,10,'UKR000000000026233',7,1),(160,'Я, \"Побєда\" і Берлін','Ця книжка — літературний дебют відомого українського співака Андрія Кузьменка (Кузьми), лідера групи «Скрябін», який загинув в автомобільній аварії 2 лютого 2015 року. До збірки увійшли дві повісті, а також тексти відомих пісень групи. Перша повість — «Я, “Побєда” і Берлін» — проникнута яскравим почуттям гумору, веселим настроєм і, напевне, розсмішить навіть дуже серйозних читачів. Події відбуваються навколо старого і пошарпаного автомобіля марки «Побєда», на якому Андрій зі своїм другом Бардом подорожують до столиці Німеччини. Друга повість — «Місто, в якому не ходять гроші» — зовсім інша за жанром. Це невесела історія про дівчину Алісу, яка через збіг обставин опинилася в жахливому місті, де панують людиноненависництво, нечувана жорстокість і нелюдські порядки, — місті, з якого немає дороги назад…','978-617-551-030-8',0,145.00000000,14,'UKR000000000104738',8,1),(224,'Я, Паштєт і Армія','Кузьма Скрябін (справжнє ім\'я Андрій Кузьменко) народився в місті Самбір Львівської області 17 серпня 1968 року. Закінчив музичну школу за класом фортепіано. У 1989 році записав першу пісню у складі музичної групи \"Скрябін\". З того часу став відомим як співак, автор музики і текстів багатьох десятків пісень, а також ще й як талановитий письменник. 2 лютого 2015 року Кузьма Скрябін загинув в автомобільній катастрофі. Ця книжка - виключно чоловіча п\'єса з використанням характерної військової риторики, написана відомим українським співаком Андрієм Кузьменком (Кузьмою), лідером групи \"Скрябін\". Книга \"Я, Паштєт і Армія\" - розповідь про армійського друга Кузьми Паштєта (Сергія Павлова), який потрапив в армію через проблеми з деканом в університеті, де Сергій був кандидатом біологічних наук. Книга проникнута армійськими буднями та гумором, який підійме настрій кожному.','978-966-03-7199-6',0,140.00000000,7,'UKR000000000004593',9,1),(224,'Мандрівка до потойбіччя. Маріуполь','Євген Шишацький — редактор, журналіст. Народився і виріс у місті Маріуполь. З 2014 року живе й працює в Києві. Як і в кожного українця, 24 лютого 2022 року світ Євгена перевернувся. У Маріуполі під обстрілами були мама і друзі, з якими обірвався зв’язок. Євген приймає рішення їхати на окупований Схід, щоб допомогти евакуювати людей. «Мандрівка до потойбіччя. Маріуполь» — сповідь водія-волонтера, що дивом вибрався з-під Маріуполя. Автор описує реальну історію про подорож до «краю застиглого часу», розповідає про людей, яких життєвий вибір і доля занесли саме сюди, про свій вибір, «от такий, недодуманий, але свідомий. У пошуках своїх. У пошуках себе. З фаталістичною готовністю до нового досвіду».','978-617-551-117-6',0,190.00000000,3,'UKR000000000104744',10,1),(352,'Земля','Ольга Кобилянська (1863—1942) — видатна письменниця-демократка, класик української літератури. Її творча спадщина велика й різноманітна. Повість «Земля» — вершина реалізму письменниці. Цей твір, завдяки своїй великій життєвій правді й високій художності, став одним з найвідоміших в українській літературі. Усім своїм єством пов’язаний із землею герой повісті Івоніка Федорчук. Він шанує й береже землю, тяжко працює на ній усе своє життя, плекаючи надію, що вона принесе щастя його синам. Але боротьба за землю, яка роками точилася в його родині, зруйнувала душі рідних людей, призвела до трагедії, до братовбивства. Із втратою сина втрачає Івоніка свої мрії та сподівання, свою віру в землю.','978-966-03-9506-0',0,245.00000000,2,'UKR000000000100036',11,1),(192,'Чорна рада. Хроніка 1663 року','Пантелеймон Куліш (1819-1897) — визначний український письменник, просвітитель і гуманіст, один із фундаторів української національної свідомості і нової літератури. Письменницький талант Куліша виявився у різних художніх жанрах: він писав прозу, вірші, поеми, драми, багато перекладав. В історію української літератури увійшов насамперед як автор першого історичного роману «Чорна рада. Хроніка 1663 року», в якому відтворив події 1663 року, коли після смерті Богдана Хмельницького почалася запекла боротьба за гетьманську булаву.','978-966-03-9899-3',0,165.00000000,7,'UKR000000000105528',12,1),(304,'Тигролови','Іван Багряний (1906—1963; справжнє прізвище — Лозов’ягін) — український письменник, який зазнав жорстокості сталінських репресій. Митця було ув’язнено, заслано до Далекого Сходу, потім була еміграція. Лише після своєї смерті він був реабілітований, став лауреатом Шевченківської премії 1992 року, і його книги почали перевидавати в Україні. Герой роману «Тигролови», молодий український інженер-авіатор Григорій Многогрішний, катований, голодний, засуджений на 25 років ув’язнення, втік по дорозі до концтабору прямо посеред суворої сибірської тайги. Майор НКВС Медвин — «професійний тигролов» — розпочинає полювання на втікача…','978-617-551-212-8',0,260.00000000,13,'UKR000000000105855',13,1),(352,'Енеїда','Ім’я Івана Петровича Котляревського (1769-1838), засновника нової української літератури, пов’язують, у першу чергу, з його поемою «Енеїда» (1798), яка вже понад двісті років зачаровує читачів. Це була перша друкована українська книга, написана народною мовою, перша національна поема. Запозичивши сюжет античної поеми Вергілія, Котляревський наповнив цей твір дотепним українським гумором, повір’ями, звичаями, картинами воєнного і мирного життя, описом одягу, страв — усе це надає поемі яскравого національного колориту. «Енеїду» сміливо можна назвати своєрідною енциклопедією українського побуту кінця XVIII — початку XIX століття.','978-617-551-040-7',0,230.00000000,30,'UKR000000000105856',14,1),(352,'Літературні твори','Унікальність видання в тому, що тут чи не вперше зібрано в одній книзі художні твори видатного філософа в повному їхньому обсязі з додачею вибраних упорядником з різних текстів (і художніх, і філософських) афоризмів, які сягають вершин афористики. Книга містить ґрунтовну розвідку В. Шевчука про письменницьку спадщину Г. Сковороди та біографію, написану учнем філософа М. Ковалинським. Всі тексти подаємо в перекладі сучасною літературною мовою. Система ілюстрацій відбиває образотворче бачення світу XVIII століття.','978-617-629-723-9',0,380.00000000,6,'UKR000000000102104',15,3),(244,'Тіні забутих предків','До збірки увійшли сім творів класика української літератури, неперевершеного майстра слова Михайла Коцюбинського: \"Хо\", \"Дорогою ціною\", \"Відьма\", \"Тіні забутих предків\", \"Нюренберзьке яйце\", \"Для загального добра\", \"Коні не винні\". Невеличкі за обсягом оповіді відтворюють поетичний світ давніх гуцульських традицій, колоритно описують надзвичайну природу Карпат, міфологічне світосприйняття українського народу та органічну єдність з природою. Нікого не залишать байдужим яскраві образи, захопливі сюжети та сильні і нестримні почуття, які створюють особливе емоційне напруження, що надовго залишає вийнятковий післясмак.','978-617-629-504-4',0,180.00000000,2,'UKR000000000103077',16,3),(96,'Тіні забутих предків. Intermezzo','До видання увійшли одні з найкращих творів талановитого українського письменника, чудового новеліста, послідовника І. Франка, майстра слова Михайла Коцюбинського — повість «Тіні забутих предків» та новела «Intermezzo». Невеликі за обсягом, проте психологічно напружені твори, в яких письменник зобразив живі й правдиві образи, належать до найвищих досягнень української класичної прози.','978-966-10-5116-3',0,79.00000000,8,'UKR000000000022319',17,2),(96,'Тіні забутих предків. Intermezzo','До видання увійшли одні з найкращих творів талановитого українського письменника, чудового новеліста, послідовника І. Франка, майстра слова Михайла Коцюбинського — повість «Тіні забутих предків» та новела «Intermezzo». Невеликі за обсягом, проте психологічно напружені твори, в яких письменник зобразив живі й правдиві образи, належать до найвищих досягнень української класичної прози.','978-966-10-5123-1',0,119.00000000,16,'UKR000000000022314',18,2),(336,'Тіні забутих предків','Михайло Коцюбинський — самобутній письменник, що збагатив світову літературу проникливими творами. Він відкрив у вітчизняній літературі сторінку модерну та імпресіонізму. Ця книга, одна з найкращих в усій українській літературі, розповідає про кохання гуцульських Ромео та Джульєтти і приголомшує драматизмом подій та доль.','978-617-12-6303-1',0,200.00000000,3,'UKR000000000021981',19,4),(96,'Федько-халамидник. Оповідання','Збірка містить оповідання «Федько-халамидник», «Кумедія з Костем», «Салдатики!» та «Момент». Твори Володимира Винниченка сповнені драматизму. Автор правдиво зображає трагічні людські долі, детально змальовує психологію дітей і дорослих, майстерно показує тонку межу між щастям та горем. Ці оповідання — взірці української малої прози першої половини ХХ століття, що досі зворушують серця читачів і читачок.','978-617-548-088-5',0,90.00000000,23,'UKR000000000104373',20,5),(640,'Щось більше за нас','Український державний діяч, прозаїк, драматург і художник Володимир Винниченко (1880–1951) дебютував у літературі на початку ХХ ст. Був змушений покинути Україну, якийсь час проживав у Австрії, Німеччині, Швейцарії, Італії, Франції. За кордоном співпрацював із низкою українських видань, політична кар\'єра митця тривала і в еміграції. Сюжети новел та оповідань, що увійшли до видання, здебільшого відбивають соціальні струси початку ХХ ст. Наростання невдоволення селян, які готові піти проти пана, змальовується в оповіданні «Суд». У творі «Салдатики!» розкрита психологія лідерства — з непримітного селянина народжується ватажок. Невідповідність між оболонкою і суттю робить кумедним в очах оточення героя твору «Народний діяч». У оповіданнях «Біля машини», «На пристані», «Раб краси» відображена вперше в українській літературі революційна інтелігенція.','978-966-03-8416-3',0,294.00000000,4,'UKR000000000023148',21,1),(328,'Записки кирпатого Мефістофеля','Володимир Винниченко — бунтар української літератури початку ХХ століття. Він спровокував чимало інтелектуальних конфліктів, та водночас побудував неймовірну літературну й політичну кар’єру. Винниченко став популярним драматургом, автором романів, статей, мемуарів, а в 1917 році очолив Генеральний Секретаріат Центральної Ради. «Записки Кирпатого Мефістофеля» — роман морального експерименту, у якому чи не вперше в українській літературі відверто аналізуються стосунки між чоловіком і жінкою. Герой, який хоче позбутися власної дитини, бо не бажає бути батьком. Жінка, яка відмовляється робити аборт. Небажана дитина, що зрештою стає психологічним прихистком для головного героя. Автор тестує своє кредо «роби, що хочеш, коли тебе не мучать докори сумління» та шукає межі особистої свободи у стосунках. Про серію «Неканонічний канон» Міркуючи про канон української літератури, в пам’яті спливають лише кілька прізвищ зі шкільної програми — Шевченко, Франко, Нечуй-Левицький. Хоча насправді цей перелік значно більший та різноманітніший. Перед вами серія «Неканонічний канон», за допомогою якої ми хочемо поговорити про всіх тих, кого ми не знали, чиї тексти ми читали, не розуміючи контексту тогочасної реальності. Перед вами серія, покликана перевідкрити знайомих незнайомців. У ній ви знайдете цілий спектр українських авторів та їхніх творів — від Підмогильного і Багряного до Хвильового та Йогансена, від вишуканого інтелектуального роману до динамічного пригодницького, від новаторської урбаністичної прози до психологічних текстів. Кожен текст супроводжується ключами для прочитання від українських літературознавців. Вони розкажуть, на що варто звернути увагу, і допоможуть подивитися на тексти українських класиків по-новому.','978-617-7960-94-1',0,249.00000000,6,'UKR000000000105611',22,6),(190,'Федько-халамидник. Момент. Вибрані твори','Кожне з наведених у пропонованій збірці ранніх оповідань Володимира Винниченка (1880—1951) — це зворушлива і яскрава розповідь про драматичну долю людини. Письменник із симпатією змальовує і простих “маленьких” людей, які є носіями найкращих начал народного буття (“Федько-халамидник”, “Раб краси”), і людей освічених, романтичних і водночас рішучих у відстоюванні високих ідеалів (“Момент”, “Зіна”). Разом з тим він далекий від сентиментального замилування і часом безжально сатиричний у зображенні рідного українства, проявів самозакоханості чи пристосуванства (“Уміркований” та “щирий”). Ці оповідання належать до найкращої частини творчої спадщини письменника та зберігають свою художню цінність і сьогодні.','978-617-07-0738-3',0,132.00000000,9,'UKR000000000023691',23,7),(352,'Чарівні істоти українського міфу. Домашні духи','Книжка «Чарівні істоти українського міфу: домашні духи» поведе вас у дивовижний і самобутній світ українських вірувань. Гортаючи її сторінки, ви дізнаєтесь про поважного домовика, надійного захисника роду, познайоми-_x000D_ тесь із добрим покутнім, який нашепче вам своє благословення, зазирнете на гостину до справедливого хлівника, який доброму господареві в усьому сприяє, а ледацюг карає. А якщо поталанить, привабите духа щастя з удатною_x000D_ сприйєю, які підуть пліч-о-пліч з вами. Однак стережіться роздоріжжя, бо там духи-перевертні заманюють у потойбіччя стомлених мандрівників._x000D_ Перед вами постане величний шар культурної спадщини давніх українців,химерний світ чарівних істот праукраїнської міфології, досліджений і опрацьований Дарою Корній.','978-966-982-012-9',0,480.00000000,14,'UKR000000000020724',24,8),(336,'Чарівні істоти українського міфу. Шкідники життя','У правіки сягає корінням віра в те, що і в небі, і не землі порядкують надприродні сили й повно всякого дива. І не завжди та сила добра й прихильна до людей. Дара Корній поведе вас у самобутній і моторошний світ, у якому уява наших пращурів пояснювала всі негаразди й болячки втручанням «нечистої сили». Ви помандруєте українськими теренами й дізнаєтесь, що це нічниці крадуть у дітей сон, злидні та хвацько однооке відбирають у родини достаток, а літавиця й перелесник — душевний спокій; пересвідчитесь, що сестри-лихоманки приносять хвороби й моровиці, насилають голод, а лиха удільниця може наперекір долі позбавити людину життя. Перед вами постане величезний шар культурної спадщини давніх українців, який може видатися надто похмурим. Разом із тим він повчальний, адже покликаний не налякати, а застерегти людину від необдуманих учинків, лихих намірів, підступу та зради.Тож цікавої мандрівки примарним світом праукраїнського міфу!','978-966-982-118-8',0,460.00000000,6,'UKR000000000024719',25,8),(240,'Пригоди Змія Багатоголового. Діти Сонцівни й молодильні яблука','Скелястий Світ Вічного Літа населяють мудрі змії та дракони, які подолали заздрість і живуть у гармонії та злагоді, кожен має своє призначення й шанує закон. Однак несподівано цей чарівний Світ спіткало лихо: у Райському саду захворіли жар-птиці й вертоград із молодильними яблуками занедужав. Про це дізнаються друзі — юні Мотузочка й трьохголовий змій, якого всі називають Халепником за бешкетний норов. І строката компанія рушає у світ людей, щоб дізнатися правду й завадити непоправній катастрофі. Читачі не тільки зануряться у плетиво незвичайних пригод, а й навчаться цінувати дружбу й дослухатися до свого серця. І ще переконаються в істинності заповідей Скелястого Світу, особливо в тому, що любов і повага до матінки Землі — понад усе.','978-966-982-186-7',0,320.00000000,9,'UKR000000000025239',26,8),(40,'Бузинова сопілка діда Всевіда','Ця добра казочка про всесильного діда Всевіда, який живе у старому Пралісі, мешкає у бузиновій хатинці, грає на чарівній бузиновій сопілці та товаришує з сестрами Весною, Осінню, Зимою та Літом. Одного сніжного зимового дня хтось поцупив дідову бузинову сопілку і у Пралісі все переплуталося — суниці посеред стужі, журавлині ключі у зимовому небі, розквітлі конвалії на галявині, де ще мить тому лежав сніг... До всього, у пісочному годиннику світу час зупинився. Як це виправити? Хто він — той загадковий крадій? І для чого йому чарівна сопілка діда Всевіда?','978-617-629-530-3',0,190.00000000,2,'UKR000000000100235',27,3),(40,'Особливий','Навіть якщо ти один з тисячі, копія своїх братів-сестер, бо народився кленовим листочком, і тобі від народження пророкують сумне та нецікаве життя, це не привід журитися. Щойно ти повіриш у власну унікальність, як усе зміниться. Ця історія про віру в себе, яка слабких робить сильними, зневірених перетворює на героїв. І звичайні стають Особливими.','978-617-7429-37-0',0,120.00000000,3,'UKR000000000027445',28,3);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `book_file`
--

LOCK TABLES `book_file` WRITE;
/*!40000 ALTER TABLE `book_file` DISABLE KEYS */;
INSERT INTO `book_file` VALUES (1,1),(2,2),(3,3),(3,4),(4,5),(5,6),(6,7),(7,8),(8,9),(9,10),(10,11),(11,12),(12,13),(13,14),(14,15),(15,16),(16,17),(17,18),(18,19),(19,20),(20,21),(20,22),(20,23),(21,24),(22,25),(23,26),(23,27),(24,28),(25,29),(26,30),(27,31),(28,32);
/*!40000 ALTER TABLE `book_file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `book_genre`
--

LOCK TABLES `book_genre` WRITE;
/*!40000 ALTER TABLE `book_genre` DISABLE KEYS */;
INSERT INTO `book_genre` VALUES (1,1),(5,1),(11,1),(12,1),(13,1),(15,1),(16,1),(19,1),(22,1),(23,1),(1,2),(2,2),(4,3),(7,3),(8,3),(9,3),(10,3),(6,4),(14,5),(17,6),(18,7),(20,8),(21,9),(24,10),(25,11),(26,11),(27,11),(28,11),(3,12);
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
INSERT INTO `file` VALUES (0,'image_not_found.png','png','book-directory/image_not_found.png'),(1,'1_1.jpg','jpg','book-directory/1_1.jpg'),(2,'2_1.jpg','jpg','book-directory/2_1.jpg'),(3,'3_1.jpg','jpg','book-directory/3_1.jpg'),(4,'3_2.jpg','jpg','book-directory/3_2.jpg'),(5,'4_1.jpg','jpg','book-directory/4_1.jpg'),(6,'5_1.jpg','jpg','book-directory/5_1.jpg'),(7,'6_1.jpg','jpg','book-directory/6_1.jpg'),(8,'7_1.jpg','jpg','book-directory/7_1.jpg'),(9,'8_1.jpg','jpg','book-directory/8_1.jpg'),(10,'9_1.jpg','jpg','book-directory/9_1.jpg'),(11,'10_1.jpg','jpg','book-directory/10_1.jpg'),(12,'11_1.jpg','jpg','book-directory/11_1.jpg'),(13,'12_1.jpg','jpg','book-directory/12_1.jpg'),(14,'13_1.jpg','jpg','book-directory/13_1.jpg'),(15,'14_1.jpg','jpg','book-directory/14_1.jpg'),(16,'15_1.jpg','jpg','book-directory/15_1.jpg'),(17,'16_1.jpg','jpg','book-directory/16_1.jpg'),(18,'17_1.jpg','jpg','book-directory/17_1.jpg'),(19,'18_1.jpg','jpg','book-directory/18_1.jpg'),(20,'19_1.jpg','jpg','book-directory/19_1.jpg'),(21,'20_1.jpg','jpg','book-directory/20_1.jpg'),(22,'20_2.jpg','jpg','book-directory/20_2.jpg'),(23,'20_3.jpg','jpg','book-directory/20_3.jpg'),(24,'21_1.jpg','jpg','book-directory/21_1.jpg'),(25,'22_1.jpg','jpg','book-directory/22_1.jpg'),(26,'23_1.jpg','jpg','book-directory/23_1.jpg'),(27,'23_2.jpg','jpg','book-directory/23_2.jpg'),(28,'24_1.jpg','jpg','book-directory/24_1.jpg'),(29,'25_1.jpg','jpg','book-directory/25_1.jpg'),(30,'26_1.jpg','jpg','book-directory/26_1.jpg'),(31,'27_1.jpg','jpg','book-directory/27_1.jpg'),(32,'28_1.jpg','jpg','book-directory/28_1.jpg');
/*!40000 ALTER TABLE `file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES (4,'Історичний роман'),(8,'Дитяча проза'),(11,'Казки для дітей'),(5,'Класична поезія'),(9,'Класична проза'),(1,'Класична українська література'),(6,'Книги для дітей українською мовою'),(12,'Книги з кулінарії'),(10,'Науково популярна література'),(2,'Проза'),(7,'Романи для підлітків'),(3,'Сучасна проза');
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
INSERT INTO `publisher` VALUES (1,'Фоліо'),(2,'Навчальна книга Богдан'),(3,'Апріорі'),(4,'Клуб Сімейного Дозвілля'),(5,'BookChef'),(6,'Віхола'),(7,'Знання'),(8,'Vivat, Pelican'),(9,'Видавництво Старого Лева'),(10,'Діалектика');
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

-- Dump completed on 2023-06-03  0:22:32
