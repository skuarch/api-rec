ALTER TABLE `regaloenclave`.`address` 
CHANGE COLUMN `address_all` `address_all` VARCHAR(512) NOT NULL ;

#mail template
INSERT INTO `regaloenclave`.`mail_template` (`mail_template_display_language`, `mail_template_from`, `mail_template_is_soft_delete`, `mail_template_message`, `mail_template_name`, `mail_template_subject`) VALUES ('English', 'regaloenclave@regaloenclave.com', '0', 'hola', 'affiliate', 'Welcome to regalo en clave');

#configuration
INSERT INTO `regaloenclave`.`configuration` (`configuration_smtp_host`, `configuration_smtp_password`, `configuration_smtp_port`, `configuration_smtp_username`) VALUES ('smtp.gmail.com', 'elpassword', '587', 'regaloenclave@gmail.com');

#transaction type
INSERT INTO `regaloenclave`.`transaction_type` (`transaction_type_name`) VALUES ('new affiliate');
INSERT INTO `regaloenclave`.`transaction_type` (`transaction_type_name`) VALUES ('new freelancer');

#gender
INSERT INTO `regaloenclave`.`gender` (`gender_name`) VALUES ('male');
INSERT INTO `regaloenclave`.`gender` (`gender_name`) VALUES ('female');

#person_type
INSERT INTO `regaloenclave`.`person_type` (`person_type_name`) VALUES ('admin');
INSERT INTO `regaloenclave`.`person_type` (`person_type_name`) VALUES ('affiliate');
INSERT INTO `regaloenclave`.`person_type` (`person_type_name`) VALUES ('freelancer');
INSERT INTO `regaloenclave`.`person_type` (`person_type_name`) VALUES ('cashier');


#mails
INSERT INTO `regaloenclave`.`mail_template` (`mail_template_display_language`, `mail_template_from`, `mail_template_is_soft_delete`, `mail_template_message`, `mail_template_name`, `mail_template_subject`) VALUES ('English', 'regaloenclave@gmail.com', '0', '<div style=\'margin: auto; border: solid 1px #ccc; min-height: 150px; width: 95%; height: 95%; text-align: center\'>\n    <span style=\'font-size: 12px; color: #cacaca; text-align: right; border-bottom: 1px solid #ccc;\'>regala lo que quieras en <a href=\'http://regaloenclave.com\'>regaloenclave.com</a></span><br/>    \n    <h1>Bienvenid(@) a regalo en clave</h1>\n    Proximamente recibiras noticas de como poder ingresar al sistema de regalo en clave\n    para que puedas afiliar negocios<br><br>\n    con esta cuanta de correo podras entrar al sistema de regalo en clave<br><br>\n    usuario: <strong>:email</strong><br>\n    password: <strong>:password</strong><br>\n    clave: <strong>:key</strong><br><br>\n    La clave es importante, es como el sistema te identificara<br>\n    <span class=\"color: #caaaaa\">Conserva este correo en el futuro te sera de utilidad</span>\n    <br><br><br>\n</div>', 'new freelancer', 'bienvenido a regaloenclave.com');
