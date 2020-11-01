insert into club (id, description, image, name, price) values (1, 'Club 1. Benefits: 10% discount on ticket prices.',  '/uploads/images/cinemania.png', 'Cinemania Premium Membership', 5000 );
insert into club (id, description, image, name, price) values (3,'Become standard member by paying 2000 RSD yearly. Benefits: Pick up reserved tickets up to 10 minutes before the start of the screening (instead of the usual 30 minutes). Free parking. Invitation for premieres and special events.', '/uploads/images/cinemania.png', 'Cinemania Standard Membership' , 2000 );
insert into club (id, description, image, name, price) values (2, 'Buy Cinemania bonus card and use card for the rest of your life. Benefits: 1 bonus point for every RSD spent. Attractive prizes in exchange for point. Buy tickets with earned bonus points. Online check of points status.', '/uploads/images/cinemania.png','Cinemania Premium Bonus Card ', 1000);

insert into user_cinema (id, email,  first_name, last_name, password, role, username) values (1, 'admin@test.com', 'Admin', 'Admin', 'admin', 'ADMIN_ROLE', 'admin');
insert into user_cinema (id, email,  first_name, last_name, password, role, username) values (2, 'user@test.com', 'User', 'User', 'user', 'USER_ROLE', 'user');

insert into movie (id, description, director, duration, genre, image, is_showing, rating, title, trailer_url, year) values (1, 'Renton, deeply immersed in the Edinburgh drug scene, tries to clean up and get out, despite the allure of the drugs and influence of friends.',
'Danny Boyle', 93, 'Drama', '/img/trainspotting.jpg', true, 8.1, 'Trainspotting', 'https://www.youtube.com/watch?v=8LuxOYIpu-I&ab_channel=MovieclipsClassicTrailers',1996);
insert into movie (id, description, director, duration, genre, image, is_showing, rating, title, trailer_url, year) values (2, 'In 1954, a U.S. Marshal investigates the disappearance of a murderer who escaped from a hospital for the criminally insane.',
'Martin Scorsese', 138, 'Thriller', '/img/shutter.jpg', true, 8.2, 'Shutter Island', 'https://www.youtube.com/watch?v=5iaYLCiq5RM&ab_channel=watchCulturetainment', 2010);
insert into movie (id, description, director, duration, genre, image, is_showing, rating, title, trailer_url, year) values (3, 'A team of explorers travel through a wormhole in space in an attempt to ensure humanity''s survival.',
'Christopher Nolan', 169, 'Sci-Fi', '/img/interstellar.jpg', true, 8.6, 'Interstellar', 'https://www.youtube.com/watch?v=zSWdZVtXT7E&ab_channel=WarnerBros.UK', 2014);

insert into cinema(id, address, city, image, name, phone_number) values (1, 'Vojislava Ilica 140', 'Beograd', '/uploads/images/cinema1.jpg', 'Cinemania Beograd', '0621234567');
insert into cinema(id, address, city, image, name, phone_number) values (2, 'Zmaj Jovina 22 ', 'Novi Sad', '/uploads/images/cinema2.jpg', 'Cinemania Novi Sad', '0631234567');
insert into cinema(id, address, city, image, name, phone_number) values (3, 'Vojvode Putnika 60', 'Kragujevac', '/uploads/images/cinema3.jpg', 'Cinemania Kragujevac', '0641234567');