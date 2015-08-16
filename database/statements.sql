#gender----------------------------------------------------------------------------------------------
INSERT INTO `regaloenclave`.`gender` (`gender_name`) VALUES ('male');
INSERT INTO `regaloenclave`.`gender` (`gender_name`) VALUES ('female');






#person_type-----------------------------------------------------------------------------------------
INSERT INTO `regaloenclave`.`person_type` (`person_type_name`) VALUES ('admin');
INSERT INTO `regaloenclave`.`person_type` (`person_type_name`) VALUES ('affiliate');
INSERT INTO `regaloenclave`.`person_type` (`person_type_name`) VALUES ('freelancer');
INSERT INTO `regaloenclave`.`person_type` (`person_type_name`) VALUES ('cashier');
INSERT INTO `regaloenclave`.`person_type` (`person_type_name`) VALUES ('responsable');
INSERT INTO `regaloenclave`.`person_type` (`person_type_name`) VALUES ('contact company');
INSERT INTO `regaloenclave`.`person_type` (`person_type_name`) VALUES ('contact billing');
INSERT INTO `regaloenclave`.`person_type` (`person_type_name`) VALUES ('partner');







#administrator of system------------------------------------------------------------------------------
INSERT INTO `regaloenclave`.`person` (`person_email`, `person_is_soft_deleted`, `person_last_name`, `person_name`, `person_phone`, `person_registration_date`, `gender_id`, `person_type_id`) VALUES ('jeam@regaloenclave.com', '0', 'aparicio', 'jaime', '3338008573', '1996-01-01 00:00:00', '1', '1');
INSERT INTO `regaloenclave`.`administrator` (`administrator_password`, `administrator_active`, `person_id`) VALUES ('6c69740ae68270e6c3e5250d0b7eed00', 1,1);







