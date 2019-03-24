USE `rdp_cloud`;

INSERT INTO `users` (`u_NAME`, `u_PASSWORD`)
VALUES ('tester', 'tester');

INSERT INTO `images` (`i_ID`, `i_INSTANCE`, `i_VERSION`, `i_TIER` ,`i_CPU`, `i_RAM`, `i_STORAGE`)
VALUES (1, 'Redhat', '1.1', 'small', 10, 11, 12);

INSERT INTO `images` (`i_ID`, `i_INSTANCE`, `i_VERSION`, `i_TIER` ,`i_CPU`, `i_RAM`, `i_STORAGE`)
VALUES (2, 'Redhat', '1.2', 'medium', 20, 21, 22);

INSERT INTO `containers` (`c_NAME`, `c_DESCRIPTION`, `c_DOCKERID`, `u_NAME`, `i_ID`) 
VALUES ('redhat container', 'small container', 'dockerId232', 'tester', 1);

INSERT INTO `containers` (`c_NAME`, `c_DESCRIPTION`, `c_DOCKERID`, `u_NAME`, `i_ID`) 
VALUES ('redhat container', 'medium container', 'dockerId2452', 'tester', 2);