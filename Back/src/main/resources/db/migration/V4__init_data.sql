insert into measurement_units(id, measurement_units_name)
values (1,'м3'),
       (2,'м2');
insert into materials(id, material_type, name, structural_element_type)
values (1,'Пиломатериал','Доска','Каркас'),
       (2,'OSB','OSB','Каркас'),
       (3,'Утеплитель','Утеплитель','Каркас'),
       (4,'Пароизоляция','Пароизоляция','Каркас'),
       (5,'Ветрозащита','Ветрозащита','Каркас');



insert into material_characteristics (id, length, name, thickness, volume, width, measurement_unit_id, mаterials_id)
values (1,3000,'Доска 50*100*3000',50,0.015,100,1,1),
       (2,3000,'Доска 50*150*3000',50,0.0225,150,1,1),
       (3,3000,'Доска 50*200*3000',50,0.03,200,1,1),
       (4,3000,'Доска 50*250*3000',50,0.0375,250,1,1),
       (5,3000,'Доска 50*300*3000',50,0.045,300,1,1),
       (6,6000,'Доска 50*100*3000',50,0.03,100,1,1),
       (7,6000,'Доска 50*150*3000',50,0.45,150,1,1),
       (8,6000,'Доска 50*200*6000',50,0.06,200,1,1),
       (9,6000,'Доска 50*250*3000',50,0.075,250,1,1),
       (10,6000,'Доска 50*300*3000',50,0.09,300,1,1),
       (11,null,'OSB 9 мм',9,null,null,2,2),
       (12,null,'OSB 10 мм',10,null,null,2,2),
       (13,null,'OSB 15 мм',15,null,null,2,2),
       (14,null,'OSB 18 мм',18,null,null,2,2),
       (15,null,'Кнауф ТеплоКнауф 100 мм',100,null,null,1,3),
       (16,null,'Технониколь 100 мм',100,null,null,1,3),
       (17,null,'Эковер 100 мм',100,null,null,1,3),
       (18,null,'Эковер 150 мм',150,null,null,1,3),
       (19,null,'Эковер 200 мм',200,null,null,1,3),
       (20,null,'Фасад 200 мм',200,null,null,1,3),
       (21,null,'Эковер 250 мм',250,null,null,1,3),
       (22,null,'Ондутис',null,null,null,2,4),
       (23,null,'Пароизоляция Axton (b)',null,null,null,2,4),
       (24,null,'Пароизоляционная пленка Ютафол Н 96 Сильвер',null,null,null,2,4),
       (25,null,'Пароизоляция В',null,null,null,2,4),
       (26,null,'Ветро-влагозащитная мембрана Brane А',null,null,null,2,5),
       (27,null,'Паропроницаемая ветро-влагозащита A Optima',null,null,null,2,5),
       (28,null,'Гидро-ветрозащита Тип А',null,null,null,2,5);

insert into price_lists(id, date, purchase_price, selling_price, material_characteristics_id)
values (1,null,12000,null,1),
       (2,null,12000,null,2),
       (3,null,12000,null,3),
       (4,null,12000,null,4),
       (5,null,12000,null,5),
       (6,null,12000,null,6),
       (7,null,12000,null,7),
       (8,null,12000,null,8),
       (9,null,12000,null,9),
       (10,null,12000,null,10),
       (11,null,256,null,11),
       (12,null,288,null,12),
       (13,null,384,null,13),
       (14,null,480,null,14),
       (15,null,3000,null,15),
       (16,null,3500,null,16),
       (17,null,2800,null,17),
       (18,null,2800,null,18),
       (19,null,2800,null,19),
       (20,null,3200,null,20),
       (21,null,2800,null,21),
       (22,null,33,null,22),
       (23,null,20,null,23),
       (24,null,21,null,24),
       (25,null,11,null,25),
       (26,null,57,null,26),
       (27,null,21,null,27),
       (28,null,53,null,28);