#mail template----------------------------------------------------------------------------------------
INSERT INTO `mail_template` VALUES (1,'English','no-responder@regaloenclave.com',0,'<div style=\'margin: auto; border: solid 1px #ccc; min-height: 150px; width: 95%; height: 95%; text-align: center\'>\n            <span style=\'font-size: 12px; color: #cacaca; text-align: right; border-bottom: 1px solid #ccc;\'>regala lo que quieras en <a href=\'http://regaloenclave.com\'>regaloenclave.com</a></span><br/>    \n            <h1>Bienvenid(@) <strong>:name</strong> a regalo en clave</h1>\n            Para nosotros es muy grato que te unas a los afiliados de regaloenclave.com\n            <br><br><br>\n        </div>','new affiliate','bienvenido afiliado a regaloenclave.com'),(2,'Spanish','no-responder@regaloenclave.com',0,'<div style=\'margin: auto; border: solid 1px #ccc; min-height: 150px; width: 95%; height: 95%; text-align: center\'>\n            <span style=\'font-size: 12px; color: #cacaca; text-align: right; border-bottom: 1px solid #ccc;\'>regala lo que quieras en <a href=\'http://regaloenclave.com\'>regaloenclave.com</a></span><br/>    \n            <h1>Bienvenid(@) <strong>:name</strong> a regalo en clave</h1>\n            Para nosotros es muy grato que te unas a los afiliados de regaloenclave.com\n            <br><br><br>\n        </div>','new affiliate','bienvenido afiliado a regaloenclave.com'),(3,'English','no-responder@regaloenclave.com',0,'<div style=\'margin: auto; border: solid 1px #ccc; min-height: 150px; width: 95%; height: 95%; text-align: center\'>\n            <span style=\'font-size: 12px; color: #cacaca; text-align: right; border-bottom: 1px solid #ccc;\'>regala lo que quieras en <a href=\'http://regaloenclave.com\'>regaloenclave.com</a></span><br/>    \n            <h1>Informacion de tu cuenta en regalo en clave ah sido actualizada</h1>\n            <br/>\n            Por favor checa que tu informacion sea la correcta en<br/>\n            <a href=\"http://regaloenclave.com/affiliate\">http://regaloenclave.com/affiliate</a>\n            <br><br><br>\n        </div>','update information','tu informacion de regaloenclave.com fue actualizada'),(4,'Spanish','no-responder@regaloenclave.com',0,'<div style=\'margin: auto; border: solid 1px #ccc; min-height: 150px; width: 95%; height: 95%; text-align: center\'>\n            <span style=\'font-size: 12px; color: #cacaca; text-align: right; border-bottom: 1px solid #ccc;\'>regala lo que quieras en <a href=\'http://regaloenclave.com\'>regaloenclave.com</a></span><br/>    \n            <h1>Informacion de tu cuenta en regalo en clave ah sido actualizada</h1>\n            <br/>\n            Por favor checa que tu informacion sea la correcta en<br/>\n            <a href=\"http://regaloenclave.com/affiliate\">http://regaloenclave.com/affiliate</a>\n            <br><br><br>\n        </div>','update information','tu informacion de regaloenclave.com fue actualizada'),(5,'English','no-responder@regaloenclave.com',0,'<div style=\'margin: auto; border: solid 1px #ccc; min-height: 150px; width: 95%; height: 95%; text-align: center\'>            \n            <div style=\'font-size: 12px; color:#cacaca; text-align: right; border-top: 1 px solid #ccc\'>\n                regala lo que quieras en <a href=\'http://regaloenclave.com\'>regaloenclave.com</a>\n            </div>            \n            \n            Bienvenido :name a regaloenclave.com<br/>\n            Proximamente recibiras informacion para poder entrar al sistema de regaloenclave.com para que puedas afiliar negocios<br/>\n            <br/>\n            Esta cuenta de correo sera tu usuario \n            usuario: <br/>\n            <strong>:email</strong>            \n            <br>\n            <br>\n            <br>\n            Gracias por formar parte de regaloenclave.com\n        </div>','new freelancer','bienvenido freelancer a regaloenclave.com'),(6,'Spanish','no-responder@regaloenclave.com',0,'<div style=\'margin: auto; border: solid 1px #ccc; min-height: 150px; width: 95%; height: 95%; text-align: center\'>            \n            <div style=\'font-size: 12px; color:#cacaca; text-align: right; border-top: 1 px solid #ccc\'>\n                regala lo que quieras en <a href=\'http://regaloenclave.com\'>regaloenclave.com</a>\n            </div>            \n            \n            Bienvenido :name a regaloenclave.com<br/>\n            Proximamente recibiras informacion para poder entrar al sistema de regaloenclave.com para que puedas afiliar negocios<br/>\n            <br/>\n            Esta cuenta de correo sera tu usuario \n            usuario: <br/>\n            <strong>:email</strong>            \n            <br>\n            <br>\n            <br>\n            Gracias por formar parte de regaloenclave.com\n        </div>','new freelancer','bienvenido freelancer a regaloenclave.com'),(7,'English','no-responder@regaloenclave.com',0,'<div style=\'margin: auto; border: solid 1px #ccc; min-height: 150px; width: 95%; height: 95%; text-align: center\'>            \n            <div style=\'font-size: 12px; color:#cacaca; text-align: right; border-top: 1 px solid #ccc\'>\n                regala lo que quieras en <a href=\'http://regaloenclave.com\'>regaloenclave.com</a>\n            </div>            \n            \n            Bienvenido a regaloenclave.com<br/>\n            Ahora puedes ingresar al sistema de regaloenclave.com en la siguiente direccion \n            <a href=\'http://regaloenclave.com/affiliate\'>http://regaloenclave.com/affiliate</a>\n            <br/>\n            Esta cuenta de correo sera tu usuario <strong>:usuario</strong> y el password es el que usaste en el registro <br/>\n            <br>\n            <br>\n            <br>\n            Gracias por formar parte de regaloenclave.com\n        </div>','new partner','bienvenido affiliado a regaloenclave.com'),(8,'Spanish','no-responder@regaloenclave.com',0,'<div style=\'margin: auto; border: solid 1px #ccc; min-height: 150px; width: 95%; height: 95%; text-align: center\'>            \n            <div style=\'font-size: 12px; color:#cacaca; text-align: right; border-top: 1 px solid #ccc\'>\n                regala lo que quieras en <a href=\'http://regaloenclave.com\'>regaloenclave.com</a>\n            </div>            \n            \n            Bienvenido a regaloenclave.com<br/>\n            Ahora puedes ingresar al sistema de regaloenclave.com en la siguiente direccion \n            <a href=\'http://regaloenclave.com/affiliate\'>http://regaloenclave.com/affiliate</a>\n            <br/>\n            Esta cuenta de correo sera tu usuario <strong>:usuario</strong> y el password es el que usaste en el registro <br/>\n            <br>\n            <br>\n            <br>\n            Gracias por formar parte de regaloenclave.com\n        </div>','new partner','bienvenido affiliado a regaloenclave.com'),(9,'Spanish','no-responder@regaloenclave.com',0,'<div style=\'margin: auto; border: solid 1px #ccc; min-height: 150px; width: 95%; height: 95%; text-align: center\'>            \r\n            <div style=\'font-size: 12px; color:#cacaca; text-align: right; border-top: 1 px solid #ccc\'>\r\n                regala lo que quieras en <a href=\'http://regaloenclave.com\'>regaloenclave.com</a>\r\n            </div>            \r\n            \r\n            <h2>Gracias :name por comprar en regalo en clave.com, tu compra es segura</h2><br/>\r\n            \r\n            Hiciste un obsequio por $:amount a :recipient quien te lo agradecera sinceramente.\r\n            <br/>\r\n            tu pago fue realizado con la tarjeta **** **** **** :card<br/>\r\n            con la clave de autorizacion :id y en tu estado de cuenta aparecera con el concepto de \"Regalo en Clave\"            \r\n            <br/>\r\n            <br/>            \r\n            <img src=\"http://static.regaloenclave.com/images/Logo150x150.png\" alt=\"\" />\r\n            <br/>\r\n            <br/>\r\n            Gracias por usar <a href=\"http://regaloenclave.com\">regaloenclave.com</a>\r\n        </div>    ','depositor new transfer','tu regalo se realizo con exito'),(10,'English','no-responder@regaloenclave.com',0,'<div style=\'margin: auto; border: solid 1px #ccc; min-height: 150px; width: 95%; height: 95%; text-align: center\'>            \r\n            <div style=\'font-size: 12px; color:#cacaca; text-align: right; border-top: 1 px solid #ccc\'>\r\n                regala lo que quieras en <a href=\'http://regaloenclave.com\'>regaloenclave.com</a>\r\n            </div>            \r\n            \r\n            <h2>Gracias :name por comprar en regalo en clave.com, tu compra es segura</h2><br/>\r\n            \r\n            Hiciste un obsequio por $:amount a :recipient quien te lo agradecera sinceramente.\r\n            <br/>\r\n            tu pago fue realizado con la tarjeta **** **** **** :card<br/>\r\n            con la clave de autorizacion :id y en tu estado de cuenta aparecera con el concepto de \"Regalo en Clave\"            \r\n            <br/>\r\n            <br/>            \r\n            <img src=\"http://static.regaloenclave.com/images/Logo150x150.png\" alt=\"\" />\r\n            <br/>\r\n            <br/>\r\n            Gracias por usar <a href=\"http://regaloenclave.com\">regaloenclave.com</a>\r\n        </div>    ','depositor new transfer','tu regalo se realizo con exito'),(13,'Spanish','no-responder@regaloenclave.com',0,'<div style=\'margin: auto; border: solid 1px #ccc; min-height: 150px; width: 95%; height: 95%; text-align: center\'>            \r\n            <div style=\'font-size: 12px; color:#cacaca; text-align: right; border-top: 1 px solid #ccc\'>\r\n                regala lo que quieras en <a href=\'http://regaloenclave.com\'>regaloenclave.com</a>\r\n            </div>                        \r\n            \r\n            hola :name la clave de tu regalo es <span style=\"color:red\"><strong>:secret</strong></span>\r\n            <br/>\r\n            por un valor de <strong>:value</strong>\r\n            <br/>\r\n            estos son los sitios donde puedes cambiar tu clave \r\n            <a href=\"http://regaloenclave.com/affiliateDirectory\">sitios afiliados a regaloenclave.com</a>\r\n            <br/>\r\n            recuerda que tu clave tiene una vigencia de 90 dias apartir de hoy.\r\n            <br/>\r\n            <br/>\r\n            <br/>\r\n            <img src=\"http://static.regaloenclave.com/images/tarjeta_:card.jpg\" style=\"text-align: center\" alt=\"\" />\r\n            <br/>\r\n            <br/>\r\n            Gracias por usar <a href=\"http://regaloenclave.com\">regaloenclave.com</a>\r\n        </div>   ','recipient new secret','tienes una nueva clave en regaloenclave.com'),(14,'English','no-responder@regaloenclave.com',0,'<div style=\'margin: auto; border: solid 1px #ccc; min-height: 150px; width: 95%; height: 95%; text-align: center\'>            \r\n            <div style=\'font-size: 12px; color:#cacaca; text-align: right; border-top: 1 px solid #ccc\'>\r\n                regala lo que quieras en <a href=\'http://regaloenclave.com\'>regaloenclave.com</a>\r\n            </div>                        \r\n            \r\n            hola :name la clave de tu regalo es <span style=\"color:red\"><strong>:secret</strong></span>\r\n            <br/>\r\n            por un valor de <strong>:value</strong>\r\n            <br/>\r\n            estos son los sitios donde puedes cambiar tu clave \r\n            <a href=\"http://regaloenclave.com/affiliateDirectory\">sitios afiliados a regaloenclave.com</a>\r\n            <br/>\r\n            recuerda que tu clave tiene una vigencia de 90 dias apartir de hoy.\r\n            <br/>\r\n            <br/>\r\n            <br/>\r\n            <img src=\"http://static.regaloenclave.com/images/tarjeta_:card.jpg\" style=\"text-align: center\" alt=\"\" />\r\n            <br/>\r\n            <br/>\r\n            Gracias por usar <a href=\"http://regaloenclave.com\">regaloenclave.com</a>\r\n        </div>   ','recipient new secret','tienes una nueva clave en regaloenclave.com'),(15,'Spanish','no-responder@regaloenclave.com',0,'<div style=\\\'margin: auto; border: solid 1px #ccc; min-height: 150px; width: 95%; height: 95%; text-align: center\\\'>            \\n            <div style=\\\'font-size: 12px; color:#cacaca; text-align: right; border-top: 1 px solid #ccc\\\'>\\n                regala lo que quieras en <a href=\\\'http://regaloenclave.com\\\'>regaloenclave.com</a>\\n            </div>            \\n            \\n            <img src=\\\"http://static.regaloenclave.c<div style=\"margin: auto; border: solid 1px #ccc; min-height: 150px; width: 95%; height: 95%; text-align: center\">            \r\n            <div style=\"font-size: 12px; color:#cacaca; text-align: right; border-top: 1 px solid #ccc\">\r\n                regala lo que quieras en <a href=\"http://regaloenclave.com\">regaloenclave.com</a>\r\n            </div>            \r\n            \r\n            <img src=\"http://static.regaloenclave.com/images/tarjeta_:card.jpg\" style=\"text-align: center\" />\r\n            <br/>\r\n            <span>\r\n                :message\r\n            </span>\r\n            <br/>\r\n            <img src=\"http://static.regaloenclave.com/images/Logo150x150.png\" />\r\n            <br/>\r\n            Muchas felicidades :name1 (:name1 :lastName1) te ha hecho un regalo en clave por la cantidad de <strong>$:value</strong>\r\n            <br/>\r\n            tu clave es <span style=\"color: red\"><strong>:secret</strong></span>\r\n            <br/>\r\n            Esta clave la puede cambiar total o parcialmente por productos\r\n            y servicios en cualquiera de los negocios afiliados a nuestro sistema, mismos que puedes \r\n            consultar <a href=\"http://regaloenclave.com/affiliateDirectory\">aquí</a> recuerda que tu clave tiene un vigencia de 90 días a partir de hoy, \r\n            si no la utilizas se pierde su importe, consulta el modo de usarla en <a href=\"https://regaloenclave.com\">www.regaloenclave.com</a>\r\n            <br/>\r\n            <br/>\r\n            <br/>\r\n            <br/>\r\n            <br/>\r\n            <br/>\r\n            <br/>\r\n            <br/>\r\n            Gracias por usar <a href=\"http://regaloenclave.com\">regaloenclave.com</a>\r\n            <br/>\r\n        </div> ','recipient new transfer','haz recibido un regalo en clave'),(16,'English','no-responder@regaloenclave.com',0,'<div style=\"margin: auto; border: solid 1px #ccc; min-height: 150px; width: 95%; height: 95%; text-align: center\">            \r\n            <div style=\"font-size: 12px; color:#cacaca; text-align: right; border-top: 1 px solid #ccc\">\r\n                regala lo que quieras en <a href=\"http://regaloenclave.com\">regaloenclave.com</a>\r\n            </div>            \r\n            \r\n            <img src=\"http://static.regaloenclave.com/images/tarjeta_:card.jpg\" style=\"text-align: center\" />\r\n            <br/>\r\n            <span>\r\n                :message\r\n            </span>\r\n            <br/>\r\n            <img src=\"http://static.regaloenclave.com/images/Logo150x150.png\" />\r\n            <br/>\r\n            Muchas felicidades :name1 (:name1 :lastName1) te ha hecho un regalo en clave por la cantidad de <strong>$:value</strong>\r\n            <br/>\r\n            tu clave es <span style=\"color: red\"><strong>:secret</strong></span>\r\n            <br/>\r\n            Esta clave la puede cambiar total o parcialmente por productos\r\n            y servicios en cualquiera de los negocios afiliados a nuestro sistema, mismos que puedes \r\n            consultar <a href=\"http://regaloenclave.com/affiliateDirectory\">aquí</a> recuerda que tu clave tiene un vigencia de 90 días a partir de hoy, \r\n            si no la utilizas se pierde su importe, consulta el modo de usarla en <a href=\"https://regaloenclave.com\">www.regaloenclave.com</a>\r\n            <br/>\r\n            <br/>\r\n            <br/>\r\n            <br/>\r\n            <br/>\r\n            <br/>\r\n            <br/>\r\n            <br/>\r\n            Gracias por usar <a href=\"http://regaloenclave.com\">regaloenclave.com</a>\r\n            <br/>\r\n        </div> ','recipient new transfer','haz recibido un regalo en clave');








