-- Items
INSERT INTO item_table (created_at, updated_at, description, name, quantity, thumbnail)
VALUES (now(),
        now(),
        'Kingdom Hearts 3',
        'Playstation 3 Games which collaborated with Disney',
        20,
        'https://www.itl.cat/pngfile/big/129-1296646_wallpaper-kingdom-hearts-1.jpg'),
       (now(),
        now(),
        'Final Fantasy VIII',
        'Playstation 3 Games which created by Square Enix',
        50,
        'https://c4.wallpaperflare.com/wallpaper/884/893/113/vincent-valentine-final-fantasy-final-fantasy-vii-dirge-of-cerberus-video-games-wallpaper-preview.jpg'),
       (now(),
        now(),
        'Harvest Moon - Back to Nature',
        'Game which teach us how to become a good farmer, become a family, and make a living',
        32,
        'https://wallpaperfm.com/img/original/2/a/8/7542.jpg');

-- Default Role
INSERT INTO role_table (created_at, updated_at, name)
VALUES (now(), now(), 'ADMIN'),
       (now(), now(), 'CUSTOMER');


-- User's Path
INSERT INTO permission_table (created_at, updated_at, http_method, path_uri)
VALUES (now(), now(), 'GET', '/api/v1/users'),
       (now(), now(), 'POST', '/api/v1/users/login'),
       (now(), now(), 'POST', '/api/v1/users/register'),
       (now(), now(), 'PUT', '/api/v1/users/password'),
       (now(), now(), 'PUT', '/api/v1/users/roles'),
       (now(), now(), 'DELETE', '/api/v1/users');

-- Item's Path
INSERT INTO permission_table (created_at, updated_at, http_method, path_uri)
VALUES (now(), now(), 'GET', '/api/v1/items'),
       (now(), now(), 'GET', '/api/v1/items/{id}'),
       (now(), now(), 'POST', '/api/v1/items'),
       (now(), now(), 'PUT', '/api/v1/items/{id}'),
       (now(), now(), 'DELETE', '/api/v1/items/{id}');

-- Role's Path
INSERT INTO permission_table (created_at, updated_at, http_method, path_uri)
VALUES (now(), now(), 'GET', '/api/v1/roles'),
       (now(), now(), 'POST', '/api/v1/roles'),
       (now(), now(), 'PUT', '/api/v1/roles');

-- Inserting All Permission for Admin
INSERT INTO role_permission_table (created_at, updated_at, permission_id, role_id)
SELECT now(),
       now(),
       permission_table.id,
       (SELECT id FROM role_table WHERE name LIKE 'ADMIN')
FROM permission_table;

-- Inserting All Permission for Customer
INSERT INTO role_permission_table (created_at, updated_at, permission_id, role_id)
SELECT now(),
       now(),
       permission_table.id,
       (SELECT id FROM role_table WHERE name LIKE 'CUSTOMER')
FROM permission_table
WHERE permission_table.path_uri LIKE '%items%';
