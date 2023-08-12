insert into t_user(name, role, created_time) values ( 'App admin', 'admin', current_timestamp());
insert into t_user(name, role, created_Time) values ( 'own_user1', 'owner', current_timestamp());
insert into t_user(name, role, created_Time) values ( 'own_user2', 'owner', current_timestamp());
insert into t_user(name, role, created_Time) values ( 'normal_user1', 'user', current_timestamp());
insert into t_user(name, role, created_Time) values ( 'normal_user2', 'user', current_timestamp());
insert into t_user(name, role, created_Time) values ( 'normal_user3', 'user', current_timestamp());
insert into t_user(name, role, created_Time) values ( 'normal_user4', 'user', current_timestamp());


insert into city(name, created_time, created_by) values ( 'Indore', current_timestamp(), 1);
insert into city(name, created_time, created_by) values ( 'Bhopal', current_timestamp(), 1);
insert into city(name, created_time, created_by) values ( 'Delhi', current_timestamp(), 1);
insert into city(name, created_time, created_by) values ( 'Mumbai', current_timestamp(), 1);


insert into theater(name, address, city_id, created_time, created_by) values ( 'PVR-CINEMA', 'Vijay Nagar', 1, current_timestamp(), 2 );
insert into theater(name, address, city_id, created_time, created_by) values ( 'INOX', 'C-21 Mall', 1, current_timestamp(), 3 );
insert into theater(name, address, city_id, created_time, created_by) values ( 'FUNDORE Cinemas', 'Rau', 1, current_timestamp(), 3 );
insert into theater(name, address, city_id, created_time, created_by) values ( 'PVR-CINEMA', 'MP Nagar', 2, current_timestamp(), 2 );
insert into theater(name, address, city_id, created_time, created_by) values ( 'INOX', 'New Market', 2, current_timestamp(), 3 );
insert into theater(name, address, city_id, created_time, created_by) values ( 'PVR-CINEMA', 'India Gate', 3, current_timestamp(), 2 );
insert into theater(name, address, city_id, created_time, created_by) values ( 'PVR-CINEMA', 'Kola ba', 4, current_timestamp(), 2 );

insert into seat_type(seat_type, price, created_time, created_by) values ( 'SILVER', 100, current_timestamp(), 1 );
insert into seat_type(seat_type, price, created_time, created_by) values ( 'GOLD', 150, current_timestamp(), 1 );
insert into seat_type(seat_type, price, created_time, created_by) values ( 'PLATINUM', 200, current_timestamp(), 1 );


insert into AUDITORIUM(name, max_row, max_column, THEATER_ID, created_time, created_by) values ( 'PVR-VJY-AUDI-1', 5, 5, 1, current_timestamp(), 2);
insert into AUDITORIUM_SUPPORTED_FEATURES values (1, 0);
insert into AUDITORIUM_SUPPORTED_FEATURES values (1, 2);

insert into AUDITORIUM(name, max_row, max_column, THEATER_ID, created_time, created_by)values ( 'PVR-VJY-AUDI-2', 4, 5, 1, current_timestamp(), 2);
insert into AUDITORIUM_SUPPORTED_FEATURES values (2, 1);
insert into AUDITORIUM_SUPPORTED_FEATURES values (2, 2);
insert into AUDITORIUM_SUPPORTED_FEATURES values (2, 3);

insert into AUDITORIUM(name, max_row, max_column, THEATER_ID, created_time, created_by) values ( 'INOX-C21-AUDI-1', 4, 5, 2, current_timestamp(), 3);
insert into AUDITORIUM_SUPPORTED_FEATURES values (3, 1);
insert into AUDITORIUM_SUPPORTED_FEATURES values (3, 2);
insert into AUDITORIUM_SUPPORTED_FEATURES values (3, 3);

insert into AUDITORIUM(name, max_row, max_column, THEATER_ID, created_time, created_by) values ( 'INOX-C21-AUDI-2', 5, 6, 2, current_timestamp(), 3);
insert into AUDITORIUM_SUPPORTED_FEATURES values (4, 0);
insert into AUDITORIUM_SUPPORTED_FEATURES values (4, 1);
insert into AUDITORIUM_SUPPORTED_FEATURES values (4, 3);

