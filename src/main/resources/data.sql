//role
(id, role_name)
VALUES(1, 'User');
INSERT INTO public."role"
(id, role_name)
VALUES(2, 'Admin');


//user (password="hunghung")
INSERT INTO public.customer
(id, avatar, email, is_verified_email, "password", phone, username, role_id)
VALUES(2, NULL, 'hungamtp@gmail.com', false, '{bcrypt}$2a$10$DHLjFxpa.GUObjSnRASZ9uVPzBkY1fk29eoyY4Qppglmw75udcnSy', '0902751408', 'hung', 1);
INSERT INTO public.customer
(id, avatar, email, is_verified_email, "password", phone, username, role_id)
VALUES(3, NULL, 'admin@gmail.com', false, '{bcrypt}$2a$10$6t85vBDIgPq5J68aAKHxbut/kG1YmwPhId4aVov2ITt61AutIssbu', '0902751401', 'admin', 2);


// product
INSERT INTO public.product
(id, created_date, description, is_delete, modified_date, "name", price, image1, image2, thumbnail, retail, sale_off, category_id)
VALUES(2, '2021-10-13', 'Good', false, '2021-10-11', 'Jd 12', 123123.0, 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.3.jpg?alt=media&token=b9eb9a32-2c3b-4018-ae74-9e4bba6abf43', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.2.jpg?alt=media&token=605068c4-2cee-4077-86eb-43d0dc7ea57a', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.1.jpg?alt=media&token=5317e91c-950a-421f-92fa-5570ae3f76de', 123123.0, 12, 'G');
INSERT INTO public.product
(id, created_date, description, is_delete, modified_date, "name", price, image1, image2, thumbnail, retail, sale_off, category_id)
VALUES(1, '2021-10-13', 'Nice', false, '2021-10-11', 'Jd 12', 1231.0, 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.3.jpg?alt=media&token=b9eb9a32-2c3b-4018-ae74-9e4bba6abf43', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.2.jpg?alt=media&token=605068c4-2cee-4077-86eb-43d0dc7ea57a', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.1.jpg?alt=media&token=5317e91c-950a-421f-92fa-5570ae3f76de', 1231.0, 12, 'G');
INSERT INTO public.product
(id, created_date, description, is_delete, modified_date, "name", price, image1, image2, thumbnail, retail, sale_off, category_id)
VALUES(3, '2021-10-13', 'Nice', false, '2021-10-11', 'Jd 12', 1231.0, 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.3.jpg?alt=media&token=b9eb9a32-2c3b-4018-ae74-9e4bba6abf43', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.2.jpg?alt=media&token=605068c4-2cee-4077-86eb-43d0dc7ea57a', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.1.jpg?alt=media&token=5317e91c-950a-421f-92fa-5570ae3f76de', 1231.0, 12, 'G');
INSERT INTO public.product
(id, created_date, description, is_delete, modified_date, "name", price, image1, image2, thumbnail, retail, sale_off, category_id)
VALUES(4, '2021-10-13', 'Good', false, '2021-10-11', 'Jd 12', 123123.0, 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.3.jpg?alt=media&token=b9eb9a32-2c3b-4018-ae74-9e4bba6abf43', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.2.jpg?alt=media&token=605068c4-2cee-4077-86eb-43d0dc7ea57a', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.1.jpg?alt=media&token=5317e91c-950a-421f-92fa-5570ae3f76de', 123123.0, 12, 'G');
INSERT INTO public.product
(id, created_date, description, is_delete, modified_date, "name", price, image1, image2, thumbnail, retail, sale_off, category_id)
VALUES(5, '2021-10-13', 'Nice', false, '2021-10-11', 'Jd 12', 1231.0, 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.3.jpg?alt=media&token=b9eb9a32-2c3b-4018-ae74-9e4bba6abf43', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.2.jpg?alt=media&token=605068c4-2cee-4077-86eb-43d0dc7ea57a', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.1.jpg?alt=media&token=5317e91c-950a-421f-92fa-5570ae3f76de', 1231.0, 12, 'G');
INSERT INTO public.product
(id, created_date, description, is_delete, modified_date, "name", price, image1, image2, thumbnail, retail, sale_off, category_id)
VALUES(6, '2021-10-13', 'Good', false, '2021-10-11', 'Jd 12', 123123.0, 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.3.jpg?alt=media&token=b9eb9a32-2c3b-4018-ae74-9e4bba6abf43', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.2.jpg?alt=media&token=605068c4-2cee-4077-86eb-43d0dc7ea57a', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.1.jpg?alt=media&token=5317e91c-950a-421f-92fa-5570ae3f76de', 123123.0, 12, 'G');
INSERT INTO public.product
(id, created_date, description, is_delete, modified_date, "name", price, image1, image2, thumbnail, retail, sale_off, category_id)
VALUES(7, '2021-10-13', 'Nice', false, '2021-10-11', 'Jd 12', 1231.0, 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.3.jpg?alt=media&token=b9eb9a32-2c3b-4018-ae74-9e4bba6abf43', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.2.jpg?alt=media&token=605068c4-2cee-4077-86eb-43d0dc7ea57a', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.1.jpg?alt=media&token=5317e91c-950a-421f-92fa-5570ae3f76de', 1231.0, 12, 'G');
INSERT INTO public.product
(id, created_date, description, is_delete, modified_date, "name", price, image1, image2, thumbnail, retail, sale_off, category_id)
VALUES(8, '2021-10-13', 'Good', false, '2021-10-11', 'Jd 12', 123123.0, 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.3.jpg?alt=media&token=b9eb9a32-2c3b-4018-ae74-9e4bba6abf43', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.2.jpg?alt=media&token=605068c4-2cee-4077-86eb-43d0dc7ea57a', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.1.jpg?alt=media&token=5317e91c-950a-421f-92fa-5570ae3f76de', 123123.0, 12, 'G');
INSERT INTO public.product
(id, created_date, description, is_delete, modified_date, "name", price, image1, image2, thumbnail, retail, sale_off, category_id)
VALUES(9, '2021-10-13', 'Nice', false, '2021-10-11', 'Jd 12', 1231.0, 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.3.jpg?alt=media&token=b9eb9a32-2c3b-4018-ae74-9e4bba6abf43', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.2.jpg?alt=media&token=605068c4-2cee-4077-86eb-43d0dc7ea57a', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.1.jpg?alt=media&token=5317e91c-950a-421f-92fa-5570ae3f76de', 1231.0, 12, 'G');
INSERT INTO public.product
(id, created_date, description, is_delete, modified_date, "name", price, image1, image2, thumbnail, retail, sale_off, category_id)
VALUES(10, '2021-10-13', 'Good', false, '2021-10-11', 'Jd 12', 123123.0, 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.3.jpg?alt=media&token=b9eb9a32-2c3b-4018-ae74-9e4bba6abf43', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.2.jpg?alt=media&token=605068c4-2cee-4077-86eb-43d0dc7ea57a', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.1.jpg?alt=media&token=5317e91c-950a-421f-92fa-5570ae3f76de', 123123.0, 12, 'G');
INSERT INTO public.product
(id, created_date, description, is_delete, modified_date, "name", price, image1, image2, thumbnail, retail, sale_off, category_id)
VALUES(11, '2021-10-13', 'Nice', false, '2021-10-11', 'Jd 12', 1231.0, 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.3.jpg?alt=media&token=b9eb9a32-2c3b-4018-ae74-9e4bba6abf43', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.2.jpg?alt=media&token=605068c4-2cee-4077-86eb-43d0dc7ea57a', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.1.jpg?alt=media&token=5317e91c-950a-421f-92fa-5570ae3f76de', 1231.0, 12, 'G');
INSERT INTO public.product
(id, created_date, description, is_delete, modified_date, "name", price, image1, image2, thumbnail, retail, sale_off, category_id)
VALUES(12, '2021-10-13', 'Good', false, '2021-10-11', 'Jd 12', 123123.0, 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.3.jpg?alt=media&token=b9eb9a32-2c3b-4018-ae74-9e4bba6abf43', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.2.jpg?alt=media&token=605068c4-2cee-4077-86eb-43d0dc7ea57a', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.1.jpg?alt=media&token=5317e91c-950a-421f-92fa-5570ae3f76de', 123123.0, 12, 'G');
INSERT INTO public.product
(id, created_date, description, is_delete, modified_date, "name", price, image1, image2, thumbnail, retail, sale_off, category_id)
VALUES(13, '2021-10-13', 'Nice', false, '2021-10-11', 'Jd 12', 1231.0, 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.3.jpg?alt=media&token=b9eb9a32-2c3b-4018-ae74-9e4bba6abf43', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.2.jpg?alt=media&token=605068c4-2cee-4077-86eb-43d0dc7ea57a', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.1.jpg?alt=media&token=5317e91c-950a-421f-92fa-5570ae3f76de', 1231.0, 12, 'G');
INSERT INTO public.product
(id, created_date, description, is_delete, modified_date, "name", price, image1, image2, thumbnail, retail, sale_off, category_id)
VALUES(14, '2021-10-13', 'Good', false, '2021-10-11', 'Jd 12', 123123.0, 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.3.jpg?alt=media&token=b9eb9a32-2c3b-4018-ae74-9e4bba6abf43', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.2.jpg?alt=media&token=605068c4-2cee-4077-86eb-43d0dc7ea57a', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.1.jpg?alt=media&token=5317e91c-950a-421f-92fa-5570ae3f76de', 123123.0, 12, 'G');
INSERT INTO public.product
(id, created_date, description, is_delete, modified_date, "name", price, image1, image2, thumbnail, retail, sale_off, category_id)
VALUES(15, '2021-10-13', 'Nice', false, '2021-10-11', 'Jd 12', 1231.0, 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.3.jpg?alt=media&token=b9eb9a32-2c3b-4018-ae74-9e4bba6abf43', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.2.jpg?alt=media&token=605068c4-2cee-4077-86eb-43d0dc7ea57a', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.1.jpg?alt=media&token=5317e91c-950a-421f-92fa-5570ae3f76de', 1231.0, 12, 'G');
INSERT INTO public.product
(id, created_date, description, is_delete, modified_date, "name", price, image1, image2, thumbnail, retail, sale_off, category_id)
VALUES(16, '2021-10-13', 'Good', false, '2021-10-11', 'Jd 12', 123123.0, 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.3.jpg?alt=media&token=b9eb9a32-2c3b-4018-ae74-9e4bba6abf43', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.2.jpg?alt=media&token=605068c4-2cee-4077-86eb-43d0dc7ea57a', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.1.jpg?alt=media&token=5317e91c-950a-421f-92fa-5570ae3f76de', 123123.0, 12, 'G');
INSERT INTO public.product
(id, created_date, description, is_delete, modified_date, "name", price, image1, image2, thumbnail, retail, sale_off, category_id)
VALUES(17, '2021-10-13', 'Nice', false, '2021-10-11', 'Jd 12', 1231.0, 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.3.jpg?alt=media&token=b9eb9a32-2c3b-4018-ae74-9e4bba6abf43', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.2.jpg?alt=media&token=605068c4-2cee-4077-86eb-43d0dc7ea57a', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.1.jpg?alt=media&token=5317e91c-950a-421f-92fa-5570ae3f76de', 1231.0, 12, 'G');
INSERT INTO public.product
(id, created_date, description, is_delete, modified_date, "name", price, image1, image2, thumbnail, retail, sale_off, category_id)
VALUES(18, '2021-10-13', 'Good', false, '2021-10-11', 'Jd 12', 123123.0, 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.3.jpg?alt=media&token=b9eb9a32-2c3b-4018-ae74-9e4bba6abf43', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.2.jpg?alt=media&token=605068c4-2cee-4077-86eb-43d0dc7ea57a', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.1.jpg?alt=media&token=5317e91c-950a-421f-92fa-5570ae3f76de', 123123.0, 12, 'G');
INSERT INTO public.product
(id, created_date, description, is_delete, modified_date, "name", price, image1, image2, thumbnail, retail, sale_off, category_id)
VALUES(19, '2021-10-13', 'Nice', false, '2021-10-11', 'Jd 12', 1231.0, 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.3.jpg?alt=media&token=b9eb9a32-2c3b-4018-ae74-9e4bba6abf43', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.2.jpg?alt=media&token=605068c4-2cee-4077-86eb-43d0dc7ea57a', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.1.jpg?alt=media&token=5317e91c-950a-421f-92fa-5570ae3f76de', 1231.0, 12, 'G');
INSERT INTO public.product
(id, created_date, description, is_delete, modified_date, "name", price, image1, image2, thumbnail, retail, sale_off, category_id)
VALUES(20, '2021-10-13', 'Good', false, '2021-10-11', 'Jd 12', 123123.0, 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.3.jpg?alt=media&token=b9eb9a32-2c3b-4018-ae74-9e4bba6abf43', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.2.jpg?alt=media&token=605068c4-2cee-4077-86eb-43d0dc7ea57a', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.1.jpg?alt=media&token=5317e91c-950a-421f-92fa-5570ae3f76de', 123123.0, 12, 'G');
INSERT INTO public.product
(id, created_date, description, is_delete, modified_date, "name", price, image1, image2, thumbnail, retail, sale_off, category_id)
VALUES(21, '2021-10-13', 'Nice', false, '2021-10-11', 'Jd 12', 1231.0, 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.3.jpg?alt=media&token=b9eb9a32-2c3b-4018-ae74-9e4bba6abf43', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.2.jpg?alt=media&token=605068c4-2cee-4077-86eb-43d0dc7ea57a', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.1.jpg?alt=media&token=5317e91c-950a-421f-92fa-5570ae3f76de', 1231.0, 12, 'G');
INSERT INTO public.product
(id, created_date, description, is_delete, modified_date, "name", price, image1, image2, thumbnail, retail, sale_off, category_id)
VALUES(22, '2021-10-13', 'Good', false, '2021-10-11', 'Jd 12', 123123.0, 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.3.jpg?alt=media&token=b9eb9a32-2c3b-4018-ae74-9e4bba6abf43', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.2.jpg?alt=media&token=605068c4-2cee-4077-86eb-43d0dc7ea57a', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.1.jpg?alt=media&token=5317e91c-950a-421f-92fa-5570ae3f76de', 123123.0, 12, 'G');
INSERT INTO public.product
(id, created_date, description, is_delete, modified_date, "name", price, image1, image2, thumbnail, retail, sale_off, category_id)
VALUES(23, '2021-10-13', 'Nice', false, '2021-10-11', 'Jd 12', 1231.0, 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.3.jpg?alt=media&token=b9eb9a32-2c3b-4018-ae74-9e4bba6abf43', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.2.jpg?alt=media&token=605068c4-2cee-4077-86eb-43d0dc7ea57a', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.1.jpg?alt=media&token=5317e91c-950a-421f-92fa-5570ae3f76de', 1231.0, 12, 'G');
INSERT INTO public.product
(id, created_date, description, is_delete, modified_date, "name", price, image1, image2, thumbnail, retail, sale_off, category_id)
VALUES(24, '2021-10-13', 'Good', false, '2021-10-11', 'Jd 12', 123123.0, 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.3.jpg?alt=media&token=b9eb9a32-2c3b-4018-ae74-9e4bba6abf43', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.2.jpg?alt=media&token=605068c4-2cee-4077-86eb-43d0dc7ea57a', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.1.jpg?alt=media&token=5317e91c-950a-421f-92fa-5570ae3f76de', 123123.0, 12, 'G');
INSERT INTO public.product
(id, created_date, description, is_delete, modified_date, "name", price, image1, image2, thumbnail, retail, sale_off, category_id)
VALUES(25, '2021-10-13', 'Nice', false, '2021-10-11', 'Jd 12', 1231.0, 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.3.jpg?alt=media&token=b9eb9a32-2c3b-4018-ae74-9e4bba6abf43', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.2.jpg?alt=media&token=605068c4-2cee-4077-86eb-43d0dc7ea57a', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.1.jpg?alt=media&token=5317e91c-950a-421f-92fa-5570ae3f76de', 1231.0, 12, 'G');
INSERT INTO public.product
(id, created_date, description, is_delete, modified_date, "name", price, image1, image2, thumbnail, retail, sale_off, category_id)
VALUES(26, '2021-10-13', 'Good', false, '2021-10-11', 'Jd 12', 123123.0, 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.3.jpg?alt=media&token=b9eb9a32-2c3b-4018-ae74-9e4bba6abf43', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.2.jpg?alt=media&token=605068c4-2cee-4077-86eb-43d0dc7ea57a', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.1.jpg?alt=media&token=5317e91c-950a-421f-92fa-5570ae3f76de', 123123.0, 12, 'G');
INSERT INTO public.product
(id, created_date, description, is_delete, modified_date, "name", price, image1, image2, thumbnail, retail, sale_off, category_id)
VALUES(27, '2021-10-13', 'Nice', false, '2021-10-11', 'Jd 12', 1231.0, 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.3.jpg?alt=media&token=b9eb9a32-2c3b-4018-ae74-9e4bba6abf43', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.2.jpg?alt=media&token=605068c4-2cee-4077-86eb-43d0dc7ea57a', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.1.jpg?alt=media&token=5317e91c-950a-421f-92fa-5570ae3f76de', 1231.0, 12, 'G');
INSERT INTO public.product
(id, created_date, description, is_delete, modified_date, "name", price, image1, image2, thumbnail, retail, sale_off, category_id)
VALUES(28, '2021-10-13', 'Good', false, '2021-10-11', 'Jd 12', 123123.0, 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.3.jpg?alt=media&token=b9eb9a32-2c3b-4018-ae74-9e4bba6abf43', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.2.jpg?alt=media&token=605068c4-2cee-4077-86eb-43d0dc7ea57a', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.1.jpg?alt=media&token=5317e91c-950a-421f-92fa-5570ae3f76de', 123123.0, 12, 'G');
INSERT INTO public.product
(id, created_date, description, is_delete, modified_date, "name", price, image1, image2, thumbnail, retail, sale_off, category_id)
VALUES(29, '2021-10-13', 'Nice', false, '2021-10-11', 'Jd 12', 1231.0, 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.3.jpg?alt=media&token=b9eb9a32-2c3b-4018-ae74-9e4bba6abf43', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.2.jpg?alt=media&token=605068c4-2cee-4077-86eb-43d0dc7ea57a', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.1.jpg?alt=media&token=5317e91c-950a-421f-92fa-5570ae3f76de', 1231.0, 12, 'G');
INSERT INTO public.product
(id, created_date, description, is_delete, modified_date, "name", price, image1, image2, thumbnail, retail, sale_off, category_id)
VALUES(30, '2021-10-13', 'Good', false, '2021-10-11', 'Jd 12', 123123.0, 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.3.jpg?alt=media&token=b9eb9a32-2c3b-4018-ae74-9e4bba6abf43', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.2.jpg?alt=media&token=605068c4-2cee-4077-86eb-43d0dc7ea57a', 'https://firebasestorage.googleapis.com/v0/b/mroki-a39d3.appspot.com/o/product-image%2F10.1.jpg?alt=media&token=5317e91c-950a-421f-92fa-5570ae3f76de', 123123.0, 12, 'G');
