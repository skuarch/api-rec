#ALTER TABLE `regaloenclave`.`address` CHANGE COLUMN `address_all` `address_all` VARCHAR(4096) NOT NULL ;

#mail template
INSERT INTO `regaloenclave`.`mail_template` (`mail_template_display_language`, `mail_template_from`, `mail_template_is_soft_deleted`, `mail_template_message`, `mail_template_name`, `mail_template_subject`) VALUES ('English', 'regaloenclave@regaloenclave.com', '0', 'hola', 'affiliate', 'Welcome to regalo en clave');
INSERT INTO `regaloenclave`.`mail_template` (`mail_template_display_language`, `mail_template_from`, `mail_template_is_soft_deleted`, `mail_template_message`, `mail_template_name`, `mail_template_subject`) VALUES ('English', 'regaloenclave@gmail.com', '0', '<div style=\'margin: auto; border: solid 1px #ccc; min-height: 150px; width: 95%; height: 95%; text-align: center\'>\n    <span style=\'font-size: 12px; color: #cacaca; text-align: right; border-bottom: 1px solid #ccc;\'>regala lo que quieras en <a href=\'http://regaloenclave.com\'>regaloenclave.com</a></span><br/>    \n    <h1>Bienvenid(@) a regalo en clave</h1>\n    Proximamente recibiras noticas de como poder ingresar al sistema de regalo en clave\n    para que puedas afiliar negocios<br><br>\n    con esta cuanta de correo podras entrar al sistema de regalo en clave<br><br>\n    usuario: <strong>:email</strong><br>\n    password: <strong>:password</strong><br>\n    clave: <strong>:key</strong><br><br>\n    La clave es importante, es como el sistema te identificara<br>\n    <span class=\"color: #caaaaa\">Conserva este correo en el futuro te sera de utilidad</span>\n    <br><br><br>\n</div>', 'new freelancer', 'bienvenido a regaloenclave.com');


#configuration
INSERT INTO `regaloenclave`.`configuration` (`configuration_smtp_host`, `configuration_smtp_password`, `configuration_smtp_port`, `configuration_smtp_username`) VALUES ('smtp.gmail.com', 'elpassword', '587', 'regaloenclave@gmail.com');

#transaction type
INSERT INTO `regaloenclave`.`transaction_type` (`transaction_type_name`) VALUES ('new affiliate');
INSERT INTO `regaloenclave`.`transaction_type` (`transaction_type_name`) VALUES ('new freelancer');
INSERT INTO `regaloenclave`.`transaction_type` (`transaction_type_name`) VALUES ('new establishment');

#gender
INSERT INTO `regaloenclave`.`gender` (`gender_name`) VALUES ('male');
INSERT INTO `regaloenclave`.`gender` (`gender_name`) VALUES ('female');

#person_type
INSERT INTO `regaloenclave`.`person_type` (`person_type_name`) VALUES ('admin');
INSERT INTO `regaloenclave`.`person_type` (`person_type_name`) VALUES ('affiliate');
INSERT INTO `regaloenclave`.`person_type` (`person_type_name`) VALUES ('freelancer');
INSERT INTO `regaloenclave`.`person_type` (`person_type_name`) VALUES ('cashier');
INSERT INTO `regaloenclave`.`person_type` (`person_type_name`) VALUES ('responsable');
INSERT INTO `regaloenclave`.`person_type` (`person_type_name`) VALUES ('contact');
INSERT INTO `regaloenclave`.`person_type` (`person_type_name`) VALUES ('contactCompany');

#category
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('arte y cultura');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('bares y antros');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('bellaza y cosmetica');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('casinos y lugares de apuestas');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('departamentales');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('deportes y espacimiento');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('electronica y linea blanca');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('Informatica y telefonia');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('joyeria y accesorios');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('moda y calzado');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('muebles y decoracion');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('regalos y juguetes');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('restaurantes y cafeterias');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('salud');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('servicios');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('viajes y hoteles');


#affiliate type
INSERT INTO `regaloenclave`.`affiliate_type` (`affiliate_type_name`) VALUES ('person');
INSERT INTO `regaloenclave`.`affiliate_type` (`affiliate_type_name`) VALUES ('company');