insert into seat(seat_num, seat_row, seat_col, AUDITORIUM_ID, seat_type_id, created_time, created_by)
values ('a1', 0, 0, 1, 1, current_timestamp(), 1);
insert into seat(seat_num, seat_row, seat_col, AUDITORIUM_ID, seat_type_id, created_time, created_by)
values ('a2', 0, 1, 1, 1, current_timestamp(), 1);
insert into seat(seat_num, seat_row, seat_col, AUDITORIUM_ID, seat_type_id, created_time, created_by)
values ('a3', 0, 2, 1, 1, current_timestamp(), 1);
insert into seat(seat_num, seat_row, seat_col, AUDITORIUM_ID, seat_type_id, created_time, created_by)
values ('a4', 0, 3, 1, 1, current_timestamp(), 1);
insert into seat(seat_num, seat_row, seat_col, AUDITORIUM_ID, seat_type_id, created_time, created_by)
values ('a5', 0, 4, 1, 1, current_timestamp(), 1);
insert into seat(seat_num, seat_row, seat_col, AUDITORIUM_ID, seat_type_id, created_time, created_by)
values ('b1', 1, 0, 1, 2, current_timestamp(), 1);
insert into seat(seat_num, seat_row, seat_col, AUDITORIUM_ID, seat_type_id, created_time, created_by)
values ('b2', 1, 1, 1, 2, current_timestamp(), 1);
insert into seat(seat_num, seat_row, seat_col, AUDITORIUM_ID, seat_type_id, created_time, created_by)
values ('b3', 1, 2, 1, 2, current_timestamp(), 1);
insert into seat(seat_num, seat_row, seat_col, AUDITORIUM_ID, seat_type_id, created_time, created_by)
values ('b4', 1, 3, 1, 2, current_timestamp(), 1);
insert into seat(seat_num, seat_row, seat_col, AUDITORIUM_ID, seat_type_id, created_time, created_by)
values ('b5', 1, 4, 1, 2, current_timestamp(), 1);

insert into movie(name, release_date) values ( 'Gadar2', current_date()-5);
insert into movie(name, release_date) values ( 'OMG2', current_date()+1);
insert into movie(name, release_date) values ( 'Barbie', current_date()+10);
insert into movie(name, release_date) values ( 'adgsj', current_date()+10);
insert into movie(name, release_date) values ( 'Barbasdie', current_date()+10);
insert into movie(name, release_date) values ( 'kajalj', current_date()+10);
insert into movie(name, release_date) values ( 'akhdk', current_date()+10);

insert into movie_casts(movie_id, casts) values ( 1, 'Sunny Deol' );
insert into movie_casts(movie_id, casts) values ( 1, 'Amisha Patel' );
insert into movie_casts(movie_id, casts) values ( 2, 'Akshay Kumar' );
insert into movie_casts(movie_id, casts) values ( 2, 'Pankaj Tripathi' );
insert into movie_casts(movie_id, casts) values ( 2, 'Kriti Senon' );
insert into movie_casts(movie_id, casts) values ( 3, 'Alia Bhatt' );


insert into show(show_name, show_time, movie_id, auditorium_id, ADDITIONAL_PRICE) values ( 'gadar2-morning-1', current_timestamp()+2, 1 , 1, 20);
insert into show(show_name, show_time, movie_id, auditorium_id, ADDITIONAL_PRICE) values ( 'OMG2-morning-1', current_timestamp()+2, 2 , 2, 30);
insert into show(show_name, show_time, movie_id, auditorium_id, ADDITIONAL_PRICE) values ( 'OMG2-morning-2', current_timestamp()+3, 6 , 2, 50);
insert into show_required_features values ( 1, 0 );
insert into show_required_features values ( 1, 2 );
insert into show_required_features values ( 2, 1 );
insert into show_required_features values ( 1, 3 );
insert into show(show_name, show_time, movie_id, auditorium_id, ADDITIONAL_PRICE) values ( 'gadar2-morning-1', current_timestamp()+2, 3, 1, 20);
insert into show(show_name, show_time, movie_id, auditorium_id, ADDITIONAL_PRICE) values ( 'OMG2-morning-1', current_timestamp()+2, 4 , 2, 30);
insert into show(show_name, show_time, movie_id, auditorium_id, ADDITIONAL_PRICE) values ( 'OMG2-morning-2', current_timestamp()+3, 5 , 2, 50);
insert into show_required_features values ( 1, 0 );
insert into show_required_features values ( 1, 2 );
insert into show_required_features values ( 2, 1 );
insert into show_required_features values ( 1, 3 );