INSERT INTO `regaloenclave`.`mail_template` (`mail_template_display_language`, `mail_template_from`, `mail_template_message`, `mail_template_name`, `mail_template_subject`) VALUES ('English', 'no-responder@regaloenclave.com', '<div style="margin: auto; border: solid 1px #ccc; min-height: 150px; width: 95%; height: 95%; text-align: center">            
            <div style="font-size: 12px; color:#cacaca; text-align: right; border-top: 1 px solid #ccc">
                regala lo que quieras en <a href="http://regaloenclave.com">regaloenclave.com</a>
            </div>            
            
            <img src="http://static.regaloenclave.com/images/tarjeta_:card.jpg" style="text-align: center" />
            <br/>
            <br/>
            
            <h2>:name1 te ha enviado un regalo en clave</h2><br/>
            hola :name2 la clave de tu regalo es :secret
            <br/>
            por un valor de :value
            <br/>
            estos son los sitios donde puedes cambiar tu clave 
            <a href="http://regaloenclave.com/affiliateDirectory">sitios afiliados a regaloenclave.com</a>
            <br/>
            <br/>
            Gracias por usar <a href="http://regaloenclave.com">regaloenclave.com</a>
        </div>        ', 'recipient new transfer', 'haz recibido un regalo en clave');
INSERT INTO `regaloenclave`.`mail_template` (`mail_template_display_language`, `mail_template_from`, `mail_template_message`, `mail_template_name`, `mail_template_subject`) VALUES ('Spanish', 'no-responder@regaloenclave.com', '<div style="margin: auto; border: solid 1px #ccc; min-height: 150px; width: 95%; height: 95%; text-align: center">            
            <div style="font-size: 12px; color:#cacaca; text-align: right; border-top: 1 px solid #ccc">
                regala lo que quieras en <a href="http://regaloenclave.com">regaloenclave.com</a>
            </div>            
            
            <img src="http://static.regaloenclave.com/images/tarjeta_:card.jpg" style="text-align: center" />
            <br/>
            <br/>
            
            <h2>:name1 te ha enviado un regalo en clave</h2><br/>
            hola :name2 la clave de tu regalo es :secret
            <br/>
            por un valor de :value
            <br/>
            estos son los sitios donde puedes cambiar tu clave 
            <a href="http://regaloenclave.com/affiliateDirectory">sitios afiliados a regaloenclave.com</a>
            <br/>
            <br/>
            Gracias por usar <a href="http://regaloenclave.com">regaloenclave.com</a>
        </div>        ', 'recipient new transfer', 'haz recibido un regalo en clave');






#configuration---------------------------------------------------------------------------------------
INSERT INTO `regaloenclave`.`configuration_mail_authentication` (`configuration_mail_authentication_smtp_host`, `configuration_mail_authentication_smtp_password`, `configuration_mail_authentication_smtp_port`, `configuration_mail_authentication_smtp_username`) VALUES ('smtp.gmail.com', 'elpassword', '587', 'regaloenclave@gmail.com');








#general configuration-------------------------------------------------------------------------------
INSERT INTO general_configuration (general_configuration_upload_path, general_configuration_url_static_images) VALUES ('/opt/glassfish4/glassfish/domains/domain1/docroot/uploads/images/', 'http://static.regaloenclave.com/uploads/images/');






#transaction type------------------------------------------------------------------------------------
INSERT INTO transaction_type (transaction_type_name) VALUES ('new affiliate');
INSERT INTO transaction_type (transaction_type_name) VALUES ('new freelancer');
INSERT INTO transaction_type (transaction_type_name) VALUES ('new establishment');
INSERT INTO transaction_type (transaction_type_name) VALUES ('new cashier');
INSERT INTO transaction_type (transaction_type_name) VALUES ('new partner');
INSERT INTO transaction_type (transaction_type_name) VALUES ('new subscriber');
INSERT INTO transaction_type (transaction_type_name) VALUES ('new administrator');
INSERT INTO transaction_type (transaction_type_name) VALUES ('new category');
INSERT INTO transaction_type (transaction_type_name) VALUES ('new company');
INSERT INTO transaction_type (transaction_type_name) VALUES ('update basic information affiliate');
INSERT INTO transaction_type (transaction_type_name) VALUES ('update affiliate password');
INSERT INTO transaction_type (transaction_type_name) VALUES ('update tax information affiliate');
INSERT INTO transaction_type (transaction_type_name) VALUES ('update electronic data transfer affiliate');
INSERT INTO transaction_type (transaction_type_name) VALUES ('update bank information affiliate');
INSERT INTO transaction_type (transaction_type_name) VALUES ('update basic information company');
INSERT INTO transaction_type (transaction_type_name) VALUES ('update company password');
INSERT INTO transaction_type (transaction_type_name) VALUES ('update tax information company');
INSERT INTO transaction_type (transaction_type_name) VALUES ('update electronic data transfer company');
INSERT INTO transaction_type (transaction_type_name) VALUES ('update bank information company');
INSERT INTO transaction_type (transaction_type_name) VALUES ('update establishment basic information');
INSERT INTO transaction_type (transaction_type_name) VALUES ('update responsable basic information');
INSERT INTO transaction_type (transaction_type_name) VALUES ('update responsable password');
INSERT INTO transaction_type (transaction_type_name) VALUES ('update freelancer');
INSERT INTO transaction_type (transaction_type_name) VALUES ('update freelancer password');
INSERT INTO transaction_type (transaction_type_name) VALUES ('update cashier');
INSERT INTO transaction_type (transaction_type_name) VALUES ('update cashier password');
INSERT INTO transaction_type (transaction_type_name) VALUES ('update partner');
INSERT INTO transaction_type (transaction_type_name) VALUES ('update partner password');
INSERT INTO transaction_type (transaction_type_name) VALUES ('active frelancer');
INSERT INTO transaction_type (transaction_type_name) VALUES ('deactivate frelancer');
INSERT INTO transaction_type (transaction_type_name) VALUES ('active affiliate');
INSERT INTO transaction_type (transaction_type_name) VALUES ('deactivate affiliate');
INSERT INTO transaction_type (transaction_type_name) VALUES ('active company');
INSERT INTO transaction_type (transaction_type_name) VALUES ('deactivate company');
INSERT INTO transaction_type (transaction_type_name) VALUES ('active cashier');
INSERT INTO transaction_type (transaction_type_name) VALUES ('deactivate cashier');
INSERT INTO transaction_type (transaction_type_name) VALUES ('active partner');
INSERT INTO transaction_type (transaction_type_name) VALUES ('deactivate partner');






#category----------------------------------------------------------------------------------------------------
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('Accesorios para Vehículos');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('Animales y Mascotas');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('Arte, Cultura y Antigüedades');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('Bares y Antros');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('Bebés');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('Belleza y Cosmética');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('Cámaras y Accesorios');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('Casinos y lugares de apuestas');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('Celulares y Telefonía');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('Coleccionables');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('Computación e Informática');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('Consolas y Videojuegos');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('Departamentales');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('Deportes Fitness y Esparcimiento');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('Línea blanca');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('Electrónica, Audio y Video');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('Especialidades Médicas');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('Hogar y Electrodomésticos');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('Industrias y Oficinas');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('Instrumentos Musicales');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('Joyería y accesorios');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('Juegos y Juguetes');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('Libros, Revistas y Comics');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('Limpieza y Mantenimiento');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('Moda, Calzado y Accesorios');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('Muebles y Decoración');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('Música, Películas y Series');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('Regalos y Juguetes');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('Restaurantes y Cafeterías');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('Seguridad');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('Servicios');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('Viajes y Hoteles');
INSERT INTO `regaloenclave`.`category` (`category_name`) VALUES ('Otras Categorías');





#affiliate type-----------------------------------------------------------------------------------------------
INSERT INTO `regaloenclave`.`affiliate_type` (`affiliate_type_name`) VALUES ('person');
INSERT INTO `regaloenclave`.`affiliate_type` (`affiliate_type_name`) VALUES ('company');






#configuration_mail-------------------------------------------------------------------------------------------
INSERT INTO configuration_mail (configuration_mail_smtp_host, configuration_mail_smtp_port) VALUES ('localhost',25);






#secret_status
INSERT INTO `regaloenclave`.`secret_status` (`secret_status_name`) VALUES ('active');
INSERT INTO `regaloenclave`.`secret_status` (`secret_status_name`) VALUES ('deactivate');
INSERT INTO `regaloenclave`.`secret_status` (`secret_status_name`) VALUES ('consumed');





#transfer_type
INSERT INTO `regaloenclave`.`transfer_type` (`transfer_type_name`) VALUES ('credit card');






#payment_status
INSERT INTO `regaloenclave`.`payment_status` (`payment_status_name`) VALUES ('paid');
INSERT INTO `regaloenclave`.`payment_status` (`payment_status_name`) VALUES ('unpaid');